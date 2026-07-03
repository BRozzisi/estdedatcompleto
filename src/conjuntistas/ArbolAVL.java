package conjuntistas;

public class ArbolAVL {
    private NodoAVL raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    public boolean pertenece(Comparable elem) {
        boolean pertenece = false;
        int comparacion;
        NodoAVL nodoAux = this.raiz;
        while ((nodoAux != null) && (!pertenece)) {
            comparacion = elem.compareTo(nodoAux.getElem());
            if (comparacion < 0) {
                nodoAux = nodoAux.getIzquierdo();
            } else if (comparacion > 0) {
                nodoAux = nodoAux.getDerecho();
            } else {
                pertenece = true;
            }
        }
        return pertenece;
    }

    public boolean insertar(Comparable elemento) {
        boolean exito = true;
        if (this.raiz == null) {
            // Si la raiz es nula, entonces asigna el elemento en la raiz
            this.raiz = new NodoAVL(elemento);
        } else {
            // Si la raiz no es nula retorna el resultado del metodo auxiliar
            exito = insertarAux(this.raiz, null, elemento, false);
        }
        return exito;
    }

    private boolean insertarAux(NodoAVL n, NodoAVL padre, Comparable elemento, boolean lugarN) {
        boolean exito = true;
        int balance = 0;
        if ((elemento.compareTo(n.getElem())) == 0) {
            /* 
            * Si el elemento es igual al del nodo actual, entonces retorna false ya que no
            * puede haber 2 elementos
            * iguales en esta implementacion del AVL
            */
            exito = false;
        } else if (elemento.compareTo(n.getElem()) < 0) {
            // Si el elemento es mas chico que el del nodo actual, pregunta:
            if (n.getIzquierdo() != null) {
                /*
                * Si el nodo actual ya tiene HI entonces llama recursivamente con el para que
                * lo inserte en su subarbol izquierdo.
                */
                exito = insertarAux(n.getIzquierdo(), n, elemento, false);
            } else {
                // Si el nodo actual no tiene HI entonces ingresa el elemento ahi.
                n.setIzquierdo(new NodoAVL(elemento));
            }
        } else {
            // Si el elemento es mas grande que el del nodo actual, pregunta:
            if (n.getDerecho() != null) {
                // Si el nodo actual tiene HD entonces llama recursivamente con el para que lo
                // inserte en su subarbol
                // derecho
                exito = insertarAux(n.getDerecho(), n, elemento, true);
            } else {
                // Si el nodo actual no tiene HD entonces inserta elemento ahi
                n.setDerecho(new NodoAVL(elemento));
            }
        }
        if (exito) {
            // Luego de la inserción, se recalcula la altura de los nodos a la vuelta de la recursión.
            n.recalcularAltura();
            // Tambien se calcula el balance de cada nodo con la nueva altura.
            balance = n.calcularBalance();
        }
        if ((balance <= -2) || (balance >= 2)) {
            // Si el balance no esta entre -1 y 1 inclusive, realiza las rotaciones correspondientes.
            if (padre == null) {
                this.raiz = realizarRotacion(n, balance);
            } else {
                if (lugarN) {
                    // lugarN es una variable que lleva la informacion de que lado esta como hijo el nodo que debe ser
                    // rotado
                    padre.setDerecho(realizarRotacion(n, balance));
                } else {
                    padre.setIzquierdo(realizarRotacion(n, balance));
                }
            }
        }
        // Retorna false solo si el elemento ingresado ya estaba en el arbol
        return exito;
    }

    private NodoAVL realizarRotacion(NodoAVL n, int balance) {
        // Este metodo decide cual de las 4 rotaciones debe realizarse, segun el balance del nodo y el de su hijo.
        NodoAVL nodoRetorno = null;
        if (balance > 0) {
            int balanceIzquierdo = n.getIzquierdo().calcularBalance();
            if ((balanceIzquierdo == 1) || (balanceIzquierdo == 0)){ 
                // System.out.println("Se realiza una rotacion simple a derecha con pivote " + n.getElem());
                nodoRetorno = rotacionSimpleDer(n);
            } else if (balanceIzquierdo < 0) {
                // System.out.println("Se realiza una rotacion doble der-izq con pivote " + n.getElem());
                nodoRetorno = rotacionDobleIzqDer(n);
            }
        } else if (balance < 0){ 
            int balanceDerecho = n.getDerecho().calcularBalance();
            if ((balanceDerecho == -1) || (balanceDerecho == 0)) {
                // System.out.println("Se realiza una rotacion simple a izquierda con pivote " + n.getElem());
                nodoRetorno = rotacionSimpleIzq(n);
            } else if (balanceDerecho > 0) {
                // System.out.println("Se realiza una rotacion doble izq-der con pivote " + n.getElem());
                nodoRetorno = rotacionDobleDerIzq(n);
            }
        }
        // Retorna la nueva raiz del subarbol que debe ser ubicada como hijo del padre de n en la recursion de insertarAux(...)
        return nodoRetorno;
    }

    private NodoAVL rotacionSimpleIzq(NodoAVL r) {
        NodoAVL h = r.getDerecho();
        NodoAVL temp = h.getIzquierdo();
        h.setIzquierdo(r);
        r.setDerecho(temp);

        r.recalcularAltura();
        h.recalcularAltura();

        return h;
    }

    private NodoAVL rotacionSimpleDer(NodoAVL r) {
        NodoAVL h = r.getIzquierdo();
        NodoAVL temp = h.getDerecho();
        h.setDerecho(r);
        r.setIzquierdo(temp);

        r.recalcularAltura();
        h.recalcularAltura();

        return h;
    }

    private NodoAVL rotacionDobleDerIzq(NodoAVL r) {
        r.setDerecho(rotacionSimpleDer(r.getDerecho()));
        NodoAVL h = rotacionSimpleIzq(r);
        return h;
    }

    private NodoAVL rotacionDobleIzqDer(NodoAVL r) {
        r.setIzquierdo(rotacionSimpleIzq(r.getIzquierdo()));
        NodoAVL h = rotacionSimpleDer(r);
        return h;
    }

    @Override
    public String toString() {
        String s = "";
        if (this.raiz == null) {
            // Si el arbol esta vacío, devuelve:
            s = "[Arbol Binario de Busqueda vacío.]";
        } else {
            // Si el árbol no está vacío, genera al cadena con la raíz, y le suma el retorno
            // del llamado al método
            // auxiliar que recorre todo el árbol.
            s += "Raiz: " + this.raiz.getElem() + "\n";
            s += toStringAux(this.raiz);
        }

        return s;
    }

    private String toStringAux(NodoAVL nodo) {
        String s = "";
        boolean tieneIzq = nodo.getIzquierdo() != null;
        boolean tieneDer = nodo.getDerecho() != null;
        s += nodo.getElem() + ": ";
        if (tieneIzq) {
            // Si tiene HI añade el texto correspondiente
            s += "HI: " + nodo.getIzquierdo().getElem() + " // ";
        } else {
            // Si no escribe que es null.
            s += "HI: null // ";
        }
        if (tieneDer) {
            // Si tiene HD añade el texto correspondiente
            s += "HD: " + nodo.getDerecho().getElem() + "\n";
        } else {
            // Si no escribe que es null.
            s += "HD: null\n";
        }
        if (tieneIzq) {
            // Si tiene HI llama recursivamente con el HI
            s += toStringAux(nodo.getIzquierdo());
        }
        if (tieneDer) {
            // Si tiene HD llama recursivamente con el HD
            s += toStringAux(nodo.getDerecho());
        }

        return s;
    }
}
