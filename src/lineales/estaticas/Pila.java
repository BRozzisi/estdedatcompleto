package lineales.estaticas;
public class Pila {
    private Object [] arreglo;
    private int tope;
    private final int TAMANIO = 20;
    
    // Constructor vacío.
    public Pila() {
        this.arreglo = new Object[TAMANIO]; 
        this.tope = -1;
    }
    
    // Pone un objeto en el tope, le suma 1 al tope. Retorna true si la pila no estaba llena.
    public boolean apilar(Object elemento) {
        boolean hayEspacio = true;
        if (this.tope+1<this.TAMANIO) { // Añade el objeto solo si hay espacio en la pila.
            this.tope++;
            this.arreglo[tope] = elemento;
        } else {                        // Devuelve false si está llena.
            hayEspacio = false;
        }
        return hayEspacio;
    }

    // Pone nulo al objeto del tope, le resta 1 al tope. Devuelve true si la pila no estaba vacía
    public boolean desapilar() {
        if (!this.esVacia()) {        // Pone nulo solo si la pila no está vacía.
            this.arreglo[tope] = null;
            this.tope--;
        }
        return !this.esVacia();
    }
    
    // Devuelve el objeto que se encuentra en el tope.
    public Object obtenerTope() {
        Object objeto;
        if (this.tope != -1){           // Revisa que la pila no esté vacía.
            objeto = this.arreglo[tope];
        } else {
            objeto = null;              // Si la pila está vacía devuelve nulo.
        }
        return objeto;
    }
    
    // Devuelve el valor del tope.
    public int getTope() {
        return this.tope;
    }
    
    // Devuelve true si la pila no tiene ningún elemento.
    public boolean esVacia() {
        return this.tope == -1;
    }
    
    // Resetea la pila a un estado vacío.
    public void vaciar() {
        if (!this.esVacia()) {               // Revisa que la pila no esté vacía.
            int i;
            for (i=0; i<this.TAMANIO; i++) { // Recorre toda la pila y le pone nulo a los valores.
                this.arreglo[i] = null;
            }
            this.tope = -1;
        }
    }
    
    // Devuelve un clon de la pila.
    @Override
    public Pila clone() {
        Pila pilaClon = new Pila();
        if (!this.esVacia()) { // Copia el arreglo y el tope solo si la pila no está vacía.
            pilaClon.arreglo = this.arreglo.clone();
            pilaClon.tope = this.tope;
        }
        return pilaClon;
    }
    
    // Devuelve una cadena con todos los elementos actuales de la pila, desde el tope hasta el fondo.
    // MÉTODO CON MOTIVOS DE DESARROLLO
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
