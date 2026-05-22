import jerarquicas.*;
import lineales.dinamicas.*;
import conjuntistas.*;
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

    public static void cargarArbolGen(ArbolGen ag) {
        ag.insertar("a", null);
        ag.insertar("b", "a");
        ag.insertar("c", "a");
        ag.insertar("d", "a");
        ag.insertar("e", "b");
        ag.insertar("f", "b");
        ag.insertar("j", "f");
        ag.insertar("k", "f");
        ag.insertar("l", "f");
        ag.insertar("g", "d");
        ag.insertar("h", "d");
        ag.insertar("i", "d");
        ag.insertar("m", "g");
        ag.insertar("p", "m");
        ag.insertar("q", "m");
        ag.insertar("n", "i");
        ag.insertar("o", "i");
    }

    public static void cargarArbolGenPorPos(ArbolGen ag) {
        ag.insertarPorPosicion("a", 0);
        ag.insertarPorPosicion("b", 1);
        ag.insertarPorPosicion("c", 1);
        ag.insertarPorPosicion("d", 1);
        ag.insertarPorPosicion("e", 4);
        ag.insertarPorPosicion("f", 4);
        ag.insertarPorPosicion("j", 5);
        ag.insertarPorPosicion("k", 5);
        ag.insertarPorPosicion("l", 5);
        ag.insertarPorPosicion("g", 2);
        ag.insertarPorPosicion("h", 2);
        ag.insertarPorPosicion("i", 2);
        ag.insertarPorPosicion("m", 5);
        ag.insertarPorPosicion("p", 6);
        ag.insertarPorPosicion("q", 6);
        ag.insertarPorPosicion("n", 3);
        ag.insertarPorPosicion("o", 3);
    }

    public static void cargarArbolBBString(ArbolBB abb) {
        abb.insertar("e");
        abb.insertar("d");
        abb.insertar("y");
        abb.insertar("b");
        abb.insertar("c");
    }

    public static void cargarArbolBBInts(ArbolBB abb) {
        abb.insertar(17);
        abb.insertar(10);
        abb.insertar(50);
        abb.insertar(5);
        abb.insertar(14);
        abb.insertar(11);
        abb.insertar(26);
        abb.insertar(80);
        abb.insertar(30);
        abb.insertar(100);
        abb.insertar(28);
        abb.insertar(8);
    }

    public static void main(String[] args) throws Exception {
        ArbolBB a = new ArbolBB();
        ArbolBB b;

        cargarArbolBBInts(a);

        a.eliminar(50);

        System.out.println(a);
        System.out.println("-----------------------------------------------");
        
    }
    
}
