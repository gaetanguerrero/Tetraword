/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetraworld;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import static tetraworld.Interface.Game_font;

/**Classe définissant un Label
 *
 * @author Romain SANFILIPPO
 */
public class Label extends JLabel{
   
    /**
     * Enum définissant le type d'un Label
     */
    public enum LabelTextType{
        Simple, Double, Input;
    }
    
    /** Titre/Contenu du Label*/
    private String title;
    /** Type du Label */
    private LabelTextType text_type;
    /** Taille du Label */
    private float size;
    
    private int tabX; // Décalage en x pour dessiner l'ombre du texte
    private int tabY; // Décalage en y pour dessiner l'ombre du texte
    
    private boolean text_resized = false;
    
    /** Constructeur d'un Label
     * 
     * @param title Contenu/Titre du Label à construire
     * @param text_type Type du Label à construire
     * @param size Taille du Label à construire
     */
    public Label(String title, LabelTextType text_type, float size){
        //super(title);
        this.title = title;
        this.text_type = text_type;
        this.size = size;
        this.tabX = 0;
        this.tabY = 0;
        this.setFont(Game_font.deriveFont(this.size));
        this.setForeground(Color.black);
    }
    
    public Label(String title, LabelTextType text_type, float size, int x, int y){
       this(title, text_type, size);
       this.tabX = x;
       this.tabY = y;
    }
    
    /**
     * Méthode pour ajouter un char au contenu du Label
     * @param c char à ajouter
     */
    public void addChar(char c){
        this.title += c;
    }
    
    public void setTitle(String t){
        this.title = t;
        if(this.text_type == LabelTextType.Input && this.title.length() >= 16 && !text_resized){
                //System.out.println("Texte trop long");
                this.size -= 10;
                this.setFont(Game_font.deriveFont(this.size));
                text_resized = true;
                //this.title = " ";
        }
    }
        
   /** Méthode servant à afficher le label
   * 
   * @param g Graphics servant à l'affichage du label suivant son type et son état
   */
    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        FontMetrics fm = g.getFontMetrics(g.getFont());
        int height = fm.getHeight();
        
        
        
        if(this.text_type == LabelTextType.Simple){
            g2.setColor(Color.black);
            g2.drawString(this.title,0,height);
        }
        else if(this.text_type == LabelTextType.Double){
            g2.setColor(new Color(44,18,1));
            g2.drawString(this.title,this.tabX,height + this.tabY);
            
            g2.setColor(new Color(195,183,159));
            g2.drawString(this.title,0,height); 
        }
        else if(this.text_type == LabelTextType.Input){
            g2.setColor(new Color(195,183,159));
            if(this.text_resized)
                g2.drawString(this.title,0,height + 10);
            else
                g2.drawString(this.title,0,height); 
        }
       
        
    }
    
}
