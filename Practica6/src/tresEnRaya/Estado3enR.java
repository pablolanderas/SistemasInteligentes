/**
 * Clase Estado3enR que modela un estado para el juego del 3 en raya
 * Representa una situacion concreta del tablero 3x3, posiblemente con piezas de 
 * ambos jugadores colocadas en algunas de las casillas, en la que el turno corresponde
 * a uno de los 2 jugadores
 */
package tresEnRaya;



/**
 * @author Ines
 * @version 2021.11.*
 *
 */
public class Estado3enR {

	// atributos que si dependen del estado concreto en el que estemos
	private int[][] tablero; 	// tablero 3x3
	private int nVacias; 		// numero de celdas vacias en el tablero
	private int turnoDe;		// id del jugador al que le toca mover (puede ser 0 o 1)
	private boolean esTerminal; // para guardar si es terminal
	private int ganador; 		// para guardar el jugador que gana (si es terminal)
	
	// CONSTRUCTORES
	/**
	 * Constructor: crea estado inicial correspondiente al tablero vacio con el turno de 0
	 */
	public Estado3enR( ) {
		// inicializamos tablero a tamanio 3x3 y valores -1 (representando vacias)
		tablero = new int[3][3];
		vaciaTablero();
		// iniciamos jugador actual a X (es decir, 0)
		setTurnoDe(0);
		// si el tablero esta vacio, no es terminal y no hay ganador
		setEsTerminal(false);
		setGanador(-1);
	}


	/**
	 * Constructor `copia'' partir de un tablero 3x3 y un id de jugador
	 * @param t, un array 3x3 de enteros (el tablero)
	 * @param idJ, un entero que indica el jugador al que le toca mover en este estado
	 */
	public Estado3enR( int[][] t, int idJ ) {
		tablero = new int[3][3];// inicializamos tablero a tamanio 3x3
		// copiamos valores de t y de paso contamos vacias
		int cont=0;
		if(t.length==3 && t[0].length==3){ // t se puede copiar
			for(int i=0; i<3; i++)
				for(int j=0; j<3; j++){
					tablero[i][j]=t[i][j];
					if ( t[i][j]==-1 ) cont++; // una vacia mas
				}
			setNVacias(cont);
			compruebaTerminal();
			setTurnoDe(idJ);
		}
		else // t no se puede copiar
			vaciaTablero();
	}
	
	/**
	 * Constructor copia a partir de estado
	 * @param e, el Estado3enR
	 */
	public Estado3enR( Estado3enR otro) {
		tablero = new int[3][3];
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				tablero[i][j]=otro.getCasilla(i, j);
		setNVacias(otro.getNVacias());
		setEsTerminal( otro.esTerminal());
		setGanador(otro.getGanador());
		setTurnoDe(otro.getJugadorQueMueve());
	}

	// OBSERVADORES Y MODIFICADORES DE ATRIBUTOS

	/** modificador para el atributo turnoDe
	 * @param i, id del jugador a quien corresponde el turno en este estado
	 * si i es impar, el turno ser� de 1 (O); si es par, de 0 (X)
	 */
	private void setTurnoDe(int i) {
		turnoDe = ( i%2==0? 0 : 1); 
	}
	
	/**
	 * @return el id del jugador al que le toca mover
	 */
	public int getTurnoDe() {
		return turnoDe;
	}

	/**
	 * @return the ganador
	 */
	public int getGanador() {
		return ganador;
	}

	/**
	 * @param ganador the ganador to set
	 *  privado para que no se modifique desde fuera de la clase
	 */
	private void setGanador(int ganador) {
		this.ganador = ganador;
	}

	/**
	 * @param esTerminal the esTerminal to set
	 * privado para que no se modifique desde fuera de la clase
	 */
	private void setEsTerminal(boolean esTerminal) {
		this.esTerminal = esTerminal;
	}

