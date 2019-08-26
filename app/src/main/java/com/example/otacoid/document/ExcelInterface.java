/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.otacoid.document;

/**
 * A collection of methods for the excel manipulation
 * @author Volpym
 */
public interface ExcelInterface {
     /**
      * Inserts the barcodes of an excel file to the com.example.otacoid.database
      * 
      * @param fileDirectory
     * @param myStmt
      */
     public void readExcel(String fileDirectory, java.sql.Statement myStmt);
}
