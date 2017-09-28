package smart.tests;

import java.io.IOException;

import smart.tests.results.lib.ResultMetrics;

public class EntryPoint {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		boolean jenkinsJobSuccessStatus = true;
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
			System.out.println("Jenkins job will fail :(");
	}

}
