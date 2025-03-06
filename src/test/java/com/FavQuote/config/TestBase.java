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

    private static final String BASE_URL = "https://favqs.com/api";
   // private static final String APP_TOKEN = "7f0ac24fada23c6689a5f54a0bb3a614";
   // private static String userToken;
  //  private static String userName = "30400";
   // private static String userPassword = "0328c9e51f0c";
    
    /**
     * Setup API BAse URL and Authenticate user before running tests.
     */
    @BeforeAll
    public static void Setup() {
    	RestAssured.baseURI =  BASE_URL;
        // Authenticate and retrieve user token
        String userToken = ApiClient.authenticate(
            System.getenv("FAVQS_USERNAME"), // Read from environment variables
            System.getenv("FAVQS_PASSWORD")  // Read from environment variables
        );
    	
    	 // Install default request & response specifications
        Specifications.installSpecification(
            Specifications.requestSpec(BASE_URL),
            Specifications.responseSpecOK200()
        );
        // Set user token in ApiClient
        ApiClient.setUserToken(userToken);
    }

}
