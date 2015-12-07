/**
 * 
 * Clase ChessGame que representa el juego de ajedrez.
 * 
 * @author Eduardo Eder Vazquez Salcedo (edervs)
 * @date 29 de Noviembre del 2015
 * @version 1.1
 * @see <a href = "https://es.wikipedia.org/wiki/Ajedrez"/> para más información sobre el juego.
 *
 **/
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class ChessGame extends Board implements Serializable {
	// Contiene a las coordenadas de las piezas de los jugadores exceptuando el Rey.
	public ArrayList<IntegerArray> player1_pieces;
	public ArrayList<IntegerArray> player2_pieces;
	// Contiene a las piezas que ya fueron comidas por el otro jugador.
	public ArrayList<Piece> player1_dead_pieces;
	public ArrayList<Piece> player2_dead_pieces;
	// Contiene las coordenas del Rey de cada jugador.
	public int[] player1_king;
	public int[] player2_king;

	/**
	 * Constructor que crea un toda la simulacion para el juego con valores
	 * por default.
	 * 
	 * @version 1.0
	 *
	 **/
	public ChessGame () {
		// Inicianizando valores.
		super(new Box[8][8]);
		super.boxes = new Box[8][8];
		player1_pieces = new <IntegerArray>ArrayList();
		player2_pieces = new <IntegerArray>ArrayList();
		player1_dead_pieces = new <Piece>ArrayList();
		player2_dead_pieces = new <Piece>ArrayList();
		
		// Iniciando valores de casillas vacias.
		for (int i = 2; i < 6; i += 1) {
			for (int j = 0; j < 8; j += 1) {
				super.boxes[i][j] = new Box();
			}
		}
		// Asignacion de coordenadas a los reyes.
		this.player1_king = new int[2];
		this.player1_king[0] = 0;
		this.player1_king[1] = 4;
		for (int i = 0; i <= 1; i += 1) {
			for (int j = 0; j <= 7; j += 1) {
				if (i != 0 || j != 4) {
					this.player1_pieces.add(new IntegerArray(super.getIntArrayFromCoordinates(i,j)));
				}
			}
		}
		this.player2_king = new int[2];
		this.player2_king[0] = 7;
		this.player2_king[1] = 3;
		for (int i = 6; i <= 7; i += 1) {
			for (int j = 0; j <= 7; j += 1) {
				if (i != 7 || j != 3) {
					this.player2_pieces.add(new IntegerArray(super.getIntArrayFromCoordinates(i,j)));
				}
			}
		}

		// Piezas blancas.
		super.boxes[0][0] = new Box(new Rook(true));
		super.boxes[0][1] = new Box(new Knight(true));
		super.boxes[0][2] = new Box(new Bishop(true));
		super.boxes[0][3] = new Box(new Queen(true));
		super.boxes[0][4] = new Box(new King(true));
		super.boxes[0][5] = new Box(new Bishop(true));
		super.boxes[0][6] = new Box(new Knight(true));
		super.boxes[0][7] = new Box(new Rook(true));
		super.boxes[1][0] = new Box(new Pawn(true));
		super.boxes[1][1] = new Box(new Pawn(true));
		super.boxes[1][2] = new Box(new Pawn(true));
		super.boxes[1][3] = new Box(new Pawn(true));
		super.boxes[1][4] = new Box(new Pawn(true));
		super.boxes[1][5] = new Box(new Pawn(true));
		super.boxes[1][6] = new Box(new Pawn(true));
		super.boxes[1][7] = new Box(new Pawn(true));

		// Piezas negras.
		super.boxes[6][0] = new Box(new Pawn(false));
		super.boxes[6][1] = new Box(new Pawn(false));
		super.boxes[6][2] = new Box(new Pawn(false));
		super.boxes[6][3] = new Box(new Pawn(false));
		super.boxes[6][4] = new Box(new Pawn(false));
		super.boxes[6][5] = new Box(new Pawn(false));
		super.boxes[6][6] = new Box(new Pawn(false));
		super.boxes[6][7] = new Box(new Pawn(false));
		super.boxes[7][0] = new Box(new Rook(false));
		super.boxes[7][1] = new Box(new Knight(false));
		super.boxes[7][2] = new Box(new Bishop(false));
		super.boxes[7][3] = new Box(new King(false));
		super.boxes[7][4] = new Box(new Queen(false));
		super.boxes[7][5] = new Box(new Bishop(false));
		super.boxes[7][6] = new Box(new Knight(false));
		super.boxes[7][7] = new Box(new Rook(false));
	}

	/**
	 * Mueve un pieza dependiendo de las coordenadas a donde es dirijida.
	 * No puede moverse si la pieza no esta calculada para mover a la coordenada dirijida.
	 * 
	 * @param from_y Coordenada 'y' de donde viene la pieza.
	 * @param from_x Coordenada 'x' de donde viene la pieza.
	 * @param to_y Coordenada 'y' de donde va la pieza.
	 * @param to_x Coordenada 'x' de donde va la pieza.
	 * @param color Color de las piezas que se tienen que mover en este turno.
	 *
	 * @return Booleano que dice si se movio con exito.
	 *
	 * @version 1.0
	 */
	private boolean movePiece(int from_y, int from_x, int to_y, int to_x,
		                    boolean color) {
		Box generic_box = new Box();
		boolean flag_king = false;
		// Se verifica que la coordenada de la pieza que quieres mover sea valida.
		if (generic_box.isValidBoxToMove(from_y, from_x, color, this) &&
			super.boxes[from_y][from_x].piece.isValidMovement(
				from_y, from_x, to_y, to_x, color, this)) {
			//Se verifica si hay una casilla en la coordenada donde queremos mover la pieza para comerla.
			if (super.boxes[to_y][to_x].isThereAPiece()) {
				// Dependiendo del turno.
				if (color) {
					// Agrega una pieza muerta o comida.
					player1_dead_pieces.add(super.boxes[to_y][to_x].piece);
					// Valida si se mueve un rey para cambiar sus coordenadas guardadas en el atributo.
					if (super.areEqual2Coordinates(super.getIntArrayFromCoordinates(from_y, from_x), this.player1_king)) {
						this.player1_king[0] = to_y;
						this.player1_king[1] = to_x;
						flag_king = true;
					// Si no, comemos la pieza.
					} else {
						this.removeElementByCoordinates(to_y, to_x, false);
					}
				} else {
					// Agrega una pieza muerta o comida.
					player2_dead_pieces.add(super.boxes[to_y][to_x].piece);
					// Valida si se mueve un rey para cambiar sus coordenadas guardadas en el atributo.
					if (super.areEqual2Coordinates(super.getIntArrayFromCoordinates(from_y, from_x), this.player2_king)) {
						this.player2_king[0] = to_y;
						this.player2_king[1] = to_x;
						flag_king = true;
					// Si no, comemos la pieza.
					} else {
						this.removeElementByCoordinates(to_y, to_x, true);
					}
				}
			}

			// Dependiendo de turno se verifica si algun jugador se comio un rey.
			if (color) {
				if (super.areEqual2Coordinates(super.getIntArrayFromCoordinates(to_y, to_x), this.player2_king)) {
					this.player2_king[0] = -1;
					this.player2_king[1] = -1;
				}
				if (super.areEqual2Coordinates(super.getIntArrayFromCoordinates(to_y, to_x), this.player1_king)) {
					this.player1_king[0] = -1;
					this.player1_king[1] = -1;
				}
			}

			// Termina cambiando la casilla donde estaba la pieza a vacia y a donde va pone la pieza.
			super.boxes[to_y][to_x] = super.boxes[from_y][from_x];
			super.boxes[from_y][from_x] = new Box();
			// Verifica si no se movio un Rey
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
	 * Imprime el tablero de juego.
	 *
	 * @version 1.0
	 */
	public void printBoard() {
		char letter;
		System.out.print("   ");
		// Imprime las piezas ya comidas por el jugador 1.
		printDeadPieces(player1_dead_pieces);
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
		// IMprime las piezas ya comidas por el jugador 2.
		System.out.print("   ");
		printDeadPieces(player2_dead_pieces);
		System.out.println();
	}

	/**
	 * Empieza el juego de ajedrez.
	 * 
	 * @version 1.0
	 **/
	public void startGame() {
		// Objeto generico de Box para invocar algunos metodos.
		Box generic_box = new Box();
		Scanner scanner = new Scanner(System.in);
		// color que contiene el turno del jugador, si es true es turno del jugador 1, si no viceversa.
		// change_player que tiene un booleano para ver si se tiene que cambiar de jugador o si no repetir el proceso.
		boolean color = true, change_player, save_game;
		// Strings de las coordenadas de desde y a donde se quiere mover una pieza.
		String from_string, to_string;
		// ints de las coordenadas de desde y a donde se quiere mover una pieza.
		int from_x, from_y, to_x, to_y;
		// arreglo de ints para guardar coordenadas.
		int[] coordinates;
		System.out.println();
		System.out.println("======== Bienvenido al juego de Ajedrez de Eder! ========");
		do {
			change_player = true;
			save_game = false;
			for (int i = 1; i < 3; i += 1) {
				System.out.println();
			}
			// Imprime el tablero.
			this.printBoard();
			// Verifica si el jugador en turno está en jaque para dar una advertencia.
			if (this.isKingInCheck(color)) {
				System.out.println("  ----- Estas en jaque! -----");
			}
			System.out.println("");
			if (color) {
				System.out.println("Turno del jugador 1");
			} else {
				System.out.println("Turno del jugador 2");
			}
			do {
				if (color) {
					System.out.print("Coordenada de pieza a mover (ej. A-1) o J-J para guardar la partida: ");
				} else {
					System.out.println("Coordenada de pieza a mover (ej. A-1): ");
				}
				// Recibe las coordenadas de cual pieza se quiere mover.
				from_string = scanner.nextLine();
				coordinates = new int[2];
				// Convierte a arreglo de ints
				coordinates = super.giveCoordinates(from_string);
				from_x = coordinates[0];
				from_y = coordinates[1];
				// Valida si se puede mover una pieza en las coordenadas que se dio
				if (!generic_box.isValidBoxToMove(from_y, from_x, color, this)) {
					if (!color && from_x != 9) {
						System.out.println("Pieza no valida para mover.");
					}
				}
				if ((from_x == 9 || from_y == 9) && color) {
					save_game = true;
					this.saveGame();
					break;
				}
			} while (!generic_box.isValidBoxToMove(from_y, from_x, color, this));
			if (!(save_game)) {
				do {
					// Recibe las coordenadas de hacia donde se quiere mover la pieza.
					System.out.println();
					System.out.print("Coordenada a mover pieza (ej. 'A-2') o 'I-I' para escoger otra pieza: ");
					to_string = scanner.nextLine();
					coordinates = new int[2];
					// Convierte a arreglo de ints
					coordinates = super.giveCoordinates(to_string);
					to_x = coordinates[0];
					to_y = coordinates[1];
					// Valida si decidio cambiar a otra pieza para que no vaya el siguiente jugador.
					if (to_x == 8 || to_y == 8) {
						change_player = false;
						break;
					}
					// Valida si se puede mover hacia esa coordenada.
				} while (!this.movePiece(from_y, from_x, to_y, to_x, color));
			} else {
				break;
			}
			// Cambia el turno.
			if (change_player) {
				color = !color;
			}
		// Todo se realiza hasta que alguien se come a un Rey.
		} while (player1_king[0] != -1 && player2_king[0] != -1);

		//Imprime el jugador ganador y el perdedor.
		System.out.println("\n\n");
		if (save_game) {
			System.out.println(" --------- Juego guardado --------- \n\n\n");
		} else {
			if (!color) {
				System.out.println(" --------- Ganó el jugador 1! --------- \n\n\n");
				System.out.println(" --------- Perdedor jugador 2 :( --------- \n\n");
			} else {
				System.out.println(" --------- Ganó el jugador 2! --------- \n\n\n");
				System.out.println(" --------- Perdedor jugador 1 :( --------- \n\n");
			}
		}
	}

	/**
	 * Imprime las piezas que ya fueron comidas por el jugador.
	 * 
	 * @param list Lista de piezas ya comidas.
	 *
	 * @version 1.0
	 **/
	private void printDeadPieces (ArrayList<Piece> list) {
		for (int i = 0; i < list.size(); i += 1) {
			System.out.print(" ");
			list.get(i).printPiece();
		}
	}

	/**
	 * Agrega coordenadas de pieza a el usuario enviado mediante coordenadas separadas y no como arreglo de ints.
	 * 
	 * @param y Coordenada 'y'.
	 * @param x Coordenada 'x'.
	 * @param color Color del jugador en turno.
	 *
	 * @version 1.0
	 **/
	private void addElementByCoordinates(int y, int x, boolean color) {
		int[] coordinates = {y, x};
		if (color) {
			this.player1_pieces.add(new IntegerArray(coordinates));
		} else {
			this.player2_pieces.add(new IntegerArray(coordinates));
		}
	}

	/**
	 * Elimina coordenadas de pieza de usuario enviado mediante coordenadas separadas y no como arreglo de ints.
	 * 
	 * @param y Coordenada 'y'.
	 * @param x Coordenada 'x'.
	 * @param color Color del jugador en turno.
	 *
	 * @version 1.0
	 **/
	private void removeElementByCoordinates(int y, int x, boolean color) {
		int[] coordinates = {y, x};
		ArrayList<IntegerArray> current_all_coordinates;
		if (color) {
			current_all_coordinates = this.player1_pieces;
		} else {
			current_all_coordinates = this.player2_pieces;
		}
		for (int i = 0; i < current_all_coordinates.size(); i += 1) {
			if (super.areEqual2Coordinates(current_all_coordinates.get(i).getIntArray(), coordinates)) {
				current_all_coordinates.remove(i);
				break;
			}
		}
	}

	/**
	 * Verifica si el ajedrez en turno esta en jaque.
	 * 
	 * @param color Color de jugador en turno.
	 *
	 * @return boolean de si el rey esta en jaque.
	 *
	 * @version 1.0
	 **/
	private boolean isKingInCheck (boolean color) {
		ArrayList<IntegerArray> all_posible_movements = this.giveAllPosibleMovements(!color);
		int[] king_coordinates;
		if (color) {
			king_coordinates = player1_king;
		} else {
			king_coordinates = player2_king;
		}
		for (int i = 0; i < all_posible_movements.size(); i += 1) {
			if (super.areEqual2Coordinates(all_posible_movements.get(i).getIntArray(), king_coordinates)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Da todos los posibles movimientos de todas las piezas del color del jugador enviado.
	 * 
	 * @param color Color de jugador que sacaran todos los posibles movimientos.
	 *
	 * @return lista de IntegerArray de todos las coordenadas de todos los posibles movimientos.
	 *
	 * @version 1.0
	 **/
	public ArrayList<IntegerArray> giveAllPosibleMovements(boolean color) {
		ArrayList<IntegerArray> posible_movements = new ArrayList<IntegerArray>();
		int[] coordinates;
		if (color) {
			// Trae todos los movimientos de cada pieza blanca.
			for (int i = 0; i < player1_pieces.size(); i += 1) {
				coordinates = new int[2];
				coordinates = player1_pieces.get(i).getIntArray();
				posible_movements.addAll(super.boxes[coordinates[0]][coordinates[1]].piece.getPosibleMovements(coordinates[0], coordinates[1], this));
			}
		} else {
			// Trae todos los movimientos de cada pieza negra.
			for (int i = 0; i < player2_pieces.size(); i += 1) {
				coordinates = new int[2];
				coordinates = player2_pieces.get(i).getIntArray();
				posible_movements.addAll(super.boxes[coordinates[0]][coordinates[1]].piece.getPosibleMovements(coordinates[0], coordinates[1], this));
			}
		}
		return posible_movements;
	}

	/**
	 * Guarda el estado de la partida
	 * 
	 * @version 1.0
	 **/
	public void saveGame () {
		try {
			FileOutputStream fos = new FileOutputStream("game.bin");
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
		} catch (IOException e) {
			System.out.println(e);	
		}
	}
}