package search;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import util.Node;
import util.Validator;

public class SLLSearch {

    // PASO 1: Primera Ocurrencia (SLL)
    public static <T> Node<T> findFirst(Node<T> head, T key) {
        if (!Validator.checkSLL(head)) return null;

        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(key)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // PASO 2: Última Ocurrencia (SLL)
    public static <T> Node<T> findLast(Node<T> head, T key) {
        if (!Validator.checkSLL(head)) return null;

        Node<T> current = head;
        Node<T> last = null;

        while (current != null) {
            if (current.data.equals(key)) {
                last = current;
            }
            current = current.next;
        }
        return last;
    }

    // PASO 3: FindAll con Predicado (SLL)
    public static <T> List<Node<T>> findAll(Node<T> head, Predicate<Node<T>> p) {
        List<Node<T>> results = new ArrayList<>();
        if (!Validator.checkSLL(head)) return results;

        Node<T> current = head;
        while (current != null) {
            if (p.test(current)) {
                results.add(current);
            }
            current = current.next;
        }
        return results;
    }
}