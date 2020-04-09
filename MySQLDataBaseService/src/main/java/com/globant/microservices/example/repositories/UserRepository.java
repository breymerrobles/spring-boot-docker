package com.globant.microservices.example.repositories;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.globant.microservices.example.entities.User;


public interface UserRepository extends CrudRepository<User, BigInteger> {
	public Optional<User> findOneByUsername(String username);

	
}
