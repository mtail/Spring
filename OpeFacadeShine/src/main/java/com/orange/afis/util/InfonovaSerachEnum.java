/**
 * 
 */
package com.orange.afis.util;

import java.util.EnumMap;

/**
 * @author Mohamed TAIL
 *
 * 8 déc. 2016
 */
public enum InfonovaSerachEnum {

	ACCOUNT_ID("accountId"),
	CUSTOMIZED_OFFER_ID("customizedOfferId"),
	OFFER_STATUS("offerStatus"),
	COMPLETENESS_STATUS("completenessStatus");
	
	private String status;
	
	static private EnumMap<InfonovaSerachEnum, String> statusMap = new EnumMap<InfonovaSerachEnum, String>(InfonovaSerachEnum.class);

	public static EnumMap<InfonovaSerachEnum, String> getStatusMap() {
		return statusMap;
	}

	static {
		statusMap.put(InfonovaSerachEnum.OFFER_STATUS, "ACTIVE");
		statusMap.put(InfonovaSerachEnum.COMPLETENESS_STATUS, "COMPLETE");
	}
	
	InfonovaSerachEnum(String status) {
		this.status = status;
	}
	
	public String getResponse() {
		return status;
	}
	
	public static boolean isGoodResponse(InfonovaSerachEnum theEnum, String theValue) {
		
		
		if (theValue != null) {
			String value = statusMap.get(theEnum);
			
			if (theValue.equalsIgnoreCase(value)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static InfonovaSerachEnum fromString(String text) {

		if (text != null) {
			for (InfonovaSerachEnum b : InfonovaSerachEnum.values()) {
				if (text.equalsIgnoreCase(b.getResponse())) {
					return b;
				}
			}
		}
		return null;
	}
	
}
