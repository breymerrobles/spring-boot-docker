package com.globant.microservices.example.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.globant.microservices.example.controller.UserController;
import com.globant.microservices.example.entities.User;
import com.globant.microservices.example.exception.ConflictException;
import com.globant.microservices.example.exception.NotFoundException;
import com.globant.microservices.example.repositories.UserRepository;
import com.globant.microservices.example.resource.UserResource;

@Service
public class UserService {
	public static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	public UserResource save(final User user) {
		try {
			final User userSaved = userRepository.save(user);
			return createResource(userSaved);
		} catch (DataAccessException e) {
			logger.error("Error creating users", e.getMessage(), e);
			throw e;
		}
	}

	public UserResource update(final User user, final BigInteger id) throws NotFoundException, ConflictException {
		try {
			if (user.getId() == null || !user.getId().equals(id)) {
				logger.error("Usuario with Id not found {}", user);
				throw new NotFoundException("User Not Found");
			}
			Optional<User> userFound = userRepository.findById(id);
			if (!userFound.isPresent()) {
				logger.error("username does not exist {}", user.getUsername());
				throw new ConflictException("Usuario con mail " + user.getUsername() + " no existe");

			}
			user.setPassword(userFound.get().getPassword());
			final User userSaved = userRepository.save(user);
			logger.info("User was updated {}", userFound);
			return createResource(userSaved);
		} catch (DataAccessException e) {
			logger.error("Error updating users", e.getMessage(), e);
			throw e;
		}
	}

	public UserResource find(final String userName) {
		try {
			final Optional<User> user = userRepository.findOneByUsername(userName);
			return user.map(u -> createResource(u)).orElse(null);
		} catch (DataAccessException e) {
			logger.error("Error getting user by username {}, {}", userName, e.getMessage(), e);
			throw e;
		}
	}

	public UserResource findById(final BigInteger id) throws NotFoundException {
		try {
			return userRepository.findById(id).map(u -> createResource(u))
					.orElseThrow(() -> new NotFoundException("Not Found user with id : " + id));
		} catch (DataAccessException e) {
			logger.error("Error getting user by ID {}, {}", id, e.getMessage(), e);
			throw e;
		}
	}

	public List<UserResource> findByAll() {
		try {
			final List<User> outs = (List<User>) userRepository.findAll();
			logger.info("Users returned {}", outs.size());
			return outs.stream().map(u -> createResource(u)).collect(Collectors.toList());
		} catch (DataAccessException e) {
			logger.error("Error getting user by query {}}", e.getMessage(), e);
			throw e;
		}
	}



	private UserResource createResource(final User userSaved) {
		UserResource userResource = new UserResource(userSaved);
		userResource.add(linkTo(methodOn(UserController.class).searchAll()).withRel("searchAll"));
		try {
			userResource.add(linkTo(methodOn(UserController.class).findUser(userSaved.getId())).withSelfRel());
		} catch (NotFoundException e) {
			logger.error("Error with link {}", e.getMessage(), e);
		}
		return userResource;
	}

}
