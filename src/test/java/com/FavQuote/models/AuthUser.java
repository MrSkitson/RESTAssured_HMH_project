package com.FavQuote.models;
/**
 * Represents an authentication request payLoad for FavQs API
 */
public class AuthUser {

	private User user;
	
	public AuthUser(String login, String password) {
		this.user = new User(login, password);
	}
	public User getUser() {
		return user;
	}
}

class User{
	private String login;
	private String password;
	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
}
