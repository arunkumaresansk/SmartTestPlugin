package com.intuit.cto.utilties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XMLSerializer {
	
	private static ObjectMapper mapper = new XmlMapper();
	
	@SuppressWarnings("unchecked")
	public static <T> T toObject(String xmlFile, Class<T> clazz) {
		T t = null;
		try {
			t = (T) mapper.readValue(StringUtils.toEncodedString(Files.readAllBytes(Paths.get(xmlFile)), 
			        StandardCharsets.UTF_8), Class.forName(clazz.getName()));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return t;
	}

}
