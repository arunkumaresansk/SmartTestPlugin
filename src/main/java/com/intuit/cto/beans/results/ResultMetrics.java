package com.intuit.cto.beans.results;

import java.util.List;

public class ResultMetrics {

	private int total;
	private int passed;
	private int failed;
	private int ignored;
	private int skipped;
	private List<String> passedTests;
	private List<String> failedTests;
	private boolean jobStatus;

	public ResultMetrics(int total, int passed, int failed, int ignored, int skipped, List<String> passedTests,
			List<String> failedTests) {
		this.total = total;
		this.passed = passed;
		this.failed = failed;
		this.ignored = ignored;
		this.skipped = skipped;
		this.passedTests = passedTests;
		this.failedTests = failedTests;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPassed() {
		return passed;
	}

	public void setPassed(int passed) {
		this.passed = passed;
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
	
	public int getSkipped() {
		return skipped;
	}

	public void setSkipped(int skipped) {
		this.skipped = skipped;
	}

	public List<String> getPassedTests() {
		return passedTests;
	}

	public void setPassedTests(List<String> passedTests) {
		this.passedTests = passedTests;
	}

	public List<String> getFailedTests() {
		return failedTests;
	}

	public void setFailedTests(List<String> failedTests) {
		this.failedTests = failedTests;
	}

	public boolean isJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(boolean jobStatus) {
		this.jobStatus = jobStatus;
	}
	
}
