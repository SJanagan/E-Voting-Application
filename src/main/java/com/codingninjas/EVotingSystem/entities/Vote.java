package com.codingninjas.EVotingSystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Vote {

	// declare the attributes here

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	long id;

	@OneToOne(cascade = CascadeType.ALL)
    User user ;

	@ManyToOne(cascade = CascadeType.ALL)
//	@JsonBackReference
	@JoinColumn(name = "election_id")
	Election election;

	@ManyToOne(cascade = CascadeType.ALL)
//	@JsonBackReference
	@JoinColumn(name = "electionChoice_id")
 	ElectionChoice electionChoice;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}

	public ElectionChoice getElectionChoice() {
		return electionChoice;
	}

	public void setElectionChoice(ElectionChoice electionChoice) {
		this.electionChoice = electionChoice;
	}

}
