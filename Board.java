/**
 * <p>Proyecto final ICC: Ajedrez</p>
 * 
 * Clase Board que representa un tablero de 8x8.
 * 
 * @author Eduardo Eder Vazquez Salcedo (edervs)
 * @date 29 de Noviembre del 2015
 * @version 1.0
 * @see <a href = "https://es.wikipedia.org/wiki/Ajedrez"/> para más información sobre el juego.
 *
 **/
import java.util.ArrayList;
import java.io.*;
public abstract class Board implements Serializable{

	// Contiene las casillas de todo el tablero.
	public Box[][] boxes;
	
	/**
	* Constructor que recibe todas las casillas del tablero.
	* 
	* @param boxes Matriz de casillas.
	*
	* @version 1.0
	**/
	public Board (Box[][] boxes) {
		this.boxes = boxes;
	}

	/**
	 * Imprime el tablero de juego.
	 *
	 * @version 1.0
	 */
	public abstract void printBoard();

	/**
	 * Da todos los posibles movimientos de todas las piezas del color del jugador enviado.
	 * 
	 * @param color Color de jugador que sacaran todos los posibles movimientos.
	 *
	 * @return lista de IntegerArray de todos las coordenadas de todos los posibles movimientos.
	 *
	 * @version 1.0
	 **/
	public abstract ArrayList<IntegerArray> giveAllPosibleMovements(boolean color);

	/**
	 * Da un arreglo de ints a partir de un string que contienes las coordenadas de tipo 'A-1'.
	 * 
	 * @param coordinate Coordenadas escritas como 'A-1'
	 *
	 * @return Arreglo de 2 ints que contiene las coordenadas convertidas a int.
	 *
	 * @version 1.0
	 **/
	public int[] giveCoordinates (String coordinate) {
		int[] coordinates = new int[2];
		if (coordinate.length() == 3) {
			coordinates[0] = this.changeLetterToNumber(coordinate.charAt(0), true);
			coordinates[1] = this.changeLetterToNumber(coordinate.charAt(2), false);
			return coordinates;
		}
		coordinates[0] = -1;
		coordinates[1] = -1;
		return coordinates;
	}

	/**
	 * Da un arreglo de ints a partir de dos coordenadas.
	 * 
	 * @param y Coordenada 'y'.
	 * @param x Coordenada 'x'.
	 *
	 * @return Un int[] con las coordenadas.
	 *
	 * @version 1.0
	 **/
	public int[] getIntArrayFromCoordinates (int y, int x) {
		int[] coordinates = new int[2];	
		coordinates[0] = y;
		coordinates[1] = x;
		return coordinates;
	}

	/**
	 * Convierte un valor char a su coordenada correspondiente en int.
	 * 
	 * @param letter Coordenada que puede ser un char con un dígito o una letra.
	 * @param flag Bandera para ver si el char debe de ser una letra o un digito.
	 *
	 * @return Valor int de su correspondiente coordenada.
	 *
	 * @version 1.0
	 **/
	public int changeLetterToNumber (char letter, boolean flag) {
		if (flag) {
			// Cambio de letra a int
			if (Character.getNumericValue(letter) >= 10 && Character.getNumericValue(letter) <= 19) {
				return Character.getNumericValue(letter) - 10;
			}
			return -1;
		} else {
			//Cmabio de digito a int
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
	 * Verifica si son iguales dos coordenadas enviadas.
	 * 
	 * @param crd1 Arreglo de ints de las primeras coordenadas.
	 * @param crd2 Arreglo de ints de las segundas coordenadas.
	 *
	 * @return boolean de si el son iguales las coordenadas.
	 *
	 * @version 1.0
	 **/
	public boolean areEqual2Coordinates (int[] crd1, int[] crd2) {
		return crd1[0] == crd2[0] && crd1[1] == crd2[1];
	}
}