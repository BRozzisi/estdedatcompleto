package jerarquicas;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;

/**
 * Otras operaciones dadas en el apunte
 */

public class ArbolBin {
    private NodoArbol raiz;

    /**
     * Crea un árbol binario vacío
     */
    public ArbolBin() {
        raiz = null;
    }

    /**
     * Dado un elemento elemNuevo y un elemento elemPadre, inserta elemNuevo como
     * hijo izquierdo o
     * derecho de la primer aparición de elemPadre, según lo indique el parámetro
     * posHijo. Para que la operación
     * termine con éxito debe existir un nodo en el árbol con elemento = elemPadre y
     * ese nodo debe tener libre
     * su hijo posHijo. Si puede realizar la inserción devuelve verdadero, en caso
     * contrario devuelve falso.
     * 
     * @param elemNuevo elemento que va a almacenar el Nodo
     * @param elemPadre elemento padre del nuevo Nodo
     * @param posHijo   true si es HI, false si es HD
     * @return true si el padre fue encontrado y la posHijo del padre estaba libre.
     */
    public boolean insertar(Object elemNuevo, Object elemPadre, boolean posHijo) {
        boolean exito = true;
        if (this.raiz == null) {
            // Si el arbol está vacío pone elemNuevo en la raiz.
            this.raiz = new NodoArbol(elemNuevo, null, null);
        } else {
            // Si el arbol no está vacío busca al padre
            NodoArbol nPadre = obtenerNodo(this.raiz, elemPadre);
            // Si padre existe y lugar posHijo no esta ocupado lo pone, si no da error
            if (nPadre != null) {
                if ((posHijo) && (nPadre.getIzquierdo() == null)) {
                    nPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else if (!posHijo && nPadre.getDerecho() == null) {
                    nPadre.setDerecho(new NodoArbol(elemNuevo, null, null));
                } else {
                    exito = false;
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    private NodoArbol obtenerNodo(NodoArbol n, Object buscado) {
        NodoArbol resultado = null;
        if (n != null) {
            if (n.getElem().equals(buscado)) {
                resultado = n;
            } else {
                resultado = obtenerNodo(n.getIzquierdo(), buscado);
                if (resultado == null) {
                    resultado = obtenerNodo(n.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

    public boolean insertarPorPosicion(Object elemNuevo, int posPadre, boolean posHijo) {
        boolean exito = true;
        if (this.raiz == null) {
            if (posPadre == 0) {
                this.raiz = new NodoArbol(elemNuevo, null, null);
            } else {
                exito = false;
            }
        } else {
            NodoArbol nPadre;
            int[] aux = { 1 };
            nPadre = buscarPosPreorden(this.raiz, posPadre, aux);
            if (nPadre != null) {
                if (posHijo && nPadre.getIzquierdo() == null) {
                    nPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else if (!posHijo && nPadre.getDerecho() == null) {
                    nPadre.setDerecho(new NodoArbol(elemNuevo, null, null));
                } else {
                    exito = false;
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    private NodoArbol buscarPosPreorden(NodoArbol nodo, int posPadre, int[] aux) {
        NodoArbol nodoRetorno = null;
        int i = aux[0];
        if (posPadre > 0) {
            if (i == posPadre) {
                nodoRetorno = nodo;
            } else {
                if (nodo.getIzquierdo() != null) {
                    aux[0] = aux[0] + 1;
                    nodoRetorno = buscarPosPreorden(nodo.getIzquierdo(), posPadre, aux);
                }
                if ((nodoRetorno == null) && (nodo.getDerecho() != null)) {
                    aux[0] = aux[0] + 1;
                    nodoRetorno = buscarPosPreorden(nodo.getDerecho(), posPadre, aux);
                }
            }
        }
        return nodoRetorno;
    }

    public Lista listarPreorden() {
        Lista lis = new Lista();
        listarPreordenAux(this.raiz, lis);
        return lis;
    }

    private void listarPreordenAux(NodoArbol nodo, Lista lis) {
        if (nodo != null) {
            lis.insertar(nodo.getElem(), lis.longitud() + 1);
            listarPreordenAux(nodo.getIzquierdo(), lis);
            listarPreordenAux(nodo.getDerecho(), lis);
        }
    }

    public Lista listarPosorden() {
        Lista listaPosorden = new Lista();
        listarPosordenAux(this.raiz, listaPosorden);
        return listaPosorden;
    }

    private void listarPosordenAux(NodoArbol nodo, Lista lis) {
        if (nodo != null) {
            listarPosordenAux(nodo.getIzquierdo(), lis);
            listarPosordenAux(nodo.getDerecho(), lis);
            lis.insertar(nodo.getElem(), lis.longitud()+1);
        }
    }

    public Lista listarInorden() {
        Lista listaInorden = new Lista();
        listarInordenAux(this.raiz, listaInorden);
        return listaInorden;
    }

    private void listarInordenAux(NodoArbol nodo, Lista lis) {
        if (nodo != null) {
            listarInordenAux(nodo.getIzquierdo(), lis);
            lis.insertar(nodo.getElem(), lis.longitud()+1);
            listarInordenAux(nodo.getDerecho(), lis);
        }
    }

    public Lista listarPorNiveles() {
        Lista listaPorNiveles = new Lista();
        Cola Q = new Cola();
        NodoArbol nodoActual = null;
        int l = 1;
        if (this.raiz != null) {
            Q.poner(this.raiz);
            while (!Q.esVacia()) {
                nodoActual = (NodoArbol) Q.obtenerFrente();
                Q.sacar();
                listaPorNiveles.insertar(nodoActual, l);
                l++;
                if (nodoActual.getIzquierdo() != null) {
                    Q.poner(nodoActual.getIzquierdo());
                }
                if (nodoActual.getDerecho() != null) {
                    Q.poner(nodoActual.getDerecho());
                }
            }
        }
        return listaPorNiveles;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public NodoArbol padre(Object elemento) {
        NodoArbol nRetorno = null;
        if ((this.raiz != null) && (this.raiz.getElem() != elemento)) {
            boolean[] aux = {false};
            nRetorno = obtenerNodoPadre(this.raiz, elemento, aux);
        }
        return nRetorno;
    }

    private NodoArbol obtenerNodoPadre(NodoArbol n, Object buscado, boolean[] encontrado) {
        NodoArbol nRetorno = null;
        if (n != null) {
            if (n.getElem() == buscado) {
                encontrado[0] = true;
            } else {
                nRetorno = obtenerNodoPadre(n.getIzquierdo(), buscado, encontrado);
                if (nRetorno == null) {
                    if (encontrado[0]) {
                        nRetorno = n;
                    } else {
                        nRetorno = obtenerNodoPadre(n.getDerecho(), buscado, encontrado);
                        if ((nRetorno == null) && (encontrado[0])) {
                            nRetorno = n;
                        }
                    }
                }
            }
        }
        return nRetorno;
    }

    public int altura() {
        int altura;
        if (this.raiz == null) {
            altura = -1;
        } else if ((this.raiz.getIzquierdo() == null) && (this.raiz.getDerecho() == null)) {
            altura = 0;
        } else {
            altura = alturaAux(this.raiz, 0);
        }

        return altura;
    }

    private int alturaAux(NodoArbol nodo, int i) {
        int j = i;
        int aux;
        if (nodo.getIzquierdo() != null) {
            aux = alturaAux(nodo.getIzquierdo(), j + 1);
            if (aux > i) {
                i = aux;
            }
        }
        if (nodo.getDerecho() != null) {
            aux = alturaAux(nodo.getDerecho(), j + 1);
            if (aux > i) {
                i = aux;
            }
        }
        if (i > j) {
            j = i;
        }

        return j;
    }

    public int nivel(Object elemento) {
        int nivel = 0;
        boolean[] aux = {false};
        if (this.raiz != null) {
            nivel = nivelAux(this.raiz, nivel, elemento, aux);
        } 
        if ((this.raiz == null) || (!aux[0])) {
            nivel = -1;
        }
        return nivel;
    }

    private int nivelAux(NodoArbol n, int i, Object buscado, boolean[] encontrado) {
        int j = i;
        if (n != null) {
            if (n.getElem() == buscado) {
                encontrado[0] = true;
            } else {
                i = nivelAux(n.getIzquierdo(), j+1, buscado, encontrado);
                if (!encontrado[0]) {
                    i = nivelAux(n.getDerecho(), j+1, buscado, encontrado);
                    if (encontrado[0]) {
                        j = i;
                    }
                } else {
                    j = i;
                }
            }
        }
        return j;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public ArbolBin clone() {
        ArbolBin arbolClon = new ArbolBin();
        if (this.raiz != null) {
            arbolClon.raiz = cloneAux(this.raiz);
        }

        return arbolClon;
    }

    private NodoArbol cloneAux(NodoArbol nOg) {
        NodoArbol nodoAux = null;
        if (nOg != null) {
            nodoAux = new NodoArbol(nOg.getElem(), cloneAux(nOg.getIzquierdo()), cloneAux(nOg.getDerecho()));
        }
        return nodoAux;
    }

    @Override
    public String toString() {
        String s = "";
        if (this.raiz == null) {
            s = "[Arbol Binario vacío.]";
        } else {
            s += "Raiz: " + this.raiz.getElem() + "\n";
            s += toStringAux(this.raiz);
        }

        return s;
    }

    private String toStringAux(NodoArbol nodo) {
        String s = "";
        boolean tieneIzq = nodo.getIzquierdo() != null;
        boolean tieneDer = nodo.getDerecho() != null;
        s += nodo.getElem() + ": ";
        if (tieneIzq) {
            s += "HI: " + nodo.getIzquierdo().getElem() + " // ";
        } else {
            s += "HI: null // ";
        }
        if (tieneDer) {
            s += "HD: " + nodo.getDerecho().getElem() + "\n";
        } else {
            s += "HD: null \n";
        }
        if (tieneIzq) {
            s += toStringAux(nodo.getIzquierdo());
        }
        if (tieneDer) {
            s += toStringAux(nodo.getDerecho());
        }

        return s;
    }
}
