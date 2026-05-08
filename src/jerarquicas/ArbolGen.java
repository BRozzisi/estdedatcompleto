package jerarquicas;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;

public class ArbolGen {
    private NodoGen raiz;

    public ArbolGen() {
        this.raiz = null;
    }

    public boolean insertar(Object elem, Object elemPadre) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoGen(elem, null, null);
        } else {
            NodoGen padre = buscarPorElem(this.raiz, elemPadre);
            if (padre != null) {
                padre.setHijoIzquierdo(new NodoGen(elem, null, padre.getHijoIzquierdo()));
            } else {
                exito = false;
            }
        }
        return exito;
    }

    private NodoGen buscarPorElem(NodoGen n, Object elem) {
        NodoGen nBuscado = null;
        if (n != null) {
            if (n.getElem() == elem) {
                nBuscado = n;
            } else {
                NodoGen hijo = n.getHijoIzquierdo();
                if (hijo == null) {
                    hijo = n.getHermanoDerecho();
                    nBuscado = buscarPorElem(hijo, elem);
                } else {
                    nBuscado = buscarPorElem(hijo, elem);
                    if (nBuscado == null) {
                        hijo = n.getHermanoDerecho();
                        nBuscado = buscarPorElem(hijo, elem);
                    }
                }
                
            }
        }
        return nBuscado;
    }

    public boolean insertarPorPosicion(Object elem, int posPadre) {
        boolean exito = true;
        if (posPadre < 0) {
            exito = false;
        } else if (posPadre == 0) {
            if (this.raiz == null) {
                this.raiz = new NodoGen(elem, null, null);
            } else {
                exito = false;
            }
        } else {
            if (this.raiz != null) {
                int[] i = {0};
                NodoGen padre = buscarPorPos(this.raiz, posPadre, i);
                if (padre != null) {
                    padre.setHijoIzquierdo(new NodoGen(elem, null, padre.getHijoIzquierdo()));
                } else {
                    exito = false;
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    private NodoGen buscarPorPos(NodoGen n, int posPadre, int[] i) {
        NodoGen nBuscado = null;
        if (n != null) {
            i[0] = i[0] + 1;
            if (i[0] == posPadre) {
                nBuscado = n;
            } else {
                NodoGen hijo = n.getHijoIzquierdo();
                while ((hijo != null) && (nBuscado == null)) {
                    nBuscado = buscarPorPos(hijo, posPadre, i);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return nBuscado;
    }

    public boolean pertenece(Object elem) {
        return perteneceAux(this.raiz, elem);
    }

    private boolean perteneceAux(NodoGen n, Object elem) {
        boolean pertenece = false;
        if (n != null) {
            if (n.getElem() == elem) {
                pertenece = true;
            } else {
                NodoGen hijo = n.getHijoIzquierdo();
                while ((hijo != null) && (!pertenece)) {
                    pertenece = perteneceAux(hijo, elem);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return pertenece;
    }

    public Lista ancestros(Object elem) {
        Lista listaAncestros = new Lista();
        ancestrosAux(this.raiz, elem, listaAncestros);
        return listaAncestros;
    }

    private boolean ancestrosAux(NodoGen n, Object elem, Lista l) {
        boolean encontrado = false;
        if (n !=  null) {
            if (n.getElem() == elem) {
                encontrado = true;
                l.insertar(n.getElem(), 1);
            } else {
                NodoGen hijo = n.getHijoIzquierdo();
                while ((hijo != null) && (!encontrado)) {
                    encontrado = ancestrosAux(hijo, elem, l);
                    hijo = hijo.getHermanoDerecho();
                }
                if (encontrado) {
                    l.insertar(n.getElem(), 1);
                }
            }
        }
        return encontrado;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public int altura() {
        int i;
        if (this.raiz == null) {
            i = -1;
        } else if (this.raiz.getHijoIzquierdo() == null) {
            i = 0;
        } else {
            i = alturaAux(this.raiz, 0);
        }
        return i;
    }

    private int alturaAux(NodoGen n, int i) {
        int j = i;
        int aux;
        NodoGen hijo = n.getHijoIzquierdo();
        while (hijo != null) {
            aux = alturaAux(hijo, j+1);
            if (aux > i) {
                i = aux;
            }
            hijo = hijo.getHermanoDerecho();
        }
        if (i > j) {
            j = i;
        }
        return j;
    }

    public int nivel(Object elem) {
        int nivel = -1;
        boolean[] aux = {false};
        if (this.raiz != null) {
            nivel = nivelAux(this.raiz, elem, 0, aux);
        } 
        if ((this.raiz == null) || (!aux[0])) {
            nivel = -1;
        }
        return nivel;
    }

    private int nivelAux(NodoGen n, Object elem, int i, boolean[] encontrado) {
        int j = i;
        if (n.getElem() == elem) {
            encontrado[0] = true;
        } else {
            NodoGen hijo = n.getHijoIzquierdo();
            while ((hijo != null) && (!encontrado[0])) {
                i = nivelAux(hijo, elem, j+1, encontrado);
                hijo = hijo.getHermanoDerecho();
            }
            if (encontrado[0]) {
                j = i;
            }
        }
        return j;
    }

    public Object padre(Object elem) {
        Object retorno = null;
        boolean[] encontrado = {false};
        if ((this.raiz != null) && (this.raiz.getElem() != elem)) {
            NodoGen nPadre = buscarPadre(this.raiz, elem, encontrado);
            if (nPadre != null) {
                retorno = nPadre.getElem();
            }
        }
        return retorno;
    }

    private NodoGen buscarPadre(NodoGen n, Object elem, boolean[] encontrado) {
        NodoGen nPadre = null;
        if (n.getElem() == elem) {
            encontrado[0] = true;
        } else {
            NodoGen hijoActual = n.getHijoIzquierdo();
            while ((hijoActual != null) && (nPadre == null)) {
                nPadre = buscarPadre(hijoActual, elem, encontrado);
                if ((nPadre == null) && (encontrado[0])) {
                    nPadre = n;
                }
                hijoActual = hijoActual.getHermanoDerecho();
            }
        }

        return nPadre;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public ArbolGen clone() {
        ArbolGen copia = new ArbolGen();
        copia.raiz = cloneAux(this.raiz);
        return copia;
    }

    private NodoGen cloneAux(NodoGen n) {
        NodoGen nAux = null;
        if (n != null) {
            nAux = new NodoGen(n.getElem(), n.getHijoIzquierdo(), n.getHermanoDerecho());
        }
        return nAux;
    }

    public Lista listarPreorden() {
        Lista lpre = new Lista();
        listarPreordenAux(this.raiz, lpre);
        return lpre;
    }

    private void listarPreordenAux(NodoGen n, Lista l) {
        if (n != null) {
            l.insertar(n.getElem(), l.longitud()+1);
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                listarPreordenAux(hijo, l);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    public Lista listarInorden() {
        Lista lin = new Lista();
        listarInordenAux(this.raiz, lin);
        return lin;
    }

    private void listarInordenAux(NodoGen n, Lista l) {
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            if (hijo != null) {
                listarInordenAux(hijo, l);
            }
            l.insertar(n.getElem(), l.longitud()+1);
            if (hijo != null) {
                hijo = hijo.getHermanoDerecho();
                while (hijo != null) {
                    listarInordenAux(hijo, l);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public Lista listarPosorden() {
        Lista lpos = new Lista();
        listarPosordenAux(this.raiz, lpos);
        return lpos;
    }

    private void listarPosordenAux(NodoGen n, Lista l) {
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                listarPosordenAux(hijo, l);
                hijo = hijo.getHermanoDerecho();
            }
            l.insertar(n.getElem(), l.longitud()+1);
        }
    }

    public Lista listarPorNiveles() {
        Lista l = new Lista();
        Cola q = new Cola();
        q.poner(this.raiz);
        while (!q.esVacia()) {
            NodoGen nodoAux = (NodoGen) q.obtenerFrente();
            q.sacar();
            l.insertar(nodoAux.getElem(), l.longitud()+1);
            NodoGen hijo = nodoAux.getHijoIzquierdo();
            while (hijo != null) {
                q.poner(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return l;
    }

    public String toString() {
        String s = "";
        if (this.raiz == null) {
            s = "[]";
        } else {
            s = toStringAux(this.raiz);
        }
        return s;
    }

    private String toStringAux(NodoGen n) {
        String s = "";
        if (n != null) {
            s += n.getElem().toString() + " -> ";
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                s += hijo.getElem().toString() + ", ";
                hijo = hijo.getHermanoDerecho();
            }
            hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                s += "\n" + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return s;
    }
}
