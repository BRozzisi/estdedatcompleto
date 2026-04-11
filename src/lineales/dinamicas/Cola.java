package lineales.dinamicas;
public class Cola {
    private Nodo frente;
    private Nodo fin;

    // Constructor vacío.
    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    // Pone un elemento al final de la cola. Devuelve true.
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

    // Saca el elemento en el frente de la cola. Devuelve true si la cola tenía al menos un elemento para sacar.
    public boolean sacar() {
        boolean exito = true;
        if (this.frente == null) {                // Si la cola está vacía devuelve false.
            exito = false;
        } else {                                  // Si no está vacía el frente se convierte en su enlace.
            this.frente = this.frente.getEnlace();
            if (this.frente == null) {            // Si el nuevo frente es nulo entonces la cola quedó vacía, se le asigna nulo al fin.
                this.fin = null;
            }
        }
        return exito;
    }

    // Devuelve el elemento en el frente.
    public Object obtenerFrente() {
        Object retorno = null;
        if (this.frente != null) {              // Si el frente no es nulo devolvemos el frente.
            retorno = this.frente.getElemento();
        }
        return retorno;
    }

    // Devuelve true si la cola no tiene elementos.
    public boolean esVacia() {
        return (this.frente == null);
    }

    // Resetea la cola.
    public void vaciar() {
        this.frente = null;
        this.fin = null;
    }

    // Devuelve un clon de la pila.
    @Override
    public Cola clone() {
        Cola copia = new Cola();
        if (!this.esVacia()) {                                                 // Si la cola no está vacía recorre los nodos creando copias de los mismos y asignandolos a copia.
            Nodo nodoCopia = new Nodo(this.frente.getElemento(), null);
            Nodo aux2 = nodoCopia;
            copia.frente = nodoCopia;
            Nodo aux = this.frente.getEnlace();
            while (aux != null) {                                              // Avanza entre los nodos asignando los enlaces correspondientes.
                nodoCopia.setEnlace(new Nodo(aux.getElemento(), null));
                nodoCopia = nodoCopia.getEnlace();
                aux = aux.getEnlace();
            }
            copia.frente = aux2;
        }
        return copia;
    }

    /*
    *
    *   MÓDULOS CON MOTIVO DE DESARROLLO.
    *
    */
    // Devuelve una cadena con todos los elementos de la cola desde el frente hasta el fin.
    @Override
    public String toString() {
        String s = "";
        if (this.frente == null) {                // Si la cola está vacia devuelve "[]".
            s = "[]";
        } else {                                  // Si la cola no está vacía añade los elementos a "s".
            Nodo aux = this.frente;
            s = "Frente - [";
            while (aux != null) {                 // Si aux no es nulo sigue recorriendo y añadiendo elementos.
                s += aux.getElemento().toString();
                aux = aux.getEnlace();
                if (aux != null) {                // Si aux no es nulo añade ", " para seguir añadiendo elementos.
                    s += ",";
                }
            }
            s += "] - Final";
        }
        return s;
    }
}

