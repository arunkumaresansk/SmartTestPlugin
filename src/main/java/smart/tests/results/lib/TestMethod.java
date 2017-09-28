package smart.tests.results.lib;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TestMethod {
	@JacksonXmlProperty(localName = "status", isAttribute = true)
	private String status;
	@JacksonXmlProperty(localName = "signature", isAttribute = true)
	private String signature;
	@JacksonXmlProperty(localName = "name", isAttribute = true)
	private String name;
	@JacksonXmlProperty(localName = "duration-ms", isAttribute = true)
	private String duration;
	@JacksonXmlProperty(localName = "started-at", isAttribute = true)
	private String startedAt;
	@JacksonXmlProperty(localName = "finished-at", isAttribute = true)
	private String finishedAt;

	@JsonProperty("reporter-output")
	@JacksonXmlElementWrapper(localName = "reporter-output", useWrapping = false)
	private ReporterOutput reporterOutput;
	
	public TestMethod(){
		
	}
	
	public TestMethod(String status, String signature, String name, String duration, String startedAt,
			String finishedAt) {
		super();
		this.status = status;
		this.signature = signature;
		this.name = name;
		this.duration = duration;
		this.startedAt = startedAt;
		this.finishedAt = finishedAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
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

}
