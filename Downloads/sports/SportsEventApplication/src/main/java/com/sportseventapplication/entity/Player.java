package com.sportseventapplication.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="tblplayer")
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String playerName;
	private int age;
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Position position;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
	@ManyToOne
	@JoinColumn(name="team_Id")
	@JsonIgnore
	private Team team;
	
	public Player(String playerName) {
		
		this.playerName = playerName;
	}
	
	

	public Player(long id, String playerName, int age, String email, Position position, List<Team> teams, Team team) {
		
		this.id = id;
		this.playerName = playerName;
		this.age = age;
		this.email = email;
		this.position = position;
		this.team = team;
	}

	public Player() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	
	public Team getTeam() {
		return team;
	}

	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public void setTeam(Team team) {
		this.team = team;
	}
	
}
