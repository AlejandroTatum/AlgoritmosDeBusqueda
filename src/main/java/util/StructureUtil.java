package util;

public class StructureUtil {

    public static <T> Node<T> arrayToSLL(T[] array) {
        if (array == null || array.length == 0) return null;

        Node<T> head = new Node<>(array[0]);
        Node<T> current = head;

        for (int i = 1; i < array.length; i++) {
            current.next = new Node<>(array[i]);
            current = current.next;
        }
        return head;
    }

    public static <T> void printResult(String label, int index, T[] array) {
        if (index >= 0 && index < array.length) {
            System.out.println(label + ": Encontrado [" + array[index] + "] en índice " + index);
        } else {
            System.out.println(label + ": NO encontrado.");
        }
    }
}