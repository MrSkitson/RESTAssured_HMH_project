package com.FavQuote.config;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

/**
 * Manages API request and response specifications for RestAssured.
 */
public class Specifications {

	/**
	 * Creates a Request Specification with Base URL and JSON content type.
	 * 
	 * @param url BAse URL for API requests.
	 * @return Configured RequestSpecification.
	 */
	public static RequestSpecification requestSpec(String url) {
		return new RequestSpecBuilder()
				.setBaseUri(url)
				.setContentType(ContentType.JSON)
				.build();
	}
	
	/**
	 * Response Specification for expected 200 OK status.
	 * @return ResponseSpecification expecting status 200.
	 */
	public static ResponseSpecification responseSpecOK200() {
		return new ResponseSpecBuilder()
				.expectStatusCode(200)
				.build();				
	}
	
	/**
	 * Response Specification for any specific status code.
	 * @param status Expected status code.
	 * @return ResponseSpecifiation expecting given status.
	 */
	public static ResponseSpecification responseSpecUnique(int status) {
		return new ResponseSpecBuilder()
				.expectStatusCode(status)
				.build(); 
	}
	/**
	 * Sets global request and response specifications for API tests.
	 * Ensures all requests use a standardised format and response expectations.
	 * 
	 * @param request  The request specification to be applied globally.
	 * @param response The response specification to be applied globally.
	 */
	public static void installSpecification(RequestSpecification request, ResponseSpecification response) {
		RestAssured.requestSpecification = request;
		RestAssured.responseSpecification = response;
	}
}
