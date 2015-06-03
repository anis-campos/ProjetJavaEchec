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
public class Jeu {
    Couleur couleur;
    
    public Jeu(Couleur couleur){
        this.couleur = couleur;
    }
    
    public boolean Move(int xInit, int yInit, int xFinal, int yFinal){
        return true;
    }
    
    public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal){
        return true;
    }
    
    public boolean capture(int xCatch, int yCatch){
        return true;
    }
    
    public boolean isPieceToMoveHere(int x, int y){
        return true;
    }
    
    public boolean isPieceToCatchHere(int x, int y){
        return true;
    }
    
    public boolean isPieceHere(int x, int y){
        return true;
    }
    
    public String toString(){
        return couleur.name();
    }
    
    public Couleur getPieceCouleur(int x, int y){
        return null;
    }
    
    public String getPieceName(int x, int y){
        return null;
    }
    
    public String getPieceType(int x, int y){
        return null;
    }
    
    public String getCouleur(){
        return null;
    }
    
    private AbstractPiece getPiece(int x, int y){
        return null;
    }
    
    public static void main(String args[]){
        
    }
}
