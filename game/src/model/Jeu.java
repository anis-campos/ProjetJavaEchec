/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import tools.ChessPieceFactory;

/**
 *
 * @author Antoine
 */
public class Jeu {
    private Couleur couleur;
    private List<Pieces> listPieces;
    
    private static final int NB_PIECES = 16;
    
    public Jeu(Couleur couleur){
        this.couleur = couleur;
        this.listPieces = ChessPieceFactory.newPieces(couleur);
    }
    
    public boolean Move(int xInit, int yInit, int xFinal, int yFinal){
        Pieces piece = findPiece(xInit, yInit);
        if(piece != null)
            return piece.move(xFinal, yFinal);
        else
            return false;
    }
    
    public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal){
        Pieces piece = findPiece(xInit, yInit);
        if(piece != null)
            return piece.isMoveOk(xFinal, yFinal);
        else
            return false;
    }
    
    public boolean capture(int xCatch, int yCatch){
        Pieces piece = findPiece(xCatch, yCatch);
        if(piece != null)
            return piece.capture();
        else
            return false;
    }
    
    public boolean isPieceToMoveHere(int x, int y){
        return isPieceHere(x, y);
    }
    
    public boolean isPieceToCatchHere(int x, int y){
        return isPieceHere(x, y);
    }
    
    public boolean isPieceHere(int x, int y){
        if(findPiece(x, y) == null)
            return false;
        else
            return true;
    }
    
    @Override
    public String toString(){
        return couleur.name();
    }
    
    public String afficherPiece(int x, int y){
        Pieces piece = findPiece(x, y);
        if(piece == null)
            return "";
        else
            return piece.getName();
    }
    
    public Couleur getPieceCouleur(int x, int y){
        return findPiece(x, y).getCouleur();
    }
    
    public String getPieceName(int x, int y){
        return findPiece(x, y).getName();
    }
    
    public String getPieceType(int x, int y){
        return findPiece(x, y).getClass().getName();
    }
    
    public Couleur getCouleur(){
        return this.couleur;
    }
    
    private Pieces findPiece(int x, int y){
        boolean find = false;
        int i = 0;
        
        while(i < listPieces.size() && !find){
            if(listPieces.get(i).getX() == x && listPieces.get(i).getY() == y)
                find = true;
            else
                i++;
        }
        
        if(i < listPieces.size())
            return listPieces.get(i);
        else
            return null;
    }
    
    public static void main(String args[]){
        Jeu jeuB = new Jeu(Couleur.BLANC);
        System.out.println(jeuB.findPiece(0, 7));
        
        
        Jeu jeuN = new Jeu(Couleur.NOIR);
        System.out.println(jeuN.findPiece(4, 0));
    }
}
