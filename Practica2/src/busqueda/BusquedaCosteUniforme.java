/**
 * Clase que realiza una busqueda de coste uniforme
 * busqueda general en grafos con frontera cola de prioridad ordenada segun g
 */
package busqueda;

import java.util.Iterator;
import java.util.List;

/**
 * @author Ines
 * @version 2023.09.*
 */

public class BusquedaCosteUniforme<Estado,Accion> extends Busqueda<Estado,Accion> {

	/**
	 * Constructor
	 * Instancia la frontera a una cola de prioridad ordenada por g()
	 * @param p, el problema para el que se va a realizar la busqueda
	 */
	public BusquedaCosteUniforme(Problema<Estado,Accion> p) {
		super(p);
		frontera = new FronteraPrioridad<Estado,Accion>( ); // cola de prioridad ordenada usando el comp
	}
	
	/**
	 * Metodo tratarRepetidos
	 * Se modifica el generico para que, si encuentra un nodo "repetido" en la frontera,
	 * en lugar de descartar el hijo (el mas nuevo), deje en la frontera el mejor de ambos nodos
	 * (segun el COSTE)
	 * @param lista de nodos hijos a tratar
	 */
	@Override
	protected void tratarRepetidos(List<Nodo<Estado,Accion>> hijos){
		/* INDICACIONES:
		 * - aqui hay que recorrer la lista de hijos para anadir los "interesantes" a la frontera
		 * - para comprobar si un hijo esta en explorados, basta utilizar el metodo Map.get, recordando que la clave del mapa es el estado
		 * - para comprobar si la frontera no contiene un nodo para el estado del hijo o si lo contiene pero es peor (en cuyo caso, el "peor" se borra),
		 * puede utilizarse un comparador de la clase ComparaNodos, que compara nodos en funcion del valor de Nodo.g
		 * y puede usarse un metodo auxiliar para recorrer la frontera si se estima oportuno
		 */
		Nodo<Estado,Accion> n;
		Iterator<Nodo<Estado,Accion>> iter;
		boolean parado;
		for (Nodo<Estado,Accion> nodo: hijos) {
			if (explorados.get(nodo.getEstado()) == null) {
				iter = frontera.frontera.iterator();
				parado = false;
				while (iter.hasNext()) {
					n = iter.next();
					if (n.getEstado().equals(nodo.getEstado())) {
						if (nodo.getG() < n.getG()) {
							frontera.frontera.remove(n);
							frontera.aniade(nodo);
						}
						parado = true;
						break;
					}
				}
				if (!parado) frontera.aniade(nodo);
			}
		}
		
	}// fin tratarRepetidos()
	

}
