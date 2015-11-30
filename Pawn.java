/**
 * Clase Pawn que representa una pieza de Peon.
 * 
 * @author Eduardo Eder Vazquez Salcedo (edervs)
 * @date 28 de Noviembre del 2015
 * @version 1.0
 *
 **/
import java.util.ArrayList;
class Pawn extends Piece {
	
	/**
	 * Constructor que crea una Peon con color.
	 * 
	 * @param color Color de la Peon.
	 *
	 * @version 1.0
	 **/
	public Pawn (boolean color) {
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
		int[] coordinates = new int[2];
		int changes = -1;
		int special = 6;
		int spaces = 1;
		if (super.color) {
			changes = 1;
			special = 1;
		}
		if (y == special) {
			spaces = 2;
		}

		for (int i = 1; i <= spaces; i += 1) {
			if (!board.boxes[y+(i * changes)][x].isThereAPiece()) {
				coordinates = new int[2];
				coordinates[0] = y + (i * changes);
				coordinates[1] = x;
				posible_movements.add(new IntegerArray(coordinates));
			} else {
				break;
			}
		}	
		
		if (y + (1 * changes) >= 0 && y + (1 * changes) <= 7) {
			if (x - 1 >= 0) {
				if (board.boxes[y + (1 * changes)][x - 1].isThereAPiece()) {
					if (board.boxes[y + (1 * changes)][x - 1].piece.color != super.color) {
						coordinates = new int[2];
						coordinates[0] =  y + (1 * changes);
						coordinates[1] = x - 1;
						posible_movements.add(new IntegerArray(coordinates));
					}
				}
			}
			if (x + 1 <= 7) {
				if (board.boxes[y + (1 * changes)][x + 1].isThereAPiece()) {
					if (board.boxes[y + (1 * changes)][x + 1].piece.color != super.color) {
						coordinates = new int[2];
						coordinates[0] =  y + (1 * changes);
						coordinates[1] = x + 1;
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
			System.out.print("♟");
		} else {
			System.out.print("♙");
		}
	}
}