package com.globant.microservices.example.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "user")
public class User   implements Serializable {

	private static final long serialVersionUID = 5038395847610766335L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger id;
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
