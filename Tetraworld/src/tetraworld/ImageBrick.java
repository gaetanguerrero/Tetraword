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

import static tetraworld.Interface.Game_font;

/** Classe contenant les informations relatives à ImageBrick
 *
 * 
 */
public class ImageBrick extends ImagePanel{
    
    
    
    private int posX;
    private int posY;
    final private int width = 29;
    final private int height = 29;
    private Block.BlockColor color;
    private Color text_color;
    private char c;
    private boolean selected;
            

    public ImageBrick(Block.BlockColor color, char c, int x, int y, boolean selected) {
        
        super("brick.png");
        String image = "brick.png";
        this.selected = selected;
        this.color = color;
        this.c = c;
        this.posX = x;
        this.posY = y;
        this.setFont(Game_font.deriveFont(22.0f));
        
           
        if(!this.selected){
            if(this.color == Block.BlockColor.blue){
                image = "blue_brick.png";
            }
            else if(this.color == Block.BlockColor.azur){
                image = "azur_brick.png";
            }
            else if(this.color == Block.BlockColor.red){
                image = "red_brick.png";
            }
            else if(this.color == Block.BlockColor.magenta){
                image = "magenta_brick.png";
            }
            else if(this.color == Block.BlockColor.yellow){
                image = "yellow_brick.png";
            }
            else if(this.color == Block.BlockColor.green){
                image = "green_brick.png";
            }
        }
        
        //Block.BlockColor.printBlockColor(color);
        //System.err.println("file : " + image);
        this.setImage(image);
        
    }
    
    public ImageBrick(char c, int x, int y){
        super("bonus_brick.png");
        this.c = c;
        this.posX = x;
        this.posY = y;
        this.setFont(Game_font.deriveFont(22.0f));
    }
    
    
    
    public char isClicked(int x, int y){
        if((x >= this.posX && x <= this.posX + width) && (y >= this.posY && y <= this.posY + height)){
            System.out.println("Block clicked");
            return this.c;
        }
        else
            return '\0';
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
       
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
       
        
        posX = this.getWidth()/3;
        posY = this.getHeight()/2 + 6;
        
        if(!this.selected){
            if(this.color == Block.BlockColor.blue){
                this.text_color = new Color(3,38,70);
            }
            else if(this.color == Block.BlockColor.azur){
                this.text_color = new Color(2,54,68);
            }
            else if(this.color == Block.BlockColor.red){
                this.text_color = new Color(66,4,2);
            }
            else if(this.color == Block.BlockColor.magenta){
                this.text_color = new Color(67,0,12);
            }
            else if(this.color == Block.BlockColor.yellow){
                this.text_color = new Color(57,37,9);
            }
            else if(this.color == Block.BlockColor.green){
                this.text_color = new Color(1,67,5);
            }
        }
        else{
            this.text_color = new Color(30,30,30);
        }
        
        g2.setColor(text_color);
        g2.drawString(Character.toString(this.c), posX+2, posY+2);
        g2.setColor(Color.white);
        g2.drawString(Character.toString(this.c), posX, posY);
        
        
        
    }
    
    
    
    
}
