package lineales.dinamicas;

public class Pila {
    private Nodo tope;
    
    public Pila() {
        this.tope = null;
    }
    
    public boolean apilar(Object elem) {
        Nodo nuevo = new Nodo(elem, this.tope);
        this.tope = nuevo;
        
        return true;
    }
    
    public boolean desapilar() {
        boolean aux = false;
        if (this.tope != null) {
            this.tope = this.tope.getEnlace();
            aux =  true;
        }
        return aux;
    }
    
    public Object obtenerTope() {
        Object objeto;
        if (this.tope == null) {
            objeto = null;
        } else {
            objeto = this.tope.getElemento();
        }
        return objeto;
    }
    
    public Nodo getTope() {
        return this.tope;
    }
    
    public boolean esVacia() {
        return this.tope == null;
    }
    
    public void vaciar() {
        this.tope = null;
    }
    
    @Override
    public Pila clone() {
        Pila pCopia = new Pila();
        if (this.tope != null) {
            Nodo nodoCopia = new Nodo(this.tope.getElemento(), null);
            Nodo aux2 = nodoCopia;
            Nodo aux = this.tope;
            while (aux.getEnlace() != null){
                aux = aux.getEnlace();
                nodoCopia.setEnlace(new Nodo(aux.getElemento(), null));
                nodoCopia = nodoCopia.getEnlace();
            }
            pCopia.tope = aux2;
        }
        return pCopia;
    }
    
    @Override
    public String toString() {
        String s = "";
        if (this.tope == null) {
            s = "Pila vacía: []";
        } else {
            Nodo aux = this.tope;
            s = "Tope --> [";

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

