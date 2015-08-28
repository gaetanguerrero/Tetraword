/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetraworld;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe intervenant lors des actions en rapport en jeu
 *
 */

public class FieldMat {
    
    public Dictionary dico;
    
    private final int xSize;
    private final int ySize;
    
    private Block[][] content;
    private Brick buffer;
    private Brick falling;
    private Bonus bonus;
    
    private ArrayList<Block> selectedBlocks;
    
    private boolean anagram;
    private boolean worddle;
    
    private boolean gameOver;
    
    private int scoreToBeAdded;
    private boolean reverseMat = false;
    private int timerReverse;
    
    private Sound s;
    private Sound p;
    private Sound b;
    private Sound n;
    
    /**
     * Constructeur de FieldMat
     * @param x
     * @param y 
     */
    public FieldMat(int x, int y){
        xSize = x;
        ySize = y;
        bonus = new Bonus();
        
        s = new Sound("ressources/sounds/ok.wav",false);
        p = new Sound("ressources/sounds/block.wav",false);
        b = new Sound("ressources/sounds/bonus.wav",false);
        n = new Sound("ressources/sounds/negative.wav",false);
        
        scoreToBeAdded=0;
        
        try {
            dico = new Dictionary();
        } catch (IOException ex) {
            Logger.getLogger(FieldMat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        content = new Block[xSize][ySize];
        selectedBlocks = new ArrayList<>();
        for(int i =0; i<xSize; ++i)
            for(int j =0; j<ySize; ++j)
                content[i][j] = new Block('\0');

        Random r = new Random();
        String alphabet = "bcdefg";
        buffer = new Brick(alphabet.charAt(r.nextInt(alphabet.length())),false);
        
        newBrick();
    }
    
    /**
     * Méthode retournant un block s'il est clické dans la grille de jeu
     * @param x Coordonnées de la souris en X
     * @param y Coordonnées de la souris en Y
     * @return Le Block clické
     */
    public Block isClickedBlock(int x, int y){
        for(int i=0; i<xSize; ++i)
            for(int j=0; j<ySize; ++j){
                int xPos = 470 + 29 * i;
                int yPos = 80 + 29 * j;
                if((x >= xPos && x <= xPos + 29) && (y >= yPos && y <= yPos + 29))
                    if(!content[i][j].isSelected()){
                        
                        ///////////////////////////////////////////////////////////////
                        if(anagram){  
                            for(int k=0; k<completeLines().size(); ++k){
                                int YTemp = 80 + 29 * completeLines().get(k);
                                if(y <= YTemp+29 && y >= YTemp){
                                    content[i][j].setSelected(true);
                                    Block temp = new Block(content[i][j].getContent());
                                    temp.setPos( i, j);
                                    return temp;
                                }
                            }
                        }


                        ///////////////////////////////////////////////////////////////
                        if(worddle){
                            //System.out.println("CACA W");
                            if(selectedBlocks.isEmpty()){
                                content[i][j].setSelected(true);
                                Block temp = new Block(content[i][j].getContent());
                                temp.setPos( i, j);
                                return temp;
                            }
                            else{
                                int XTemp = 470 + 29 * selectedBlocks.get(0).getXPos();
                                int YTemp = 80 + 29 * selectedBlocks.get(0).getYPos();
                                //System.out.println("size = " + selectedBlocks.size());
                                //System.out.println("XTemp = " + XTemp);
                                //System.out.println("YTemp = " + YTemp);
                                if(x <= XTemp + 48)
                                    if(x >= XTemp -29)
                                        if(y <= YTemp + 48)
                                            if(y >= YTemp - 29){
                                                content[i][j].setSelected(true);
                                                Block temp = new Block(content[i][j].getContent());
                                                temp.setPos( i, j);
                                                //System.out.println(i + " " + j);
                                                //System.out.println(worddle);
                                                return temp;
                                            }

                            }
                        }
                    }
            }
        
        return new Block('\0');
    }
    
    /**
     * Méthode permettant de remettre les block selectionnés à leur état initial
     */
     public void resetSelected(){
        for(int j =0; j<ySize; ++j){   
            for(int i =0; i<xSize; ++i){
                content[i][j].setSelected(false);
            }
        }
    }
    
    /**
     * Ajoute le block passé en paramètre à la liste des blocks selectionnés
     * @param b Block à ajouter à la liste
     */
    public void selectedBlocks(Block b){
        if(b.getContent() != '\0')
            selectedBlocks.add(0,b);
    }
    
    public String charsSelected(){
        String result ="";
        
        StringBuilder sb = new StringBuilder();

        for(int i = selectedBlocks.size()-1; i>=0; --i)
            sb.append(selectedBlocks.get(i).getContent());
        
        result = sb.toString();
        //System.out.println(result);
        return result;
    }
    
    /**
     * Méthode permttant de détruire les blocks qui ont été selectionnés
     */
    public int deleteSelectedBlocks(){
        int temp = selectedBlocks.size();
        
        for(int i = selectedBlocks.size()-1; i>=0; --i){
            
            content[selectedBlocks.get(i).getXPos()][selectedBlocks.get(i).getYPos()].setChar('\0');
            for(int j = selectedBlocks.get(i).getYPos(); j>0;--j){
                content[selectedBlocks.get(i).getXPos()][j] = content[selectedBlocks.get(i).getXPos()][j-1];
            }
            content[selectedBlocks.get(i).getXPos()][0] = new Block('\0');
        }
        selectedBlocks = new ArrayList<>();
        
        return temp;
        
    }
    
    private int scoreCalcWorddle(int t){
        return 15*t;
    }
    
    private int scoreCalcAnagram(int t){
        return 20*t;
    }
    
    public boolean testDelete(){
        //System.out.println(dico.isAWord(charsSelected()));
        if(dico.isAWord(charsSelected())){
            if(worddle){
                int temp = deleteSelectedBlocks();
                scoreToBeAdded+=scoreCalcWorddle(temp);
                selectedBlocks = new ArrayList<>();
                worddleOff();
                anagramOff();
                s.play();
                return true;
            }
            if(anagram){
                int temp = charsSelected().length();
                eraseLine(selectedBlocks.get(0).getYPos());
                scoreToBeAdded+=scoreCalcAnagram(temp);
                selectedBlocks = new ArrayList<>();
                worddleOff();
                anagramOff();
                s.play();
                return true;
            }
        }
        
        n.play();
        selectedBlocks = new ArrayList<>();
        worddleOff();
        anagramOff();
        //System.out.println(worddle);
        //System.out.println(anagram);
        return false;
            
    }
    
    public boolean CollideBonus(){
        boolean collide = false;
        Block[] tmp = falling.getBlocks();
        
        for(int i=0; i<falling.getBlocks().length; ++i){
            
            if(bonus.getX() == tmp[i].getXPos() + falling.getXPos() && bonus.getY() == tmp[i].getYPos() + falling.getYPos()){
               collide = true;
            }
            
           
        }       
        return collide;
    }
    
    
    public void update(){
        //System.out.println("Timer bonus : " + bonus.getTimer());
        //System.out.println(falling.getXPos());
        //System.out.println(falling.getYPos()); 
        this.timerReverse --;
        if(this.timerReverse <= 0)
            this.reverseMat = false;
        
        if(bonus.getTimer() <= 0 && bonus.getDrawState() == false){
            //System.out.println("Pop du bonus sur le field");
            bonus.setDrawState(true);
        }
        
        if(bonus.getTimer() > 0){
            bonus.setTimer(bonus.getTimer()-1);
        }
        
        boolean collide = false;
        
        if(bonus.getDrawState()){ // On peut collider avec le bonus seulement s'il est sur le terrain
            collide = CollideBonus();
        }

        if(collide && bonus.getEnable() == false){
            //System.out.println("Bonus récupéré");
            bonus.setEnable(true);
            b.play();
            if(bonus.getTypeBonus() == Bonus.TypeBonus.Score){
                this.scoreToBeAdded = 300;
            }
            else if(bonus.getTypeBonus() == Bonus.TypeBonus.Reverse){
                reverseMat = true;
                timerReverse = 40;
               
            }
            
            bonus = new Bonus();
            
            if(reverseMat) // Si on est en reverse on ne peut pas réobtenir ce même bonus
                bonus.setTypeBonus(Bonus.TypeBonus.Score);
           
        }
            
        
        if(!makeItFall()){
           p.play();
           blockToBrick();    
        }
    }
    
    public int completeLine(){
        
        for(int j =0; j<ySize; ++j)
            for(int i =0; i<xSize; ++i){
                if(content[i][j].getContent() == '\0')
                    break;
                if(i == xSize -1)
                    return j;
            }
        return -1;
    }
    
    private ArrayList<Integer> completeLines(){
        
        ArrayList<Integer> result = new ArrayList<>();
        
        for(int j =0; j<ySize; ++j)
            for(int i =0; i<xSize; ++i){
                if(content[i][j].getContent() == '\0')
                    break;
                if(i == xSize -1)
                    result.add(j);
            }
        return result;
    }
    
    /**
     * Méthode permettant détruire une ligne
     * @param x
     * @return 
     */
    boolean eraseLine(int x){
        for(int i=0; i<xSize; ++i)
            content[i][x].setChar('\0');

        for(int j = selectedBlocks.get(0).getYPos(); j>0;--j)
            for(int i=0; i<xSize; ++i)
                content[i][j] = content[i][j-1];
        
        return true;
    }
    
    /**
     * Méthode permttant de faire tomber les FieldMat
     * @return false si la FieldMat ne peut plus tomber dans la grille
     */
    private boolean makeItFall(){
        
        for(int i=0; i<falling.getBlocks().length; ++i){
            //System.out.println(falling.getYPos()+tempBlocks[i].getYPos()+1);
            if((falling.getYPos()+falling.getBlocks()[i].getYPos())+1 >= ySize){
                //System.out.println("HIT TEH BOTTOM");
                
                return false;
            }
            
            for(int j=xSize-1; j>=0; --j)
                for(int k=ySize-1; k>=0; --k){
                    if( content[j][k].getContent() != '\0'){
                        //System.out.println("BLOCKS FOUND");
                        if( falling.getXPos()+falling.getBlocks()[i].getXPos() ==  j)
                            if(falling.getYPos()+falling.getBlocks()[i].getYPos()+1 == k){
                                //System.out.println("HIT TEH BLOCKS");
                                //System.out.println(falling.getXPos()+falling.getBlocks()[i].getXPos()+1);
                                //System.out.println(falling.getYPos()+falling.getBlocks()[i].getYPos()+1);
                                return false;
                            }
                    }
                }
        }     
                        
        falling.setYPos(falling.getYPos() + 1);
        return true;
    }
    
    private boolean matrixFall(){
        for(int i=xSize-1; i>=0; --i)
            for(int j=ySize-2; j>=0; --j)
                if(content[i][j+1].getContent() == '\0'){
                    content[i][j+1]=content[i][j];
                    content[i][j].setChar('\0');
                }
        
        return true;
    }
    
    /**
     * Méthode permettant de déplacer la FieldMat vers la gauche
     * @return false si la FieldMat ne peut plus se déplacer vers la gauche
     */
    public boolean moveLeft(){
        for(int i=0; i<falling.getBlocks().length; ++i){
            if((falling.getXPos()+falling.getBlocks()[i].getXPos())-1 < 0){
                return false;
            }
            for(int j=xSize-1; j>=0; --j)
                for(int k=ySize-1; k>=0; --k){
                    if( content[j][k].getContent() != '\0'){
                        if( falling.getXPos()+falling.getBlocks()[i].getXPos()-1 ==  j)
                            if(falling.getYPos()+falling.getBlocks()[i].getYPos() == k){
                                //System.out.println(falling.getXPos()+falling.getBlocks()[i].getXPos()+1);
                                //System.out.println(falling.getYPos()+falling.getBlocks()[i].getYPos()+1);
                                return false;
                            }
                    }
                }
        }
        falling.setXPos(falling.getXPos()-1);
        return true;
    }
    
    public void playS(){
        s.play();
    }
    
    /**
     * Méthode permettant de déplacer la FieldMat vers la droite
     * @return false si la FieldMat ne peut plus se déplacer vers la droite
     */
    public boolean moveRight(){
        for(int i=0; i<falling.getBlocks().length; ++i){
            if((falling.getXPos()+falling.getBlocks()[i].getXPos())+1 > xSize-1){
                return false;
            }
            for(int j=xSize-1; j>=0; --j)
                for(int k=ySize-1; k>=0; --k){
                    if( content[j][k].getContent() != '\0'){
                        if( falling.getXPos()+falling.getBlocks()[i].getXPos()+1 ==  j)
                            if(falling.getYPos()+falling.getBlocks()[i].getYPos() == k){
                                //System.out.println(falling.getXPos()+falling.getBlocks()[i].getXPos()+1);
                                //System.out.println(falling.getYPos()+falling.getBlocks()[i].getYPos()+1);
                                return false;
                            }
                    }
                }
        }
        falling.setXPos(falling.getXPos()+1);
        return true;
    }
    
    /**
     * Méthode pour tourner la FieldMat sur elle-même
     */
    public boolean rotate(){
       
        falling.rotate();
        
        return true;
    }
    
    /**
     * Méthode créant une nouvelle Brick dans la grille de Jeu
     * @return false s'il est impossible de créer une nouvelle Brick, true sinon
     */
    private boolean newBrick(){
        
        Random r = new Random();
        String alphabet = "bcdefg";
        
        falling = buffer;
        falling.switchPlayable();
        
        buffer = new Brick(alphabet.charAt(r.nextInt(alphabet.length())),false);
        falling.setXPos((xSize)/2);
        
        for(int i=0; i<falling.getBlocks().length; ++i){
            for(int j=0; j<xSize; ++j)
                for(int k=0; k<ySize; ++k){
                    if( content[j][k].getContent() != '\0'){
                        //System.out.println("BLOCKS FOUND");
                        if( falling.getXPos()+falling.getBlocks()[i].getXPos() ==  j)
                            if(falling.getYPos()+falling.getBlocks()[i].getYPos() == k){
                                //System.out.println("HIT TEH BLOCKS");
                                //System.out.println(falling.getXPos()+falling.getBlocks()[i].getXPos()+1);
                                //System.out.println(falling.getYPos()+falling.getBlocks()[i].getYPos()+1);
                                System.out.println("GAMEOVER");
                                return false;
                            }
                    }
                }
        }
        
        return true;

    }
    
    private void blockToBrick(){
        for(int i=0; i<4; i++){
            //System.out.println(falling.getBlocks()[i].getXPos());
            //System.out.println(falling.getBlocks()[i].getYPos());
            addBlock(falling.getBlocks()[i],falling.getXPos(),falling.getYPos());
        }
        if(!newBrick()){
            gameOver();
        }
    }
    
    /**
     * Méthode settant le gameOver à true
     */
    private void gameOver(){
        gameOver = true;
    }
    
    private void addBlock(Block b,int x, int y){
        //System.out.println(b.getContent());
        //System.out.println(x+b.getXPos());
        //System.out.println(y+b.getYPos());
        if((x+b.getXPos())<xSize && (x+b.getXPos())>=0 && (y+b.getYPos())<ySize && (y+b.getYPos())>=0)
            content[b.getXPos()+x][b.getYPos()+y]=b;
    }
    
    public ArrayList<Block> toDraw(){
        ArrayList<Block> result = new ArrayList();
        
        
        for(int i=0; i<falling.getBlocks().length; ++i){
            Block temp = new Block();
            temp.setPos(falling.getXPos()+falling.getBlocks()[i].getXPos(), falling.getYPos()+falling.getBlocks()[i].getYPos());
            temp.setColor(falling.getBlocks()[i].getColor());
            temp.setChar(falling.getBlocks()[i].getContent());
            result.add(0,temp);
        }
        
        for(int i = 0; i<xSize; ++i )
            for(int j = 0; j<ySize; ++j )
                if(content[i][j].getContent() != '\0'){
                    Block temp = new Block();
                    temp.setPos(i,j);
                    temp.setColor(content[i][j].getColor());
                    temp.setChar(content[i][j].getContent());
                    temp.setSelected(content[i][j].isSelected());
                    result.add(0,temp);
                }
        /*System.out.println("//////////////////////////////////////");
        for(int i= 0; i<result.size(); ++i)
            System.out.println(result.get(i).getContent());*/
        
        return result;
    }
    
    public ArrayList<Block> getBuffer(){
        ArrayList<Block> result = new ArrayList();
        
        for(int i=0; i<buffer.getBlocks().length; ++i){
            Block temp = new Block();
            temp.setPos(buffer.getBlocks()[i].getXPos(), buffer.getBlocks()[i].getYPos());
            temp.setColor(buffer.getBlocks()[i].getColor());
            temp.setChar(buffer.getBlocks()[i].getContent());
            temp.setSelected(false);
            result.add(0,temp);
        }
        
        return result;
    }
    
    public boolean isAnagram(){
        return anagram;
    }
    
    public void anagramOn(){
        anagram = true;
    }
    
    public void anagramOff(){
        anagram = false;
    }
    
    public boolean isWorddle(){
        return worddle;
    }
    
    public void worddleOn(){
        worddle = true;
    }
    
    public void worddleOff(){
        worddle = false;
    }
    
    public Block getBlockAt(int x, int y){
        if(x<xSize && y<ySize)
            return content[x][y];
        else return null;
    }
    
    public boolean isGameOver(){
        return gameOver;
    }
    
    public boolean isAWord(){
        if(dico.isAWord(charsSelected()))
            return true;
        else
            return false;
    }
    
    public int scoreToBeAdded(){
        return scoreToBeAdded;
    }
    
    public void resetScoreToBeAdded(){
        scoreToBeAdded = 0;
    }
    
    public Bonus getBonus(){
        return this.bonus;
    }
    
    public boolean getReverseState(){
        return this.reverseMat;
    }
    
    public void setReverseState(boolean reverse){
        this.reverseMat = reverse;
    }
    
    public void Print(){
        System.out.println("/////// BLOCK ////////");
        for(int i=0;i<xSize; ++i)
            for(int j=0; j<ySize; ++j)
                if(content[i][j].getContent() != '\0'){
                    System.out.println(content[i][j].getXPos());
                    System.out.println(content[i][j].getYPos());
                    //System.out.println(i);
                    //System.out.println(j);
                }
                    
        
        System.out.println("/////// BRICK ////////");
        System.out.println(falling.getType());
        System.out.println(falling.getXPos());
        System.out.println(falling.getYPos());
        
    }
    
    public static void main(String[] args)
    {
        FieldMat f = new FieldMat(10,20);
        for(int i=0;i<40;++i){
            //f.Print();
            f.update();
            f.toDraw();
        }
        System.out.println("//////////////////////////////////////");
        f.Print();
    }

}
