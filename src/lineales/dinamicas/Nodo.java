package lineales.dinamicas;
public class Nodo {
    private Object elemento;
    private Nodo enlace;
    
    public Nodo(Object elem, Nodo enlace) {
        this.elemento = elem;
        this.enlace = enlace;
    }
    
    public void setElemento(Object elem) {
        this.elemento = elem;
    }
    
    public void setEnlace(Nodo enlace) {
        this.enlace = enlace;
    }
    
    public Object getElemento() {
        return this.elemento;
    }
    
    public Nodo getEnlace() {
        return this.enlace;
    }
}
