package com.globant.microservices.example.controller;

import java.math.BigInteger;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.globant.microservices.example.entities.User;
import com.globant.microservices.example.exception.ConflictException;
import com.globant.microservices.example.exception.NotFoundException;
import com.globant.microservices.example.resource.UserResource;
import com.globant.microservices.example.services.UserService;

@RestController
@ExposesResourceFor(UserResource.class)
@RequestMapping(value = "user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<UserResource> createUser(@RequestBody(required = true) @Valid User newUser) throws ConflictException {
		if (userService.find(newUser.getUsername()) != null) {
			logger.error("username Already exist " + newUser.getUsername());
			throw new ConflictException("Usuario con mail: " + newUser.getUsername() + " ya existe");
		}

		newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getIdentification()));
		logger.info("New User was created {}", newUser);
		final UserResource saveUser = userService.save(newUser);

		return new ResponseEntity<UserResource>(saveUser, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<UserResource> updateUser(@PathVariable(value = "id",
	required = true) BigInteger id,
			@RequestBody(required = true) @Valid User updatedUser) throws ConflictException, NotFoundException {

		UserResource user = userService.update(updatedUser, id);

		logger.info("User was updated {}", user);
		return new ResponseEntity<UserResource>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserResource> findUser(@PathVariable(value = "id",
	required = true) BigInteger id) throws NotFoundException {
		logger.info("searching information by {}", id);
		final UserResource userFound = userService.findById(id);

		return new ResponseEntity<UserResource>(userFound, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<UserResource>> searchAll() {

		logger.info("searching all ");
		List<UserResource> users = userService.findByAll();
		return new ResponseEntity<List<UserResource>>(users, HttpStatus.OK);
	}
	
	

}
