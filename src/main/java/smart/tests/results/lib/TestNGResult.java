package smart.tests.results.lib;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "testng-results")
public class TestNGResult {

	@JacksonXmlProperty(localName = "skipped", isAttribute = true)
	private int skipped;
	@JacksonXmlProperty(localName = "failed", isAttribute = true)
	private int failed;
	@JacksonXmlProperty(localName = "passed", isAttribute = true)
	private int passed;
	@JacksonXmlProperty(localName = "total", isAttribute = true)
	private int total;

	@JsonProperty("reporter-output")
	@JacksonXmlElementWrapper(localName = "reporter-output", useWrapping = false)
	private ReporterOutput reporterOutput;
	@JacksonXmlElementWrapper(localName = "suite", useWrapping = false)
	private Suite suite;
	
	public TestNGResult(){
		
	}

	public TestNGResult(int skipped, int failed, int passed, int total, ReporterOutput reporterOutput, Suite suite) {
		super();
		this.skipped = skipped;
		this.failed = failed;
		this.passed = passed;
		this.total = total;
		this.reporterOutput = reporterOutput;
		this.suite = suite;
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

	public ReporterOutput getReporterOutput() {
		return reporterOutput;
	}

	public void setReporterOutput(ReporterOutput reporterOutput) {
		this.reporterOutput = reporterOutput;
	}

	public Suite getSuite() {
		return suite;
	}

	public void setSuite(Suite suite) {
		this.suite = suite;
	}

}
