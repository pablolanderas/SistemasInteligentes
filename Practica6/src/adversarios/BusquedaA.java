/**
 * Clase abstracta que representa una busqueda con adversarios
 */
package adversarios;

/**
 * @param Estado, el tipo de los estados del juego
 * @param Accion, el tipo de las acciones aplicables en un estado (movimientos)
 * @param Jugador, el tipo de los jugadores
 * @author Ines
 * @version 2021.11
 *
 */
public abstract class BusquedaA<Estado,Accion,Jugador> {

	protected final Juego<Estado,Accion,Jugador> juego; // el juego (problema), sera siempre el mismo
	/**
	 * Constructor
	 * @param j, el Juego para el que se busca la accion
	 */
	public BusquedaA( Juego<Estado,Accion,Jugador> j ) {
		this.juego = j;
	}
	
	/**
	 * Metodo para decidir el mejor movimiento a realizar
	 * en un estado e
	 * Se concreta en cada subclase, segun que metodo de busqueda se use (minimax, poda alfa-beta, minimax con profundidad acotada...)
	 * @param e, el Estado
	 * @return la Accion a realizar
	 */
	public abstract Accion decideJugada( Estado e ); 
	
	
	// OBSERVADORES Y MODIFICADORES
	/**
	 * @return the juego
	 */
	public Juego<Estado,Accion,Jugador> getJuego() {
		return juego;
	}


}
