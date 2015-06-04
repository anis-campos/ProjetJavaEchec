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
public class Reine extends AbstractPiece {

    public Reine(String name, Couleur couleur, Coord coord) {
        super(name, couleur, coord);
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal) {

        return Coord.coordonnees_valides(xFinal, yFinal)
                && ((abs(xFinal - getX()) == abs(yFinal - getY()))
                || (abs(xFinal - getX()) == 0 && abs(getY() - yFinal) > 0)
                || (abs(xFinal - getX()) > 0 && abs(getY() - yFinal) == 0));
    }

  
}
