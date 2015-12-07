/**
 * Clase King que representa una pieza de Rey.
 * 
 * @author Eduardo Eder Vazquez Salcedo (edervs)
 * @date 28 de Noviembre del 2015
 * @version 1.0
 *
 **/
import java.io.*;
import java.util.ArrayList;
public class King extends Piece implements Serializable{
	
	/**
	 * Constructor que crea una King con color.
	 * 
	 * @param color Color de la King.
	 *
	 * @version 1.0
	 **/
	public King (boolean color) {
		super(color);
	}

	/**
	* Obtiene los posibles movimientos de la pieza en el tablero.
	*
	* @param y Coordenada 'y' de donde se encuentra la pieza.
	* @param x Coordenada 'x' de donde se encuentra la pieza.
	* @param board Tablero del juego.
	*
	* @version 1.0
	**/
	public ArrayList<IntegerArray> getPosibleMovements (int y, int x, ChessGame board) {

		ArrayList<IntegerArray> posible_movements = new
		                            				<IntegerArray>ArrayList();
		int[] coordinates;
		boolean flag;
		for (int i = -1; i <= 1; i += 1) {
			for (int j = -1; j <= 1; j += 1) {
				if (i != 0 || j != 0) {
					if (y + i < 8 && y + i >= 0 && x + j < 8 && x + j >= 0) {
						flag = false;
						if (super.getFlagToSavePosibleMovements(y + i, x + j, board)) {
							coordinates = new int[2];
							coordinates[0] = y + i;
							coordinates[1] = x + j;
							posible_movements.add(new IntegerArray(coordinates));
						}
					}
				}
			}
		}
		return posible_movements;
	}

	/**
	* Imprime la pieza.
	*
	* @version 1.0
	**/
	public void printPiece () {
		if (this.color) {
			System.out.print("♛");
		} else {
			System.out.print("♕");
		}
	}
}