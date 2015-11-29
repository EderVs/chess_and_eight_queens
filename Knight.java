import java.util.ArrayList;
class Knight extends Piece {
	
	public Knight (boolean color) {
		super(color);
	}

	public ArrayList getPosibleMovements (int y, int x, Board board) {
		ArrayList<IntegerArray> posible_movements = new 
													<IntegerArray>ArrayList();
		int[] coordinates;
		int current_y;
		for (int i = 1; i <= 2; i += 1) {
			current_y = y - i;
			coordinates = new int[2];
			if (current_y >= 0) {
				if (x - (1 + 2-i) >= 0) {
					if (super.getFlagToSavePosibleMovements(current_y, x - (1 + 2-i), board)) {
						coordinates[0] = current_y;
						coordinates[1] = x - (1 + 2-i);
						posible_movements.add(new IntegerArray(coordinates));
					}
				}
				coordinates = new int[2];
				if (x + (1 + 2-i) <= 7) {
					if (super.getFlagToSavePosibleMovements(current_y, x + (1 + 2-i), board)) {
						coordinates[0] = current_y;
						coordinates[1] = x + (1 + 2-i);
						posible_movements.add(new IntegerArray(coordinates));
					}
				}
			}
			current_y = y + i;
			coordinates = new int[2];
			if (current_y <= 7) {
				if (x - (1 + 2-i) >= 0) {
					if (super.getFlagToSavePosibleMovements(current_y, x - (1 + 2-i), board)) {
						coordinates[0] = current_y;
						coordinates[1] = x - (1 + 2-i);
						posible_movements.add(new IntegerArray(coordinates));
					}
				}
				coordinates = new int[2];
				if (x + (1 + 2-i) <= 7) {
					if (super.getFlagToSavePosibleMovements(current_y, x + (1 + 2-i), board)) {
						coordinates[0] = current_y;
						coordinates[1] = x + (1 + 2-i);
						posible_movements.add(new IntegerArray(coordinates));
					}
				}
			}
		}
		return posible_movements;
	}

	public void printPiece () {
		if (this.color) {
			System.out.print("♞");
		} else {
			System.out.print("♘");
		}
	}
}