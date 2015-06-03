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
        message = "Aucun mouvement n'a encore été fait";
        jeuBlanc = new Jeu(Couleur.BLANC);
        jeuNoir = new Jeu(Couleur.NOIR);
    }
    
    public String getMessage() {
        return message;
    }
    
    public boolean isEchecEtMat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
        boolean rep = false;
        switch (this.jeuCourant) {
            case BLANC:
                if (jeuBlanc.isPieceHere(xInit, yInit) 
                        && !(xInit == xFinal && yInit == yFinal) 
                        && jeuBlanc.isMoveOk(xInit, yInit, xFinal, yFinal)) {
                   
                }
                
                break;
            case NOIR:
                this.jeuCourant = Couleur.BLANC;
                break;
        }
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
        String ret = "", temp;
        
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
