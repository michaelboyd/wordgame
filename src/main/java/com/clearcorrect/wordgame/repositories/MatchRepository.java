package com.clearcorrect.wordgame.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.clearcorrect.wordgame.entities.Match;

@RepositoryRestResource(collectionResourceRel = "match", path = "match")
public interface MatchRepository extends CrudRepository<Match, Long> {
	
	public List<Match> findByActiveOrderByCreateDate(boolean active);

}
