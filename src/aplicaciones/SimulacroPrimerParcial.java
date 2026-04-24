package aplicaciones;
import lineales.dinamicas.*;

public class SimulacroPrimerParcial {
    public static Cola generar(Cola c1) {
        Cola cr = new Cola();
        if (!c1.esVacia()) {
            Pila p1 = new Pila();
            Pila p2 = new Pila();
            Cola c1Copia = c1.clone();
            Object aux = c1Copia.obtenerFrente();
            while (!c1Copia.esVacia()) {
                if (aux != '#') {
                    cr.poner(aux);
                    p1.apilar(aux);
                } else {
                    while (!p1.esVacia()) {
                        aux = p1.obtenerTope();
                        p2.apilar(aux);
                        cr.poner(aux);
                        p1.desapilar();
                    }
                    while (!p2.esVacia()) {
                        aux = p2.obtenerTope();
                        cr.poner(aux);
                        p2.desapilar();
                    }
                    cr.poner('#');
                }
                c1Copia.sacar();
                aux = c1Copia.obtenerFrente();
            }
            while (!p1.esVacia()) {
                aux = p1.obtenerTope();
                p2.apilar(aux);
                cr.poner(aux);
                p1.desapilar();
            }
            while (!p2.esVacia()) {
                aux = p2.obtenerTope();
                cr.poner(aux);
                p2.desapilar();
            }
        }
    }

    public static void main (String[] args) {
        Cola c1 = new Cola();
        c1.poner('A');
        c1.poner('B');
        c1.poner('#');
        c1.poner('C');
        c1.poner('D');
        c1.poner('E');
        c1.poner('#');
        c1.poner('F');

        Cola c2 = generar(c1);

        System.out.println(c1);

        System.out.println(c2);
    }
}
