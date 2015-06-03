/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Antoine
 */
public abstract class AbstractPiece implements Pieces {
    String name;
    Couleur couleur;
    Coord coord;
    
    public AbstractPiece(String name, Couleur couleur, Coord coord){
        this.name = name;
        this.couleur = couleur;
        this.coord = coord;
    }
    
    @Override
    public String toString(){
        return "["+name+" "+couleur.name()+"]"+" "+coord.toString();
    }
    
    @Override
    public boolean capture(){
        return false;
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
}
