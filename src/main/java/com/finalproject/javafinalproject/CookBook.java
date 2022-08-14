/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.javafinalproject;

/**
 *
 * @author Joshua
 */
public class CookBook extends Book {
    private String recipie;
    public CookBook(String url, String title, String author, String recipie) {
        super(url, title, author);
        this.recipie = recipie;
    }

    @Override
    String getType() {
        return "CookBook";
    }
     
    String getRecipie(){
        return this.recipie;
    }
}
