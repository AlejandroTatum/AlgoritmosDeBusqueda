package search;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import sorting.BubbleSort;
import util.Validator;

public class ArraySearch {

    public static <T> int findFirst(T[] array, T key) {
        if (!Validator.checkArray(array)) return -1;

        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> int findLast(T[] array, T key) {
        if (!Validator.checkArray(array)) return -1;

        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> List<Integer> findAll(T[] array, Predicate<T> p) {
        List<Integer> results = new ArrayList<>();
        if (!Validator.checkArray(array)) return results;

        for (int i = 0; i < array.length; i++) {
            if (p.test(array[i])) {
                results.add(i);
            }
        }
        return results;
    }

    public static <T> int findSentinel(T[] array, T key) {
        if (!Validator.checkArray(array)) return -1;

        int n = array.length;
        T lastOriginal = array[n - 1];

        // Ubicamos el centinela
        array[n - 1] = key;
        int i = 0;

        while (!array[i].equals(key)) {
            i++;
        }

        array[n - 1] = lastOriginal;

        if (i < n - 1 || (lastOriginal != null && lastOriginal.equals(key))) {
            return i;
        }
        return -1;
    }

    // PASO 5: Búsqueda Binaria (Requiere ordenamiento previo)
    public static <T extends Comparable<T>> int binarySearch(T[] array, T key) {
        if (!Validator.checkArray(array)) return -1;

        System.out.println("   [Sistema] Ordenando arreglo con BubbleSort antes de búsqueda binaria...");
        BubbleSort.ordenarAscendente(array);

        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            T midVal = array[mid];
            int cmp = midVal.compareTo(key);

            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}