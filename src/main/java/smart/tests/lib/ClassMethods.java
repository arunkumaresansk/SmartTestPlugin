package smart.tests.lib;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClassMethods {

	@JsonProperty("class")
	private String name;
	private List<MethodPriority> methods;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MethodPriority> getMethods() {
		return methods;
	}

	public void setMethods(List<MethodPriority> methods) {
		this.methods = methods;
	}

}
