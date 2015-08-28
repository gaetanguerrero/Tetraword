/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetraworld;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JTextField;
import static tetraworld.Interface.Game_font;

/**Classe définissant un TextField
 *
 * @author Romain SANFILIPPO
 */
public class TextField extends JTextField{
    
    /**
     * Constructeur par défaut d'un TextField
     */
    public TextField(){
        super();
        this.setFont(Game_font.deriveFont(33.0f));
        this.setForeground(new Color(195,183,159));
    }
    
    /** Méthode servant à afficher le button
   * 
   * @param g Graphics servant à l'affichage du TextField
   */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);      
    }
    
}
