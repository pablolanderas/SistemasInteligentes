package busqueda;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que realiza una busqueda primero el mejor:
 * busqueda general en grafos con frontera cola de prioridad
 * Segun el criterio de ordenacion puede ser:
 * - g(): busqueda coste uniforme
 * - h(): busqueda primero el mejor voraz
 * - f(): busqueda A*
 * Para cualquiera de los 2 ultimos, por defecto h=0, aunque puede ser otro
 * Generaliza (y hace innecesaria) la clase BusquedaCosteUniforme
 * @author Ines
 * @version 2022.09.*
 */
public class BusquedaPrimeroMejor<Estado,Accion> extends Busqueda<Estado,Accion> {
	private Heuristico<Estado> h;// heuristico que se va a usar
	private Comparator<Nodo<Estado,Accion>> comp;// comparador para comparar nodos (elegir el mejor)

	/**
	 * Constructor
	 * Instancia la frontera a una cola de prioridad ordenada por g():
	 * BUSQUEDA DE COSTE UNIFORME
	 * @param p, el problema para el que se va a realizar la busqueda
	 */
	public BusquedaPrimeroMejor(Problema<Estado,Accion> p) {
		super(p);
		frontera = new FronteraPrioridad<Estado,Accion>(Criterio.g);
		h = new Heuristico<Estado>();// h trivial
		comp = new ComparaNodos<Estado,Accion>(Criterio.g);// comparar segun g
	}
	
	/**
	 * Constructor con criterio de prioridad c (g(), h() o f()) y heuristico h
	 * BUSQUEDA PRIMERO EL MEJOR:
	 * - si c es g(), busqueda de coste uniforme
	 * - si c es h(), busqueda primero el mejor voraz
	 * - si c es f(), A*
	 * @param p, el Problema a resolver
	 * @param c, el criterio de ordenacion de la frontera (puede ser g(), f() o h())
	 * @param h, la funcion heuristica a utilizar
	 */
	public BusquedaPrimeroMejor(Problema<Estado,Accion> p, Criterio c, Heuristico<Estado> h){
		super(p);
		frontera = new FronteraPrioridad<Estado,Accion>(c);
		this.h = h;
		comp = new ComparaNodos<Estado,Accion>(c);
	}
	
	/**
	 * Metodo inicia() modificado para calcular valor de H de nodo inicial
	 */
	@Override
	protected void inicia(){
		frontera.clear();
		Nodo<Estado,Accion> inicial = new Nodo<Estado,Accion>( prob.getInicio());
		// al crear el nodo no se calcula h, hay que hacerlo explicitamente
		inicial.setH(h.calculaH(inicial.getEstado()));
		frontera.aniade(inicial);
		explorados.clear();
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
		Nodo<Estado,Accion> n;
		Iterator<Nodo<Estado,Accion>> iter;
		boolean parado;
		for (Nodo<Estado,Accion> nodo: hijos) {
			nodo.setH(h.calculaH(nodo.getEstado()));
			if (explorados.get(nodo.getEstado()) == null) {
				iter = frontera.frontera.iterator();
				parado = false;
				while (iter.hasNext() && !parado) {
					n = iter.next();
					if (n.getEstado().equals(nodo.getEstado())) {
						if (nodo.getG() < n.getG()) {
							frontera.frontera.remove(n);
							frontera.aniade(nodo);
						}
						parado = true;
					}
				}
				if (!parado) frontera.aniade(nodo);
			}
		}
		
	}
	

}
