
import com.assyifacake.utils.UniqueStringGenerator;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.jupiter.api.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
public class testCRUDImage {
    
    
    @Test
    public void uniqueFileName() {
        
       UniqueStringGenerator unique = new UniqueStringGenerator();
       String string = unique.generate();
        System.out.println(string);
    }
    @Test
    public void insertImage() {
//        File file = new File("C:\\Users\\user\\Documents\\kuliah\\pemrograman desktop\\tugas\\Program Assyifa Cake\\img\\1.png");
// Java program to demonstrate read and write of image

    // width of the image
    int width = 150;

    // height of the image
    int height = 150;

    // For storing image in RAM
    BufferedImage image = null;

    // READ IMAGE
    try {
            File input_file = new File(
                    "C:\\Users\\user\\Documents\\kuliah\\pemrograman desktop\\tugas\\Program Assyifa Cake\\img\\1.png");

            // image file path create an object of
            // BufferedImage type and pass as parameter the
            // width, height and image int
            // type. TYPE_INT_ARGB means that we are
            // representing the Alpha , Red, Green and Blue
            // component of the image pixel using 8 bit
            // integer value.

            image = new BufferedImage(
                    width, height, BufferedImage.TYPE_INT_ARGB);

            // Reading input file
            image = ImageIO.read(input_file);

            System.out.println("Reading complete.");
    }
    catch (IOException e) {
            System.out.println("Error: " + e);
    }

    // WRITE IMAGE
    try {
            // Output file path
            File output_file = new File(
                     getClass().getClassLoader().getResource("/resources/img").getPath());

            // Writing to file taking type and path as
            ImageIO.write(image, "png", output_file);

            System.out.println("Writing complete.");
    }
    catch (IOException e) {

}
    }
}

