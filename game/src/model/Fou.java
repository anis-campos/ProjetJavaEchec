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
        return estDansPlateau(xFinal, yFinal)&&(abs(xFinal-getX())==abs(yFinal-getY()));
    }

    @Override
    public List<Coord> movePath(int xFinal, int yFinal) {
        int x = this.coord.x;
        int y = this.coord.y;
        
        List<Coord> ret = new ArrayList<>();
        
        while(x != xFinal && y != yFinal){
            if(xFinal < x && yFinal < y){
            //[0 0] déplacement en haut à droite
                x--;
                y--;
                ret.add(new Coord(x, y));
            }
            else if(xFinal < x && yFinal > y){
            //[0 1] déplacement en bas à gauche
                x--;
                y++;
                ret.add(new Coord(x, y));
            }
            else if(xFinal > x && yFinal < y){
            //[1 0] déplacement en haut à droite
                x++;
                y--;
                ret.add(new Coord(x, y));    
            }
            else if(xFinal > x && yFinal > y){
            //[1 1] déplacement en bas à droite
                x++;
                y++;
                ret.add(new Coord(x, y));    
            }
        }
        
        return ret;
    }

}
