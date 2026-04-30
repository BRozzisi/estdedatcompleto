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

    public static void cargarArbolParaPatron(ArbolBin a) {
        a.insertarPorPosicion("A", 0, false);
        a.insertarPorPosicion("B", 1, true);
        a.insertarPorPosicion("B", 1, false);
        a.insertarPorPosicion("D", 2, true);
        a.insertarPorPosicion("D", 4, true);
        a.insertarPorPosicion("D", 4, false);
        a.insertarPorPosicion("Z", 5, false);
        a.insertarPorPosicion("F", 7, true);
        a.insertarPorPosicion("E", 7, false);
        a.insertarPorPosicion("G", 9, true);
    }

    public static void a(Object nazi){ 
        System.out.println(nazi);
    }
    public static void main(String[] args) throws Exception {
        ArbolBin a1 = new ArbolBin();
        ArbolBin a2 = new ArbolBin();
        cargarArbolAsimetrico(a2);
        ArbolBin a3 = new ArbolBin();
        cargarArbolCompleto4Niveles(a3);
        ArbolBin a4 = new ArbolBin();
        cargarArbolSoloRaiz(a4);

        ArbolBin a1c = a1.clonarInvertido();
        ArbolBin a2c = a2.clonarInvertido();
        ArbolBin a3c = a3.clonarInvertido();
        ArbolBin a4c = a4.clonarInvertido();

        String nazi;
        System.out.println("------------------------------------------------------------");
        nazi = a1c.toString();
        a(nazi);
        System.out.println("------------------------------------------------------------");
        nazi = a2c.toString();
        a(nazi);
        System.out.println("------------------------------------------------------------");
        nazi = a3c.toString();
        a(nazi);
        System.out.println("------------------------------------------------------------");
        nazi = a4c.toString();
        a(nazi);
    }
}
