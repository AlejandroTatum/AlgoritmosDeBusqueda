package util;

public class Validator {

    // Validar Arreglos
    public static <T> boolean checkArray(T[] array) {
        if (array == null) {
            System.err.println("[Error] El arreglo es nulo.");
            return false;
        }
        if (array.length == 0) {
            System.err.println("[Advertencia] El arreglo está vacío.");
            return false;
        }
        if (array.length == 1) {
            System.out.println("[Info] El arreglo tiene un solo elemento (Caso borde).");
        }
        return true;
    }

    public static <T> boolean checkSLL(Node<T> head) {
        if (head == null) {
            System.err.println("[Advertencia] La lista está vacía o es nula.");
            return false;
        }
        if (head.next == null) {
            System.out.println("[Info] La lista tiene un solo nodo (Caso borde).");
        }
        return true;
    }
}