	/**
	 * Metodo que decide si un estado es terminal
	 * @return cierto, si hay 3 en raya para algun jugador
	 */
	public boolean esTerminal(){
		return this.esTerminal;
	}

	/**
	 * @return the nVacias
	 */
	public int getNVacias() {
		return nVacias;
	}

	/**
	 * @param nVacias the nVacias to set
	 * privado para que no se modifique desde fuera de la clase
	 */
	private void setNVacias(int nVacias) {
		this.nVacias = nVacias;
	}
	
	/**
	 * toString(), devuelve el tablero del estado (no el jugador)
	 */
	@Override
	public String toString() {
		return Util3enR.formatTablero( tablero )+ " mueve: " + Util3enR.maskId2Char(getJugadorQueMueve());
	}
	

	/**
	 * observador de turnoDe
	 * @return el id del jugador al que corresponde mover en este estado
	 */
	public int getJugadorQueMueve() {
		return turnoDe;
	}
	
	/**
	 * observador del oponente del jugador que tiene el turno en este estado
	 * @return el id del contrincante
	 */
	public int getJugadorOponente() {
		return Util3enR.contrario(turnoDe);
	}
		
	/**
	 * observador de el ganador
	 * @return el Jugador3enR ganador, -1 si no lo hay (no es un estado terminal)
	 */
	public int getJugadorGanador() {
		return (esTerminal()? ganador : -1);
	}
	
	/**
	 * Metodo que nos dice si una casilla esta vacia en este estado
	 * @param x, coordenada x de la casilla
	 * @param y, coordenada y de la casilla
	 * @return cierto, si la casilla (x,y) esta dentro del tablero y esta vacia
	 */
	public boolean esCasillaVacia(int x, int y) {
		if( x<0 || x>2 || y<0 || y>2 ) return false;
		return tablero[x][y]==-1;
	}
	
	/**
	 * Metodo para saber cual es el contenido de la casilla x, y
	 * @param x, coordenada x
	 * @param y, coordenada y
	 * @return, el valor de la casilla [x,y] en el estado
	 */
	public int getCasilla( int x, int y){
		return tablero[x][y];
	}
	
	/**
	 * Metodo para modificar una casilla, poniendo una ficha del jugador j 
	 * @param x, coordenada x
	 * @param y, coordenada y
	 * @param jId, id del jugador que pone ficha
	 * @return cierto si se puede poner la ficha
	 */
	public boolean setCasilla(int x, int y, int jId ){
		if( tablero[x][y]==-1 && (jId == 0 || jId == 1)) {
			tablero[x][y]=jId;
			setNVacias(getNVacias()-1);
			compruebaTerminal();
			return true;
		}
		return false;
	}
		
	/**
	 * Metodo que inicia el tablero a todas las casillas vacias
	 */
	private void vaciaTablero(){
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				tablero[i][j]=-1; // -1 indica casilla vacia
		setEsTerminal(false);// tablero vacio no es terminal
		setGanador(-1); // todavia no hay ganador
		setNVacias(9); // todas las celdas estan vacias
	}
	
	/**
	 * Metodo para pasar el turno en este estado al contrincante
	 */
	public void pasaTurno() {
		setTurnoDe( Util3enR.contrario(turnoDe) );
	}


	// METODOS PARA COMPROBAR SI EL ESTADO ES TERMINAL Y, SI HAY 3 EN RAYA, DETERMINAR QUIEN ES EL GANADOR
	
