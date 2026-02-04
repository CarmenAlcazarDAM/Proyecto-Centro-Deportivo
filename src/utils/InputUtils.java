package utils;


import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtils {

    private static final Scanner sc = new Scanner(System.in);

    /**
     * Lee un entero cualquiera con control de errores.
     */
    public static int leeEntero(String mensaje) {
        int valor = 0;
        boolean esCorrecto = false;
        do {

            try {
                System.out.print(mensaje);
                valor = sc.nextInt();
                esCorrecto = true;
            } catch (InputMismatchException e) {
                System.out.println("⚠️ Error: debes introducir un número entero.");
                sc.next();
            }
        } while (!esCorrecto);
        return valor;
    }

    /**
     * Lee un entero en un rango [min, max].
     */
    public static int leeEntero(String mensaje, int min, int max) {
        int valor;
        do {
            valor = leeEntero(mensaje + " (" + min + " - " + max + "): ");
            if (valor < min || valor > max) {
                System.out.println("⚠️ El valor debe estar entre " + min + " y " + max + ".");
            }
        } while (valor < min || valor > max);
        return valor;
    }

    /**
     * Lee un double con control de errores.
     */
    public static double leeDouble(String mensaje) {
        double valor = 0;
        boolean esCorrecto = false;
        do {

            try {
                System.out.print(mensaje);
                valor = sc.nextDouble();
                esCorrecto = true;
            } catch (InputMismatchException e) {
                System.out.println("⚠️ Error: debes introducir un número decimal correcto.");
                sc.next();
            }
        } while (!esCorrecto);
        return valor;
    }

    /**
     * Lee una cadena no vacía (trimmed).
     */
    public static String leerCadenaNoVacia(String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = sc.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("⚠️ No se permite una cadena vacía. Inténtalo de nuevo.");
            }
        } while (texto.isEmpty());
        return texto;
    }
}