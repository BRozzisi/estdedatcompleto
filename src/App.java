import jerarquicas.ArbolBin;
import jerarquicas.NodoArbol;
import lineales.dinamicas.Lista;

public class App {
    public static void cargarArbolCompleto4Niveles(ArbolBin arbol) {
        arbol.insertar("A", null, true);

        arbol.insertar("B", "A", true);
        arbol.insertar("C", "A", false);

        arbol.insertar("D", "B", true);
        arbol.insertar("E", "B",false);
        arbol.insertar("F", "C", true);
        arbol.insertar("G", "C", false);

        arbol.insertar("H", "D", true);
        arbol.insertar("I", "D",false);
        arbol.insertar("J", "E", true);
        arbol.insertar("K", "E", false);
        arbol.insertar("L", "F", true);
        arbol.insertar("M", "F",false);
        arbol.insertar("N", "G", true);
        arbol.insertar("O", "G", false);
    }
    public static void cargarArbolSoloRaiz(ArbolBin arbol) {
        arbol.insertar("A", null, true);
    }
    public static void cargarArbolAsimetrico(ArbolBin arbol) {
        arbol.insertar("A", null, true);
        arbol.insertar("B", "A", true);
        arbol.insertar("D", "B", true);
        arbol.insertar("C", "A", false);
        arbol.insertar("E", "C", true);
        arbol.insertar("F", "C", false);
        arbol.insertar("G", "E", true);
        arbol.insertar("H", "E", false);
    }
    public static void main(String[] args) throws Exception {
        ArbolBin arbol = new ArbolBin();

        cargarArbolCompleto4Niveles(arbol);
        // cargarArbolSoloRaiz(arbol);
        // cargarArbolAsimetrico(arbol);

        Lista listaPorNiveles = arbol.listarPorNiveles();

        System.out.println(arbol.toString());
    }
}
