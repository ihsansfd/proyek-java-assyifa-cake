
import com.assyifacake.db.KoneksiDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
public class TestGetMetaDataResultSet {
    
    @Test
    public void test() {
        
        try {
            String sql = "SELECT * FROM fileUpload LIMIT 6 OFFSET 20";
            PreparedStatement pS = KoneksiDatabase.getConnection().prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, 
    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pS.executeQuery();
            rs.last();
            int size = rs.getRow();
            System.out.println(size);
            rs.beforeFirst();
            while(rs.next()) {
                
            System.out.println(rs.getString("nama"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestGetMetaDataResultSet.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
