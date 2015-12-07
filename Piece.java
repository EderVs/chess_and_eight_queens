/**
 * Clase Piece que representa una pieza de ajedrez.
 * 
 * @author Eduardo Eder Vazquez Salcedo (edervs)
 * @date 28 de Noviembre del 2015
 * @version 1.0
 *
 **/
import java.io.*;
import java.util.ArrayList;
public abstract class Piece implements Serializable{
	
	// Color de la pieza que es representado por un boolean.
	// Cuando es true entonces el color es blanco, en cambio el color es negro.
	public boolean color;

	/**
	* Contructor que crea una pieza.
	*
	* @param color Color de la pieza.
	*
	* @version 1.0
	**/
	public Piece (boolean color) {
		this.color = color;
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
	public abstract ArrayList getPosibleMovements (int y,int x, ChessGame board);
	
	/**
	* Imprime la pieza.
	*
	* @version 1.0
	**/
	public abstract void printPiece ();

	/**
	* Verifica si es un movimiento valido para mover.
	*
	* @param from_y Coordenada 'y' de donde se encuentra la pieza que se quiere validar.
	* @param from_x Coordenada 'x' de donde se encuentra la pieza que se quiere validar.
	* @param to_y Coordenada 'y' de donde se quiere mover la pieza.
	* @param to_x Coordenada 'x' de donde se quiere mover la pieza.
	* @param color Color de la pieza que se quiere mover.
	* @param board Tablero del juego.
	*
	* @return boolean de si es valido el movimiento.
	*
	* @version 1.0
	**/
	public boolean isValidMovement (int from_y, int from_x, int to_y,
		                            int to_x, boolean color, ChessGame board) {
		ArrayList<IntegerArray> posible_movements = board.
			boxes[from_y][from_x].piece.getPosibleMovements(from_y,from_x, board);

		for (int i = 0; i < posible_movements.size(); i += 1) {
			if (posible_movements.get(i).getIntArray()[0] == to_y &&
				posible_movements.get(i).getIntArray()[1] == to_x) {
				return true;
			}
		}
		return false;
	}

	/**
	* Verifica hay una pieza en la coordenada mandada y es de diferente color a la de pieza.
	*
	* @param y Coordenada 'y' de la casilla.
	* @param x Coordenada 'x' de la casilla.
	* @param board Tablero del juego.
	*
	* @return boolean de si es valido el movimiento y es de diferente color al de la coordenada solicitada.
	*
	* @version 1.0
	**/
	public boolean getFlagToSavePosibleMovements (int y, int x, ChessGame board) {
		if (board.boxes[y][x].isThereAPiece()) {
			if (board.boxes[y][x].piece.color != this.color) {
				return true;
			}
			return false;
		} else {
			return true;
		}
	}
}