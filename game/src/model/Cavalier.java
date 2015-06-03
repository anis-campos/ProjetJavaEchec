
package model;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.List;

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
                (
                (abs(xFinal - getX()) == 2 && abs(yFinal - getY()) == 1) 
                    || 
                (abs(xFinal - getX()) == 1 && abs(yFinal - getY()) == 2)
                );

    }

    @Override
    public List<Coord> movePath(int xFinal, int yFinal) {
        List<Coord> rep = new ArrayList<>();
        rep.add(new Coord(xFinal, yFinal));
        return rep;
    }


}
