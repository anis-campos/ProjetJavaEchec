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
public class Tour extends AbstractPiece {

    public Tour(String name, Couleur couleur, Coord coord) {
        super(name, couleur, coord);
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal) {
        return estDansPlateau(xFinal, yFinal) && ((abs(xFinal - getX()) == 0
                && abs(getY() - yFinal) > 0)
                || (abs(xFinal - getX()) > 0
                && abs(getY() - yFinal) == 0));
    }

    @Override
    public List<Coord> movePath(int xFinal, int yFinal) {

        int x = this.coord.x;
        int y = this.coord.y;
        Deplacement direction = getMoveDirection(xFinal, yFinal);
        List<Coord> ret = new ArrayList<>();

        while (x != xFinal && y != yFinal) {

            switch (direction) {
                case HAUT:
                    y++;
                    break;

                case DROITE:
                    x++;
                    break;

                case BAS:
                    y--;
                    break;

                case GAUCHE:
                    x--;
                    break;

                default:
                    throw new AssertionError(direction.name());

            }
            ret.add(new Coord(x, y));
        }
        return ret;
    }

}
