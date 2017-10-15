package com.intuit.cto.listeners;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.cto.beans.PriorityList;
import com.intuit.cto.beans.TestPriority;
import com.intuit.cto.beans.TestsInClass;

public class TestMethods {

	private static ObjectMapper mapper = new ObjectMapper();
	private static Map<Integer, List<String>> methodPriorities = new HashMap<Integer, List<String>>();
	
	public static Map<Integer, List<String>> getPriorities(String testPriorities) throws JsonParseException, JsonMappingException, IOException {
		StringBuilder jsonAsString = new StringBuilder();
		Scanner scanner = new Scanner(new File(testPriorities));
		while(scanner.hasNextLine())
			jsonAsString.append(scanner.nextLine());
		scanner.close();
		PriorityList priorityList = mapper.readValue(jsonAsString.toString(), PriorityList.class);
		for(TestsInClass cMethods : priorityList.getPriorities()){
			String className = cMethods.getName();
			for(TestPriority mPriority : cMethods.getMethods()){
				List<String> methods;
				if(methodPriorities.get(mPriority.getPriority()) == null){
					methods = new ArrayList<String>();
				} else {
					methods = methodPriorities.get(mPriority.getPriority());
				}
				methods.add(className + ":" + mPriority.getName());
				methodPriorities.put(mPriority.getPriority(), methods);
			}
		}
		return methodPriorities;
	}
	
}
