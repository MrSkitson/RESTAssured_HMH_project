package com.FavQuote.config;

import org.junit.jupiter.api.BeforeAll;

import com.FavQuote.models.AuthUser;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
/**
 * TestBase sets up the API environment and handles authentication.
 * It runs once before all test cases.
 * 
 * @author Alexander Pigulewski
 * @version 1.0
 */

public class TestBase {

    private static final String BASE_URL = "https://favqs.com/api";
    private static final String APP_TOKEN = "7f0ac24fada23c6689a5f54a0bb3a614";
    private static String userToken;
    private static String userName = "30400";
    private static String userPassword = "0328c9e51f0c";
    
    /**
     * Setup API BAse URL and Authenticate user before running tests.
     */
    @BeforeAll
    public static void Setup() {
    	RestAssured.baseURI =  BASE_URL;
    	authenticate(userName, userPassword);
    }
    
    private static void authenticate(String name, String password) {
    	//Using Pojo class
    	AuthUser authUser = new AuthUser(name, password);
    	
    	Response response = given()
    			.header("Content-Type", "application/json")
    			.header("Authentication", "Token token=" + APP_TOKEN)
    			.body(authUser)
    			.when()
    			.post("/session")
    			.then()
    			.extract().response();
    	
    	userToken = response.jsonPath().getString("User-Token");
    	
    	if(userToken == null || userToken.isEmpty()) 
    	{
    		throw new RuntimeException("Authentication failed: User-Token not received. Check credentials.");
    	}
    	System.out.println("Authenticated! USer-Token: " + userToken);
    			
    }
}
