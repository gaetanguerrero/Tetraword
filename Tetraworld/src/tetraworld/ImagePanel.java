/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetraworld;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 * Classe permettant le chargement des images
 */
public class ImagePanel extends TransparentPanel{

    private BufferedImage image;

    public ImagePanel(String file) {
      super();
       try {                
          image = ImageIO.read(new File("ressources/images/" + file));
       } catch (IOException ex) {
            System.err.println("Could not load the image file");
       }
    }
    
    public void setImage(String file){
        try {                
          image = ImageIO.read(new File("ressources/images/" + file));
       } catch (IOException ex) {
            System.err.println("Could not load the image file");
       } 
    }
    
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
    }

}
