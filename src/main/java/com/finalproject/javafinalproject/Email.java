package com.finalproject.javafinalproject;
import java.util.Date;


/**
 *
 * @author Joshua
 */
public class Email extends TextDocument {
    
    Date date;
    String creator;
    public Email(String url, String title, Date date, String creator) {
        super(url, title);
        this.date = date;
        this.creator = creator;
    }

    @Override
    String getType() {
        return "Email";
    }

    @Override
    String getCreator() {
        return this.creator;
    }
    
    Date getDate(){
        return this.date;
    }
    
}
