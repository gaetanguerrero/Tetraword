/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetraworld;

import java.util.ArrayList;

/**
 * Classe de l'interface de jeu
 */
public class GameInterface extends Interface{

       private Label input_pseudo;
       private Label input_score;
       private Label input_label;
       private Label next_label;
       private Label input_mode;
       private final ImagePanel led_panel;
       private final ImagePanel input_panel;
       private ImagePanel next_panel;
       private ImagePanel game_panel;
       private ImagePanel input_mode_panel;
       private TransparentPanel mid_panel;
       private TransparentPanel right_panel;
       final private int xOrigin = 43;
       final private int yOrigin = 57;
       final private int xNextOrigin = 90;
       final private int yNextOrigin = 122;
    
    /**
     * Constructeur de l'interface de jeu
     * @param height Hauteur de l'interface
     * @param width Largeur de l'interface
     * @param player Joueur jouant au jeu
     */
    public GameInterface(int height, int width, Player player) {
        super(height, width, "background.png");
        
        /* Panel de jeu */      
        mid_panel = new TransparentPanel();
        mid_panel.setLayout(null);
        
        game_panel = new ImagePanel("game_plateform.png");
        game_panel.setBounds(0,5,388,687);
        
        
                
        mid_panel.add(game_panel);
        
        
        /* Panel gauche */
        TransparentPanel left_panel = new TransparentPanel();
        left_panel.setLayout(null);
        
            /* Portrait */
            ImagePanel portrait_panel = new ImagePanel("portrait.png");
            portrait_panel.setBounds(10,10,386,211);

            this.input_pseudo = new Label(player.getPseudo(), Label.LabelTextType.Simple, 31.f);
            this.input_pseudo.setBounds(41,153,86,46);

            this.input_score = new Label(Integer.toString(player.getScore()), Label.LabelTextType.Double, 31.f, 2, 2);
            this.input_score.setBounds(245,65,100,46);
            
            /* Worddle - Anagram mode */
            this.input_panel = new ImagePanel("input_disable.png");
            this.input_panel.setBounds(10,300,394,126);
            
            this.input_mode_panel = new ImagePanel("large_button_disable.png");
            this.input_mode_panel.setBounds(50,450,316,114);
    
            this.input_mode = new Label(" ", Label.LabelTextType.Double, 60.f, 3, 3);
            this.input_mode.setBounds(100,450,316,114);
            
            this.led_panel = new ImagePanel("led_disable.png");
            this.led_panel.setBounds(330,340,36,34);
            
            this.input_label = new Label("", Label.LabelTextType.Input, 35.f);
            this.input_label.setBounds(75,325,240,46);
        
        left_panel.add(input_pseudo);
        left_panel.add(input_score);
        left_panel.add(portrait_panel);
        left_panel.add(led_panel);
        left_panel.add(input_label);
        left_panel.add(input_panel);
        left_panel.add(input_mode);
        left_panel.add(input_mode_panel);
        
        
        
        
        /* Panel right */
       right_panel = new TransparentPanel();      
       right_panel.setLayout(null);
       //right_panel.setBounds();
       
       next_panel = new ImagePanel("next_background.png");
       next_panel.setBounds(0,20,217,267);
       
       
       next_label = new Label("Next", Label.LabelTextType.Double, 31.f);
       next_label.setBounds(70,15,86,45);
       
       right_panel.add(next_label);
       right_panel.add(next_panel);
       
        
 
      
        /* Ajout des composants */
        this.background.add(left_panel);
        this.background.add(mid_panel);
        this.background.add(right_panel);
        
       
        this.setVisible(true);
    }
    
    /**
     * Méthode méttant à jour l'interface de jeu
     * @param grid Grille de jeu
     * @param player Joueur actuel
     */
    @Override
    public void updateGameFrame(FieldMat grid, Player player){
        /* Réinitialisation des dessins sur les panneaux mid et right */
        mid_panel.removeAll();
        right_panel.removeAll();
        
        if(grid.isAnagram()){
            input_mode.setTitle("Anagram");
            input_panel.setImage("input.png");
            input_mode_panel.setImage("large_button.png");
             if(grid.isAWord())
                led_panel.setImage("green_led.png");
            else
                 led_panel.setImage("red_led.png");
        }
        else if(grid.isWorddle()){
            input_mode.setTitle("Worddle");
            input_panel.setImage("input.png");
            input_mode_panel.setImage("large_button.png");
            if(grid.isAWord())
                led_panel.setImage("green_led.png");
            else
                 led_panel.setImage("red_led.png");
        }
        else{
            input_mode.setTitle(" ");
            input_panel.setImage("input_disable.png");
            input_mode_panel.setImage("large_button_disable.png"); 
            led_panel.setImage("led_disable.png");
        }
        
        input_label.setTitle(grid.charsSelected());
        input_score.setTitle(Integer.toString(player.getScore()));
                
        ArrayList<Block> blocks = grid.toDraw();
        ArrayList<Block> next_shape = grid.getBuffer();
        
        if(grid.getReverseState() == false){    
            /* Bonus */
            if(grid.getBonus().getDrawState() && grid.getBonus().getEnable() == false){
                int x_bonus = xOrigin + 29 * grid.getBonus().getX();
                int y_bonus = yOrigin + 29 * grid.getBonus().getY();
                ImageBrick image_bonus = new ImageBrick(' ', x_bonus, y_bonus);
                image_bonus.setBounds(x_bonus, y_bonus, 29, 29);
                mid_panel.add(image_bonus);
            }

            /* Blocks de la grid */
            for(int i=0; i < blocks.size() ; i++){
               int x = xOrigin + 29 * blocks.get(i).getXPos();
               int y = yOrigin + 29 * blocks.get(i).getYPos();
               ImageBrick tmp = new ImageBrick(blocks.get(i).getColor(),blocks.get(i).getContent(),x,y, blocks.get(i).isSelected());
               tmp.setBounds(x,y,29,29);
               mid_panel.add(tmp);
            }
        }
        else{
           /* Bonus */
            if(grid.getBonus().getDrawState() && grid.getBonus().getEnable() == false){
                int x_bonus = xOrigin + 29*9 - 29 * grid.getBonus().getX();
                int y_bonus = yOrigin + 29*19 - 29 * grid.getBonus().getY();
                ImageBrick image_bonus = new ImageBrick('B', x_bonus, y_bonus);
                image_bonus.setBounds(x_bonus, y_bonus, 29, 29);
                mid_panel.add(image_bonus);
            }

            /* Blocks de la grid */
            for(int i=0; i < blocks.size() ; i++){
               int x = xOrigin + 29*9 - 29 * blocks.get(i).getXPos();
               int y = yOrigin + 29*19 - 29 * blocks.get(i).getYPos();
               ImageBrick tmp = new ImageBrick(blocks.get(i).getColor(),blocks.get(i).getContent(),x,y, blocks.get(i).isSelected());
               tmp.setBounds(x,y,29,29);
               mid_panel.add(tmp);
            } 
        }
        
        /* Blocks de la prochaine pièce */
        for(int i=0; i < next_shape.size() ; i++){
           int x = xNextOrigin + 29 * next_shape.get(i).getXPos();
           int y = yNextOrigin + 29 * next_shape.get(i).getYPos();
           ImageBrick tmp = new ImageBrick(next_shape.get(i).getColor(),next_shape.get(i).getContent(),x,y, false);
           tmp.setBounds(x,y,29,29);
           right_panel.add(tmp);
        }
        right_panel.add(next_label);
        right_panel.add(next_panel);
              
        mid_panel.add(game_panel);
        repaint();
    }
   
    
    
    
}
