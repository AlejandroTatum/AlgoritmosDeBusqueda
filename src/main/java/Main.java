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

        // 1. CARGA DE DATOS
        String filePath = "src/main/java/data/inventario_500_inverso.csv";
        String colName = "stock";
        Double[] dataArray;

        try {
            dataArray = CsvReader.readDoubleColumn(filePath, colName);
            System.out.println("Datos cargados. Total elementos: " + dataArray.length);
            System.out.println("Dato en índice 0: " + dataArray[0]);
            System.out.println("Dato en índice 1: " + dataArray[1]);
        } catch (IOException e) {
            System.err.println("Error al leer CSV: " + e.getMessage());
            return;
        }


        Node<Double> head = StructureUtil.arrayToSLL(dataArray);

        Double keyPresente = 300.0;

        System.out.println("\n--- PRUEBAS CON ARREGLOS ---");

        int idxFirst = ArraySearch.findFirst(dataArray, keyPresente);
        StructureUtil.printResult("1. FindFirst", idxFirst, dataArray);
        int idxLast = ArraySearch.findLast(dataArray, keyPresente);
        StructureUtil.printResult("2. FindLast", idxLast, dataArray);
        int idxSentinela = ArraySearch.findSentinel(dataArray, keyPresente);
        StructureUtil.printResult("4. Centinela", idxSentinela, dataArray);
        System.out.println("5. Búsqueda Binaria (" + keyPresente + "):");
        int idxBinary = ArraySearch.binarySearch(dataArray, keyPresente);
        StructureUtil.printResult("   Resultado Binaria", idxBinary, dataArray);

        System.out.println("\n--- PRUEBAS CON LISTAS (SLL) ---");

        List<Node<Double>> nodesHigh = SLLSearch.findAll(head, node -> node.data > 490.0);
        System.out.println("3. SLL FindAll (> 490): " + nodesHigh.size() + " nodos encontrados.");

        if (!nodesHigh.isEmpty()) {
            System.out.print("   Valores: ");
            for (Node<Double> nodo : nodesHigh) {
                System.out.print("[" + nodo.data + "] ");
            }
            System.out.println();
        } else {
            System.out.println("   Ningún nodo cumple la condición.");
        }
    }
}