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
		int c1 = Util.randomInt(parent1.length()); // crossover point
		int c2 = c1;
		while (c2 == c1) c2 = Util.randomInt(parent1.length()); // crossover point
		if (c2 < c1) {
			int t = c1;
			c1 = c2;
			c2 = t;
		}
		children.add(0, cross2(c1,c2,parent1,parent2));
		/*if( nchildren == 2 ) {
			children.add(1, cross2(c,parent2,parent1)); // exchange parent roles to produce 2nd child
		}*/		
		return children;
	}
	
	/**
	 * Operador de cruce: CRUCE EN DOS PUNTOS
	 * @param x, un cromosoma
	 * @param y, otro cromosoma
	 * @return el hijo de x e y
	 */
	private Individuo<A> cross2(int c1, int c2, Individuo<A> x, Individuo<A> y) {
		// n <- LENGTH(x);
				// c <- random number from 1 to n
				List<A> xChromosome = x.getRepresentation(); // chromosome x
				List<A> yChromosome = y.getRepresentation(); // chromosome y
				List<A> childChromosome = new ArrayList<A>(x.length()); // child chromosome
				// The first substring from the first parent, order and position
				int k;
				for (k= c1; k<c2; k++) childChromosome.add(k, xChromosome.get(k));
				// The remaining genes from the second parent, relative order
				k = 0;
				for (int i = 0; i<y.length(); i++) { // traverse y
					if (!childChromosome.contains(yChromosome.get(i))) {
						childChromosome.add(k, yChromosome.get(k));
						k++;
						if (k == c1) k = c2;
					}
				}
				Individuo<A> child = new Individuo<A>(childChromosome);
				return child;
	}
	


}
