import java.util.ArrayList;
public class King extends Piece{
	
	public King (boolean color) {
		super(color);
	}

	public ArrayList<IntegerArray> getPosibleMovements (int y, int x,
													 Board board) {

		ArrayList<IntegerArray> posible_movements = new
		                            				<IntegerArray>ArrayList();
		int[] coordinates;
		boolean flag;
		for (int i = -1; i <= 1; i += 1) {
			for (int j = -1; j <= 1; j += 1) {
				if (i != 0 || j != 0) {
					if (y + i < 8 && y + i >= 0 && x + j < 8 && x + j >= 0) {
						flag = false;
						if (super.getFlagToSavePosibleMovements(y + i, x + j, board)) {
							coordinates = new int[2];
							coordinates[0] = y + i;
							coordinates[1] = x + j;
							posible_movements.add(new IntegerArray(coordinates));
						}
					}
				}
			}
		}
		return posible_movements;
	}

	public void printPiece () {
		if (this.color) {
			System.out.print("♛");
		} else {
			System.out.print("♕");
		}
	}
}