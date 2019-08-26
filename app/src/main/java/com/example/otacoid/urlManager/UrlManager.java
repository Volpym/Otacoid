/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.otacoid.urlManager;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import com.example.otacoid.scraper.Scrapper;

/**
 *
 * @author jedic
 */
public abstract class UrlManager implements com.example.otacoid.urlManager.URLManagerInterface {
    
    @Override
    public String createBarcodeLink(String barcode) {
        String url = "https://www.amazon.co.uk/s?k=" + barcode + "&ref=nb_sb_noss";
        return url;
    }

    @Override
    public String createListingLink(String asinCode) {
        
        String link = "https://www.amazon.co.uk/gp/offer-listing/" + asinCode;
        System.out.println("ASIN CODE: "+asinCode);
        
        return link;
    }

    @Override
    public String createLastPageLink(int totalNumberOfPages, String resultLink) {
        int indexInt = (totalNumberOfPages-1)*10;
        String strIndex = String.valueOf(indexInt);
        
        String lastPageLink = resultLink+"/ref=olp_page_3?ie=UTF8&f_all=true&startIndex="+strIndex;
        
        
        return lastPageLink;
    }

    @Override
    public int getHTTPresponse(String listingUrl) {
        Connection.Response response;
        
        try {
             System.setProperty("http.proxyHost", "119.82.253.210");
             System.setProperty("http.proxyPort", "54134");   
             response = Jsoup.connect(listingUrl).ignoreHttpErrors(true).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.102 Safari/537.36")
            .header("cookie", "incap_ses_436_255598=\"F9EQLjSoHvMNHRxAq38wDOj6SOfVJ0QOUYqB/XpwC33c4wxiZ6RAjFPs0o/pUb3c6T7Ho2+ocfUkHmD8/iN4TK3rTr6j0VB/BTcORDXLgVqCEP6RB3deDUBgRhCL5gPyySAblpdZStjkPVjGCwIYiKhHgilk5k3MVCwAmutcSMpV0oNTzt8rw/HcBhziAYn0RmKmHagfvDbW4NZHU3VGsw==\"")
            .timeout(0).execute();
            System.out.println(response.statusCode());
            return response.statusCode(); 
        } catch (IOException ex) {
            Logger.getLogger(Scrapper.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }














}
