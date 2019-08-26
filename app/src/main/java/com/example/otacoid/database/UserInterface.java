/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.otacoid.database;

import android.content.Context;
import android.os.AsyncTask;

/**
 *
 * @author jedic
 */
public interface UserInterface {
    
    public boolean checkPassword(String password, String cPassword);
    
    public void register(String username, String password, String email);

    public int login (String username, String password);

    public Boolean checkUserExistance(String username);
    
    public Boolean checkEmailExistance(String email);
    
}
