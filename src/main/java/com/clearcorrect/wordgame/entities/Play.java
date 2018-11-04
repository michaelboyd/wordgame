package com.clearcorrect.wordgame.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Play {
	
	public Play() {
		super();
	}
	
	public Play(Integer x, Integer y, String word, String direction, Match match) {
		super();
		this.x = x;
		this.y = y;
		this.word = word;
		this.direction = direction;
		this.match = match;
		this.createDate = new Date();		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;	
	
	@Column(nullable = false)
    private Integer x;
	
	@Column(nullable = false)
    private Integer y;
	
	@Column(nullable = false)
    private String word;
	
	@Column(nullable = false)
    private String direction;

	@ManyToOne
    @JoinColumn(name="match_id")
    private Match match;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

}
