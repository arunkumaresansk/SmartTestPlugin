package com.intuit.cto.listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import com.intuit.cto.processors.PriorityProcessor;
import com.intuit.cto.processors.ResultProcessor;
import com.intuit.cto.processors.RulesProcessor;

public class MethodListener implements IInvokedMethodListener {

	PriorityProcessor priorityProcessor = PriorityProcessor.getInstance();
	ResultProcessor resultProcessor = ResultProcessor.getInstance();
	RulesProcessor rulesProcessor = RulesProcessor.getInstance();

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		resultProcessor.updateTotal();
		priorityProcessor.addPriority(testResult.getMethod().getTestClass().getName(),
				method.getTestMethod().getMethodName());
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if (testResult.getStatus() == ITestResult.SUCCESS) {
			resultProcessor.updatePassed();
		}else{
			int priority = priorityProcessor.getPriority(testResult.getMethod().getTestClass().getName(),
					method.getTestMethod().getMethodName());
			rulesProcessor.validatePriorityRule(method.getTestMethod().getQualifiedName(), priority);
			resultProcessor.updateFailedTests(method.getTestMethod().getQualifiedName());
		}
	}

}
