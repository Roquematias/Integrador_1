package Actividad;

import java.util.Random;
import java.util.Scanner;

public class Matriz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean ejecutar = true;

        while (ejecutar) {
            int dimension = solicitarDimension(scanner);
            int[][] matriz = crearMatriz(dimension, scanner);
            mostrarMatriz(matriz);

            int[] valoresCentrales = obtenerValoresCentrales(matriz);
            System.out.println("Valores centrales:");
            mostrarArray(valoresCentrales);

            ordenarArray(valoresCentrales);
            System.out.println("Valores centrales ordenados:");
            mostrarArray(valoresCentrales);

            double promedio = calcularPromedioDeMatrices(matriz, valoresCentrales);
            System.out.printf("Promedio de ambas matrices: ", promedio);

            System.out.print("¿Desea iniciar nuevamente la ejecucion del programa? (SI/NO): ");
            ejecutar = scanner.next().equalsIgnoreCase("SI");
        }
        scanner.close();
    }

    private static int solicitarDimension(Scanner scanner) {
        int dimension;
        do {
            System.out.print("Ingrese la dimensión de la matriz (impar entre 3 y 15): ");
            dimension = scanner.nextInt();
        } while (dimension < 3 || dimension > 15 || dimension % 2 == 0);
        return dimension;
    }

    private static int[][] crearMatriz(int dimension, Scanner scanner) {
        int[][] matriz = new int[dimension][dimension];
        Random random = new Random();

        for (int i = 0; i < dimension - 1; i++) {
            for (int j = 0; j < dimension; j++) {
                matriz[i][j] = 10 + random.nextInt(90);
            }
        }

        System.out.println("Ingrese los valores de la ultima fila:");
        for (int j = 0; j < dimension; j++) {
            int valor;
            do {
                System.out.printf("Valor (entre 10 y 99): ", j + 1);
                valor = scanner.nextInt();
            } while (valor < 10 || valor > 99);
            matriz[dimension - 1][j] = valor;
        }
        return matriz;
    }

    private static void mostrarMatriz(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int elemento : fila) {
                System.out.print(elemento + " ");
            }
            System.out.println();
        }
    }

    public static int[] obtenerValoresCentrales(int[][] matriz) {
        int dimension = matriz.length;
        int centro = dimension / 2;
        return new int[] {
                matriz[centro - 1][centro - 1], matriz[centro - 1][centro], matriz[centro - 1][centro + 1],
                matriz[centro][centro - 1], matriz[centro][centro], matriz[centro][centro + 1],
                matriz[centro + 1][centro - 1], matriz[centro + 1][centro], matriz[centro + 1][centro + 1]
        };
    }

    private static void mostrarArray(int[] array) {
        for (int elemento : array) {
            System.out.print(elemento + " ");
        }
        System.out.println();
    }

    private static void ordenarArray(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static double calcularPromedioDeMatrices(int[][] matriz, int[] matrizValoresCentral) {
        int sumaBidimensional = 0;
        int elementosBidimensional = 0;
        for (int[] fila : matriz) {
            for (int valor : fila) {
                sumaBidimensional += valor;
                elementosBidimensional++;
            }
        }
        double promedioBidimensional = (double) sumaBidimensional / elementosBidimensional;
        System.out.printf("Suma total de la matriz bidimensional: ", sumaBidimensional);
        System.out.printf("Cantidad de elementos: ", elementosBidimensional);
        System.out.printf("Promedio de la matriz bidimensional: ", promedioBidimensional);

        int sumaUnidimensional = 0;
        for (int valor : matrizValoresCentral) {
            sumaUnidimensional += valor;
        }
        double promedioUnidimensional = (double) sumaUnidimensional / matrizValoresCentral.length;
        System.out.printf("Suma total de la matriz unidimensional: ", sumaUnidimensional);
        System.out.printf("Cantidad de elementos: ", matrizValoresCentral.length);
        System.out.printf("Promedio de la matriz unidimensional: ", promedioUnidimensional);

        return (promedioBidimensional + promedioUnidimensional) / 2;
    }
}
