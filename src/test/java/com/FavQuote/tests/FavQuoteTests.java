package com.FavQuote.tests;

import org.junit.jupiter.api.Test;

import com.FavQuote.api.ApiClient;
import com.FavQuote.config.TestBase;
import com.FavQuote.config.TestHelpers;
import com.FavQuote.models.Quote;
import com.FavQuote.models.QuoteResponse;

import static org.junit.jupiter.api.Assertions.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * Test class for favoriting and unfavoriting quotes.
 */
public class FavQuoteTests extends TestBase {
	
	 /**
     * Tests favoriting a quote with a valid quote ID.
     * Asserts that the quote is successfully favorited.
     */
	@Test
	public void testFavoriteQuote_Sucess() {

		int quoteId = 4;
		Response response = ApiClient.favoriteQuote(quoteId);
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
		int quoteId = 4;
		Response response = ApiClient.unfavoriteQuote(quoteId);
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
		int invalidQuoteId = 999999; // Non-existent quote ID
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
		int invalidQuoteId = 212334263; // Non-existent quote ID
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
	    int quoteId = 4;
	    Response response = ApiClient.favoriteQuote(quoteId);

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
	    int invalidQuoteId = -1; // Invalid negative ID
	    Response response = ApiClient.favoriteQuote(invalidQuoteId);
	    
	    TestHelpers.assertStatusCode(response, 404);
	}

}
