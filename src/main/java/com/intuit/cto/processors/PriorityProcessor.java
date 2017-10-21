package com.intuit.cto.processors;

import java.util.HashMap;
import java.util.Map;

import com.intuit.cto.beans.PriorityList;
import com.intuit.cto.utilties.PriorityListConverter;

public class PriorityProcessor {

	private static Map<String, Map<String, Integer>> allTestPriorities;
	private static PriorityProcessor priorityProcessor = new PriorityProcessor();

	public static PriorityProcessor getInstance() {
		return priorityProcessor;
	}
	
	public void setBasePriorities(PriorityList priorityList) {
		allTestPriorities = PriorityListConverter.toMap(priorityList);
	}
	
	public int getPriority(String className, String methodName){
		return allTestPriorities.get(className).get(methodName);
	}

	public Map<String, Map<String, Integer>> getPriorities() {
		return allTestPriorities;
	}

	public void addPriority(String className, String methodName) {
		Map<String, Integer> testPriorities = allTestPriorities.get(className);
		if (testPriorities == null) {
			testPriorities = new HashMap<String, Integer>();
			testPriorities.put(methodName, 0);
			allTestPriorities.put(className, testPriorities);
		} else {
			Integer priority = testPriorities.get(methodName);
			if (priority == null) {
				testPriorities.put(methodName, 0);
			}
		}
	}

	private PriorityProcessor() {

	}
	
}
