/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetraworld;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Classe gérant les interfaces du jeu
 * @author Romain SANFILIPPO
 */
public class Interface extends JFrame{

    /**
     * Entier contenant la hauteur de l'interface
     */
    final private int height;
    /**
     * Entier contenant la largeur de l'interface
     */
    final private int width;
    /** Font contenant la police utilisée dans l'interface */
    static Font Game_font;
    
    /** JPanel contenant l'image de fond */
    protected ImagePanel background; // JPanel avec image de fond
    
    protected boolean go_play = false;
    protected boolean go_quit = false;
 
   
    /**
     * Constructeur de l'interface
     * @param height Hauteur en pixel de l'interface
     * @param width Largeur en pixel de l'interface
     * @param background String contenant le chemin vers l'image de fond
     */
    
    @SuppressWarnings("LeakingThisInConstructor")
    public Interface(int height, int width, String background){     
        super("Tetraword");       
        this.height = height;
        this.width = width;
        this.setSize(this.height, this.width);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        /* Image de fond et mise en page de la fenêtre */
        this.setLayout(new BorderLayout());
        this.background = new ImagePanel("background.png");
        this.add(this.background);
        this.background.setLayout(new BoxLayout(this.background, BoxLayout.LINE_AXIS));
        
        JButton button = new JButton("Clear");
        
        /* Récupération de la font depuis le dossier */
        try
        {
             File font_file = new File("ressources/fonts/Equestria.ttf");

             Game_font = Font.createFont(Font.TRUETYPE_FONT, font_file);
             Game_font = Game_font.deriveFont((float)38.0);
             
        }
        catch (FontFormatException | IOException e) {
            System.err.println("Could not load the font file ");
        }
        
        this.setVisible(true);  
    }
     
    
    public void Clear(){
        this.removeAll();
        this.revalidate();
        this.repaint();
    }
    
    public void Close(){
        System.exit(0);
    }
    
     public boolean getPlayState(){
        return this.go_play;
    }
    
    public boolean getQuitState(){
        return this.go_quit;
    }
    
  

    void updateGameFrame(FieldMat grid, Player player) {
       
    }


}
