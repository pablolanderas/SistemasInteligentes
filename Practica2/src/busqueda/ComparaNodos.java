/**
 * Comparador para nodos (en funci�n de un criterio dado, que puede ser g(), h() o f() (este es el valor por defecto))
 */
package busqueda;

import java.util.Comparator;

/**
 * @author Ines
 * @version 2017.10.*
 *
 */
public class ComparaNodos<Estado,Accion> implements Comparator<Nodo<Estado,Accion>> {


	/**
	 * @return numero negativo si n1<n2; positivo si n1>n2 y 0 si n1==n2 segun el COSTE g;
	 */
	@Override
	public int compare(Nodo<Estado,Accion> n1, Nodo<Estado,Accion> n2) {
		return (int) Math.signum(n1.getG() - n2.getG());
	}
}
