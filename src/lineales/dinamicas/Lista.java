package lineales.dinamicas;
/**
 * La clase Lista es una estructura de datos que almacena datos de forma similar
 * a un arreglo de Java, almacenando mediante {@link Nodo} un elemento junto
 * con el siguiente en la lista, siendo esta clase la encargada de gestionar
 * todas las posiciones necesarias.
 * @author Bruno Rozzisi - FAI 5892
 */
public class Lista {
    private Nodo cabecera;

    /**
     * Constructor vacío
     */
    public Lista() {
        this.cabecera = null;
    }

    /**
     * Pone un elemento en la posición indicada
     * @param elem elemento a guardar
     * @param pos posicion en la que queremos guardar elem
     * @return true si pos estaba dentro del rango válido para guardar
     */
    public boolean insertar(Object elem, int pos) {
        boolean exito = true;
        if (pos<1 || pos>this.longitud() + 1) {
            exito = false;
        } else {
            if (pos == 1) {
                this.cabecera = new Nodo(elem, this.cabecera);
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i<(pos-1)) {
                    aux = aux.getEnlace();
                    i++;
                }
                Nodo nuevo = new Nodo(elem, aux.getEnlace());
                aux.setEnlace(nuevo);
            }
        }
        return exito;
    }

    /**
     * {@return la cantidad de elementos que tiene almacenados la lista.}
     */
    public int longitud() {
        int i = 0;
        Nodo aux = this.cabecera;
        if (aux != null) {
            do {
                aux = aux.getEnlace();
                i++;
            } while (aux != null);
        }
        return i;
    }

    /**
     * Elimina el elemento en la posición dada.
     * @param pos posición que queremos eliminar.
     * @return true si  la pos dada es válida.
     */
    public boolean eliminar(int pos) {
        boolean exito = true;
        if (pos<1 || pos>this.longitud()) {
            exito = false;
        } else {
            if (pos == 1) {
                this.cabecera = this.cabecera.getEnlace();
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i<(pos-1)) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
        }
        return exito;
    }

    /**
     * {@return el elemento en la posición dada}
     * @param pos posición que queremos recuperar
     */
    public Object recuperar(int pos) {
        Object retorno;
        if (pos<1 || pos>this.longitud()) {
            retorno = null;
        } else {
            Nodo aux = this.cabecera;
            int i = 1;
            while(i != pos) {
                aux = aux.getEnlace();
                i++;
            }
            retorno = aux.getElemento();
        }
        return retorno;
    }

    /**
     * {@return la posición en la que aparece por primera vez el elemento dado
     * en la lista}
     * @param elem elemento que estamos buscando
     */
    public int localizar(Object elem) {
        int pos = -1;
        if (this.cabecera != null) {
            pos = 0;
            boolean encontrado = false;
            Nodo aux = this.cabecera;
            while((aux != null) && (!encontrado)) {
                if(aux.getElemento()==elem) {
                    encontrado = true;
                }
                aux = aux.getEnlace();
                pos++;
            }
        }
        return pos;
    }

    /**
     * {@return true si la lista no tiene elementos}
     */
    public boolean esVacia() {
        return this.cabecera == null;
    }

    /**
     * Vacía todos los elementos de la lista
     */
    public void vaciar() {
        this.cabecera = null;
    }

    /**
     * {@return clon de la lista}
     */
    @Override
    public Lista clone() {
        Lista lClone = new Lista();
        if (!this.esVacia()) {
            Nodo nodoCopia = new Nodo(this.cabecera.getElemento(), null);
            lClone.cabecera = nodoCopia;
            Nodo nodoAux = this.cabecera.getEnlace();;
            while (nodoAux != null) {
                nodoCopia.setEnlace(new Nodo(nodoAux.getElemento(), null));
                nodoCopia = nodoCopia.getEnlace();
                nodoAux = nodoAux.getEnlace();
            }
            
        }
        return lClone;
    }

    /**s
     * {@return una representación textual de la lista}
     */
    @Override
    public String toString() {
        String s = "";
        if(this.esVacia()) {
            s = "[]";
        } else {
            s = "[";
            Nodo aux = this.cabecera;
            while (aux != null) {
                s += aux.getElemento().toString();
                aux = aux.getEnlace();
                if (aux != null) {
                    s += ",";
                }
            }
            s += "]";
        }
        return s;
    }

    // METODOS PARA EJERCICIOS DEL SIMULACRO DEL PRIMER PARCIAL
    public Lista obtenerMultiplos(int num) {
        Lista l = new Lista();
        if (this.longitud() >= num) {
            
        }

        return l;
    }
}
