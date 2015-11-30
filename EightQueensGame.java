import java.util.Scanner;
import java.util.ArrayList;
public class EightQueensGame extends Board{
	
	Queen[] queens;
	IntegerArray[] queens_coordinates;

	public EightQueensGame () {
		super(new Box[8][8]);
		queens = new Queen[8];
		queens_coordinates = new IntegerArray[8];

		for (int i = 0; i < 8; i += 1) {
			queens[i] = new Queen(true);
			for (int j = 0; j < 8; j += 1) {
				super.boxes[i][j] = new Box();
			}
		}
	}

	public void putQueenInBoard (int[] coordinates, int queens_number) {
		super.boxes[coordinates[1]][coordinates[0]].piece = new Queen(true);
		queens_coordinates[queens_number] = new IntegerArray(super.getIntArrayFromCoordinates(coordinates[1], coordinates[0]));
	}

	public void startGame () {
		Scanner scanner = new Scanner(System.in);
		Box generic_box = new Box();
		String to_string;
		int to_x, to_y;
		int[] coordinates;
		System.out.println();
		System.out.println("============ Bienvenido al juego de Ocho Reinas de Eder! ============\n\n");
		for (int i = 0; i < 8; i += 1) {
			this.printBoard();
			System.out.println();
			do {
				System.out.print("Coordenada de casilla a poner Reina " + (i + 1) + " (ej. A-1): ");
				// Recibe las coordenadas de donde se quiere poner una Reina.
				to_string = scanner.nextLine();
				coordinates = new int[2];
				// Convierte a arreglo de ints.
				coordinates = super.giveCoordinates(to_string);
				to_x = coordinates[0];
				to_y = coordinates[1];
				// Valida si se puede poner una reina en las coordenadas que se dio.
				if (!generic_box.isValidBoxToPutPiece(to_y, to_x, this)) {
					System.out.println("Casilla no valida para poner la pieza.");
				}
			} while (!generic_box.isValidBoxToPutPiece(to_y, to_x, this));
			this.putQueenInBoard(coordinates, i);
		}
		this.printBoard();
		System.out.println("\n");
		if (giveIfPlayerWon(this.giveAllPosibleMovements(true))) {
			System.out.println("------ Has Ganado! -----");
		} else {
			System.out.println("------ Has Perdido! -----");
		}
	}

	public boolean giveIfPlayerWon (ArrayList<IntegerArray> all_posible_movements) {
		for (int i = 0; i < all_posible_movements.size(); i += 1) {
			for (int j = 0; j < queens_coordinates.length; j += 1) {
				if (super.areEqual2Coordinates(all_posible_movements.get(i).getIntArray(), queens_coordinates[j].getIntArray())) {
					return false;
				}
			}
		}
		return true;
	}

	public ArrayList<IntegerArray> giveAllPosibleMovements (boolean color) {
		ArrayList<IntegerArray> posible_movements = new ArrayList<IntegerArray>();
		int[] coordinates;
		Queen generic_queen = new Queen(true);
		for (int i = 0; i < queens_coordinates.length; i += 1) {
			coordinates = new int[2];
			coordinates = queens_coordinates[i].getIntArray();
			posible_movements.addAll(generic_queen.getPosibleMovements(coordinates[0], coordinates[1], this));
		}
		return posible_movements;
	}

	/**
	 * Imprime una lista de IntegerArrays para usos de desarrollo.
	 * 
	 * @param list lista de IntegerArrays.
	 *
	 * @version 1.0
	 **/
	public void printArrayListIntegerArray (IntegerArray[] array) {
		for (int i = 0; i < array.length; i += 1) {
			System.out.println("y: " + array[i].getIntArray()[0] + " x: " + array[i].getIntArray()[1]);
		}
	}

	public void printArrayListIntegerArray (ArrayList<IntegerArray> array) {
		for (int i = 0; i < array.size(); i += 1) {
			System.out.println("y: " + array.get(i).getIntArray()[0] + " x: " + array.get(i).getIntArray()[1]);
		}
	}

	public void printBoard() {
		char letter;
		System.out.println();
		System.out.print("   ");
		// Imprime las letras de las coordenadas del tablero.
		for (int i = 0; i < 8; i += 1) {
			letter = Character.toChars(i + 65)[0];
			System.out.print(" " + letter + " ");
		}
		// Imprime las casillas.
		System.out.println("");
		for (int i = 0; i < 8; i += 1) {
			System.out.print(" " + (i + 1) + " ");
			for (int j = 0; j < 8; j += 1) {
				System.out.print(" ");
				super.boxes[i][j].printBox((j + i) % 2 == 0);
				System.out.print(" ");
			}
			System.out.print(" " + (i + 1) + " ");
			System.out.println("");
		}
		// Imprime las letras de las coordenadas del tablero.
		System.out.print("   ");
		for (int i = 0; i < 8; i += 1) {
			letter = Character.toChars(i + 65)[0];
			System.out.print(" " + letter + " ");
		}
		System.out.println();
	}

}