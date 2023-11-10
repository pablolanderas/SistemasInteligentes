package tresEnRaya;

import java.util.Scanner;

import adversarios.*;

/**
 * Tester para probar el minimax aplicado al 3 en raya
 * @author ines
 * @version 2021.11.*
 *
 */
public class Tester3enR {


	private static Scanner inputHumano;
	private static Scanner inputHumano2;

	/**
	 * @param args
	 * Metodo que lanza partidas de 3 en raya; pueden ser maquina contra maquina, maquina contra humano o humano contra maquina
	 */
	public static void main(String[] args) {
		// pueden comentarse cualquiera de las 3 partidas
		// para que juegue la maquina contra la maquina
		System.out.println("\nDemo, maquina contra maquina");
		lanzaMinimaxDemo();
		// para que juegue la maquina contra el humano (empieza la maquina)
		System.out.println("\nDemo, maquina contra humano");
		juegaPrimeroIA();
		// para que juegue el humano contra la maquina (empieza el humano)
		System.out.println("\nDemo, humano contra maquina");
		juegaPrimeroHumano();
	}


	/**
	 * Metodo para lanzar demo minimax (jugando computadora vs computadora)
	 * Va alternando turnos de dos ``jugadores'' no humanos o IAs
	 */
	private static void lanzaMinimaxDemo() {
		System.out.println("DEMO DEL MINI MAX\n");
		Juego3enR juego = new Juego3enR();
		Estado3enR actual = (Estado3enR) juego.estadoInicial();
		BusquedaA<Estado3enR,Accion3enR,Jugador3enR> busqueda = new BusqMinMax<Estado3enR,Accion3enR,Jugador3enR>( juego );
		while (!juego.terminalTest( actual )) {
			System.out.println( "\n Turno de " + juego.jugador( actual ) + "  ... ");
			Accion3enR accion = busqueda.decideJugada( actual );
			actual = (Estado3enR) juego.resultado( actual, accion );
			System.out.println(actual);
		}
		System.out.println("FIN DE LA DEMO DE MINI MAX");
	}

	/**
	 * Metodo para lanzar demo minimax, jugando computadora vs humano
	 * Va alternando turnos de la IA y del jugador humano, empezando por la IA
	 */
	private static void juegaPrimeroIA(){
		System.out.println("\nJUEGO DEL 3 EN RAYA, IA vs HUMANO... ?TE ATREVES?");
		Juego3enR juego = new Juego3enR();
		Estado3enR actual = (Estado3enR) juego.estadoInicial();
		System.out.println( "\nComenzamos " + actual );
		BusquedaA<Estado3enR,Accion3enR,Jugador3enR> busqueda = new BusqMinMax<Estado3enR,Accion3enR,Jugador3enR>( juego );
		inputHumano2 = new Scanner(System.in );
		while (!juego.terminalTest( actual )) {
			if( juego.jugador( actual ).getId()==0 ){ // turno de la IA
				Accion3enR accion = busqueda.decideJugada( actual );
				actual = juego.resultado( actual, accion );
				System.out.println("\nLa IA juega:\n " + actual);
			}
			else{ // turno del humano
				System.out.println("\n?Que decides?");
				boolean preguntar=true;
				do{
					System.out.println("Escribe separadas por un espacio las coordenadas x e y de la casilla donde quieres colocar ficha (la esquina superior izquierda es 0 0)");
					int x = inputHumano2.nextInt();
					int y = inputHumano2.nextInt();
					if( actual.esCasillaVacia(x, y) ){
						preguntar=false;
						Accion3enR accion = new Accion3enR(x,y);
						actual = juego.resultado( actual, accion );
						System.out.println("Resultado de tu decision:\n" + actual);
					}
				}while(preguntar);
			}
		}
		//inputHumano.close();
		System.out.println("\nFIN DEL JUEGO");
		int idGanador = actual.getGanador();
		if(  idGanador!= -1 ){
			Jugador3enR ganador = juego.jugador(idGanador);
			System.out.println("Ganador: " + ganador );
		}
		else
			System.out.println("Empate");

	}

	/**
	 * Metodo para lanzar demo minimax, jugando humano vs computadora 
	 * Va alternando turnos de la IA y del jugador humano, empezando por el humano
	 */

	private static void juegaPrimeroHumano(){
		System.out.println("\nJUEGO DEL 3 EN RAYA, HUMANO vs IA... ?TE ATREVES?");
		Juego3enR juego = new Juego3enR();
		Estado3enR actual = juego.estadoInicial();
		System.out.println( "\n Comenzamos " + actual );
		BusquedaA<Estado3enR,Accion3enR,Jugador3enR> busqueda = new BusqMinMax<Estado3enR,Accion3enR,Jugador3enR>( juego );
		inputHumano = new Scanner(System.in );
		while (!juego.terminalTest( actual )) {
			if( juego.jugador( actual ).getId()==1 ){ // turno de la IA
				Accion3enR accion = busqueda.decideJugada( actual );
				actual = juego.resultado( actual, accion );
				System.out.println("\nLa IA juega:\n" + actual);
			}
			else{ // turno del humano
				System.out.println("\nJuegas tu. ?Que decides?");
				boolean preguntar=true;
				do{
					System.out.println("Escribe separadas por un espacio las coordenadas x e y de la casilla donde quieres colocar ficha");
					int x = inputHumano.nextInt();
					int y = inputHumano.nextInt();
					if( actual.esCasillaVacia(x, y) ){
						preguntar=false;
						Accion3enR accion = new Accion3enR(x,y);
						actual = (Estado3enR) juego.resultado( actual, accion );
						System.out.println("Resultado de tu decision:\n" + actual);
					}
				}while(preguntar);
			}
		}
		//inputHumano.close();
		System.out.println("\nFIN DEL JUEGO");
		int idGanador = actual.getGanador();
		if(  idGanador!= -1 ){
			Jugador3enR ganador = juego.jugador(idGanador);
			System.out.println("Ganador: " + ganador );
		}
		else
			System.out.println("Empate");

	}
	
}
