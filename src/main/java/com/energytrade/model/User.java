package com.energytrade.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the all_users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT a FROM User a")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@Column(name="id")
	private int userId;

	private String email;

	@Column(name="username")
	private String username;
	

	@Column(name="password")
	private String password;
	
	@Column(name="bearer_token")
	private String bearerToken;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBearerToken() {
		return bearerToken;
	}

	public void setBearerToken(String bearerToken) {
		this.bearerToken = bearerToken;
	}
	
}