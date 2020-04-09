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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "User Management System", description = "Operations configure Users ")
@RestController
@RequestMapping(value = "user")
//@RequestMapping(value = "user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@ApiOperation(value = "View a list of available Users", 
			response = List.class)

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getUsers() {

		final List<UserDTO> users = new ArrayList<UserDTO>();
		users.add(UserDTO.builder().id(1).name("User v1").build());
		users.add(UserDTO.builder().id(2).name("User v2").build());
		users.add(UserDTO.builder().id(3).name("User v3").build());
		users.add(UserDTO.builder().id(4).name("User v4").build());
		return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
	}

}
