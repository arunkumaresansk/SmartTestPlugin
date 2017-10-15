package com.intuit.cto.listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import com.intuit.cto.processors.PriorityProcessor;

public class MethodListener implements IInvokedMethodListener {

	PriorityProcessor priorityProcessor = PriorityProcessor.getInstance();

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
			priorityProcessor.addPriority(testResult.getMethod().getTestClass().getName(),
					method.getTestMethod().getMethodName());
		}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		System.out.println("current Result - " + (testResult.isSuccess() ? "PASSED" : "FAILED"));
	}

}
