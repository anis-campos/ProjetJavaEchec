package controller;



import model.Coord;
import model.Couleur;
import model.ChessGame;


/**
 * @author francoise.perrin
 * 
 * Méthodes communes des controleurs
 * dont le travail essentiel est de faire communiquer
 * la vue et le modèle pour gérer le déplacement 
 * des pièces
 *
 */
public abstract class AbstractChessGameControler implements ChessGameControlers {
	
	protected ChessGame chessGame;
	
	
	public AbstractChessGameControler(ChessGame chessGame) {
		super();
		this.chessGame = chessGame;
	}
	
	public abstract boolean move(Coord initCoord, Coord finalCoord) ;
	

	public boolean isEchecEtMat(){
		return chessGame.isEchecEtMat();		
	}
	
	public String getMessage() {
		return chessGame.getMessage();
	}

	public String toString() {
		return chessGame.toString();
	}
	
	public Couleur getColorCurrentPlayer(){		
		return chessGame.getColorCurrentPlayer();		
	}	


}
