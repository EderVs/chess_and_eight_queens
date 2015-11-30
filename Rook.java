/**
 * Clase Rook que representa una pieza de Torre.
 * 
 * @author Eduardo Eder Vazquez Salcedo (edervs)
 * @date 28 de Noviembre del 2015
 * @version 1.0
 *
 **/
import java.util.ArrayList;
class Rook extends Piece {
	
	/**
	 * Constructor que crea una Rook con color.
	 * 
	 * @param color Color de la Rook.
	 *
	 * @version 1.0
	 **/
	public Rook (boolean color) {
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
		ArrayList<IntegerArray> posible_movements = new <IntegerArray>ArrayList();
		int[] coordinates;

		for (int i = y + 1; i <= 7; i += 1) {
			if (super.getFlagToSavePosibleMovements(i, x, board)) {
				coordinates = new int[2];
				coordinates[0] = i;
				coordinates[1] = x;
				posible_movements.add(new IntegerArray(coordinates));
				if (board.boxes[i][x].isThereAPiece()) {
					break;
				}
			} else {
				break;
			}
		}

		for (int i = y - 1; i >= 0; i -= 1) {
			if (super.getFlagToSavePosibleMovements(i, x, board)) {
				coordinates = new int[2];
				coordinates[0] = i;
				coordinates[1] = x;
				posible_movements.add(new IntegerArray(coordinates));
				if (board.boxes[i][x].isThereAPiece()) {
					break;
				}
			} else {
				break;
			}
		}

		for (int i = x + 1; i <= 7; i += 1) {
			if (super.getFlagToSavePosibleMovements(y, i, board)) {
				coordinates = new int[2];
				coordinates[0] = y;
				coordinates[1] = i;
				posible_movements.add(new IntegerArray(coordinates));
				if (board.boxes[y][i].isThereAPiece()) {
					break;
				}
			} else {
				break;
			}
		}

		for (int i = x - 1; i >= 0; i -= 1) {
			if (super.getFlagToSavePosibleMovements(y, i, board)) {
				coordinates = new int[2];
				coordinates[0] = y;
				coordinates[1] = i;
				posible_movements.add(new IntegerArray(coordinates));
				if (board.boxes[y][i].isThereAPiece()) {
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
	public ArrayList<IntegerArray> getPosibleMovements (int y, int x, EightQueensGame board) {
		ArrayList<IntegerArray> posible_movements = new <IntegerArray>ArrayList();
		int[] coordinates;

		for (int i = y + 1; i <= 7; i += 1) {
			coordinates = new int[2];
			coordinates[0] = i;
			coordinates[1] = x;
			posible_movements.add(new IntegerArray(coordinates));
			if (board.boxes[i][x].isThereAPiece()) {
				break;
			}
		}

		for (int i = y - 1; i >= 0; i -= 1) {
			coordinates = new int[2];
			coordinates[0] = i;
			coordinates[1] = x;
			posible_movements.add(new IntegerArray(coordinates));
			if (board.boxes[i][x].isThereAPiece()) {
				break;
			}
		}

		for (int i = x + 1; i <= 7; i += 1) {
			coordinates = new int[2];
			coordinates[0] = y;
			coordinates[1] = i;
			posible_movements.add(new IntegerArray(coordinates));
			if (board.boxes[y][i].isThereAPiece()) {
				break;
			}
		}

		for (int i = x - 1; i >= 0; i -= 1) {
			coordinates = new int[2];
			coordinates[0] = y;
			coordinates[1] = i;
			posible_movements.add(new IntegerArray(coordinates));
			if (board.boxes[y][i].isThereAPiece()) {
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
			System.out.print("♜");
		} else {
			System.out.print("♖");
		}
	}
}