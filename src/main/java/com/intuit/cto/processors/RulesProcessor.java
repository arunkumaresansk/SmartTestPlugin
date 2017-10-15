package com.intuit.cto.processors;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.intuit.cto.beans.results.ResultMetrics;
import com.intuit.cto.beans.rules.ControlRules;

public class RulesProcessor {

	private static ObjectMapper mapper = new ObjectMapper();

	public static void process(ControlRules controlRules, ResultMetrics result,
			Map<Integer, List<String>> testPriorities) throws JsonGenerationException, JsonMappingException, IOException{
		boolean jenkinsJobSuccessStatus = true;
		boolean isPrioritySucessful = true;
		int testSuccessPercentage = Math.round(result.getPassed() * 100 / result.getTotal());
		System.out.println("-----------------------RULES-----------------------");
		System.out.println("Expected Success Percentage - " + controlRules.getPassPercentage() + "%");
		System.out.println("Test Priority Threshold - P" + controlRules.getPriorityThreshold());
		System.out.println("---------------------------------------------------");
		if (testSuccessPercentage < controlRules.getPassPercentage()){
			System.out.println("Validation rule for Success% FAILED: " + testSuccessPercentage + "%");
			jenkinsJobSuccessStatus = false;
		}else
			System.out.println("Validation rule for Success% PASSED: " + testSuccessPercentage + "%");
		for (int key : testPriorities.keySet()) {
			isPrioritySucessful = true;
			for (String testMethod : testPriorities.get(key)) {
				if (result.getFailedTests().contains(testMethod)) {
					isPrioritySucessful = false;
					if(key <= controlRules.getPriorityThreshold()){
						jenkinsJobSuccessStatus = false;
						break;
					}
				}
			}
			if(isPrioritySucessful)
				System.out.println("Validation rule for P" + key + " tests: PASSED");
			else
				System.out.println("Validation rule for P" + key + " tests: FAILED");
		}
		System.out.println("Overall Job status based on rules: " + (jenkinsJobSuccessStatus ? "PASSED" : "FAILED"));
		result.setJobStatus(jenkinsJobSuccessStatus);
		ObjectWriter jsonWriter = mapper.writer(new DefaultPrettyPrinter());
		jsonWriter.writeValue(new File("results.json"), result);
	}

}
