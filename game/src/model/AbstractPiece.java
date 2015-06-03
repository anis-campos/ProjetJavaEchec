/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Antoine
 */
public abstract class AbstractPiece implements Pieces {
    private String name;
    private Couleur couleur;
    private Coord coord;
    
    private boolean capture;
    
    public AbstractPiece(String name, Couleur couleur, Coord coord){
        this.name = name;
        this.couleur = couleur;
        this.coord = coord;
        this.capture = false;
    }
    
    @Override
    public String toString(){
        return "["+name+" "+couleur.name()+"]"+" "+coord.toString();
    }
    
    @Override
    public boolean capture(){
        capture = true;
        return capture;
    }
    
    @Override
    public Couleur getCouleur(){
        return this.couleur;
    }
    
    @Override
    public String getName(){
        return this.name;
    }
    
    @Override
    public int getX(){
        return this.coord.x;
    }
    
    @Override
    public int getY(){
        return this.coord.y;
    }
    
     public static void main (String args[]){
       List<AbstractPiece> list = new ArrayList<>();
       int x=0, y=0;
       list.add(new Tour("B_To1", Couleur.BLANC, new Coord(x++, y)));
       list.add(new Cavalier("B_Ca1", Couleur.BLANC, new Coord(x++, y)));
       list.add(new Fou("B_Fo1", Couleur.BLANC, new Coord(x++, y)));
       list.add(new Reine("B_Re1", Couleur.BLANC, new Coord(x++, y)));
       list.add(new Roi("B_Ro1", Couleur.BLANC, new Coord(x++, y)));
       list.add(new Roi("B_Ro1", Couleur.BLANC, new Coord(x++, y)));
       
       for(AbstractPiece piece : list){
           System.out.println(piece);
       }
       
    }
}
