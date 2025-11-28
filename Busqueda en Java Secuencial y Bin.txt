# Búsqueda en Java: Secuencial y Binaria

![Java](https://img.shields.io/badge/Java-21-blue?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-Project-orange?style=for-the-badge)
![Datasets](https://img.shields.io/badge/Datasets-CSV-green?style=for-the-badge)

---

## Descripción del Proyecto

Este proyecto implementa un sistema completo para:

- Cargar datasets reales en formato CSV

- Implementar algoritmos de búsqueda en:

- Arreglos (Arrays)

- Listas Enlazadas Simples (SLL)

- Ejecutar pruebas de rendimiento para comparar el comportamiento de los algoritmos

- Validar con casos borde y diferentes escenarios

---

# Generación de Datasets

Los datasets se guardan en:

src/main/java/data/

### Formatos de Datos

- Codificación: UTF-8
- Separador: ;
- Encabezado en primera fila
- Tipos: numéricos y textuales

---

# Arquitectura del Proyecto

```java
src/
└── main/
    └── java/
        ├── search/
        │   ├── ArraySearch.java
        │   └── SLLSearch.java
        ├── sorting/
        │   └── BubbleSort.java
        ├── util/
        │   ├── CsvReader.java
        │   ├── Node.java
        │   ├── StructureUtil.java
        │   └── Validator.java
        ├── data/
        │   ├── inventario_500_inverso.csv
        │   ├── NumerosPositivos.csv
        │   ├── NumerosDuplicados.csv
        │   └── ...
        └── Main.java
```

# Cómo Ejecutar

### Requisitos
- JDK: OpenJDK 8 o superior
- Dependencia: Apache Commons CSV

### Compilar
```bash

javac -cp ".:lib/*" -d bin src/main/java/**/*.java

```
### Al ejecutar
```bash

java -cp "bin:lib/*" Main

```
Se realiza estas acciones al ejecutar:

- Carga de todos los datasets
- Pruebas con cada algoritmo de búsqueda
- Medición de tiempos en nanosegundos
- Validación de casos borde
- Reporte de resultados en consola
  
---

## Preccondiciones:

## Busqueda binaria:

```java

ArraySearch.binarySearch(array, key);

```
Arreglo debe estar ordenado. Se incluye ordenamiento automático con BubbleSort.
No funciona con listas enlazadas. Aunque estén ordenadas.
Costo adicional - Tiempo de ordenamiento incluido en la medición

## Busqueda secuencial:

```java

ArraySearch.findFirst(array, key);
SLLSearch.findFirst(head, key);

```

Funciona con cualquier estructura (Array o SLL)
No requiere ordenamiento previo
Maneja datos duplicados y nulos

## Tecnicia centinela

```java

ArraySearch.findSentinel(array, key);

```
Solo para arreglos - No aplicable a SLL
Modifica temporalmente el arreglo - Restaura valor original
Optimizado para búsquedas frecuentes

 ---
#  Casos Borde Implementados

## Arreglo Vacio
```java

Double[] emptyArray = {};
ArraySearch.findFirst(emptyArray, 10.0);  // Retorna -1
ArraySearch.binarySearch(emptyArray, 10.0); // Retorna -1

```
Todos los algoritmos retornan -1 sin excepciones

## Un solo elemento
```java

Double[] single = {50.0};
ArraySearch.findFirst(single, 50.0);  // Retorna 0
ArraySearch.binarySearch(single, 50.0); // Retorna 0 (crítico para while loop)

```
Índice correcto en búsqueda exitosa.

## ELemento no existente
```java

Double[] array = {10.0, 20.0, 30.0};
ArraySearch.findSentinel(array, 99.0); // Retorna -1

```
Retorna -1 consistentemente

## Valores nulos
```java

ArraySearch.findFirst(array, null); // Retorna -1

```

No causa NullPointerException.

## Elementos duplicados
```java

// En NumerosDuplicados.csv: 12.0 aparece en índices 0, 3, 14, 64
ArraySearch.findFirst(duplicates, 12.0);  // Retorna 0 (primera)
ArraySearch.findLast(duplicates, 12.0);   // Retorna 64 (última)
SLLSearch.findAll(head, 12.0);           // Retorna 4 nodos

```

Primera/última ocurrencia correctamente identificada.

## Todos elementos iguales
```java

Double[] allSame = {5.0, 5.0, 5.0, 5.0};
ArraySearch.findFirst(allSame, 5.0); // Retorna 0
ArraySearch.findLast(allSame, 5.0);  // Retorna 3

```

Manejo correcto de duplicados masivos

## Elemento del Inicio/Fin
```java

Double[] edges = {42.0, 10.0, 20.0, 99.0};
ArraySearch.findFirst(edges, 42.0); // Retorna 0
ArraySearch.findLast(edges, 99.0);  // Retorna 3

```
 Índices extremos correctamente manejados
 
---
