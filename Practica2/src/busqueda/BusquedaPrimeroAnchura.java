/**
 * Clase que realiza busqueda primero en anchura:
 * busqueda general en grafos con frontera FIFO
 */
package busqueda;

/**
 * @author Domingo
 * @author Ines
 * @version 2015.10.08
 */
public class BusquedaPrimeroAnchura<Estado, Accion> extends Busqueda<Estado, Accion> {

	/**
	 * Constructor: instancia la frontera a contenedor FIFO
	 */
	public BusquedaPrimeroAnchura( Problema<Estado,Accion> p) {
		super(p);
		frontera = new FronteraFIFO<Estado, Accion>();
		
	}
	// el resto igual que en la busqueda general

}
