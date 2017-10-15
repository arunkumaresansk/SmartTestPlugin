package com.intuit.cto.utilties;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializer {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	@SuppressWarnings("unchecked")
	public static <T> T get(String jsonAsString, Class<T> clazz) {
		T t = null;
		try {
			t = (T) mapper.readValue(jsonAsString, Class.forName(clazz.getName()));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return t;
	}

	@SuppressWarnings("unchecked")
	public static <T> String get(Object obj, Class<T> clazz) {
		String jsonAsString = null;
		T t = (T) obj;
		try {
			jsonAsString = mapper.writeValueAsString(t);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonAsString;
	}
}
