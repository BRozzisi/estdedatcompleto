package lineales.estaticas;
public class Cola {
    private Object[] arreglo;
    private int frente;
    private int fin;
    private final int tamanio = 5;
    
    public Cola() {
        this.arreglo = new Object[this.tamanio];
        this.frente = 0;
        this.fin = 0;
    }
    
    public boolean poner(Object elem){
        boolean exito = true;
        if (this.frente == (this.fin+1)%this.tamanio) {
            exito = false;
        } else {
            this.arreglo[this.fin] = elem;
            this.fin = (this.fin+1)%this.tamanio;
        }
        return exito;
    }
    
    public boolean sacar() {
        boolean exito = true;
        if (this.esVacia()) {
            exito = false;
        } else {
            this.arreglo[this.frente] = null;
            this.frente = (this.frente+1) % this.tamanio;
        }
        return exito;
    }
    
    public boolean esVacia() {
        return this.fin == this.frente;
    }
    
    public Object obtenerFrente() {
        return this.arreglo[this.frente];
    }
    
    public void vaciar() {
        int i;
        for (i=0; i<this.tamanio; i++) {
            this.arreglo[i] = null;
        }
        this.frente = 0;
        this.fin = 0;
    }
    
    @Override
    public Cola clone() {
        Cola cClon = new Cola();
        int i;
        for (i=0; i<this.tamanio; i++) {
            cClon.arreglo[i] = this.arreglo[i];
        }
        cClon.frente = this.frente;
        cClon.fin = this.fin;
        
        return cClon;
    }
    
    @Override
    public String toString() {
        String s = "Frente [";
        int i = this.frente;
        if (this.esVacia()) {
            s = "[]";
        } else {
            do {
                s += this.arreglo[i].toString();
                i = (i+1)%this.tamanio;
                if (i != this.fin) {
                    s += ",";
                }
            } while (i != this.fin);
            s += "] Final";
        }
        return s;
    }
    
    public String mostrarCola() {
        String s = "[";
        if (this.frente == this.fin) {
            s = "Cola vacía";
        } else {
            for (int i=0; i<this.tamanio; i++) {
                s += this.arreglo[i].toString() + ", ";
            }
            s = s.trim();
            s += "] Frente= " + this.frente + " Fin= " + this.fin;
        }
        return s;
    }
}
