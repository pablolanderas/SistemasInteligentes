/**
 * Accion para el problema GPF (grid pathfinding)
 * Puede ser: ir a derecha|arriba|izquierda|abajo
 */
package busqueda.GPF;

/**
 * @author Ines
 * @version 2022.09.*
 */
public class AccionGPF {
	
	String accion;
	char tipo;
	
	public AccionGPF(char tipoAccion) {
		tipo = tipoAccion;
		if (tipoAccion == 'u') {
			this.accion = "Arriba";
		} else if (tipoAccion == 'd') {
			this.accion = "Abajo";
		} else if (tipoAccion == 'l') {
			this.accion = "Izquierda";
		} else if (tipoAccion == 'r') {
			this.accion = "Derecha";
		} else {
			/* throw new Exception("Accion no valida"); 
			 * Se deberia implementar una excepcion para los casos no esperados
			 */
		}
	}	
	
	// �IMPORTANTE! Tiene que implementar el m�todo toString() para poder imprimir la solucion
    // (es decir, la secuencia de acciones para llegar desde el estado inicial a la meta)	
	@Override
	public String toString() {
		return accion;
	}
	
	public char getTipo() {
		return tipo;
	}
}
