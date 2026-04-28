package aplicaciones;
import lineales.dinamicas.*;

// Implementar la operación boolean verificarPatron(Lista patron), que recibe por parámetro una lista patron
// y determine si coincide exactamente con al menos un camino del árbol que comience en la raíz y termine en
// una hoja.
public class SimulacroPrimerParcial {
    public static Cola generar(Cola c1) {
        Cola cr = new Cola();
        if (!c1.esVacia()) {
            Pila p1 = new Pila();
            Pila p2 = new Pila();
            Cola c1Copia = c1.clone();
            char aux = (Character) c1Copia.obtenerFrente();
            while (!c1Copia.esVacia()) {
                if (aux != '#') {
                    cr.poner(aux);
                    p1.apilar(aux);
                } else {
                    while (!p1.esVacia()) {
                        aux = (Character) p1.obtenerTope();
                        p2.apilar(aux);
                        cr.poner(aux);
                        p1.desapilar();
                    }
                    while (!p2.esVacia()) {
                        aux = (Character) p2.obtenerTope();
                        cr.poner(aux);
                        p2.desapilar();
                    }
                    cr.poner('#');
                }
                c1Copia.sacar();
                if (c1Copia.obtenerFrente() != null) {
                    aux = (Character) c1Copia.obtenerFrente();
                }
            }
            while (!p1.esVacia()) {
                aux = (Character) p1.obtenerTope();
                p2.apilar(aux);
                cr.poner(aux);
                p1.desapilar();
            }
            while (!p2.esVacia()) {
                aux = (Character) p2.obtenerTope();
                cr.poner(aux);
                p2.desapilar();
            }
        }
        return cr;
    }
    

    public static void main (String[] args) {

    }
}
