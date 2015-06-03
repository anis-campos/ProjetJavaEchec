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
public class Pion extends AbstractPiece implements Pions{

    boolean depart;
    
    public Pion(String name, Couleur couleur, Coord coord) {
        super(name, couleur, coord);
        this.depart = true;
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal) {
        return true;
    }

    @Override
    public boolean move(int xFinal, int yFinal) {
        //
        //
        this.depart = false;
        return true;
    }

    @Override
    public boolean isMoveDiagOk() {
        return true;
    }
    
}
