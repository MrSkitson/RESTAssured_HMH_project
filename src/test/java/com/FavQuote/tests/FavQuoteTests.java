package com.FavQuote.tests;

import org.junit.jupiter.api.Test;

import com.FavQuote.api.ApiClient;
import com.FavQuote.config.TestBase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import io.restassured.response.Response;

/**
 * FavQuoteTests contains test cases for favoriting and unfavoriting quotes.
 */
public class FavQuoteTests extends TestBase{

	@Test
	public void testFavoriteQuote_Sucess() {
		int quoteId = 4;
		Response response = ApiClient.favoriteQuote(quoteId);
		
		 assertEquals(200, response.statusCode(), "Expected status 200 when favoriting a quote.");
	        System.out.println("Quote favorited successfully!");
	}
}
