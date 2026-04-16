package lineales.dinamicas;

public class Lista {
    private Nodo cabecera;

    public Lista() {
        this.cabecera = null;
    }

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

    public boolean esVacia() {
        return this.cabecera == null;
    }

    public void vaciar() {
        this.cabecera = null;
    }

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
}
