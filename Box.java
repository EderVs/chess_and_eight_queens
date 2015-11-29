/**
** Clase Box que representa una casilla dentro del tablero.
**/
public class Box {
	public Piece piece;

	public Box (Piece piece) {
		this.piece = piece;
	}

	public Box () {
		this.piece = null;
	}

	/**
	** Regresa si hay una pieza en esa casilla
	**/
	public boolean isThereAPiece() {
		return piece != null;
	}

	public boolean isValidBoxToMove (int y, int x, boolean color, Board board) {
		if (y >= 0 && y <= 7 && x >= 0 && x <= 7) {
			if (board.boxes[y][x].isThereAPiece()) {
				return board.boxes[y][x].piece.color == color && board.boxes[y][x].piece.getPosibleMovements(y, x, board).size() > 0;
			}
		}
		return false;
	}

	/**
	** Imprime la casilla
	** Si no tiene casilla, se pondra como si fuera un color en la casilla
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