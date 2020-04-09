package com.globant.microservices.example.resource;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.globant.microservices.example.entities.User;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserResource extends RepresentationModel<UserResource> {

	private final User user;

	@JsonCreator
	public UserResource(@JsonProperty("user") User user) {
		this.user = user;
	}

}
