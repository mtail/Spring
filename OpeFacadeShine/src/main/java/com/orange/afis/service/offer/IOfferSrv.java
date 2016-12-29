/**
 * 
 */
package com.orange.afis.service.offer;

import com.orange.afis.vo.shine.CustomizedServiceWrapper;
import com.orange.afis.vo.shine.SearchResultWrapper;

/**
 * @author Mohamed TAIL
 *
 * 12 déc. 2016
 * 
 * This class traits the offers and services
 */
public interface IOfferSrv {

	/**
	 * 
	 * @param installedServiceId
	 * @return SearchResultWrapper
	 */
	public SearchResultWrapper getOffersByService(final String installedServiceId);
	
	/**
	 * @param customerId
	 * @param offerId
	 * @return CustomizedServices
	 */
	public CustomizedServiceWrapper getServicesByOffer(final String customerId, final String offerId);
	
	/**
	 * @return
	 */
	public String test();
}
