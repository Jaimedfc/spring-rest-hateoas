package com.capgemini.sports.model.dto;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

public class PlayerDto extends RepresentationModel<PlayerDto> implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private TeamDto teamDto;

	public PlayerDto(int id, String firstName, String lastName, TeamDto teamDto) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.teamDto = teamDto;
	}
	
	public PlayerDto() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public TeamDto getTeamDto() {
		return teamDto;
	}
	public void setTeamDto(TeamDto teamDto) {
		this.teamDto = teamDto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayerDto other = (PlayerDto) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
