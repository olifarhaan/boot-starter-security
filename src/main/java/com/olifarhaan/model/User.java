package com.olifarhaan.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.olifarhaan.request.UserRegistrationRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	private String fullName;

	@Column(unique = true)
	private String email;

	@JsonIgnore
	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	private String phoneNumber;

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	private LocalDate dateOfBirth;

	public enum Role {
		ADMIN,
		USER
	}

	public enum Gender {
		MALE,
		FEMALE,
		OTHER,
		NOT_SPECIFIED
	}

	public User(UserRegistrationRequest request, String encodedPassword) {
		this.fullName = request.getFullName();
		this.email = request.getEmail();
		this.password = encodedPassword;
		this.role = Role.USER;
	}
}
