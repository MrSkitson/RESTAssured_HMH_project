package com.FavQuote.config;

import org.junit.jupiter.api.BeforeAll;

import com.FavQuote.api.ApiClient;
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

    private static final String BASE_URL = ConfigReader.getProperty("base.url");

    
    /**
     * Setup API BAse URL and Authenticate user before running tests.
     */
    @BeforeAll
    public static void Setup() {
    	RestAssured.baseURI =  BASE_URL;
    	  RestAssured.requestSpecification = Specifications.requestSpec(BASE_URL);
        // Authenticate and retrieve user token
        String userToken = ApiClient.authenticate();
        if (userToken == null || userToken.isEmpty()) {
            throw new RuntimeException("User authentication failed! Token not retrieved.");
        }
        // Set user token in ApiClient
        ApiClient.setUserToken(userToken);
    }

}
