package Main;
//Alumno: Ronald Isaias Godinez Hernández
//Carne: 202402155
//Estructura de Datos

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Crea una instancia de la clase AVLTree, que contendrá la lógica del árbol AVL.
        AVLTree tree = new AVLTree();
        // Crea una instancia de la clase Scanner para leer la entrada del usuario desde la consola.
        Scanner scanner = new Scanner(System.in);
        // Declara una variable String para almacenar la entrada del usuario.
        String input;

        System.out.println("Árbol AVL interactivo.");
        // Explica al usuario cómo ingresar números para insertarlos en el árbol.
        System.out.println("Ingrese números enteros uno a uno.");
        // Indica las opciones para salir del programa.
        System.out.println("Escriba 'exit' o -1 para salir.\n");

        // Inicia un bucle do-while que continuará hasta que el usuario decida salir.
        do {
            // Solicita al usuario que ingrese un número.
            System.out.print("Ingrese un número: ");
            // Lee la línea de texto ingresada por el usuario.
            input = scanner.nextLine();

            // Comprueba si la entrada del usuario es "exit" (ignorando mayúsculas/minúsculas) o "-1".
            // Si es así, sale del bucle, terminando el programa.
            if (input.equalsIgnoreCase("exit") || input.equals("-1")) {
                break;
            }

            // Intenta convertir la entrada del usuario a un entero.
            try {
                // Convierte la cadena de entrada a un entero.
                int number = Integer.parseInt(input);
                // Inserta el número en el árbol AVL. La función 'insert' devuelve la nueva raíz del árbol
                // después de la inserción y posibles rotaciones para mantener el equilibrio AVL.
                tree.root = tree.insert(tree.root, number);

                // Imprime un mensaje indicando que el número ha sido insertado.
                System.out.println("\n Árbol AVL después de insertar " + number + ":");
                // Llama a la función 'printTree' para imprimir la estructura actual del árbol AVL.
                // Esto permite al usuario ver cómo el árbol cambia con cada inserción.
                tree.printTree(tree.root);
            } catch (NumberFormatException e) {
                // Si la entrada del usuario no se puede convertir a un entero (por ejemplo, ingresa texto),
                // este bloque catch se ejecuta.
                System.out.println(" Entrada inválida. Por favor, ingrese un número válido.");
            }

        } while (true); // El bucle continúa indefinidamente hasta que se ejecuta la instrucción 'break'.

        // Imprime un mensaje de finalización del programa después de que el usuario sale del bucle.
        System.out.println("\n Programa finalizado.");
        // Cierra el objeto Scanner para liberar los recursos del sistema.
        scanner.close();
    }
}
