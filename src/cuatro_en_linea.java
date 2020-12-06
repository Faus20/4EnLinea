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

		int modo_juego = elegir_modo_juego();
		if (modo_juego == 1) {
			System.out.println("Modo de juego: Unx vs Unx");
			unx_vs_unx(tablero, ancho, largo);
		} else {
			System.out.println("Modo de juego: Unx vs CPU");
			// unx_vs_cpu(tablero, ancho, largo);
		}

	}// fin main

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
				System.out
						.println("Seleccione modo de juego: (1) Unx vs Unx - (2) Unx vs CPU");
				numero = new Integer(entrada.readLine());
			} catch (Exception error) {
				System.out.println(error);
				numero = MINIMO;
			}
		}

		return numero;
	}// fin elegir modo juego

	private static void unx_vs_unx(char[][] tablero, int ancho, int largo) {
		boolean gano = false;
		boolean columna_valida = false;
		
		while (gano == false) {
			try {
				System.out.println("Turno jugador/a 1: ");
				turno_usuarix(tablero,ancho,largo,1, columna_valida);
				//gano = comprobar_si_gano (tablero, ancho, largo);
				
				System.out.println("Turno jugador/a 2: ");
				turno_usuarix(tablero,ancho,largo,2, columna_valida);
				//gano = comprobar_si_gano (tablero, ancho, largo);
						
			} catch (Exception error) {
				System.out.println(error);
				gano = false;
			}
		}

	}// fin unx vs unx

	private static void turno_usuarix(char[][] tablero, int ancho, int largo, int usuarix, boolean columna_valida) {
	
		int columna = CERO;
		
		while (columna_valida == false) {
			columna = pedir_columna(ancho);
			columna_valida = es_valida(tablero, ancho, largo, columna);
		}
		insertar_ficha(tablero, ancho, largo, columna, 1);
	}//fin turno usuarix

	private static void insertar_ficha(char[][] tablero, int ancho, int largo, int columna, int juega) {

		for (int fila = largo - 1; fila < CERO; fila--) {
			if (tablero[fila][columna] == ' ') {
				if (juega == 1) {
					tablero[fila][columna] = 'x';
				} else {
					tablero[fila][columna] = 'o';
				}
			}
		} 
		imprimir_matriz(tablero, ancho, largo);
	}// fin insertar ficha

	private static boolean es_valida(char[][] tablero, int ancho, int largo, int columna) {

		if (tablero[CERO][columna] == ' ') {
			return true;
		}
		return false;
	}// fin es valida

	private static int pedir_columna(int ancho) {
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		int columna = CERO;

		while (columna < CERO || columna > ancho) {
			try {
				System.out.println("Eliga N° de columna: ");
				columna = new Integer(entrada.readLine());
			} catch (Exception error) {
				System.out.println(error);
			}
		}
		return columna;
	}// fin pedir columna

	private static void imprimir_matriz(char[][] matriz, int ancho, int largo) {

		for (int fila = 0; fila < largo; fila++) {
			System.out.print("| ");
			for (int columna = 0; columna < ancho; columna++) {
				System.out.print(matriz[fila][columna] + " | ");
			}
			System.out.println();
		}

	}// FIN IMPRIMIR MATRIZ
}
