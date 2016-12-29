package com.orange.afis.service;

import com.orange.afis.vo.shine.ProvisioningResponse;

/**
 * @author Mohamed TAIL
 *
 * 8 déc. 2016
 * 
 * The provisioning service
 */
public interface IProvisioningSrv {

	/**
	 * @param installedServiceId
	 * @param productId
	 * @return ProvisioningResponse
	 */
	public ProvisioningResponse provision(final String installedServiceId, final String productId);
	
	
	/**
	 * @return
	 */
	public String test(); 
}
