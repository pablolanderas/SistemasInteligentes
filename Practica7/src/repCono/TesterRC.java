/**
 * 
 */
package repCono;

import java.util.Set;

/**
 * @author Ines
 * @version 2021
 */
public class TesterRC {


	/**
	 * @param args: tiene un unico argumento, el nombre del fichero con la base de conocimiento
	 */
	public static void main(String[] args) {
		
		BaseCono bc = new BaseCono(); // base de conocimiento inicialmente vacia
		String obj = "*"; // objetivo, inicialmente "*", representando que no hay objetivo
		// Intentamos leer la base de conocimiento del fichero
		try{
			obj=Util.leeBCDeFichero(args[0], bc );
			System.out.println("\nBase de conocimiento leida, hay objetivo = "+ !obj.contains("*"));
			System.out.println("Reglas:");
			for( ClausHorn c: bc.getBaseReglas())
				System.out.println(c);
			System.out.println("Hechos:");
			for( ClausHorn c: bc.getBaseHechos())
				System.out.println(c);
			System.out.println("Objetivo");
			System.out.println(obj);
		}
		catch(Exception e) {
			System.out.println("Algo fue mal leyendo la base de conocimiento");
		}
		
		// lanzamos el proceso de inferencia
		Inferencia inf = new Inferencia(bc, obj);
		System.out.println("\nLanzamos encadenamiento hacia delante");
		inf.encDelante();
		System.out.println("Conocimiento inferido");
		Set<String> inferido = inf.getInferido();
		for( String hecho: inferido )
			System.out.println(hecho);

	}

}
