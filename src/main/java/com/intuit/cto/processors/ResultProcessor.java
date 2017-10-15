package com.intuit.cto.processors;

public class ResultProcessor {

	private static ResultProcessor resultProcessor = new ResultProcessor();

	private ResultProcessor() {

	}

	public static ResultProcessor getInstance() {
		return resultProcessor;
	}
}
