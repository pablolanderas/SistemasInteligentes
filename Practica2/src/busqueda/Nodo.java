/**
 * Clase para Nodo de la busqueda
 */
package busqueda;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Ines
 * @version 2022.09.*
 */
public class Nodo<Estado,Accion> {
	// atributos
	private Estado estado; // estado
	private Nodo<Estado,Accion> padre; // nodo padre
	private Accion accion; // accion aplicada para llegar al estado desde el padre
	private double g; // coste
	
	// constructores
	/**
	 * Constructor de un nodo para un estado sin padre (raiz)
	 * @param e, el estado al que correspode el nodo
	 */
	public Nodo(Estado e){
		setEstado(e);
		setAccion(null);
		setPadre(null);
		setG(0,0); // g=0
	}
	

	/**
	 * Constructor de un nodo con padre
	 * @param e, el estado al que corresponde
	 * @param p, el padre (otro nodo)
	 * @param a, la Accion aplicada para llegar a este estado
	 * @param coste, el coste de pasar de p.estado a e
	 */
	public Nodo(Estado e, Nodo<Estado,Accion> p, Accion a, double coste){
		setEstado(e);
		setPadre(p);
		setAccion(a);
		setG(p.getG(),coste);
	}

	// observadores
	public Estado getEstado() {
		return estado;
	}
	
	public Nodo<Estado,Accion> getPadre() {
		return padre;
	}
	
	public Accion getAccion() {
		return accion;
	}
	
	public double getG(){
		return g;
	}
	
	// modificadores
	private void setEstado(Estado e) {
		estado = e;
	}
	public void setPadre(Nodo<Estado,Accion> p){
		padre = p;
	}
		
	public void setAccion(Accion a) {
		accion=a;
	}
	
	public void setG( double gPadre, double coste){
		g=gPadre+coste;
	}
	
	// otros
	/**
	 * toString()
	 * @return un string con la info del nodo
	 */
	@Override
	public String toString(){
		if (esRaiz())
			return "[ estado: " + getEstado().toString() + 
					" sin padre " + 
					"coste: " + getG() + "]";
		return "[ estado: " + getEstado().toString() + 
				" padre: " + getPadre().getEstado().toString() + 
				" accion:" + getAccion().toString() +
				" coste: " + getG() + "]";
	}
	
	/** equals() para poder utilizar contains() en contenedores de nodos
	 * @return cierto si ambos nodos corresponden al mismo estado
	 */
	@Override
	public boolean equals(Object otro){
		if(otro instanceof Nodo<?,?>)
			return getEstado().equals(((Nodo<?,?>)otro).getEstado());
		else
			return false;
	}
	
	/**
	 * hashCode() para poder utilizar contains() en contenedores de nodos
	 * @return el hashCode del estado
	 */
	@Override
	public int hashCode(){
		return getEstado().hashCode();
	}
	
	/**
	 * esRaiz()
	 * @return cierto si es nodo raiz, falso en otro caso
	 */
	public boolean esRaiz(){
		return padre==null;
	}
	
	/**
	 * caminoDesdeInicio
	 * @return el camino desde el inicio hasta el nodo actual
	 */
	public List<Nodo<Estado,Accion>> caminoDesdeInicio() {
		LinkedList<Nodo<Estado,Accion>> camino = new LinkedList<Nodo<Estado,Accion>>();
		Nodo<Estado,Accion> nodo = this;
		while(nodo != null) {
			camino.addFirst(nodo);
			nodo = nodo.padre;
		}
		return camino;
	}
}
