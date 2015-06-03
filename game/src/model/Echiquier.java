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
public class Echiquier {
    
    private Jeu jeuBlanc;
    private Jeu jeuNoir;
    private Couleur jeuCourant;
    
    private String message;

    public Echiquier() {
        jeuBlanc = new Jeu(Couleur.BLANC);
        jeuNoir = new Jeu(Couleur.NOIR);
        jeuCourant = Couleur.values()[(int) Math.round(Math.random())];
        
        switch(jeuCourant){
            case BLANC:
                message = "L'équipe blanche a gagné le tirage au sort !\n";
                break;
            case NOIR:
                message = "L'équipe noire a gagné le tirage au sort !\n";
                break;
        }
    }
    
    public String getMessage() {
        return message;
    }
    
    public boolean isEchecEtMat() {
        return false;
    }
    
    public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
        boolean rep = false;
        switch (this.jeuCourant) {
            case BLANC:
                if (jeuBlanc.isPieceHere(xInit, yInit) 
                        && !(xInit == xFinal && yInit == yFinal) 
                        && jeuBlanc.isMoveOk(xInit, yInit, xFinal, yFinal)) { 
                   rep = jeuBlanc.Move(xInit, yInit, xFinal, yFinal);
                   this.message = "[" + this.jeuCourant.toString() + "] Déplacement de (" + xInit + "," + yInit + " vers " + xFinal + "," + yFinal + ") : OK : déplacement simple\n";
                }
                break;
            case NOIR:
                if (jeuNoir.isPieceHere(xInit, yInit) 
                        && !(xInit == xFinal && yInit == yFinal) 
                        && jeuNoir.isMoveOk(xInit, yInit, xFinal, yFinal)) { 
                   rep = jeuNoir.Move(xInit, yInit, xFinal, yFinal);
                   this.message = "[" + this.jeuCourant.toString() + "] Déplacement de (" + xInit + "," + yInit + " vers " + xFinal + "," + yFinal + ") : OK : déplacement simple\n";
                }
                break;
        }
        this.message = "Déplacement de (" + xInit + "," + yInit + " vers " + xFinal + "," + yFinal + " : OK : déplacement simple";
        
        return rep;
    }
    

    
    public void switchJoueur() {
        switch (this.jeuCourant) {
            case BLANC:
                this.jeuCourant = Couleur.NOIR;
                break;
            case NOIR:
                this.jeuCourant = Couleur.BLANC;
                break;
        }
    }
    
    public Couleur getColorCurrentPlayer() {
        return this.jeuCourant;
    }
    
    public String toString(){
        String ret = "Y \\ X", temp;
        
        for(int i = 0 ; i < 8 ; i++)
            ret += "\t  " + i;
        
        for(int y = 0 ; y < 8 ; y++){
            ret += "\n" + y;
            for(int x = 0 ; x < 8 ; x++){
                temp = jeuBlanc.afficherPiece(x, y);
                if(temp == ""){
                    temp = jeuNoir.afficherPiece(x, y);
                    if(temp == "")
                        ret += "\t _____";
                    else
                        ret += "\t" + temp;
                }
                else
                    ret += "\t" + temp;
            }
        }
        
        return ret;
    }
    
}
