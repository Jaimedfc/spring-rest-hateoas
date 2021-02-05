package com.capgemini.sports.hateoast;

import org.springframework.hateoas.RepresentationModel;

public abstract class AbstractHal<T, R extends RepresentationModel<?>> {

	/*
	public static void linkSelf(R dto) {
		Link link = WebMvcLinkBuilder.linkTo(T.class).slash(dto.getId()).withSelfRel();
		dto.add(link);
	}*/
}
