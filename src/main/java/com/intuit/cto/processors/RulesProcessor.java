package com.intuit.cto.processors;

import org.apache.log4j.Logger;

import com.intuit.cto.beans.rules.ControlRules;

public class RulesProcessor {

	private static final String priorityRuleName = "priorityThreshold";
	private static final String percentageRuleName = "passPercentage";
	private static RulesProcessor rulesProcessor = new RulesProcessor();
	private static ResultProcessor resultProcessor = ResultProcessor.getInstance();
	private boolean priorityRuleBreached = false;
	private boolean percentageRuleBreached = false;
	private boolean isExecutionAborted = false;
	private boolean abortOnFailure = false;
	private int passPercentageThreshold = 0;
	private int priorityThreshold = 0;
	private final static Logger logger = Logger.getLogger(RulesProcessor.class);

	public static RulesProcessor getInstance() {
		return rulesProcessor;
	}

	public void setRules(ControlRules rules) {
		abortOnFailure = rules.isAbortOnFailure();
		passPercentageThreshold = rules.getPassPercentage();
		priorityThreshold = rules.getPriorityThreshold();
	}
	
	public boolean isRuleBreached(){
		return priorityRuleBreached || percentageRuleBreached;
	}
	
	public boolean isExecutionAborted(){
		return isExecutionAborted;
	}

	public void setExecutionAborted(String reason) {
		logger.error(reason);
		isExecutionAborted = true;
	}
	
	public void validatePriorityRule(String method, int priority) {
		if (priority <= priorityThreshold){
			setPriorityRuleBreached();
			resultProcessor.updateBreachedTests(method);
			if(abortOnFailure)
				setExecutionAborted("PRIORITY RULE BREACHED: Skipping all the remaining test cases");
		}
	}

	public void validatePercentageRule(int passPercentage) {
		if (passPercentage < passPercentageThreshold)
			setPercentageRuleBreached();
	}

	private void setPriorityRuleBreached() {
		if(!priorityRuleBreached){
			priorityRuleBreached = true;
			resultProcessor.updateViolatedRules(priorityRuleName);
		}
	}

	private void setPercentageRuleBreached() {
		if(!percentageRuleBreached){
			percentageRuleBreached = true;
			resultProcessor.updateViolatedRules(percentageRuleName);
		}
	}

	private RulesProcessor() {

	}

}
