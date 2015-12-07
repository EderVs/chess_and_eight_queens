/**
 * Clase Knight que representa una pieza de Caballo.
 * 
 * @author Eduardo Eder Vazquez Salcedo (edervs)
 * @date 28 de Noviembre del 2015
 * @version 1.0
 *
 **/
import java.io.*;
import java.util.ArrayList;
class Knight extends Piece implements Serializable{
	
	/**
	 * Constructor que crea una Caballo con color.
	 * 
	 * @param color Color de la Caballo.
	 *
	 * @version 1.0
	 **/
	public Knight (boolean color) {
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
	public ArrayList getPosibleMovements (int y, int x, ChessGame board) {
		ArrayList<IntegerArray> posible_movements = new 
													<IntegerArray>ArrayList();
		int[] coordinates;
		int current_y;
		for (int i = 1; i <= 2; i += 1) {
			current_y = y - i;
			coordinates = new int[2];
			if (current_y >= 0) {
				if (x - (1 + 2-i) >= 0) {
					if (super.getFlagToSavePosibleMovements(current_y, x - (1 + 2-i), board)) {
						coordinates[0] = current_y;
						coordinates[1] = x - (1 + 2-i);
						posible_movements.add(new IntegerArray(coordinates));
					}
				}
				coordinates = new int[2];
				if (x + (1 + 2-i) <= 7) {
					if (super.getFlagToSavePosibleMovements(current_y, x + (1 + 2-i), board)) {
						coordinates[0] = current_y;
						coordinates[1] = x + (1 + 2-i);
						posible_movements.add(new IntegerArray(coordinates));
					}
				}
			}
			current_y = y + i;
			coordinates = new int[2];
			if (current_y <= 7) {
				if (x - (1 + 2-i) >= 0) {
					if (super.getFlagToSavePosibleMovements(current_y, x - (1 + 2-i), board)) {
						coordinates[0] = current_y;
						coordinates[1] = x - (1 + 2-i);
						posible_movements.add(new IntegerArray(coordinates));
					}
				}
				coordinates = new int[2];
				if (x + (1 + 2-i) <= 7) {
					if (super.getFlagToSavePosibleMovements(current_y, x + (1 + 2-i), board)) {
						coordinates[0] = current_y;
						coordinates[1] = x + (1 + 2-i);
						posible_movements.add(new IntegerArray(coordinates));
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
			System.out.print("♞");
		} else {
			System.out.print("♘");
		}
	}
}