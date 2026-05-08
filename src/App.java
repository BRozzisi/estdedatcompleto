import jerarquicas.*;
import lineales.dinamicas.*;

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

    public static void main(String[] args) throws Exception {
        ArbolGen ag = new ArbolGen();
        cargarArbolGen(ag);
        boolean ex = ag.insertar("r", "e");
        System.out.println(ag.toString());

        a -> d, c, b, 
d -> i, h, g, 
i -> o, n, 
o -> 
n -> 
h -> 
g -> m, 
m -> q, p, 
q -> 
p -> 
c -> 
b -> f, e, 
f -> l, k, j, 
l -> 
k -> 
j -> 
e -> r, 
r -> 
    }
    
}
