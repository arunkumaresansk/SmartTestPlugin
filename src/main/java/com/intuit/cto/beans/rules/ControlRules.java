package com.intuit.cto.beans.rules;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ControlRules {

	@JsonProperty("priorityThreshold")
	private int priorityThreshold;

	@JsonProperty("passPercentage")
	private int passPercentage;

	@JsonProperty("abortOnFailure")
	private boolean abortOnFailure;

	public boolean isAbortOnFailure() {
		return abortOnFailure;
	}

	public void setAbortOnFailure(boolean abortOnFailure) {
		this.abortOnFailure = abortOnFailure;
	}

	public int getPriorityThreshold() {
		return priorityThreshold;
	}

	public void setPriorityThreshold(int priorityThreshold) {
		this.priorityThreshold = priorityThreshold;
	}

	public int getPassPercentage() {
		return passPercentage;
	}

	public void setPassPercentage(int passPercentage) {
		this.passPercentage = passPercentage;
	}

}
