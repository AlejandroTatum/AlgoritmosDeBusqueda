package search;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import util.Node;


public class SLLSearch {
    public static <T> Node<T> findFirst(Node<T> head, T key) {
        Node<T> current = head;

        while (current != null) {
            if (current.data.equals(key)) {
                return current; // primer match
            }
            current = current.next;
        }

        return null; // no encontrado
    }

    public static <T> Node<T> findLast(Node<T> head, T key) {
        Node<T> current = head;
        Node<T> last = null;

        while (current != null) {
            if (current.data.equals(key)) {
                last = current;   // actualiza la última ocurrencia encontrada
            }
            current = current.next;
        }

        return last; // null en caso de no haber
    }

    public static <T> List<Node<T>> findAll(Node<T> head, Predicate<Node<T>> p) {
        List<Node<T>> results = new ArrayList<>();
        Node<T> current = head;

        while (current != null) {
            if (p.test(current)) {   // si el nodo cumple el predicado
                results.add(current);
            }
            current = current.next;
        }

        return results; // lista de nodos que cumplen la condición
    }
}
