package com.intuit.cto.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestsInClass {

	@JsonProperty("class")
	private String name;
	private List<TestPriority> methods;

	public TestsInClass(String name, List<TestPriority> methods) {
		this.name = name;
		this.methods = methods;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TestPriority> getMethods() {
		return methods;
	}

	public void setMethods(List<TestPriority> methods) {
		this.methods = methods;
	}

}
