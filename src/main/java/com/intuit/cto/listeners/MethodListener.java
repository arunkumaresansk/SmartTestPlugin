package com.intuit.cto.listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import com.intuit.cto.processors.PriorityProcessor;
import com.intuit.cto.processors.ResultProcessor;
import com.intuit.cto.processors.RulesProcessor;

public class MethodListener implements IInvokedMethodListener {

	PriorityProcessor priorityProcessor = PriorityProcessor.getInstance();
	ResultProcessor resultProcessor = ResultProcessor.getInstance();
	RulesProcessor rulesProcessor = RulesProcessor.getInstance();

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		if(method.isTestMethod()){
			priorityProcessor.addPriority(testResult.getMethod().getTestClass().getName(), testResult.getMethod().getMethodName());
			if (rulesProcessor.isExecutionAborted())
				throw new SkipException("SKIPPING TEST: See the logs for more information.");
		}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if(testResult.getStatus() == ITestResult.FAILURE){
			int priority = priorityProcessor.getPriority(testResult.getMethod().getTestClass().getName(),
					testResult.getMethod().getMethodName());
			rulesProcessor.validatePriorityRule(testResult.getMethod().getQualifiedName(), priority);
			resultProcessor.updateFailedTests(testResult.getMethod().getQualifiedName());
		}
	}

}
