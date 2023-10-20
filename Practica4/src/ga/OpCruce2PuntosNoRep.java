package ga;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase OpCruce2PuntosNoRep
 * implements 2-point crossover with no repetitions (OX)
 * @param A el tipo de los elementos que componen un crosomosoma (genes)
 * @author Ines y alumnos
 * @version 2018.11.*
 */
public class OpCruce2PuntosNoRep<A> extends OpCruce<A> {


	/* (non-Javadoc)
	 * @see ga.OpCruce#apply(int, ga.Individuo, ga.Individuo)
	 */
	@Override
	public List<Individuo<A>> apply(int nchildren, Individuo<A> parent1, Individuo<A> parent2) {
		
		assert(nchildren>0 && nchildren<=2); // can generate 1 or 2 children
		List<Individuo<A>> children = new ArrayList<Individuo<A>>(nchildren); // list of children
		// TODO
		// Hay que programar el cruce en 2 puntos (¡sin repeticiones!)
		return children;	
	}
	


}
