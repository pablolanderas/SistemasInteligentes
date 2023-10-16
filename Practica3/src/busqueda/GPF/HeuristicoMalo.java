package busqueda.GPF;

import java.util.Random;

public class HeuristicoMalo extends HeuristicoGPFManhattan{
	int tamano;
	private int[][] grid;
	private ProblemaGPF prob;

	public HeuristicoMalo(ProblemaGPF prob) {
		super(prob);
		this.prob = prob;
		tamano = prob.getGridNCols() * prob.getGridNFilas() * mayorValor();
		
		setMeta(prob.getMeta());
	}
	
	private int mayorValor() {
		int mayor = 0;
		this.grid = prob.getGrid();
		for ( int i=0; i<prob.getGridNFilas(); i++ ){
			for( int j=0; j<prob.getGridNCols(); j++ )
			if (mayor < grid[i][j]) {
				mayor = grid[i][j];
			}
		}
		return mayor;
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
