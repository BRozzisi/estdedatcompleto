import lineales.dinamicas.Lista;
import jerarquicas.NodoArbol;

public class App {
    public static void main(String[] args) throws Exception {
        Lista lista = new Lista();

        NodoArbol nodo = new NodoArbol(1, null, null);

        lista.insertar(nodo, 1);

        NodoArbol nodoRetorno = lista.recuperar(1);

        System.out.println(nodoRetorno.toString());
    }
}
