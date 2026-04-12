package lineales.dinamicas;

/**
 * La clase Cola es una estructura de datos dinámica que permite almacenar
 * objetos de forma FIFO (First In, First Out).
 * @param frente - {@link Nodo} que almacena el objeto en el frente y un puntero
 * al segundo elemento de la cola.
 * @param fin - {@link Nodo} que almacena el último objeto almacenado en la cola.
 * @version 1.0
 * @author Bruno Rozzisi || FAI-5892.
 */
public class Cola {
    private Nodo frente;
    private Nodo fin;

    /**
     * Constructor vacío.
     */
    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    /**
     * Pone un {@link Object} al final de la cola.
     * @param elem {@link Object} a poner.
     * @return Devuelve true.
     */
    public boolean poner(Object elem) {
        Nodo nuevo = new Nodo(elem, null);
        if (this.frente == null) {                // Si la cola está vacía coloca el elem en el frente.
            this.frente = nuevo;
        }  else {                                 // Si no está vacía coloca el elem como enlace del fin.
            this.fin.setEnlace(nuevo);
        }
        this.fin = nuevo;
        return true;
    }

    /**
     * Si la cola no está vacía, saca el objeto en el frente de la cola.
     * @return Devuelve true si la cola no estaba vacía.
     */
    public boolean sacar() {
        boolean exito = true;
        if (this.frente == null) {
            exito = false;
        } else {
            this.frente = this.frente.getEnlace();
            if (this.frente == null) {
                this.fin = null;
            }
        }
        return exito;
    }

    /**
     * @return Si la pila no está vacía devuelve el objeto en el frente.
     */
    public Object obtenerFrente() {
        Object retorno = null;
        if (this.frente != null) {
            retorno = this.frente.getElemento();
        }
        return retorno;
    }

    /**
     * @return Devuelve true si la cola está vacía.
     */
    public boolean esVacia() {
        return (this.frente == null);
    }

    /**
     * Resetea todos los valores de la cola a valores iniciales.
     */
    public void vaciar() {
        this.frente = null;
        this.fin = null;
    }

    /**
     * Si la cola no está vacía, copia todos los Nodos a un clon.
     * @return Devuelve el clon creado.
     */
    @Override
    public Cola clone() {
        Cola copia = new Cola();
        if (!this.esVacia()) {
            Nodo nodoCopia = new Nodo(this.frente.getElemento(), null);
            Nodo aux2 = nodoCopia;
            copia.frente = nodoCopia;
            Nodo aux = this.frente.getEnlace();
            while (aux != null) {
                nodoCopia.setEnlace(new Nodo(aux.getElemento(), null));
                nodoCopia = nodoCopia.getEnlace();
                aux = aux.getEnlace();
            }
            copia.frente = aux2;
        }
        return copia;
    }

    /**
     * Representa los valores almacenados en la cola en forma de {@link String}.
     * @return Devuelve la cadena creada. Si la pila está vacía devuelve "[]".
     */
    @Override
    public String toString() {
        String s = "";
        if (this.frente == null) {
            s = "[]";
        } else {
            Nodo aux = this.frente;
            s = "Frente - [";
            while (aux != null) {
                s += aux.getElemento().toString();
                aux = aux.getEnlace();
                if (aux != null) {
                    s += ",";
                }
            }
            s += "] - Final";
        }
        return s;
    }
}

