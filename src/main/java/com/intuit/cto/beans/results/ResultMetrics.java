package com.intuit.cto.beans.results;

import java.util.List;

public class ResultMetrics {

	private int total;
	private int passed;
	private int failed;
	private int ignored;
	private int skipped;
	private List<String> breachedTests;
	private List<String> failedTests;
	private List<String> rulesViolated;
	private boolean jobStatus;

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

	public List<String> getBreachedTests() {
		return breachedTests;
	}

	public void setBreachedTests(List<String> breachedTests) {
		this.breachedTests = breachedTests;
	}

	public List<String> getFailedTests() {
		return failedTests;
	}

	public void setFailedTests(List<String> failedTests) {
		this.failedTests = failedTests;
	}

	public List<String> getRulesViolated() {
		return rulesViolated;
	}

	public void setRulesViolated(List<String> rulesViolated) {
		this.rulesViolated = rulesViolated;
	}

	public boolean isJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(boolean jobStatus) {
		this.jobStatus = jobStatus;
	}
	
}
