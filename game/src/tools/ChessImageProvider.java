package tools;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import model.Coord;
import model.Couleur;

/**
 * @author francoise.perrin
 * Inspiration Jacques SARAYDARYAN, Adrien GUENARD
 * 
 * Cette classe s'appuie sur ChessPieceImage
 * pour fournir les noms des images des pi�ces
 * qui sont utilis�es dans l'IHM par rapport � des indices
 * exprim�s par rapport aux lignes (i), colonnes (j) du damier
 * de l'IHM graphique
 *  *
 */
public class ChessImageProvider {
	
	private static Map<String, String> mapImage;
	private static Map<Coord, NomCouleurPiece> mapCoordPiece;

	static {
		
		mapImage = new HashMap<String, String>();
		for (int i = 0; i < ChessPieceImage.values().length; i++) {
			mapImage.put(ChessPieceImage.values()[i].nom, ChessPieceImage.values()[i].imageFile);
		}
		
		mapCoordPiece = new HashMap<Coord, NomCouleurPiece>();
		for (int i = 0; i < ChessPiecePos.values().length; i++) {			
			for (int j = 0; j < (ChessPiecePos.values()[i].coords).length; j++) {
				String nomPiece = ChessPiecePos.values()[i].nom;	
				Couleur couleurPiece = ChessPiecePos.values()[i].couleur;
				Coord pieceCoord = ChessPiecePos.values()[i].coords[j];
				mapCoordPiece.put(pieceCoord, new NomCouleurPiece(nomPiece, couleurPiece));			
			}
		}
	}

	/**
	 * private pour ne pas instancier d'objets
	 */
	private ChessImageProvider() {

	}
	
	/**
	 * @param i
	 * @param j
	 * @return le type de la pi�ce aux coordonn�es i, j
	 */
	public static String getType(int i, int j) {
		String type = null;
		Coord coord = null;
		NomCouleurPiece nomCouleurPiece = null;
		
		coord = findMapCoordPiece(i, j);
		if (coord != null){
			nomCouleurPiece = mapCoordPiece.get(coord);
			type = nomCouleurPiece.nom;
		}
		return type;
	}

	/**
	 * @param i
	 * @param j
	 * @return couleur de la pi�ce aux coordonn�es i, j
	 */
	public static Couleur getCouleur(int i, int j) {
		Couleur couleur  = null;
		Coord coord = null;
		NomCouleurPiece nomCouleurPiece = null;
		
		coord = findMapCoordPiece(i, j);
		if (coord != null){
			nomCouleurPiece = mapCoordPiece.get(coord);
			couleur = nomCouleurPiece.couleur;
		}
		return couleur;
	}
	
	/**
	 * @param pieceType
	 * @param pieceCouleur
	 * @return nom fichier contenant image de la pi�ce
	 */
	public static String getImageFile(String pieceType, Couleur pieceCouleur){
		String ret, key, value;
		ret = null;
		key = pieceType + pieceCouleur.name();
		value = mapImage.get(key);
		File g=new File("");
		return Paths.get(g.getAbsolutePath(),"src","images", value).toString();		
	}



	/**
	 * @param i
	 * @param j
	 * @return true s'il existe une pi�ce sur cette ligne et cette colonne
	 */
	public static boolean isCoordOK(int i, int j) {
		boolean ret = false;
		ret = findMapCoordPiece(i, j) != null ? true : false;
		return ret;
	}

	/**
	 * @param i
	 * @param j
	 * @return un objet de type Coord connaissant i et j
	 */
	private static Coord findMapCoordPiece(int i, int j) {
		Coord ret = null;
		Set<Coord> keys = mapCoordPiece.keySet();
		for (Coord coord : keys){
			if (j == coord.x && i == coord.y){
				ret = coord;
			}
		}
		return ret;
	}

	

	/**
	 * Test unitaires
	 * @param args
	 */
	public static void main(String[] args) {


		System.out.println(ChessImageProvider.getImageFile("Cavalier", Couleur.BLANC));
	}


}
