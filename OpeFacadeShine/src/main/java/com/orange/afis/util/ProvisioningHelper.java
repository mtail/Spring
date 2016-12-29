/**
 * 
 */
package com.orange.afis.util;

import java.util.List;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import com.orange.afis.vo.shine.CustomizedService;
import com.orange.afis.vo.shine.CustomizedServices;
import com.orange.afis.vo.shine.SearchResult;

/**
 * @author Mohamed TAIL
 *
 *         12 déc. 2016
 *         
 *         This is class is helper class
 */
public class ProvisioningHelper {

	private static final Logger logger = Logger.getLogger(ProvisioningHelper.class);

	/**
	 * 
	 * @param serviceId
	 * @param offers
	 * @return true/flase
	 */
	static public boolean isSouscribedService(final String serviceId, final List<SearchResult> offers) {

		boolean isSouscribed = false;

		if (serviceId == null || serviceId.isEmpty()) {
			logger.error("Service identifier is invalid !");
			throw new IllegalArgumentException("Service identifier is invalid !");
		}

		if (offers == null) {
			return isSouscribed;
		}

		logger.debug("Service id : " + serviceId);

		for (SearchResult offer : offers) {
				logger.debug("key : " + offer.getKey() + ", value : " + offer.getValue());
	
				InfonovaSerachEnum targetEnum = InfonovaSerachEnum.fromString(offer.getKey());
				if (targetEnum != null) {
					isSouscribed = InfonovaSerachEnum.isGoodResponse(targetEnum, offer.getValue());
					
					if (isSouscribed) {
						break;
					}
				}
		}

		return isSouscribed;
	}

	/**
	 * @param serviceKey
	 * @param customizedServices
	 * @return true/flase
	 */
	static public boolean isGoodService(final String serviceKey, final CustomizedServices customizedServices) {

		boolean isGoodService = false;

		if (serviceKey == null || serviceKey.isEmpty()) {
			logger.error("Service key name is invalid !");
			throw new IllegalArgumentException("Service key name is invalid !");
		}

		if (customizedServices == null) {
			logger.error("Services are invalid !");
			throw new IllegalArgumentException("Services are invalid !");
		}
		

		logger.debug("Service key name : " + serviceKey);

		CustomizedService service = customizedServices.getCustomizedService();

		if (service != null) {
			if (service.getServiceKey() != null && service.getServiceKey().equalsIgnoreCase(serviceKey)) {
				isGoodService = true;
			}
		}

		return isGoodService;
	}
	
	/**
	 * 
	 * @param service
	 * @param services
	 * @return true/false
	 */
	static public boolean isPresentService(final String service, final List<String> services) {

		if (service == null || service.isEmpty()) {
			logger.error("Service key name is invalid !");
			throw new IllegalArgumentException("Service key name is invalid !");
		}

		if (services == null) {
			logger.error("Services are invalid !");
			throw new IllegalArgumentException("Services are invalid !");
		}
		

		logger.debug("Service key name : " + service);
		
		/** 
		 * JAVA 8
		 * Does not work in kermit server 
		 * */
		/*
		Stream<String> streamSupplier = services.stream().filter(item -> item.equalsIgnoreCase(service));
		return streamSupplier.findAny().isPresent();
		*/
		
		for (String currentService : services) {
			if (service.equalsIgnoreCase(currentService)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param opeService
	 * @param services
	 * @return true/false
	 */
	static public boolean getShineService(final String opeService, final List<String> services) {

		if (opeService == null || opeService.isEmpty()) {
			logger.error("Service key name is invalid !");
			throw new IllegalArgumentException("Service key name is invalid !");
		}

		if (services == null) {
			logger.error("Services are invalid !");
			throw new IllegalArgumentException("Services are invalid !");
		}
		

		logger.debug("Service key name : " + opeService);
		
		/**
		 * JAVA 8
		 * Does not work in Kermit
		 */
		/*
		Stream<String> streamSupplier = services.stream().filter(item -> item.equalsIgnoreCase(opeService));
		return streamSupplier.findAny().isPresent();
		*/
		
		for (String currentService : services) {
			if (opeService.equalsIgnoreCase(currentService)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * @param key
	 * @param offers
	 * @return target value
	 */
	static public String getTargetKeyValue(final InfonovaSerachEnum key, final List<SearchResult> offers) {

		if (key == null || offers == null) {
			logger.error("Invalid input parameters !");
			throw new IllegalArgumentException("Invalid input parameters !");
		}
		
		logger.debug("key : " + key);

		/**
		 * JAVA 8
		 * Does not work in Kermit
		 */
		/*
		String name = offers.stream()
				.filter(x -> key.getResponse().equals(x.getKey()))
				.map(SearchResult::getValue)						//convert stream to String
				.findAny()
				.orElse("");
		
		return name;
		*/
		
		for (SearchResult searchResult : offers) {
			if (key.getResponse().equalsIgnoreCase(searchResult.getKey())) {
				return searchResult.getValue();
			}
		}
		
		return "";
	}


	/**
	 * @param url
	 * @param resource
	 * @param clause
	 * @param value
	 * @return formated url
	 */
	static public String generateUrlWithClause(final String url, final String resource, final String clause, final String value) {
		
		String clauseWhere = String.format(clause, value);
		 
		return new StringBuilder().append(url).append(resource).append(clauseWhere).toString();
	}
	
	/**
	 * @param url
	 * @param resource
	 * @param values
	 * @return formated url
	 */
	static public String generateUrl(final String url, final String resource, final String[] values) {
		
		String formatedResource = String.format(resource, values);
		 
		return new StringBuilder().append(url).append(formatedResource).toString();
	}
}
