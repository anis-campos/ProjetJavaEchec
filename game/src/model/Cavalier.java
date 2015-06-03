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
public class Cavalier extends AbstractPiece {

    public Cavalier(String name, Couleur couleur, Coord coord) {
        super(name, couleur, coord);
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal) {
        return estDansPlateau(xFinal, yFinal)&&
                ((abs(xFinal - getX()) == 2 && abs(yFinal - getY()) == 1) 
                    || (abs(xFinal - getX()) == 1 && abs(yFinal - getY()) == 1));

    }

}
