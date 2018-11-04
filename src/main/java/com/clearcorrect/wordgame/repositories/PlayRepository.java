package com.clearcorrect.wordgame.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clearcorrect.wordgame.entities.Match;
import com.clearcorrect.wordgame.entities.Play;

@RepositoryRestResource(collectionResourceRel = "play", path = "play")
public interface PlayRepository extends CrudRepository<Play, Long> {
	
	public List<Play> findByMatchOrderByCreateDateDesc(Match match);

}
