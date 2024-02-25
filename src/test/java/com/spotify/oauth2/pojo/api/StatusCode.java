package com.spotify.oauth2.pojo.api;

public enum StatusCode {
	
	CODE_200(200, ""),
	CODE_201(201, ""),
	CODE_400(400, "Missing required field: name"),
	CODE_401(401, "Invalid access token");
	
	private final int statusCode;
	private final String message;
	
	StatusCode(int statusCode, String message){
		this.statusCode=statusCode;
		this.message=message;
	}
	
	
	public int getCode() {
		return statusCode;
	}

	public String getMessage() {
		return message;
	}
	
}
