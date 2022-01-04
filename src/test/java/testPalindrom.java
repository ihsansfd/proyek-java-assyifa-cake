
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
public class testPalindrom {
    
    public Boolean palindromChecker(String input) {
        
        input = "";
        for(int i = input.length()-1; i>=0; i--) {
            input += input.charAt(i);
        }
        return input.equals(input);
    }
    @Test
    public void callPalindromChecker() {
        Boolean inputBaru = palindromChecker("kodok");
        Assertions.assertTrue(inputBaru);
    
}
    
    
}
