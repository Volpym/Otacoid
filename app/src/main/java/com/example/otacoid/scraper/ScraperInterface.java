/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.otacoid.scraper;

import org.jsoup.nodes.Document;

/**
 * A collection of methods that are essential to the process of Amazon Scrapping
 *
 * @author Volpym
 */
public interface ScraperInterface {
    /**
     * Establishes a connection to the given URL, from where you will be able to extract
     * all the necessary information to proceed.
     *
     * @param url is the given URL
     * @return Document object
     */
    public Document connectToSite(String url);

    /**
     * Extracts product's ASIN Code from a link
     *
     * @param resultLink
     * @return subString
     */
    public String scrapeASINcode(String resultLink);

    /**
     * Scrapes the Description of the product.
     *
     * @param html is the Document object returned from ConnectToSite method
     * @return a string with the product's desctription
     */
    public String scrapeDescription(Document html);

    /**
     * Scrapes the Manufactuter's name of the product.
     *
     * @param html is the Document object returned from ConnectToSite method.
     * @return a string with the Manufacturer's name.
     */
    public String scrapeManufacturer(Document html);

    /**
     * Scrapes the Minimum Price of the product.
     *
     * @param html is the Document object returned from ConnectToSite method
     * @return a string with the product's Minimum Price
     */
    public String scrapeMinPrice(Document html);

    /**
     * Scrapes the Maximum Price of the product.
     *
     * @param html is the Document object returned from ConnectToSite method
     * @return a string with the product's Maximum Price
     */
    public String scrapeMaxPrice(Document html);

    /**
     * Scrapes the URL where the product's image is located.
     *
     * @param html is the Document object returned from ConnectToSite method
     * @return a string with the product's image url.
     */
    public String scrapeImageUrl(Document html);

    /**
     * Scrapes the product's listing URL .
     *
     * @param html is the Document object returned from ConnectToSite method
     * @return a string with the product's listing URL.
     */
    public String scrapeLink(Document html);

    /**
     * Scrapes the delivery costs of the product with the Minimum Price.
     *
     * @param html is the Document object returned from ConnectToSite method
     * @return a string with the product's delivery costs.
     */
    public String scrapeMinDeliveryCost(Document html);

    /**
     * Scrapes Seller's name.
     *
     * @param html is the Document object returned from ConnectToSite method
     * @return a string with the Seller's name.
     */
    public String scrapeSellerName(Document html);

    /**
     * Scrapes the number of listings of the product.
     *
     * @param html is the Document object returned from ConnectToSite method
     * @return a string the number.
     */
    public String scrapeNumberOfListings(Document html);

    /**
     * Calculates the number of pages the listings have .
     *
     * @param html is the Document object returned from ConnectToSite method
     * @return integer with the number.
     */
    public int scrapeNumberOfPages(Document html);
}
