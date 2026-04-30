package jerarquicas;

/**
 * La clase NodoArbol es una estructura de datos auxiliar utilizada por
 * {@link ArbolBin} en la cual cada NodoArbol
 * almacena un elemento, junto con dos punteros hacia sus "hijos" izquierdo y
 * derecho.
 * 
 * @param elem      - {@link Object} que se desea guardar en el nodo.
 * @param izquierdo - {@link NodoArbol} que representa al Hijo Izquierdo
 * @param derecho   - {@link NodoArbol} que representa al Hijo Derecho
 * @version 1.00
 * @author @BRozzisi
 */
public class NodoArbol {
    private Object elem;
    private NodoArbol izquierdo;
    private NodoArbol derecho;

    /**
     * Constructor predeterminado.
     * 
     * @param elemento {@link Object} que queremos almacenar en el nodo
     * @param hIzq     {@link NodoArbol} que indica el Hijo Izquierdo (HI)
     * @param hDer     {@link NodoArbol} que indica el Hijo Derecho (HD)
     */
    public NodoArbol(Object elemento, NodoArbol hIzq, NodoArbol hDer) {
        this.elem = elemento;
        this.izquierdo = hIzq;
        this.derecho = hDer;
    }

    /**
     * {@return el {@link Object} que almacena el nodo}
     */
    public Object getElem() {
        return this.elem;
    }

    /**
     * {@return el {@link NodoArbol} que representa el HI}
     */
    public NodoArbol getIzquierdo() {
        return this.izquierdo;
    }

    /**
     * {@return el {@link NodoArbol} que representa al HD}
     */
    public NodoArbol getDerecho() {
        return this.derecho;
    }

    /**
     * @param elem a guardar en this.elem
     */
    public void setElem(Object elem) {
        this.elem = elem;
    }

    /**
     * @param nodo {@link NodoArbol} a guardar en izquierdo
     */
    public void setIzquierdo(NodoArbol nodo) {
        this.izquierdo = nodo;
    }

    /**
     * @param nodo {@link NodoArbol} a guardar en this.derecho
     */
    public void setDerecho(NodoArbol nodo) {
        this.derecho = nodo;
    }

    /**
     * {@return una representación textual del nodo en {@link String}, mostrando su
     * elemento, y los elementos de sus
     * hijos}
     */
    @Override
    public String toString() {
        String s = this.elem.toString();
        if (this.izquierdo != null) {
            // Si tiene HI, añade el elemento del HI a la cadena
            s += "(" + this.izquierdo.getElem().toString() + ", ";
        } else {
            // Si no tiene HI, escribe n, que representa null.
            s += "(n, ";
        }

        if (this.derecho != null) {
            // Si tiene HD, añade el elemento del HD a la cadena
            s += this.derecho.getElem().toString() + ")";
        } else {
            // Si no tiene HD, escribe n, que representa null.
            s += "n)";
        }

        return s;
    }

    public boolean esHoja(){ 
        return ((this.izquierdo == null) && (this.derecho == null));
    }
}
