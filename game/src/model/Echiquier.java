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

    private boolean echecEtMat;

    public Echiquier() {
        jeux = new Jeu[2];
        for (Couleur coul : Couleur.values()) {
            jeux[coul.ordinal()] = new Jeu(coul);
        }
        echecEtMat = false;
        jeuCourant = jeux[Couleur.BLANC.ordinal()];
        /*
         switch (jeuCourant.getCouleur()) {
         case BLANC:
         message = "L'équipe blanche a gagné le tirage au sort !";
         break;
         case NOIR:
         message = "L'équipe noire a gagné le tirage au sort !";
         break;
         }*/
    }

    public String getMessage() {
        return message;
    }

    public boolean isEchecEtMat() {
        return echecEtMat;
    }

    public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
        boolean rep = false;

        if (Coord.coordonnees_valides(xFinal, yFinal)
                && !(xInit == xFinal && yInit == yFinal)
                && jeuCourant.isPieceHere(xInit, yInit)
                && jeuCourant.isMoveOk(xInit, yInit, xFinal, yFinal)
                && collisionManager(xInit, yInit, xFinal, yFinal)) {

            if (jeuCourant.getPieceType(xInit, yInit).equals("Roi") && roiEnDanger(xFinal, yFinal)) {
                return false;
            }

            if (rep = jeuCourant.Move(xInit, yInit, xFinal, yFinal)) {
                this.message += "\n -> déplacement terminé";
                testEchecEtMat();
            } else {
                this.message += "\n -> il y a eu un problème dans le déplacement";
            }
        } else {
            message = "Il y a une/plusieurs erreur : ";
            if (!Coord.coordonnees_valides(xFinal, yFinal)) {
                message += "\n\t-> Coordonnées hors échiquier";
            }
            if (!jeuCourant.isPieceHere(xInit, yInit)) {
                message += "\n\t-> Ce n'est pas une pièce de votre équipe";
            } else if (xInit == xFinal && yInit == yFinal) {
                message += "\n\t-> Déplacement sur la même case";
            } else if (!jeuCourant.isMoveOk(xInit, yInit, xFinal, yFinal)) {
                message += "\n\t-> Déplacement interdit pour cette pièce : " + jeuCourant.getPieceType(xInit, yInit);
            }
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

    private boolean captureManager(int xInit, int yInit, int xFinal, int yFinal) {

        if (getAdversaire().isPieceHere(xFinal, yFinal)) {
            if ("Pion".equals(jeuCourant.getPieceType(xInit, yInit)) && !(Math.abs(xFinal - xInit) == Math.abs(yInit - yFinal))) {
                message = "deplacement interdit";
                return false;
            }
            getAdversaire().capture(xFinal, yFinal);
            String nomPieceCapture = getAdversaire().getPieceName(xFinal, yFinal);
            message = "[" + this.jeuCourant.toString() + "] Capture de (" + xInit + "," + yInit + " vers " + xFinal + "," + yFinal + ") : " + nomPieceCapture;
            return true;
        }

        message = "La pièce à capturer est une pièce de votre équipe";
        return false;
    }

    private boolean collisionManager(int xInit, int yInit, int xFinal, int yFinal) {

        int x = xInit;
        int y = yInit;
        String typePiece = jeuCourant.getPieceType(x, y);
        if (!"Cavalier".equals(typePiece)) {

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

            if ("Pion".equals(typePiece) && (Math.abs(xFinal - xInit) == Math.abs(yInit - yFinal))) {
                message = "Déplacement de pion interdit";

                return false;
            }
            message = "[" + this.jeuCourant.toString() + "] Déplacement simple de (" + xInit + "," + yInit + " vers " + xFinal + "," + yFinal + ")";
            return true;

        }

        if (getAdversaire().isPieceHere(xFinal, yFinal)) {
            if ("Pion".equals(typePiece) && !(Math.abs(xFinal - xInit) == Math.abs(yInit - yFinal))) {
                message = "Déplacement interdit";
                return false;
            }
            getAdversaire().capture(xFinal, yFinal);
            String nomPieceCapture = getAdversaire().getPieceName(x, y);
            message = "[" + this.jeuCourant.toString() + "] Capture de (" + xInit + "," + yInit + " vers " + xFinal + "," + yFinal + ") : " + nomPieceCapture;
            return true;

        }

        return false;

    }

    private boolean roiEnDanger(int xFinal, int yFinal) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (getAdversaire().isPieceHere(x, y)) {
                    if (collisionManager(x, y, xFinal, yFinal)) {
                        message = "Impossible, La pièce " + getAdversaire().getPieceName(x, y) + " " + new Coord(x, y) + " menace le roi";
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void testEchecEtMat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isPionToPromote(int x, int y) {
        return ("Pion".equals(jeuCourant.getPieceType(x, y)) && jeuCourant.getCouleur() == Couleur.BLANC) ? y == 0 : y == 7;
    }

    public boolean promote(int x, int y, String newType) {
        return this.jeuCourant.promote(x, y, newType);
    }

}
