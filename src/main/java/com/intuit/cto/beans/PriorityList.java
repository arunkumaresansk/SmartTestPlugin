package com.intuit.cto.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PriorityList {

	@JsonProperty("priorities")
	private List<TestsInClass> priorities;
	
	public PriorityList(){
		
	}

	public PriorityList(List<TestsInClass> priorities) {
		this.priorities = priorities;
	}

	public List<TestsInClass> getPriorities() {
		return priorities;
	}

	public void setPriorities(List<TestsInClass> priorities) {
		this.priorities = priorities;
	}

}
