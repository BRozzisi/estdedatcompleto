import jerarquicas.ArbolBin;
import lineales.dinamicas.Cola;

public class App {
    public static void main(String[] args) throws Exception {
        ArbolBin arbolPrueba = new ArbolBin();

        arbolPrueba.insertar(1, null, true);

        System.out.println(arbolPrueba.toString());
    }
}
