/**
 * Clase Bishop que representa una pieza de Alfil.
 * 
 * @author Eduardo Eder Vazquez Salcedo (edervs)
 * @date 28 de Noviembre del 2015
 * @version 1.0
 *
 **/
import java.util.ArrayList;
import java.io.*;
class Bishop extends Piece implements Serializable{
	
	/**
	 * Constructor que crea una Alfil con color.
	 * 
	 * @param color Color de la Alfil.
	 *
	 * @version 1.0
	 **/
	public Bishop (boolean color) {
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

		for (int i = y + 1; i <= 7; i += 1) {
			if (x + i - y <= 7) {
				if (super.getFlagToSavePosibleMovements(i, x + i - y, board)) {
					coordinates = new int[2];
					coordinates[0] = i;
					coordinates[1] = x + i - y;
					posible_movements.add(new IntegerArray(coordinates));
					if (board.boxes[i][x + i - y].isThereAPiece()) {
						break;
					}
				} else {
					break;
				}
			} else {
				break;
			}
		}

		for (int i = y + 1; i <= 7; i += 1) {
			if (x - i + y >= 0) {
				if (super.getFlagToSavePosibleMovements(i, x - i + y, board)) {
					coordinates = new int[2];
					coordinates[0] = i;
					coordinates[1] = x - i + y;
					posible_movements.add(new IntegerArray(coordinates));
					if (board.boxes[i][x - i + y].isThereAPiece()) {
						break;
					}
				} else {
					break;
				}
			} else {
				break;
			}
		}

		for (int i = y - 1; i >= 0; i -= 1) {
			if (x + i - y >= 0) {
				if (super.getFlagToSavePosibleMovements(i, x + i - y, board)) {
					coordinates = new int[2];
					coordinates[0] = i;
					coordinates[1] = x + i - y;
					posible_movements.add(new IntegerArray(coordinates));
					if (board.boxes[i][x + i - y].isThereAPiece()) {
						break;
					}
				} else {
					break;
				}
			} else {
				break;
			}
		}

		for (int i = y - 1; i >= 0; i -= 1) {
			if (x - i + y <= 7) {
				if (super.getFlagToSavePosibleMovements(i, x - i + y, board)) {
					coordinates = new int[2];
					coordinates[0] = i;
					coordinates[1] = x - i + y;
					posible_movements.add(new IntegerArray(coordinates));
					if (board.boxes[i][x - i + y].isThereAPiece()) {
						break;
					}
				} else {
					break;
				}
			} else {
				break;
			}
		}
		return posible_movements;
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
	public ArrayList getPosibleMovements (int y, int x, EightQueensGame board) {
		ArrayList<IntegerArray> posible_movements = new 
													<IntegerArray>ArrayList();
		int[] coordinates;

		for (int i = y + 1; i <= 7; i += 1) {
			if (x + i - y <= 7) {
				coordinates = new int[2];
				coordinates[0] = i;
				coordinates[1] = x + i - y;
				posible_movements.add(new IntegerArray(coordinates));
				if (board.boxes[i][x + i - y].isThereAPiece()) {
					break;
				}
			} else {
				break;
			}
		}

		for (int i = y + 1; i <= 7; i += 1) {
			if (x - i + y >= 0) {
				coordinates = new int[2];
				coordinates[0] = i;
				coordinates[1] = x - i + y;
				posible_movements.add(new IntegerArray(coordinates));
				if (board.boxes[i][x - i + y].isThereAPiece()) {
					break;
				}
			} else {
				break;
			}
		}

		for (int i = y - 1; i >= 0; i -= 1) {
			if (x + i - y >= 0) {
				coordinates = new int[2];
				coordinates[0] = i;
				coordinates[1] = x + i - y;
				posible_movements.add(new IntegerArray(coordinates));
				if (board.boxes[i][x + i - y].isThereAPiece()) {
					break;
				}
			} else {
				break;
			}
		}

		for (int i = y - 1; i >= 0; i -= 1) {
			if (x - i + y <= 7) {
				coordinates = new int[2];
				coordinates[0] = i;
				coordinates[1] = x - i + y;
				posible_movements.add(new IntegerArray(coordinates));
				if (board.boxes[i][x - i + y].isThereAPiece()) {
					break;
				}
			} else {
				break;
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
			System.out.print("♝");
		} else {
			System.out.print("♗");
		}
	}

}