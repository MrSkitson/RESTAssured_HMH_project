package com.FavQuote.tests;

import org.junit.jupiter.api.Test;

import com.FavQuote.api.ApiClient;
import com.FavQuote.config.TestBase;
import com.FavQuote.models.Quote;
import com.FavQuote.models.QuoteResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * FavQuoteTests contains test cases for favoriting and unfavoriting quotes.
 */
public class FavQuoteTests extends TestBase {

	@Test
	public void testFavoriteQuote_Sucess() {
		int quoteId = 4;
		Response response = ApiClient.favoriteQuote(quoteId);

		assertEquals(200, response.statusCode(), "Expected status 200 when favoriting a quote.");
		System.out.println("Quote favorited successfully!");
	}

	@Test
	public void testUnfavoriteQuote_Success() {
		int quoteId = 4; 
		Response response = ApiClient.unfavoriteQuote(quoteId);

		assertEquals(200, response.statusCode(), "Expected status 200 when unfavoriting a quote.");
		System.out.println("Quote unfavorited successfully!");
	}
	
    @Test
    public void testFavoriteQuote_InvalidQuoteId() {
        int invalidQuoteId = 999999; // Non-existent quote ID
        Response response = ApiClient.favoriteQuote(invalidQuoteId);

        assertEquals(404, response.statusCode(), "Expected status 404 for a non-existent quote.");
        System.out.println("Correctly handled non-existent quote.");
    }
    
    @Test
    public void testUnfavoriteQuote_InvalidQuoteId() {
        int invalidQuoteId = 212334263; // Non-existent quote ID
        Response response = ApiClient.unfavoriteQuote(invalidQuoteId);

        assertEquals(404, response.statusCode(), "Expected status 404 for a non-existent quote.");
        System.out.println("Correctly handled non-existent quote.");
    }
    
    /**
     * Test fetching the list of quotes successfully.
     */
    @Test
    public void testGetQuotes_Success() {
    	Response response = ApiClient.getQuotesResponse(); // Use method that returns raw Response

        assertEquals(200, response.statusCode(), "Expected status 200 when fetching quotes.");

        QuoteResponse quoteResponse = response.as(QuoteResponse.class); // Convert response into POJO

        assertNotNull(quoteResponse.getQuotes(), "Quotes list should not be null");
        assertFalse(quoteResponse.getQuotes().isEmpty(), "Expected at least one quote in the response.");


        System.out.println("Successfully retrieved quotes list.");
    }

    /**
     * Test to validate the response body
     */
    @Test
    public void testGetQuotes_ValidateResponseBody() {
        QuoteResponse quoteResponse = ApiClient.getQuotes();
        
        // Validate response structure
        assertNotNull(quoteResponse, "Response body should not be null");
        assertNotNull(quoteResponse.getQuotes(), "Quotes list should not be null");
        assertTrue(quoteResponse.getQuotes().size() > 0, "Quotes list should not be empty");

        // Validate first quote's data (assuming at least one quote exists)
        Quote firstQuote = quoteResponse.getQuotes().get(0);
        assertNotNull(firstQuote.getBody(), "Quote body should not be null");
        assertNotNull(firstQuote.getAuthor(), "Quote author should not be null");
        assertTrue(firstQuote.getId() > 0, "Quote ID should be greater than 0");

        System.out.println("Test Passed: Response body structure validated successfully!");
    }


    /**
     * Test fetching quotes with an invalid request (e.g., incorrect parameters).
     */
    @Test
    public void testGetQuotes_InvalidRequest() {
        Response response = ApiClient.getQuotesWithInvalidParams();

        // Validate error response
        assertEquals(400, response.statusCode(), "Expected status 400 for invalid request.");
        System.out.println("Correctly handled invalid request for quotes.");
    }
    
}
