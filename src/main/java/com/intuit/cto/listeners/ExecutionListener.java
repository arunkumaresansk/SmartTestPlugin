package com.intuit.cto.listeners;

import org.apache.log4j.Logger;
import org.testng.IExecutionListener;

import com.intuit.cto.beans.PriorityList;
import com.intuit.cto.beans.input.InstanceConfig;
import com.intuit.cto.beans.rules.ControlRules;
import com.intuit.cto.processors.PriorityProcessor;
import com.intuit.cto.processors.ResultProcessor;
import com.intuit.cto.processors.RulesProcessor;
import com.intuit.cto.restclient.FileRegistry;
import com.intuit.cto.utilties.PriorityListConverter;

public class ExecutionListener implements IExecutionListener {
	
	private FileRegistry registry;
	private static final String PROJECT_PRIORITIES = "project.priority";
	private static final String PROJECT_RULES = "project.rules";
	PriorityProcessor priorityProcessor = PriorityProcessor.getInstance();
	ResultProcessor resultProcessor = ResultProcessor.getInstance();
	RulesProcessor rulesProcessor = RulesProcessor.getInstance();
	private final static Logger logger = Logger.getLogger(ExecutionListener.class);
	
	@Override
	public void onExecutionStart() {
		InstanceConfig config = new InstanceConfig();
		registry = new FileRegistry(config);
		PriorityList priorityList = registry.getPriorities(System.getProperty(PROJECT_PRIORITIES));
		priorityProcessor.setBasePriorities(priorityList);
		ControlRules rules = registry.getRules(System.getProperty(PROJECT_RULES));
		rulesProcessor.setRules(rules);
		if(rules == null){
			logger.error("NO RULES SET: Set the rules before proceeding...");
			resultProcessor.finalize();
			System.exit(1);
		}
	}

	@Override
	public void onExecutionFinish() {
		registry.setPriorities(PriorityListConverter.toList(priorityProcessor.getPriorities()));
		resultProcessor.setExecutionCompleted(true);
		resultProcessor.finalize();
	}

}
