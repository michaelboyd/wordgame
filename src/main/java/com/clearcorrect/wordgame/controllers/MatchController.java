package com.clearcorrect.wordgame.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clearcorrect.wordgame.entities.Board;
import com.clearcorrect.wordgame.entities.Match;
import com.clearcorrect.wordgame.entities.Play;
import com.clearcorrect.wordgame.repositories.BoardRepository;
import com.clearcorrect.wordgame.repositories.MatchRepository;
import com.clearcorrect.wordgame.repositories.PlayRepository;
import com.clearcorrect.wordgame.utils.BoardUtil;
import com.clearcorrect.wordgame.utils.PlayUtil;

@RestController
@RequestMapping("/api/match")
public class MatchController {

	private static final Logger LOGGER = LogManager.getLogger();
	private final static int DEFAULT_BOARD_SIZE = 15;

	@Autowired
	private MatchRepository matchRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired 
	private PlayRepository playRepository;
	
	@GetMapping("/list")
	public List<Match> listMatches(@RequestParam(name = "active", required=false) String active) {
	    Boolean result = StringUtils.isEmpty(active) ? true : Boolean.valueOf(active);
		List<Match> matches = matchRepository.findByActiveOrderByCreateDate(result);
		return matches;
	}

	@PostMapping("/create")
	public ResponseEntity <Match>  createMatch(@RequestParam Optional<Integer> boardSize) throws Exception {
		Match match = new Match(true);
		int size = boardSize.isPresent() ? boardSize.get() : DEFAULT_BOARD_SIZE;
		if(size < 0) {
			return ResponseEntity.badRequest().build();
		}
		matchRepository.save(match);
		Board board = new Board(size);
		board.setMatch(match);
		boardRepository.save(board);
		LOGGER.info("created match with id: " + match.getId());
		return new ResponseEntity<>(match, HttpStatus.CREATED);
	}

	@GetMapping(path = "/{id}/board", produces = "text/plain")
	public String getMatchBoard(@PathVariable long id) {
		Optional<Match> matchOptional = matchRepository.findById(id);
		if (matchOptional.isPresent()) {
			Board board = boardRepository.findByMatch(matchOptional.get());
			List <Play> plays = playRepository.findByMatchOrderByCreateDateDesc(matchOptional.get());
			String table = BoardUtil.getBoard(board.getSize(), plays);
			return table;
		}
		else 
		{
			return "Game was not found";	
		}
	}

	@PutMapping("/{id}/update")
	public ResponseEntity<Object> updateMatch(@RequestParam Optional<Boolean> active, @PathVariable long id) {
		Optional<Match> matchOptional = matchRepository.findById(id);
		if (!matchOptional.isPresent())
			return ResponseEntity.notFound().build();
		if (!active.isPresent())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		matchOptional.get().setActive(active.get());
		matchRepository.save(matchOptional.get());
		return new ResponseEntity<>(matchOptional.get(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<Object> deleteMatch(@PathVariable long id) {
		Optional<Match> match = matchRepository.findById(id);
		if (match.isPresent()) {
			Board board = boardRepository.findByMatch(match.get());
			boardRepository.delete(board);
			List <Play> plays = playRepository.findByMatchOrderByCreateDateDesc(match.get());
			for(Play play : plays) {
				playRepository.delete(play);
			}
			matchRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/{id}/play")
	public ResponseEntity <Play> createPlay(@RequestBody Play play, @PathVariable long id) throws Exception {
		Optional<Match> matchOptional = matchRepository.findById(id);
		if (!matchOptional.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Board board = boardRepository.findByMatch(matchOptional.get());
		List <Play> plays = playRepository.findByMatchOrderByCreateDateDesc(matchOptional.get());
		if(PlayUtil.isValid(play, board.getSize(), plays)) {
			play.setMatch(matchOptional.get());
			play.setCreateDate(new Date());
			playRepository.save(play);
			LOGGER.info("created play with id: " + play.getId());
			return new ResponseEntity<>(play, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}	
	
	@GetMapping("/{id}/play/history")
	public List<Play> getPlayHistory(@PathVariable long id) throws Exception {
		Optional<Match> matchOptional = matchRepository.findById(id);
		if (!matchOptional.isPresent())
			return null;		
		List<Play> plays = playRepository.findByMatchOrderByCreateDateDesc(matchOptional.get());
		return plays;
	}	
	
}
