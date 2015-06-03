/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Coord> movePath(int xFinal, int yFinal) {
        List<Coord> ret = new ArrayList<>();
        
        if(abs(yFinal - this.coord.y) == 2){
            //si le pion se déplace de deux cases
            //on retient d'abord la première case (switch-case)
            switch(this.couleur){
                case BLANC:
                    ret.add(new Coord(xFinal, this.coord.y - 1));
                    break;
                case NOIR:
                    ret.add(new Coord(xFinal, this.coord.y + 1));
                    break;
            }
        }
        
        ret.add(new Coord(xFinal, yFinal));
        
        return ret;
    }

}
