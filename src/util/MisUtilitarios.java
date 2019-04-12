package util;

import java.util.List;
import java.util.Scanner;

import code.Constantes;

public class MisUtilitarios {

	public static void mostrarMatrices(String titulo, String[][] matriz,
			int nroFilas, int nroColumnas) {
		System.out.println("______________________________");
		System.out.println(titulo);

		if (matriz == null) {
			System.err.println("Error!, la matriz es nula");
		} else if (nroFilas <= 0) {
			System.err.println("Error!, el nroFilas es menor o igual a 0");
		} else if (nroColumnas <= 0) {
			System.err.println("Error!, el nroColumnas es menor o igual a 0");
		} else {
			for (int i = 0; i < nroFilas; i++) {
				for (int j = 0; j < nroColumnas; j++) {
					System.out.print(matriz[i][j] + "    ");
				}
				System.out.println();
			}
		}

		System.out.println("______________________________");

	}

	public static void mostrarArrayCadenas(String titulo, String[] array) {
		System.out.println("\n______________________________");
		System.out.println(titulo);

		if (array == null) {
			System.err.println("Error!, el array es nula");
		} else {
			for (int j = 0; j < array.length; j++) {
				System.out.print(array[j] + "    ");
			}
		}

		System.out.println("\n______________________________");

	}

	public static void ordenarMatricesDeMayorAMenor(String[][] matrizAOrdenar,
			int nroColumnas) {

		if (matrizAOrdenar == null) {
			System.err.println("Error!, la matriz es nula");
		} else if (nroColumnas <= 0) {
			System.err.println("Error!, el nroColumnas es menor o igual a 0");
		} else {

			for (int i = 1; i < nroColumnas; i++) {
				for (int j = 0; j < nroColumnas - 1; j++) {

					String gananciaComponenteActualStr = matrizAOrdenar[1][j];
					String gananciaComponenteSiguienteStr = matrizAOrdenar[1][j + 1];

					// ACTUAL
					int gananciaComponenteActual = -99;
					if (gananciaComponenteActualStr != null && !Constantes.CADENA_VACIA.equals(gananciaComponenteActualStr) && !Constantes.SIMBOLO_X.equals(gananciaComponenteActualStr)) {
						gananciaComponenteActual = Integer.parseInt(gananciaComponenteActualStr);
					} else if (Constantes.CADENA_VACIA.equals(gananciaComponenteActualStr)) {
						gananciaComponenteActual = 0;
					} else if (Constantes.SIMBOLO_X.equals(gananciaComponenteActualStr)) {
						gananciaComponenteActual = -1;
					}

					// SIGUIENTE
					int gananciaComponenteSiguiente = -99;
					if (gananciaComponenteSiguienteStr != null && !Constantes.CADENA_VACIA.equals(gananciaComponenteSiguienteStr) && !Constantes.SIMBOLO_X.equals(gananciaComponenteSiguienteStr)) {
						gananciaComponenteSiguiente = Integer.parseInt(gananciaComponenteSiguienteStr);
					} else if (Constantes.CADENA_VACIA.equals(gananciaComponenteSiguienteStr)) {
						gananciaComponenteSiguiente = 0;
					} else if (Constantes.SIMBOLO_X.equals(gananciaComponenteSiguienteStr)) {
						gananciaComponenteSiguiente = -1;
					}

					if (gananciaComponenteActual < gananciaComponenteSiguiente) {

						// valores
						String auxValorNombreComponente = matrizAOrdenar[0][j];
						matrizAOrdenar[0][j] = matrizAOrdenar[0][j + 1];
						matrizAOrdenar[0][j + 1] = auxValorNombreComponente;

						// valores
						String auxValorGanancia = matrizAOrdenar[1][j];
						matrizAOrdenar[1][j] = matrizAOrdenar[1][j + 1];
						matrizAOrdenar[1][j + 1] = auxValorGanancia;
					}
				}
			}
		}

	}

