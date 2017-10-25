package com.intuit.cto.listeners;

import java.io.File;

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
	
	private FileRegistry registry;
	private String projectName;
	private static final String PROJECT_NAME = "project.name";
	PriorityProcessor priorityProcessor = PriorityProcessor.getInstance();
	ResultProcessor resultProcessor = ResultProcessor.getInstance();
	RulesProcessor rulesProcessor = RulesProcessor.getInstance();
	private final static Logger logger = Logger.getLogger(ExecutionListener.class);
	
	public ExecutionListener(){
		projectName = System.getProperty(PROJECT_NAME).toLowerCase();
	}
	
	@Override
	public void onExecutionStart() {
		InstanceConfig config = new InstanceConfig();
		registry = new FileRegistry(config, projectName);
		PriorityList priorityList = registry.getPriorities();
		priorityProcessor.setBasePriorities(priorityList);
		ControlRules rules = registry.getRules();
		if(rules == null){
			rulesProcessor.setExecutionAborted("NO RULES SET: Set rules for " + projectName + " before proceeding.");
		}else{
			rulesProcessor.setRules(rules);
		}
	}

	@Override
	public void onExecutionFinish() {
		ResultMetrics result = resultProcessor.finalise();
		JsonSerializer.toFile(new File(projectName + "-result.json"), result, ResultMetrics.class);
		if(registry.setPriorities(PriorityListConverter.toList(priorityProcessor.getPriorities())) != HttpCodes.SUCCESS)
			logger.error("Failed to update priorities for " + projectName);
	}

}
