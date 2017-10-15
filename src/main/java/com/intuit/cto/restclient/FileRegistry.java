package com.intuit.cto.restclient;

import org.apache.log4j.Logger;

import com.intuit.cto.beans.PriorityList;
import com.intuit.cto.beans.input.InstanceConfig;
import com.intuit.cto.beans.rules.ControlRules;
import com.intuit.cto.utilties.JsonSerializer;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class FileRegistry {

	private InstanceConfig config;
	private RequestSpecification postRequest = null;
	private final static Logger logger = Logger.getLogger(FileRegistry.class);

	public FileRegistry(InstanceConfig config) {
		this.config = config;
		RestAssured.baseURI = config.getProtocol() + "://" + config.getFileServer() + ":" + config.getPort();
		postRequest = RestAssured.given();
	}

	public PriorityList getPriorities(String projectName) {
		logger.info("Getting priority for the project - " + projectName);
		Response jsonResponse = postRequest.when().get(config.getPrioritiesURI());
		return JsonSerializer.get(jsonResponse.getBody().asString(), PriorityList.class);
	}
	
	public void setPriorities(PriorityList priorityList) {
		System.out.println(JsonSerializer.get(priorityList, PriorityList.class));
	}

	public ControlRules getRules(String projectName) {
		logger.info("Getting priority for the project - " + projectName);
		Response jsonResponse = postRequest.when().get(config.getRulesURI());
		return JsonSerializer.get(jsonResponse.getBody().asString(), ControlRules.class);
	}

}