	public static int posicionDelNodoConMayorValorValidoExceptoNodoFinal(
			String nodoFinalTest, int numeroCompuestos, String[][] matriz,
			String[] nodosVisitados) {

		int posicionDelMayor = -1, mayor = 0;

		if (matriz == null) {
			System.err.println("Error!, la matriz es nula");
		} else if (nodosVisitados == null) {
			System.err.println("Error!, la nodosVisitados es nulo");
		} else if (nodoFinalTest == null || nodoFinalTest.isEmpty()) {
			System.err.println("Error!, el nodoFinalTest es nulo");
		} else if (numeroCompuestos <= 0) {
			System.err.println("Error!, el numeroCompuestos es menor o igual a 0");
		} else {

			// System.out.println("posicionDelMayorValidoComponenteX. Nodos VISITADO: "
			// + Arrays.toString(nodosVisitados));

			for (int i = 0; i < numeroCompuestos; i++) {
				if (!nodoFinalTest.equals(matriz[0][i])) {
					if (!existeElemento(matriz[0][i], nodosVisitados)) {
						String valorNumericoStr = matriz[1][i];

						int valorNumerico = 0;
						if (valorNumericoStr != null && !Constantes.CADENA_VACIA.equals(valorNumericoStr) && !Constantes.SIMBOLO_X.equals(valorNumericoStr)) {
							valorNumerico = Integer.parseInt(valorNumericoStr);
						} else if (Constantes.CADENA_VACIA.equals(valorNumericoStr)) {
							valorNumerico = 0;
						} else if (Constantes.SIMBOLO_X.equals(valorNumericoStr)) {
							valorNumerico = -1;
						} else if (valorNumericoStr == null) {
							valorNumerico = 0;
						}

						if (mayor < valorNumerico) {
							mayor = valorNumerico;
							posicionDelMayor = i;
						}

					}
				}
			}

		}

		return posicionDelMayor;
	}

	public static int posicionDelNodoConMayorValorValidoIncluidoNodoFinal(
			String nodoPartida, String nodoFinal, int numeroCompuestos,
			String[][] matriz, String[] nodosVisitados) {

		int posicionDelMayor = -1, mayor = 0;

		if (matriz == null) {
			System.err.println("Error!, la matriz es nula");
			return posicionDelMayor;
		} else if (nodosVisitados == null) {
			System.err.println("Error!, la nodosVisitados es nulo");
			return posicionDelMayor;
		} else if (nodoPartida == null || nodoPartida.isEmpty()) {
			System.err.println("Error!, el nodoFinalTest es nulo");
			return posicionDelMayor;
		} else if (nodoFinal == null || nodoFinal.isEmpty()) {
			System.err.println("Error!, el nodoFinalTest es nulo");
			return posicionDelMayor;
		} else if (numeroCompuestos <= 0) {
			System.err.println("Error!, el numeroCompuestos es menor o igual a 0");
			return posicionDelMayor;
		} else {

			// System.out.println("posicionDelMayorValidoComponenteX. Nodos VISITADO: "
			// + Arrays.toString(nodosVisitados));

			if (nodoPartida.equals(matriz[0][0])) {
				for (int i = 0; i < numeroCompuestos; i++) {
					String valorNumericoStr = matriz[1][i];

					int valorNumerico = 0;
					if (valorNumericoStr != null && !Constantes.CADENA_VACIA.equals(valorNumericoStr) && !Constantes.SIMBOLO_X.equals(valorNumericoStr)) {
						valorNumerico = Integer.parseInt(valorNumericoStr);
					} else if (Constantes.CADENA_VACIA.equals(valorNumericoStr)) {
						valorNumerico = 0;
					} else if (Constantes.SIMBOLO_X.equals(valorNumericoStr)) {
						valorNumerico = -1;
					} else if (valorNumericoStr == null) {
						valorNumerico = 0;
					}

					if (mayor < valorNumerico) {
						mayor = valorNumerico;
						posicionDelMayor = i;
					}

				}
			} else {
				for (int i = 0; i < numeroCompuestos; i++) {
					if (!existeElemento(matriz[0][i], nodosVisitados)) {
						String valorNumericoStr = matriz[1][i];

						int valorNumerico = 0;
						if (valorNumericoStr != null && !Constantes.CADENA_VACIA.equals(valorNumericoStr) && !Constantes.SIMBOLO_X.equals(valorNumericoStr)) {
							valorNumerico = Integer.parseInt(valorNumericoStr);
						} else if (Constantes.CADENA_VACIA.equals(valorNumericoStr)) {
							valorNumerico = 0;
						} else if (Constantes.SIMBOLO_X.equals(valorNumericoStr)) {
							valorNumerico = -1;
						} else if (valorNumericoStr == null) {
							valorNumerico = 0;
						}

						if (mayor < valorNumerico) {
							mayor = valorNumerico;
							posicionDelMayor = i;
						}

					}
				}
			}
		}

		// System.out.println("$$$$$$$$$$$$$$$$$$$$$MAYOR: [" + mayor + "]");
		return posicionDelMayor;
	}

