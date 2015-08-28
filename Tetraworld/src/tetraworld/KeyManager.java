/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*  VK_CONTROL = ctrl
    VK_SPACE = espace
    VK_ENTER = Entrée
    VK_BACK_SPACE = retour chariot
    VK_ESCAPE = Echap
    VK_W = W
    VK_P = P
    VK_PAUSE = Pause
    VK_SHIFT = Shift
*/
package tetraworld;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Classe permettant de gérer le clavier
 */
public class KeyManager implements KeyListener {
    
    FieldMat field;
    
    public KeyManager(FieldMat f){
        super();
        
        update(f);
    }

    KeyManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Met à jour la FieldMat
     * @param f Nouvelle FieldMat
     */
    public void update(FieldMat f){
        field = f;
    }
    
    /**
     * Méthode intervenant lorsque qu'une touche du clavier est préssée
     * @param event Touche préssée
     */
    @Override
    public void keyPressed(KeyEvent event) {
        //System.out.println("Code touche pressée : " + event.getKeyCode() + " - caractère touche pressée : " + event.getKeyChar());
        switch (event.getKeyCode())
        {
            
            case KeyEvent.VK_A:
                if(field.completeLine()!=-1)
                    if(!(field.isAnagram()||field.isWorddle()))
                    field.anagramOn();
                break;
            
            case KeyEvent.VK_W:
                if(!(field.isAnagram()||field.isWorddle()))
                    field.worddleOn();
                break;
            
            case KeyEvent.VK_ENTER:
                field.testDelete();
                field.resetSelected();
                break;
                
            case KeyEvent.VK_DOWN:
                //System.out.println("DOWN PRESSED");
                if(!(field.isAnagram()||field.isWorddle()))
                    field.update();
                break;

            case KeyEvent.VK_LEFT:
                //System.out.println("LEFT PRESSED");
                if(!(field.isAnagram()||field.isWorddle()))
                    field.moveLeft();
                break;

            case KeyEvent.VK_UP:
                //System.out.println("UP PRESSED");
                if(!(field.isAnagram()||field.isWorddle()))
                    field.rotate();
                break;

            case KeyEvent.VK_RIGHT:
                //System.out.println("RIGHT PRESSED");
                if(!(field.isAnagram()||field.isWorddle()))
                    field.moveRight();
                break; 
        }
    }

    /**
     * Méthode intervenant lorsque qu'une touche du clavier est relâchée
     * @param event Touche relâchée
     */
    @Override
    public void keyReleased(KeyEvent event) {
        //System.out.println("Code touche relâchée : " + event.getKeyCode() + " - caractère touche relâchée : " + event.getKeyChar());         
        switch (event.getKeyCode())
        {
            case KeyEvent.VK_DOWN:
                //System.out.println("DOWN RELEASED");
                break;

            case KeyEvent.VK_LEFT:
                //System.out.println("LEFT RELEASED");
                break;

            case KeyEvent.VK_UP:
                //System.out.println("UP RELEASED");
                break;

            case KeyEvent.VK_RIGHT:
                //System.out.println("RIGHT RELEASED");
                break; 
        }                
    }

    /**
     * Méthode intervenant lorsque qu'une touche du clavier est tapée
     * @param event Touche tapée
     */
    @Override
    public void keyTyped(KeyEvent event) {
        //System.out.println("Code touche tapée : " + event.getKeyCode() + " - caractère touche tapée : " + event.getKeyChar());
         switch (event.getKeyCode())
        {
            case KeyEvent.VK_DOWN:
                //System.out.println("DOWN TYPED");
                break;

            case KeyEvent.VK_LEFT:
                //System.out.println("LEFT TYPED");
                break;

            case KeyEvent.VK_UP:
                //System.out.println("UP TYPED");
                break;

            case KeyEvent.VK_RIGHT:
                //System.out.println("RIGHT TYPED");
                break; 
        }    
    }   	

}
