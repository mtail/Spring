/**
 * 
 */
package com.orange.afis.vo.shine;

import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * @author Mohamed TAIL
 *
 * 21 déc. 2016
 */
public class SearchResultWrapper {

	private HttpStatus httpStatus;
	
	private List<SearchResult> searchResults;

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public List<SearchResult> getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(List<SearchResult> searchResults) {
		this.searchResults = searchResults;
	}
	
	
}
