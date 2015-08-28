/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetraworld;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

/** Classe servant à la lecture de fichiers
 *
 * @author niggaLuvWatermelon
 */
public class ReadFile {
    
    /**
     * String contenant le chemin du fichier
     */
    private String path;
    
    /** Setter du chemin du fichier
     * 
     * @param file_path Chemin du fichier
     */
    public ReadFile(String file_path){
        path = file_path;
    }
    
    /** Méthode ouvrant le fichier
     * 
     * @return Le contenu du fichier
     * @throws IOException 
     */
    public String[] openFile() throws IOException{
        FileReader fr = new FileReader(path);
        BufferedReader textReader = new BufferedReader(fr);
        
        int numberOfLines = readLines();
        String[] textData = new String[numberOfLines];
        
        for(int i=0; i<numberOfLines; ++i)
            textData[i] = textReader.readLine();
        
        textReader.close();
        return textData;
    }
    
    /** Méthode servant à compter le nombre de lignes du fichier
     * 
     * @return Le nombre de lignes du fichier
     * @throws IOException 
     */
    private int readLines() throws IOException{
        
        FileReader file_to_read = new FileReader(path);
        BufferedReader bf = new BufferedReader(file_to_read);
        
        String aLine;
        int numberOfLines = 0;
        
        while(( aLine = bf.readLine())!= null )
            numberOfLines++;
        
        bf.close();
        
        return numberOfLines;
    }
}
