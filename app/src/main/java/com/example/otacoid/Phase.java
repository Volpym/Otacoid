/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.otacoid;

import com.example.otacoid.database.Product;
import com.example.otacoid.scraper.Scrapper;
import com.example.otacoid.urlManager.UrlManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.jsoup.nodes.Document;

/**
 * This class splits the process into two parts. The barcode and ASIN phase. If we are not aware of the ASIN code we run the barcode phase. 
 * But if we have scraped the ASIN Code on a previous instance then we should proceed with the ASIN phase. That way we minimize the number 
 * of connection to Amazon.
 * @author jedic
 * 
 */
class Phase{
   
   Product database;
   Scrapper spiderman;
   UrlManager jarvis;
   
   java.sql.Connection connection = database.connect("root", "password", "jdbc:mysql://localhost/amazoup");
   java.sql.Statement myStmt;
   
   /**
    * This phase is used only when we try to scrape product information for the first time
    * 
    * @param barcode
    */
   void startBarcodePhase(String barcode) throws SQLException{
        String query;
        String searchUrl;
        String listingUrl;
        String asinCode;
        String resultLink;
        int httpResponse = 0;
        
        System.out.println("Barcode Phase commense");
        
        query = "UPDATE product SET"
              + "code = ?, "
              + "listingUrl = ? "
              + "WHERE barcode = ?";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        
        
        searchUrl = jarvis.createBarcodeLink(barcode);
        Document resultHtml = spiderman.connectToSite(searchUrl);
        
        
        resultLink = spiderman.scrapeLink(resultHtml);
        if(resultLink.equals("invalid")){return;}
        asinCode = spiderman.scrapeASINcode(resultLink);
        listingUrl = jarvis.createListingLink(asinCode);
        
        
        preparedStmt.setString(1, asinCode);
        preparedStmt.setString(2, listingUrl);
        preparedStmt.setString(3, barcode);
        preparedStmt.execute();
        
        
   }
   
   /**
    * This phase is used mainly to update the product information    * 
    * @param barcode
    */
   
   void startAsinPhase(String barcode) throws SQLException{
       Document resultHtml;
       String description;
       String manufacturer;
       String minPrice;
       String maxPrice;
       String minDeliveryCost;
       String seller;
       String searchUrl;
       int totalNumberOfPages = 0;
       
       
       System.out.println("ASIN phase commence");
       String listingUrl = database.selectListingLink(barcode, null);
       String asinCode = database.selectASINCode(connection, barcode);
       
       
       int httpResponse = jarvis.getHTTPresponse(listingUrl);
       
       if (httpResponse == 200){
           
           resultHtml = spiderman.connectToSite(listingUrl);
           description = spiderman.scrapeDescription(resultHtml);
           manufacturer = spiderman.scrapeManufacturer(resultHtml);           
           minPrice = spiderman.scrapeMinPrice(resultHtml);
           minDeliveryCost = spiderman.scrapeMinDeliveryCost(resultHtml);
           seller = spiderman.scrapeSellerName(resultHtml);
           totalNumberOfPages= spiderman.scrapeNumberOfPages(resultHtml);
           
           /* 
                Checks if there are multiple pages of the listing, in order to find the information of the listing with the maximum price
           */
           
           if (totalNumberOfPages != 0){
               searchUrl = jarvis.createLastPageLink(totalNumberOfPages,listingUrl);
               resultHtml = spiderman.connectToSite(searchUrl);
               maxPrice = spiderman.scrapeMaxPrice(resultHtml);
               database.update(connection, description, manufacturer, barcode, minPrice, maxPrice, minDeliveryCost, seller);
               connection.commit();
           }else if (totalNumberOfPages == 0){
               searchUrl = jarvis.createLastPageLink(totalNumberOfPages,listingUrl);
               resultHtml = spiderman.connectToSite(searchUrl);
               maxPrice = spiderman.scrapeMaxPrice(resultHtml);
               database.update(connection, description, manufacturer, barcode, minPrice, maxPrice, minDeliveryCost, seller);
               
           }
       }else if(httpResponse == 404){
           database.deleteBarcode(asinCode, null);
       }
              
   }
   
}
