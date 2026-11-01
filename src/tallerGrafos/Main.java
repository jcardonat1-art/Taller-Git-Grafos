/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tallerGrafos;

/**
 *
 * @author José Cardona
 */

public class Main {

    private static void titulo(String texto) {
        System.out.println();
        System.out.println("=== " + texto + " ===");
    }

    public static void main(String[] args) {
        Grafo g = new Grafo(false); // no dirigido

        titulo("1. Agregar vértices");
        String[] ciudades = {"Bogota", "Medellin", "Cali", "Barranquilla", "Cartagena"};
        for (String c : ciudades) {
            System.out.println("Agregar " + c + ": " + g.agregarVertice(c));
        }
        System.out.println("Agregar Bogota (repetido): " + g.agregarVertice("Bogota"));

        titulo("2. Agregar aristas (con peso = distancia aproximada en km)");
        System.out.println(g.agregarArista("Bogota", "Medellin", 415));
        System.out.println(g.agregarArista("Bogota", "Cali", 460));
        System.out.println(g.agregarArista("Medellin", "Barranquilla", 640));
        System.out.println(g.agregarArista("Medellin", "Cartagena", 640));
        System.out.println(g.agregarArista("Barranquilla", "Cartagena", 120));

        titulo("3. Mostrar el grafo");
        g.mostrarListaAdyacencia();
        System.out.println();
        g.mostrarMatrizAdyacencia();
        System.out.println("Vértices: " + g.cantidadVertices() + " | Aristas: " + g.cantidadAristas());

        titulo("4. Consultas");
        System.out.println("¿Existe vértice Cali? " + g.existeVertice("Cali"));
        System.out.println("¿Existe vértice Pasto? " + g.existeVertice("Pasto"));
        System.out.println("¿Existe arista Bogota-Cali? " + g.existeArista("Bogota", "Cali"));
        System.out.println("¿Existe arista Cali-Cartagena? " + g.existeArista("Cali", "Cartagena"));
        System.out.println("Vecinos de Medellin: " + g.vecinos("Medellin"));
        System.out.println("Grado de Medellin: " + g.grado("Medellin"));

        titulo("5. Recorridos desde Bogota");
        System.out.println("BFS (anchura):     " + g.bfs("Bogota"));
        System.out.println("DFS (profundidad): " + g.dfs("Bogota"));

        titulo("6. Eliminar una arista (Barranquilla-Cartagena)");
        System.out.println("Eliminada: " + g.eliminarArista("Barranquilla", "Cartagena"));
        g.mostrarListaAdyacencia();

        titulo("7. Eliminar un vértice (Cali)");
        System.out.println("Eliminado: " + g.eliminarVertice("Cali"));
        g.mostrarListaAdyacencia();
        System.out.println("Vértices: " + g.cantidadVertices() + " | Aristas: " + g.cantidadAristas());
    }
}