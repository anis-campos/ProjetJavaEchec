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
public class Echiquier implements Cloneable {

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

    private Echiquier(Jeu jeux[], Jeu jeuCourant, String message, boolean echecEtMat) {
        this.jeux = jeux;
        this.jeuCourant = jeuCourant;
        this.message = message;
        this.echecEtMat = echecEtMat;

    }

    public String getMessage() {
        return message;
    }

    public boolean isEchecEtMat() {
        return echecEtMat;
    }

    @Override
    protected Object clone() {

        Jeu jeuxClone[] = new Jeu[2];
        for (int i = 0; i < jeuxClone.length; i++) {
            jeuxClone[i] = (Jeu) this.jeux[i].clone();
        }

        Jeu jeuCourantClone = jeuCourant.getCouleur() == Couleur.BLANC ? jeuxClone[Couleur.BLANC.ordinal()] : jeuxClone[Couleur.NOIR.ordinal()];

        Echiquier clone = new Echiquier(jeuxClone, jeuCourantClone, message, echecEtMat);

        return clone;
    }

    private boolean MetRoiEDanger(int xInit, int yInit, int xFinal, int yFinal, Coord roi) {
        Echiquier MondeParallele = (Echiquier) this.clone();
        MondeParallele.move(xInit, yInit, xFinal, yFinal);
        if (MondeParallele.roiEnDanger(roi)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
        boolean rep = false;

        Coord RoiCourant = jeuCourant.getKingCoord();
        Coord RoiAdversaire = getAdversaire().getKingCoord();

        boolean JeuCourantIsEchec = roiEnDanger(RoiCourant);

        if (roiEnDanger(RoiCourant)) {
            testEchecEtMat(RoiCourant);
        }

        if (Coord.coordonnees_valides(xFinal, yFinal)
                && !(xInit == xFinal && yInit == yFinal)
                && jeuCourant.isPieceHere(xInit, yInit)
                && jeuCourant.isMoveOk(xInit, yInit, xFinal, yFinal)
                && !collisionInPath(xInit, yInit, xFinal, yFinal)) {

            //Gestiion déplacement du roi.
            if (jeuCourant.getPieceType(xInit, yInit).equals("Roi") && roiEnDanger(new Coord(xFinal, yFinal))) {
                //gestion d'un déplacement interdit du roi
                return false;
            } else if (jeuCourant.getPieceType(xInit, yInit).equals("Roi")) {
                if (JeuCourantIsEchec) {
                    if (isPieceHereAllGames(xFinal, yFinal)) {
                        if (ifPionCapture(xInit, yInit, xFinal, yFinal)) //Il capture une pièce et n'est plus en danger
                        {
                            JeuCourantIsEchec = false;
                        } else {
                            message = "Vous Etes en échec et le roi ne peut manger cette pièce";
                        }
                    } else {
                        //il se sauve en se déplacant
                        JeuCourantIsEchec = false;
                    }
                }
            }

            if (JeuCourantIsEchec) {
                if (MetRoiEDanger(xInit, yInit, xFinal, yFinal, RoiCourant)) {
                    message = "Coups inutilse, roi toujour en danger !!!";
                    return false;
                } //Piece protège le roi
                else {
                    JeuCourantIsEchec = false;
                }

            }

            if (JeuCourantIsEchec) {
                if (testEchecEtMat(RoiCourant)) {
                    message = "votre roi est en échec et mat";
                    return false;
                } else {
                    message = "votre roi est en échec";
                    return false;
                }
            } else {
                //testEchecEtMat(RoiCourant);
                //PAT A GERER
            }

            //Gestion déplacement des autre pièces
            //Deplacement et Capture
            if (isPieceHereAllGames(xFinal, yFinal) && ifPionCapture(xInit, yInit, xFinal, yFinal)) {

                getAdversaire().capture(xFinal, yFinal);
                String nomPieceCapture = getAdversaire().getPieceName(xFinal, yFinal);
                message = "[" + this.jeuCourant.toString() + "] Deplacement avec  Capture de (" + xInit + "," + yInit + " vers " + xFinal + "," + yFinal + ") : " + nomPieceCapture;

            } else if (!isPieceHereAllGames(xFinal, yFinal)
                    && "Pion".equals(jeuCourant.getPieceType(xInit, yInit))
                    && (Math.abs(xFinal - xInit) == Math.abs(yInit - yFinal))) {
                message = "deplacement interdit";
                return false;
            } else {
                message = "Deplacement simple";
            }

            //Deplacement simple;
            if (rep = jeuCourant.move(xInit, yInit, xFinal, yFinal)) {
                this.message += "\n -> déplacement terminé";
                testEchecEtMat(RoiAdversaire);
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
        return getAdversaire(jeuCourant);
    }

    private Jeu getAdversaire(Jeu jeu) {
        return jeu.getCouleur() == Couleur.BLANC ? jeux[Couleur.NOIR.ordinal()] : jeux[Couleur.BLANC.ordinal()];
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
            rep = jeux[i].isPieceHere(x, y)
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

    private boolean ifPionCapture(int xInit, int yInit, int xFinal, int yFinal) {

        if ("Pion".equals(jeuCourant.getPieceType(xInit, yInit)) && !(Math.abs(xFinal - xInit) == Math.abs(yInit - yFinal))) {
            message = "deplacement interdit";
            return false;
        }
        return true;
    }

    private boolean collisionInPath(int xInit, int yInit, int xFinal, int yFinal) {

        int x = xInit;
        int y = yInit;
        String typePiece = jeuCourant.getPieceType(x, y);
        if (!"Cavalier".equals(typePiece)) {

            x += (int) Math.signum(xFinal - x);
            y += (int) Math.signum(yFinal - y);
            while (!(x == xFinal && y == yFinal)) {

                if (isPieceHereAllGames(x, y)) {
                    message = "Il y a une pièce sur la trajectoire";
                    return true;
                }

                x += (int) Math.signum(xFinal - x);
                y += (int) Math.signum(yFinal - y);

            }
        }

        //Pas de colision
        return false;

    }

    private boolean roiEnDanger(Coord roi) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (getAdversaire().isPieceHere(x, y)) {
                    if (getAdversaire().isMoveOk(x, y, roi.x, roi.y) && !collisionInPath(x, y, roi.x, roi.y) && ifPionCapture(x, y, roi.x, roi.y)) {
                        message = "La pièce " + getAdversaire().getPieceName(x, y) + " " + new Coord(x, y) + " menace le roi";
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean testEchecEtMat(Coord roi) {

       /* //Tester les deplacemnt du roi
        int X[] = {-1, 0, 1};
        int Y[] = {-1, 0, 1};
        for (int x : X) {
            for (int y : Y) {
                if (!this.roiEnDanger(new Coord(roi.x + x, roi.y + y))) {
                    return false;
                }
            }

        }

        //Tester tous les autres pièces 
        for (int xInit = 0; xInit < 8; xInit++) {
            for (int yInit = 0; yInit < 8; yInit++) {
                if (jeuCourant.isPieceHere(xInit, yInit)) {
                    for (int xFinal = 0; xFinal < 8; xFinal++) {
                        for (int yFinal = 0; yFinal < 8; yFinal++) {
                            if (!this.MetRoiEDanger(xInit, yInit, xFinal, yFinal, roi))
                                return false;
                        }
                    }
                }
            }
        }
        
        //Tous les déplacement on été testés, le roi est foutu !!!!
        return true;*/
        
        return false;
    }

    public boolean isPionToPromote(int x, int y) {
        if (!("Pion".equals(jeuCourant.getPieceType(x, y)))) {
            return false;
        } else {
            return jeuCourant.getCouleur() == Couleur.BLANC ? y == 0 : y == 7;
        }
    }

    public boolean promote(int x, int y, String newType) {
        return this.jeuCourant.promote(x, y, newType);
    }
    
    public boolean isALittleRoque(int xInit, int yInit, int xFinal, int yFinal){
        if(!jeuCourant.isALittleRoque(xInit, yInit, xFinal, yFinal))
            return false;
        else if(collisionInPath(xInit, yInit, xFinal, yFinal))
            return false;
        else
            return true;
    }
    
    public boolean isABigRoque(int xInit, int yInit, int xFinal, int yFinal){
        
        if(!jeuCourant.isABigRoque(xInit, yInit, xFinal, yFinal))
            return false;
        else if(collisionInPath(xInit, yInit, xFinal, yFinal))
            return false;
        else
            return true;
    }
    
    public boolean littleRoque(int xInit, int yInit, int xFinal, int yFinal){
        return this.jeuCourant.littleRoque(xInit, yInit, xFinal, yFinal);
    }
    
    public boolean bigRoque(int xInit, int yInit, int xFinal, int yFinal){
        return this.jeuCourant.bigRoque(xInit, yInit, xFinal, yFinal);
    }

}
