package com.codingninjas.EVotingSystem.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Election {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@Column(unique=true)
	private String name;

//	@OneToMany(mappedBy = "election",cascade = CascadeType.ALL)
//	@JsonManagedReference
//	List<Vote> votes;

//	@OneToMany(mappedBy = "election",cascade = CascadeType.ALL)
//	List<ElectionChoice> electionChoices;





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
}
