import jerarquicas.ArbolBin;
import lineales.dinamicas.Lista;

public class App {
    public static void main(String[] args) throws Exception {
        ArbolBin arbol = new ArbolBin();

        boolean algo = arbol.insertar("A", 1, true);
        arbol.insertarPorPosicion("B", 1, true);
        arbol.insertarPorPosicion("C", 1, false);
        arbol.insertarPorPosicion("E", 2, false);
        arbol.insertarPorPosicion("F", 4, true);
        arbol.insertarPorPosicion("G", 4, false);
        arbol.insertarPorPosicion("H", 5, false);


        System.out.println(arbol.toString());
    }
}
