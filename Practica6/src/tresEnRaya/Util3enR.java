/**
 * Clase de utilidades para el 3 en raya:
 * - 
 */
package tresEnRaya;

/**
 * @author Ines
 * @version 2019.11
 */
public final class Util3enR {

	/**
	 * nadie puede instanciar esta clase
	 */
	private Util3enR() {}
	
	/** mascara que pasa de id de jugador (entero) a caracter (cruz o circulo)
	 * @param el, id del jugador
	 * @return el caracter 'X' si es el jugador 0 (o par), 'O' si es el jugador 1 (o impar)
	 */
	static public char maskId2Char(int el){
		if(el%2==0) return 'X';
		if(el%2==1) return 'O';
		return '-';
	}
	

	/** Metodo para formatear un tablero para mostrarlo luego por pantalla
	 * @param tablero, un array 3x3 con un tablero del 3 en raya
	 * @return un string para mostrar por pantalla el tablero
	 */
	static public String formatTablero(int[][] tablero) {
		if( tablero.length !=3 || tablero[0].length != 3) return formatTVacio();
		String fila0 = "|"+ maskId2Char(tablero[0][0])+"|"+maskId2Char(tablero[0][1])+"|"+maskId2Char(tablero[0][2])+"|"+"\n";
		String fila1 = "|"+ maskId2Char(tablero[1][0])+"|"+maskId2Char(tablero[1][1])+"|"+maskId2Char(tablero[1][2])+"|"+"\n";
		String fila2 = "|"+ maskId2Char(tablero[2][0])+"|"+maskId2Char(tablero[2][1])+"|"+maskId2Char(tablero[2][2])+"|";
		return "\n"+fila0+fila1+fila2;
	}

	/** Metodo para formatear un tablero vacio para mostrarlo luego por pantalla
	 * @return un string para mostrar por pantalla un tablero vacio
	 */
	private static String formatTVacio() {
		String fila = "|-|-|-|"+"\n";
		return "\n"+fila+fila+fila;
	}
	
	/** Metodo auxiliar para obtener el contrario de un jugador (usando ids)
	 * @param idJ el id de un jugador (0-par o 1-impar)
	 * @return 0 si idJ es 1 (o impar), 1 si idJ es 0 (o par)
	 */
	public static int contrario( int idJ ) {
		return (idJ + 1) % 2;
	}

}
