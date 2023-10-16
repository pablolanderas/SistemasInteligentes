package busqueda.GPF;
/**
 * Clase de prueba minimal
 * @author Ines
 * @version 2021.10.*
 */
import java.util.List;
import java.util.Random;

import busqueda.Busqueda;
//import busqueda.BusquedaCosteUniforme;
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
		pruebaUnProblema("prob10x10-0.txt");
		pruebaUnProblema("prob10x10-1.txt");
		pruebaUnProblema("prob10x10-2.txt");
		pruebaUnProblema("prob10x10-3.txt");
		pruebaUnProblema("prob10x10-4.txt");
		pruebaUnProblema("prob10x10-5.txt");
		pruebaUnProblema("prob10x10-6.txt");
		pruebaUnProblema("prob10x10-7.txt");
		pruebaUnProblema("prob10x10-8.txt");
		pruebaUnProblema("prob10x10-9.txt");
		pruebaUnProblema("prob10x15-0.txt");
		pruebaUnProblema("prob10x15-1.txt");
		pruebaUnProblema("prob10x15-2.txt");
		pruebaUnProblema("prob10x15-3.txt");
		pruebaUnProblema("prob10x15-4.txt");
		pruebaUnProblema("prob10x15-5.txt");
		pruebaUnProblema("prob10x15-6.txt");
		pruebaUnProblema("prob10x15-7.txt");
		pruebaUnProblema("prob10x15-8.txt");
		pruebaUnProblema("prob10x15-9.txt");
		pruebaUnProblema("prob15x15-0.txt");
		pruebaUnProblema("prob15x15-1.txt");
		pruebaUnProblema("prob15x15-2.txt");
		pruebaUnProblema("prob15x15-3.txt");
		pruebaUnProblema("prob15x15-4.txt");
		pruebaUnProblema("prob15x15-5.txt");
		pruebaUnProblema("prob15x15-6.txt");
		pruebaUnProblema("prob15x15-7.txt");
		pruebaUnProblema("prob15x15-8.txt");
		pruebaUnProblema("prob15x15-9.txt");		
	}
	
	
	/**
	 * Metodo para probar todas las busquedas (informadas y ciegas) sobre un problema concreto
	 * @param nomFichProb, el nombre del fichero con el enunciado del problema
	 */
	private static void pruebaUnProblema( String nomFichProb ) {
		ProblemaGPF p = new ProblemaGPF( nomFichProb );
		p.muestraProblema();

		Heuristico<EstadoGPF> h0 = new Heuristico<EstadoGPF>();
		Heuristico<EstadoGPF> hM = new HeuristicoGPFManhattan( p );
		Heuristico<EstadoGPF> hN = new HeuristicoMalo( p );
		
		System.out.print(nomFichProb + "	");
		
		Busqueda<EstadoGPF,AccionGPF> bGBFM = new BusquedaPrimeroMejor<EstadoGPF,AccionGPF>(p);
		List<Nodo<EstadoGPF,AccionGPF>> solGBFM = bGBFM.busqueda();
		muestraSol( solGBFM, bGBFM, p );
		
		Busqueda<EstadoGPF,AccionGPF> bAStarM = new BusquedaPrimeroMejor<EstadoGPF,AccionGPF>( p, Criterio.f, hM );
		List<Nodo<EstadoGPF,AccionGPF>> solAStarM = bAStarM.busqueda();
		muestraSol( solAStarM, bAStarM, p );
		
		Busqueda<EstadoGPF,AccionGPF> bAStarN = new BusquedaPrimeroMejor<EstadoGPF,AccionGPF>( p, Criterio.f, hN );
		List<Nodo<EstadoGPF,AccionGPF>> solAStarN = bAStarN.busqueda();
		muestraSol( solAStarN, bAStarN, p );
		
		System.out.println();
		
	}

		

	/**
	 * Metodo para mostrar una solucion por pantalla
	 * @param sol, la solucion (la rama del arbol de busqueda correspondiente al camino entre el inicio y la meta)
	 * @param b, la busqueda realizada (para saber numero de nodos expandidos, en frontera...)
	 * @param p, el problema a resolver
	 */
	private static void muestraSol ( List<Nodo<EstadoGPF,AccionGPF>> sol, Busqueda<EstadoGPF,AccionGPF> b, Problema<EstadoGPF,AccionGPF> p ){
		if( sol==null ) // para evitar excepciones por punteros nulos
			System.out.print("No se ha encontrado solucion		");
		else 
			System.out.print(b.nodosExplorados()+"	"+(b.nodosExplorados()+b.nodosEnFrontera())+"	"+b.costeSolucion()+"	");		
	}
	
} // Numero de nodos explorados | Numero total de nodos jenerados | Coste final
