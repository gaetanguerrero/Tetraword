/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetraworld;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import static tetraworld.Interface.Game_font;


/**Classe des bouttons
 *
 * @author Romain SANFILIPPO
 */
public class Button extends JButton{

   
    
    /**
     * Enum précisant les types de Button
     */
    public enum ButtonType{
        Little, Large, GameButton;
    }
    

 
    
    /**
     * Enum précisant l'état du Button
     */
    public enum ButtonState{
        Default, Disable, Hover;
    }
    
    /** BuferredImage du Button */
    private BufferedImage image;
    /** Taile du Button */
    private Dimension size;
    /** Position en X et en Y du Button */
    private int posX, posY; // Position du texte
    
    /** Titre du Button */
    private String title;
    
    /** Type du Button */
    private ButtonType type; // Petit  ex : bouton d'aide, Large ex : bouton du menu de jeu
    
    /** Etat du Button*/
    private ButtonState state;
   
    
    /** Taille de la police du Button*/
    private float font_size;
    
    
    

  /**Constructeur d'un Button en passant un titre, un type et un état
   * 
   * @param title Contenu/Titre du Button
   * @param type Type du Button
   * @param state Etat du button
     * @param action
   */
    
  public Button(String title, ButtonType type, ButtonState state){
      super();
      this.type = type;
      this.state = state;
      this.setBorderPainted(false); 
      this.setContentAreaFilled(false); 
      this.setFocusPainted(false);
      this.setOpaque(false);
      
      if(type == ButtonType.Little){
         this.size = new Dimension(93, 88);
         this.font_size = 44.f;
      }
      else if(type == ButtonType.Large){// Par défaut on utilise le bouton large
          this.size = new Dimension(316, 120);
          this.font_size = 44.f;
      }
      else if(type == ButtonType.GameButton){
          this.size = new Dimension(316, 120);
          this.font_size = 50.f;
      }
      
      
      this.setFont(Game_font.deriveFont(this.font_size));
      this.setPreferredSize(this.size);
      this.setMaximumSize(this.size);
      this.title = title;
 
      
      try {
         if(type == ButtonType.Little)
            image = ImageIO.read(new File("ressources/images/little_button.png"));
         else if(type == ButtonType.Large || type == ButtonType.GameButton)
             if(this.state == ButtonState.Default)
                image = ImageIO.read(new File("ressources/images/large_button.png"));
             else if(this.state == ButtonState.Disable)
                image = ImageIO.read(new File("ressources/images/large_button.png"));
         
      } catch (IOException ex) {
         System.err.println("Could not load the image file");
      }
    }
  
 
  /** Setter pour modifier l'état du Button
   * 
   * @param state Etat à appliquer au Button
   */
  
  public void setState(ButtonState state){
      this.state = state;
       try {
         if(type == ButtonType.Little)
            image = ImageIO.read(new File("ressources/images/little_button.png"));
         else if(type == ButtonType.Large || type == ButtonType.GameButton)
             if(this.state == ButtonState.Default)
                image = ImageIO.read(new File("ressources/images/large_button.png"));
             else if(this.state == ButtonState.Disable)
                image = ImageIO.read(new File("ressources/images/large_button.png"));
         
      } catch (IOException ex) {
         System.err.println("Could not load the image file");
      }
  }
  
 
  
 
    
    

  /** Méthode servant à afficher le button
   * 
   * @param g Graphics servant à l'affichage du boutton suivant son type et son état
   */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	
        g2.drawImage(image, 0, 0, this);
       
        if(this.type == ButtonType.Large){
           if(this.title.length() < 5){
            posX = this.getWidth()/3 + 8;
            posY = this.getHeight()/2;
           }
           else{
            posX = this.getWidth()/5 + 10;
            posY = this.getHeight()/2;
           } 
        }
        else if(this.type == ButtonType.Little){
           posX = this.getWidth()/3 + 4;
           posY = this.getHeight()/2 + 10; 
        }
        else if(this.type == ButtonType.GameButton){
          if(this.title.length() < 5){
            posX = this.getWidth()/3 + 8;
            posY = this.getHeight()/2;
           }
           else{
            posX = this.getWidth()/5 + 8;
            posY = this.getHeight()/2 + 5;
           }
        }
        
        g2.setColor(new Color(44,17,1));
        g2.drawString(this.title, posX+3, posY+3);
        g2.setColor(new Color(195,183,159));
        g2.drawString(this.title, posX, posY);
        
        
    }
    
    
    
}
