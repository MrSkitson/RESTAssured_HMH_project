package com.FavQuote.config;

import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;

import com.FavQuote.models.Quote;
/**
* Utility class providing helper methods for API test assertions.
*/
public class TestHelpers {
	
	  /**
     * Asserts that the response has the expected HTTP status code.
     *
     * @param response          The API response object.
     * @param expectedStatusCode The expected HTTP status code.
     */
    public static void assertStatusCode(Response response, int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.statusCode(),
                "Expected status " + expectedStatusCode + " but got " + response.statusCode());
    }

    /**
     * Asserts that the response body is not null.
     *
     * @param response The API response object.
     */
    public static void assertResponseNotNull(Response response) {
        assertNotNull(response.getBody(), "Response body should not be null");
    }
    
     /**
      * Validates that the quote object contains the necessary fields.
      *
      * @param quote The Quote object to validate.
      */
    public static void assertQuoteValid(Quote quote) {
        assertNotNull(quote, "Quote object should not be null");
        assertNotNull(quote.getBody(), "Quote body should not be null");
        assertNotNull(quote.getAuthor(), "Quote author should not be null");
        assertTrue(quote.getId() > 0, "Quote ID should be greater than 0");
    }
}
