/**
 * Clase Juego3enRaya
 * implementa la Interfaz Juego para el 3 en raya
 * con los siguientes "genericos":
 * 	- Estado3enR, clase que representa un estado del juego 3 en raya (tablero 3x3, quizas con algunas fichas colocadas)
 *  - Accion3enR, clase que representa una posible accion del juego 3 en raya (colocar una ficha en una casilla)
 *  - Jugador3enR, clase que representa un jugador del 3 en raya (el que pone los circulos o el que pone las cruces)
 */
package tresEnRaya;

import java.util.LinkedList;
import java.util.List;

import adversarios.Juego;


/**
 * @author Ines
 * @version 2022.11
 */

public class Juego3enR implements Juego<Estado3enR,Accion3enR,Jugador3enR> {
	
	// los dos posibles jugadores para el 3 en raya no cambian, siempre son los mismos (X y O)
	public static final Jugador3enR j0 = new Jugador3enR(0); // jugador con 'X' (id 0)
	public static final Jugador3enR j1 = new Jugador3enR(1); // jugador con 'O' (id 1)

	// METODOS DE LA INTERFAZ
	/**
	 * @see adversarios.Juego#estadoInicial()
	 * getEstadoInicial, devuelve el estado inicial
	 * @return el estado correspondiente al tablero vacio y el turno de 'X'
	 */
	@Override
	public Estado3enR estadoInicial() {
		return new Estado3enR(); // tablero vacio
	}
	
	/**
	 * @see adversarios.Juego#jugador(Estado e)
	 * devuelve el jugador que tiene el turno actualmente (independientemente del estado que se pasa como parametro)
	 */
	@Override
	public Jugador3enR jugador(Estado3enR s) {
		return jugador(s.getJugadorQueMueve()); // devolvemos el jugador al que le toca mover en el estado
	}

	/**
	 * @see adversarios.Juego#acciones(Estado e)
	 * esencialmente, se devuelve una accion "colocar ficha en..." por cada casilla vacia en el estado s
	 */
	@Override
	public List<Accion3enR> acciones(Estado3enR s) {
		List<Accion3enR> listaA=new LinkedList<Accion3enR>();
		for(int i=0; i<3; i++) // recorremos todas las casillas buscando las vacias (donde pueden colocarse fichas)
			for(int j=0; j<3; j++){
				if( s.esCasillaVacia(i,j) ){ // casilla vacia: puede colocarse ficha ahi
					Accion3enR a = new Accion3enR(i,j);
					listaA.add(a);
				}
			}
		return listaA;
	}

	/**
	 * @see adversarios.Juego#aplicable( Accion a, Estado e )
	 * aplicable, comprueba si una accion es aplicable en un estado
	 * @param a, accion del 3 en raya (colocar una ficha en una casilla)
	 * @param e, un estado del 3 en raya (tablero con fichas colocadas)
	 * @return cierto si la casilla donde se quiere colocar la ficha esta vacia, falso en otro caso
	 */
	@Override
	public boolean aplicable(Accion3enR a, Estado3enR e) {
			return e.esCasillaVacia(a.getX(),a.getY());
	}

	/**
	 * @see adversarios.Juego#resultado( Estado s, Accion accion )
	 * resultado, devuelve el resultado de que un jugador aplique una accion en un estado dado
	 * @param e el estado actual
	 * @param j el jugador que tiene que realizar la accion
	 * @param a la accion que quiere realizar
	 * @return el estado que resulta de aplicar la accion (si no es aplicable, no se hace nada)
	 */
	@Override
	public Estado3enR resultado(Estado3enR s, Accion3enR a) {
		if( aplicable( a, s ) ){
			Estado3enR resul = new Estado3enR( s ); // copiamos el estado original
			resul.setCasilla(a.getX(), a.getY(), s.getTurnoDe()); // colocamos la ficha del jugador que tiene turno
			resul.pasaTurno(); // en el resultado el turno es del contrincante
			return resul;
		}
		return s;
	}

	/**
	 * @see adversarios.Juego#terminalTest( Estado e );  
	 * terminalTest, comprueba si un estado es terminal
	 * @param e, un estado del 3 en raya
	 * @return cierto si el estado es terminal, falso en otro caso
	 */
	@Override
	public boolean terminalTest(Estado3enR e) {
			return e.esTerminal();
	}
	
	/**
	 * jugador devuelve el Jugador3enR que corresponde al id que se pasa por parametro
	 * @param int id
	 * @return el Jugador3enR al que corresponde el id: j0 si id==0, j1 si id==1
	 */
	public Jugador3enR jugador( int id ) {
		if( id%2 == 0 )
			return j0;
		else
			return j1;
	}


	/**
	 * @see adversarios.Juego#utilidad( Estado e, Jugador j)
	 * utilidad, devuelve la utilidad de un estado para un jugador:
	 * +10 si el jugador gana, -10 si el jugador pierde, 0 si hay empate o si no es terminal
	 * @param e el estado actual
	 * @param j el jugador
	 * @param la utilidad  para el jugador del estado (si es terminal) o 0 en otro caso
	 */
	@Override
	public double utilidad(Estado3enR e, Jugador3enR j) {
		if ( e.esTerminal() ){
				int ganadorId = e.getGanador();
				if( ganadorId == -1 ) return 0.0; // no hay ganador, empate
				if ( ganadorId == j.getId() ) // el ganador es el jugador
					return 10.0;
				else return -10.0; // el ganador es el oponente a j
			}
		return 0.0;
	}
	
	

}
