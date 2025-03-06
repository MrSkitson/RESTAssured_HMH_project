package com.FavQuote.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * POJO class. Represents Quote
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {
	private int id;
    private String body;
    private String author;
    private boolean favorite; // to track favorited status

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public boolean isFavorited() { return favorite; } // Getter 
    public void setFavorited(boolean favorited) { this.favorite = favorited; } // Setter 
}

