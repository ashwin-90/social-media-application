package com.ash.response;

public class Apiresponse {

	private String message;
	private boolean status;
	public Apiresponse(String message, boolean status) {
		super();
		this.message = message;
		this.status = status;
	}
	public Apiresponse(){
		
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
