/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.otacoid.database;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 * A collection of methods for com.example.otacoid.database use
 * @author Volpym
 */
public interface ProductInterface {
     /**
     *  Uses JFileChooser to get the directory of the spreadsheet with the product barcodes
     * @return 
    */
    public String getFileDirectory();
    
    
     /**
     *  Retrieves all the barcodes from the com.example.otacoid.database and returns an ArrayList
     * @return
    */
    public ArrayList selectAll (java.sql.Connection conn);
    
    
     /**
     *  Retrieves the ASIN code from the com.example.otacoid.database that corresponds to the given barcode
     * @param barcode
     * @return 
    */
    public String selectASINCode (java.sql.Connection conn, String barcode);
    
    
    /**
     * Connects to the ProductInterface 
     * @param username
     * @param password
     * @param database
     * @return
     */
    public java.sql.Connection connect(String username, String password, String database);
    

    /**
     * Updates product's information 
     * 
     * @param conn
     * @param description
     * @param manufacturer
     * @param barcode
     * @param minPrice
     * @param maxPrice
     * @param deliveryCost
     * @param seller
     */
    public void update( java.sql.Connection conn, String description, String manufacturer, String barcode, String minPrice, String maxPrice, String deliveryCost, String seller);
    

    /**
     * Deletes the barcode from the list if it's not valid
     * @param asinCode
     */
    public void deleteBarcode(String asinCode, java.sql.Connection conn);
    

    /**
     * Retrieves the Listing URL of a product 
     * @param barcode
     * @return
     */
    public String selectListingLink(String barcode, java.sql.Connection conn);
    

    /**
     * Retrieves the Time Stamp of the latest update
     * @param barcode
     * @return
     */
    public Date selectTimeStamp (String barcode, Connection conn);
    

    /**
     * Compares the timeStamp with current day/time
     * @param retievedDate
     * @return
     */
    public boolean compareDates (LocalDate retievedDate);
    
    
}
