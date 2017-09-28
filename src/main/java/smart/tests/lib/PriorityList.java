package smart.tests.lib;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PriorityList {

	@JsonProperty("priorities")
	private List<ClassMethods> priorities;
	
	public PriorityList(){
		
	}

	public PriorityList(List<ClassMethods> priorities) {
		this.priorities = priorities;
	}

	public List<ClassMethods> getPriorities() {
		return priorities;
	}

	public void setPriorities(List<ClassMethods> priorities) {
		this.priorities = priorities;
	}

}
