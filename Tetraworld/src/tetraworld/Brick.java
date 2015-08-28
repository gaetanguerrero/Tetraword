/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetraworld;

import java.awt.Color;
import java.util.Random;

/**
 *Classe définissant les Brick, les formes composées de Block qui tombent dans la grille de jeu.
 */
public class Brick {
    /** Entier contenant la position en X de la Brick */
    private int xPos;
     /** Entier contenant la position en Y de la Brick */
    private int yPos;
    
     /** Char contenant le type de la Brick */
    private char type;
    
    /** Tableau d'entiers contenant les coordonnées des Block de la Brick */
    private int[] coordBlocks;
    
    /** Tbaleau de Blocks contenant les Blocks de la Brick */
    private Block[] blocks;
    
    /** Entier contenant la taille de la Brick */
    private int size;
    
    /** Booléen permettant de savoir si la Brick est jouable ou non */
    private boolean playable;
    
    /** Entier contenant l'angle pour la rotation de la Brick */
    private int angle;
    
    /**
     * Constructeur d'une Brick en passant en paramètre le type et un booléen pour playable 
     * @param t Type de block
     * @param p Booléen pour playable, si la pièce peut etre jouée ou si elle est en attente
     */
    public Brick(char t, boolean p){
        xPos = 0;
        yPos = 0;
        type = t;
        playable = p;
        
        angle =0;
        
        Block.BlockColor temp =  Block.BlockColor.getRandomBrickColor();
        
        switch(t){
            case 'a':
                coordBlocks = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 }; // null shape
                size = 4;
                break;
            case 'b':
                coordBlocks = new int[] { 0, -1, 0, 0, -1, 0, -1, 1 };
                size = 4;
                break;
            case 'c':
                coordBlocks = new int[] { 0, -1, 0, 0, 1, 0, 1, 1 };
                size = 4;
                break;
            case 'd':
                coordBlocks = new int[] { 0, -1, 0, 0, 0, 1, 0, 2 };
                size = 4;
                break;
            case 'e':
                coordBlocks = new int[] { -1, 0, 0, 0, 1, 0, 0, 1 };
                size = 4;
                break;
            case 'f':
                coordBlocks = new int[] { 0, 0, 1, 0, 0, 1, 1, 1 }; 
                size = 4;
                break;
            case 'g':
                coordBlocks = new int[] { 1, -1, 0, -1, 0, 0, 0, 1 };
                size = 4;
                break;
            default:
                //Here you take care of added shaped by the user
                size = 0; //temporary values
                break;
        }
        
