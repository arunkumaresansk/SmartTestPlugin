package smart.tests.rules.lib;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ControlRules {

	@JsonProperty("priorityThreshold")
	private int priorityThreshold;
	
	@JsonProperty("passPercentage")
	private int passPercentage;

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
