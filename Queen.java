/**
 * Clase Queen que representa una pieza de Reina.
 * 
 * @author Eduardo Eder Vazquez Salcedo (edervs)
 * @date 29 de Noviembre del 2015
 * @version 2
 *
 **/
import java.io.*;
import java.util.ArrayList;
class Queen extends Piece implements Serializable{

	/**
	 * Constructor que crea una Reina con color.
	 * 
	 * @param color Color de la Reina.
	 *
	 * @version 1.0
	 **/
	public Queen (boolean color) {
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
		Bishop bishop = new Bishop(color);
		Rook rook = new Rook(color);
		posible_movements = bishop.getPosibleMovements(y, x, board);
		posible_movements.addAll(rook.getPosibleMovements(y, x, board));
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
		ArrayList<IntegerArray> posible_movements = new 
													<IntegerArray>ArrayList();
		Bishop bishop = new Bishop(color);
		Rook rook = new Rook(color);
		posible_movements = bishop.getPosibleMovements(y, x, board);
		posible_movements.addAll(rook.getPosibleMovements(y, x, board));
		return posible_movements;
	}

	/**
	* Imprime la pieza.
	*
	* @version 1.0
	**/
	public void printPiece () {
		if (this.color) {
			System.out.print("♚");
		} else {
			System.out.print("♔");
		}
	}
}