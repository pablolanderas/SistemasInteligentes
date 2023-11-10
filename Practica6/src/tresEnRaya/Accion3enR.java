/**
 * Accion3enR, clase que representa una accion para el juego de las 3 en raya
 * modela la accion de colocar una pieza en una casilla del tablero
 */
package tresEnRaya;



/**
 * @author Ines
 * @version 2021.11.*
 *
 */
public class Accion3enR{
	
	final private int x, y; // coordenadas donde colocar ficha, han de estar entre 0 y 2

	/**
	 * Constructor a partir de unos valores de coordenadas
	 * (si no son válidos, por defecto x=0 e y=0)
	 * @param x, fila (de 0 a 2, de arriba a abajo)
	 * @param y, columna (de 0 a 2, de izquierda a derecha)
	 */
	public Accion3enR(int x, int y) {
		this.x = ( (x >=0) && (x<3) ? x : 0);
		this.y= ( (y >=0) && (y<3) ? y : 0);

	}
	
	// observadores y modificadores

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "coloca en [x=" + x + ", y=" + y + "]";
	}
	
	
	

}
