package com.intuit.cto.beans.results;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ReporterOutput {
	
	private String name;
	
	public ReporterOutput(){
		
	}

	public ReporterOutput(String name){
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
