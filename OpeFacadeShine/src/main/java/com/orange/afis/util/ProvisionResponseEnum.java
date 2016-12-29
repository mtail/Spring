/**
 * 
 */
package com.orange.afis.util;

/**
 * @author Mohamed TAIL
 *
 * 8 déc. 2016
 */
public enum ProvisionResponseEnum {

	OK("ok"),
	KO("ko");
	
	private String response;

	ProvisionResponseEnum(String response) {
		this.response = response;
	}
	
	public String getResponse() {
		return response;
	}
	
}
