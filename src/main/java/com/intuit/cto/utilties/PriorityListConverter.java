package com.intuit.cto.utilties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intuit.cto.beans.PriorityList;
import com.intuit.cto.beans.TestPriority;
import com.intuit.cto.beans.TestsInClass;

public class PriorityListConverter {

	public static Map<String, Map<String, Integer>> toMap(PriorityList priorityList){
		Map<String, Map<String, Integer>> allTestPriorities = new HashMap<String, Map<String, Integer>>();
		if(priorityList != null){
			for (TestsInClass testsInClass : priorityList.getPriorities()) {
				Map<String, Integer> testPriorities = new HashMap<String, Integer>();
				for (TestPriority testPriority : testsInClass.getMethods()) {
					testPriorities.put(testPriority.getName(), testPriority.getPriority());
				}
				allTestPriorities.put(testsInClass.getName(), testPriorities);
			}
		}
		return allTestPriorities;
	}
	
	public static PriorityList toList(Map<String, Map<String, Integer>> allTestPriorities){
		PriorityList priorityList = new PriorityList();
		if(allTestPriorities.size() > 0){
			List<TestsInClass> allTestsInClass = new ArrayList<TestsInClass>();
			for(String className : allTestPriorities.keySet()){
				Map<String, Integer> testPriorities = allTestPriorities.get(className);
				List<TestPriority> priorities = new ArrayList<TestPriority>();
				for(String methodName : testPriorities.keySet()){
					TestPriority testPriority = new TestPriority(methodName, testPriorities.get(methodName));
					priorities.add(testPriority);
				}
				allTestsInClass.add(new TestsInClass(className, priorities));
			}
			priorityList.setPriorities(allTestsInClass);
		}
		return priorityList;
	}
	
}
