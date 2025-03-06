package com.FavQuote.models;
/*
 * POJO class. Represents Quote
 */
public class Quote {
	private int id;
    private String body;
    private String author;
    private boolean favorited; // to track favorited status

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public boolean isFavorited() { return favorited; } // Getter 
    public void setFavorited(boolean favorited) { this.favorited = favorited; } // Setter 
}