	/**
	 * Metodo que comprueba si un estado es terminal y, en ese caso,
	 * modifica los atributos correspondientes
	 */
	public void compruebaTerminal(){
		if( !esTerminal() ) { // solo lo comprobamos si no lo hemos hecho ya
			int gana = -1;
			setEsTerminal(false); // no terminal, salvo que se encuentre 3 en raya o quede cubierto
			// buscamos si hay 3 en raya y, en ese caso, de quien
			gana = tresEnFila();// comprobamos si hay un 3 en raya por filas
			if(gana==-1){ // no hay ganador por filas
				gana = tresEnCol(); // comprobamos si hay 3 en raya por columnas
				if(gana==-1) // no hay ganador por columnas
					gana = tresEnDiag(); // comprobamos si hay 3 en raya en diagonales
			}
			if( gana!=-1 ){ // hay 3 en raya para el jugador con id==gana
				setEsTerminal(true);
				setGanador(gana);
			}
			else if( getNVacias()==0 ){ // tablero completo sin 3 en raya
				setEsTerminal(true);
				setGanador(-1); // no hay ganador
			}
		}
	}// fin compruebaTerminal()
	
	// para comprobar si hay 3 en raya
	/**
	 * Metodo que comprueba si hay 3 en raya en alguna fila
	 * @return el id del jugador con 3 en raya en alguna fila (-1 si no lo hay)
	 */
	private int tresEnFila() {
		int i=0;// para recorrer filas
		int idJ=-1; // para identificar jugador que tiene 3 en fila
		while( i<3 && idJ==-1 ){
			idJ=tresEnFila( i );
			i++;
		}// fin recorrido filas
		return idJ;
	}
	
	/**
	 * Metodo que comprueba si hay 3 en raya en una fila dada
	 * @param iFila el indice de fila
	 * @return el id del jugador con 3 en raya en una fila (-1 si no lo hay)
	 */
	private int tresEnFila( int iFila ){
		int jCol=0;
		int idJ=this.getCasilla(iFila, jCol); // id del jugador que puede tener 3 en raya en esta fila
		boolean enRaya = true;
		while( jCol < 3 && enRaya ){
			if( this.getCasilla( iFila, jCol )==-1 || this.getCasilla( iFila, jCol )!=idJ ) // casilla vacia o de otro jugador
				enRaya = false;
			else // puede que haya 3 en raya
				jCol++; // avanzamos en la fila
		}
		return (enRaya? idJ : -1);
	}
	
	
	/**
	 * Metodo que comprueba si hay 3 en raya en alguna columna
	 * @return el id del jugador con 3 en raya en alguna columna (-1 si no lo hay)
	 */
	private int tresEnCol() {
		int j=0;// para recorrer columnas
		int idJ=-1; // para identificar jugador que tiene 3 en columna
		while( j<3 && idJ==-1 ){
			idJ=tresEnCol( j );
			j++;
		}// fin recorrido filas
		return idJ;
	}
	
	/**
	 * Metodo que comprueba si hay 3 en raya en una columna dada
	 * @param jCol el indice de columna
	 * @return el id del jugador con 3 en raya en una fila (-1 si no lo hay)
	 */
	private int tresEnCol( int jCol ){
		int iFila=0;
		int idJ=this.getCasilla(iFila, jCol); // id del jugador que puede tener 3 en raya en esta columna
		boolean enRaya = true;
		while( iFila < 3 && enRaya ){
			if( this.getCasilla( iFila, jCol )==-1 || this.getCasilla( iFila, jCol )!=idJ ) // casilla vacia o de otro jugador
				enRaya = false;
			else // puede que haya 3 en raya
				iFila++; // avanzamos en la fila
		}
		return (enRaya? idJ : -1);
	}
	
	/**
	 * Metodo que comprueba si hay 3 en raya en diagonal
	 * @return el id del jugador con 3 en diagonal (si hay), -1 si no
	 */
	private int tresEnDiag() {
		int idJ = tresEnDiagPrincipal(); // comprobamos diagonal principal
		if( idJ!=-1 ) return idJ; // hay 3 en raya para idJ en la diagonal principal
		idJ = tresEnDiagSecundaria(); // comprobamos diagonal secundaria
		return idJ;
	}
		
