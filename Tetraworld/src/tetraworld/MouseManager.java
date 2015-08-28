/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


    
     /* Pour faire écouter avec le Mouse Listener
    
     ObjectGraphique.addMouseListener(this);
     addMouseListener(this);
    
    
    Pressed > Clicked > Pressed
    
    */

package tetraworld;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.lang.System.exit;

/** Classe gérant la souris
 *
 */
public class MouseManager implements MouseListener {
    
    /** FieldMat */
    FieldMat field;
    MenuInterface menu;
    
    public MouseManager(FieldMat f){
        super();
        
        update(f, null);
    }
    
    public MouseManager(FieldMat f, MenuInterface M){
        super();
        
        update(f, M);
    }

    public MouseManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void update(FieldMat f, MenuInterface M){
        field = f;
        menu = M;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.print(" CLICKED");
        
	//Quand un bouton de la souris est clické
	} 
    
    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.print(" PRESSED");
        //System.out.println("Mouse pressed. x = " + e.getX() + " y = " + e.getY());
        
        if(field.isAnagram() || field.isWorddle())
            field.selectedBlocks(field.isClickedBlock(e.getX(), e.getY()));
    }
	//Quand un bouton de la souris est préssé

    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.print(" RELEASED");
       }
	//Quand un bouton de la souris est relâché

    @Override
    public void mouseEntered(MouseEvent e) {
        //Quand on entre dans une zone avec la souris
    }

    @Override
    public void mouseExited(MouseEvent e) {
       //Quand on sort d'une zone avec la souris
    }
        
}
