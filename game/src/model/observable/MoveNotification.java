/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.observable;

import model.Couleur;

/**
 *
 * @author Anis.DASILVACAMPOS
 */
public class MoveNotification extends Notification {

    public boolean isMoveOk,isPionToPromote;

    MoveNotification(int xInit, int yInit, int xFinal, int yFinal, boolean isMoveOk, boolean isPionToPromote) {
        super(xInit, yInit, xFinal, yFinal);
        this.isMoveOk = isMoveOk;
        this.isPionToPromote = isPionToPromote;
    }

}
