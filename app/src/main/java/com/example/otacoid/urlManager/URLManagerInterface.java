/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.otacoid.urlManager;

/**
 * A collection of methods that help create specific URLs for the purposes of the scraping procedure 
 *
 * @author Volpym
 */
public interface URLManagerInterface {
    /** Creates a URL based on the barcode 
     * 
     * @param barcode
     * @return URL.
     *  
    */
    public String createBarcodeLink(String barcode);
    /**
     * Creates the URL based on the ASIN Code 
     * 
     * @param asinCode
     * @return String
     */
    public String createListingLink(String asinCode);
    /** Creates the URL of the final page where the maximum price of the product is located
     * 
     * @param asinCode
     * @param totalNumberOfPages
     * @return URL.
    */
    public String createLastPageLink(int totalNumberOfPages, String asinCode);
    /**
     * Returns the status code of an HTTP connection to the given URL 
     * 
     * @param url
     * @return statusCode
     */
    public int getHTTPresponse (String url);
}
