/**
 * Clase EightQueensGame que representa una el juego de Ocho Reinas.
 * 
 * @author Eduardo Eder Vazquez Salcedo (edervs)
 * @date 29 de Noviembre del 2015
 * @version 1.0
 * @see <a href = "https://es.wikipedia.org/wiki/Problema_de_las_ocho_reinas"/> para más información sobre el juego.
 *
 **/
import java.util.Scanner;
import java.util.ArrayList;
public class EightQueensGame extends Board{
	
	// Arreglo de coordenadas de reinas
	public IntegerArray[] queens_coordinates;

	/**
	 * Constructor que crea una juego de Ocho Reinas.
	 * 
	 * @version 1.0
	 **/
	public EightQueensGame () {
		// Llama al constructor padre para crear las casillas.
		super(new Box[8][8]);
		// Inicializa valores.
		queens_coordinates = new IntegerArray[8];

		for (int i = 0; i < 8; i += 1) {
			for (int j = 0; j < 8; j += 1) {
				// Crea cada casilla vacia.
				super.boxes[i][j] = new Box();
			}
		}
	}


	/**
	 * Pone una reina en el tablero.
	 * 
	 * @param coordinates Coordenadas donde se pondra.
	 * @param queens_number Numero de la reina que se creara.
	 * 
	 * @version 1.0
	 **/
	private void putQueenInBoard (int[] coordinates, int queens_number) {
		super.boxes[coordinates[1]][coordinates[0]].piece = new Queen(true);
		queens_coordinates[queens_number] = new IntegerArray(super.getIntArrayFromCoordinates(coordinates[1], coordinates[0]));
	}

	/**
	 * Inicia el juego de Ocho Reinas.
	 * 
	 * @version 1.0
	 **/
	public void startGame () {
		Scanner scanner = new Scanner(System.in);
		// Crea una Casilla generica.
		Box generic_box = new Box();
		// Coordenadas en formato String
		String to_string;
		// Coordenadas en formato int.
		int to_x, to_y;
		// Coordenadas para utilizar.
		int[] coordinates;
		System.out.println();
		System.out.println("============ Bienvenido al juego de Ocho Reinas de Eder! ============\n\n");
		for (int i = 0; i < 8; i += 1) {
			// Imprime el tablero.
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
				// Valida si se puede poner una Reina en las coordenadas que se dio.
				if (!generic_box.isValidBoxToPutPiece(to_y, to_x, this)) {
					System.out.println("Casilla no valida para poner la pieza.");
				}
			// Valida si se puede poner una Reina en las coordenadas que se dio.	
			} while (!generic_box.isValidBoxToPutPiece(to_y, to_x, this));
			// Pone la Reina en la coordenada que ya se valido con su respectio numero de Reina.
			this.putQueenInBoard(coordinates, i);
		}
		this.printBoard();
		System.out.println("\n");
		// Dice si el jugador gano o perdio.
		if (giveIfPlayerWon(this.giveAllPosibleMovements(true))) {
			System.out.println("------ Has Ganado! -----");
		} else {
			System.out.println("------ Has Perdido! -----");
		}
	}

	/**
	 * Verifica si se gano o perdio la partida.
	 * 
	 * @param all_posible_movements Todos los posibles movimientos.
	 * 
	 * @return boolean de si se gano o perdio.
	 * 
	 * @version 1.0
	 **/
	private boolean giveIfPlayerWon (ArrayList<IntegerArray> all_posible_movements) {
		for (int i = 0; i < all_posible_movements.size(); i += 1) {
			for (int j = 0; j < queens_coordinates.length; j += 1) {
				if (super.areEqual2Coordinates(all_posible_movements.get(i).getIntArray(), queens_coordinates[j].getIntArray())) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Da todos los posibles movimientos de las Reinas.
	 * 
	 * @param color Color de las reinas(true).
	 * 
	 * @return Todos los posibles movimientos en tipo ArrayList de IntegerArray.
	 * 
	 * @version 1.0
	 **/
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
	 * Imprime el tablero.
	 * 
	 * @version 1.0
	 **/
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