/**
 * Clase IntegerArray que se utiliza para utilizar un arreglo de int como objeto.
 * 
 * @author Eduardo Eder Vazquez Salcedo (edervs)
 * @date 28 de Noviembre del 2015
 * @version 1.0
 *
 **/
import java.io.*;
public class IntegerArray implements Serializable{
	private int[] int_array;

	/**
	* Constructor para crear el objeto de un arreglo de int.
	*
	* @param int_array Arreglo de int.
	*
	* @version 1.0
	**/
	public IntegerArray (int[] int_array) {
		this.int_array = int_array;
	}

	/**
	* Obtiene el arreglo de int.
	*
	* @return El arreglo de int.
	*
	* @version 1.0
	**/
	public int[] getIntArray() {
		return int_array;
	}
}