	public static boolean existeElemento(String nombreElemento, String[] nodos) {

		if (nodos == null) {
			System.err.println("Error!, la matriz es nula");
		} else if (nombreElemento == null) {
			System.err.println("Error!, la nombreElemento es nulo");
		} else if (nodos == null || nodos.length <= 0) {
			System.err.println("Error!, el nodos es nulo o vacio");
		} else {
			for (int i = 0; i < nodos.length; i++) {
				if (nombreElemento.equals(nodos[i])) {
					return true;
				}
			}
		}

		return false;
	}

	public static int numeroDeValoresNoNulos(String[] nodosVisitados) {

		int contador = 0;

		if (nodosVisitados == null) {
			return contador;
		}

		for (int i = 0; i < nodosVisitados.length; i++) {
			if (nodosVisitados[i] != null) {
				contador++;
			}
		}

		return contador;
	}

	public static String construirRutaConElementoLista(
			List<String> listaElementos) {

		String ruta = null;

		if (listaElementos == null) {
			return ruta;
		}

		ruta = "";

		int numeroElementos = listaElementos.size();
		int contador = 0;

		for (String elemento : listaElementos) {

			if (elemento == null) {
				ruta = "Error, no se puede llegar al nodo inicial.";
			} else {
				ruta += elemento;
				if (contador < numeroElementos - 1) {
					ruta += " -> ";
				}
			}

			contador++;
		}

		return ruta;
	}

	public static String[] llenarUnArrayDeCadenas(String conceptoElemento,
			int numeroElementos) {

		String[] arrayCadenas = null;

		if (numeroElementos <= 0) {
			return arrayCadenas;
		}

		int contador = 0;
		// System.out.println("llenarUnArrayDeCadenas.numeroElementos ->" +
		// numeroElementos);
		arrayCadenas = new String[numeroElementos];

		Scanner sc = new Scanner(System.in); // crear un objeto Scanner

		while (contador < numeroElementos) {
			System.out.print("Introduzca un(a) " + conceptoElemento + ": ");
			String cadena = sc.nextLine();

			arrayCadenas[contador] = cadena.toUpperCase();
			contador++;
		}

		return arrayCadenas;
	}

	public static String obtenerPrimerElementoArray(String[] nodosVisitados) {

		String primerElemento = null;

		if (nodosVisitados == null) {
			return primerElemento;
		}

		if (nodosVisitados.length > 0) {
			primerElemento = nodosVisitados[0];
		}

		return primerElemento;
	}

	public static void vaciarPrimerElementoArray(String[] nodosVisitados) {

		if (nodosVisitados != null) {
			nodosVisitados[0] = null;
		}

	}
}
