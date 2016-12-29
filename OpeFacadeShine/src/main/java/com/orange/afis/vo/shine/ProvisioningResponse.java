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
public class ProvisioningResponse {

	private boolean isError = false;
	
	private HttpStatus httpStatus;
	
	private String response = "";

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}
}
