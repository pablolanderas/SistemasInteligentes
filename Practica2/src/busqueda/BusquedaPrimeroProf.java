/**
 * 
 */
package busqueda;

/**
 * @author Domingo
 * @author Ines
 * @version 2015.10.08
 */

public class BusquedaPrimeroProf<Estado,Accion> extends Busqueda<Estado,Accion> {
	/**
	 * Constructor: instancia la frontera a contenedor LIFO
	 */
	public BusquedaPrimeroProf( Problema<Estado,Accion> p) {
		super(p);
		frontera = new FronteraLIFO<Estado,Accion>();
		}
// el resto igual que en la busqueda general
	}