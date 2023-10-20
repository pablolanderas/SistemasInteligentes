package ga;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.math.*;

/** Clase AlgoritmoGenetico
 * <pre>
 * Genetic algorithm; loosely based on the GA implementation at AIMA-Java
 * </pre>
 * 
 * @author Ciaran O'Reilly
 * @author Mike Stampone
 * @author Ruediger Lunde
 * @author Ines y alumnos
 * 
 * @param <A>
 *            the type of the alphabet used in the representation of the
 *            individuals in the population (this is to provide flexibility in
 *            terms of how a problem can be encoded).
 * @version 2022.10.*
 */
public class AlgoritmoGenetico<A> {
	// ATRIBUTOS
	// representacion de soluciones como cromosomas
	protected int individualLength; 		// longitud del cromosoma (cadena de genes o alelos)
	protected List<A> finiteAlphabet;		// alfabeto (carecteres que pueden usarse en cada alelo)
	// parametros del GA
	protected int popSize;					// tamanio de poblacion
	protected double mutationProbability;	// probabilidad de mutacion
	protected double crossoverProbability;	// probabilidad de cruce (si ==1, cruce incondicional)
	// traza
	protected Individuo<A> cromosomaSol;	// cromosoma correspondiente al mejor individuo encontrado
	protected int iterations;				// iteraciones del algoritmo (generaciones)
	protected long timeInMSec;				// tiempo consumido por el algoritmo
	private ArrayList<Double> FitnessBest = new ArrayList<Double>(); //Traza mejores fitness.
	private ArrayList<Double> FitnessAverage = new ArrayList<Double>(); //Traza media fitness
	
	// CONSTRUCTOR
	public AlgoritmoGenetico(int individualLength, Collection<A> finiteAlphabet, 
			double crossoverProbability, 
			double mutationProbability, int popSize ) {
		this.individualLength = individualLength;
		this.finiteAlphabet = new ArrayList<A>(finiteAlphabet);
		this.mutationProbability = mutationProbability;
		this.crossoverProbability = crossoverProbability;
		this.popSize = popSize;
		// las probabilidades tienen que estar en [0,1]
		assert(this.crossoverProbability >= 0.0 && this.crossoverProbability <= 1.0);
		assert(this.mutationProbability >= 0.0 && this.mutationProbability <= 1.0);
	}
	
	// METODOS DEL GA
	/**
	 * lanzaGA()
	 * version 1
	 * Metodo que lanza una ejecucion del GA:
	 * Generates an initial population, then starts the genetic algorithm and stops after a specified number of
	 * iterations.
	 * @param opG un OpGeneracion (para generar la poblacion inicial)
	 * @param maxIterations el numero maximo de iteraciones del GA (criterio de parada)
	 * @param opCross un opCruce (para realizar los cruces)
	 * @param opM un OpMutacion (para realizar la mutacion)
	 * @param opSel un OpSeleccion (para seleccionar individuos de la poblacion para cruzar)
	 * @param opDecod un OpDecodificacion (para transformar un individuo/cromosoma en una solucion del problema)
	 * @param opReemp un OpReemplazo (para seleccionar, a partir de la poblacion vieja y los hijos, quien pasa a la siguiente generacion)
	 * @return el mejor individuo de la ultima poblacion (la solucion)
	 */
	public Individuo<A> lanzaGA( OpGeneracion<A> opG, final int maxIterations, 
			OpCruce<A> opCross, OpMutacion<A> opM, OpSeleccion<A> opSel, OpDecodificacion<A> opDecod, OpReemplazo<A> opReemp) {
		
		
		List<Individuo<A>> pob = generateInitPopulation(opG, popSize);
		FitnessAverage.add(updateFitness(pob, opDecod));
		FitnessBest.add(retrieveBestIndividual(pob).getFitness());
		Individuo<A> hijo;
		for(int nGen = 0; nGen > maxIterations; nGen++ ) {
			List<Individuo<A>> nPob = new ArrayList<Individuo<A>>();
			
			for(int i = 0; i < pob.size(); i++) {
				Individuo<A> x = opSel.apply(pob);
				Individuo<A> y = opSel.apply(pob);
				if(Math.random() < crossoverProbability) {
					hijo = opCross.apply(1, x, y).get(0);
				} else {
					hijo = x;
				} 
				if(Math.random() < mutationProbability) {
					hijo = opM.apply(hijo);
				}
				nPob.add(hijo);
			}
			
			FitnessAverage.add(updateFitness(nPob, opDecod));
			FitnessBest.add(retrieveBestIndividual(nPob).getFitness());
			pob = opReemp.apply(pob, nPob);
		}
		return retrieveBestIndividual(pob);
	}
	


	
	// METODOS AUXILIARES
	
