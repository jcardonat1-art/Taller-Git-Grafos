package tallerGrafos;

/**
 *
 * @author José Cardona
 */
import java.util.*;

/**
 * Grafo ponderado implementado con lista de adyacencia.
 * Puede ser dirigido o no dirigido.
 */
public class Grafo {

    private final Map<String, Map<String, Integer>> adyacencia = new LinkedHashMap<>();
    private final boolean dirigido;

    public Grafo(boolean dirigido) {
        this.dirigido = dirigido;
    }

    // ---------- Operaciones de vértices ----------

    public boolean agregarVertice(String v) {
        if (adyacencia.containsKey(v)) {
            return false;
        }
        adyacencia.put(v, new LinkedHashMap<>());
        return true;
    }

    public boolean eliminarVertice(String v) {
        if (!adyacencia.containsKey(v)) {
            return false;
        }
        adyacencia.remove(v);
        for (Map<String, Integer> vecinos : adyacencia.values()) {
            vecinos.remove(v);
        }
        return true;
    }

    public boolean existeVertice(String v) {
        return adyacencia.containsKey(v);
    }

    // ---------- Operaciones de aristas ----------

    public boolean agregarArista(String origen, String destino, int peso) {
        if (!existeVertice(origen) || !existeVertice(destino) || origen.equals(destino)) {
            return false;
        }
        adyacencia.get(origen).put(destino, peso);
        if (!dirigido) {
            adyacencia.get(destino).put(origen, peso);
        }
        return true;
    }

    public boolean eliminarArista(String origen, String destino) {
        if (!existeArista(origen, destino)) {
            return false;
        }
        adyacencia.get(origen).remove(destino);
        if (!dirigido) {
            adyacencia.get(destino).remove(origen);
        }
        return true;
    }

    public boolean existeArista(String origen, String destino) {
        return existeVertice(origen) && adyacencia.get(origen).containsKey(destino);
    }

    // ---------- Consultas ----------

    public Set<String> vecinos(String v) {
        if (!existeVertice(v)) {
            return Collections.emptySet();
        }
        return adyacencia.get(v).keySet();
    }

    public int grado(String v) {
        return vecinos(v).size();
    }

    public int cantidadVertices() {
        return adyacencia.size();
    }

    public int cantidadAristas() {
        int total = 0;
        for (Map<String, Integer> vecinos : adyacencia.values()) {
            total += vecinos.size();
        }
        return dirigido ? total : total / 2;
    }

    // ---------- Recorridos ----------

    /** Recorrido en anchura (BFS). */
    public List<String> bfs(String inicio) {
        List<String> orden = new ArrayList<>();
        if (!existeVertice(inicio)) {
            return orden;
        }
        Set<String> visitados = new HashSet<>();
        Queue<String> cola = new LinkedList<>();
        visitados.add(inicio);
        cola.add(inicio);
        while (!cola.isEmpty()) {
            String actual = cola.poll();
            orden.add(actual);
            for (String vecino : adyacencia.get(actual).keySet()) {
                if (visitados.add(vecino)) {
                    cola.add(vecino);
                }
            }
        }
        return orden;
    }

    /** Recorrido en profundidad (DFS). */
    public List<String> dfs(String inicio) {
        List<String> orden = new ArrayList<>();
        if (existeVertice(inicio)) {
            dfsRecursivo(inicio, new HashSet<>(), orden);
        }
        return orden;
    }

    private void dfsRecursivo(String actual, Set<String> visitados, List<String> orden) {
        visitados.add(actual);
        orden.add(actual);
        for (String vecino : adyacencia.get(actual).keySet()) {
            if (!visitados.contains(vecino)) {
                dfsRecursivo(vecino, visitados, orden);
            }
        }
    }

    // ---------- Visualización ----------

    public void mostrarListaAdyacencia() {
        System.out.println("Lista de adyacencia (" + (dirigido ? "dirigido" : "no dirigido") + "):");
        for (Map.Entry<String, Map<String, Integer>> e : adyacencia.entrySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append("  ").append(e.getKey()).append(" -> ");
            if (e.getValue().isEmpty()) {
                sb.append("(sin vecinos)");
            } else {
                for (Map.Entry<String, Integer> a : e.getValue().entrySet()) {
                    sb.append(a.getKey()).append("(").append(a.getValue()).append(") ");
                }
            }
            System.out.println(sb.toString().trim());
        }
    }

    public void mostrarMatrizAdyacencia() {
        List<String> nombres = new ArrayList<>(adyacencia.keySet());
        System.out.println("Matriz de adyacencia (0 = sin arista, otro valor = peso):");
        System.out.printf("%12s", "");
        for (String n : nombres) {
            System.out.printf("%12s", n);
        }
        System.out.println();
        for (String fila : nombres) {
            System.out.printf("%12s", fila);
            for (String col : nombres) {
                Integer peso = adyacencia.get(fila).get(col);
                System.out.printf("%12d", peso == null ? 0 : peso);
            }
            System.out.println();
        }
    }
}