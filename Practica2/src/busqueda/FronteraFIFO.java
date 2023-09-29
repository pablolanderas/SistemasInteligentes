/**
 * Frontera FIFO
 */
package busqueda;

import java.util.LinkedList;

/**
 * @author Ines
 * @version 2017.10.*
 */

public class FronteraFIFO<Estado,Accion> extends Frontera<Estado,Accion> {
	
	/**
	 * Constructor
	 */
	public FronteraFIFO (){
		frontera = new LinkedList<Nodo<Estado,Accion>>();
	}

	/* (non-Javadoc)
	 * @see busqueda.Frontera#primero()
	 */
	@Override
	public Nodo<Estado,Accion> primero() {
		return ((LinkedList<Nodo<Estado,Accion>>) frontera). removeFirst();//poll();
	}

	/* (non-Javadoc)
	 * @see busqueda.Frontera#aniade(busqueda.Nodo)
	 */
	@Override
	public void aniade(Nodo<Estado,Accion> n) {
		((LinkedList<Nodo<Estado,Accion>>) frontera).addLast(n); // offer(n);

	}


}
