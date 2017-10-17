package com.intuit.cto.processors;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.intuit.cto.beans.PriorityList;
import com.intuit.cto.utilties.PriorityListConverter;

public class PriorityProcessor {

	private boolean isBasePrioritiesDefined = false;
	private static Map<String, Map<String, Integer>> allTestPriorities;
	private static PriorityProcessor priorityProcessor = new PriorityProcessor();
	private final static Logger logger = Logger.getLogger(PriorityProcessor.class);

	public static PriorityProcessor getInstance() {
		return priorityProcessor;
	}

	public boolean isBasePrioritiesDefined() {
		return isBasePrioritiesDefined;
	}
	
	public void setBasePriorities(PriorityList priorityList) {
		allTestPriorities = PriorityListConverter.toMap(priorityList);
		isBasePrioritiesDefined = allTestPriorities.size() == 0 ? false : true;
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
			logger.info("Adding class to the priority list: " + className);
			testPriorities = new HashMap<String, Integer>();
			testPriorities.put(methodName, 0);
			allTestPriorities.put(className, testPriorities);
		} else {
			Integer priority = testPriorities.get(methodName);
			if (priority == null) {
				logger.info("Adding priority for the method: " + methodName);
				testPriorities.put(methodName, 0);
			}
		}
	}

	private PriorityProcessor() {

	}
	
}
