package lineales.dinamicas;

public class Pila {
    private Nodo tope;
    
    // Constructor vacío.
    public Pila() {
        this.tope = null;
    }
    
    // Apila un elemento. Devuelve true.
    public boolean apilar(Object elem) {
        Nodo nuevo = new Nodo(elem, this.tope);
        this.tope = nuevo;
        
        return true;
    }
    
    // Desapila un elemento. Devuelve true si la pila no estaba vacía.
    public boolean desapilar() {
        boolean aux = false;
        if (this.tope != null) {              // Desapila si la pila tiene algún elemento.
            this.tope = this.tope.getEnlace();
            aux =  true;
        }
        return aux;
    }
    
    // Devuelve el objeto en el tope.
    public Object obtenerTope() {
        Object objeto;
        if (this.tope == null) {              // Si la pila está vacía devuelve nulo.
            objeto = null;
        } else {
            objeto = this.tope.getElemento();
        }
        return objeto;
    }
    
    // Devuelve true si la pila está vacía.
    public boolean esVacia() {
        return this.tope == null;
    }
    
    // Resetea la pila.
    public void vaciar() {
        this.tope = null;
    }
    
    // Devuelve un clon de la pila.
    @Override
    public Pila clone() {
        Pila pCopia = new Pila();
        if (this.tope != null) {                                               // Si la pila no está vacía, copia todos los nodos y sus enlaces.
            Nodo nodoCopia = new Nodo(this.tope.getElemento(), null);
            Nodo aux2 = nodoCopia;
            Nodo aux = this.tope;
            while (aux.getEnlace() != null){                                   // Mientras aux tenga un enlace existente, avanza y clona su elemento en nodoCopia.
                aux = aux.getEnlace();
                nodoCopia.setEnlace(new Nodo(aux.getElemento(), null));
                nodoCopia = nodoCopia.getEnlace();
            }
            pCopia.tope = aux2;
        }
        return pCopia;
    }

    /*
    *
    *
    *     MÉTODOS CON MOTIVOS DE DESARROLLO.
    *
    */
    // Devuelve una cadena con todos los elementos de la pila.
    @Override
    public String toString() {
        String s = "";
        if (this.tope == null) {                  // Si la pila está vacía devuelve "[]".
            s = "[]";
        } else {                                  // Si la pila no está vacía añade los elementos del tope al fondo.
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

    // Devuelve el Nodo en el tope.
    public Nodo getTope() {
        return this.tope;
    }
}

