package com.orange.afis.common;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * @author Mohamed TAIL
 *
 * 12 déc. 2016
 * 
 * This class allows to configure the authentication and template
 */

@Service
public class RestTemplateGenerator {
	/**
	 * Logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger(RestTemplateGenerator.class);

	@Value("${rest.infonova.api.header.authorization.username}")
	private String autorizationUsername;

	@Value("${rest.infonova.api.header.authorization.password}")
	private String authorizationPassword;

	@Value("${rest.proxy.username}")
	private String proxyUsername;

	@Value("${rest.proxy.password}")
	private String ProxyPassword;

	@Value("${rest.proxy.url}")
	private String proxyUrl;

	@Value("${rest.proxy.port}")
	private int proxyPort;
	
	@Value("${rest.proxy.use}")
	private boolean useProxy = false;
	
	/**
	 * 
	 * @return RestTemplate
	 */
	public RestTemplate createRestTemplate() {
		
		logger.debug("Creating the rest Template client ...");

		final String username = proxyUsername;
		final String password = ProxyPassword;
		final String Url = proxyUrl;
		final int port = proxyPort;

		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(Url, port), new UsernamePasswordCredentials(username, password));

		HttpHost myProxy = new HttpHost(Url, port);
		HttpClientBuilder clientBuilder = HttpClientBuilder.create();

		logger.debug("Use proxy : " + useProxy);
		if (useProxy) {
			clientBuilder.setProxy(myProxy).setDefaultCredentialsProvider(credsProvider).disableCookieManagement();
		}

		HttpClient httpClient = clientBuilder.build();
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setHttpClient(httpClient);

		return new RestTemplate(factory);
	}

	/**
	 * 
	 * @return HttpEntity<String>
	 */
	public HttpEntity<String> configureHeaderAuthorizationForGet() {
		
		logger.debug("Configuring header authorization for get methods ...");
		
		String plainCreds = autorizationUsername + ":" + authorizationPassword;
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);

		HttpHeaders headers = new HttpHeaders();

		headers.add("Authorization", "Basic " + base64Creds);
		
		HttpEntity<String> request = new HttpEntity<>(headers);
		
		return request;
	}

	/**
	 * 
	 * @return HttpHeaders
	 */
	public HttpHeaders configureHeaderAuthorizationForPost() {
		
		logger.debug("Configuring header authorization for post methods ...");
		
		String plainCreds = autorizationUsername + ":" + authorizationPassword;
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);

		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Authorization", "Basic " + base64Creds);
		
		return headers;
	}
	
}
