package conjuntistas;
public class NodoABB {
    private Object elem;
    private NodoABB izquierdo;
    private NodoABB derecho;

    public NodoABB(Object elem) {
        this.elem = elem;
    }

    public Object getElem() {
        return this.elem;
    }

    public void setElem(Object elemento) {
        this.elem = elemento;
    }

    public NodoABB getIzquierdo() {
        return this.izquierdo;
    }

    public NodoABB getDerecho() {
        return this.derecho;
    }

    public void setIzquierdo(NodoABB n) {
        this.izquierdo = n;
    }

    public void setDerecho(NodoABB n) {
        this.derecho = n;
    }
}
