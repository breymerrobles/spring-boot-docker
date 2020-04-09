package com.globant.microservices.example.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
@Document(collection = "user")
public class User   implements Serializable {

	private static final long serialVersionUID = 5038395847610766335L;
	@Id
	
	private String id;
	@EqualsAndHashCode.Include
	private String username;
	@Setter
	@JsonProperty(access = Access.WRITE_ONLY)
	@ToString.Exclude
	private String password;
	private String role;
	private String fullName;
	private String identification;

	


	

}
