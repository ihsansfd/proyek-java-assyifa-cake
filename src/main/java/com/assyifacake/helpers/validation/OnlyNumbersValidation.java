/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assyifacake.helpers.validation;

import com.assyifacake.helpers.validation.exception.NonAlphanumericException;
import com.assyifacake.helpers.validation.exception.NotNumbersException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author user
 */
public class OnlyNumbersValidation {
        
    private static Pattern p = Pattern.compile("^[a-zA-Z0-9]*$");
    
    public static void validate(String input) throws NotNumbersException {
        Matcher m = p.matcher(input);
        
        if(!m.matches()) {
            throw new NotNumbersException();
        }          
    }
    
    public static Boolean check(String input) {
        Matcher m = p.matcher(input);
        
        return m.matches();
        
    }
    
}
