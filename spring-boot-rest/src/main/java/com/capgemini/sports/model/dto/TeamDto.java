package com.capgemini.sports.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

public class TeamDto extends RepresentationModel<TeamDto> implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	
	private String name;
	
	private String stadium;

	public TeamDto() {
		
	}
	
	public TeamDto(int id, String name, String stadium) {
		this.id = id;
		this.name = name;
		this.stadium = stadium;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
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
		TeamDto other = (TeamDto) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
