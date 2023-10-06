package busqueda.GPF;

public class HeuristicoMalo extends HeuristicoGPFManhattan{

	public HeuristicoMalo(ProblemaGPF prob) {
		super(prob);
		setMeta(prob.getMeta());
	}
	
	@Override
	public double calculaH(EstadoGPF e){
		return 0;
		
	}

}
