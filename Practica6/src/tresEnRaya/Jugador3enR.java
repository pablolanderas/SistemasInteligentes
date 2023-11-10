/**
 * Jugador3enRaya, implementa la interfaz Jugador para el juego de las 3 en raya
 * puede ser el jugador 0 (cruces) o 1 (circulos)
 */
package tresEnRaya;


/**
 * @author Ines
 * @version 2019.11.*
 *
 */
public class Jugador3enR {
	

	final protected int id; // id del jugador, puede ser 0 (cruz X) o 1 (circulo O); una vez construido, no cambia

	/**
	 * Constructor por defecto: al jugador le damos el id 0
	 */
	public Jugador3enR() {
		id = 0; // por defecto, es el jugador 0
	}
	
	/**
	 * Constructor con id
	 * @param id (debe ser 0 o 1)
	 */
	public Jugador3enR( int id ){
		this.id=id%2; // si id es par, es jugador 0, si id es impar, es jugador 1
	}

	/**
	 * Observador
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	/**
	 * toString(), sobrecargado para que devuelva la "ficha" (X o O) correspondiente al jugador
	 */
	public String toString() {
		return Character.toString(Util3enR.maskId2Char(this.id));
	}


	/**
	 * equals()
	 * Dos jugadores de 3 en raya son iguales si tienen el mismo id (si juegan con la misma ficha)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Jugador3enR) {
			Jugador3enR other = (Jugador3enR) obj;
			if (id == other.id) {
				return true;
			}
		}
		return false; // obj no es un Jugador3enR o su id es distinto
	}

	
	/**
	 * hashCode(), el del id
	 */
	@Override
	public int hashCode() {
		return id;
	}

	

}
