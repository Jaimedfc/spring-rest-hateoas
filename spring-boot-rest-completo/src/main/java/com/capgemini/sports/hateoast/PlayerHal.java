package com.capgemini.sports.hateoast;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.capgemini.sports.model.dto.PlayerDto;
import com.capgemini.sports.services.PlayerRest;

public class PlayerHal {

	// /players/{id}
	public static void linkSelf(PlayerDto dto) {
		Link link = WebMvcLinkBuilder.linkTo(PlayerRest.class).slash(dto.getId()).withSelfRel();
		dto.add(link);
	}

	// /players
	public static void link2Players(PlayerDto dto) {
		Link link = WebMvcLinkBuilder.linkTo(PlayerRest.class).withRel("allPlayers");
		dto.add(link);
	}
}
