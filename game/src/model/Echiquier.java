/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

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

        switch (jeuCourant) {
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
                    if (collisionManager(jeuBlanc.movePath(xInit, yInit, xFinal, yFinal))) {
                        rep = jeuBlanc.Move(xInit, yInit, xFinal, yFinal);
                    }
                }
                break;
            case NOIR:
                if (jeuNoir.isPieceHere(xInit, yInit)
                        && !(xInit == xFinal && yInit == yFinal)
                        && jeuNoir.isMoveOk(xInit, yInit, xFinal, yFinal)) {

                    if (collisionManager(jeuNoir.movePath(xInit, yInit, xFinal, yFinal))) {
                        rep = jeuNoir.Move(xInit, yInit, xFinal, yFinal);
                    }
                }
                break;
        }
        this.message = "[" + this.jeuCourant.toString() + "] Déplacement de (" + xInit + "," + yInit + " vers " + xFinal + "," + yFinal + ") : OK : déplacement simple\n";

        return rep;
    }

    //si renvoie true : OK pour MOVE
    private boolean collisionManager(List<Coord> listCoordPath) {
        int i = 0;
        boolean haveNotAFriendInPath = true;
        while (i < listCoordPath.size() && haveNotAFriendInPath) {
            if (jeuBlanc.isPieceHere(listCoordPath.get(i).x, listCoordPath.get(i).y)
                    || jeuNoir.isPieceHere(listCoordPath.get(i).x, listCoordPath.get(i).y)) {
                haveNotAFriendInPath = false;
            }

            i++;
        }

        if (i == listCoordPath.size() && !haveNotAFriendInPath) {
            int dernierX = listCoordPath.get(listCoordPath.size() - 1).x;
            int dernierY = listCoordPath.get(listCoordPath.size() - 1).y;
            switch (this.jeuCourant) {
                case BLANC:
                    if (this.jeuNoir.isPieceHere(dernierX, dernierY)) {
                        this.jeuNoir.capture(dernierX, dernierY);
                    }
                    break;
                case NOIR:
                    if (this.jeuBlanc.isPieceHere(dernierX, dernierY)) {
                        this.jeuBlanc.capture(dernierX, dernierY);
                    }
                    break;
            }
        }

        return haveNotAFriendInPath;
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

    public String toString() {
        String ret = "Y \\ X", temp;

        for (int i = 0; i < 8; i++) {
            ret += "\t  " + i;
        }

        for (int y = 0; y < 8; y++) {
            ret += "\n" + y;
            for (int x = 0; x < 8; x++) {
                ret += "\t";
                ret += !jeuBlanc.isCaptured(x, y) && jeuBlanc.isPieceHere(x, y)
                        ? jeuBlanc.getPieceName(x, y)
                        : !jeuNoir.isCaptured(x, y) && jeuNoir.isPieceHere(x, y) ? jeuNoir.getPieceName(x, y) : "_____";
            }
        }

        return ret;
    }

}
