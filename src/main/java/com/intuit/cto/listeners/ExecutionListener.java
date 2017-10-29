package com.intuit.cto.listeners;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.testng.IExecutionListener;

import com.intuit.cto.beans.PriorityList;
import com.intuit.cto.beans.input.InstanceConfig;
import com.intuit.cto.beans.results.ResultMetrics;
import com.intuit.cto.beans.rules.ControlRules;
import com.intuit.cto.constants.HttpCodes;
import com.intuit.cto.processors.PriorityProcessor;
import com.intuit.cto.processors.ResultProcessor;
import com.intuit.cto.processors.RulesProcessor;
import com.intuit.cto.restclient.FileRegistry;
import com.intuit.cto.utilties.JsonSerializer;
import com.intuit.cto.utilties.PriorityListConverter;

public class ExecutionListener implements IExecutionListener {

	private static final String BU_NAME = "bu.name";
	private static final String GROUP_NAME = "group.name";
	private static final String PROJECT_JIRA_ID = "project.jiraId";

	private FileRegistry registry;
	private String projectName, buName, groupName, executionTime;
	PriorityProcessor priorityProcessor = PriorityProcessor.getInstance();
	ResultProcessor resultProcessor = ResultProcessor.getInstance();
	RulesProcessor rulesProcessor = RulesProcessor.getInstance();
	private final static Logger logger = Logger.getLogger(ExecutionListener.class);

	public ExecutionListener() {
		DateFormat formatter = new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss aa zzz");
		executionTime = formatter.format(System.currentTimeMillis());
		projectName = System.getProperty(PROJECT_JIRA_ID);
		buName = System.getProperty(BU_NAME);
		groupName = System.getProperty(GROUP_NAME);
		if (buName == null || groupName == null || projectName == null) {
			logger.error("Missing Value(s): Set a valid " + BU_NAME + ", " + GROUP_NAME + ", " + PROJECT_JIRA_ID + " to continue...");
			System.exit(1);
		}
	}

	@Override
	public void onExecutionStart() {
		InstanceConfig config = new InstanceConfig();
		registry = new FileRegistry(config, projectName);
		PriorityList priorityList = registry.getPriorities();
		priorityProcessor.setBasePriorities(priorityList);
		ControlRules rules = registry.getRules();
		if (rules == null) {
			rulesProcessor.setExecutionAborted("NO RULES SET: Set rules for " + projectName + " before proceeding.");
		} else {
			rulesProcessor.setRules(rules);
		}
	}

	@Override
	public void onExecutionFinish() {
		ResultMetrics result = resultProcessor.finalise();
		result.setBu(buName);
		result.setGroup(groupName);
		result.setProjectJiraId(projectName);
		result.setExecutionStartTime(executionTime);
		JsonSerializer.toFile(new File(projectName.toLowerCase() + "-result.json"), result, ResultMetrics.class);
		if (registry.setPriorities(PriorityListConverter.toList(priorityProcessor.getPriorities())) != HttpCodes.SUCCESS)
			logger.error("Failed to update priorities for " + projectName);
	}

}
