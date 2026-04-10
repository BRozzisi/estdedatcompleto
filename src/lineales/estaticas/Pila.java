package lineales.estaticas;
public class Pila {
    private Object [] arreglo;
    private int tope;
    private final int tamanio = 20;
    
    public Pila() {
        this.arreglo = new Object[tamanio]; 
        this.tope = -1;
    }
    
    public boolean apilar(Object elemento) {
        boolean hayEspacio = true;
        if (this.tope+1<this.tamanio) {
            this.tope++;
            this.arreglo[tope] = elemento;
        } else {
            hayEspacio = false;
        }
        return hayEspacio;
    }
    public boolean desapilar() {
        boolean esVacia = this.esVacia();
        if (!esVacia) {
            this.arreglo[tope] = null;
            this.tope--;
        }
        return !esVacia;
    }
    
    public Object obtenerTope() {
        Object objeto;
        if (this.tope != -1){
            objeto = this.arreglo[tope];
        } else {
            objeto = null;
        }
        return objeto;
    }
    
    public int getTope() {
        return this.tope;
    }
    
    public boolean esVacia() {
        return this.tope == -1;
    }
    
    public void vaciar() {
        int i;
        for (i=0; i<this.tamanio; i++) {
            this.arreglo[i] = null;
        }
        this.tope = -1;
    }
    
    @Override
    public Pila clone() {
        Pila pilaClon = new Pila();
        int i;
        for (i=0; i<this.tamanio; i++) {
            pilaClon.arreglo[i] = this.arreglo[i];
        }
        pilaClon.tope = this.tope;
        return pilaClon;
    }
    
    @Override
    public String toString() {
        String cadena = "";
        int i;
        if(this.tope == -1) {
            cadena = "[]";
        } else {
            cadena = "Tope --> [";
            for (i=this.tope; i>=0; i--) {
                cadena += this.arreglo[i].toString();
                if(i == 0) {
                    cadena += "]";
                } else {
                    cadena += ",";
                }
            }
        }
        return cadena;
    }
}
