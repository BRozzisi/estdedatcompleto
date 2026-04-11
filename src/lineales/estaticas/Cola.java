package lineales.estaticas;
public class Cola {
    private Object[] arreglo;
    private int frente;
    private int fin;
    private final int TAMANIO = 8;
    
    // Constructor vacío
    public Cola() {
        this.arreglo = new Object[this.TAMANIO];
        this.frente = 0;
        this.fin = 0;
    }
    
    // Añadir un elemento mas al final de la cola. 
    public boolean poner(Object elem){
        boolean exito = true;
        if (this.frente == (this.fin+1)%this.TAMANIO) { // Verifica si la cola está llena.
            exito = false;
        } else {
            this.arreglo[this.fin%TAMANIO] = elem;
            this.fin = (this.fin+1)%this.TAMANIO;
        }
        return exito; // Retorna true si la cola no estaba llena.
    }
    
    // Sacar el elemento del frente de la cola. 
    public boolean sacar() {
        boolean exito = true;
        if (this.esVacia()) { // Verifica si la cola está vacía.
            exito = false;
        } else {
            this.arreglo[this.frente] = null;
            this.frente = (this.frente+1) % this.TAMANIO;
        }
        return exito; // Retorna true si la cola tenía algun objeto para quitar.
    }
    
    // Devuelve true si la cola esta vacía (frente == fin).
    public boolean esVacia() {
        return this.fin == this.frente;
    }
    
    // Retorna el objeto en el frente de la cola.
    public Object obtenerFrente() {
        return this.arreglo[this.frente];
    }
    
    // Resetea todos los valores de la cola.
    public void vaciar() {
        int i = this.frente;
        while (i>this.fin) { // Recorre los elementos del arreglo únicamente desde el frente hasta el fin.
            this.arreglo[i] = null;
            i = (i+1)%this.TAMANIO;
        }
        this.frente = 0;
        this.fin = 0;
    }
    
    // Devuelve una copia de la cola.
    @Override
    public Cola clone() {
        Cola cClon = new Cola();
        if(!this.esVacia()) { // Verifica si la cola no está vacía
            int i;
            cClon.arreglo = this.arreglo.clone();
            cClon.frente = this.frente;
            cClon.fin = this.fin;
        }
        
        return cClon;
    }
    
    // Imprime todos los objetos de la cola, del frente al final. METODO CON MOTIVOS DE DESARROLLO.
    @Override
    public String toString() {
        String s = "Frente [";
        int i = this.frente;
        if (this.esVacia()) { // Verifica si la cola está vacía
            s = "[]";
        } else {
            do { // Añade las posiciones del arreglo a una cadena hasta que i sea igual que fin
                s += this.arreglo[i].toString();
                i = (i+1)%this.TAMANIO;
                if (i != this.fin) { // Añade una coma en caso de que no sea la ultima posicion del arreglo
                    s += ",";
                }
            } while (i != this.fin);
            s += "] Final";
        }
        return s;
    }
}
