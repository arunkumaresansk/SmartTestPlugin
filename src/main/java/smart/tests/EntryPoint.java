package smart.tests;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import smart.tests.results.lib.ResultMetrics;
import smart.tests.rules.lib.ControlRules;

public class EntryPoint {

	private static final String SAVE_PRIORITIES = "save-default-priorities";
	private static final String SET_TEST_STATUS = "set-test-status";
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		int exitCode = 0;
		ArgumentParser parser = ArgumentParsers.newFor("SmartTestPlugin").build().defaultHelp(true)
				.description("Smart plugin for CI/CD.");
		parser.addArgument("actionName").metavar("ACTIONNAME").type(String.class)
				.choices(SAVE_PRIORITIES, SET_TEST_STATUS).help("Action to invoke");
		parser.addArgument("-j", "--jar").metavar("TESTJAR").dest("testjar").type(String.class)
				.help("Path to the test jar containing all the TestNG test cases");
		parser.addArgument("-p", "--priorities").metavar("PRIORITYJSON").dest("priorityJson").type(String.class)
				.help("Path to the json containing all test priorities");
		parser.addArgument("-t", "--result").metavar("TESTRESULT").dest("testResult").type(String.class)
				.help("Path to testng-result.xml");
		parser.addArgument("-r", "--rules").metavar("CONTROLRULES").dest("rules").type(String.class)
				.help("Path to controlRules.json");
		Namespace namespace = null;
		try {
			namespace = parser.parseArgs(args);
		} catch (ArgumentParserException e) {
			parser.handleError(e);
			exitCode = 1;
		}
		if (exitCode == 0) {
			switch (namespace.getString("actionName")) {
			case SAVE_PRIORITIES:
				TestMethods.setPriorities(namespace.getString("testjar"));
				break;
			case SET_TEST_STATUS:
				boolean jenkinsJobSuccessStatus = true;
				Map<Integer, List<String>> testPriorities = TestMethods.getPriorities(namespace.getString("priorityJson"));
				ResultMetrics result = TestResults.parse(namespace.getString("testResult"));
				ControlRules controlRules = FilterRules.get(namespace.getString("rules"));
				if (Math.round(result.getPassed() * 100 / result.getTotal()) < controlRules.getPassPercentage())
					jenkinsJobSuccessStatus = false;
				else {
					for (int key : testPriorities.keySet()) {
						if (key < controlRules.getPriorityThreshold()) {
							System.out.println("Validating priority: " + key);
							for (String testMethod : testPriorities.get(key)) {
								if (result.getFailedTests().contains(testMethod)) {
									jenkinsJobSuccessStatus = false;
									break;
								}
							}
						}
					}
				}
				result.setJobSuccess(jenkinsJobSuccessStatus);
				ObjectWriter jsonWriter = mapper.writer(new DefaultPrettyPrinter());
				jsonWriter.writeValue(new File("results.json"), result);
				break;
			default:
				exitCode = 1;
			}
			System.exit(exitCode);
		}
	}

}
