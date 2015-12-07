/**
 * Clase Box que representa una casilla de un tablero.
 * 
 * @author Eduardo Eder Vazquez Salcedo (edervs)
 * @date 28 de Noviembre del 2015
 * @version 1.1
 *
 **/
import java.io.*;
public class Box implements Serializable{
	// Pieza dentro de la casilla, si no hay entonces sera null
	public Piece piece;

	/**
	* Contructor que crea una casilla y pone una pieza dentro.
	*
	* @param piece Pieza dentro de la casilla.
	*
	* @version 1.0
	**/
	public Box (Piece piece) {
		this.piece = piece;
	}

	/**
	* Contructor que crea una casilla y pone null a la pieza preestablecidamente.
	*
	* @version 1.0
	**/
	public Box () {
		this.piece = null;
	}

	/**
	* Regresa si hay una pieza dentro de la casilla.
	*
	* @return boolean de si hay una pieza dentro de la casilla.
	*
	* @version 1.0
	**/
	public boolean isThereAPiece() {
		return piece != null;
	}


	/**
	* Regresa si es valido mover la pieza que esta dentro de la casilla.
	*
	* @param y Coordenada 'y' de la coordenada que se quiere mover.
	* @param x Coordenada 'x' de la coordenada que se quiere mover.
	* @param color Color del jugador en turno.
	* @param board Tablero del juego.
	*
	* @return boolean de si es valido mover esa pieza.
	*
	* @version 1.0
	**/
	public boolean isValidBoxToMove (int y, int x, boolean color, ChessGame board) {
		if (y >= 0 && y <= 7 && x >= 0 && x <= 7) {
			if (board.boxes[y][x].isThereAPiece()) {
				return board.boxes[y][x].piece.color == color && board.boxes[y][x].piece.getPosibleMovements(y, x, board).size() > 0;
			}
		}
		return false;
	}

	/**
	* Regresa si es valido poner la pieza en la casilla deseada.
	*
	* @param y Coordenada 'y' de la coordenada donde se quiere poner.
	* @param x Coordenada 'x' de la coordenada donde se quiere poner.
	* @param board Tablero del juego.
	*
	* @return boolean de si es valido mover esa pieza.
	*
	* @version 1.0
	**/
	public boolean isValidBoxToPutPiece (int y, int x, EightQueensGame board) {
		if (y >= 0 && y <= 7 && x >= 0 && x <= 7) {
			return (!board.boxes[y][x].isThereAPiece());
		}
		return false;
	}

	/**
	 * Imprime la casilla. Si tiene una pieza la imprime, si no  se pondra como si fuera un diamante de color en la casilla.
	 *
	 * @param color Color del diamante de la casilla que debe de ir.
	 * 
	 **/
	public void printBox(boolean color) {
		if (!(this.isThereAPiece())) {
			if (color) {
				System.out.print("♢");
			} else {
				System.out.print("♦");
			}			
		} else {
			piece.printPiece();
		}
	}
}