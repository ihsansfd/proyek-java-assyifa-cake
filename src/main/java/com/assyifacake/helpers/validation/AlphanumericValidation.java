/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assyifacake.helpers.validation;

import com.assyifacake.helpers.validation.exception.NonAlphanumericException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author user
 */
public class AlphanumericValidation {
    
    public static void validate (String input) throws NonAlphanumericException {
        Pattern p = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher m = p.matcher(input);
        
        if(!m.matches()) {
            throw new NonAlphanumericException();
        }  

}

    
}
