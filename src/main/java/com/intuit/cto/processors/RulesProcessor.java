package com.intuit.cto.processors;

import com.intuit.cto.beans.rules.ControlRules;

public class RulesProcessor {

	private static final String priorityRuleName = "priorityThreshold";
	private static final String percentageRuleName = "passPercentage";
	private static RulesProcessor rulesProcessor = new RulesProcessor();
	private static ResultProcessor resultProcessor = ResultProcessor.getInstance();
	private static PriorityProcessor priorityProcessor = PriorityProcessor.getInstance();
	private boolean priorityRuleBreached = false, percentageRuleBreached = false;
	private int priorityThreshold;
	private int passThreshold;
	private boolean abortOnFailure;

	public static RulesProcessor getInstance() {
		return rulesProcessor;
	}

	public void setRules(ControlRules rules) {
		priorityThreshold = rules.getPriorityThreshold();
		passThreshold = rules.getPassPercentage();
		abortOnFailure = rules.isAbortOnFailure();
	}
	
	public boolean isRuleBreached(){
		return priorityRuleBreached || percentageRuleBreached;
	}

	public void validatePriorityRule(String method, int priority) {
		if (priority <= priorityThreshold){
			resultProcessor.updateBreachedTests(method);
			setPriorityRuleBreached();
			if(abortOnFailure && priorityProcessor.isBasePrioritiesDefined()){
				resultProcessor.finalize();
			}
		}
	}

	public void validatePercentageRule(int passPercentage) {
		if (passPercentage < passThreshold)
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
