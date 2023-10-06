package monopoly;


import java.util.Random;
import java.util.Scanner;

public class Game {
	public static void main(String[] args) {
		int turno = 1;
		int[] posiciones = { 1, 1, 1, 1 };
		int capitalInicial = 1000;
		int capitales[] = { capitalInicial, capitalInicial, capitalInicial, capitalInicial };
		int propiedad[] = new int[40];
		boolean ganador = false;
		rellenaPropiedad(propiedad);
		do {
			moverJugador(posiciones, turno);
			comprobarCasilla(posiciones, turno, capitales, propiedad);
			ganador = comprobarCapitales(capitales);
			if (!ganador) {
				turno = cambiarTurno(capitales, turno);
			}
			mostrarCapitales(capitales);
		} while (!ganador);
	}

	/**
	 * muestra los capitales de todos los jugadores
	 * 
	 * @param capitales
	 */
	private static void mostrarCapitales(int[] capitales) {
		for (int i = 0; i < capitales.length; i++) {
			System.out.println("el capital de " + i + " es " + capitales[i]);
		}
	}

	/**
	 * Pasa el turno al siguiente jugador cuyo capital sea mayor que cero
	 * 
	 * @param capitales
	 * @param turno
	 * @return
	 */
	private static int cambiarTurno(int[] capitales, int turno) {
		do {
			turno++;
			if (turno > 4)
				turno = 1;
		} while (capitales[turno] > 0);
		return 0;
	}

	/**
	 * comprueba si hay tres capitales a cero
	 * 
	 * @param capitales
	 * @return true si hay tres capitales a cero
	 */
	private static boolean comprobarCapitales(int[] capitales) {
		boolean ganador = true;
		int contador = 0;
		for (int i = 0; i < capitales.length; i++) {
			if (capitales[i] == 0) {
				contador++;
			}
		}
		if (contador == 3) {
			ganador = true;
		}
		return ganador;
	}

	/**
	 * Pone todas las casillas de propiedad a valor -1 (sin propiedad)
	 * 
	 * @param propiedad
	 */
	private static void rellenaPropiedad(int[] propiedad) {
		for (int i = 0; i < propiedad.length; i++) {
			propiedad[i] = -1;
		}
	}

	/**
	 * comprueba si una casilla es especial, si no lo es miramos su dueño, si lo
	 * tiene cobramos al jugador por la estancia
	 * 
	 * @param posiciones
	 * @param turno
	 * @param capitales
	 * @param propiedad
	 */
	private static void comprobarCasilla(int[] posiciones, int turno, int[] capitales, int[] propiedad) {
		int dueno = 0;
		if (!comprobarCasillaEspecial(posiciones[turno])) {
			dueno = administrarPropiedad(capitales, posiciones, turno, propiedad);
			if (dueno != -1) {
				System.out.println("la casilla es propiedad del jugador " + propiedad[turno]);
				cobrarEstancia(capitales, posiciones, turno, propiedad, dueno);
			}
		}
	}

	/**
	 * cobramos al jugador del turno actual el valor de la estancia en la
	 * casilla o tanto como pueda si su capital es menor e incrementamos el
	 * capital del jugador dueño de la casilla
	 * 
	 * @param capitales
	 * @param posiciones
	 * @param turno
	 * @param propiedad
	 * @param dueno
	 */
	private static void cobrarEstancia(int[] capitales, int[] posiciones, int turno, int[] propiedad, int dueno) {
		int estancia = posiciones[turno] * Constantes.estancia;
		capitales[turno] -= estancia;
		estancia = corregirCapitalNegativo(capitales, turno, estancia);
		capitales[propiedad[posiciones[turno]]] += estancia;
		System.out.println("has de pagar " + estancia);
	}

	/**
	 * calcula el valor de la estancia teniendo en cuenta el capital del jugador
	 * pagador
	 * 
	 * @param capitales
	 * @param turno
	 * @param estancia
	 * @return
	 */
	private static int corregirCapitalNegativo(int[] capitales, int turno, int estancia) {
		if (capitales[turno] < 0) {
			estancia += capitales[turno];
			capitales[turno] = 0;
		}
		return estancia;
	}

