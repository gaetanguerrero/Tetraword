/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
COMMENTAIRE
*/

package tetraworld;

/** Classe représentant un joueur
 *
 * @author Romain SANFILIPPO
 */
public class Player {
    
    /**
     * Numéro d'identification du joueur
     */
    private int id;
    /**
     * Pseudonyme du joueur
     */
    private String pseudo;
    
     /**
     * Score du joueur
     */
    private int score;
    
     /**
     * Effet spécial du joueur
     */
    
    private Modifier modifier;
    
    static int lastId = 0;
    
    /** Constructeur d'un joueur en passant son pseudo
     * 
     * @param pseudo Pseudo du joueur
     */
    public Player(String pseudo){
        this.pseudo = pseudo;
        this.id = lastId++;
        this.score = 0;
    }
    
    /** Méthode pour augmenter ou diminuer le score du joueur
     * 
     * @param val Points à ajouter au score
     */
    public void updateScore(int val){
        this.score += val;
    }
    
    /**Méthode pour ajouter un effet (Modifier) au joueur
     * 
     * @param mod Effet passé 
     */
    public void addModifier(Modifier mod){
        this.modifier = mod;
    }
    
    /** Getter du Modifier du joueur
     * 
     * @return Modifier actuel du joueur
     */
    public Modifier getModifier(){
        return this.modifier;
    }
    
    /** Getter du Pseudo du joueur
     * 
     * @return Pseudo du joueur
     */
    
    public String getPseudo(){
        return this.pseudo;
    }
    
    /** Getter de l'id du joueur
     * 
     * @return l'id du joueur
     */
    public int getId(){
        return this.id;
    }
     /** Getter du score du joueur
     * 
     * @return le score actuel du joueur
     */
    public int getScore(){
        return this.score;
    }
   
}
