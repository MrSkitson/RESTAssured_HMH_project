package com.FavQuote.tests;

import org.junit.jupiter.api.Test;

import com.FavQuote.api.ApiClient;
import com.FavQuote.config.ConfigReader;
import com.FavQuote.config.TestBase;
import com.FavQuote.config.TestHelpers;
import com.FavQuote.models.Quote;
import com.FavQuote.models.QuoteResponse;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * Test class for favoriting and unfavoriting quotes.
 */
public class FavQuoteTests extends TestBase {
   
	private static int validQuoteId;
    private static int invalidQuoteId;
    private static int negativeQuoteId;

    /**
     * Loads test data from config before running tests.
     */
    @BeforeAll
    public static void setupTestData() {
        validQuoteId = Integer.parseInt(ConfigReader.getProperty("quote.id"));
        invalidQuoteId = Integer.parseInt(ConfigReader.getProperty("invalid.quote.id"));
        negativeQuoteId = Integer.parseInt(ConfigReader.getProperty("negative.quote.id"));
    }
    
	 /**
     * Tests favoriting a quote with a valid quote ID.
     * Asserts that the quote is successfully favorited.
     */
	@Test
	public void testFavoriteQuote_Sucess() {

		Response response = ApiClient.favoriteQuote(validQuoteId);
		JsonPath jsonPath = response.jsonPath();
		boolean isFavorited = jsonPath.getBoolean("user_details.favorite");

		// Asserts that the 'favorite' field is true and status code is 200
		TestHelpers.assertStatusCode(response, 200);
		TestHelpers.assertResponseNotNull(response);
		assertTrue(isFavorited, "Expected quote to be favorited.");
	}
	
	 /**
     * Tests unfavoriting a quote with a valid quote ID.
     * Asserts that the quote is successfully unfavorited.
     */
	@Test
	public void testUnfavoriteQuote_Success() {
		Response response = ApiClient.unfavoriteQuote(validQuoteId);
		Quote quote = response.as(Quote.class);// Convert response into POJO

		TestHelpers.assertStatusCode(response, 200);
        assertFalse(quote.isFavorited(), "Expected quote to be unfavorited.");

	}
	
    /**
     * Tests favoriting a quote with an invalid quote ID.
     * Asserts that a 404 error is returned.
     */
	@Test
	public void testFavoriteQuote_InvalidQuoteId() {
		   Response response = ApiClient.favoriteQuote(invalidQuoteId);       
	        // Validate response status
	        TestHelpers.assertStatusCode(response, 404);
	}
	
//	/**
//	 * looks like this logic wasn't deployed 
//	 * Test to unfavorite quote that is already unfavorited - .
//	 */
//	@Test
//	public void testUnfavoriteQuote_AlreadyUnfavorited() {
//	    int quoteId = 5;
//	    ApiClient.unfavoriteQuote(quoteId); // Ensure it's unfavorited
//
//	    Response response = ApiClient.unfavoriteQuote(quoteId);
//	    
//	    TestHelpers.assertStatusCode(response, 400); // Expecting a bad request if it's already unfavorited
//	}

    /**
     * Tests unfavoriting a quote with an invalid quote ID.
     * Asserts that a 404 error is returned.
     */
	@Test
	public void testUnfavoriteQuote_InvalidQuoteId() {
		Response response = ApiClient.unfavoriteQuote(invalidQuoteId);
        // Validate response status
        TestHelpers.assertStatusCode(response, 404);
	}
	
    /**
     * Tests the response headers when favoriting a quote.
     * Asserts that the "Content-Type" header is correctly set.
     */
	@Test
	public void testFavoriteQuote_ResponseHeaders() {
	    Response response = ApiClient.favoriteQuote(validQuoteId);

	    TestHelpers.assertStatusCode(response, 200);
	    assertEquals("application/json; charset=utf-8", response.getHeader("Content-Type"),
	            "Expected JSON response.");
	}
	
    /**
     * Tests favoriting a quote with a negative quote ID.
     * Asserts that a 404 error is returned.
     */
	@Test
	public void testFavoriteQuote_NegativeId() {
	    Response response = ApiClient.favoriteQuote(negativeQuoteId);
	    
	    TestHelpers.assertStatusCode(response, 404);
	}

}
