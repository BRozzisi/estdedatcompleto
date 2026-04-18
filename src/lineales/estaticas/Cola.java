package lineales.estaticas;

/**
 * La clase Cola es una estructura de datos de tipo FIFO
 * Esta es una implementación estática de Cola, donde la capacidad de guardado
 * esta determinado por la variable TAMANIO.
 * @param arreglo - array de Java utilizado de forma circular para guardar los
 * objetos en la cola.
 * @param frente - indice del arreglo que actúa como frente de la cola.
 * @param fin - indice del arreglo que actúa como final de la cola.
 * @param TAMANIO - entero final que define la cantidad de elementos que puede
 * guardar la cola. En esta implementación la cantidad de objetos útiles que
 * puede almacenar la cola se reduce a TAMANIO-1.
 * @version 1.0
 * @author Bruno Rozzisi || FAI-5892
 */
public class Cola {
    private Object[] arreglo;
    private int frente;
    private int fin;
    private final int TAMANIO = 8;
    
    /**
     * Constructor vacío
     */
    public Cola() {
        this.arreglo = new Object[this.TAMANIO];
        this.frente = 0;
        this.fin = 0;
    }
    
    /**
     * Si la cola tiene espacio, pone un elemento al fin de la misma.
     * @param elem - Objeto a poner.
     * @return Devuelve true si había espacio en la cola.
     */
    public boolean poner(Object elem){
        boolean exito = true;
        if (this.frente == (this.fin+1)%this.TAMANIO) {
            exito = false;
        } else {
            this.arreglo[this.fin%TAMANIO] = elem;
            this.fin = (this.fin+1)%this.TAMANIO;
        }
        return exito;
    }
    
    /**
     * Saca el elemento en el frente de la cola.
     * @return Devuelve true si la cola tenía algún elemento para sacar.
     */
    public boolean sacar() {
        boolean exito = true;
        if (this.esVacia()) {
            exito = false;
        } else {
            this.arreglo[this.frente] = null;
            this.frente = (this.frente+1) % this.TAMANIO;
        }
        return exito;
    }
    
    /**
     * @return Devuelve true si la cola no tiene elementos.
     */
    public boolean esVacia() {
        return this.fin == this.frente;
    }
    
    /**
     * @return Devuelve el objeto en el frente.
     */
    public Object obtenerFrente() {
        return this.arreglo[this.frente];
    }
    
    /**
     * Resetea todos los valores de la cola a nulos. Resetea frente y fin al
     * indice 0.
     */
    public void vaciar() {
        if (!this.esVacia()) {
            int i = this.frente;
            while (i>this.fin) {
                this.arreglo[i] = null;
                i = (i+1)%TAMANIO;
            }
            this.frente = this.fin = 0;
        }
    }
    
    /**
     * Si la cola no esta vacía, se crea un clon de la misma.
     * @return Devuelve la copia creada.
     */
    @Override
    public Cola clone() {
        Cola cClon = new Cola();
        if(!this.esVacia()) {
            cClon.arreglo = this.arreglo.clone();
            cClon.frente = this.frente;
            cClon.fin = this.fin;
        }
        
        return cClon;
    }
    
    /**
     * @return Devuelve una representación textual de la cola. Si la cola está
     * vacía devuelve "[]".
     */
    @Override
    public String toString() {
        String s = "Frente [";
        int i = this.frente;
        if (this.esVacia()) {
            s = "[]";
        } else {
            do {
                s += this.arreglo[i].toString();
                i = (i+1)%this.TAMANIO;
                if (i != this.fin) {
                    s += ",";
                }
            } while (i != this.fin);
            s += "] Final";
        }
        return s;
    }
}
