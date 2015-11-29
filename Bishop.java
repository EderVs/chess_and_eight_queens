import java.util.ArrayList;
class Bishop extends Piece {
	
	public Bishop (boolean color) {
		super(color);
	}

	public ArrayList getPosibleMovements (int y, int x, Board board) {
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

	public void printPiece () {
		if (this.color) {
			System.out.print("♝");
		} else {
			System.out.print("♗");
		}
	}

}