/**
 * 
 */
package com.orange.afis.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Mohamed TAIL
 *
 *         20 déc. 2016
 *         
 *         This class allows to load the services configuration file
 *         The file name configuration is configured in appalication.properties
 */
@Service
public class AppConfiguration {

	private static final Logger logger = Logger.getLogger(AppConfiguration.class);


	@Value("${services.config.file.path}")
	private String servicesConfigFile = "configuration.properties";
	
	private Map<String, String> properties = new HashMap<>();

	public Map<String, String> getProperties() {
		return properties;
	}

	public String getServicesConfigFile() {
		return servicesConfigFile;
	}
	
	/**
	 * 
	 */
	public void loadProperties() {

		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(servicesConfigFile);
			prop.load(input);

			properties.clear();
			Enumeration<?> e = prop.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = prop.getProperty(key);

				properties.put(key,  value);
			}

		} catch (IOException e) {
			logger.error("Error, when loading file : " + servicesConfigFile + ", error : " + e.getLocalizedMessage());
			
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error("Error, when closing input stream : " + e.getLocalizedMessage());
				}
			}
		}
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
	
		if (key != null && ! key.isEmpty()) {
			return properties.get(key);
		}
		
		return "";
	}
	
	/**
	 * 
	 */
	public void print() {
		//properties.forEach( (k, v) -> System.out.println("Key: " + k + ": Value: " + v));
	}
}