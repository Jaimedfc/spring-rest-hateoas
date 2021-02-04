package com.capgemini.sports.hateoast;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.capgemini.sports.model.dto.PlayerDto;
import com.capgemini.sports.services.PlayerRest;
import com.capgemini.sports.services.TeamRest;

public class PlayerHal {

	public static void linkSelf(PlayerDto dto) {
		Link link = WebMvcLinkBuilder.linkTo(PlayerRest.class).slash(dto.getId()).withSelfRel();
		dto.add(link);
	}
}
