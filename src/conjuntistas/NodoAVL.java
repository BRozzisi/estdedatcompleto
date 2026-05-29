package conjuntistas;

public class NodoAVL {
    private Object elem;
    private int altura;
    private NodoAVL izquierdo;
    private NodoAVL derecho;

    public NodoAVL(Object elemento) {
        this.elem = elemento;
        this.altura = 0;
        this.izquierdo = null;
        this.derecho = null;
    }

    public Object getElem() {
        return elem;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public int getAltura() {
        return altura;
    }

    public void recalcularAltura() {
        int alturaIzquierdo = -1;
        int alturaDerecho = -1;
        if (this.izquierdo != null) {
            alturaIzquierdo = this.izquierdo.altura;
        } 
        if (this.derecho != null) {
            alturaDerecho = this.derecho.altura;
        }
        if (alturaDerecho <= alturaIzquierdo) {
            this.altura = alturaIzquierdo+1;
        } else {
            this.altura = alturaDerecho+1;
        }
    }

    public int calcularBalance() {
        int balance = 0;
        int alturaIzquierdo = -1;
        if (this.getIzquierdo() != null) {
            alturaIzquierdo = this.izquierdo.altura;
        }
        int alturaDerecho = -1;
        if (this.getDerecho() != null) {
            alturaDerecho = this.derecho.altura;
        }
        balance = alturaIzquierdo - alturaDerecho;
        return balance;
    }

    public NodoAVL getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoAVL izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoAVL getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoAVL derecho) {
        this.derecho = derecho;
    }

    
}
