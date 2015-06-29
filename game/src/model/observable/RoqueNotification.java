/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.observable;

/**
 *
 * @author Anis.DASILVACAMPOS
 */
public class RoqueNotification extends MoveNotification {

    public int xTourInit, xTourFinal, yTourInit, yTourFinal;


    public RoqueNotification(int xInit, int yInit, int xFinal, int yFinal, boolean isMoveOk, TypeRoque typeRoque) {
        super(xInit, yInit, xFinal, yFinal, isMoveOk, false);

        this.yTourInit = yInit;
        this.yTourFinal = yFinal;
        
        
        switch (typeRoque) {
            case BIG:
                this.xTourInit = 0;
                this.xTourFinal = 3;
                break;
            case LITTLE:
                this.xTourInit = 7;
                this.xTourFinal = 5;
                break;
            default :
                this.xTourInit = xInit;
                this.xTourFinal = xFinal;
                break;
        }

    }

    public enum TypeRoque {

        BIG, LITTLE
    }

    @Override
    public String toString() {
        return "Big Roque : Roi ("+xInit+","+yInit+")->("+xFinal+","+yFinal+") et Tour ("+xTourInit+","+yTourInit+")->("+xTourFinal+","+yTourFinal+")";
    }

    
}
