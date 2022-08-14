package com.finalproject.javafinalproject;

/**
 *
 * @author Joshua
 */
public class Book extends TextDocument{
    
    private String author;

    public Book(String url, String title, String author) {
        super(url, title);
        this.author = author;
    }

    @Override
    String getType() {
        return "Book";
    }

    @Override
    String getCreator() {
        return this.author;
    }
    
    String getURL(){
        return super.getUrl();
    }
}
