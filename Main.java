import java.util.Scanner;
public class Main {

	public static int validateOption(char letter) {
		//Cambio de digito a int
		if (Character.getNumericValue(letter) >= 1 && Character.getNumericValue(letter) <= 3) {
			return Character.getNumericValue(letter) - 1;
		}
		return -1;
	}

	public static void main(String[] args) {
		ChessGame chess;
		EightQueensGame eight_queens;
		Scanner scanner = new Scanner(System.in);
		char option = ' ';
		System.out.println("\n\n");
		System.out.println("===== Bienvenido al proyecto final de Eder! =====");
		do {
			do {
				System.out.println();
				System.out.println("\n\nSelecciona el juego que quieres jugar:");
				System.out.println("       1) Ajedrez");
				System.out.println("       2) Ocho Reinas");
				System.out.println("       3) Salir\n\n");
				System.out.print("Opcion: ");
				option = scanner.nextLine().charAt(0);
				if (validateOption(option) == -1) {
					System.out.println("Opcion no valida.");
				}
			} while (validateOption(option) == -1);
			if (validateOption(option) + 1 == 1) {
				chess = new ChessGame();
				chess.startGame();
			} else if (validateOption(option) + 1 == 2) {
				eight_queens = new EightQueensGame();
				eight_queens.startGame();
			} else {
				break;
			}
			System.out.print("\n\nQuieres seguir jugando? (s/n): ");
			option = scanner.nextLine().charAt(0);
		} while (option == 's' || option == 'S');

		System.out.println("\n\n\nMade with <3 and Java.\n\n\n");
	}
}