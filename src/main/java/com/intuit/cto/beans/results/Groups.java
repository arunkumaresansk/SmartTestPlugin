package com.intuit.cto.beans.results;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Groups {

	private String name;
	
	public Groups(String name) {
		this.name = name;
	}
	
	public Groups(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
