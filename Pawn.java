import java.util.ArrayList;
class Pawn extends Piece {
	
	public Pawn (boolean color) {
		super(color);
	}

	public ArrayList getPosibleMovements (int y, int x, Board board) {
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


	public void printPiece () {
		if (this.color) {
			System.out.print("♟");
		} else {
			System.out.print("♙");
		}
	}
}