package com.intuit.cto.processors;

import java.util.ArrayList;
import java.util.List;

import com.intuit.cto.beans.results.Clazz;
import com.intuit.cto.beans.results.ResultMetrics;
import com.intuit.cto.beans.results.TestMethod;
import com.intuit.cto.beans.results.TestNGResult;
import com.intuit.cto.utilties.XMLSerializer;

public class ResultProcessor {

	private static ResultProcessor resultProcessor = new ResultProcessor();
	private static List<String> passedTests = new ArrayList<String>();
	private static List<String> failedTests = new ArrayList<String>();
	private static final String PASSED = "PASS";
	private static final String FAILED = "FAIL";
	private ResultMetrics result;
	
	public void getMetrics(){
		TestNGResult testngResult = XMLSerializer.toObject(System.getProperty("user.dir") + "/test-output/testng-results.xml", TestNGResult.class);
		for(Clazz clazz : testngResult.getSuite().getTest().getClasses()){
        	for(TestMethod method : clazz.getTestMethods()){
        		if(method.getStatus().equalsIgnoreCase(PASSED))
        			passedTests.add(clazz.getName() + ":" + method.getName());
        		else if(method.getStatus().equalsIgnoreCase(FAILED))
        			failedTests.add(clazz.getName() + ":" + method.getName());
        	}
        }
		result = new ResultMetrics(testngResult.getTotal(), testngResult.getPassed(), testngResult.getFailed(),
        		testngResult.getIgnored(), testngResult.getSkipped(), passedTests, failedTests);
		System.out.println(result.getTotal());
		System.out.println(result.getPassed());
		System.out.println(result.getFailed());
		System.out.println(result.getPassedTests());
		System.out.println(result.getFailedTests());
	}

	public static ResultProcessor getInstance() {
		return resultProcessor;
	}
	
	private ResultProcessor() {

	}
}
