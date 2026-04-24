import jerarquicas.ArbolBin;
import lineales.dinamicas.Lista;

public class App {
    public static void main(String[] args) throws Exception {
        Lista lista = new Lista();
        for (int i = 20; i>0; i--) {
            lista.insertar(i, 1);
        }

        Lista listaNueva = lista.obtenerMultiplos(5);

        System.out.println(lista.toString());
        System.out.println(listaNueva.toString());
    }
}