        blocks = new Block[size];
        for(int i = 0; i<size; ++i)
                blocks[i] = new Block(temp);
        fillBlocksPos();
    }
  /*  
    private Color generateColor(){
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        
        return new Color(r,g,b);
    }
 */   
    public void fillBlocksPos(){
        for(int i=0; i<size; ++i)
            blocks[i].setPos(coordBlocks[2*i],coordBlocks[2*i+1]);
    }
    
    /**Setter de la position X de la Brick
     * 
     * @param newX Nouvelle coordonnées X
     */
    void setXPos(int newX){
        xPos = newX;
    }
    /**Setter de la position Y de la Brick
     * 
     * @param newY Nouvelle coordonnées Y
     */
    
    void setYPos(int newY){
        yPos = newY;
    }
    /**
     * Méthode pour switcher l'attribut playable de la Brick
     */
    void switchPlayable(){
        playable = !playable;
    }
    
    /**
     * Getter de la position X de la Brick
     * @return Position X de la Brick
     */
    int getXPos(){
        return xPos;
    }
    /**
     * Getter de la position Y de la Brick
     * @return Position Y de la Brick
     */
    int getYPos(){
        return yPos;
    }
    /**
     * Getter du Type de la Brick
     * @return Type de la Brick
     */
    char getType(){
        return type;
    }
    /**
     * Getter des coordonnées des Blocks
     * @return Tableau contenant les coordonnées de Blocks de la Brick
     */
    int[] getCoordBlocks(){
        return coordBlocks;
    }
    /**
     * Getter des Blocks de la Brick
     * @return Tableau des Blocks 
     */
    Block[] getBlocks(){
        return blocks;
    }
    
    /**
     * Getter de la position X de la Brick
     * @return true si la Brick est playable
     */
    
    boolean isPlayable(){
        return playable;
    }

    /**
     * Méthode permttant de rotate/tourner la brick sur elle-même
     */
    void rotate() {
        if(isPlayable()){
            if(angle == 3)
                angle=0;
            else
                ++angle;


            //System.out.println(type);

            if(angle == 0)
                switch(type){
                    case'b':
                        coordBlocks = new int[] { 0, -1, 0, 0, -1, 0, -1, 1 }; //
                        //System.out.println("ROTATE");
                        fillBlocksPos();
                        break;
                    case'c':
                        coordBlocks = new int[] { 0, -1, 0, 0, 1, 0, 1, 1 }; //
                        //System.out.println("ROTATE");
                        fillBlocksPos();
                        break;
                    case'd':
                        coordBlocks = new int[] { 0, -1, 0, 0, 0, 1, 0, 2 }; //straight
                        //System.out.println("ROTATE");
                        fillBlocksPos();
                        break;
                    case'e':
                        coordBlocks = new int[] { -1, 0, 0, 0, 1, 0, 0, 1 }; //T
                        //System.out.println("ROTATE");
                        fillBlocksPos();
                        break;
                    case'f':
                        coordBlocks = new int[] { 0, 0, 1, 0, 0, 1, 1, 1 }; //cube
                        //System.out.println("ROTATE");
                        fillBlocksPos();
                        break;
                    case'g':
                        coordBlocks = new int[] { 1, -1, 0, -1, 0, 0, 0, 1 };
                        //System.out.println("ROTATE");
                        fillBlocksPos();
                        break;
                    default:
                        break;
                }
            if(angle==1)
                switch(type){
                    case 'b':
                        coordBlocks = new int[] { 1, 0, 0, 0, 0, -1, -1, -1 }; //
                        fillBlocksPos();
                        break;
                    case 'c':
                        coordBlocks = new int[] { 1, 0, 0, 0, 0, 1, -1, 1 };  //
                        fillBlocksPos();
                        break;
                    case 'd':
                        coordBlocks = new int[] { -1, 0, 0, 0, 1, 0, 2, 0 }; //straight
                        fillBlocksPos();
                        break;
                    case 'e':
                        coordBlocks = new int[] { 0, -1, 0, 0, 0, 1, -1, 0 }; //T
                        fillBlocksPos();
                        break;
                    case 'g':
                        coordBlocks = new int[] { 1, 1, 1, 0, 0, 0, -1, 0 }; //reverse L
                        fillBlocksPos();
                        break;
                    default:
                        break;
                }

            if(angle==2)
                switch(type){
                    case 'b':
                        coordBlocks = new int[] { 0, 1, 0, 0, 1, 0, 1, -1 }; //
                        fillBlocksPos();
                        break;
                    case 'c':
                        coordBlocks = new int[] { 0, 1, 0, 0, -1, 0, -1, -1 }; //
                        fillBlocksPos();
                        break;
                    case 'd':
                        coordBlocks = new int[] { 0, -1, 0, 0, 0, 1, 0, 2 }; //straight
                        fillBlocksPos();
                        break;
                    case 'e':
                        coordBlocks = new int[] { -1, 0, 0, 0, 1, 0, 0, -1 }; //T
                        fillBlocksPos();
                        break;
                    case 'g':
                        coordBlocks = new int[] { -1, 1, 0, 1, 0, 0, 0, -1 }; //reverse L
                        fillBlocksPos();
                        break;
                    default:
                        break;
                }
            if(angle==3)       
                switch(type){
                    case 'd':
                        coordBlocks = new int[] { -1, 0, 0, 0, 1, 0, 2, 0 }; //straight
                        fillBlocksPos();
                        break;
                    case 'e':
                        coordBlocks = new int[] { 0, 1, 0, 0, 0, -1, 1, 0 }; //T
                        fillBlocksPos();
                        break;
                    case 'g':
                        coordBlocks = new int[] { -1, -1, -1, 0, 0, 0, 1, 0 }; //reverse L
                        fillBlocksPos();
                        break;
                    default:
                        break;
                }
        }
        

    }

    
}
