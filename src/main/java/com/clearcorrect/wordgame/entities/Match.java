package com.clearcorrect.wordgame.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Match {
	
	public Match() {
		super();
	}
	
	public Match(boolean active) {
		super();
		this.active = active;
		this.createDate = new Date();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;	
	
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;	
	
	@Column(nullable = true)
	private boolean active;

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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
