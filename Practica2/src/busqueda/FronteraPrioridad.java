/**
 * Frontera ordenada por prioridad
 * (el criterio de comparacion de nodos puede ser g(), h() o f())
 */
package busqueda;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * @author ines
 * @version 2017.10.*
 */
public class FronteraPrioridad<Estado,Accion> extends Frontera<Estado,Accion> {
	/**
	 * Constructor por defecto
	 * Ordena la cola segun g()
	 */
	public FronteraPrioridad() {
		Comparator<Nodo<Estado,Accion>> comp = new ComparaNodos<Estado,Accion>();// comparar segun g
		frontera = new PriorityQueue<Nodo<Estado,Accion>>(10,comp); // cola de prioridad ordenada por g
	}
	

	/* (non-Javadoc)
	 * @see busqueda.Frontera#primero()
	 */
	@Override
	public Nodo<Estado,Accion> primero() {
		return frontera.poll();
	}

	/* (non-Javadoc)
	 * @see busqueda.Frontera#aniade(busqueda.Nodo)
	 */
	@Override
	public void aniade(Nodo<Estado,Accion> n) {
		frontera.offer(n);
	}
	
	public Nodo<Estado,Accion> obtenEstado(Estado estado) {
		Iterator<Nodo<Estado,Accion>> iter = frontera.iterator();
		Nodo<Estado,Accion> n;
		while (iter.hasNext()) {
			n = iter.next();
			if (n.getEstado().equals(estado)) {
				return n;
			}
		}
		return null;
	}

	


}

	

