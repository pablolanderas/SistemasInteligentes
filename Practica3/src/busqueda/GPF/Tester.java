package busqueda.GPF;
/**
 * Clase de prueba minimal
 * @author Ines
 * @version 2021.10.*
 */
import java.util.List;

import busqueda.Busqueda;
import busqueda.BusquedaPrimeroAnchura;
import busqueda.BusquedaPrimeroMejor;
import busqueda.BusquedaPrimeroProf;
import busqueda.Criterio;
import busqueda.Heuristico;
//import busqueda.HeuristicoGPFManhattan;
import busqueda.Nodo;
import busqueda.Problema;

public class Tester {


	public static void main( String[] argv) {

		// OPCION 1: Para probar todas las busquedas con un problema concreto
		pruebaUnProblema("p8x8.txt");
		pruebaUnProblema("prob10x10-0.txt");
		pruebaUnProblema("prob10x15-7.txt");
		
		// OPCION 2: Probar todas las busquedas con un conjunto de problemas y recopilar resultados relevantes
		// TODO esto hay que hacerlo (la alternativa es ir lanzando uno a uno y hacer corta/pega muchas veces)
	}
	
	
	/**
	 * Metodo para probar todas las busquedas (informadas y ciegas) sobre un problema concreto
	 * @param nomFichProb, el nombre del fichero con el enunciado del problema
	 */
	private static void pruebaUnProblema( String nomFichProb ) {
		// Problema a resolver
		ProblemaGPF p = new ProblemaGPF( nomFichProb );
		p.muestraProblema();

		// Creamos heuristicos
		Heuristico<EstadoGPF> h0 = new Heuristico<EstadoGPF>();
		Heuristico<EstadoGPF> hM = new HeuristicoGPFManhattan( p );
		// aqui podria aniadirse el heuristico malo a programar
		
		// Lanzamos diversas busquedas
		// se puede comentar o descomentar segun la búsqueda que quiera lanzarse
		
		// BUSQUEDA PRIMERO EL MEJOR VORAZ CON HEURISTICO MANHATTAN
		//System.out.println("---------------------------");
		//System.out.println("1o MEJOR VORAZ CON MANHATTAN. ");
		Busqueda<EstadoGPF,AccionGPF> bGBFM = new BusquedaPrimeroMejor<EstadoGPF,AccionGPF>( p, Criterio.h, hM );
		List<Nodo<EstadoGPF,AccionGPF>> solGBFM = bGBFM.busqueda();
		muestraSol( solGBFM, bGBFM, p );
		
		// BUSQUEDA A* CON HEURISTICO MANHATTAN
		//System.out.println("---------------------------");
		//System.out.println("A* CON MANHATTAN. Deberia encontrar solucion optima");
		Busqueda<EstadoGPF,AccionGPF> bAStarM = new BusquedaPrimeroMejor<EstadoGPF,AccionGPF>( p, Criterio.f, hM );
		List<Nodo<EstadoGPF,AccionGPF>> solAStarM = bAStarM.busqueda();
		muestraSol( solAStarM, bAStarM, p );
		
		System.out.println(nomFichProb);
		}
	
		

	/**
	 * Metodo para mostrar una solucion por pantalla
	 * @param sol, la solucion (la rama del arbol de busqueda correspondiente al camino entre el inicio y la meta)
	 * @param b, la busqueda realizada (para saber numero de nodos expandidos, en frontera...)
	 * @param p, el problema a resolver
	 */
	private static void muestraSol ( List<Nodo<EstadoGPF,AccionGPF>> sol, Busqueda<EstadoGPF,AccionGPF> b, Problema<EstadoGPF,AccionGPF> p ){
		if( sol==null ) // para evitar excepciones por punteros nulos
			System.out.println("No se ha encontrado solucion");
		else 
			System.out.print(b.nodosExplorados()+"  "+(b.nodosExplorados()+b.nodosEnFrontera())+"  "+b.costeSolucion()+"    ");		
	}
	
} // Numero de nodos explorados | Numero total de nodos jenerados | Coste final
