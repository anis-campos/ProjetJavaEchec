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

        while (x != xFinal && y != yFinal) {
            switch (getMoveDirection(xFinal, yFinal)) {
                case HAUT_GAUCHE:
                    x--;
                    y--;
                    break;
                case BAS_GAUCHE:
                    x--;
                    y++;
                    break;
                case HAUT_DROITE:
                    x++;
                    y--;
                    break;
                case BAS_DROITE:
                    x++;
                    y++;
                    break;
                case BAS:
                    y--;
                    break;
                case GAUCHE:
                    x--;
                    break;
                case HAUT:
                    y++;
                    break;
                case DROITE:
                    x++;
                    break;
            }
            ret.add(new Coord(x, y));
        }

        return ret;
    }

}
