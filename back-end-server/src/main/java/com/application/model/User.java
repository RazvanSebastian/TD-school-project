package com.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Parautiu
 *
 */

@Entity
@Table(name = "user_table")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@Column(name = "email")
	private String user;

	@NotNull
	@Column(name = "password")
	private String password;

	public User() {
		super();
	}

	public User(String user, String password) {

		this.user = user;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
