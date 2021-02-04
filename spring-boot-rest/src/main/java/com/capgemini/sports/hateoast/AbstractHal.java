package com.capgemini.sports.hateoast;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.capgemini.sports.model.dto.PlayerDto;
import com.capgemini.sports.model.dto.TeamDto;
import com.capgemini.sports.services.PlayerRest;
import com.capgemini.sports.services.TeamRest;

public abstract class AbstractHal<T, R extends RepresentationModel<?>> {

	/*
	public static void linkSelf(R dto) {
		Link link = WebMvcLinkBuilder.linkTo(T.class).slash(dto.getId()).withSelfRel();
		dto.add(link);
	}*/
}
