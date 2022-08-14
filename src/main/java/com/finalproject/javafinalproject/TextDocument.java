package com.finalproject.javafinalproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 *
 * @author Joshua
 */
public abstract class TextDocument {

    private String url;
    private String title;
    public TextDocument(String url, String title) {
        this.url = url;
        this.title = title;
    }

    
    

    int getLines() throws FileNotFoundException {
        int lines = 0;
        File text = new File(url);
        Scanner reader = new Scanner(text);
        if (text.exists()) {
            while(reader.hasNextLine()){
                lines++;
            }
        }

        return lines;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }
    
    
    
    
   abstract String getType();
   
   abstract String getCreator();
}
