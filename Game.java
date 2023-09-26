package Juego;

public class Game {
	public static void main(String[] args) {
		int[][] tablero,ocultas=new int[Constantes.size][Constantes.size];
		tablero=CoupleParts.crearTablero();
		CoupleParts.rellenarOcultas(ocultas);
		boolean ganador=false;
		do{
			CoupleParts.mostrarTablero(ocultas);
			if(CoupleParts.realizarJugada(tablero,ocultas))
				ganador=CoupleParts.comprobarGanador(ocultas);
		}while(!ganador);
	}	
}
