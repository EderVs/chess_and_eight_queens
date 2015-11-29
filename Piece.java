import java.util.ArrayList;
public abstract class Piece {
	
	/**
	** Color de la pieza que es representado por un boolean.
	** Cuando is true entonces el color es blanco, en cambio el color es
	** negro.
	**/
	public boolean color;

	public Piece (boolean color) {
		this.color = color;
	}

	/**
	** Método para saber los posibles movimientos de la
	**/
	public abstract ArrayList getPosibleMovements (int y,int x, Board board);
	public abstract void printPiece ();

	public boolean isValidMovement (int from_y, int from_x, int to_y,
		                            int to_x, boolean color, Board board) {
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

	public boolean getFlagToSavePosibleMovements (int y, int x, Board board) {
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