package jerarquicas;

public class NodoGen {
    private Object elemento;
    private NodoGen hijoIzquierdo;
    private NodoGen hermanoDerecho;

    public NodoGen(Object e, NodoGen hji, NodoGen hrd) {
        this.elemento = e;
        this.hijoIzquierdo = hji;
        this.hermanoDerecho = hrd;
    }

    public Object getElem() {
        return this.elemento;
    }

    public NodoGen getHijoIzquierdo() {
        return this.hijoIzquierdo;
    }

    public NodoGen getHermanoDerecho() {
        return this.hermanoDerecho;
    }

    public void setElem(Object e) {
        this.elemento = e;
    }

    public void setHijoIzquierdo(NodoGen hi) {
        this.hijoIzquierdo = hi;
    }

    public void setHermanoDerecho(NodoGen hd) {
        this.hermanoDerecho = hd;
    }
}
