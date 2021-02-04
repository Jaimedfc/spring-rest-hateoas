package com.capgemini.sports.hateoast;

import java.util.Optional;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.capgemini.sports.model.dto.TeamDto;
import com.capgemini.sports.services.PlayerRest;
import com.capgemini.sports.services.TeamRest;

public class TeamHal {

	// /teams/{id}
	public static void linkSelf(TeamDto dto) {
		Link link = WebMvcLinkBuilder.linkTo(TeamRest.class).slash(dto.getId()).withSelfRel();
		dto.add(link);
	}
	
	// /players?teamID=xx
	public static void link2PlayersByTeam(TeamDto dto, Optional<Integer> teamId) {
		Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PlayerRest.class).findAll(teamId)).withRel("players");
		dto.add(link);
	}
	
	// /players
	public static void link2Players(TeamDto dto) {
		Link link = WebMvcLinkBuilder.linkTo(PlayerRest.class).withRel("allPlayers");
		dto.add(link);
	}
	
	// /teams
	public static void link2Teams(TeamDto dto) {
		Link link = WebMvcLinkBuilder.linkTo(TeamRest.class).withRel("teams");
		dto.add(link);
		
	}
}
