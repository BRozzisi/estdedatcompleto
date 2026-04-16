package lineales.estaticas;

/**
 * La clase Pila es una estructura de datos de tipo FILO
 * Esta es una implementación estática de pila, donde el tamaño de la misma esta
 * fijada por la variable TAMANIO
 * 
 * @param arreglo - Array de Java utilizado como estructura para almacenar los
 * datos de la pila.
 * @param tope - Entero que determina la posición en arreglo que actúa como el
 * tope de la pila.
 * @param TAMANIO - Entero que determina la cantidad de objetos que puede
 * almacenar la pila.
 * @version 1.0
 * @author Bruno Rozzisi || FAI-5892
 */

public class Pila {
    private Object [] arreglo;
    private int tope;
    private final int TAMANIO = 20;
    
    /**
     * Constructor vacio.
     */
    public Pila() {
        this.arreglo = new Object[TAMANIO]; 
        this.tope = -1;
    }
    
    /**
     * Si el arreglo tiene espacio, apila elemento en tope+1. Añade 1 a tope.
     * @param elemento Elemento que queremos apilar.
     * @return Devuelve true si la el arreglo tiene espacio para apilar. Si no
     *         devuelve false.
     */
    public boolean apilar(Object elemento) {
        boolean hayEspacio = true;
        if (this.tope+1<this.TAMANIO) {
            this.tope++;
            this.arreglo[this.tope] = elemento;
        } else {
            hayEspacio = false;
        }
        return hayEspacio;
    }

    /**
     * Si el arreglo tiene algún elemento, lo elimina de la pila y resta 1 a
     * tope.
     * @return Devuelve true si la pila tenía algun elemento.
     */
    public boolean desapilar() {
        if (!this.esVacia()) {
            this.arreglo[tope] = null;
            this.tope--;
        }
        return !this.esVacia();
    }
    
    /**
     * 
     * @return Devuelve el elemento en arreglo[tope], en caso de que la pila no
     * tenga elementos, devuelve un objeto nulo.
     */
    public Object obtenerTope() {
        Object objeto;
        if (this.tope != -1){
            objeto = this.arreglo[this.tope];
        } else {
            objeto = null;
        }
        return objeto;
    }
    
    /**
     * @return Devuelve true si la pila no tiene ningún elemento.
     */
    public boolean esVacia() {
        return this.tope == -1;
    }
    
    /**
     * Vacía todos los espacios de la pila. Setea tope a -1.
     */
    public void vaciar() {
        if (!this.esVacia()) {
            int i;
            for (i=0; i<this.TAMANIO; i++) {
                this.arreglo[i] = null;
            }
            this.tope = -1;
        }
    }
    
    /**
     * @return Devuelve una copia de la pila.
     */
    @Override
    public Pila clone() {
        Pila pilaClon = new Pila();
        if (!this.esVacia()) {
            pilaClon.arreglo = this.arreglo.clone();
            pilaClon.tope = this.tope;
        }
        return pilaClon;
    }
    
    /**
     * @return Devuelve una cadena con todos los elementos de la pila. Si la
     * pila está vacía devuelve "[]".
     */
    @Override
    public String toString() {
        String cadena = "";
        int i;
        if(this.tope == -1) {                         // Si la pila está vacía devuelve "[]".
            cadena = "[]";
        } else {
            cadena = "Tope --> [";
            for (i=this.tope; i>=0; i--) {            // Si la pila no está vacía recorre los elementos del tope hasta [0] añadiendolos a una cadena.
                cadena += this.arreglo[i].toString(); 
                if(i == 0) {                          // Si es el ultimo elemento se le añade "]" para terminar la cadena.
                    cadena += "]";
                } else {                              // Si no es el último elemento se le añade ", " para continuar la cadena.
                    cadena += ",";
                }
            }
        }
        return cadena;
    }
}
