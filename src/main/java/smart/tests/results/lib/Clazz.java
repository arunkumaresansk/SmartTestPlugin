package smart.tests.results.lib;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Clazz {

	@JacksonXmlProperty(localName = "name", isAttribute = true)
	private String name;

	@JsonProperty("test-method")
	@JacksonXmlElementWrapper(localName = "test-method", useWrapping = false)
	private TestMethod[] testMethods;

	public Clazz() {

	}

	public Clazz(String name, TestMethod[] testMethods) {
		super();
		this.name = name;
		this.testMethods = testMethods;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TestMethod[] getTestMethods() {
		return testMethods;
	}

	public void setTestMethods(TestMethod[] testMethods) {
		this.testMethods = testMethods;
	}

}
