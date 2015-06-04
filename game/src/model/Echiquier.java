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

    private Jeu jeux[];
    private Jeu jeuCourant;

    private String message;

    public Echiquier() {
        jeux = new Jeu[2];
        for (Couleur coul : Couleur.values()) {
            jeux[coul.ordinal()] = new Jeu(coul);
        }

        jeuCourant = jeux[(int) Math.round(Math.random())];

        switch (jeuCourant.getCouleur()) {
            case BLANC:
                message = "L'équipe blanche a gagné le tirage au sort !";
                break;
            case NOIR:
                message = "L'équipe noire a gagné le tirage au sort !";
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

        if (Coord.coordonnees_valides(xFinal, yFinal)
                && !(xInit == xFinal && yInit == yFinal)
                && jeuCourant.isPieceHere(xInit, yInit)
                && jeuCourant.isMoveOk(xInit, yInit, xFinal, yFinal)
                && collisionManager(xInit, yInit, xFinal, yFinal)) {
            if (rep = jeuCourant.Move(xInit, yInit, xFinal, yFinal))
                this.message += "\n -> déplacement terminé";
            
        }

        return rep;
    }

    public void switchJoueur() {
        jeuCourant = getAdversaire();
    }

    private Jeu getAdversaire() {
        return jeuCourant.getCouleur() == Couleur.BLANC ? jeux[Couleur.NOIR.ordinal()] : jeux[Couleur.BLANC.ordinal()];
    }

    public Couleur getColorCurrentPlayer() {
        return this.jeuCourant.getCouleur();
    }

    @Override
    public String toString() {
        String ret = "Y \\ X", temp;

        for (int i = 0; i < 8; i++) {
            ret += "\t  " + i;
        }

        for (int y = 0; y < 8; y++) {
            ret += "\n" + y;
            for (int x = 0; x < 8; x++) {
                ret += "\t";
                for (Jeu jeu : jeux) {

                }
                ret += getCase(x, y);
            }
        }

        return ret;
    }

    private String getCase(int x, int y) {

        String rep = "_____";

        int i = 0;
        while (i < jeux.length && "_____".equals(rep)) {
            rep = !jeux[i].isCaptured(x, y) && jeux[i].isPieceHere(x, y)
                    ? jeux[i].getPieceName(x, y) : rep;
            i++;
        }

        return rep;
    }

    private boolean isPieceHereAllGames(int x, int y) {
        boolean rep = false;
        for (Jeu jeu : jeux) {
            if (jeu.isPieceHere(x, y)) {
                return true;
            }
        }
        return rep;
    }

    private boolean collisionManager(int xInit, int yInit, int xFinal, int yFinal) {

        int x = xInit;
        int y = yInit;
        if (!"Cavalier".equals(jeuCourant.getPieceType(x, y))) {

            x += (int) Math.signum(xFinal - x);
            y += (int) Math.signum(yFinal - y);
            while (!(x == xFinal && y == yFinal)) {

                if (isPieceHereAllGames(x, y)) {
                    message = "Il y a une pièce sur la trajectoire";
                    return false;
                }

                x += (int) Math.signum(xFinal - x);
                y += (int) Math.signum(yFinal - y);

            }
        }

        if (!isPieceHereAllGames(xFinal, yFinal)) {
            message = "[" + this.jeuCourant.toString() + "] Déplacement Simple de (" + xInit + "," + yInit + " vers " + xFinal + "," + yFinal + ")";
            return true;
        }

        if (getAdversaire().isPieceHere(xFinal, yFinal)) {
            getAdversaire().capture(xFinal, yFinal);
            String nomPieceCapture = getAdversaire().getPieceName(x, y);
            message = "[" + this.jeuCourant.toString() + "] Capture de (" + xInit + "," + yInit + " vers " + xFinal + "," + yFinal + ") : "+nomPieceCapture;
            return true;
        }

        message = "La pièce à capturer est une pièce de votre équipe";
        return false;

    }

}
