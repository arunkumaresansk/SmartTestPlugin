package com.intuit.cto.beans.input;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InstanceConfig {
	
	private JsonNode instanceConfig = null;

	private static final String INSTANCE_CONFIG = "instance.json";
	private static final String PROTOCOL = "protocol";
	private static final String FILESERVER = "fileServer";
	private static final String PORT = "port";
	private static final String UPLOAD_URL = "uploadUri";
	private static final String DOWNLOAD_URL = "downloadUri";
	
	public InstanceConfig(){
		ObjectMapper mapper = new ObjectMapper();
		InputStream configStream = InstanceConfig.class.getClassLoader().getResourceAsStream(INSTANCE_CONFIG);
		try {
			instanceConfig = mapper.readTree(configStream);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				configStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getProtocol(){
		String protocol = null;
		if (instanceConfig != null && instanceConfig.has(PROTOCOL)) {
			protocol = instanceConfig.get(PROTOCOL).textValue();
        }
		return protocol;
	}
	
	public String getFileServer(){
		String fileServer = null;
		if (instanceConfig != null && instanceConfig.has(FILESERVER)) {
			fileServer = instanceConfig.get(FILESERVER).textValue();
        }
		return fileServer;
	}
	
	public String getPort(){
		String port = null;
		if (instanceConfig != null && instanceConfig.has(PORT)) {
			port = instanceConfig.get(PORT).textValue();
        }
		return port;
	}
	
	public String getUploadURI(){
		String priorityUri = null;
		if (instanceConfig != null && instanceConfig.has(UPLOAD_URL)) {
			priorityUri = instanceConfig.get(UPLOAD_URL).textValue();
        }
		return priorityUri;
	}

	public String getDownloadURI(){
		String rulesUri = null;
		if (instanceConfig != null && instanceConfig.has(DOWNLOAD_URL)) {
			rulesUri = instanceConfig.get(DOWNLOAD_URL).textValue();
        }
		return rulesUri;
	}
	
}
