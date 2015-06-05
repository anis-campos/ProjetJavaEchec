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
                boolean roqueMade = false;
                
                int xInitRoque = 0;
                int xFinalRoque = 0;
                int yInitRoque = yInit;
                int yFinalRoque = yFinal;
                
                if(this.echiquier.isABigRoque(xInit, yInit, xFinal, yFinal)){
                    ret = this.echiquier.bigRoque(xInit, yInit, xFinal, yFinal);
                    roqueMade = true;
                    xInitRoque = 0;
                    xFinalRoque = 3;
                }                
                else if(this.echiquier.isALittleRoque(xInit, yInit, xFinal, yFinal)){
                    ret = this.echiquier.littleRoque(xInit, yInit, xFinal, yFinal);
                    roqueMade = true;
                    xInitRoque = 7;
                    xFinalRoque = 5;
                }
                else if (!echiquier.isEchecEtMat()) {
			ret = echiquier.move(xInit, yInit, xFinal, yFinal);
                        if(ret)
                            pionPromoted = echiquier.isPionToPromote(xFinal, yFinal);
		}
		if (ret && !pionPromoted){
			echiquier.switchJoueur();
		}
		this.setChanged();
                
		this.notifyObservers(new Object[]{xInit, yInit, xFinal, yFinal, ret, pionPromoted, getColorCurrentPlayer(), roqueMade, xInitRoque, yInitRoque,
                    xFinalRoque, yFinalRoque});
		return ret;	
	}
        
        public boolean promote(int x, int y, String newType){
            boolean ret = false;
            Couleur couleur = null;
		if (!echiquier.isEchecEtMat()) {
			ret = echiquier.promote(x, y, newType);
		}
		if (ret){
                        couleur = getColorCurrentPlayer();
			echiquier.switchJoueur();
		}
		this.setChanged();
		this.notifyObservers(new Object[]{x, y, newType, couleur});
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
