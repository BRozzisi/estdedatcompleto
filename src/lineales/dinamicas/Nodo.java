package lineales.dinamicas;

/**
 * La clase Nodo es una estructura de datos que permite almacenar un {@link
 * Object}, junto con un puntero que lo enlaza con otro {@link Nodo}.
 * @param elemento - {@link Object} que deseamos almacenar.
 * @param enlace - {@link Nodo} al que apunta este.
 * @version 1.0
 * @author Bruno Rozzisi || FAI-5892
 */
public class Nodo {
    private Object elemento;
    private Nodo enlace;
    
    /**
     * Constructor con elemento y enlace.
     * @param elem - {@link Object} que deseamos almacenar.
     * @param enlace - {@link Nodo} al que apunta este.
     */
    public Nodo(Object elem, Nodo enlace) {
        this.elemento = elem;
        this.enlace = enlace;
    }
    
    /**
     * Modifica el objeto en elemento.
     * @param elem - Nuevo {@link Object} que queremos almacenar.
     */
    public void setElemento(Object elem) {
        this.elemento = elem;
    }
    
    /**
     * Modifica el enlace.
     * @param enlace - {@link Nodo} al que queremos que apunte este.
     */
    public void setEnlace(Nodo enlace) {
        this.enlace = enlace;
    }
    
    /**
     * @return Devuelve elemento.
     */
    public Object getElemento() {
        return this.elemento;
    }
    
    /**
     * @return Devuelve el {@link Nodo} al que apunta.
     */
    public Nodo getEnlace() {
        return this.enlace;
    }
}
