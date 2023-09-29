/**
 * Frontera LIFO
 */
package busqueda;

import java.util.LinkedList;

/**
 * @author Domingo
 * @author Ines
 * @version 2015.10.08
 */
public class FronteraLIFO<Estado,Accion> extends Frontera<Estado,Accion> {
	
	/**
	 * Constructor
	 */
	public FronteraLIFO() {
		frontera = new LinkedList<Nodo<Estado,Accion>>();
	}

	@Override
	public Nodo<Estado,Accion> primero() {
		return ((LinkedList<Nodo<Estado,Accion>>) frontera).removeFirst();	// pop
	}

	@Override
	public void aniade(Nodo<Estado,Accion> n) {
		((LinkedList<Nodo<Estado,Accion>>) frontera).addFirst(n); // push(n)
		
	}

}
