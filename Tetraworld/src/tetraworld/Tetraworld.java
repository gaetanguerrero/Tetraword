/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetraworld;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.lang.System.exit;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author niggaLuvWatermelon
 */
public class Tetraworld {

    /**
     * @param args the command line arguments
     */
    
    
    
    public static void main(String[] args) throws InterruptedException {
        
        int counter = 0;
        int scoreRate = 1;
        int currentState = 0;
        
        System.setProperty("awt.useSystemAAFontSettings","on");
        System.setProperty("swing.aatext", "true");
        
        FieldMat grid = new FieldMat(10,20);
        Player player = new Player("");
        Sound ps = new Sound("ressources/sounds/music.wav",true);
        
        
        Interface Gameframe;     
        
        if(currentState == 0){
            Gameframe = new MenuInterface(1280, 720);
            KeyManager Keys = new KeyManager(grid);
            MouseManager Mouse = new MouseManager(grid, (MenuInterface)Gameframe);
            Gameframe.addKeyListener(Keys);
            Gameframe.addMouseListener(Mouse);
            Gameframe.setFocusable(true);
            Gameframe.requestFocus();
            
            player = new Player("Player");
            
            
            
            while(currentState == 0){
                //System.out.println(Gameframe.getPlayState());
                sleep(1);
                if(Gameframe.getPlayState()){
                    System.out.println("Jouer");
                    currentState = 1;
                    Gameframe.dispose();
                }
                    
            }
                
        }
        
        if(currentState == 1){
            
            grid.playS();
            
            Gameframe = new GameInterface(1280, 720, player);
            Gameframe = (GameInterface) Gameframe;
            Gameframe.updateGameFrame(grid, player);
            
            KeyManager Keys = new KeyManager(grid);
            MouseManager Mouse = new MouseManager(grid, null);

            Gameframe.addKeyListener(Keys);
            Gameframe.addMouseListener(Mouse);
            Gameframe.setFocusable(true);
            Gameframe.requestFocus();
            ps.play();

            while(true){
                if(grid.isGameOver())
                    break;
                else{
                    Gameframe.requestFocus();

                    Keys.update(grid);
                    Mouse.update(grid, null);
                    ++counter;


                    //UPDATE TIME
                    Gameframe.updateGameFrame(grid, player);
                    if(counter == 10){
                        if(!grid.isAnagram())
                            if(!grid.isWorddle()){
                                player.updateScore(1 + scoreRate/100);
                                grid.update();
                                ++scoreRate;
                            }

                        counter=0;
                    }

                    player.updateScore(grid.scoreToBeAdded());
                    grid.resetScoreToBeAdded();

                    try {
                        sleep(1000/25);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Tetraworld.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }  
        }
        
        
        
        //GESTION GAMEOVER
    }

    
    
}
