package com.clearcorrect.wordgame.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Board {
	
	public Board() {
		super();
	}
	
	public Board(Integer size) {
		super();
		this.size = size;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
    private Integer size;
 
	//@OneToOne(fetch = FetchType.LAZY, optional = false)
	@OneToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}	

}
