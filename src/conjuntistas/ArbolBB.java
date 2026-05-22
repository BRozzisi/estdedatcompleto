package conjuntistas;
import lineales.dinamicas.Lista;
public class ArbolBB {
    private NodoABB raiz;

    public ArbolBB() {
        this.raiz = null;
    }

    public boolean insertar(Comparable elemento) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoABB(elemento);
        } else {
            exito = insertarAux(this.raiz, elemento);
        }
        return exito;
    }

    private boolean insertarAux(NodoABB n, Comparable elemento) {
        boolean exito = true;
        if ((elemento.compareTo(n.getElem())) == 0) {
            exito = false;
        } else if (elemento.compareTo(n.getElem()) < 0) {
            if (n.getIzquierdo() != null) {
                exito = insertarAux(n.getIzquierdo(), elemento);
            } else {
                n.setIzquierdo(new NodoABB(elemento));
            }
        } else {
            if (n.getDerecho() != null) {
                exito = insertarAux(n.getDerecho(), elemento);
            } else {
                n.setDerecho(new NodoABB(elemento));
            }
        }
        return exito;
    }

    public boolean eliminar(Comparable elemento) {
        boolean exito = false;
        NodoABB n = this.raiz;
        NodoABB padre = null;
        boolean esHijoIzq = false;
        int comparacion = 1;
            while ((n != null) && (comparacion != 0)) {
                comparacion = elemento.compareTo(n.getElem());
                if (comparacion == 0) {
                    if ((n.getIzquierdo() != null) && (n.getDerecho() != null)) {
                        /* CASO DOS HIJOS */
                        casoDosHijos(n);
                    } else if ((n.getIzquierdo() != null) || (n.getDerecho() != null)) {
                        /* CASO UN HIJO */
                        casoUnHijo(n, padre, esHijoIzq);
                    } else {
                        /* CASO SIN HIJOS */
                        casoSinHijos(padre, esHijoIzq);
                    }
                    exito = true;
                } else if (comparacion < 0) {
                    padre = n;
                    n = n.getIzquierdo();
                    esHijoIzq = true;
                } else {
                    padre = n;
                    n = n.getDerecho();
                    esHijoIzq = false;
                }
            }

        return exito;
    }

    private void casoSinHijos(NodoABB padre, boolean esHijoIzq) {
        if (padre == null) {
            this.raiz = null;
        } else {
            if (esHijoIzq) {
                padre.setIzquierdo(null);
            } else {
                padre.setDerecho(null);
            }
        }
    }

    private void casoDosHijos(NodoABB padre) {
        NodoABB padreCandidato = padre;
        NodoABB aux;
        NodoABB candidato = padre.getIzquierdo();
        if ((candidato.getDerecho()) != null) {
            while (candidato.getDerecho() != null) {
                padreCandidato = candidato;
                candidato = candidato.getDerecho();
            }
            padre.setElem(candidato.getElem());
            padreCandidato.setDerecho(candidato.getIzquierdo());
        } else {
            padre.setElem(candidato.getElem());
            padre.setIzquierdo(candidato.getIzquierdo());
        }
    }

    private void casoUnHijo(NodoABB n, NodoABB padre, boolean esHijoIzq) {
        if (n.getIzquierdo() != null) {
            if (padre == null) {
                this.raiz = this.raiz.getIzquierdo();
            } else {
                if (esHijoIzq) {
                    padre.setIzquierdo(n.getIzquierdo());
                } else {
                    padre.setDerecho(n.getIzquierdo());
                }
            }
        } else if (n.getDerecho() != null) {
            if (padre == null) {
                this.raiz = this.raiz.getDerecho();
            } else {
                if (esHijoIzq) {
                    padre.setIzquierdo(n.getDerecho());
                } else {
                    padre.setDerecho(n.getDerecho());
                }
            }
        }
    }

    public boolean pertenece(Comparable elemento) {
        boolean pertenece = false;
        int comparacion;
        NodoABB n = this.raiz;
        while ((n != null) && (!pertenece)) {
            comparacion = elemento.compareTo(n.getElem());
            if (comparacion < 0) {
                n = n.getIzquierdo();
            } else if (comparacion > 0) {
                n = n.getDerecho();
            } else {
                pertenece = true;
            }
        }
        return pertenece;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public Lista listar() {
        Lista listaOrdenada = new Lista();
        listarAux(this.raiz, listaOrdenada);
        return listaOrdenada;
    }

    private void listarAux(NodoABB n, Lista l) {
        if (n != null) {
            listarAux(n.getIzquierdo(), l);
            l.insertar(n.getElem(), l.longitud()+1);
            listarAux(n.getDerecho(), l);
        }
    }

    public Lista listarRango(Comparable elemMin, Comparable elemMax) {
        Lista listaRango = new Lista();
        listarRangoAux(this.raiz, listaRango, elemMin, elemMax);
        return listaRango;
    }

    private void listarRangoAux(NodoABB n, Lista l, Comparable elemMin, Comparable elemMax) {
        if (n != null) {
            if (elemMin.compareTo(n.getElem()) <= 0) {
                listarRangoAux(n.getIzquierdo(), l, elemMin, elemMax);
                if (elemMax.compareTo(n.getElem()) >= 0 ) {
                    l.insertar(n.getElem(), l.longitud() + 1);
                    listarRangoAux(n.getDerecho(), l, elemMin, elemMax);
                }
            } else {
                listarRangoAux(n.getDerecho(), l, elemMin, elemMax);
            }
        }
    }

    public Object minimoElem() {
        Object rta = null;
        if (this.raiz != null) {
            NodoABB n = this.raiz;
            while (n.getIzquierdo() != null) {
                n = n.getIzquierdo();
            }
            rta = n.getElem();
        }
        return rta;
    }

    public Object maximoElem() {
        Object rta = null;
        if (this.raiz != null) {
            NodoABB n = this.raiz;
            while (n.getDerecho() != null) {
                n = n.getDerecho();
            }
            rta = n.getElem();
        }
        return rta;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public ArbolBB clone() {
        ArbolBB copia = new ArbolBB();
        copia.raiz = cloneAux(this.raiz);
        return copia;
    }

    private NodoABB cloneAux(NodoABB nOrg) {
        NodoABB n = null;
        if (nOrg != null) {
            n = new NodoABB(nOrg.getElem());
            n.setIzquierdo(cloneAux(nOrg.getIzquierdo()));
            n.setDerecho(cloneAux(nOrg.getDerecho()));
        }
        return n;
    }

    @Override
    public String toString() {
        String s = "";
        if (this.raiz == null) {
            // Si el arbol esta vacío, devuelve:
            s = "[Arbol Binario de Busqueda vacío.]";
        } else {
            // Si el árbol no está vacío, genera al cadena con la raíz, y le suma el retorno
            // del llamado al método
            // auxiliar que recorre todo el árbol.
            s += "Raiz: " + this.raiz.getElem() + "\n";
            s += toStringAux(this.raiz);
        }

        return s;
    }

    private String toStringAux(NodoABB nodo) {
        String s = "";
        boolean tieneIzq = nodo.getIzquierdo() != null;
        boolean tieneDer = nodo.getDerecho() != null;
        s += nodo.getElem() + ": ";
        if (tieneIzq) {
            // Si tiene HI añade el texto correspondiente
            s += "HI: " + nodo.getIzquierdo().getElem() + " // ";
        } else {
            // Si no escribe que es null.
            s += "HI: null // ";
        }
        if (tieneDer) {
            // Si tiene HD añade el texto correspondiente
            s += "HD: " + nodo.getDerecho().getElem() + "\n";
        } else {
            // Si no escribe que es null.
            s += "HD: null\n";
        }
        if (tieneIzq) {
            // Si tiene HI llama recursivamente con el HI
            s += toStringAux(nodo.getIzquierdo());
        }
        if (tieneDer) {
            // Si tiene HD llama recursivamente con el HD
            s += toStringAux(nodo.getDerecho());
        }

        return s;
    }
}
