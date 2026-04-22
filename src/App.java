import jerarquicas.ArbolBin;
import lineales.dinamicas.Lista;

public class App {
    public static void main(String[] args) throws Exception {
        Lista lista1 = new Lista();
        
        for (int i = 1; i<=10; i++) {
            lista1.insertar(i, i);
        }

        Lista lista2 = lista1.obtenerMultiplos(1);

        System.out.println(lista2.toString());
    }
}
