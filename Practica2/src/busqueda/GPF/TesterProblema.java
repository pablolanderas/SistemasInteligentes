/**
 * Tester para probar que se está programando bien la clase ProblemaGPF
 */
package busqueda.GPF;

import java.util.List;


/**
 * @author Ines
 * @version 2023.09.*
 *
 */
public class TesterProblema {



	/**
	 * @param args: el nombre del fichero del que se carga el problema 
	 */
	public static void main(String[] args) {
		String nomFichProb = args[0];
		ProblemaGPF p = new ProblemaGPF( nomFichProb );
		System.out.println("Problema cargado");
		p.muestraProblema();
		
		System.out.println("Estado inicial: " + p.getInicio() );
		System.out.println("Acciones aplicables en el estado inicial: ");
		List<AccionGPF> aplicables = p.acciones(p.getInicio());
		for( AccionGPF a: aplicables ) {
			System.out.println(" - " + a);
		}
		System.out.println("Sucesores del estado inicial");
		List<EstadoGPF> sucs = p.sucesores(p.getInicio());
		for( EstadoGPF e: sucs ) {
			System.out.println(" - " + e);
		}
		
		if( sucs.size()>0 ) {
			EstadoGPF sgte = sucs.get(0); // primer sucesor
			System.out.println("Estado siguiente " + sgte );
			System.out.println("Acciones aplicables en este estado ");
			aplicables = p.acciones(sgte);
			for( AccionGPF a: aplicables ) {
				System.out.println(" - " + a);
			}
			System.out.println("Sucesores del estado inicial");
			sucs = p.sucesores(sgte);
			for( EstadoGPF e: sucs ) {
				System.out.println(" - " + e);
			}
		}

	}

}
