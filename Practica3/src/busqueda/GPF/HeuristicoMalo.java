package busqueda.GPF;

import java.util.Random;

public class HeuristicoMalo extends HeuristicoGPFManhattan{
	int tamano;

	public HeuristicoMalo(ProblemaGPF prob) {
		super(prob);
		tamano = prob.getGridNCols() * prob.getGridNFilas();
		setMeta(prob.getMeta());
	}
	
	/**
	 * Heuristico Manhattan
	 * @param un Estado e (del problema de grid pathfinding)
	 * @return el valor h(e), que es la distancia Manhattan al objetivo
	 */	
	@Override
	public double calculaH(EstadoGPF e){
		return new Random().nextInt(tamano)+tamano;		
	}

}
