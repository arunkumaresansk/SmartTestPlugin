package smart.tests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import smart.tests.lib.ClassMethods;
import smart.tests.lib.MethodPriority;
import smart.tests.lib.PriorityList;

public class TestMethods {

	private static ObjectMapper mapper = new ObjectMapper();
	private static Map<Integer, List<String>> methodPriorities = new HashMap<Integer, List<String>>();
	private static final String CLASS = ".class";
	
	public static Map<Integer, List<String>> getPriorities(String testJarFile) throws JsonParseException, JsonMappingException, IOException {
		StringBuilder jsonAsString = new StringBuilder();
		Scanner scanner = new Scanner(new File(testJarFile));
		while(scanner.hasNextLine())
			jsonAsString.append(scanner.nextLine());
		scanner.close();
		PriorityList priorityList = mapper.readValue(jsonAsString.toString(), PriorityList.class);
		for(ClassMethods cMethods : priorityList.getPriorities()){
			String className = cMethods.getName();
			for(MethodPriority mPriority : cMethods.getMethods()){
				List<String> methods;
				if(methodPriorities.get(mPriority.getPriority()) == null){
					methods = new ArrayList<String>();
				} else {
					methods = methodPriorities.get(mPriority.getPriority());
				}
				methods.add(className + ":" + mPriority.getName());
				methodPriorities.put(mPriority.getPriority(), methods);
			}
		}
		return methodPriorities;
	}
	
	public static void setPriorities(String pathToJar) throws ClassNotFoundException, IOException{
		ClassMethods classMethods = null;
		JarFile jarFile = new JarFile(pathToJar);
		Enumeration<JarEntry> e = jarFile.entries();
		URL[] urls = { new URL("jar:file:" + pathToJar +"!/") };
		URLClassLoader classLoader = URLClassLoader.newInstance(urls);
		List<ClassMethods> allClassMethods = new ArrayList<ClassMethods>();

		
		while (e.hasMoreElements()) {
		    JarEntry jarEntry = e.nextElement();
		    if(!jarEntry.isDirectory() && jarEntry.getName().endsWith(CLASS)){
		    	classMethods = new ClassMethods();
		        String className = jarEntry.getName().substring(0, jarEntry.getName().length()-CLASS.length());
			    className = className.replace('/', '.');
			    classMethods.setName(className);
			    Class<?> clazz = classLoader.loadClass(className);
			    List<MethodPriority> methods = new ArrayList<MethodPriority>();
			    for(Method method : clazz.getDeclaredMethods()){
			    	if(method.getAnnotation(org.testng.annotations.Test.class) != null){
			    		MethodPriority mPriority = new MethodPriority();
			    		mPriority.setName(method.getName());
			    		mPriority.setPriority(0);
			    		methods.add(mPriority);
			    	}
			    }
			    classMethods.setMethods(methods);
			    allClassMethods.add(classMethods);
		    }
		}
		jarFile.close();
		ObjectWriter jsonWriter = mapper.writer(new DefaultPrettyPrinter());
		jsonWriter.writeValue(new File("testMethods.json"), new PriorityList(allClassMethods));
	}
}
