/**
 * 
 */
package com.orange.afis.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.orange.afis.common.AppConfiguration;
import com.orange.afis.service.IProvisioningSrv;
import com.orange.afis.service.offer.IOfferSrv;
import com.orange.afis.util.InfonovaSerachEnum;
import com.orange.afis.util.ProvisioningHelper;
import com.orange.afis.vo.shine.CustomizedServiceWrapper;
import com.orange.afis.vo.shine.CustomizedServices;
import com.orange.afis.vo.shine.ProvisioningResponse;
import com.orange.afis.vo.shine.SearchResult;
import com.orange.afis.vo.shine.SearchResultWrapper;
import com.orange.afis.vo.shine.SearchResults;
/**
 * @author Mohamed TAIL
 *
 * 8 déc. 2016
 */

@Service
public class ProvisioningSrv implements IProvisioningSrv {

	private static final Logger logger = Logger.getLogger(ProvisioningSrv.class);
	
	private static String ALLOWED_SERVICES = "allowed.services";
	
	/**
	 * Configuration parameters
	 */
	private List<String> allowedServices;
	
	@Value("${no.service.found.msg}")
	private String noServiceFoundMsg;
	
	@Value("${no.match.service.found.msg}")
	private String noMatchServiceFoundMsg;
	
	@Value("${retreive.service.error}")
	private String retreiveServiceError;
	
	@Autowired
	private IOfferSrv offerSrv;

	@Autowired
	private AppConfiguration configuration;
	
	
	/* (non-Javadoc)
	 * @see com.orange.afis.service.IProvisioningSrv#provision(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ProvisioningResponse provision(String installedServiceId, String productId) {

		logger.debug("Installed service id : " + installedServiceId);
		
		if (installedServiceId ==  null || installedServiceId.isEmpty()) {
			throw new IllegalArgumentException("Service indetifier is required !");
		}
		
		if (productId ==  null || productId.isEmpty()) {
			throw new IllegalArgumentException("Product indetifier is required !");
		}
		
		// Load services from Application configuration
		configuration.loadProperties();
		configuration.print();
		String services = configuration.getProperty(ALLOWED_SERVICES);
		
		if (services == null || services.isEmpty()) {
			throw new IllegalArgumentException("Allowed services are not configured in file : " + configuration.getServicesConfigFile());
		}
		
		allowedServices = Arrays.asList(services.split(","));
		
		// Check if the product id (OPE) is present in configuration 
		boolean isPresentService = ProvisioningHelper.isPresentService(productId, allowedServices);
		if (! isPresentService) {
			throw new IllegalArgumentException(productId + " : is not presente in the configuration : " + configuration.getServicesConfigFile());
		}
		
		// Get the installed service
		SearchResultWrapper searchResultWrapper = offerSrv.getOffersByService(installedServiceId);
		
		List<SearchResult> offers = searchResultWrapper.getSearchResults();
		HttpStatus httpStatus = searchResultWrapper.getHttpStatus();
		
		ProvisioningResponse provisioningResponse = new ProvisioningResponse();
		provisioningResponse.setHttpStatus(httpStatus);
		
		if (offers == null) {
			provisioningResponse.setError(true);
			provisioningResponse.setResponse(String.format(noMatchServiceFoundMsg, installedServiceId));
		}
		else if (HttpStatus.ACCEPTED == httpStatus || HttpStatus.OK == httpStatus) {
			boolean isSouscribed = ProvisioningHelper.isSouscribedService(installedServiceId, offers);
			
			if (isSouscribed) {
				String targetService = configuration.getProperty(productId);
				
				if (targetService ==  null || targetService.isEmpty()) {
					throw new IllegalArgumentException(productId + " has not correspondence shine service in configuration : " + 
				configuration.getServicesConfigFile());
				}
				
				String accountValue = ProvisioningHelper.getTargetKeyValue(InfonovaSerachEnum.ACCOUNT_ID, offers);
				String offerValue = ProvisioningHelper.getTargetKeyValue(InfonovaSerachEnum.CUSTOMIZED_OFFER_ID, offers);
					
				// Get service by customer by offer
				CustomizedServiceWrapper customizedServicesWrapper = offerSrv.getServicesByOffer(accountValue, offerValue);
				
				CustomizedServices customizedServices = customizedServicesWrapper.getCustomizedServices();
				httpStatus = customizedServicesWrapper.getHttpStatus(); 
				
				provisioningResponse.setHttpStatus(httpStatus);
				provisioningResponse.setResponse("");
				
				if (HttpStatus.ACCEPTED == httpStatus || HttpStatus.OK == httpStatus) {
					boolean isGoodService = ProvisioningHelper.isGoodService(targetService, customizedServices);
					
					if (! isGoodService) {
						provisioningResponse.setError(true);
						provisioningResponse.setResponse(String.format(noServiceFoundMsg, productId));
					}
				}
			}
		}
		else {
			provisioningResponse.setError(true);
			provisioningResponse.setResponse(retreiveServiceError);
		}
		
		return provisioningResponse;
	}


	/* (non-Javadoc)
	 * @see com.orange.afis.service.IProvisioningSrv#test()
	 */
	@Override
	public String test() {

		logger.debug("test ...");
		
		return offerSrv.test();
	}

}
