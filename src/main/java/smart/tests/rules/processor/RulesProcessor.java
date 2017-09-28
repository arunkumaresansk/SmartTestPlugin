package smart.tests.rules.processor;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import smart.tests.results.lib.ResultMetrics;
import smart.tests.rules.lib.ControlRules;

public class RulesProcessor {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static void process(ControlRules controlRules, ResultMetrics result,
			Map<Integer, List<String>> testPriorities) throws JsonGenerationException, JsonMappingException, IOException{
		boolean jenkinsJobSuccessStatus = true;
		if (Math.round(result.getPassed() * 100 / result.getTotal()) > controlRules.getPassPercentage())
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
		result.setJobStatus(jenkinsJobSuccessStatus);
		ObjectWriter jsonWriter = mapper.writer(new DefaultPrettyPrinter());
		jsonWriter.writeValue(new File("results.json"), result);
	}

}
