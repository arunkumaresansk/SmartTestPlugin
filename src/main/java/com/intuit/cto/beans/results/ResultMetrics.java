package com.intuit.cto.beans.results;

import java.util.List;

public class ResultMetrics {

	private String bu;
	private String group;
	private String projectJiraId;
	private int total;
	private int passed;
	private int failed;
	private int ignored;
	private int skipped;
	private List<String> breachedTests;
	private List<String> failedTests;
	private List<String> rulesViolated;
	private String executionTime;
	private boolean qualitySucceed;

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

	public String getBu() {
		return bu;
	}

	public void setBu(String bu) {
		this.bu = bu;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getProjectJiraId() {
		return projectJiraId;
	}

	public void setProjectJiraId(String projectJiraId) {
		this.projectJiraId = projectJiraId;
	}

	public String getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(String executionTime) {
		this.executionTime = executionTime;
	}

	public boolean isQualitySucceed() {
		return qualitySucceed;
	}

	public void setQualitySucceed(boolean qualitySucceed) {
		this.qualitySucceed = qualitySucceed;
	}

}
