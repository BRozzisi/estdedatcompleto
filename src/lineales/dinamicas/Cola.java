package lineales.dinamicas;
public class Cola {
    private Nodo frente;
    private Nodo fin;

    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    public boolean poner(Object elem) {
        Nodo nuevo = new Nodo(elem, null);
        if (this.frente == null) {
            this.frente = nuevo;
        }  else {
            this.fin.setEnlace(nuevo);
        }
        this.fin = nuevo;
        return true;
    }

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

    public Object obtenerFrente() {
        Object retorno = null;
        if (this.frente != null) {
            retorno = this.frente.getElemento();
        }
        return retorno;
    }

    public boolean esVacia() {
        return (this.frente == null);
    }

    public void vaciar() {
        this.frente = null;
        this.fin = null;
    }

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

    @Override
    public String toString() {
        String s = "";
        if (this.frente == null) {
            s = "La cola esta vacía.";
        } else {
            Nodo aux = this.frente;
            s = "Frente - [";
            while (aux != null) {
                s += aux.getElemento().toString();
                aux = aux.getEnlace();
                if (aux != null) {
                    s += ", ";
                }
            }
            s += "] - Final";
        }
        return s;
    }
}

