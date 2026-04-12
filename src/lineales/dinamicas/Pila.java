package lineales.dinamicas;

/**
 * La clase Pila es una estructura de datos dinámica que permite almacenar
 * Objects uno encima del otro, pudiendo acceder únicamente al último objeto
 * añadido.
 * @param tope - {@link Nodo} que representa el elemento en el tope.
 * @version 1.0
 * @author Bruno Rozzisi || FAI-5892
 */
public class Pila {
    private Nodo tope;
    
    /**
     * Constructor vacío
     */
    public Pila() {
        this.tope = null;
    }
    
    /**
     * Coloca un {@link Nodo} en el tope de la pila, convirtiendo a este en el
     * nuevo tope.
     * @param elem - {@link Object} que será utilizado como elemento para el
     * {@link Nodo} del tope.
     * @return true.
     */
    public boolean apilar(Object elem) {
        Nodo nuevo = new Nodo(elem, this.tope);
        this.tope = nuevo;
        
        return true;
    }
    
    /**
     * Saca de la pila el {@link Nodo} que está en el tope.
     * @return Devuelve true si la pila no está vacía.
     */
    public boolean desapilar() {
        boolean aux = false;
        if (this.tope != null) {
            this.tope = this.tope.getEnlace();
            aux =  true;
        }
        return aux;
    }
    
    /**
     * @return Devuelve el {@link Object} en el {@link Nodo} del tope. Si la pila está
     * vacía devuelve nulo.
     */
    public Object obtenerTope() {
        Object objeto;
        if (this.tope == null) {
            objeto = null;
        } else {
            objeto = this.tope.getElemento();
        }
        return objeto;
    }
    
    /**
     * {@return true si la pila no tiene ningún elemento.}
     */
    public boolean esVacia() {
        return this.tope == null;
    }
    
    /**
     * Resetea todos los valores de la pila.
     */
    public void vaciar() {
        this.tope = null;
    }
    
    /**
     * {@return a clone}
     */
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

    /**
     * {@return una representación textual de la pila en forma de 
     * {@link String}. Si la pila está vacía devuelve "[]".}
     */
    @Override
    public String toString() {
        String s = "";
        if (this.tope == null) {
            s = "[]";
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

