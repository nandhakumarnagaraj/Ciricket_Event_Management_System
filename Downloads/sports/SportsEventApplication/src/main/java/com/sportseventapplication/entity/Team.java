package com.sportseventapplication.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="tbl_team")
@Data
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String teamName;
	private String Coach;
	
	@OneToMany(mappedBy = "team")
	private List<Player> players;
	
	@OneToOne
	@JoinColumn(name="captain_Id")
	private Player captain;
	
	@ManyToOne
	@JoinColumn(name = "tournament_Id")
	@JsonIgnore
	private Tournament tour;

	public Team(long id, String teamName, String coach, List<Player> players, Player captain) {
		super();
		this.id = id;
		this.teamName = teamName;
		this.Coach = coach;
		this.players = players;
		this.captain = captain;
	}

	public Team() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getCoach() {
		return Coach;
	}

	public void setCoach(String coach) {
		Coach = coach;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Player getPlayer() {
		return captain;
	}

	public void setPlayer(Player player) {
		this.captain = captain;
	}
	
}
