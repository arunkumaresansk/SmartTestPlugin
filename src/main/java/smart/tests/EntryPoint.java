package smart.tests;

import java.io.IOException;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import smart.tests.results.lib.ResultMetrics;

public class EntryPoint {
	
	private static final String SAVE_PRIORITIES = "save-default-priorities";
	private static final String SET_TEST_STATUS = "set-test-status";

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		int exitCode = 0;
		ArgumentParser parser = ArgumentParsers.newFor("SmartTestPlugin").build()
                .defaultHelp(true)
                .description("Smart plugin for CI/CD.");
		parser.addArgument("actionName").metavar("ACTIONNAME")
        		.type(String.class).choices(SAVE_PRIORITIES, SET_TEST_STATUS)
        		.help("Action to invoke");
		parser.addArgument("-f", "--file").metavar("TESTJAR")
        		.dest("testjar").type(String.class)
        		.help("Path to the test jar containing all the TestNG test cases");
		Namespace res = null;
        try {
            res = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.handleError(e);
            exitCode = 1;
        } 
        if(exitCode == 0){
        	switch (res.getString("actionName")) {
	            case SAVE_PRIORITIES:
	                TestMethods.setPriorities(res.getString("testjar"));
	                break;
	            case SET_TEST_STATUS:
	            	break;
	            default:
	            	exitCode = 1;
        	}
        System.exit(exitCode);
        }
	}
        
        
		/*boolean jenkinsJobSuccessStatus = true;
		if (args.length == 0) {
			System.out.println("Usage: ListTestMethods <Test Jar File>");
			return;
		}
		System.out.println(TestMethods.getPriorities(args));
		ResultMetrics metrics = TestResults.parse();
		System.out.println(metrics.getTotal());
		System.out.println(metrics.getPassed());
		System.out.println(metrics.getFailed());
		System.out.println(metrics.getSkipped());
		System.out.println("PASSED Tests: " + metrics.getPassedTests());
		System.out.println("FAILED Tests: " + metrics.getFailedTests());
		System.out.println("Pass %" + FilterRules.get().getPassPercentage());
		System.out.println("No Failures for priorities > P" + FilterRules.get().getPriorityThreshold());
		
		if(Math.round(metrics.getPassed() * 100 / metrics.getTotal()) < FilterRules.get().getPassPercentage())
			jenkinsJobSuccessStatus = false;
		else{
			for(int key : TestMethods.getPriorities(args).keySet()){
				if(key < FilterRules.get().getPriorityThreshold()){
					System.out.println("Validating priority: " + key);
					for(String testMethod : TestMethods.getPriorities(args).get(key)){
						if(metrics.getFailedTests().contains(testMethod)){
							jenkinsJobSuccessStatus = false;
							break;
						}
					}
				}
			}
		}
		if(jenkinsJobSuccessStatus)
			System.out.println("Jenkins job will pass !!");
		else
			System.out.println("Jenkins job will fail :(");*/

}
