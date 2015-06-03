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
public class Roi extends AbstractPiece {

    public Roi(String name, Couleur couleur, Coord coord) {
        super(name, couleur, coord);
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal) {

        return estDansPlateau(xFinal, yFinal) && (abs(getX() - xFinal) <= 1 && abs(yFinal - getY()) <= 1);
    }

    @Override
    public List<Coord> movePath(int xFinal, int yFinal) {

        List<Coord> rep = new ArrayList<>();

        rep.add(new Coord(xFinal, yFinal));

        return rep;

    }

}
