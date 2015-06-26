package controller.controllerRemote;
import model.Coord;
import model.observable.ChessGame;
import controller.AbstractChessGameControler;


/**
 * @author francoise.perrin
 * 
 * Ce controleur ne fait quasi rien � part
 * transformer les coordonn�es venant de la vue sous forme de Coord
 * en 2 int
 *
 */
public class ChessGameControler extends AbstractChessGameControler {
	
	
	public ChessGameControler(ChessGame chessGame) {
		super(chessGame);
	}

    @Override
    public boolean move(Coord initCoord, Coord finalCoord) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean promote(Coord coord, String newType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
	

}
