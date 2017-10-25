package com.intuit.cto.restclient;

import org.apache.log4j.Logger;

import com.intuit.cto.beans.PriorityList;
import com.intuit.cto.beans.input.InstanceConfig;
import com.intuit.cto.beans.rules.ControlRules;
import com.intuit.cto.constants.RestService;
import com.intuit.cto.utilties.JsonSerializer;

import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class FileRegistry {

	private static final String SMART_PLUGIN = "smart-plugin";
	private static final String FILE_NOT_FOUND = "FILENOTFOUND";
	private final String projectPriorities;
	private final String projectRules;
	private InstanceConfig config;
	private String projectName;
	private final static Logger logger = Logger.getLogger(FileRegistry.class);
	
	public FileRegistry(InstanceConfig config, String projectName) {
		this.config = config;
		RestAssured.baseURI = config.getProtocol() + "://" + config.getFileServer() + ":" + config.getPort();
		this.projectName = projectName;
		this.projectPriorities = projectName + "-priorities.json";
		this.projectRules = projectName + "-rules.json";
	}

	public PriorityList getPriorities() {
		logger.info("Check if priorties are defined for project " + projectName);
		RequestSpecification postRequest = RestAssured.given();
		postRequest.header(RestService.CACHE_CONTROL, RestService.NO_CACHE);
		String jsonResponse = postRequest.when().get(config.getDownloadURI() + "/" + projectPriorities).getBody().asString();
		return jsonResponse.equals(FILE_NOT_FOUND) ? null : JsonSerializer.toObject(jsonResponse, PriorityList.class);
	}
	
	public int setPriorities(PriorityList priorityList) {
		logger.info("Updating priorities for project " + projectName);
		RequestSpecification postRequest = RestAssured.given();
		postRequest.header(RestService.CACHE_CONTROL, RestService.NO_CACHE);
		postRequest.multiPart(new MultiPartSpecBuilder(JsonSerializer.toString(priorityList, PriorityList.class))
				.with().controlName(RestService.FILE)
				.and().mimeType(RestService.TEXT_PLAIN)
				.build());
		postRequest.formParam(RestService.FILE_NAME, projectPriorities);
		postRequest.formParam(RestService.CREATED_BY, SMART_PLUGIN);
		postRequest.formParam(RestService.DESCRIPTION, projectPriorities);
		Response response = postRequest.when().post(config.getUploadURI());
		return response.statusCode();
	}

	public ControlRules getRules() {
		logger.info("Getting rules defined for project " + projectName);
		RequestSpecification postRequest = RestAssured.given();
		postRequest.header(RestService.CACHE_CONTROL, RestService.NO_CACHE);
		String jsonResponse = postRequest.when().get(config.getDownloadURI() + "/" + projectRules).getBody().asString();
		return jsonResponse.equals(FILE_NOT_FOUND) ? null : JsonSerializer.toObject(jsonResponse, ControlRules.class);
	}
	
}
