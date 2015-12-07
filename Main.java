/**
 * <p>Proyecto final ICC: Ajedrez y Ocho Reinas</p>
 *
 * Clase Main que sirve como menu para entrar a cualquiera de los dos juegos.
 *
 * @author Eduardo Eder Vazquez Salcedo (edervs)
 * @date 29 de Noviembre del 2015
 * @version 1.0
 *
 **/
import java.util.Scanner;
import java.io.*;
public class Main {

	/**
	 * Valida si es la opcion enviada es correcta.
	 * 
	 * @param letter Char de la opcion enviada.
	 * 
	 * @return int de la opcion ya validada. Si returna un -1 quiere decir que no fue valida la opcion.
	 * 
	 * @version 1.0
	 **/
	public static int validateOption(char letter) {
		//Cambio de digito a int
		if (Character.getNumericValue(letter) >= 1 && Character.getNumericValue(letter) <= 3) {
			return Character.getNumericValue(letter) - 1;
		}
		return -1;
	}

	/**
	 * Ver si hay una partida guardada.
	 * 
	 * @return boolean diciendo si hay una partida guardada.
	 * 
	 * @version 1.0
	 **/
	public static boolean checkIfThereIsChessLoadedGame () throws Exception{
		ChessGame game;
		boolean answer = false;
		try {
			FileInputStream fis = new FileInputStream("game.bin");
			ObjectInputStream in = new ObjectInputStream(fis);
			answer = true;
		} catch (IOException e) {
		}
		return answer;
	}

	/**
	 * Carga el estado ultimo estado de la partida guardada.
	 * 
	 * @return Juego que es cargado.
	 * 
	 * @version 1.0
	 **/
	public static ChessGame loadChessGame () throws Exception{
		ChessGame game = new ChessGame();
		try {
			FileInputStream fis = new FileInputStream("game.bin");
			ObjectInputStream in = new ObjectInputStream(fis);
			game = (ChessGame)in.readObject();
		} catch (IOException e) {
			System.out.println(e);
		}
		return game;
	}

	public static void main(String[] args) {
		ChessGame chess;
		EightQueensGame eight_queens;
		Scanner scanner = new Scanner(System.in);
		// Variable que sera ocupada por opcion.
		char option = ' ';
		System.out.println("\n\n");
		System.out.println("===== Bienvenido al proyecto final de Eder! =====");
		do {
			do {
				// Imprimiendo el menu
				System.out.println();
				System.out.println("\n\nSelecciona el juego que quieres jugar:");
				System.out.println("       1) Ajedrez");
				System.out.println("       2) Ocho Reinas");
				System.out.println("       3) Salir\n\n");
				System.out.print("Opcion: ");
				option = scanner.nextLine().charAt(0);
				// Validando la opcion.
				if (validateOption(option) == -1) {
					System.out.println("Opcion no valida.");
				}
			// Validando la opcion.
			} while (validateOption(option) == -1);
			if (validateOption(option) + 1 == 1) {
				// Cargando juego de Ajedrez.
				try {
					if (checkIfThereIsChessLoadedGame()) {
						System.out.print("Hay un juego guardado, quieres cargarlo?(s/n): ");
						option = scanner.nextLine().charAt(0);
						if (option == 's') {
							chess = loadChessGame();
						} else {
							chess = new ChessGame();
						}
					} else {
						chess = new ChessGame();
					}
				} catch (Exception e) {
					chess = new ChessGame();
					System.out.println(e);
				}
				chess.startGame();
			} else if (validateOption(option) + 1 == 2) {
				// Cargando juego de Ocho Reinas.
				eight_queens = new EightQueensGame();
				eight_queens.startGame();
			} else {
				// Saliendo.
				break;
			}
			System.out.print("\n\nQuieres seguir jugando? (s/n): ");
			option = scanner.nextLine().charAt(0);
		} while (option == 's' || option == 'S');

		System.out.println("\n\n\nMade with <3 and Java.\n\n\n");
	}
}