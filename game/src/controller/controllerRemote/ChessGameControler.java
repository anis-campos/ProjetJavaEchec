package controller.controllerRemote;

import model.Coord;
import model.observable.ChessGame;
import controller.AbstractChessGameControler;
import controller.MoveNotification;
import controller.PromotedNotification;
import model.Couleur;

/**
 * @author francoise.perrin
 *
 * Ce controleur ne fait quasi rien � part transformer les coordonn�es venant de
 * la vue sous forme de Coord en 2 int
 *
 */
public class ChessGameControler extends AbstractChessGameControler {

    public ChessGameControler(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public boolean move(Coord initCoord, Coord finalCoord) {
        if (chessGame.getColorCurrentPlayer() == Couleur.NOIR && chessGame.move(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y)) {
            notifyObservers(new MoveNotification(initCoord,finalCoord));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean promote(Coord coord, String newType) {
        if (chessGame.getColorCurrentPlayer() == Couleur.NOIR && chessGame.promote(coord.x, coord.y, newType)) {
            notifyObservers(new PromotedNotification(coord, newType));
            return true;
        } else {
            return false;
        }
    }

}
