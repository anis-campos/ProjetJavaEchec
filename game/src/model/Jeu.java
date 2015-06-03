/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.sun.javafx.animation.TickCalculation;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Antoine
 */
public class Jeu {
    Couleur couleur;
    Map<Coord, AbstractPiece> mapPieces;
    
    private static final int NB_PIECES = 16;
    
    public Jeu(Couleur couleur){
        this.couleur = couleur;
        mapPieces = new HashMap<>(NB_PIECES);
        
        switch(this.couleur){
            case BLANC:
                initPiecesMapBlanc();
                break;
            case NOIR:
                initPiecesMapNoir();
                break;
        }
    }
    
    private void initPiecesMapBlanc(){
        mapPieces.put(new Coord(0, 0), new Tour("B_To1", this.couleur, new Coord(0, 0)));
        mapPieces.put(new Coord(1, 0), new Cavalier(("B_Ca1"), this.couleur, new Coord(1, 0)));
        mapPieces.put(new Coord(2, 0), new Fou(("B_Fo1"), this.couleur, new Coord(2, 0)));
        mapPieces.put(new Coord(3, 0), new Reine(("B_Re1"), this.couleur, new Coord(3, 0)));
        mapPieces.put(new Coord(4, 0), new Roi(("B_Ro1"), this.couleur, new Coord(4, 0)));
        mapPieces.put(new Coord(5, 0), new Fou(("B_Fo2"), this.couleur, new Coord(5, 0)));
        mapPieces.put(new Coord(6, 0), new Cavalier(("B_Ca2"), this.couleur, new Coord(6, 0)));
        mapPieces.put(new Coord(7, 0), new Tour(("B_To2"), this.couleur, new Coord(7, 0)));
        
        for(int i = 0 ; i < 8 ; i++)
            mapPieces.put(new Coord(i, 1), new Pion(("B_Pi"+Integer.toString(i+1)), this.couleur, new Coord(i, 1)));
    }
    
    private void initPiecesMapNoir(){
        mapPieces.put(new Coord(0, 0), new Tour("N_To1", this.couleur, new Coord(0, 0)));
        mapPieces.put(new Coord(1, 0), new Cavalier(("N_Ca1"), this.couleur, new Coord(1, 0)));
        mapPieces.put(new Coord(2, 0), new Fou(("N_Fo1"), this.couleur, new Coord(2, 0)));
        mapPieces.put(new Coord(3, 0), new Reine(("N_Re1"), this.couleur, new Coord(3, 0)));
        mapPieces.put(new Coord(4, 0), new Roi(("N_Ro1"), this.couleur, new Coord(4, 0)));
        mapPieces.put(new Coord(5, 0), new Fou(("N_Fo2"), this.couleur, new Coord(5, 0)));
        mapPieces.put(new Coord(6, 0), new Cavalier(("N_Ca2"), this.couleur, new Coord(6, 0)));
        mapPieces.put(new Coord(7, 0), new Tour(("N_To2"), this.couleur, new Coord(7, 0)));
        
        for(int i = 0 ; i < 8 ; i++)
            mapPieces.put(new Coord(i, 1), new Pion(("N_Pi"+Integer.toString(i+1)), this.couleur, new Coord(i, 1)));
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
        return getPiece(x, y).getCouleur();
    }
    
    public String getPieceName(int x, int y){
        return getPiece(x, y).getName();
    }
    
    public String getPieceType(int x, int y){
        return getPiece(x, y).getClass().getName();
    }
    
    public Couleur getCouleur(){
        return this.couleur;
    }
    
    private AbstractPiece getPiece(int x, int y){
        return mapPieces.get(new Coord(x, y));
    }
    
    public static void main(String args[]){
        Jeu jeuB = new Jeu(Couleur.BLANC);
        System.out.println(jeuB.getPiece(0, 0));
        
        
        Jeu jeuN = new Jeu(Couleur.NOIR);
        System.out.println(jeuN.getPiece(4, 0));
    }
}
