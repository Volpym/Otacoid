/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.otacoid.database;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 *
 * @author jedic
 */
public class User implements UserInterface {
     com.example.otacoid.database.Product db = null;


    /**
     * Checks if the two given password are the same.
     * 
     * @param password Password, String cPassword.
     * 
     * @return Boolean;
     */
    @Override
    public boolean checkPassword(String password, String cPassword) {
        if(cPassword.equals(password)){
            return true;
        }else{
            return false;
        }
        
    }
    /**
     * Registers the user 
     * @param username
     * @param password
     * @param email 
     */
    @Override
    public void register(String username, String password, String email){
        System.out.println("Edw apo tin methodo register tis klasis User------------> Username:+"+username+" Password:"+password+" Email:"+email);
         try {
             URL url= new URL("http://192.168.1.5/user/register.php?username="+username+"&email="+password+"&password="+email);
             System.out.println(url);

             HttpURLConnection conn = (HttpURLConnection) url.openConnection();
             conn.connect();
             conn.getInputStream();
         } catch (MalformedURLException ex) {
             Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public int login(String username, String password){
        int response = 0;

        try {
            URL url= new URL("http://192.168.1.5/user/log_in.php?username="+username+"&password="+password);
            System.out.println(url);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            response = conn.getResponseCode();
            System.out.println("The response code is "+response);
            return response;

        }

        catch (MalformedURLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

            return response;

    }

    /**
     * Checks if the given Username is already in use by another account. Returns true if there is one, false if there isn't.
     * 
     * @param username Username.
     * @return Boolean
     */
    @Override
    public Boolean checkUserExistance(String username) {
        java.sql.Connection connection = db.connect("root", "ac0b3passGGsmokescreen", "jdbc:mysql://192.168.1.5/amazoup");
         try {
             String query = "SELECT username FROM users WHERE users.username = ?";
             PreparedStatement ps = connection.prepareStatement(query);
             ps.setString(0, username);
             ResultSet rs= ps.executeQuery();
             
             if(rs.next()){return true;}
             else{return false;}
         } catch (SQLException ex) {
             Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
         }
         
        return null;
        
    }
    /**
     * Checks if the given Email is already in use by another account. Returns true if there is one, false if there isn't.
     * 
     * @param email Email.
     * @return Boolean
     */
    @Override
    public Boolean checkEmailExistance(String email) {
        java.sql.Connection connection = db.connect("root", "ac0b3passGGsmokescreen", "jdbc:mysql://192.168.1.5/amazoup");
         try {
             String query = "SELECT email FROM users WHERE users.email = ?";
             PreparedStatement ps = connection.prepareStatement(query);
             ps.setString(0, email);
             ResultSet rs= ps.executeQuery();
             
             if(rs.next()){return true;}
             else{return false;}
         } catch (SQLException ex) {
             Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
         }
         return null;
         
    }

}
