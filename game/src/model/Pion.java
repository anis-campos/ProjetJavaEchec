/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static java.lang.Math.abs;

/**
 *
 * @author Anis
 */
public class Pion extends AbstractPiece implements Pions {

    boolean depart;

    public Pion(String name, Couleur couleur, Coord coord) {
        super(name, couleur, coord);
        this.depart = true;
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal) {
        if (depart) {
            switch (this.couleur) {
                case BLANC:
                    return getY() - yFinal <= 2;

                case NOIR:
                    return yFinal - getY() <= 2;

                default:
                    return false;
            }
        } else {
            switch (this.couleur) {
                case BLANC:
                    return (getY() - yFinal <= 1) /*&& (abs(getX() - xFinal) <= 1)*/;

                case NOIR:
                    return (yFinal - getY() <= 1) /*&& (abs(getX() - xFinal) <= 1)*/;
                default:
                    return false;
            }
        }

    }

    @Override
    public boolean move(int xFinal, int yFinal) {
        boolean rep = super.move(xFinal, yFinal);
        depart = false;
        return rep;
    }

    @Override
    public boolean isMoveDiagOk() {
        return true;
    }

}
