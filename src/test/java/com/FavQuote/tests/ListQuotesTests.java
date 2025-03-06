package com.FavQuote.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.FavQuote.api.ApiClient;
import com.FavQuote.config.TestBase;
import com.FavQuote.config.TestHelpers;
import com.FavQuote.models.Quote;
import com.FavQuote.models.QuoteResponse;

import io.restassured.response.Response;

/**
 * Test class for listing quotes from the API.
 */
public class ListQuotesTests extends TestBase {

	/**
	 * Tests fetching the list of quotes successfully. Asserts that the response
	 * contains at least one quote.
	 */
	@Test
	public void testGetListQuotes_Success() {
		Response response = ApiClient.getQuotesResponse();
		TestHelpers.assertStatusCode(response, 200);
		TestHelpers.assertResponseNotNull(response);

		QuoteResponse quoteResponse = response.as(QuoteResponse.class); // Convert response into POJO

		assertNotNull(quoteResponse.getQuotes(), "Quotes list should not be null");
		assertFalse(quoteResponse.getQuotes().isEmpty(), "Expected at least one quote in the response.");

	}

	/**
	 * Tests validating the response body of the quotes list. Asserts that the
	 * response structure contains required fields.
	 */
	@Test
	public void testGetListQuotes_ValidateResponseBody() {
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
	}

	/**
	 * Tests fetching quotes with parameters. Asserts that the response is
	 * successful.
	 */
	@Test
	public void testGetListQuotesWithParams_Sucess() {
		Response response = ApiClient.getQuotesWithParams();

		// Validate error response
		TestHelpers.assertStatusCode(response, 200);

	}
}
