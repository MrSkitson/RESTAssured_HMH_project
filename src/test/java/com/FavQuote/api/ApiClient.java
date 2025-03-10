package com.FavQuote.api;

import com.FavQuote.config.ConfigReader;
import com.FavQuote.config.Specifications;
import com.FavQuote.models.AuthUser;
import com.FavQuote.models.QuoteResponse;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

/**
 * ApiClient handles authentication and API calls.
 */
public class ApiClient {
	   private static final String BASE_URL = ConfigReader.getProperty("base.url"); 
	    private static final String API_KEY = System.getenv("FAVQS_API_KEY"); // Secure API key
	    private static String userToken;

	    /**
	     * Authenticate user and retrieve User-Token.
	     * 
	     * @param username User's login name.
	     * @param password User's password.
	     * @return User-Token required for authentication.
	     */
	    public static String authenticate() {
	        String username = System.getenv("FAVQS_USERNAME");
	        String password = System.getenv("FAVQS_PASSWORD");

	        if (username == null || password == null || API_KEY == null) {
	            throw new RuntimeException("Missing environment variables. Please set FAVQS_API_KEY, FAVQS_USERNAME, and FAVQS_PASSWORD.");
	        }

	        AuthUser authUser = new AuthUser(username, password);
	        Response response = given()
	        	.baseUri(BASE_URL)  // Ensure API Base URL is used
	            .header("Content-Type", "application/json")
	            .header("Authorization", "Token token=" + API_KEY)
	            .body(authUser)
	        .when()
	        .post(ConfigReader.getProperty("api.session.endpoint"))
	        .then()
	            .extract().response();

	        userToken = response.jsonPath().getString("User-Token");
	        return userToken;
	    }
	    
	    /**
	     * Sets the user token for authenticated requests.
	     * 
	     * @param token The User-Token retrieved from authentication.
	     */
	    public static void setUserToken(String token) {
	        userToken = token;
	    }
	    
	    /**
	     * Sends a request to favorite a quote.
	     * 
	     * @param quoteId The ID of the quote to favorite.
	     * @return API Response.
	     */
	    public static Response favoriteQuote(int quoteId) {
	    	 String endpoint = ConfigReader.getProperty("api.quote.favorite.endpoint").replace("{id}", String.valueOf(quoteId)); 
	      return given()
	    		  .spec(Specifications.requestWithAuthSpec(BASE_URL, API_KEY, userToken))
	    		  .when()
	        	  .put(endpoint)
	        	  .then()
	           	  .log().all()
	              .extract().response();
	    
	    }

	    /**
	     * Sends a request to unfavorite a quote.
	     * 
	     * @param quoteId The ID of the quote to unfavorite.
	     * @return API Response.
	     */
		public static Response unfavoriteQuote(int quoteId) {
			String endpoint = ConfigReader.getProperty("api.quote.unfavorite.endpoint").replace("{id}",
					String.valueOf(quoteId));
			return given()
					.spec(Specifications.requestWithAuthSpec(BASE_URL, API_KEY, userToken))
					.when()
					.put(endpoint)
					.then()
					.log().all()
					.extract()
					.response();
	    }
	    
	    /**
	     * Fetch a list of quotes and return raw API response.
	     */
	    public static Response getQuotesResponse() {
	        return given()
	        		.spec(Specifications.requestWithAuthSpec(BASE_URL, API_KEY, userToken))
	        		.when()
	        		.get(ConfigReader.getProperty("api.quotes.endpoint"))
	        		.then()
	                .extract().response();
	    }

	    /**
	     * Fetch a list of quotes.
	     */
	    public static QuoteResponse getQuotes() {
	        Response response = given()
	        		.spec(Specifications.requestWithAuthSpec(BASE_URL, API_KEY, userToken)) 
	        		.when()
	        		.get(ConfigReader.getProperty("api.quotes.endpoint"))
	        		.then()
	                .spec(Specifications.responseSpecOK200()) 
	                .extract().response();
	        return response.as(QuoteResponse.class); // Convert JSON -> POJO
	    }

	    /**
	     * Fetch quotes with an invalid request (example: invalid parameter).
	     */
	    public static Response getQuotesWithParams() {
	    	Response response = given()
	    			.spec(Specifications.requestWithAuthSpec(BASE_URL, API_KEY, userToken)) 
	    	        .queryParam("filter", "funny") // Invalid parameter
	    	        .when()
	    	        .get(ConfigReader.getProperty("api.quotes.endpoint"))
	    	        .then()
	    	        .log().all()
	    	        .extract().response();

	    	    System.out.println("Filtered Request Response: " + response.asString());
	    	    return response;
	    }
}
