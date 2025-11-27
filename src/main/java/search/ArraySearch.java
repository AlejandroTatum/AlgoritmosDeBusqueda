package search;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import sorting.BubbleSort;

public class ArraySearch {

    /**
     * Busca la primera ocurrencia de una clave en el arreglo.
     * Equivalente a SLLSearch.findFirst
     */
    public static <T> int findFirst(T[] array, T key) {
        // Recorremos el arreglo desde el principio
        for (int i = 0; i < array.length; i++) {
            // Usamos .equals para objetos genéricos
            if (array[i].equals(key)) {
                return i; // Retorna el primer índice encontrado
            }
        }
        return -1; // Retorna -1 si no encontrado (equivalente a null en SLL)
    }

    /**
     * Busca la última ocurrencia de una clave en el arreglo.
     * Equivalente a SLLSearch.findLast
     */
    public static <T> int findLast(T[] array, T key) {
        // A diferencia de la lista enlazada (SLL) que debe recorrerse hacia adelante,
        // un arreglo permite recorrerse hacia atrás eficientemente.
        // Esto es más óptimo, pero sigue la lógica de encontrar el "último".
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i].equals(key)) {
                return i; // Retorna el último índice encontrado
            }
        }
        return -1; // -1 si no existe
    }

    /**
     * Encuentra todos los elementos que cumplen una condición.
     * Equivalente a SLLSearch.findAll
     */
    public static <T> List<Integer> findAll(T[] array, Predicate<T> p) {
        List<Integer> results = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            // Evaluamos el predicado con el elemento actual
            if (p.test(array[i])) {
                results.add(i); // Agregamos el índice a la lista de resultados
            }
        }

        return results; // Lista de índices que cumplen la condición
    }
    public static <T> int findSentinel(T[] array, T key) {
        int n = array.length;
        if (n == 0) {
            return -1;
        }

        // 1. Guardar el valor original del último elemento
        //    para poder restaurarlo al final.
        T lastOriginal = array[n - 1];

        // 2. Colocar el centinela (la clave) en la última posición.
        //    Esto garantiza que el bucle while SIEMPRE terminará.
        array[n - 1] = key;

        int i = 0;

        // 3. Bucle optimizado: Solo una comparación por iteración.
        //    Usamos .equals() para comparar el contenido de los objetos.
        while (!array[i].equals(key)) {
            i++;
        }

        // 4. Restaurar el valor original INMEDIATAMENTE.
        array[n - 1] = lastOriginal;

        // 5. Verificar si lo encontramos de verdad o si chocamos con el centinela.
        //    Caso A: El índice 'i' es menor que la última posición (lo encontramos antes).
        //    Caso B: El índice es la última posición, pero el valor original
        //            AHÍ CASUALMENTE era igual a la clave.
        if (i < n - 1 || (lastOriginal != null && lastOriginal.equals(key))) {
            return i;
        } else {
            return -1; // Solo encontramos el centinela artificial
        }
    }
    /**
     * Realiza una Búsqueda Binaria.
     * NOTA: Este método ORDENA el arreglo primero usando BubbleSort
     * (tal como solicitaste), por lo que modifica el orden original de los elementos.
     */
    public static <T extends Comparable<T>> int binarySearch(T[] array, T key) {

        // 1. Delegamos el ordenamiento a tu clase externa
        BubbleSort.ordenarAscendente(array);

        // 2. Lógica de búsqueda binaria
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
                return mid; // Encontrado
            }
        }
        return -1;
    }
}