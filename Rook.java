import java.util.ArrayList;
class Rook extends Piece {
	
	public Rook (boolean color) {
		super(color);
	}

	public ArrayList<IntegerArray> getPosibleMovements (int y, int x, Board board) {
		ArrayList<IntegerArray> posible_movements = new 
													<IntegerArray>ArrayList();
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

	public void printPiece () {
		if (this.color) {
			System.out.print("♜");
		} else {
			System.out.print("♖");
		}
	}
}