package lineales.dinamicas;

public class Lista {
    // localizar(Object elem) : int
    // esVacia() : boolean
    // vaciar() : void
    // toString() : String
    // clone() : Lista
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
}
