package util;

public class Validator {
    public static <T> void validateArray(T[] array) {
        if (array == null) {
            System.out.println("El arreglo no existe");
            return;
        }

        if (array.length == 0) {
            System.out.println("El arreglo está vacío");
            return;
        }

        if (array.length == 1) {
            System.out.println("El arreglo tiene 1 elemento");
        }
    }

    public static <T> void validateSLL(Node<T> head) {

        if (head == null) {
            System.out.println("La lista no existe");
            return;
        }

        if (head.next == null) {
            System.out.println("La lista tiene un solo nodo");
        }
    }
}