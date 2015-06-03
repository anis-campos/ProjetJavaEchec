package model;

import java.util.List;

/**
 *
 * @author Antoine
 */
public interface Pieces {
    boolean capture();
    Couleur getCouleur();
    String getName();
    int getX();
    int getY();
    boolean getCapture();
    boolean  isMoveOk(int xFinal, int yFinal);
    boolean move(int xFinal, int yFinal);
    List<Coord> movePath (int xFinal, int yFinal);
    
}