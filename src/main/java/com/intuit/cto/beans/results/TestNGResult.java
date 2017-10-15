package com.intuit.cto.beans.results;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonIgnoreProperties(ignoreUnknown=true)
@JacksonXmlRootElement(localName = "testng-results")
public class TestNGResult {

	@JacksonXmlProperty(localName = "skipped", isAttribute = true)
	private int skipped;
	@JacksonXmlProperty(localName = "failed", isAttribute = true)
	private int failed;
	@JacksonXmlProperty(localName = "ignored", isAttribute = true)
	private int ignored;
	@JacksonXmlProperty(localName = "passed", isAttribute = true)
	private int passed;
	@JacksonXmlProperty(localName = "total", isAttribute = true)
	private int total;
	@JacksonXmlElementWrapper(localName = "suite", useWrapping = false)
	private Suite suite;
	
	public TestNGResult(){
		
	}

	public int getSkipped() {
		return skipped;
	}

	public void setSkipped(int skipped) {
		this.skipped = skipped;
	}

	public int getFailed() {
		return failed;
	}

	public void setFailed(int failed) {
		this.failed = failed;
	}

	public int getIgnored() {
		return ignored;
	}

	public void setIgnored(int ignored) {
		this.ignored = ignored;
	}
	
	public int getPassed() {
		return passed;
	}

	public void setPassed(int passed) {
		this.passed = passed;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Suite getSuite() {
		return suite;
	}

	public void setSuite(Suite suite) {
		this.suite = suite;
	}

}
