package com.getir.readingisgoodservicerest.model;

import java.io.Serializable;

public class JwtResponse implements Serializable{

	private static final long serialVersionUID = 1714050961457730313L;
	
	
	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}