	/**
	 * generateInitPopulation()
	 * Metodo para generar la poblacion inicial
	 * @param opG el operador de generacion de individuos
	 * @param popSize el tamanio de la poblacion
	 * @return initPopulation la poblacion inicial
	 */
	protected List<Individuo<A>> generateInitPopulation(OpGeneracion<A> opG, int popSize) {
		List<Individuo<A>> initPopulation = new ArrayList<Individuo<A>>(popSize);
		for( int i=0; i<popSize; i++ ) {
			initPopulation.add( opG.apply() );
		}
		return initPopulation;
	}

	/**
	 * updateFitness
	 * Metodo para actualizar el fitness de los individuos de una poblacion
	 * @param population la poblacion
	 * @param fitness la funcion de fitness
	 * @return el fitness medio de la poblacion
	 */
	public double updateFitness(List<Individuo<A>> population, OpDecodificacion<A> opDecod ){
		// Determine all of the fitness values
		double avFitness = 0.0;
		for (int i = 0; i < population.size(); i++) {
			Individuo<A> ind = population.get(i);
			ind.setFitness( opDecod.apply(population.get(i)).getFitness() );
			avFitness = avFitness + ind.getFitness();
		}
		return (population.size()>0 ? avFitness/population.size() : 0.0);
	}
	
	/**
	 * computeAverageFitness
	 * Metodo para calcular el fitness medio de una poblacion
	 * @param population la poblacion
	 * @return el fitness medio de la poblacion
	 */
	public double computeAverageFitness(List<Individuo<A>> population ){
		double avFitness = 0.0;
		for (int i = 0; i < population.size(); i++) {
			Individuo<A> ind = population.get(i);
			avFitness = avFitness + ind.getFitness();
		}
		return (population.size()>0 ? avFitness/population.size() : 0.0);
	}

	/**
	 * retrieveBestIndividual
	 * Metodo para encontrar el mejor individuo de una poblacion
	 * @param population la poblacion
	 * @return el mejor individuo (mayor fitness)
	 */
	public Individuo<A> retrieveBestIndividual(List<Individuo<A>> population) {
		Individuo<A> popBestIndividual = population.get(0);
		double bestSoFarFValue = Double.NEGATIVE_INFINITY;
		for ( int i=0; i<population.size(); i++ ) {
			double fValue = population.get(i).getFitness();
			if (fValue > bestSoFarFValue) {
				popBestIndividual = population.get(i);
				bestSoFarFValue = fValue;
			}
		}
		return popBestIndividual;
	}

	/**
	 * clearInstrumentation
	 * Sets the population size and number of iterations to zero.
	 */
	public void clearInstrumentation() {
		updateMetrics(new ArrayList<Individuo<A>>(), 0, 0L);
	}


	/**
	 * Returns the population size.
	 * 
	 * @return the population size.
	 */
	public int getPopulationSize() {
		return popSize;
	}

	/**
	 * Returns the number of iterations of the genetic algorithm.
	 * 
	 * @return the number of iterations of the genetic algorithm.
	 */
	public int getIterations() {
		return iterations;
	}

	/**
	 * 
	 * @return the time in milliseconds that the genetic algorithm took.
	 */
	public long getTimeInMilliseconds() {
		return timeInMSec;
	}

	/**
	 * Updates statistic data collected during search.
	 * 
	 * @param itCount
	 *            the number of iterations.
	 * @param time
	 *            the time in milliseconds that the genetic algorithm took.
	 */
	protected void updateMetrics(Collection<Individuo<A>> population, int itCount, long time) {
		popSize = population.size();
		iterations = itCount;
		timeInMSec= time;
	}

	

}