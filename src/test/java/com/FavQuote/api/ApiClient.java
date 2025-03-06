package com.FavQuote.api;

import com.FavQuote.models.AuthUser;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

/**
 * ApiClient handles authentication and API calls.
 */
public class ApiClient {
	
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
	            .header("Content-Type", "application/json")
	            .header("Authorization", "Token token=" + API_KEY)
	            .body(authUser)
	        .when()
	            .post("/session")
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
	        return given()
	            .header("Authorization", "Token token=" + API_KEY)
	            .header("User-Token", userToken)
	            .header("Content-Type", "application/json")
	        .when()
	            .put("/quotes/" + quoteId + "/fav")
	        .then()
	            .extract().response();
	    }

	    /**
	     * Sends a request to unfavorite a quote.
	     * 
	     * @param quoteId The ID of the quote to unfavorite.
	     * @return API Response.
	     */
	    public static Response unfavoriteQuote(int quoteId) {
	        return given()
	            .header("Authorization", "Token token=" + API_KEY)
	            .header("User-Token", userToken)
	        .when()
	            .put("/quotes/" + quoteId + "/unfav")
	        .then()
	            .extract().response();
	    }
}
