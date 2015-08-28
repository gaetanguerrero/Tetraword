/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetraworld;

import java.util.Random;

/**
 * Classe des Bonus, les effets de jeu comme le ralentissement ou des bonus de score
 */
public class Bonus {
    
    /** 
     * Enum des différents types possibles de Bonus
     */
    public enum TypeBonus{
        Score, Reverse;
        
        /*
        Bonus de ralentissement ou d'accéleration de chute
        Bonus de tremblement (le plateau tremble et la pièce chutant également)
        Bonus de tempête (un vent latéral déplace la pièce chutant)
        Bonus de retournement de plateau (les pièces "montent" en haut)
        Bonus d'échange de plateau avec un adversaire
        Bonus de bonus ou malus de score
        Bonus bombe (la forme courante explose et détruit des briques aux alentours)
        Bonus de voyage temporel (l'état du jeu retourne 30 secondes en arrière)
        Bonus d'entrée dans le mode worddle permettant pendant un temps limité de trouver des mots en suivant des briques adjacentes
        */
        
        /**
         * Méthode permettant d'avoir aléatoirement un type
         * @return Un type aléatoire de Bonus
         */
        
        public static TypeBonus getRandomBonus() {
            return values()[(int) (Math.random() * values().length)];
        }
    }
    
    /** Type du Bonus */
    private TypeBonus type;
    /** Valeur du bonus */
    private int value; // Valeur à définir en fonction du bonus instancié
    /** Position en X du Modifer */
    private int xPos = 0;
    /** Position en Y du Modifer */
    private int yPos = 0;
    private boolean toDraw = false;
    private boolean enable = false;
    private int timer;
    
    /**
     * Contructeur d'un Modifier avec un Id Joueur et la présence d'un mot
     
     */
    public Bonus(){
       
        xPos = (int) (Math.random() * (9 - 2) + 2);
        yPos = (int) (Math.random() * ( 3 - 1) + 1);
        System.out.println("x : " + xPos);
        System.out.println("y : " + yPos);
        timer = (int) (Math.random() * (100));
        type = TypeBonus.getRandomBonus();     
     }
    
    public TypeBonus getTypeBonus(){
        return this.type;
    }
    
    public void setTypeBonus(TypeBonus type_bonus){
        this.type = type_bonus;
    }
    
    public int getX(){
        return this.xPos;
    }
    
    public int getY(){
        return this.yPos;
    }
    
    public int getTimer(){
        return this.timer;
    }
    
    public void setTimer(int timer){
        this.timer = timer;
    }
    
    public boolean getDrawState(){
        return this.toDraw;
    }
    
    public void setDrawState(boolean state){
        this.toDraw = state;
    }
    
    public boolean getEnable(){
        return this.enable;
    }
    
    public void setEnable(boolean enable){
        this.enable = enable;
    }
        
 }   