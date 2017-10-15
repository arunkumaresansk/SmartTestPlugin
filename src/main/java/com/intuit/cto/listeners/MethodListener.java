package com.intuit.cto.listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import com.intuit.cto.processors.PriorityProcessor;

public class MethodListener implements IInvokedMethodListener {
	
	PriorityProcessor priorityProcessor = PriorityProcessor.getInstance();

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		String className = testResult.getMethod().getTestClass().getName();
		String methodName = method.getTestMethod().getMethodName();
		if(method.isTestMethod()){
			priorityProcessor.addPriority(className, methodName);
		}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		System.out.println("-------------In AfterMethod Invocation ----------");
		System.out.println("current Result - " + (testResult.isSuccess() ? "PASSED" : "FAILED"));
	}

}
