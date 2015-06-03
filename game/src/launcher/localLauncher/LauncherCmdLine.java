package launcher.localLauncher;

import model.observable.ChessGame;
import view.ChessGameCmdLine;
import controller.controllerLocal.ChessGameControler;


/**
 * @author francoise.perrin
 * Lance l'exécution d'un jeu d'échec en mode console.
 */
public class LauncherCmdLine {
	
	public static void main(String[] args) {		
		
		ChessGame chessGame = new ChessGame();
		ChessGameControler chessGameControler;		
		
		chessGame = new ChessGame();	
		chessGameControler = new ChessGameControler(chessGame);
		
		new ChessGameCmdLine(chessGameControler);	
	}

}
