import java.io.BufferedReader;
import java.io.InputStreamReader;

public class cuatro_en_linea {
	private static final int MINIMO = 3;
	private static final int CERO = 0;

	public static void main(String[] args) {

		System.out.println("Ingrese ancho del tablero: ");
		int ancho = pedir_numero();
		System.out.println("Ingrese largo del tablero: ");
		int largo = pedir_numero();

		char[][] tablero = new char[largo][ancho];
		cargarMatriz(tablero, ancho, largo);

		int modo_juego = elegir_modo_juego();
		if (modo_juego == 1) {
			System.out.println("Modo de juego: Unx vs Unx");
			jugar(tablero, ancho, largo, modo_juego);
		} else {
			System.out.println("Modo de juego: Unx vs CPU");
			jugar(tablero, ancho, largo, modo_juego);
		}
	}// fin main

	private static void cargarMatriz(char[][] matriz, int ancho, int largo) {

		for (int fila = 0; fila < largo; fila++) {
			for (int columna = 0; columna < ancho; columna++) {
				matriz[fila][columna] = ' ';
			}
		}
	}// fin cargar matriz

	private static int pedir_numero() {
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		int numero = MINIMO;

		while (numero <= MINIMO) {
			try {
				numero = new Integer(entrada.readLine());
			} catch (Exception error) {
				System.out.println(error);
				numero = MINIMO;
			}
		}

		return numero;
	}// fin pedir numero

	private static int elegir_modo_juego() {
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		int numero = CERO;

		while (numero < 1 || numero > 2) {
			try {
				System.out.println("Seleccione modo de juego: (1) Unx vs Unx - (2) Unx vs CPU");
				numero = new Integer(entrada.readLine());
			} catch (Exception error) {
				System.out.println(error);
				numero = MINIMO;
			}
		}

		return numero;
	}// fin elegir modo juego

	private static void jugar(char[][] tablero, int ancho, int largo, int modo) {
		boolean gano_o_empato = false;
		boolean columna_valida = false;

		while (!gano_o_empato) {
			try {
				System.out.println("Turno jugador/a 1: ");
				gano_o_empato = turno_usuarix(tablero, ancho, largo, 1,columna_valida);

				if (gano_o_empato) {
					System.out.println("Gana jugador/a 1");
				}

				if (!gano_o_empato && modo == 1) {

					System.out.println("Turno jugador/a 2: ");
					gano_o_empato = turno_usuarix(tablero, ancho, largo, 2,columna_valida);

					if (gano_o_empato) {
						System.out.println("Gana jugador/a 2");
					}
				}

				if (!gano_o_empato && modo == 2) {

					System.out.println("Turno CPU: ");
					gano_o_empato = turno_usuarix(tablero, ancho, largo, 3, columna_valida);

					if (gano_o_empato) {
						System.out.println("Gana CPU");
					}

				}
				if (!gano_o_empato) {
					if (empate(tablero, largo, ancho)) {
						System.out.println("Empate");
						gano_o_empato = true;
					}
				}

			} catch (Exception error) {
				System.out.println(error);
				gano_o_empato = false;
			}

		}
	}// fin unx vs unx

	private static boolean turno_usuarix(char[][] tablero, int ancho,int largo, int usuarix, boolean columna_valida) {

		boolean gano_o_empato = false;
		int columna = CERO;

		while (!columna_valida) {
			columna = pedir_columna(ancho, usuarix);
			columna_valida = es_valida(tablero, ancho, largo, columna);
		}
		gano_o_empato = insertar_ficha(tablero, ancho, largo, columna, usuarix);
		return gano_o_empato;
	}// fin turno usuarix

	private static int pedir_columna(int ancho, int usuarix) {
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		int columna = -1;

		while (columna < CERO || columna >= ancho) {
			try {
				if (usuarix == 1 || usuarix == 2) {
					System.out.println("Eliga N� de columna: ");
					columna = new Integer(entrada.readLine());
				}
				if (usuarix == 3) {
					columna = (int) (Math.random() * ancho);
				}
			} catch (Exception error) {
				System.out.println(error);
			}
		}
		return columna;
	}// fin pedir columna

	private static boolean es_valida(char[][] tablero, int ancho, int largo,int columna) {
		if (tablero[0][columna] == ' ') {
			return true;
		}
		return false;
	}// fin es valida

