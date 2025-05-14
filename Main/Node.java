package Main;
/**
 * Clase que representa el nodo de un árbol AVL.
 */
public class Node {
    int value;     // Valor del nodo
    int height;    // Altura del nodo
    Node left;     // Hijo izquierdo
    Node right;    // Hijo derecho

    /**
     * Constructor del nodo.
     * @param value Valor a asignar al nodo
     */
    public Node(int value) {
        this.value = value;
        this.height = 1; // Altura inicial es 1
    }
}
