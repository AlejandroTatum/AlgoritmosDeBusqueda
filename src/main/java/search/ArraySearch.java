package search;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import sorting.BubbleSort;
import util.Validator;

public class ArraySearch {

    public static <T> int findFirst(T[] array, T key) {
        if (!Validator.checkArray(array) || key == null) return -1;

        for (int i = 0; i < array.length; i++) {
            // Usamos equals de manera segura
            if (array[i] != null && array[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> int findLast(T[] array, T key) {
        if (!Validator.checkArray(array) || key == null) return -1;

        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] != null && array[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> List<Integer> findAll(T[] array, Predicate<T> p) {
        List<Integer> results = new ArrayList<>();
        if (!Validator.checkArray(array) || p == null) return results;

        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && p.test(array[i])) {
                results.add(i);
            }
        }
        return results;
    }

    public static <T> int findSentinel(T[] array, T key) {
        if (!Validator.checkArray(array) || key == null) return -1;

        int n = array.length;
        T lastOriginal = array[n - 1];

        // 1. Colocar centinela en la última posición
        array[n - 1] = key;
        int i = 0;

        // 2. Bucle optimizado (sin chequeo de límites i < n)
        while (!array[i].equals(key)) {
            i++;
        }

        // 3. Restaurar el valor original
        array[n - 1] = lastOriginal;

        // 4. Verificar si lo encontramos de verdad o era el centinela forzado
        // Caso especial: El valor original en la última posición ERA la clave
        if (i < n - 1 || (lastOriginal != null && lastOriginal.equals(key))) {
            return i;
        }
        return -1;
    }

    public static <T extends Comparable<T>> int binarySearch(T[] array, T key) {
        if (!Validator.checkArray(array) || key == null) return -1;

        // La búsqueda binaria REQUIERE datos ordenados.
        // Ordenamos aquí para cumplir la precondición, aunque sea costoso.
        System.out.println("   [Sistema] Ordenando datos (BubbleSort) para Búsqueda Binaria...");
        BubbleSort.ordenarAscendente(array);

        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            T midVal = array[mid];

            if (midVal == null) continue; // Salta valores nulos

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