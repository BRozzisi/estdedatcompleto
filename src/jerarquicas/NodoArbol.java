package jerarquicas;

public class NodoArbol {
    private Object elem;
    private NodoArbol izquierdo;
    private NodoArbol derecho;

    public NodoArbol(Object elemento, NodoArbol hIzq, NodoArbol hDer) {
        this.elem = elemento;
        this.izquierdo = hIzq;
        this.derecho = hDer;
    }

    public Object getElem() {
        return this.elem;
    }

    public NodoArbol getIzquierdo() {
        return this.izquierdo;
    }

    public NodoArbol getDerecho() {
        return this.derecho;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public void setIzquierdo(NodoArbol nodo) {
        this.izquierdo = nodo;
    }

    public void setDerecho(NodoArbol nodo) {
        this.derecho = nodo;
    }

    @Override
    public String toString() {
        String s = this.elem.toString();
        if (this.izquierdo != null) {
            s += "(" + this.izquierdo.getElem().toString() + ", ";
        } else {
            s += "(n, ";
        }
        
        if (this.derecho != null) {
            s += this.derecho.getElem().toString() + ")";
        } else {
            s += "n)";
        }

        return s;
    }
}