	/**
	 * permite al jugador comprar una casilla sin dueño
	 * 
	 * @param capitales
	 * @param posiciones
	 * @param turno
	 * @param propiedad
	 * @return
	 */
	private static int administrarPropiedad(int[] capitales, int[] posiciones, int turno, int[] propiedad) {
		int dueno = comprobarPropiedad(propiedad, posiciones[turno]);
		if (dueno == -1)
			comprarPropiedad(propiedad, posiciones[turno], turno, capitales);
		return dueno;
	}

	/**
	 * le da la titularidad a un jugador de una casilla (siempre que tenga
	 * capital suficiente)
	 * 
	 * @param propiedad
	 * @param i
	 * @param turno
	 * @param capitales
	 */
	private static void comprarPropiedad(int[] propiedad, int i, int turno, int[] capitales) {
		int valor = i * Constantes.factor;
		if (capitales[turno] > valor && pedirConsentimiento(i, valor, capitales[turno])) {
			propiedad[i] = turno;
			capitales[turno] -= valor;
		}
	}

	/**
	 * pregunta al usuario si quiere comprar la propiedad
	 * 
	 * @param i
	 * @param valor
	 * @param capital
	 * @return
	 */
	private static boolean pedirConsentimiento(int i, int valor, int capital) {
		System.out
				.println("Desea comprar la propiedad " + i + " por " + valor + ", su capital es " + capital + " (y/n)");
		if (pedirLetra() == 'Y') {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * nos dice de quien es la casilla en la posicion i
	 * 
	 * @param propiedad
	 * @param i
	 * @return
	 */
	private static int comprobarPropiedad(int[] propiedad, int i) {
		return propiedad[i];
	}

	/**
	 * Comprueba si es una casilla especial
	 * 
	 * @param i
	 * @return
	 */
	private static boolean comprobarCasillaEspecial(int i) {
		if (buscarBinarioEnArreglo(Constantes.casillasEspeciales, i) == -1) {
			return false;
		} else {
			System.out.println("es una casilla especial");
			return true;
		}
	}

	/**
	 * Se encarga de mover al jugador desde la posicion actual a una sorteada
	 * 
	 * @param posiciones
	 * @param turno
	 */
	public static void moverJugador(int posiciones[], int turno) {
		System.out.println("pulsa una tecla para tirar");
		pedirLetra();
		int valor = tirarDados();
		System.out.println("ha salido el valor " + valor);
		moverFicha(posiciones, turno, valor);
		System.out.println("tu ficha esta en la casilla " + posiciones[turno]);
	}

	/**
	 * mueve la ficha a la posicion indicada por el numero
	 * 
	 * @param posiciones
	 * @param turno
	 * @param numero
	 */
	private static void moverFicha(int[] posiciones, int turno, int numero) {
		int casillaActual = posiciones[turno] + numero;
		if (comprobarLimites(casillaActual)) {
			casillaActual -= Constantes.ultimaCasilla;
		}
		posiciones[turno] = casillaActual;
	}

	/**
	 * comprueba si una posicion casillaActual esta fuera de los limites
	 * 
	 * @param casillaActual
	 * @return
	 */
	private static boolean comprobarLimites(int casillaActual) {
		return casillaActual > Constantes.ultimaCasilla;
	}

	/**
	 * Se encarga de la jugada obtiene un valor de dos tiradas
	 * 
	 * @return
	 */
	private static int tirarDados() {
		return tirarDado() + tirarDado();
	}

	/**
	 * obtiene un valor aleatorio entre 1 y 6
	 * 
	 * @return
	 */
	private static int tirarDado() {
		return new Random().nextInt(6) + 1;
	}

	public static int buscarBinarioEnArreglo(int[] arreglo, int elemento) {
		int medio, alto = arreglo.length, bajo = 0, contador = 0;
		do {
			medio = (bajo + alto) / 2;
			if (elemento < arreglo[medio]) {
				alto = medio - 1;
			} else {
				bajo = medio + 1;
			}
			contador++;
		} while (arreglo[medio] != elemento && bajo < alto);
		System.out.println("la busqueda binaria necesita " + contador + " pasos");
		if (arreglo[medio] == elemento) {
			return medio;
		} else {
			return -1;
		}
	}

	public static char pedirLetra() {
		Scanner leer = new Scanner(System.in);
		char letra = leer.nextLine().charAt(0);
		letra = Character.toUpperCase(letra);
		return letra;
	}
}
