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

        return estDansPlateau(xFinal, yFinal)
                && ((abs(xFinal - getX()) == abs(yFinal - getY()))
                || (abs(xFinal - getX()) == 0 && abs(getY() - yFinal) > 0)
                || (abs(xFinal - getX()) > 0 && abs(getY() - yFinal) == 0));
    }

    @Override
    public List<Coord> movePath(int xFinal, int yFinal) {
        int x = this.coord.x;
        int y = this.coord.y;

        List<Coord> ret = new ArrayList<>();

        if (abs(xFinal - getX()) == abs(yFinal - getY())) {
            //SI DEPLACEMENT DIAGONALE

            while (x != xFinal && y != yFinal) {
                if (xFinal < x && yFinal < y) {
                    //[0 0] déplacement en haut à droite
                    x--;
                    y--;
                    ret.add(new Coord(x, y));
                } else if (xFinal < x && yFinal > y) {
                    //[0 1] déplacement en bas à gauche
                    x--;
                    y++;
                    ret.add(new Coord(x, y));
                } else if (xFinal > x && yFinal < y) {
                    //[1 0] déplacement en haut à droite
                    x++;
                    y--;
                    ret.add(new Coord(x, y));
                } else if (xFinal > x && yFinal > y) {
                    //[1 1] déplacement en bas à droite
                    x++;
                    y++;
                    ret.add(new Coord(x, y));
                }
            }

        } else {
            //SI DEPLACEMENT DROIT
            while (x != xFinal && y != yFinal) {
                if (abs(xFinal - getX()) == 0 && (getY() - yFinal) > 0) {
                    y--;
                } else if (abs(xFinal - getX()) == 0 && (getY() - yFinal) < 0) {
                    y++;
                } else if ((xFinal - getX()) > 0 && abs(getY() - yFinal) == 0) {
                    x++;
                } else if ((xFinal - getX()) < 0 && abs(getY() - yFinal) == 0) {
                    x--;
                }
            }

        }

        return ret;
    }

}
