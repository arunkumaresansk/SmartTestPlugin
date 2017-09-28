package smart.tests;

import java.io.IOException;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import smart.tests.rules.processor.RulesProcessor;

public class EntryPoint {

	private static final String SAVE_PRIORITIES = "save-default-priorities";
	private static final String SET_JOB_STATUS = "set-job-status";
	
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		int exitCode = 0;
		ArgumentParser parser = ArgumentParsers.newFor("SmartTestPlugin").build().defaultHelp(true)
				.description("Smart plugin for CI/CD.");
		parser.addArgument("actionName").metavar("ACTIONNAME").type(String.class)
				.choices(SAVE_PRIORITIES, SET_JOB_STATUS).help("Action to invoke");
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
			case SET_JOB_STATUS:
				RulesProcessor.process(FilterRules.get(namespace.getString("rules")), 
						TestResults.parse(namespace.getString("testResult")), 
						TestMethods.getPriorities(namespace.getString("priorityJson")));
				break;
			default:
				exitCode = 1;
			}
			System.exit(exitCode);
		}
	}

}
