package com.globant.microservices.example.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.globant.microservices.example.entities.User;


public interface UserRepository extends MongoRepository<User, String> {
	public Optional<User> findOneByUsername(String username);

	@Query("{$or: [{ username: { $regex: ?0 , $options: 'i'} }, {fullName : { $regex: ?0 , $options: 'i'}},"
			+ " {identification : ?0 }] }, " + "  $orderby: {fullName: 1} ")
	public List<User> findByRegexQuery(final String value);
}
