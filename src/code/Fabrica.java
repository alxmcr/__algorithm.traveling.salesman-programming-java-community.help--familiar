package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import util.MisUtilitarios;

class Fabrica {
	private String[][]	gananciasPorCompuestos;
	private String		name;
	private String[]	nombresCompuestos;
	private int			numeroCompuestos;

	public Fabrica(String name, int numeroCompuestos) {
		this.name = name;
		this.numeroCompuestos = numeroCompuestos;

		if (numeroCompuestos > 0) {
			this.nombresCompuestos = new String[numeroCompuestos];
			this.gananciasPorCompuestos = new String[numeroCompuestos][numeroCompuestos];
		} else {
			System.err.println("Error!, no se pudo crear la matriz de ganancias");
		}
	}

	public String[][] getGananciasPorCompuestos() {
		return gananciasPorCompuestos;
	}

	public void setGananciasPorCompuestos(String[][] gananciasPorCompuestos) {
		this.gananciasPorCompuestos = gananciasPorCompuestos;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getNombresCompuestos() {
		return nombresCompuestos;
	}

	public void setNombresCompuestos(String[] nombresCompuestos) {
		this.nombresCompuestos = nombresCompuestos;
	}

	public int getNumeroCompuestos() {
		return numeroCompuestos;
	}

	public void setNumeroCompuestos(int numeroCompuestos) {
		this.numeroCompuestos = numeroCompuestos;
	}

	public void llenarNombreDeCompuestosPorDefecto(
			String templateNombreComponente) {
		if (this.nombresCompuestos != null) {
			for (int i = 0; i < nombresCompuestos.length; i++) {
				this.nombresCompuestos[i] = templateNombreComponente + i;
			}
		} else {
			System.err.println("Error!, la matriz de nombres de compuestos, no esta creada");
		}
	}

	public void mostrarFabrica() {
		System.out.println("==============================");
		System.out.println("Fabrica: " + this.name);
		System.out.println("------------------------------");
		System.out.println("Compuestos");
		System.out.println(Arrays.toString(this.nombresCompuestos));
		System.out.println("______________________________");
		MisUtilitarios.mostrarMatrices("Matriz Ganancias", this.gananciasPorCompuestos, this.numeroCompuestos, this.numeroCompuestos);
		System.out.println("==============================");

	}

	public String[][] obtenerMatrizConGananciasComponenteX(int posicionCompuesto) {

		String[][] matrizCompuestosConGanancias = null;

		if (this.gananciasPorCompuestos == null) {
			System.err.println("Error!, la matriz de ganancias no esta creada");
			return matrizCompuestosConGanancias;
		}

		if (posicionCompuesto < 0) {
			// System.err.println("Error!, posicionCompuesto es igual o menor a 0");
			return matrizCompuestosConGanancias;
		}

		if (this.numeroCompuestos <= 0) {
			System.err.println("Error!, numeroCompuestos es igual o menor a 0");
			return matrizCompuestosConGanancias;
		}

		matrizCompuestosConGanancias = new String[2][this.numeroCompuestos];

		for (int i = 0; i < nombresCompuestos.length; i++) {
			matrizCompuestosConGanancias[0][i] = this.nombresCompuestos[i];
			matrizCompuestosConGanancias[1][i] = this.gananciasPorCompuestos[posicionCompuesto][i];
		}

		return matrizCompuestosConGanancias;
	}

	public int obtenerPosicionCompuesto(String nombreCompuesto) {

		int posicionCompuesto = -1;

		if (nombreCompuesto == null) {
			return posicionCompuesto;
		}
		if (this.nombresCompuestos != null) {
			for (int i = 0; i < nombresCompuestos.length; i++) {
				String aux = nombresCompuestos[i];
				if (nombreCompuesto.equals(aux)) {
					posicionCompuesto = i;
				}

			}
		} else {
			System.err.println("Error!, la matriz de nombres de compuestos, no esta creada");
		}

		return posicionCompuesto;
	}

	public ArrayList<String> generarRuta(String nodoPartida, String nodoFinal) {
		String[] nodosVisitados = new String[numeroCompuestos];

		ArrayList<String> rutaFinal = null;
		boolean isTerminoLaRuta = false;

		if (nodoPartida == null || nodoPartida.isEmpty()) {
			return rutaFinal;
		}
		if (nodoFinal == null || nodoFinal.isEmpty()) {
			return rutaFinal;
		} else {
			rutaFinal = new ArrayList<String>();
			rutaFinal.add(nodoPartida);

			String nombreCompuesto = nodoPartida;

			for (int i = 0; i < this.numeroCompuestos; i++) {
				// RUTA_1 Comenzando de "C"
				nodosVisitados[i] = nombreCompuesto;

				int posicionCompuesto = this.obtenerPosicionCompuesto(nombreCompuesto);
				String[][] matrizGananaciasComponente_1 = this.obtenerMatrizConGananciasComponenteX(posicionCompuesto);

				if (matrizGananaciasComponente_1 != null && !isTerminoLaRuta) {

					// getMatrizConGananciasComponenteX - C
					// MisUtilitarios.mostrarMatrices("Matrix ORIGINAL de [" +
					// nombreCompuesto + "]", matrizGananaciasComponente_1, 2,
					// numeroCompuestos);
					// MisUtilitarios.ordenarMatricesDeMayorAMenor(matrizGananaciasComponente_1,
					// numeroCompuestos);
					// MisUtilitarios.mostrarMatrices("Matrix ORDENADA de [" +
					// nombreCompuesto + "]", matrizGananaciasComponente_1, 2,
					// numeroCompuestos);

					String nameComponenteMaximoValido = null;
					String valorGananciaComponenteMaximoValido = null;

					int nro_intentos = 0;
					boolean continuar = true;
					do {
						int posicionDelMayorValidoComponenteX = MisUtilitarios.posicionDelNodoConMayorValorValidoExceptoNodoFinal(nodoFinal, numeroCompuestos, matrizGananaciasComponente_1, nodosVisitados);

						if (posicionDelMayorValidoComponenteX >= 0) {
							nameComponenteMaximoValido = matrizGananaciasComponente_1[0][posicionDelMayorValidoComponenteX];
							valorGananciaComponenteMaximoValido = matrizGananaciasComponente_1[1][posicionDelMayorValidoComponenteX];
						}

						if (nameComponenteMaximoValido == null) {
							// System.err.println("generarRuta. Error, nameComponenteMaximoValido es: nulo");
							continuar = false;
						} else if (!nodoFinal.equals(nameComponenteMaximoValido)) {
							continuar = false;
						}

						nro_intentos++;

					} while (nro_intentos < this.numeroCompuestos && continuar);

					// System.out.println("nameComponenteMaximoValido: [" +
					// nameComponenteMaximoValido + "]");
					// System.out.println("valorGananciaComponenteMaximoValido: ["
					// + valorGananciaComponenteMaximoValido + "]");

					if (nameComponenteMaximoValido == null) {
						// System.out.println("//////////////////////////////////////////////////");

						posicionCompuesto = this.obtenerPosicionCompuesto(nodoFinal);

						String primerElemento = MisUtilitarios.obtenerPrimerElementoArray(nodosVisitados);

						if (nodoFinal.equals(primerElemento)) {
							MisUtilitarios.vaciarPrimerElementoArray(nodosVisitados);
						}

						int cantidadDeNodosVisitados = MisUtilitarios.numeroDeValoresNoNulos(nodosVisitados);
						boolean condicionUltimoNodo = cantidadDeNodosVisitados == this.numeroCompuestos - 1;

						if (condicionUltimoNodo) {

							int posicionNodoFinal = MisUtilitarios.posicionDelNodoConMayorValorValidoIncluidoNodoFinal(nodoPartida, nodoFinal, numeroCompuestos, matrizGananaciasComponente_1, nodosVisitados);

							if (posicionNodoFinal >= 0) {
								nameComponenteMaximoValido = matrizGananaciasComponente_1[0][posicionNodoFinal];
								valorGananciaComponenteMaximoValido = matrizGananaciasComponente_1[1][posicionNodoFinal];
							}

							int valorGananciaComponenteMaximoValidoNumerico = 0;
							if (valorGananciaComponenteMaximoValido != null) {
								valorGananciaComponenteMaximoValidoNumerico = Integer.parseInt(valorGananciaComponenteMaximoValido);
							}

							if (valorGananciaComponenteMaximoValidoNumerico > 0) {
								if (!MisUtilitarios.existeElemento(nameComponenteMaximoValido, nodosVisitados)) {
									nodosVisitados[i] = nodoFinal;
									isTerminoLaRuta = true;
								}

							}
						}
						// System.out.println("//////////////////////////////////////////////////");
					}

					// CONSTRUYENDO LA RUTA
					rutaFinal.add(nameComponenteMaximoValido);

					nombreCompuesto = nameComponenteMaximoValido;
				}

			}

		}

		return rutaFinal;

	}

	public void llenarCompuestosValoresPorDefecto() {

		if (this.gananciasPorCompuestos != null) {
			// Para el compuesto "A" (posicion: 0)
			this.gananciasPorCompuestos[0][0] = Constantes.CADENA_VACIA; // A ->
																			// A
			this.gananciasPorCompuestos[0][1] = "250"; // A -> B
			this.gananciasPorCompuestos[0][2] = "300"; // A -> C
			this.gananciasPorCompuestos[0][3] = "100"; // A -> D
			this.gananciasPorCompuestos[0][4] = Constantes.SIMBOLO_X; // A -> E
			this.gananciasPorCompuestos[0][5] = "170"; // A -> F

			// Para el compuesto "B" (posicion: 1)
			this.gananciasPorCompuestos[1][0] = "250"; // B -> A
			this.gananciasPorCompuestos[1][1] = Constantes.CADENA_VACIA; // B ->
																			// B
			this.gananciasPorCompuestos[1][2] = "160"; // B -> C
			this.gananciasPorCompuestos[1][3] = Constantes.SIMBOLO_X; // B -> D
			this.gananciasPorCompuestos[1][4] = "270"; // B -> E
			this.gananciasPorCompuestos[1][5] = "150"; // B -> F

			// Para el compuesto "C" (posicion: 2)
			this.gananciasPorCompuestos[2][0] = "300"; // C -> A
			this.gananciasPorCompuestos[2][1] = "160"; // C -> B
			this.gananciasPorCompuestos[2][2] = Constantes.CADENA_VACIA; // C ->
																			// C
			this.gananciasPorCompuestos[2][3] = "230"; // C -> D
			this.gananciasPorCompuestos[2][4] = Constantes.SIMBOLO_X; // C -> E
			this.gananciasPorCompuestos[2][5] = "140"; // C -> F

			// Para el compuesto "D" (posicion: 3)
			this.gananciasPorCompuestos[3][0] = "100"; // D -> A
			this.gananciasPorCompuestos[3][1] = Constantes.SIMBOLO_X; // D -> B
			this.gananciasPorCompuestos[3][2] = "230"; // D ->
														// C
			this.gananciasPorCompuestos[3][3] = Constantes.CADENA_VACIA; // D ->
																			// D
			this.gananciasPorCompuestos[3][4] = "220"; // D -> E
			this.gananciasPorCompuestos[3][5] = Constantes.SIMBOLO_X; // D -> F

			// Para el compuesto "E" (posicion: 4)
			this.gananciasPorCompuestos[4][0] = Constantes.SIMBOLO_X; // E -> A
			this.gananciasPorCompuestos[4][1] = "270"; // E -> B
			this.gananciasPorCompuestos[4][2] = Constantes.SIMBOLO_X; // E ->
			// C
			this.gananciasPorCompuestos[4][3] = "220"; // E ->
														// D
			this.gananciasPorCompuestos[4][4] = Constantes.CADENA_VACIA; // E ->
																			// E
			this.gananciasPorCompuestos[4][5] = "100"; // E -> F

			// Para el compuesto "F" (posicion: 5)
			this.gananciasPorCompuestos[5][0] = "170"; // E -> A
			this.gananciasPorCompuestos[5][1] = "150"; // E -> B
			this.gananciasPorCompuestos[5][2] = "140"; // E ->
			// C
			this.gananciasPorCompuestos[5][3] = Constantes.SIMBOLO_X; // E ->
			// D
			this.gananciasPorCompuestos[5][4] = "100"; // E ->
														// E
			this.gananciasPorCompuestos[5][5] = Constantes.CADENA_VACIA; // E ->
																			// F

		} else {
			System.err.println("Error!, la matriz de ganancias no esta creada");
		}

	}

	public void llenarCompuestosValoresPorTeclado() {
		if (this.gananciasPorCompuestos != null && this.nombresCompuestos != null && this.numeroCompuestos > 0) {

			// Permitir lectura por teclado
			Scanner sc = new Scanner(System.in); // crear un objeto Scanner

			for (int i = 0; i < this.numeroCompuestos; i++) {

				System.out.println("GANANCIAS PARA EL COMPUESTO: '" + this.nombresCompuestos[i] + "'");

				for (int j = 0; j < this.numeroCompuestos; j++) {
					System.out.print("Introduzca su ganancia [" + this.nombresCompuestos[i] + "->" + this.nombresCompuestos[j] + "]: ");
					String gananciaCompuestoStr = sc.nextLine();
					this.gananciasPorCompuestos[i][j] = gananciaCompuestoStr.toUpperCase();
				}
			}

		} else {
			System.err.println("Error!, la matriz de ganancias no esta creada");
		}

	}
}
