package com.codingninjas.EVotingSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.data.repository.cdi.Eager;

import java.util.List;

@Entity
public class ElectionChoice {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String name;

	@ManyToOne(cascade = CascadeType.ALL)
//	@JsonIgnore
	@JoinColumn(name = "election_id")
	private Election election;

//	@OneToMany(mappedBy = "electionChoice", cascade=CascadeType.ALL)
//	List<Vote> votes;

//	public List<Vote> getVotes() {
//		return votes;
//	}
//
//	public void setVotes(List<Vote> votes) {
//		this.votes = votes;
//	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}
}