	private static boolean insertar_ficha(char[][] tablero, int ancho,int largo, int columna, int juega) {
		boolean gano_o_empato = false;
		int fila_ficha = 0;

		for (int fila = largo - 1; fila >= CERO; fila--) {
			if (tablero[fila][columna] == ' ') {
				fila_ficha = fila;
				if (juega == 1) {
					tablero[fila][columna] = 'x';
				} else {
					tablero[fila][columna] = 'o';
				}
				fila = -1;
			}
		}
		imprimir_matriz(tablero, ancho, largo);
		gano_o_empato = comprobar_si_gano(tablero, ancho, largo, juega,fila_ficha, columna);

		return gano_o_empato;
	}// fin insertar ficha

	private static boolean comprobar_si_gano(char[][] tablero, int ancho,int largo, int juega, int fila_ficha, int col) {
		char aux = tablero[fila_ficha][col];

		if (buscar_horizontal(tablero, ancho, aux, fila_ficha, col)) {
			return true;
		}
		if (buscar_vertical(tablero, largo, aux, fila_ficha, col)) {
			return true;
		}
		if (buscar_ascendente(tablero, largo, ancho, aux, fila_ficha, col)) {
			return true;
		}
		if (buscar_descendente(tablero, largo, ancho, aux, fila_ficha, col)) {
			return true;
		}
		return false;
	}// fin gano o empato

	private static boolean empate(char[][] tablero, int largo, int ancho) {

		for (int fila = 0; fila < largo; fila++) {
			for (int columna = 0; columna < ancho; columna++) {
				if (tablero[fila][columna] == ' ') {
					return false;
				}
			}
		}
		return true;
	}// fin empate

	private static boolean buscar_descendente(char[][] tablero, int largo,int ancho, char aux, int fila_ficha, int col) {
		int contador = 0;
		int auxfila = fila_ficha;
		int auxcol = col;

		while (auxfila >= CERO && auxcol >= CERO && tablero[auxfila][auxcol] == aux) {
			contador++;
			auxfila--;
			auxcol--;
		}
		if (contador != CERO) {
			contador--;
		}

		auxfila = fila_ficha;
		auxcol = col;

		while (auxfila < largo && auxcol < ancho && tablero[auxfila][auxcol] == aux) {
			contador++;
			auxfila++;
			auxcol++;
		}
		if (contador >= 4) {
			return true;
		}

		return false;
	}// buscar descendente

	private static boolean buscar_ascendente(char[][] tablero, int largo,int ancho, char aux, int fila_ficha, int col) {
		int contador = 0;
		int auxfila = fila_ficha;
		int auxcol = col;

		while (auxfila >= CERO && auxcol < ancho && tablero[auxfila][auxcol] == aux) {
			contador++;
			auxfila--;
			auxcol++;
		}
		if (contador != CERO) {
			contador--;
		}

		auxfila = fila_ficha;
		auxcol = col;

		while (auxfila < largo && auxcol >= CERO && tablero[auxfila][auxcol] == aux) {
			contador++;
			auxfila++;
			auxcol--;
		}
		if (contador >= 4) {
			return true;
		}
		return false;
	}// fin buscar ascendente

	private static boolean buscar_vertical(char[][] tablero, int largo, char aux, int fila_ficha, int col) {
		int contador = 0;

		for (int fila = largo - 1; fila >= CERO; fila--) {
			if (aux == tablero[fila][col]) {
				aux = tablero[fila][col];
				contador++;
				if (contador == 4) {
					return true;
				}
			} else {
				contador = 0;
			}
		}
		return false;
	}// fin buscar vertical

	private static boolean buscar_horizontal(char[][] tablero, int ancho,char aux, int fila_ficha, int col) {
		int contador = 0;

		for (int columna = 0; columna < ancho; columna++) {
			if (aux == tablero[fila_ficha][columna]) {
				aux = tablero[fila_ficha][columna];
				contador++;
				if (contador == 4) {
					return true;
				}
			} else {
				contador = 0;
			}
		}
		return false;
	}// fin buscar horizontal

	private static void imprimir_matriz(char[][] matriz, int ancho, int largo) {

		for (int fila = 0; fila < largo; fila++) {
			System.out.print("| ");
			for (int columna = 0; columna < ancho; columna++) {
				System.out.print(matriz[fila][columna] + " | ");
			}
			System.out.println();
		}
	}// fin imprimir matriz
}