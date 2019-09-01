/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.otacoid.scraper;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author jedic
 */
public class Scrapper implements com.example.otacoid.scraper.ScraperInterface {
    
    @Override
    public String scrapeASINcode(String resultLink){
        String subString = resultLink.substring(resultLink.lastIndexOf("dp/") + 3, resultLink.indexOf("/ref"));
        System.out.println("The ASIN code is: " + subString);
        return subString;
        
    }
    
    @Override
    public String scrapeManufacturer(Document html) {
        Element productManufacturer = html.select("div#olpProductByline.a-section.a-spacing-mini").first();
        System.out.println("the manufacturer is: " + productManufacturer.text());
        return productManufacturer.text();
    }
   
    @Override
    public String scrapeMinPrice(Document html) {
        Element minPrice = html.select("span.a-size-large.a-color-price.olpOfferPrice.a-text-bold").first();
        return minPrice.text();
    }
    
    @Override
    public String scrapeMaxPrice(Document html) {
        Element maxPrice = html.select("span.a-size-large.a-color-price.olpOfferPrice.a-text-bold").last();
        System.out.println("the maximum price: " + maxPrice.text());
        return maxPrice.text();
    }

    @Override
    public String scrapeMinDeliveryCost(Document html) {
        Element transportationCost = html.select("p.olpShippingInfo").first();
        System.out.println("the transportation cost is: " + transportationCost.text());
        return transportationCost.text();
    }
    
    @Override
    public String scrapeSellerName(Document html) {
        Element sellerName = (Element) html.select("h3.a-spacing-none.olpSellerName").first();
        
        System.out.println("Seller: " + sellerName.text());
        return sellerName.text();
    }   

    @Override
    public String scrapeLink(Document html)   {
        String productLink;
        try{
            productLink = html.select("a.a-link-normal.a-text-normal").first().attr("abs:href");
            return productLink;
        }catch(java.lang.NullPointerException ex){
            productLink = "invalid";
            
            return productLink;
        } 
    }
    
    @Override
    public String scrapeDescription(Document html) {
        Element productManufacturer = html.select("h1.a-size-large.a-spacing-none").first();
        System.out.println("the Desctription is: " + productManufacturer.text());
        return productManufacturer.text();
    }
    
    @Override
    public int scrapeNumberOfPages(Document html) {
        int totalNumberOfPages;
        
        Elements numberOfPages = html.select("ul.a-pagination");
        
        String lastPageNumber = numberOfPages.last().text();
        String subString = lastPageNumber.substring(lastPageNumber.lastIndexOf(" ") - 1, lastPageNumber.indexOf(" Next→"));   
        totalNumberOfPages = Integer.parseInt(subString);
        
        return totalNumberOfPages;
        
        
    }
    
   
       
 
    
    @Override
    public String scrapeNumberOfListings(Document html) {
       Element numberOfListings = html.select("span.olp-padding-right").first();
       String number = numberOfListings.text();
       System.out.println("There are totally " + number + " Listings");
       return number;
    }

    @Override
    public String scrapeImageUrl(Document html) {
        Element imageUrl = html.select("a.a-link-normal").first().child(0);
       
        System.out.println("Image Url: " + imageUrl.attr("src"));
        
        return imageUrl.attr("src");
    }

    @Override
    public Document connectToSite(String url) {
        Document productListing;
        try {
             
             productListing = Jsoup.connect(url).ignoreHttpErrors(true).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.102 Safari/537.36")
            .header("cookie", "incap_ses_436_255598=\"F9EQLjSoHvMNHRxAq38wDOj6SOfVJ0QOUYqB/XpwC33c4wxiZ6RAjFPs0o/pUb3c6T7Ho2+ocfUkHmD8/iN4TK3rTr6j0VB/BTcORDXLgVqCEP6RB3deDUBgRhCL5gPyySAblpdZStjkPVjGCwIYiKhHgilk5k3MVCwAmutcSMpV0oNTzt8rw/HcBhziAYn0RmKmHagfvDbW4NZHU3VGsw==\"")
            .timeout(0).get();
            
            return productListing; 
            
        } catch (IOException ex) {
            Logger.getLogger(Scrapper.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
 
    }

}
