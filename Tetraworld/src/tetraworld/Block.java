/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetraworld;

import java.awt.Color;
import java.util.Random;

/** Classe définissant les élements de base contenant une lettre, ce sont les Blocks qui forment les Bricks de jeu.
 *
 * @author niggaLuvWatermelon
 */
public class Block {
    
    
    //Remove position ?
    
    /** Classe Enum qui contient les différentes couleurs possibles pour la couleur du block
    */
    public enum BlockColor{
        blue, azur, red, magenta, yellow, green;
        
        /** Méthode retournant alétoirement une couleur de block
         * 
         * @return Une couleur parmi les 6 disponibles
         */
        public static BlockColor getRandomBrickColor() {
            return values()[(int) (Math.random() * values().length)];
        }
        
        public static void printBlockColor(BlockColor color){
            if(color == Block.BlockColor.blue){
                System.out.println("blue");
            }
            else if(color == Block.BlockColor.azur){
                System.out.println("azur");
            }
            else if(color == Block.BlockColor.red){
                System.out.println("red");
            }
            else if(color == Block.BlockColor.magenta){
                System.out.println("magenta");
            }
            else if(color == Block.BlockColor.yellow){
                System.out.println("yellow");
            }
            else if(color == Block.BlockColor.green){
                System.out.println("green");
            }
        }
    }
    
    /** Caractère contenant la lettre du block 
     */
    private char content;
    //private Color color;
    /** BlockColor qui définit la couleur du block     */
    BlockColor color;
    /** Entier qui contient la position x du block    */
    int xPos;
     /** Entier qui contient la position y du block    */
    int yPos;
    
    /** Booléen pour savoir si le block est selected ou non*/
    private boolean selected = false;
    
    /** Contructeur par défaut du block
     * 
     */
    public Block(){
        xPos = 0;
        yPos = 0;
        content = generateContent();
        //color = generateColor();
        color = BlockColor.getRandomBrickColor();
        
    }
    
    /** Contructeur du Block en passant un BlockColor
     * @param c Couleur du Block
     */
    
    public Block(BlockColor c){
        xPos = 0;
        yPos = 0;
        content = generateContent();
        color = c;
    }
    
    /** Contructeur du Block en passant un BlockColor et un char
     * @param c Couleur du Block
     * @param k Char contenu par le Block
     */
    
    public Block(BlockColor c, char k){
        xPos = 0;
        yPos = 0;
        content = k;
        color = c;
    }
    
     /** Contructeur du Block en passant un char
     * @param k Char contenu par le Block
     */
    public Block(char k){
        xPos = 0;
        yPos = 0;
        content = k;
         color = BlockColor.getRandomBrickColor();
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
    
     /** Méthode servant à récuperer un caractère aléatoire dans l'alphabet
     * @return un char contenant une lettre entre a et z
     */
    private char generateContent(){
        Random r = new Random();

        String alphabet = "aaaaaaaaabbccddddeeeeeeeeeeeeffggghhiiiiiiiiijkllllmmnnnnnnooooooooppqrrrrrrssssttttttuuuuvvwwxyyz";

        return alphabet.charAt(r.nextInt(alphabet.length()));
    }
    
     /** Affecte un Block avec les paramètres d'un autre Block passé en paramètre
     * @param b Block servant pour la copie
     */
    void setBlock(Block b){
        content = b.getContent();
        color = b.getColor();
        xPos = b.getXPos();
        yPos = b.getYPos();
    }
     /** Setter de la position en x et en y du Block
     * @param x Position en x
     * @param y Position en y
     */
    
    void setPos(int x, int y){
        xPos = x;
        yPos = y;
    }
    
     /** Setter de la couleur du Block
     * @param c Couleur contenue dans BlockColor
     */
    public void setColor(BlockColor c){
        color = c;
    }
    
    /** Setter du char du Block
     * @param c Couleur contenue dans BlockColor
     */
    public void setChar(char c){
        content = c;
    }
    
    /** Getter de la position en x du Block
     * @return Position en x du block
     */
    
    int getXPos(){
        return xPos;
    }
    /** Getter de la position en y du Block
     * @return Position en y du block
     */
    int getYPos(){
        return yPos;
    }
    
    /** Getter du BlockColor (Couleur) du Block
     * @return Couleur du block
     */
    BlockColor getColor(){
        return color;
    }
    
     /** Getter du char contenu par le Block
     * @return Char du Block
     */
    
    char getContent(){
        return content;
    }
    
    /**
     * Getter du boolean selected
     * @return true si le block est selected
     */
    public boolean isSelected(){
        return this.selected;
    }
    
    /**
     * Setter de l'attribut selected du Block
     * @param b Boolean a utiliser pour changer l'attribut
     */
    public void setSelected(boolean b){
        this.selected = b;
    }
}
