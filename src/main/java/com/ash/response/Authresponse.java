package com.ash.response;

public class Authresponse {
	private String token ;
	private String message;

	public Authresponse() {
		
	}

	public Authresponse(String token, String message) {
		super();
		this.token = token;
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
