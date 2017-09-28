package smart.tests;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import smart.tests.rules.lib.ControlRules;

public class FilterRules {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static ControlRules get () throws JsonParseException, JsonMappingException, IOException {
		StringBuilder jsonAsString = new StringBuilder();
		Scanner scanner = new Scanner(new File("/Users/Shared/Jenkins/Home/workspace/Test1/controlRules.json"));
		while(scanner.hasNextLine())
			jsonAsString.append(scanner.nextLine());
		scanner.close();
		return mapper.readValue(jsonAsString.toString(), ControlRules.class);
	}

}
