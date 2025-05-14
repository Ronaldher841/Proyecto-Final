package Main;
import java.util.*;

// Raíz del árbol AVL. El punto de entrada para acceder a la estructura del árbol.

public class AVLTree {
    public Node root; // Raíz del árbol

    public AVLTree() {
        this.root = null; //Inicializa un árbol AVL vacío estableciendo la raíz en null.
    }

    /**
     * Obtiene la altura de un nodo.
     */
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    /**
     * Actualiza la altura del nodo basándose en la de sus hijos.
     */
    private void updateHeight(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }

    /**
     * Obtiene el factor de balanceo de un nodo.
     */
    private int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    /**
     * Rotación simple a la derecha.
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x; // Nueva raíz del subárbol
    }

    /**
     * Rotación simple a la izquierda.
     */
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y; // Nueva raíz del subárbol
    }

    /**
     * Inserta un valor en el árbol AVL con balanceo automático.
     */
    public Node insert(Node node, int value) {
        // Inserción normal de BST
        if (node == null)
            return new Node(value);

        if (value < node.value)
            node.left = insert(node.left, value);
        else if (value > node.value)
            node.right = insert(node.right, value);
        else
            return node; // No se permiten duplicados

        // Actualizar altura y obtener balance
        updateHeight(node);
        int balance = getBalance(node);

        // Aplicar las rotaciones según el tipo de desequilibrio
        if (balance > 1 && value < node.left.value)
            return rightRotate(node); // Izquierda-Izquierda

        if (balance < -1 && value > node.right.value)
            return leftRotate(node);  // Derecha-Derecha

        if (balance > 1 && value > node.left.value) { // Izquierda-Derecha
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && value < node.right.value) { // Derecha-Izquierda
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node; // Retornar el nodo (ya balanceado)
    }

    /**
     * Imprime el árbol AVL jerárquicamente usando slashes `/` y `\`.
     */
   //  Esta función utiliza una técnica de recorrido por niveles (BFS) para imprimir la estructura del árbol
   //  de una manera visualmente intuitiva.
    public void printTree(Node root) {
        if (root == null) {
            System.out.println("Árbol vacío.");
            return;
        }

        int maxLevel = height(root); // Altura máxima
        List<Node> nodes = new ArrayList<>();
        nodes.add(root);
        int level = 1;

        while (!allElementsNull(nodes)) {
            int floor = maxLevel - level;
            int edgeLines = (int) Math.pow(2, Math.max(floor - 1, 0));
            int firstSpaces = (int) Math.pow(2, floor) - 1;
            int betweenSpaces = (int) Math.pow(2, floor + 1) - 1;

            printWhitespaces(firstSpaces);

            List<Node> newNodes = new ArrayList<>();
            for (Node node : nodes) {
                if (node != null) {
                    System.out.print(String.format("%2d", node.value)); // Imprime valor
                    newNodes.add(node.left);
                    newNodes.add(node.right);
                } else {
                    System.out.print("  ");
                    newNodes.add(null);
                    newNodes.add(null);
                }
                printWhitespaces(betweenSpaces);
            }
            System.out.println();

            // Dibujar ramas '/' y '\'
            for (int i = 1; i <= edgeLines; i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    printWhitespaces(firstSpaces - i);

                    if (nodes.get(j) == null) {
                        printWhitespaces(edgeLines + edgeLines + i + 1);
                        continue;
                    }

                    if (nodes.get(j).left != null)
                        System.out.print("/");
                    else
                        printWhitespaces(1);

                    printWhitespaces(i + i - 1);

                    if (nodes.get(j).right != null)
                        System.out.print("\\");
                    else
                        printWhitespaces(1);

                    printWhitespaces(edgeLines + edgeLines - i);
                }
                System.out.println();
            }

            nodes = newNodes;
            level++;
        }

        System.out.println();
    }

    /**
     * Imprime una cantidad de espacios.
     */
    private void printWhitespaces(int count) {
        for (int i = 0; i < count; i++) System.out.print(" ");
    }

    /**
     * Verifica si todos los nodos de una lista son nulos.
     */
    private boolean allElementsNull(List<Node> list) {
        for (Node node : list) {
            if (node != null) return false;
        }
        return true;
    }
}
