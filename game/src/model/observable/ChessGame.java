package model.observable;


import java.util.Observable;
import model.Couleur;

import model.Echiquier;


/**
 * @author francoise.perrin
 * 
 * Cette classe rend un Echiquier Observable
 * et en simplifie l'interface ( DP Proxy, Facade, Observer)
 *
 */
public class ChessGame extends Observable{
	
	private Echiquier echiquier;
	
	/**
	 * Cr�e une instance de la classe Echiquier
	 */
	public ChessGame() {
		super();
		this.echiquier = new Echiquier();
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String st = "";
		st += "\n" + echiquier.getMessage() + "\n";
		st += echiquier.toString();	
		return  st;
	}


	/**
	 * Permet de d�placer une pi�ce connaissant ses coordonn�es initiales vers ses
	 * coordonn�es finales si le d�placement est "l�gal". 
	 * Si d�placement OK, permet l'alternance des joueurs.
	 * @param xInit
	 * @param yInit
	 * @param xFinal
	 * @param yFinal
	 * @return OK si d�placement OK
	 * si OK, permet l'alternance des joueurs
	 */
	public boolean move (int xInit, int yInit, int xFinal, int yFinal){
		boolean ret = false;
                boolean pionPromoted = false;
		if (!echiquier.isEchecEtMat()) {
			ret = echiquier.move(xInit, yInit, xFinal, yFinal);
                        if(ret)
                            pionPromoted = echiquier.isPionToPromote(xFinal, yFinal);
		}
		if (ret && !pionPromoted){
			echiquier.switchJoueur();
		}
		this.setChanged();
                
		this.notifyObservers(new Object[]{xInit, yInit, xFinal, yFinal, ret, pionPromoted, getColorCurrentPlayer()});
		return ret;	
	}
        
        public boolean promote(int x, int y, String newType){
            boolean ret = false;
		if (!echiquier.isEchecEtMat()) {
			ret = echiquier.promote(x, y, newType);
		}
		if (ret){
			echiquier.switchJoueur();
		}
		this.setChanged();
		this.notifyObservers(new Object[]{x, y});
		return ret;
        }

	public boolean isEchecEtMat(){
		return echiquier.isEchecEtMat();		
	}
	
	public String getMessage() {
		return echiquier.getMessage();
	}
	

	public Couleur getColorCurrentPlayer(){		
		return echiquier.getColorCurrentPlayer();		
	}	

}
