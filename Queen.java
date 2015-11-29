import java.util.ArrayList;
class Queen extends Piece {

	public Queen (boolean color) {
		super(color);
	}

	public ArrayList getPosibleMovements (int y, int x, Board board) {
		ArrayList<IntegerArray> posible_movements = new 
													<IntegerArray>ArrayList();
		Bishop bishop = new Bishop(color);
		Rook rook = new Rook(color);
		posible_movements = bishop.getPosibleMovements(y, x, board);
		posible_movements.addAll(rook.getPosibleMovements(y, x, board));
		return posible_movements;
	}


	public void printPiece () {
		if (this.color) {
			System.out.print("♚");
		} else {
			System.out.print("♔");
		}
	}
}