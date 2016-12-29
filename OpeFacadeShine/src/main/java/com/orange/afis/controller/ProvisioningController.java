/**
 * 
 */
package com.orange.afis.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.afis.service.IProvisioningSrv;
import com.orange.afis.vo.ope.OpeRequest;
import com.orange.afis.vo.shine.ProvisioningResponse;

/**
 * @author Mohamed TAIL
 *
 *         8 déc. 2016
 *         
 *         The provisioning controller
 */

@RestController
@RequestMapping("/facade")
public class ProvisioningController {

	private static final Logger logger = LoggerFactory.getLogger(ProvisioningController.class);

	@Autowired
	private IProvisioningSrv provisioningSrv;

	// =================================================================================================
	//
	// Public functions
	//
	// =================================================================================================

	@RequestMapping(value = "/provision", method = RequestMethod.POST)
	public void doProvision(@RequestBody OpeRequest opeRequest, HttpServletResponse httpServletResponse) {

		String installedService = opeRequest.getParameterValues().getMerchantCode();
		String productId = opeRequest.getProductId();

		try {
			ProvisioningResponse provisionResponse = provisioningSrv.provision(installedService, productId);
			HttpStatus httpStatus = provisionResponse.getHttpStatus();

			/*
			 * Traits the service response
			 */
			if (provisionResponse.isError()) {
				updateHttpResponse(provisionResponse.getResponse(), httpServletResponse);
				
			} else if (HttpStatus.ACCEPTED == httpStatus || HttpStatus.OK == httpStatus) {
				httpServletResponse.setStatus(HttpStatus.CREATED.value());

				httpServletResponse.setCharacterEncoding("UTF-8");
				httpServletResponse.setContentType(MediaType.APPLICATION_JSON);

				updateHttpResponse(opeRequest, httpServletResponse);
			} else {
				httpServletResponse.setStatus(httpStatus.value());
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			updateHttpResponse(e.getLocalizedMessage(), httpServletResponse);
		}
	}

	// For test
//	@RequestMapping(value = "/test2", method = RequestMethod.GET)
//	public Object doProvision2(@RequestParam(value = "serviceId") String serviceId,
//			@RequestParam(value = "productId") String productId, HttpServletResponse httpServletResponse) {
//
//		return provisioningSrv.provision(serviceId, productId);
//	}

	// For test
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public Object doProvision3(HttpServletResponse httpServletResponse) {

		return provisioningSrv.test();
	}

	// For test
	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	public Object doProvision4(HttpServletResponse httpServletResponse) {

		return "OK";
	}

	// =================================================================================================
	//
	// Private functions
	//
	// =================================================================================================

	private void updateHttpResponse(OpeRequest opeRequest, HttpServletResponse httpServletResponse) {

		ObjectMapper mapper = new ObjectMapper();

		try {
			// Convert pojo to json
			String jsonContent = mapper.writeValueAsString(opeRequest);
			httpServletResponse.setContentLength(jsonContent.length());

			// Update http request response
			httpServletResponse.getOutputStream().println(jsonContent);

		} catch (JsonProcessingException e) {
			logger.error("Error was occured when converting POJO to JSON : " + e.getLocalizedMessage());
		} catch (IOException e) {
			logger.error("Error was occured when writing content in http request : " + e.getLocalizedMessage());
		}

	}

	private void updateHttpResponse(String message, HttpServletResponse httpServletResponse) {

		try {
			// Update http request response
			httpServletResponse.getOutputStream().println(message);

		} catch (IOException e) {
			logger.error("Error was occured when writing content in http request : " + e.getLocalizedMessage());
		}
	}
}
