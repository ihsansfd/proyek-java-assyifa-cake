/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assyifacake.controllers;

import static com.assyifacake.db.KoneksiDatabase.getConnection;
import com.assyifacake.helpers.validations.AlphanumericValidation;
import com.assyifacake.helpers.validations.exceptions.NonAlphanumericException;
import com.assyifacake.views.Produk.LihatProdukView;
import java.awt.Image;
import static java.awt.Image.SCALE_SMOOTH;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class LihatProdukController {

    LihatProdukView lP;
   
    int offSet = 0;
    
    Map<Integer,JLabel> image = new HashMap<>();
    Map<Integer,JLabel> namaImage = new HashMap<>();
    
    public LihatProdukController(LihatProdukView lP) {
        
        this.lP = lP;
    }
    
    
    public void myComponents() {
            image.put(1, lP.getImage1());
            image.put(2, lP.getImage2());
            image.put(3, lP.getImage3());
            image.put(4, lP.getImage4());
            image.put(5, lP.getImage5());
            image.put(6, lP.getImage6());
            namaImage.put(1, lP.getNamaImage1());
            namaImage.put(2, lP.getNamaImage2());
            namaImage.put(3, lP.getNamaImage3());
            namaImage.put(4, lP.getNamaImage4());
            namaImage.put(5, lP.getNamaImage5());
            namaImage.put(6, lP.getNamaImage6());            
            String sql = "SELECT * FROM fileUpload LIMIT 6";
            tampilGambar(sql);     
    }
    
    public void kosonginGambar() {
        
        for(int i=1; i <= image.size(); i++) {
        image.get(i).setIcon(null);
        namaImage.get(i).setText("");
        }
    }
    
    public void  tampilGambar(String sql) {    
    if(Thread.currentThread() != null && Thread.currentThread().isAlive()) {
        Thread.currentThread().interrupt();
    }    
    kosonginGambar();
    Runnable threadGambar = new Runnable() {
                @Override
                public void run() {
                Connection conn = null;
                PreparedStatement pS = null;
                ResultSet rS = null; 
                    try {
                        if(offSet == 0) {
                            lP.getPrevBT().setEnabled(false);
                        } else{
                            lP.getPrevBT().setEnabled(true);
                        }
                        conn = getConnection();
                        pS = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        rS = pS.executeQuery();
                        rS.last();
                        if(rS.getRow()<6) {
                            lP.getNextBT().setEnabled(false);
                        } else {
                            lP.getNextBT().setEnabled(true);
                        }
                        
                        rS.beforeFirst();
                        int i =1;
                        while(rS.next()) {
                            if(Thread.currentThread().interrupted()) {                                    
                                throw new InterruptedException();
                            }                            
                            byte[] getBytes = rS.getBytes("gambar");
                            String namaDariDb = rS.getString("nama");
                            ByteArrayInputStream inputStream = new ByteArrayInputStream(getBytes);
                            BufferedImage gambar = ImageIO.read(inputStream);
                            ImageIcon iconBelumResized = new ImageIcon(gambar);
                            Image resizedImage = iconBelumResized.getImage().getScaledInstance(150, 150, SCALE_SMOOTH);
                            ImageIcon icon = new ImageIcon(resizedImage);


                            image.get(i).setIcon(icon);
                            namaImage.get(i).setText(namaDariDb);
                            i++;

                        }
                       


                    } catch (SQLException | IOException ex) {
                        Logger.getLogger(LihatProdukView.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(LihatProdukView.class.getName()).log(Level.SEVERE, null, "interrupted");

                    } finally {
                        try {
                            if(rS != null) {
                                rS.close();
                            }
                            if(conn != null) {
                                
                                conn.close();
                            }
                            if(pS != null) {
                                
                                pS.close();
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(LihatProdukView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            };


        Thread thread = new Thread(threadGambar);
        thread.start();

}
    
    public void search() {
        offSet =0;
                
        
        String ketikan = lP.getSearch().getText();
        try {
            AlphanumericValidation.validate(ketikan);
        } catch (NonAlphanumericException ex) {
            JOptionPane.showMessageDialog(lP, ex.getMessage());
            lP.getSearch().setText("");
            return;
        }
        String sql =  "SELECT * FROM fileUpload WHERE nama like '%"+ketikan+"%' LIMIT 6";    
    
        tampilGambar(sql);
    }
    
    public void prevBT() {
        if(!lP.getNextBT().isEnabled()){
            lP.getNextBT().setEnabled(true);
        }
        
        offSet-= 6;        
        if(lP.getSearch().getText().isBlank()) {

            String sql =  "SELECT * FROM fileUpload LIMIT 6 OFFSET " + offSet;    
            tampilGambar(sql);
            
        } else {
            String ketikan = lP.getSearch().getText();
            String sql =  "SELECT * FROM fileUpload WHERE nama LIKE '%"+ketikan+"%' LIMIT 6 OFFSET " +offSet;  
            tampilGambar(sql);

        }   
    }
    
    public void nextBT() {
        if(!lP.getPrevBT().isEnabled()){
        lP.getPrevBT().setEnabled(true);
        }    
        offSet+= 6;        
        if(lP.getSearch().getText().isBlank()) {

            String sql =  "SELECT * FROM fileUpload LIMIT 6 OFFSET " + offSet;    
            tampilGambar(sql);
            
        } else {
            String ketikan = lP.getSearch().getText();
            String sql =  "SELECT * FROM fileUpload WHERE nama LIKE '%"+ketikan+"%' LIMIT 6 OFFSET " +offSet;  
            tampilGambar(sql);

        }
    }
    
}
