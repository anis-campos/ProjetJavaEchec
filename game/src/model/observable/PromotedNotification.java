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
public class PromotedNotification extends Notification {

    public String newType;
    public Couleur couleur;

    PromotedNotification(int x, int y, String newType, Couleur couleur) {
        super(x, y, x, y);
        this.newType = newType;
        this.couleur = couleur;
    }


}
