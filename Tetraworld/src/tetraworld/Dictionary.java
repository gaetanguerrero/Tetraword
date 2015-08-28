/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetraworld;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**Classe de la gestion du Dictionnaire
 *
 * @author niggaLuvWatermelon
 */
public class Dictionary extends JFrame
{
    /**
     * Arbre de recherche du dictionnaire
     */
    private DictionnaryTree tree;
    
    /** Constructeur de Dictionary
     * 
     * @throws IOException 
     */
    
    public Dictionary() throws IOException
    {
        tree = new DictionnaryTree('\0');
        List<String> words = new ArrayList<>();

        File file = new File("ressources/dico/complete.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line = null;
        while( (line = br.readLine())!= null ){
                // \\s+ means any number of whitespaces between tokens
            String [] tokens = line.split("\\s+");
            for(int i=0; i<tokens.length; ++i)
                words.add(tokens[i]);
        }
        
        
        for(int i = 0; i<words.size(); ++i){
            tree.addWord(words.get(i));
        }
    }
    
    /** Getter de l'arbre du Dictionary
     * 
     * @return L'arbre de recherche
     */
    public DictionnaryTree getDic(){
        return tree;
    }
    
    /**Teste dans l'arbre si un mot est présent
     * 
     * @param str String à tester
     * @return true si le mot a été trouvé dans l'arbre de recherche
     */
    public boolean isAWord(String str){
        return tree.isAWord(str);
    }
     
    public static void main(String[] args)
    {
        Dictionary d;
        try {
            d = new Dictionary();
        } catch (IOException e) {
             System.out.println( e.getMessage() );
             d = null;
        }
        System.out.println(d.isAWord("nevertheless"));
    }
}
