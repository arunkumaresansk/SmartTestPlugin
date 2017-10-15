package com.intuit.cto.beans.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;


public class Test {

	@JacksonXmlProperty(localName = "name", isAttribute = true)
	private String name;
	@JacksonXmlProperty(localName = "duration-ms", isAttribute = true)
	private int duration;
	@JacksonXmlProperty(localName = "started-at", isAttribute = true)
	private String startedAt;
	@JacksonXmlProperty(localName = "finished-at", isAttribute = true)
	private String finishedAt;

	@JsonProperty("class")
	@JacksonXmlElementWrapper(localName = "class", useWrapping = false)
	private Clazz[] classes;
	
	public Test(){
		
	}
	
	public Test(String name, int duration, String startedAt, String finishedAt, Clazz[] classes) {
		super();
		this.name = name;
		this.duration = duration;
		this.startedAt = startedAt;
		this.finishedAt = finishedAt;
		this.classes = classes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(String startedAt) {
		this.startedAt = startedAt;
	}

	public String getFinishedAt() {
		return finishedAt;
	}

	public void setFinishedAt(String finishedAt) {
		this.finishedAt = finishedAt;
	}

	public Clazz[] getClasses() {
		return classes;
	}

	public void setClasses(Clazz[] classes) {
		this.classes = classes;
	}

}
