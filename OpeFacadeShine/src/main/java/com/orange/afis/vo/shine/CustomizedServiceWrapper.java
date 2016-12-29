/**
 * 
 */
package com.orange.afis.vo.shine;

import org.springframework.http.HttpStatus;

/**
 * @author Mohamed TAIL
 *
 * 21 déc. 2016
 */
public class CustomizedServiceWrapper {

	private HttpStatus httpStatus;
	
	private CustomizedServices customizedServices;
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public CustomizedServices getCustomizedServices() {
		return customizedServices;
	}

	public void setCustomizedServices(CustomizedServices customizedServices) {
		this.customizedServices = customizedServices;
	}
}
