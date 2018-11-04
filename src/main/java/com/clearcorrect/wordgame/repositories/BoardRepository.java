package com.clearcorrect.wordgame.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clearcorrect.wordgame.entities.Board;
import com.clearcorrect.wordgame.entities.Match;

@RepositoryRestResource(collectionResourceRel = "board", path = "board")
public interface BoardRepository extends CrudRepository<Board, Long> {
	
	public Board findByMatch(Match match);

}
