/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.otacoid.database;




import android.os.Build;
import android.support.annotation.RequiresApi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jedic
 */
public abstract class Product implements com.example.otacoid.database.ProductInterface {
    
    @Override
    public java.sql.Connection connect(String username, String password, String database) {
       try {
            
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
           
            java.sql.Connection conn = DriverManager.getConnection(database,username,password);
            return conn;

        } catch (SQLException | ClassNotFoundException ex) { 
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public String selectASINCode(java.sql.Connection conn, String barcode) {
        try {
            String query = "SELECT code FROM product WHERE barcode = ?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, barcode);
            ResultSet rs = ps.executeQuery(query);
            rs.next();
            String result = rs.getString("code");
            System.out.println(result);
            
            return rs.getString("code");    
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    @Override
    public ArrayList selectAll(java.sql.Connection conn) {
       
        try{
            ArrayList<String> inner = new ArrayList<>();
            String query = "SELECT * FROM product";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery(query);
            
            while(rs.next()){
                inner.add(rs.getString("barcode"));
               }
            
            return inner;
            
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("problem in RetrieveFromDB");
        }
        return null;
    }

    @Override
    public void update(java.sql.Connection conn, String description, String manufacturer, String barcode, String minPrice, String maxPrice, String deliveryCost, String seller) {
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        String query = "UPDATE product SET description = ?, manufacturer = ?, "
                + "minPrice = ?, maxPrice = ?, deliveryCost = ?, seller = ?, updated = ? "
                + "where barcode = ?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, description);
            ps.setString(2, manufacturer);
            ps.setString(3, minPrice);
            ps.setString(4, maxPrice );
            ps.setString(5, deliveryCost );            
            if(seller == null){seller = "Amazon";}
            ps.setString(6, seller );
            ps.setTimestamp(7, date);
            ps.setString(8, barcode);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String selectListingLink(String barcode, java.sql.Connection conn) {
         try {
            String query = "SELECT listingUrl FROM product WHERE barcode = ?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery(query);
            rs.next();
            String result = rs.getString("listingUrl");
            
            
            return rs.getString("listingUrl");    
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    @Override
    public Date selectTimeStamp(String barcode, Connection conn) {
        
        try {
            String query = "SELECT updated FROM product WHERE barcode = ?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery(query);
            rs.next();
            Date result = rs.getDate("updated");
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
         
    }
    
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean compareDates(LocalDate retrievedDate) {
       
        
        LocalDate currentDate = LocalDate.now();
        
        if(currentDate.isAfter(retrievedDate)){
         return true;
        }else{
         System.out.println("Was updated on "+currentDate.toString()+" :)");
         return false;   
        }
    }
    
    @Override
    public void deleteBarcode(String asinCode, java.sql.Connection conn) {
        try {
            String query = "Delete FROM product WHERE code = ?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute(query);
            } catch (SQLException ex) {
            System.out.println(ex.toString());
            
        }}

    
}
