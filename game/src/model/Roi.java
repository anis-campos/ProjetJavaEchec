 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Anis
 */
public class Roi extends AbstractPiece{

    public Roi(String name, Couleur couleur, Coord coord) {
        super(name, couleur, coord);
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean move(int xFinal, int yFinal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
