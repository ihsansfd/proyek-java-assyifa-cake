/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assyifacake.helpers.validations;

import com.assyifacake.helpers.validations.exceptions.NonAlphanumericException;
import com.assyifacake.helpers.validations.exceptions.NotNumbersException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author user
 */
public class NumbersValidation {
    
     private String input = "^[0-9]{1,11}$";
     
     public void setLength(int from, int to) {
        
         if(to > 11) {
             to = 11;
         }
         
         if(from < 1) {
             from = 1;
         }
         input = "^[0-9]{"+from+","+to+"}$";
     }
     public void validate (String input, String name) throws NotNumbersException {
        Pattern p = Pattern.compile(input);
        Matcher m = p.matcher(input);
        
        if(!m.matches()) {
            NotNumbersException exception = new NotNumbersException();
            exception.setMessage("Input " +name + " harus diisi dengan angka yang valid");
            throw exception; 
        }  
}
}
