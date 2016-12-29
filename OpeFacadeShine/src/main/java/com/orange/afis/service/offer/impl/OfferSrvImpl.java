/**
 * 
 */
package com.orange.afis.service.offer.impl;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.orange.afis.common.RestTemplateGenerator;
import com.orange.afis.service.offer.IOfferSrv;
import com.orange.afis.util.ProvisioningHelper;
import com.orange.afis.vo.shine.CustomizedServiceWrapper;
import com.orange.afis.vo.shine.CustomizedServices;
import com.orange.afis.vo.shine.SearchResultWrapper;
import com.orange.afis.vo.shine.SearchResults;

/**
 * @author Mohamed TAIL
 *
 * 12 déc. 2016
 */

@Service
public class OfferSrvImpl implements IOfferSrv {

	private static final Logger logger = LoggerFactory.getLogger(OfferSrvImpl.class);

	@Value("${rest.infonova.api.url.endPoint}")
	private String endpointUrl;

	@Value("${rest.infonova.api.url.search.offer.resource}")
	private String searchResourceUrl;

	@Value("${rest.infonova.api.url.search.offer.clause}")
	private String searchClause;
	
	@Value("${rest.infonova.api.url.customer.offer.service.resource}")
	private String customerServicesUrl;
	
	@Autowired
	RestTemplateGenerator restTemplateGenerator;

	
	/* (non-Javadoc)
	 * @see com.orange.afis.service.offer.IOfferSrv#getOffersByService(java.lang.String)
	 */
	@Override
	public SearchResultWrapper getOffersByService(String installedServiceId) {
		
		logger.debug("Get offers by service id : " +  installedServiceId);
		
		RestTemplate restTemplate = restTemplateGenerator.createRestTemplate();
		//RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> httpEntity = restTemplateGenerator.configureHeaderAuthorizationForGet();
		
		// Get the offers installed 
		String url = ProvisioningHelper.generateUrlWithClause(endpointUrl, searchResourceUrl, searchClause, installedServiceId);

		ResponseEntity<SearchResults> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, SearchResults.class);
		
		SearchResultWrapper searchResultWrapper = new SearchResultWrapper();
		searchResultWrapper.setHttpStatus(response.getStatusCode());
		searchResultWrapper.setSearchResults(response.getBody().getSearchResult());
		
		return searchResultWrapper;
	}


	/* (non-Javadoc)
	 * @see com.orange.afis.service.offer.IOfferSrv#getServicesByOffer(java.lang.String, java.lang.String)
	 */
	@Override
	public CustomizedServiceWrapper getServicesByOffer(String customerId, String offerId) {

		if (customerId == null || customerId.isEmpty() ||  offerId == null || offerId.isEmpty()) {
			throw new IllegalArgumentException("customer id or offer id is empty");
		}
		
		logger.debug("Get service by customer id : " +  customerId + ", by offer id : " + offerId);
		
		RestTemplate restTemplate = restTemplateGenerator.createRestTemplate();
		//RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> httpEntity = restTemplateGenerator.configureHeaderAuthorizationForGet();
		
		String url = ProvisioningHelper.generateUrl(endpointUrl, customerServicesUrl, new String[] {customerId, offerId});
		
		ResponseEntity<CustomizedServices> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, CustomizedServices.class);

		CustomizedServiceWrapper customizedServiceWrapper = new CustomizedServiceWrapper();
		customizedServiceWrapper.setHttpStatus(response.getStatusCode());
		customizedServiceWrapper.setCustomizedServices(response.getBody());
		
		return customizedServiceWrapper;
	}


	/* (non-Javadoc)
	 * @see com.orange.afis.service.offer.IOfferSrv#test()
	 */
	@Override
	public String test() {

		RestTemplate restTemplate = restTemplateGenerator.createRestTemplate();
		String url = "http://www.thomas-bayer.com/sqlrest/CUSTOMER/";
		
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, new HashMap<>());
		
		return response.getBody();
	}

}
