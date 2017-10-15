package com.intuit.cto.listeners;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.intuit.cto.beans.results.Clazz;
import com.intuit.cto.beans.results.ResultMetrics;
import com.intuit.cto.beans.results.TestMethod;
import com.intuit.cto.beans.results.TestNGResult;

public class TestResults {

	private static List<String> passedTests = new ArrayList<String>();
	private static List<String> failedTests = new ArrayList<String>();
	private static final String PASSED = "PASS";
	private static final String FAILED = "FAIL";
	
	public static ResultMetrics parse(String testResultXml){
		ObjectMapper objectMapper = new XmlMapper();
		TestNGResult testngResult = null;
		try {
			testngResult = objectMapper.readValue(
			        StringUtils.toEncodedString(Files.readAllBytes(Paths.get(testResultXml)), StandardCharsets.UTF_8),
			        TestNGResult.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        for(Clazz clazz : testngResult.getSuite().getTest().getClasses()){
        	for(TestMethod method : clazz.getTestMethods()){
        		if(method.getStatus().equalsIgnoreCase(PASSED))
        			passedTests.add(clazz.getName() + ":" + method.getName());
        		else if(method.getStatus().equalsIgnoreCase(FAILED))
        			failedTests.add(clazz.getName() + ":" + method.getName());
        	}
        }
        return new ResultMetrics(testngResult.getTotal(), testngResult.getPassed(), testngResult.getFailed(),
        		testngResult.getSkipped(), passedTests, failedTests);
	}
}
