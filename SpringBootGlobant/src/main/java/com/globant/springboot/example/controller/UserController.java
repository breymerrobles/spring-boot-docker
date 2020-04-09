package com.globant.springboot.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.globant.springboot.example.domain.UserDTO;

@RestController
@RequestMapping("user")
public class UserController {
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getUsers() {
		logger.info("Starting getter user");
		final List<UserDTO> users = new ArrayList<UserDTO>();
		users.add(UserDTO.builder().id(1).name("User v1").build());
		users.add(UserDTO.builder().id(2).name("User v2").build());
		users.add(UserDTO.builder().id(3).name("User v3").build());
		users.add(UserDTO.builder().id(4).name("User v4").build());
		logger.info("response  user {}" , users.size());
		return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
	}

}
