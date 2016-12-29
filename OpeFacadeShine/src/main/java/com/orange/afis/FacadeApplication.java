package com.orange.afis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author Mohamed TAIL
 * 
 *         8 déc. 2016
 * 
 * 
 */

/*
 * This case to generate package JAR
 */

// @SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
// public class FacadeApplication {
//
// public static void main(String[] args) {
//
// System.setProperty("jsse.enableSNIExtension", "false");
// System.setProperty("https.protocols", "SSLv1.2");
//
// SpringApplication.run(FacadeApplication.class, args);
// }
// }

/*
 * This case to generate package WAR
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FacadeApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

		System.setProperty("jsse.enableSNIExtension", "false");
		System.setProperty("https.protocols", "SSLv1.2");

		return builder.sources(FacadeApplication.class);
	}

	public static void main(String[] args) {

		System.setProperty("jsse.enableSNIExtension", "false");
		System.setProperty("https.protocols", "SSLv1.2");

		SpringApplication.run(FacadeApplication.class, args);
	}
}