	/**
	 * Metodo que comprueba si hay 3 en raya en la diagonal principal
	 * @return el id del jugador con 3 en raya (si hay), -1 si no
	 */
	private int tresEnDiagPrincipal(){
		int idJ = this.getCasilla(0, 0); // ficha en la esquina de arriba a la izquierda
		int d=1;
		while( d<3 && this.getCasilla(d, d) == idJ ) d++; // recorro diagonal mientras no cambie de ficha
		return ( d==3? idJ : -1 );
	}
	
	/**
	 * Metodo que comprueba si hay 3 en raya en la diagonal principal
	 * @return el id del jugador con 3 en raya (si hay), -1 si no
	 */
	private int tresEnDiagSecundaria(){
		int idJ = this.getCasilla(0, 2); // ficha en la esquina de arriba a la derecha
		int d=1;
		while( d<3 && this.getCasilla(d, 2-d) == idJ ) d++; // recorro diagonal mientras no cambie de ficha
		return ( d==3? idJ : -1 );
	}

	// METODOS UTILES PARA LA EVALUACION HEURISTICA DEL ESTADO
	// BORRAR SI LA PRACTICA SOLO HACE BUSQUEDA PERFECTA
	/**
	 * Metodo para ver si hay un `camino ganador`'' (posibilidad de colocar 3 en raya) en una fila para un jugador
	 * (lo utiliza la funcion de utilidad)
	 * @param fila, la fila
	 * @param idJ, el id del jugador
	 * @return cierto si en la fila hay posibilidad de hacer 3 en raya para el jugador idJ
	 */
	public boolean caminoGanadorEnFila(int fila, int idJ) {
		boolean camino = true;
		int col=0;
		// recorremos la fila hasta el final o hasta encontrar una pieza del contrario
		while( col < 3 && camino ){
			if( tablero[fila][col] == Util3enR.contrario(idJ) )
				camino=false;
			col++;
		}
		return camino;
	}

	/**
	 * Metodo para ver si hay un `camino ganador`'' (posibilidad de colocar 3 en raya) en una columna para un jugador
	 * (lo utiliza la funcion de utilidad)
	 * @param col, la columna
	 * @param idJ, el id del jugador
	 * @return cierto si en la columna hay posibilidad de hacer 3 en raya para el jugador idJ
	 */
	public boolean caminoGanadorEnCol(int col, int idJ) {
		boolean camino = true;
		int fila=0;
		// recorremos la columna hasta el final o hasta encontrar una pieza del contrario
		while( fila < 3 && camino ){
			if( tablero[fila][col] == Util3enR.contrario(idJ) )
				camino=false;
			fila++;
		}
		return camino;
	}

	/**
	 * Metodo para ver si hay un `camino ganador`'' (posibilidad de colocar 3 en raya) en 
	 * la diagonal principal para un jugador
	 * (lo utiliza la funcion de utilidad)
	 * @param idJ, el id del jugador
	 * @return cierto si en la diagonal principal hay posibilidad de hacer 3 en raya para el jugador idJ
	 */
	public boolean caminoGanadorEnDiagPrincipal(int idJ) {
		boolean camino = true;
		int d=0;
		// recorremos la columna hasta el final o hasta encontrar una pieza del contrario
		while( d < 3 && camino ){
			if( tablero[d][d] == Util3enR.contrario(idJ) )
				camino=false;
			d++;
		}
		return camino;
	
	}

	/**
	 * Metodo para ver si hay un `camino ganador`'' (posibilidad de colocar 3 en raya) en 
	 * la diagonal secundaria para un jugador
	 * (lo utiliza la funcion de utilidad)
	 * @param idJ, el id del jugador
	 * @return cierto si en la diagonal principal hay posibilidad de hacer 3 en raya para el jugador idJ
	 */
	public boolean caminoGanadorEnDiagSecundaria(int idJ) {
		boolean camino = true;
		int d=0;
		// recorremos la columna hasta el final o hasta encontrar una pieza del contrario
		while( d < 3 && camino ){
			int col = 2-d;
			if( tablero[d][col] == Util3enR.contrario(idJ) )
				camino=false;
			d++;
		}
		return camino;
	}

}
