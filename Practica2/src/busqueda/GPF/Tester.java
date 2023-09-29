/**
 * Clase de prueba minimal
 */
package busqueda.GPF;

/**
 * @author Ines
 * @version 2023.09.*
 */
import java.util.List;

import busqueda.Busqueda;
import busqueda.BusquedaCosteUniforme;
import busqueda.BusquedaPrimeroAnchura;
import busqueda.BusquedaPrimeroProf;
import busqueda.Nodo;
import busqueda.Problema;

public class Tester {


	public static void main( String[] args) {

		// Para probar todas las busquedas con un problema concreto (que se pasa como argumento al ejecutable)
		String nomFichProb = args[0];
		pruebaUnProblema(nomFichProb); 
		
	}
	
	private static void pruebaUnProblema( String nomFichProb ) {
		ProblemaGPF p = new ProblemaGPF( nomFichProb );
		p.muestraProblema();
		
		System.out.println("---------------------------");
		System.out.println("PRIMERO EN ANCHURA");
		Busqueda<EstadoGPF,AccionGPF> b = new BusquedaPrimeroAnchura<EstadoGPF,AccionGPF>( p );
		List<Nodo<EstadoGPF,AccionGPF>> sol=b.busqueda();
		muestraSol( sol, b, p );
		
		System.out.println("---------------------------");
		System.out.println("PRIMERO EN PROFUNDIDAD");
		Busqueda<EstadoGPF,AccionGPF> bProf = new BusquedaPrimeroProf<EstadoGPF,AccionGPF>( p );
		List<Nodo<EstadoGPF,AccionGPF>> solProf=bProf.busqueda();
		muestraSol( solProf, bProf, p );
		
		
		System.out.println("---------------------------");
		System.out.println("COSTE UNIFORME. Deberia encontrar solucion optima");
		Busqueda<EstadoGPF,AccionGPF> bCU = new BusquedaCosteUniforme<EstadoGPF,AccionGPF>( p );
		List<Nodo<EstadoGPF,AccionGPF>> solCU = bCU.busqueda();
		muestraSol( solCU, bCU, p );
		}

	private static void muestraSol ( List<Nodo<EstadoGPF,AccionGPF>> sol, Busqueda<EstadoGPF,AccionGPF> b, Problema<EstadoGPF,AccionGPF> p ){
		System.out.println("Camino solucion");
		for( Nodo<EstadoGPF,AccionGPF> n: sol ){
			if(n.esRaiz())
				System.out.print(n.getEstado()+" ");
			else
				System.out.print(n.getAccion() + " " + n.getEstado()+" ");
		}
		System.out.println("");
		System.out.println("Coste de la solucion: "+b.costeSolucion());
		System.out.println("Nodos explorados: "+b.nodosExplorados());
		System.out.println("Nodos en frontera: "+b.nodosEnFrontera());
		
		
	}
	

}
