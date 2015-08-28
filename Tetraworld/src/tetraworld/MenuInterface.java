/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetraworld;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JLabel;

/** Classe de l'interface du menu principal
 *
 * @author Romain SANFILIPPO
 */
public class MenuInterface extends Interface implements ActionListener{
    
    /** Constructeur du menu principal en passant la taille de celle-ci
     * 
     * @param height Hauteur de la fentre de menu
     * @param width  Largeur de la fentre de menu
     */
    
    private Button play_button;
    private Button quit_button;
    
    
    
    public MenuInterface(int height, int width) {
        super(height, width, "background.png");
       
       
        
        /* Boutons du menu */
        play_button = new Button("New Game", Button.ButtonType.Large, Button.ButtonState.Default);
        play_button.addActionListener(this);
        //Button button_load = new Button("Load", Button.ButtonType.Large, Button.ButtonState.Default);
       // Button button_score = new Button("High scores", Button.ButtonType.Large, Button.ButtonState.Default);
        quit_button = new Button("Quit", Button.ButtonType.Large, Button.ButtonState.Default);
        quit_button.addActionListener(this);
        //Button button_help = new Button("?", Button.ButtonType.Little, Button.ButtonState.Default);
        
        
        /*
       play_button.addActionListener(new ActionListener(){
 
        public void actionPerformed(ActionEvent e) {
            start = true;
           System.out.println("jouer !" + start);
        }
      });
        */
        
        /* Logo */
        ImagePanel logo_panel = new ImagePanel("logo.png");
        logo_panel.setMaximumSize(new Dimension(750, 160));
        logo_panel.setPreferredSize(new Dimension(750, 160));
        
        
        /* Panel transparents */
        TransparentPanel panel_left = new TransparentPanel();
        TransparentPanel panel_right = new TransparentPanel();     
        Dimension dim = new Dimension(426,720);   
        panel_left.setMaximumSize(dim);
        panel_left.setPreferredSize(dim);
        panel_right.setMaximumSize(dim);
        panel_right.setPreferredSize(dim);
        
        /* Panel à droite */
        panel_right.setLayout(new BorderLayout());
        Box panel_rightBottom = Box.createVerticalBox();
        //panel_rightBottom.add(button_help);
        panel_rightBottom.add(new JLabel(" "));
        panel_rightBottom.add(new JLabel(" "));
        panel_right.add(panel_rightBottom, BorderLayout.SOUTH);
        
      
        /* Menu */
        Box panel_menu = Box.createVerticalBox();
        panel_menu.setPreferredSize(new Dimension(427,700));
        panel_menu.add(logo_panel);
        panel_menu.add(play_button);
        //panel_menu.add(button_load);
        //panel_menu.add(button_score);
        panel_menu.add(quit_button);
        
   
        /* Ajout des composants */
        background.add(panel_left);
        background.add(panel_menu);
        background.add(panel_right);
        

        this.setVisible(true);
        
        
    }
    
     @Override
   public void actionPerformed(ActionEvent e) {
  
       
    if(e.getSource() == play_button){
        //System.out.println("Go play !");
        go_play = true;
    }
    else if(e.getSource() == quit_button){
        go_quit = true;
        System.exit(0);
    }
   }
    
   
    
}
