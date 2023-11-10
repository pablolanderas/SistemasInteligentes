/**
 * Interfaz para un Juego (problema de búsqueda con adversarios) a resolver
 * Genericos:
 * 	- Estado: clase que representa un estado del juego
 *  - Accion: clase que representa una accion a tomar en el juego
 *  - Jugador: clase que representa un jugador
 */
package adversarios;

import java.util.List;

/**
 * @param Estado, el tipo de los estados del juego
 * @param Accion, el tipo de las acciones aplicables en un estado (movimientos)
 * @param Jugador, el tipo de los jugadores
 * @author Ines
 * @version 2023.11.*
 *
 */
public interface Juego<Estado,Accion,Jugador> {
	
	// ESTADO INICIAL s_0: estado inicial, como esta el juego al iniciar la partida
	public Estado estadoInicial(); 
	
	// JUGADOR(s): jugador que tiene el turno, es decir, el que puede mover en el estado s
	public Jugador jugador( Estado s );
 
	//ACCIONES(s): lista de movimientos (acciones) permitidos en el estado s
	public List<Accion> acciones( Estado s );
	
	// comprueba si una accion es aplicable en un estado
	public boolean aplicable( Accion a, Estado e ); 

	 // RESULTADO(s,a): modelo de transicion que define el resultado de aplicar el movimiento (accion) a en el estado s
	public Estado resultado( Estado s, Accion accion );
	
	// TERMINAL-TEST(s): test de estado terminal
	public boolean terminalTest( Estado s );  
	
	// UTILIDAD(s,p) devuelve la utilidad del estado *terminal* s para el jugador p (decision perfecta)
	public double utilidad(Estado s, Jugador p); 
	
}
