package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import util.MisUtilitarios;

public class Principal {

	public static void main(String[] args) {

		// Valores Por Defecto
		ejemploExitoso_ConValoresPorDefecto();
		ejemploNoExitoso_ConValoresPorDefecto();

		// Lectura por teclado
		ejemploConLecturaPorTeclado();

	}

	private static void ejemploConLecturaPorTeclado() {
		System.out.println("==============================================");
		// Permitir lectura por teclado
		Scanner sc = new Scanner(System.in); // crear un objeto Scanner

		System.out.print("Introduzca su nombre de fabrica: ");
		String nombreFabrica = sc.nextLine();

		System.out.print("Introduzca cantidad de compuestos: ");
		String numeroComponentesStr = sc.nextLine();

		int cantidadDeCompuestos = 0;
		if (!numeroComponentesStr.isEmpty()) {
			cantidadDeCompuestos = Integer.parseInt(numeroComponentesStr);
		}

		Fabrica fabrica = iniciarConValoresPorTeclado(nombreFabrica, cantidadDeCompuestos);

		// BUSQUEDA RUTA
		System.out.print("Introduzca NOMBRE del componente INICIAL [" + Arrays.toString(fabrica.getNombresCompuestos())
				+ "] -> ");
		String componenteInicio_1 = sc.nextLine().toUpperCase();

		System.out.print(
				"Introduzca NOMBRE del componente FINAL [" + Arrays.toString(fabrica.getNombresCompuestos()) + "] -> ");
		String componenteFinal_1 = sc.nextLine().toUpperCase();

		System.out.println("Estamos partiendo de...." + componenteInicio_1);
		System.out.println("Y siempre tenemos que llegar a..." + componenteFinal_1);

		ArrayList<String> rutaFinal_1 = fabrica.generarRuta(componenteInicio_1, componenteFinal_1);
		// System.out.println("RUTA ----> [" + rutaFinal_1.toString() + "]");
		String rutaFinal_1Str = MisUtilitarios.construirRutaConElementoLista(rutaFinal_1);

		mostrarResultadosFinales(fabrica, rutaFinal_1Str);

		sc.close();
		System.out.println("==============================================");
	}

	private static void ejemploExitoso_ConValoresPorDefecto() {
		System.out.println("==============================================");
		// COMPUESTOS (6 compuestos): A, B, C, D, E, F
		Fabrica fabrica = iniciarConValoresPorDefecto("Quimica S.A", 6);

		// BUSQUEDA RUTA
		String componenteInicio_1 = Constantes.TEMPLATE_1_NOMBRE_COMPONENTE_PRUEBA_1_INICIO;
		String componenteFinal_1 = Constantes.TEMPLATE_1_NOMBRE_COMPONENTE_PRUEBA_1_FINAL;

		System.out.println("Estamos partiendo de...." + componenteInicio_1);
		System.out.println("Y siempre tenemos que llegar a..." + componenteFinal_1);

		ArrayList<String> rutaFinal_1 = fabrica.generarRuta(componenteInicio_1, componenteFinal_1);
		// System.out.println("RUTA ----> [" + rutaFinal_1.toString() + "]");
		String rutaFinal_1Str = MisUtilitarios.construirRutaConElementoLista(rutaFinal_1);

		mostrarResultadosFinales(fabrica, rutaFinal_1Str);

		System.out.println("==============================================");

	}

	private static void mostrarResultadosFinales(Fabrica fabrica, String rutaFinal) {
		System.out.println("-->" + fabrica.getName());
		String[][] matriz = fabrica.getGananciasPorCompuestos();
		int nroFilas = fabrica.getNumeroCompuestos();
		int nroColumnas = fabrica.getNumeroCompuestos();
		String[] nombresComponentes = fabrica.getNombresCompuestos();
		MisUtilitarios.mostrarArrayCadenas("Sus componentes de la fabrica", nombresComponentes);
		MisUtilitarios.mostrarMatrices("Componentes vs. Ganancias", matriz, nroFilas, nroColumnas);
		System.out.println("RUTA: [" + rutaFinal + "]");

	}

	private static void ejemploNoExitoso_ConValoresPorDefecto() {

		System.out.println("==============================================");
		// COMPUESTOS: A, B, C, D, E, F
		Fabrica fabrica = iniciarConValoresPorDefecto("Quimica S.A", 6);

		// BUSQUEDA RUTA
		String componenteInicio_2 = Constantes.TEMPLATE_1_NOMBRE_COMPONENTE_PRUEBA_2_INICIO;
		String componenteFinal_2 = Constantes.TEMPLATE_1_NOMBRE_COMPONENTE_PRUEBA_2_FINAL;

		System.out.println("Estamos partiendo de...." + componenteInicio_2);
		System.out.println("Y siempre tenemos que llegar a..." + componenteFinal_2);

		ArrayList<String> rutaFinal_2 = fabrica.generarRuta(componenteInicio_2, componenteFinal_2);
		// System.out.println("RUTA ----> [" + rutaFinal_2.toString() + "]");
		String rutaFinal_2Str = MisUtilitarios.construirRutaConElementoLista(rutaFinal_2);

		mostrarResultadosFinales(fabrica, rutaFinal_2Str);

		System.out.println("==============================================");

	}

	public static Fabrica iniciarConValoresPorDefecto(String name, int numeroCompuestos) {

		Fabrica fabrica = new Fabrica(name, numeroCompuestos);
		String[] nombresCompuestos = { "A", "B", "C", "D", "E", "F" };
		fabrica.setNombresCompuestos(nombresCompuestos);
		fabrica.llenarCompuestosValoresPorDefecto();

		return fabrica;

	}

	private static Fabrica iniciarConValoresPorTeclado(String nombreFabrica, int cantidadDeCompuestos) {

		Fabrica fabrica = new Fabrica(nombreFabrica, cantidadDeCompuestos);

		System.out.println("======LLENANDO... COMPUESTOS===========");
		String conceptoElemento = "COMPUESTO";
		String[] nombresCompuestos = MisUtilitarios.llenarUnArrayDeCadenas(conceptoElemento, cantidadDeCompuestos);
		fabrica.setNombresCompuestos(nombresCompuestos);
		fabrica.llenarCompuestosValoresPorTeclado();
		System.out.println("========================================");

		return fabrica;
	}
}