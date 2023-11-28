/**
 * Clase para realizar inferencia en L0
 */
package repCono;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Ines y alumnos
 * @version 2023
 */
public class Inferencia {
	
	// ATRIBUTOS
	BaseCono BC; // base de conocimiento
	String ob;  // objetivo (puede no haber)
	HashMap<ClausHorn, Integer> cont = new HashMap<>();
	HashMap<String, Boolean> inferido = new HashMap<>();
	LinkedList<String> agenda = new LinkedList<String>();
	
	// pueden aniadirse atributos auxiliares si se estima oportuno
	

	/**
	 * Constructor a partir de una base de conocimiento; no hay objetivo
	 */
	public Inferencia( BaseCono BC ) {
		this.BC = BC;
		this.ob = "*"; // convenio para indicar que no hay objetivo
		
		for (int i = 0; i< BC.getNumHechos(); i++) {
			ClausHorn temp = BC.getBaseHechos().get(i);
			cont.put(temp, temp.getNumPremisas());
		}
		for (ClausHorn c: BC.getBaseReglas()) {
			cont.put(c, c.getNumPremisas());
		}
		
		for (String s: BC.getListaHechos()) {
			if (BC.esCierto(s)) {
			agenda.add(s);
			}
		}
		
		
	}
	
	/**
	 * Constructor a partir de una base de conocimiento y de un objetivo
	 */
	public Inferencia( BaseCono BC, String ob ) {
		this.BC = BC;
		this.ob = ob;
	}
	
	/**
	 * observador que nos dice si hay objetivo para realizar la inferencia o no
	 * @return
	 */
	public boolean hayObjetivo() {
		return (ob == null ? false : true);
	}
	
	/**
	 * metodo que lanza un proceso de encadenamiento hacia delante, tal y como hemos visto en clase
	 * @return cierto si se alcanza el objetivo, falso si no (o si no habia objetivo)
	 */
	public boolean encDelante( ) {
		
		while (!agenda.isEmpty()) {
			String p = agenda.pop();
			if (p.equals(ob)) {
				return true;
			}
			if (!inferido.containsKey(p)) {
				inferido.put(p, true);
				for (ClausHorn c: BC.getBaseReglas()) {
					if (c.tienePremisa(p)) {
						cont.put(c, cont.get(c).intValue() - 1);
					}
					if (cont.get(c) == 0) {
						agenda.add(c.getConsecuente());
					}
				}
			}
			
		}
		
		return false;
	}

	/**
	 * observador para consultar el conocimiento inferido tras un proceso de inferencia:
	 * esto incluye los hechos iniciales mas los que se han ido concluyendo durante el encadenamiento al
	 * aplicar Modus Ponens
	 * @return el conjunto de variables proposicionales que sabemos que son ciertas (hechos)
	 */
	public Set<String> getInferido() {
	    return inferido.keySet();	
	}
	
	// Pueden aniadirse metodos auxiliares si se estima oportuno

}
