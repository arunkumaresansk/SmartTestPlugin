package com.intuit.cto.processors;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.intuit.cto.beans.results.ResultMetrics;
import com.intuit.cto.beans.results.TestNGResult;
import com.intuit.cto.utilties.JsonSerializer;
import com.intuit.cto.utilties.XMLSerializer;

public class ResultProcessor {

	private static ResultProcessor resultProcessor = new ResultProcessor();
	private static RulesProcessor rulesProcessor = RulesProcessor.getInstance();
	private List<String> breachedTests = new ArrayList<String>();
	private List<String> failedTests = new ArrayList<String>();
	private List<String> violatedRules = new ArrayList<String>();

	public static ResultProcessor getInstance() {
		return resultProcessor;
	}

	public void updateBreachedTests(String testCase) {
		breachedTests.add(testCase);
	}

	public void updateFailedTests(String testCase) {
		failedTests.add(testCase);
	}

	public void updateViolatedRules(String ruleName) {
		violatedRules.add(ruleName);
	}

	public void finalize() {
		ResultMetrics result = new ResultMetrics();
		TestNGResult testngResult = XMLSerializer
				.toObject(System.getProperty("user.dir") + "/test-output/testng-results.xml", TestNGResult.class);
		result.setTotal(testngResult.getTotal());
		result.setPassed(testngResult.getPassed());
		result.setFailed(testngResult.getFailed());
		result.setSkipped(testngResult.getSkipped());
		result.setIgnored(testngResult.getIgnored());
		result.setBreachedTests(breachedTests);
		result.setFailedTests(failedTests);
		result.setRulesViolated(violatedRules);
		rulesProcessor.validatePercentageRule(Math.round(testngResult.getPassed() * 100 / result.getTotal()));
		result.setJobStatus((rulesProcessor.isRuleBreached() || rulesProcessor.isExecutionAborted()) ? false : true);
		JsonSerializer.toFile(new File("result.json"), result, ResultMetrics.class);
	}

	private ResultProcessor() {

	}

}
