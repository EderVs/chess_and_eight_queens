import java.util.ArrayList;
import java.util.Scanner;
/**
 * <p>Proyecto final ICC: Ajedrez</p>
 * 
 * Clase Board que representa el tablero del juego de ajedrez.
 * 
 * @author Eduardo Eder Vazquez Salcedo (edervs)
 * @date 29 de Noviembre del 2015
 * @version 1.0
 * @see <a href = "https://es.wikipedia.org/wiki/Ajedrez"/> para más información sobre el juego.
 *
 **/
public class Board {

	//Contiene las casillas de todo el tablero
	public Box[][] boxes;
	//Contiene a las coordenadas de las piezas de los jugadores exceptuando el Rey.
	public ArrayList<IntegerArray> player1_pieces;
	public ArrayList<IntegerArray> player2_pieces;
	//Contiene a las piezas que ya fueron comidas por el oto jugador
	public ArrayList<Piece> player1_dead_pieces;
	public ArrayList<Piece> player2_dead_pieces;
	//Contiene las coordenas del Rey de cada jugador
	public int[] player1_king;
	public int[] player2_king;
	
	/**
	 * Constructor que crea un tablero a partir de la matriz de casillas que le es enviado como parámetro.
	 * 
	 * @param boxes Matriz de casillas con las que será generado el tablero.
	 * @param player1_pieces Lista de coordenadas donde están las piezas del jugador 1
	 * @param player2_pieces Lista de coordenadas donde están las piezas del jugador 2
	 * @param player1_dead_pieces Lista de piezas ya comidas por el usuario jugador 1
	 * @param player2_dead_pieces Lista de piezas ya comidas por el usuario jugador 2
	 * @version 0.0
	 *
	 **/
	public Board (Box[][] boxes, ArrayList<IntegerArray> player1_pieces, ArrayList<IntegerArray> player2_pieces,
		          ArrayList<Piece> player1_dead_pieces, ArrayList<Piece> player2_dead_pieces,
		          int[] player1_king, int{] player2_king}) {
		this.boxes = boxes;
		player1_dead_pieces = new <Piece>ArrayList();
		player2_dead_pieces = new <Piece>ArrayList();
		player1_pieces = new <IntegerArray>ArrayList();
		player2_pieces = new <IntegerArray>ArrayList();
	}

	/**
	 * Constructor que crea un nuevo tablero.
	 * 
	 * @version 1.0
	 *
	 **/
	public Board () {}//Inicianizando valores
		this.boxes = new Box[8][8];
		player1_pieces = new <IntegerArray>ArrayList();
		player2_pieces = new <IntegerArray>ArrayList();
		player1_dead_pieces = new <Piece>ArrayList();
		player2_dead_pieces = new <Piece>ArrayList();
		
		for (int i = 2; i < 6; i += 1) {
			for (int j = 0; j < 8; j += 1) {
				this.boxes[i][j] = new Box();
			}
		}
		this.player1_king = new int[2];
		this.player1_king[0] = 0;
		this.player1_king[1] = 4;
		for (int i = 0; i <= 1; i += 1) {
			for (int j = 0; j <= 7; j += 1) {
				if (i != 0 || j != 4) {
					this.player1_pieces.add(new IntegerArray(getIntArrayFromCoordinates(i,j)));
				}
			}
		}
		this.player2_king = new int[2];
		this.player2_king[0] = 7;
		this.player2_king[1] = 3;
		for (int i = 6; i <= 7; i += 1) {
			for (int j = 0; j <= 7; j += 1) {
				if (i != 7 || j != 3) {
					this.player2_pieces.add(new IntegerArray(getIntArrayFromCoordinates(i,j)));
				}
			}
		}
		//Piezas blancas
		this.boxes[0][0] = new Box(new Rook(true));
		this.boxes[0][1] = new Box(new Knight(true));
		this.boxes[0][2] = new Box(new Bishop(true));
		this.boxes[0][3] = new Box(new Queen(true));
		this.boxes[0][4] = new Box(new King(true));
		this.boxes[0][5] = new Box(new Bishop(true));
		this.boxes[0][6] = new Box(new Knight(true));
		this.boxes[0][7] = new Box(new Rook(true));
		this.boxes[1][0] = new Box(new Pawn(true));
		this.boxes[1][1] = new Box(new Pawn(true));
		this.boxes[1][2] = new Box(new Pawn(true));
		this.boxes[1][3] = new Box(new Pawn(true));
		this.boxes[1][4] = new Box(new Pawn(true));
		this.boxes[1][5] = new Box(new Pawn(true));
		this.boxes[1][6] = new Box(new Pawn(true));
		this.boxes[1][7] = new Box(new Pawn(true));

		//Piezas negras
		this.boxes[6][0] = new Box(new Pawn(false));
		this.boxes[6][1] = new Box(new Pawn(false));
		this.boxes[6][2] = new Box(new Pawn(false));
		this.boxes[6][3] = new Box(new Pawn(false));
		this.boxes[6][4] = new Box(new Pawn(false));
		this.boxes[6][5] = new Box(new Pawn(false));
		this.boxes[6][6] = new Box(new Pawn(false));
		this.boxes[6][7] = new Box(new Pawn(false));
		this.boxes[7][0] = new Box(new Rook(false));
		this.boxes[7][1] = new Box(new Knight(false));
		this.boxes[7][2] = new Box(new Bishop(false));
		this.boxes[7][3] = new Box(new King(false));
		this.boxes[7][4] = new Box(new Queen(false));
		this.boxes[7][5] = new Box(new Bishop(false));
		this.boxes[7][6] = new Box(new Knight(false));
		this.boxes[7][7] = new Box(new Rook(false));
	}

	public int[] getIntArrayFromCoordinates (int y, int x) {
		int[] coordinates = new int[2];	
		coordinates[0] = y;
		coordinates[1] = x;
		return coordinates;
	}

	/**
	** Mueve un pieza dependiendo de las coordenadas.
	**/
	public boolean movePiece(int from_y, int from_x, int to_y, int to_x,
		                    boolean color) {
		Box generic_box = new Box();
		boolean flag_king = false;
		if (generic_box.isValidBoxToMove(from_y, from_x, color, this) &&
			this.boxes[from_y][from_x].piece.isValidMovement(
				from_y, from_x, to_y, to_x, color, this)) {

			if (this.boxes[to_y][to_x].isThereAPiece()) {
				if (color) {
					player1_dead_pieces.add(this.boxes[to_y][to_x].piece);
					if (this.validateTwoCoordinates(this.getIntArrayFromCoordinates(from_y, from_x), this.player1_king)) {
						this.player1_king[0] = to_y;
						this.player1_king[1] = to_x;
						flag_king = true;
					} else {
						this.removeElementByCoordinates(to_y, to_x, false);
					}
				} else {
					player2_dead_pieces.add(this.boxes[to_y][to_x].piece);
					if (this.validateTwoCoordinates(this.getIntArrayFromCoordinates(from_y, from_x), this.player2_king)) {
						this.player2_king[0] = to_y;
						this.player2_king[1] = to_x;
						flag_king = true;
					} else {
						this.removeElementByCoordinates(to_y, to_x, true);
					}
				}
			}

			if (color) {
				if (this.validateTwoCoordinates(this.getIntArrayFromCoordinates(to_y, to_x), this.player2_king)) {
					this.player2_king[0] = -1;
					this.player2_king[1] = -1;
				}
				if (this.validateTwoCoordinates(this.getIntArrayFromCoordinates(to_y, to_x), this.player1_king)) {
					this.player1_king[0] = -1;
					this.player1_king[1] = -1;
				}
			}

			this.boxes[to_y][to_x] = this.boxes[from_y][from_x];
			this.boxes[from_y][from_x] = new Box();
			if (!flag_king) {
				this.removeElementByCoordinates(from_y, from_x, color);
				this.addElementByCoordinates(to_y, to_x, color);
			}
			return true;
		} else {
			System.out.println("Casilla invalida de mover");
			return false;
		}
	}

	/**
	** Imprime el tablero.
	**/
	public void printBoard() {
		char letter;
		System.out.print("   ");
		printDeadPieces(player1_dead_pieces);
		System.out.println();
		System.out.print("   ");
		for (int i = 0; i < 8; i += 1) {
			letter = Character.toChars(i + 65)[0];
			System.out.print(" " + letter + " ");
		}
		System.out.println("");
		for (int i = 0; i < 8; i += 1) {
			System.out.print(" " + (i + 1) + " ");
			for (int j = 0; j < 8; j += 1) {
				System.out.print(" ");
				boxes[i][j].printBox((j + i) % 2 == 0);
				System.out.print(" ");
			}
			System.out.print(" " + (i + 1) + " ");
			System.out.println("");
		}
		System.out.print("   ");
		for (int i = 0; i < 8; i += 1) {
			letter = Character.toChars(i + 65)[0];
			System.out.print(" " + letter + " ");
		}
		System.out.println();
		System.out.print("   ");
		printDeadPieces(player2_dead_pieces);
		System.out.println();
	}

	/**
	** Da un arreglo de ints a partir de un string
	**/
	public int[] giveCoordinates (String coordinate) {
		int[] coordinates = new int[2];
		if (coordinate.length() == 3) {
			coordinates[0] = changeLetterToNumber(coordinate.charAt(0), true);
			coordinates[1] = changeLetterToNumber(coordinate.charAt(2), false);
			return coordinates;
		}
		coordinates[0] = -1;
		coordinates[1] = -1;
		return coordinates;
	}

	/**
	** Cambia un char a int para que pueda ser trabajado como coordenada
	**/
	public int changeLetterToNumber (char letter, boolean flag) {
		if (flag) {
			if (Character.getNumericValue(letter) >= 10 && Character.getNumericValue(letter) <= 18) {
				return Character.getNumericValue(letter) - 10;
			}
			return -1;
		} else {
			if (Character.getNumericValue(letter) >= 1 && Character.getNumericValue(letter) <= 8) {
				return Character.getNumericValue(letter) - 1;
			}
			if (Character.getNumericValue(letter) == 18) {
				return Character.getNumericValue(letter) - 10;
			}
			return -1;
		}
	}

	/**
	** Inicia un juego
	**/
	public void startGame() {
		Box generic_box = new Box();
		Scanner scanner = new Scanner(System.in);
		boolean color = true, change_player;
		String from_string, to_string;
		int from_x, from_y, to_x, to_y;
		System.out.println("======== Welcome to Eder's Chess! ========");
		int[] coordinates;
		do {
			change_player = true;
			for (int i = 1; i < 3; i += 1) {
				System.out.println();
			}
			this.printBoard();
			/*if (color) {
				printArrayListIntegerArray(player1_pieces);
			} else {
				printArrayListIntegerArray(player2_pieces);
			}*/
			if (this.isKingInCheck(color)) {
				System.out.println("  ----- Estas en jaque! -----");
			}
			//this.printArrayListIntegerArray(this.giveAllPosibleMovements(color));
			System.out.println("");
			if (color) {
				System.out.println("Turno del jugador 1");
			} else {
				System.out.println("Turno del jugador 2");
			}
			do {
				System.out.print("Coordenada de pieza a mover (eg. A-1): ");
				from_string = scanner.nextLine();
				coordinates = new int[2];
				coordinates = giveCoordinates(from_string);
				from_x = coordinates[0];
				from_y = coordinates[1];
				if (!generic_box.isValidBoxToMove(from_y, from_x, color, this)) {
					System.out.println("Pieza no valida para mover.");
				}
			} while (!generic_box.isValidBoxToMove(from_y, from_x, color, this));
			do {
				System.out.println();
				System.out.print("Coordenada a mover pieza (eg. 'A-2') o 'I-I' para escoger otra pieza: ");
				to_string = scanner.nextLine();
				coordinates = new int[2];
				coordinates = giveCoordinates(to_string);
				to_x = coordinates[0];
				to_y = coordinates[1];
				if (to_x == 8 || to_y == 8) {
					change_player = false;
					break;
				}
			} while (!this.movePiece(from_y, from_x, to_y, to_x, color));
			if (change_player) {
				color = !color;
			}
		} while (player1_king[0] != -1 && player2_king[0] != -1);

		System.out.println("\n\n");
		if (!color) {
			System.out.println(" --------- Ganó el jugador 1! --------- \n\n\n");
			System.out.println(" --------- Perdedor jugador 2 :( --------- \n\n");
		} else {
			System.out.println(" --------- Ganó el jugador 2! --------- \n\n\n");
			System.out.println(" --------- Perdedor jugador 1 :( --------- \n\n");
		}

	}

	/**
	** Imprime las piezas que ya fueron comidas
	**/
	public void printDeadPieces (ArrayList<Piece> list) {
		for (int i = 0; i < list.size(); i += 1) {
			System.out.print(" ");
			list.get(i).printPiece();
		}
	}

	public boolean validateTwoCoordinates (int[] arr1, int[] arr2) {
		return arr1[0] == arr2[0] && arr1[1] == arr2[1];
	}

	public void printArrayListIntegerArray (ArrayList<IntegerArray> list) {
		for (int i = 0; i < list.size(); i += 1) {
			System.out.println("y: " + list.get(i).getIntArray()[0] + " x: " + list.get(i).getIntArray()[1]);
		}
	}

	public void addElementByCoordinates(int y, int x, boolean color) {
		int[] coordinates = {y, x};
		if (color) {
			this.player1_pieces.add(new IntegerArray(coordinates));
		} else {
			this.player2_pieces.add(new IntegerArray(coordinates));
		}
	}

	public void removeElementByCoordinates(int y, int x, boolean color) {
		int[] coordinates = {y, x};
		ArrayList<IntegerArray> current_all_coordinates;
		if (color) {
			current_all_coordinates = this.player1_pieces;
		} else {
			current_all_coordinates = this.player2_pieces;
		}
		for (int i = 0; i < current_all_coordinates.size(); i += 1) {
			if (this.validateTwoCoordinates(current_all_coordinates.get(i).getIntArray(), coordinates)) {
				current_all_coordinates.remove(i);
				break;
			}
		}
	}

	public boolean isKingInCheck (boolean color) {
		ArrayList<IntegerArray> all_posible_movements = this.giveAllPosibleMovements(!color);
		int[] king_coordinates;
		if (color) {
			king_coordinates = player1_king;
		} else {
			king_coordinates = player2_king;
		}
		for (int i = 0; i < all_posible_movements.size(); i += 1) {
			if (this.validateTwoCoordinates(all_posible_movements.get(i).getIntArray(), king_coordinates)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<IntegerArray> giveAllPosibleMovements(boolean color) {
		ArrayList<IntegerArray> posible_movements = new ArrayList<IntegerArray>();
		int[] coordinates;
		if (color) {
			for (int i = 0; i < player1_pieces.size(); i += 1) {
				coordinates = new int[2];
				coordinates = player1_pieces.get(i).getIntArray();
				posible_movements.addAll(this.boxes[coordinates[0]][coordinates[1]].piece.getPosibleMovements(coordinates[0], coordinates[1], this));
			}
		} else {
			for (int i = 0; i < player2_pieces.size(); i += 1) {
				coordinates = new int[2];
				coordinates = player2_pieces.get(i).getIntArray();
				posible_movements.addAll(this.boxes[coordinates[0]][coordinates[1]].piece.getPosibleMovements(coordinates[0], coordinates[1], this));
			}
		}
		return posible_movements;
	}


	public static void main(String[] args) {
		Board board = new Board();
		/* Production */
		board.startGame();
		/* Dev */
		//board.printBoard();
		//System.out.println(board.isKingInCheck(true));
		//board.printArrayListIntegerArray(board.giveAllPosibleMovements(true));
		//board.printArrayListIntegerArray(board.boxes[1][5].piece.posibleMovements(1,5,board));
	}
}