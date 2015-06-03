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
 * @author Antoine
 */
public class Fou extends AbstractPiece {

    public Fou(String name, Couleur couleur, Coord coord) {
        super(name, couleur, coord);
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal) {
        return Coord.coordonnees_valides(xFinal, yFinal) && (abs(xFinal - getX()) == abs(yFinal - getY()));
    }

    @Override
    public List<Coord> movePath(int xFinal, int yFinal) {
        int x = this.coord.x;
        int y = this.coord.y;
        Deplacement direction = getMoveDirection(xFinal, yFinal);
        List<Coord> ret = new ArrayList<>();
        while (x != xFinal && y != yFinal) {
            switch (direction) {
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
                default:
                    throw new AssertionError(direction.name());
            }
            ret.add(new Coord(x, y));
        }
        return ret;
    }



}
