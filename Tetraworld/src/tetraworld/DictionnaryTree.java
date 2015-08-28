/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetraworld;

import java.util.ArrayList;
import java.util.List;

/** Classe de l'Arbe pour la recherche de mots dans le dictionnaire
 */
public class DictionnaryTree {
    
    /** Noeud Racine de l'arbe*/
    private final Node root;

    /** Contructeur de DictionnaryTree
     * 
     * @param rootData char servant de racine pour l'arbre
     */
    
    public DictionnaryTree(char rootData) {
        root = new Node(rootData);
    }
    
    /** Méthode servant à tester l'existence d'un mot dans le dictionnaire
     * 
     * @param str Mot à tester
     * @return true si le mot passé en paramètre est dans le dictionnaire
     */
    public boolean isAWord(String str){
        Node temp = root;
        Node temp2 = new Node();
        
        int tempi;
        int currentSize = 1;
        
        for(int i=0; i<str.length();++i){
            
            temp2.setData(str.charAt(i));
            tempi = temp.isInChild( temp2 );
            if(-1 != tempi){
                temp = temp.getChildren(tempi);
            }
            else
                return false;
        }
        return temp.isEnd();
    }
    
    public boolean addWord(String str){
        if(isAWord(str))
            return false;
        
        Node temp = new Node();
        
        Node test = root;
        
        int tempInt;
        
        for(int i=0; i<str.length(); ++i){
            temp.setData(str.charAt(i));
            tempInt = test.isInChild(temp);
            
            if(tempInt == -1){
                String tempString = str.substring(i);
                test.addChildren(str.substring(i));
                return true;
            }
            else{
                test = test.getChildren(tempInt);
            }
        }
        return true;
    }
    
    public void printOut(){
        root.printOut();
    }

    public static class Node {
        private char data;
        //private Node<T> parent;
        private List<Node> children;
        boolean end;
        
        public Node(){
            data = '\0';
            children = new ArrayList<>();
            end = false;
        }
        
        public Node(char c){
            data = c;
            children = new ArrayList<>();
            end = false;
        }
        
        public Node(char c, boolean b){
            data = c;
            children = new ArrayList<>();
            end = b;
        }
        
        public void setData(char c){
            data = c;
        }
        
        public void addChildren(Node n){
            children.add(n);
        }
        
        public void addChildren(String str){
            Node temp = this;
            
            for(int i=0; i<str.length(); ++i){
                if(i == str.length()-1)
                    temp.addChild(str.charAt(i), true);
                else temp.addChild(str.charAt(i));
                temp = temp.getChildren(0);
            }
        }
        
        public void addChild(char c){
            Node n = new Node(c);
            children.add(0,n);
        }
        
        public void addChild(char c, boolean b){
            Node n = new Node(c, b);
            children.add(0,n);
        }
        
        public List<Node> getChildren(){
            return children;
        }
        
        public Node getChildren(int i){
            return children.get(i);
        }
        
        public char getData(){
            return data;
        }
        
        public int isInChild(Node a){
            for(int i=0; i<children.size() ;++i)
                if(children.get(i).compare(a) )
                    return i;
            return -1;
        }
        
        public boolean hasChild(){
            if(children.isEmpty())
                return false;
            return false;
        }
        
        public boolean isEnd(){
            return end;
        }

        private boolean compare(Node a){
            return this.getData() == a.getData();
        }
        
        public void printOut(){
            System.out.println(getData());
            for(int i=0; i<children.size(); ++i){
                children.get(i).printOut();
            }
        }
    }
    
    /*public static void main(String[] args){
        DictionnaryTree t;
        t = new DictionnaryTree('\0');
        String str = "chair";
        String str2 = "choose";
        String str3 = "dick";
        t.addWord(str2);
        t.addWord(str);
        t.addWord(str3);
        System.out.println(t.isAWord("chair"));
        System.out.println(t.isAWord("choose"));
        System.out.println(t.isAWord("dick"));
        
        t.printOut();
    }*/
}
