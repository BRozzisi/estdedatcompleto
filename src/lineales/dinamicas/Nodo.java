package lineales.dinamicas;
public class Nodo {
    private Object elemento;
    private Nodo enlace;
    
    // Constructor con elemento y enlace.
    public Nodo(Object elem, Nodo enlace) {
        this.elemento = elem;
        this.enlace = enlace;
    }
    
    // Asigna elem a elemento.
    public void setElemento(Object elem) {
        this.elemento = elem;
    }
    
    // Asigna otro Nodo como enlace.
    public void setEnlace(Nodo enlace) {
        this.enlace = enlace;
    }
    
    // Devuelve elemento.
    public Object getElemento() {
        return this.elemento;
    }
    
    // Devuelve el Nodo enlace.
    public Nodo getEnlace() {
        return this.enlace;
    }
}
