package tests;
import lineales.dinamicas.Lista;

public class ListaTest {
    public static void main(String[] args) {
        Lista lista = new Lista();

        // boolean exito = lista.insertar(1, 1);
        // exito = lista.insertar(2, 2);

        Object algo = lista.recuperar(2);
        System.out.println(algo);
    }
}
