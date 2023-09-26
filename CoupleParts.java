package Juego;

import java.util.Random;
import java.util.Scanner;

public class CoupleParts {
	/**
	 * Comprueba si todas las casillas estan ocultas
	 * 
	 * @param ocultas
	 * @return
	 */
	public static boolean comprobarGanador(int[][] ocultas) {
		boolean resultado = true;
		for (int i = 0; i < ocultas.length && resultado; i++) {
			for (int j = 0; j < ocultas.length && resultado; j++) {
				if (ocultas[i][j] == -1)
					resultado = false;
			}
		}
		return resultado;
	}

	/**
	 * controla la jugada actual
	 * 
	 * @param tablero
	 * @param ocultas
	 * @return
	 */
	public static boolean realizarJugada(int[][] tablero, int[][] ocultas) {
		int[] origen;
		do{
		origen= pedirCoordenada();
		}while(!revelarPosicion(tablero, ocultas, origen));
		CoupleParts.mostrarTablero(ocultas);
		int[] destino;
		do{
			destino= pedirCoordenada();
		}while(!revelarPosicion(tablero, ocultas, destino));
		CoupleParts.mostrarTablero(ocultas);
		boolean comprobacion = comprobarParejas(tablero, origen, destino);
		pausar(1);
		if (!comprobacion) {
			ocultarPosiciones(ocultas, origen);
			ocultarPosiciones(ocultas, destino);
		}
		CoupleParts.mostrarTablero(ocultas);
		return comprobacion;
	}
	
	public static void pausar(int i) {
		try {
			new Thread().sleep(i * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void ocultarPosiciones(int[][] ocultas, int[] origen) {
		ocultas[origen[0]][origen[1]] = -1;
	}

	public static boolean revelarPosicion(int[][] tablero, int[][] ocultas, int[] origen) {
		boolean retorno = false;
		if (ocultas[origen[0]][origen[1]] == -1) {
			ocultas[origen[0]][origen[1]] = tablero[origen[0]][origen[1]];
			retorno = true;
		}
		return retorno;
	}

	public static boolean comprobarParejas(int[][] tablero, int[] origen, int[] destino) {
		return tablero[origen[0]][origen[1]] == tablero[destino[0]][destino[1]];
	}

	public static int[] pedirCoordenada() {
		int[] coordenada = new int[2];
		for (int i = 0; i < coordenada.length; i++) {
			coordenada[i] = pedirValor();
		}
		return coordenada;
	}

	public static int pedirValor() {
		Scanner leer = new Scanner(System.in);
		int valor = 0;
		do {
			System.out.println("introduce un valor entre 0 y " + (Constantes.size - 1));
			valor = leer.nextInt();
		} while (!comprobarValor(valor));
		return valor;
	}

	public static boolean comprobarValor(int valor) {
		return valor >= 0 && valor < Constantes.size;
	}

	public static void mostrarTablero(int[][] matriz) {
		System.out.print("   ");
		for (int i = 0; i < matriz.length; i++) {
			System.out.print("   " + i + " ");
		}
		System.out.println();
		System.out.print("   ");
		System.out.print("╔");
		for (int i = 0; i < matriz.length; i++) {
			System.out.print("════");
			if (i != matriz.length - 1)
				System.out.print("╦");
		}
		System.out.println("╗");
		for (int i = 0; i < matriz.length; i++) {
			System.out.print("  " + i);
			System.out.print("║ ");
			for (int j = 0; j < matriz[i].length; j++) {
				if (matriz[i][j] < 10 && matriz[i][j] > -1)
					System.out.print(" ");
				if (matriz[i][j] == -1)
					System.out.print("X ");
				else
					System.out.print(matriz[i][j]);
				System.out.print(" ║ ");
			}
			System.out.println();
			if (i != matriz.length - 1) {
				System.out.print("   ");
				System.out.print("╠");
				for (int j = 0; j < matriz.length; j++) {
					System.out.print("════");
					if (j != matriz.length - 1)
						System.out.print("╬");
				}
				System.out.println("╣");
			} else {
				System.out.print("   ");
				System.out.print("╚");
				for (int h = 0; h < matriz.length; h++) {
					System.out.print("════");
					if (h != matriz.length - 1)
						System.out.print("╩");
				}
				System.out.println("╝");
			}
		}
	}

	/**
	 * coloca las parejas seguidas en la matriz
	 * 
	 * @param tablero
	 *            con las partejas ordenadas
	 */
	public static void rellenarOrdenado(int[][] tablero) {
		for (int i = 0, valor = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length - 1; j += 2, valor++) {
				tablero[i][j] = valor;
				tablero[i][j + 1] = valor;
			}
		}
	}

	/**
	 * genera una coordenda valida (dentro de los limites del tablero
	 * 
	 * @param coordenada
	 */
	public static void realizarSorteo(int[] coordenada) {
		for (int i = 0; i < coordenada.length; i++) {
			coordenada[i] = new Random().nextInt(Constantes.size);
		}
	}

	/**
	 * Inmtercambia los valores de entre dos posiciones en una matriz
	 * 
	 * @param tablero
	 * @param origen
	 * @param destino
	 */
	public static void intercambiarPosicion(int[][] tablero, int[] origen, int[] destino) {
		int intercambio = tablero[origen[0]][origen[1]];
		tablero[origen[0]][origen[1]] = tablero[destino[0]][destino[1]];
		tablero[destino[0]][destino[1]] = intercambio;
	}

	/**
	 * Intercambiar las posiciones del tablero para ocultar las parejas
	 * 
	 * @param tablero
	 */
	public static void intercambiarPosiciones(int[][] tablero) {
		// creamos las coordenadas de origen y destino
		int[] origen = new int[2], destino = new int[2];
		int intercambios = (int) Math.pow(Constantes.size, 3);
		for (int i = 0; i < intercambios; i++) {
			CoupleParts.realizarSorteo(origen);
			CoupleParts.realizarSorteo(destino);
			CoupleParts.intercambiarPosicion(tablero, origen, destino);
		}
	}

	/**
	 * Coloca aleatoriamente las parejas sobre el tablero
	 * 
	 * @return
	 */
	public static int[][] crearTablero() {
		// Los archivos no se reconocen bien siestan en el
		// default package por eso creamos el package Juego
		int[][] tablero = new int[Constantes.size][Constantes.size];
		CoupleParts.rellenarOrdenado(tablero);
		CoupleParts.intercambiarPosiciones(tablero);
		return tablero;
	}

	/**
	 * Rellenar de -1 todas las fichas de ocultas
	 * 
	 * @param ocultas
	 */
	public static void rellenarOcultas(int[][] ocultas) {
		for (int i = 0; i < ocultas.length; i++) {
			for (int j = 0; j < ocultas.length; j++) {
				ocultas[i][j] = -1;
			}
		}
	}

}
