/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assyifacake.helpers.validation;

import com.assyifacake.helpers.validation.exception.NullException;
import javax.swing.JLabel;

/**
 *
 * @author user
 */
public class NotNullValidation {
    
    public static void validate(JLabel[] input) throws NullException {
        for(JLabel label : input) {
            if(label.getText().isBlank() || label.getText().isEmpty() ) {
                throw new NullException();
            }
        }
}
    
}
