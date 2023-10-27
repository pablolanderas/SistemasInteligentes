/**
 * Clase para resolver un CSP en el que los valores de las variables son del tipo V
 */
package busqCSP;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Ines y alumnos
 * @version 2023.10.*
 * @param <V> el tipo de los valores en los dominios
 */

public class CSPSolver<V> {

	// ATRIBUTOS
	// ninguno

	// METODOS

	/**
	 * Metodo para imprimir por pantalla quienes han realizado la practica
	 * 
	 * @return un String con nombre y apellidos de los autores de la practica
	 */
	public String autores() {
		String autores = null;
		// TODO cambiar la siguiente linea para que autores contenga el nombre de los
		// alumnos del grupo
		autores = "Nombre1 Apellidos1\n" + "Nombre2 Apellidos2\n";
		return autores;
	}

	/**
	 * Metodo resuelve, resuelve un CSP binario: 1) aplica AC-3 al CSP dado, para
	 * que resulte arco-consistente 2) si no queda inconsistente y tampoco una
	 * asignacion total, lanza MAC
	 * 
	 * @param csp, un problema CSP con los valores de las variables de tipo V
	 * @return una tabla hash con la solucion (una asignacion total y factible) (la
	 *         clave de la tabla es un String, el nombre de la variable; el valor es
	 *         el valor asignado a dicha variable (de tipo V))
	 */
	public Map<String, V> resuelve(CSP<V> csp) {
		Map<String, V> asignacion = new HashMap<String, V>(); // comenzamos con la asignacion vacia
		// 1) propagamos restricciones en los dominios iniciales para establecer
		// arco-consistencia
		if (AC3(csp)) {
			// 2) asignamos valores a las variables para las que solo queda un elemento en el dominio
			actualizaAsignacion(asignacion, csp);
			// 3) si todavia quedan dominios con mas de un valor, lanzamos busqCSP
			if (!asignacionTotal(asignacion, csp)) {
				asignacion = mac(asignacion, csp);
			}
		}
		if (asignacion != null && asignacionTotal(asignacion, csp))
			return asignacion;
		else
			return null;

	}

	/**
	 * Metodo AC-3 para todas las restricciones del CSP
	 * @param csp, el problema
	 * @return falso si se encuentra una inconsistencia, cierto en otro caso
	 * IMPORTANTE: Se inicia la lista de arcos de restriccion por comprobar 
	 * 			   a TODOS los arcos/restricciones del problema
	 */
	private boolean AC3( CSP<V> csp ){
		// TODO Completar
		return false;
	}

	
	/**
	 * Metodo AC-3 para las restricciones desde una variable
	 * @param csp, el problema
	 * @param var, la variable cuyo dominio acabamos de modificar
	 * @return falso si se encuentra una inconsistencia (un dominio queda vacio), cierto en otro caso
	 * IMPORTANTE: Se inicia la lista de arcos de restriccion por comprobar 
	 * 			   a los arcos con origen la variable var
	 */
	private boolean AC3 ( CSP<V> csp, String var ){
		// TODO Completar
		return false;
	}
	

	
	/**
	 * Metodo revisar, para revisar una restriccion y eliminar inconsistencias:
	 * recorre el dominio de la variable X eliminando los valores inconsistentes
	 * (aquellos para los que no existe un valor en el dominio de Y que cumpla la
	 * restriccion)
	 * 
	 * @param csp,  el problema CSP
	 * @param arco, el arco de restriccion (X,Y) que queremos revisar
	 * @return cierto si ha modificado el dominio de X, falso en otro caso !OJO!
	 *         Puede modificar el CSP (si borra valores del dominio de X)
	 */
	private boolean revisar(CSP<V> csp, ArcoRB<V> arco) {
		boolean revisado = false;
		// TODO Completar
		return revisado;
	}

	/**
	 * metodo mac, lanza el algoritmo MAC para resolver un problema CSP (modifica el
	 * CSP)
	 * 
	 * @param csp, el problema a resolver
	 * @return una solucion (asignacion total y factible) si la ha encontrado, false
	 *         en otro caso !OJO! En las llamadas recursivas hay que tener cuidado
	 *         de pasarle una copia del CSP, por si hay que hacer backtracking.
	 */
	private Map<String, V> mac(Map<String, V> asignacion, CSP<V> csp) {
		// TODO Completar
		// Optativo: seleccionar la variable a asignar utilizando el heurístico MRV
		return null;
	}

	// METODOS AUXILIARES
	/**
	 * modificador/actualizador de la asignacion: actualiza la asignacion segun los
	 * dominios, de manera que si queda un dominio con un unico valor, lo asigna a
	 * la variable correspondiente
	 */
	public void actualizaAsignacion(Map<String, V> asignacion, CSP<V> csp) {
		for (String var : csp.getVariables()) {
			Set<V> domVar = csp.getDominioDe(var);
			if (domVar.size() == 1) {// dominio con un solo valor
				List<V> domList = new LinkedList<V>(domVar);
				asignacion.put(var, domList.get(0));
			}
		}
	}

	/**
	 * mira si hay una asignacion total, es decir, el numero de variables asignadas
	 * es el mismo que el total de variables
	 * 
	 * @param asignacion la asignacion
	 * @param csp        un csp
	 * @return cierto si la asignacion es total
	 */
	public boolean asignacionTotal(Map<String, V> asignacion, CSP<V> csp) {
		return asignacion.size() == csp.getnVars();
	}

	/**
	 * Metodo para saber que variables no estan asignadas
	 * 
	 * @param asignacion, la asignacion actual
	 * @param csp         el csp al que se refiere la asignacion
	 * @return la lista de variables no asignadas
	 */
	public List<String> varsNoAsignadas(Map<String, V> asignacion, CSP<V> csp) {
		List<String> varsNoAsig = new LinkedList<String>();
		Set<String> todasVars = csp.getVariables(); // para recorrer variables
		for (String x : todasVars) {
			if (asignacion.get(x) == null) { // x no tiene valor asignado
				varsNoAsig.add(x);
			}
		}
		return varsNoAsig;
	}

	/**
	 * modificador de la asignacion para una unica variable asignaValorA asigna un
	 * valor a la variable var, modificando la asignacion y dejando el dominio en el
	 * csp con solo esa variable
	 * 
	 * @param var,            la variable
	 * @param valor,          el valor a asignar
	 * @param copiaAsignacion la asignacion parcial hasta el momento
	 * @param csp             el csp a resolver
	 */
	public void asignaValorA(String var, V valor, Map<String, V> copiaAsignacion, CSP<V> csp) {
		Set<V> dominio = new HashSet<V>();
		dominio.add(valor);
		csp.setDominioDe(var, dominio);
		copiaAsignacion.put(var, valor);
	}

}
