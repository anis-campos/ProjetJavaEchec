package controller;

import model.Coord;
import model.Couleur;

public interface ChessGameControlers {

	boolean move(Coord initCoord, Coord finalCoord);

	String getMessage();
	
	public boolean isEchecEtMat();
	
	public Couleur getColorCurrentPlayer();

}
