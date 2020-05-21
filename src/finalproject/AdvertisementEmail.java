/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.util.Date;

/**
 *
 * @author Joshua
 */
public class AdvertisementEmail extends Email{
    
    public AdvertisementEmail(String url, String title, Date date, String creator) {
        super(url, title, date, creator);
    }
     @Override
    String getType() {
        return "Advertisement";
    }   
}
