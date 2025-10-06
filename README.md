# Sistema para Agencia de Viajes – Gestión Eficiente de Rutas.

  - *Contexto:* Proyecto de la materia Algoritmos y Estructuras de Datos II, desarrollado en el marco de la carrera de Analista Programador en ORT Uruguay.
  - *Estado:* Finalizado en Junio 2025.
  - *Tecnologia principal:* Java

## Descripción del Problema y Solución.

*Problema Abordado:* Resolver la alta demanda de las operaciones de una agencia de viajes aéreos.
*Objetivo Principal:* Desarrollo de un sistema de optimización de rutas de vuelo, balanceando el coste y el tiempo.

## Implementaciones Clave (Foco Técnico).
### Estructuras de Datos Implementadas:
**Grafos:** Utilizados para modelar las conexiones aéreas (rutas de vuelo) y sus atributos (coste, tiempo).
**ABB:** (Árbol Binario de Búsqueda): Implementado para la gestión y búsqueda eficiente de ciertos datos o entidades dentro del sistema.
**Listas:** Empleadas para la manipulación y almacenamiento temporal de datos de vuelos y operaciones.

### Algoritmos y Lógica:
**Recorridos:** Aplicación de algoritmos de recorridos (como Dijkstra o BFS/DFS, aunque no están explícitamente nombrados, se infiere por "Recorridos" y el uso de Grafos) para encontrar la ruta óptima (coste/tiempo) entre dos destinos.

**Diseño de Software:** POO (Programación Orientada a Objetos): El sistema fue diseñado siguiendo principios de Programación Orientada a Objetos.

### Funcionalidades del Sistema
**Lista las principales operaciones que puede realizar el programa:**
  - Gestión de información de vuelos y rutas.
  - Búsqueda de la ruta de vuelo más eficiente (optimización de rutas coste/tiempo).
  - Manejo de operaciones de alta demanda de la agencia de viajes.

### Guía de Ejecución
**🛠️ Requisitos**

*Para poder ejecutar este proyecto de manera local, necesitarás:*
  1. Java Development Kit (JDK): Se recomienda tener instalado una versión reciente de Java (JDK 8 o superior) en el sistema operativo para la compilación y ejecución del código.
  2. Entorno de Desarrollo Integrado (IDE): Se recomienda utilizar un IDE compatible con proyectos Java, como IntelliJ IDEA o Eclipse, para una gestión más sencilla de las dependencias y la estructura del proyecto.
  3. Git: Necesario para clonar el repositorio de GitHub.
     
**🚀 Pasos para la Ejecución**

*Sigue estos pasos para poner en marcha el sistema:*
1. Clonar el Repositorio: Abre tu terminal o consola de comandos y utiliza Git para clonar el proyecto en tu máquina local: git clone https://github.com/MikaCurbelo/Algoritmos-y-Estructura-de-datos-II.git
2. Abrir el Proyecto en el IDE: Abre tu IDE (IntelliJ o Eclipse).
3. Selecciona la opción "Abrir Proyecto" o "Importar Proyecto".
4. Navega hasta la carpeta Algoritmos-y-Estructura-de-datos-II que acabas de clonar y ábrela. El IDE debería reconocer automáticamente el proyecto como un proyecto Java.

*Compilar y Ejecutar*
  
  1. Una vez cargado el proyecto, asegúrate de que el JDK esté configurado correctamente en la configuración del proyecto.
  2. Localiza la clase principal que contiene el método main. En este proyecto, la clase de inicio es típicamente Main.java (o el nombre de la clase principal que inicia la aplicación de consola).
  3. Haz clic derecho sobre el archivo Main.java y selecciona la opción "Ejecutar" (Run).
  
  El sistema debería iniciarse, permitiendo la interacción con las funcionalidades de gestión de rutas y estructuras de datos (ABB, Grafos).
