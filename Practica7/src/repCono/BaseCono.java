/**
 * Clase para representar una base de conocimiento
 */
package repCono;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Ines
 * @version 2019
 */
public class BaseCono {
	
	// ATRIBUTOS
	Set<ClausHorn> baseReglas; // conocimiento generico
	Set<ClausHorn> baseHechos; // conocimiento especifico

	// METODOS
	/**
	 * constructor por defecto
	 */
	public BaseCono() {
		baseReglas = new HashSet<ClausHorn>();
		baseHechos = new HashSet<ClausHorn>();
	}
	
	/**
	 * metodo para aniadir un hecho (una proposicion) a la base de conocimiento
	 * @param h el String que representa el hecho a aniadir
	 */
	public void aniade( String h ) {
		ClausHorn hecho =new ClausHorn(h);
		baseHechos.add(hecho);
	}
	
	/**
	 * metodo para aniadir un hecho o una regla (una clausula de Horn con cabeza) a la base de conocimiento
	 * @param c la clausula de Horn que representa el hecho o regla a aniadir
	 */
	public void aniade( ClausHorn c ) {
		if( c.esUnHecho())
			baseHechos.add(c);
		else
			baseReglas.add(c);
	}
	
	/**
	 * metodo que devuelve el numero de reglas en la base de conocimiento
	 * @return el numero de reglas
	 */
	public int getNumReglas() {
		return baseReglas.size();
	}
	
	/**
	 * metodo que devuelve el numero de hechos en la base de conocimiento
	 * @return el numero de hechos
	 */
	public int getNumHechos() {
		return baseHechos.size();
	}
	
	/**
	 * metodo que devuelve todos los hechos como una lista de clausulas de Horn
	 * @return la lista de clausulas que conforman la base de Hechos
	 */
	public List<ClausHorn> getBaseHechos(){
		return new LinkedList<ClausHorn>(this.baseHechos);
	}
	
	/**
	 * metodo que devuelve todos los hechos como una lista de variables proposicionales (String)
	 * @return la lista de hechos en la base de conocimiento (cada hecho es una variable proposicional, un String)
	 */
	public List<String> getListaHechos(){
		List<String> listaHechos = new LinkedList<String>();
		for( ClausHorn c: baseHechos ) {
			listaHechos.add(c.getConsecuente());
		}
		return listaHechos;
	}
	
	/**
	 * metodo que devuelve la base de reglas como un conjunto de clausulas de Horn
	 * @return el conjunto de clausulas que conforman la base de reglas
	 */
	public Set<ClausHorn> getBaseReglas(){
		return this.baseReglas;
	}

	
	/**
	 * metodo que devuelve todas las reglas que tienen una variable dada como consecuente
	 * @param p una variable proposicional
	 * @return la lista de reglas cuyo consecuente es p
	 */
	public List<ClausHorn> reglasConConsecuente( String p ){
		List<ClausHorn> lista = new LinkedList<ClausHorn>();
		for( ClausHorn c: baseReglas ) {
			if( c.getConsecuente().equals(p))
				lista.add(c);
		}
		return lista;
	}
	
	/**
	 * metodo para saber si un hecho es cierto en la base de conocimiento
	 * @param un String, el simbolo de variable proposicional que queremos saber si se cumple
	 */
	public boolean esCierto( String p ) {
		ClausHorn pclausula = new ClausHorn(p);
		return baseHechos.contains(pclausula);
	}

}
