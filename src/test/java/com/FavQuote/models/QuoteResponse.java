package com.FavQuote.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class QuoteResponse {
	
		@JsonProperty("quotes")
	    private List<Quote> quotes;

	    @JsonProperty("page")
	    private int page;

	    @JsonProperty("last_page")
	    private boolean lastPage;

	    // Getters and Setters
	    public List<Quote> getQuotes() { return quotes; }
	    public void setQuotes(List<Quote> quotes) { this.quotes = quotes; }

	    public int getPage() { return page; }
	    public void setPage(int page) { this.page = page; }

	    public boolean getLastPage() { return lastPage; }
	    public void setLastPage(boolean lastPage) { this.lastPage = lastPage; }
}
