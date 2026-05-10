# Algoritmos de Búsqueda en Java

Proyecto académico en Java para implementar y comparar algoritmos de búsqueda sobre arreglos y listas simplemente enlazadas, usando datasets en CSV y validación de casos borde.

## Descripción

Este proyecto fue desarrollado como práctica de Estructura de Datos. Su objetivo es comparar diferentes estrategias de búsqueda, medir tiempos de ejecución y analizar cómo cambia el comportamiento según la estructura de datos utilizada.

El sistema carga datasets desde archivos CSV, ejecuta búsquedas sobre arreglos y listas enlazadas simples, y muestra resultados en consola junto con una verificación de casos borde.

## Características

- Carga de datasets desde archivos CSV.
- Implementación de búsquedas en arreglos.
- Implementación de búsquedas en listas simplemente enlazadas.
- Comparación básica de tiempos de ejecución en nanosegundos.
- Validación de casos borde:
  - arreglo vacío
  - un solo elemento
  - elemento no existente
  - clave nula
  - valores duplicados
- Uso de Bubble Sort como paso previo para búsqueda binaria.

## Tecnologías utilizadas

- Java 21
- Maven
- Apache Commons CSV
- Programación orientada a objetos
- Estructuras de datos
- Algoritmos de búsqueda

## Estructura del proyecto

```txt
src/main/java/
├── Main.java
├── data/
│   ├── NumerosDuplicados.csv
│   ├── NumerosPositivos.csv
│   ├── NumerosPositivosNegativos.csv
│   ├── citas_100.csv
│   ├── citas_100_casi_ordenadas.csv
│   ├── inventario_500_inverso.csv
│   └── pacientes_500.csv
├── search/
│   ├── ArraySearch.java
│   └── SLLSearch.java
├── sorting/
│   └── BubbleSort.java
└── util/
    ├── CsvReader.java
    ├── Node.java
    ├── StructureUtil.java
    └── Validator.java
```

## Cómo ejecutar el proyecto

### Requisitos

- Java 21
- Git

El proyecto incluye Maven Wrapper, por lo que no es necesario instalar Maven globalmente.

### Clonar el repositorio

```bash
git clone https://github.com/AlejandroTatum/AlgoritmosDeBusqueda.git
cd AlgoritmosDeBusqueda
```

### Compilar

```bash
./mvnw clean compile
```

### Ejecutar

```bash
./mvnw exec:java
```

## Algoritmos implementados

| Categoría | Implementación |
|---|---|
| Búsqueda secuencial | Primera ocurrencia |
| Búsqueda secuencial | Última ocurrencia |
| Búsqueda secuencial | Todas las ocurrencias con predicado |
| Búsqueda con centinela | Arreglos |
| Búsqueda binaria | Arreglos ordenados |
| Ordenamiento auxiliar | Bubble Sort |
| Estructuras | Arreglos y listas simplemente enlazadas |

## Datasets utilizados

| Dataset | Uso principal |
|---|---|
| `NumerosPositivos.csv` | Búsqueda sobre valores positivos |
| `NumerosDuplicados.csv` | Validación de ocurrencias repetidas |
| `NumerosPositivosNegativos.csv` | Búsqueda sobre valores positivos y negativos |
| `inventario_500_inverso.csv` | Pruebas con mayor volumen de datos |
| `citas_100.csv` | Dataset complementario de citas |
| `citas_100_casi_ordenadas.csv` | Dataset complementario casi ordenado |
| `pacientes_500.csv` | Dataset complementario de pacientes |

## Demo de ejecución

Salida resumida al ejecutar el programa:

```txt
=== LABORATORIO 7: ALGORITMOS DE BÚSQUEDA ===

PRUEBA DE ARCHIVO: Inventario Inverso
Archivo: src/main/java/data/inventario_500_inverso.csv
Buscando clave: 45.0

-> Datos cargados. Total elementos: 500
1. Secuencial (First): Encontrado [45.0] en índice 455
2. Secuencial (Last) : Encontrado [45.0] en índice 455
3. Centinela         : Encontrado [45.0] en índice 455
4. Binaria (con Sort): Encontrado [45.0] en índice 44
5. SLL FindAll: Encontrados 1 nodos.

VERIFICACIÓN DE CASOS BORDE
FindFirst: OK (Manejado)
Centinela: OK (Manejado)
Binaria:   OK (Manejado)
```

## Decisiones de diseño

- Se utilizaron arreglos para comparar búsquedas directas y búsqueda binaria.
- Se utilizaron listas simplemente enlazadas para practicar recorrido secuencial con nodos.
- La búsqueda binaria ordena previamente el arreglo porque requiere datos ordenados.
- La técnica de centinela modifica temporalmente la última posición del arreglo y luego restaura su valor original.
- Los casos borde permiten verificar que los algoritmos respondan sin excepciones ante entradas vacías, nulas o inexistentes.

## Aprendizajes principales

- Comprender la diferencia entre búsqueda secuencial y búsqueda binaria.
- Identificar la precondición principal de la búsqueda binaria: datos ordenados.
- Practicar estructuras enlazadas simples y recorridos nodo por nodo.
- Leer datos desde archivos CSV para probar algoritmos con datasets externos.
- Validar casos borde para evitar errores comunes como `NullPointerException`.

## Informe académico

El repositorio incluye el informe original de la práctica en PDF:

- `APE_Implementación_Búsqueda (INFORME).pdf`

## Autores

- Alejandro Padilla
- Mark Gonzales
- Steven Jumbo
- Gyna Yupanqui
