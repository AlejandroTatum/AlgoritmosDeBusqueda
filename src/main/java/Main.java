import search.ArraySearch;
import search.SLLSearch;
import util.CsvReader;
import util.Node;
import util.StructureUtil;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== LABORATORIO 7: ALGORITMOS DE BÚSQUEDA ===");
        System.out.println("=== Integrantes del Grupo: Mark Gonzales, Steven Jumbo, Alejandro Padilla, Gyna Yupanqui ===\n");

        // ---------------------------------------------------------
        // PARTE 1: PRUEBAS CON ARCHIVOS (DATOS REALES Y VOLUMEN)
        // ---------------------------------------------------------

        // 1. Inventario (Datos Reales)
        runTest("Inventario Inverso",
                "src/main/java/data/inventario_500_inverso.csv",
                "stock",
                45.0);

        // 2. Números Positivos (Prueba general)
        runTest("Números Positivos",
                "src/main/java/data/NumerosPositivos.csv",
                "valor",
                999.0);

        // 3. Duplicados (Prueba de estrés para FindAll)
        runTest("Números Duplicados",
                "src/main/java/data/NumerosDuplicados.csv",
                "valor",
                12.0);

        // 4. Positivos y Negativos
        runTest("Positivos y Negativos",
                "src/main/java/data/NumerosPositivosNegativos.csv",
                "valor",
                -5.0);

        // ---------------------------------------------------------
        // PARTE 2: PRUEBAS DE CASOS BORDE (ESTRUCTURALES)
        // Estas pruebas aseguran la nota de "Manejo de bordes"
        // ---------------------------------------------------------
        runEdgeCases();
    }

    /**
     * Executes all search algorithms on a given CSV file and measures execution time.
     */
    private static void runTest(String testName, String filePath, String colName, Double keyToFind) {
        System.out.println("\n=================================================");
        System.out.println(" PRUEBA DE ARCHIVO: " + testName);
        System.out.println(" Archivo: " + filePath);
        System.out.println(" Buscando clave: " + keyToFind);
        System.out.println("=================================================");

        Double[] dataArray;

        try {
            dataArray = CsvReader.readDoubleColumn(filePath, colName);
            // Validación básica de carga
            if (dataArray.length == 0) {
                System.err.println("[Error] El archivo está vacío o no se encontró la columna.");
                return;
            }
            System.out.println(" -> Datos cargados. Total elementos: " + dataArray.length);

        } catch (IOException e) {
            System.err.println("[Error IO] No se pudo leer el archivo: " + e.getMessage());
            return;
        }

        // Convertir a SLL
        Node<Double> head = StructureUtil.arrayToSLL(dataArray);

        long start, end;

        // --- A. Secuencial First ---
        start = System.nanoTime();
        int idxFirst = ArraySearch.findFirst(dataArray, keyToFind);
        end = System.nanoTime();
        StructureUtil.printResult("1. Secuencial (First)", idxFirst, dataArray);
        System.out.println("   Tiempo: " + (end - start) + " ns");

        // --- B. Secuencial Last ---
        start = System.nanoTime();
        int idxLast = ArraySearch.findLast(dataArray, keyToFind);
        end = System.nanoTime();
        StructureUtil.printResult("2. Secuencial (Last) ", idxLast, dataArray);
        System.out.println("   Tiempo: " + (end - start) + " ns");

        // --- C. Centinela ---
        start = System.nanoTime();
        int idxSentinel = ArraySearch.findSentinel(dataArray, keyToFind);
        end = System.nanoTime();
        StructureUtil.printResult("3. Centinela         ", idxSentinel, dataArray);
        System.out.println("   Tiempo: " + (end - start) + " ns");

        // --- D. Binaria ---
        start = System.nanoTime();
        int idxBinary = ArraySearch.binarySearch(dataArray, keyToFind);
        end = System.nanoTime();
        StructureUtil.printResult("4. Binaria (con Sort)", idxBinary, dataArray);
        System.out.println("   Tiempo: " + (end - start) + " ns (Incluye ordenamiento)");

        // --- E. SLL FindAll ---
        start = System.nanoTime();
        List<Node<Double>> allNodes = SLLSearch.findAll(head, node -> node.data.equals(keyToFind));
        end = System.nanoTime();

        if (!allNodes.isEmpty()) {
            System.out.println("5. SLL FindAll: Encontrados " + allNodes.size() + " nodos.");
        } else {
            System.out.println("5. SLL FindAll: No encontrado.");
        }
        System.out.println("   Tiempo: " + (end - start) + " ns");
    }


    private static void runEdgeCases() {
        System.out.println("\n#################################################");
        System.out.println("       VERIFICACIÓN DE CASOS BORDE (EXTREMOS)      ");
        System.out.println("#################################################");

        // CASO 1: ARREGLO VACÍO
        System.out.println("\n--- CASO BORDE 1: ARREGLO VACÍO ---");
        Double[] emptyArray = {};
        Double key = 10.0;

        System.out.println("Prueba: Buscar " + key + " en arreglo []");
        // Esperamos -1 en todos
        System.out.println("FindFirst: " + (ArraySearch.findFirst(emptyArray, key) == -1 ? "OK (Manejado)" : "FALLO"));
        System.out.println("Centinela: " + (ArraySearch.findSentinel(emptyArray, key) == -1 ? "OK (Manejado)" : "FALLO"));
        System.out.println("Binaria:   " + (ArraySearch.binarySearch(emptyArray, key) == -1 ? "OK (Manejado)" : "FALLO"));

        // CASO 2: UN SOLO ELEMENTO (Coincide)
        System.out.println("\n--- CASO BORDE 2: UN SOLO ELEMENTO (EXISTE) ---");
        Double[] singleMatches = {50.0};
        Double keyMatch = 50.0;

        System.out.println("Prueba: Buscar " + keyMatch + " en arreglo [50.0]");
        // Esperamos índice 0
        int r1 = ArraySearch.findFirst(singleMatches, keyMatch);
        int r2 = ArraySearch.binarySearch(singleMatches, keyMatch); // Prueba crítica para el while(low<=high)

        System.out.println("FindFirst index: " + r1 + (r1 == 0 ? " [CORRECTO]" : " [ERROR]"));
        System.out.println("Binaria index:   " + r2 + (r2 == 0 ? " [CORRECTO]" : " [ERROR]"));

        // CASO 3: ELEMENTO NO EXISTENTE
        System.out.println("\n--- CASO BORDE 3: ELEMENTO NO EXISTENTE ---");
        Double[] smallArray = {10.0, 20.0, 30.0};
        Double keyNo = 99.0;

        int r3 = ArraySearch.findSentinel(smallArray, keyNo);
        System.out.println("Centinela buscando 99.0 en [10,20,30]: " + r3 + (r3 == -1 ? " [CORRECTO]" : " [ERROR]"));

        // CASO 4: CLAVE NULA
        System.out.println("\n--- CASO BORDE 4: BÚSQUEDA DE NULL ---");
        try {
            int rNull = ArraySearch.findFirst(smallArray, null);
            System.out.println("Buscar null: " + rNull + " (Debe ser -1 y no crashear) -> " + (rNull == -1 ? "OK" : "FALLO"));
        } catch (Exception e) {
            System.out.println("Buscar null: EXCEPCIÓN DETECTADA (Mal manejo) -> " + e.getMessage());
        }
    }
}