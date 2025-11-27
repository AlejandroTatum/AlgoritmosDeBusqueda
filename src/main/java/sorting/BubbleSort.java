package sorting;

public class BubbleSort {

    public static <T extends Comparable<T>> void ordenarAscendente(T[] array) {
        sortInternal(array);
    }

    private static <T extends Comparable<T>> void sortInternal(T[] array) {
        if (array == null) return;

        int length = array.length;
        boolean hasSwaps;

        for (int i = length - 1; i > 0; i--) {
            hasSwaps = false;

            for (int j = 0; j < i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                    hasSwaps = true;
                }
            }

            if (!hasSwaps) break;
        }
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}