/**
 * Clase BusqMinMax que extiende la BusquedaA para realizar busqueda con adversarios utilizando el algoritmo minimax
 */
package adversarios;

import java.util.List;

/**
 * @param Estado, el tipo de los estados del juego
 * @param Accion, el tipo de las acciones aplicables en un estado (movimientos)
 * @param Jugador, el tipo de los jugadores
 * @author Ines y alumnos
 * @version 2023.11
 */
public class BusqMinMax<Estado,Accion,Jugador> extends BusquedaA<Estado,Accion,Jugador>{

	/**
	 * Constructor
	 * @param juego, el Juego a jugar
	 */
	public BusqMinMax( Juego<Estado,Accion,Jugador> juego ) {
		super( juego );
	}

	/** 
	 * Funcion que decir cual es el movimiento mas optimo a realizar
	 * @param e, el estado actual del juego
	 * @return la mejor accion a realizar
	 */
	public Accion decideJugada( Estado e ) {
		Jugador max = getJuego().jugador(e); // max es el jugador al que le toca mover en el estado e
		List<Accion> acciones = getJuego().acciones(e); // posibles acciones/movimientos en el estado e
		double arg = Double.NEGATIVE_INFINITY;
		Accion ret = null;
		double argTemp;
		for (Accion a : acciones) {
			argTemp = minValor(juego.resultado(e, a), max);
			if (argTemp >= arg) {
				ret = a;
				arg = argTemp;
			}
		}
		return ret;
	}
	
	/**
	 * Funcion que calcula el valor maximo posible
	 * @param e, estado actual
	 * @param max, jugador a maximiza el estado
	 * @return el valor
	 */
	private double maxValor(Estado e, Jugador max) {
		if (juego.terminalTest(e)) 
			return juego.utilidad(e, max);
		double v = Double.NEGATIVE_INFINITY;
		for (Accion a : juego.acciones(e)) 
			v = Math.max(v, minValor(juego.resultado(e, a), max));
		return v;
	}
	
	/**
	 * Funcion que calcula el valor minimo posible
	 * @param e, estado actual
	 * @param max, jugador a minimizar el estado
	 * @return el valor
	 */
	private double minValor(Estado e, Jugador max) {
		if (juego.terminalTest(e)) 
			return juego.utilidad(e, max);
		double v = Double.POSITIVE_INFINITY;
		for (Accion a : juego.acciones(e)) 
			v = Math.min(v, maxValor(juego.resultado(e, a), max));
		return v;
	}
}
