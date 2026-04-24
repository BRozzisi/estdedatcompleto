package jerarquicas;

import lineales.dinamicas.Lista;

/**
 * altura
 * nivel
 * vaciar
 * clonar
 * toString
 * listarPreorden
 * listarPosorden
 * listarInorden
 * listarPorNiveles
 * Otras operaciones dadas en el apunte
 */

public class ArbolBin {
    private NodoArbol raiz;

    public ArbolBin() {
        raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre, boolean posHijo) {
        boolean exito = true;
        if (this.raiz == null) {
            // Si el arbol está vacío pone elemNuevo en la raiz.
            this.raiz = new NodoArbol(elemNuevo, null, null);
        } else {
            // Si el arbol no está vacío busca al padre
            NodoArbol nPadre = obtenerNodo(this.raiz, elemPadre);
            // Si padre existe y lugar posHijo no esta ocupado lo pone, si no da error
            if (nPadre!=null) {
                if ((posHijo)&&(nPadre.getIzquierdo()==null)) {
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
        if (n!=null) {
            if (n.getElem().equals(buscado)) {
                resultado = n;
            } else {
                resultado = obtenerNodo(n.getIzquierdo(),buscado);
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
            int[] aux = {1};
            nPadre = buscarPosPreorden(this.raiz, posPadre, aux);
            if (nPadre != null) {
                if (posHijo && nPadre.getIzquierdo()==null) {
                    nPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else if (!posHijo && nPadre.getDerecho()==null) {
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
            lis.insertar(nodo.getElem(), lis.longitud()+1);
            listarPreordenAux(nodo.getIzquierdo(), lis);
            listarPreordenAux(nodo.getDerecho(), lis);
        }
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    // public NodoArbol padre(Object elemento) {
    //     NodoArbol nActual = this.raiz;
    //     NodoArbol nRetorno = null;
    //     while (nRetorno != null) {
    //         if (nActual.getIzquierdo() != null) {
    //             if(nActual.getIzquierdo().getElem() == elemento) {
    //                 nRetorno = nActual;
    //             }
    //         }
    //         if (nActual.getDerecho() != null) {
    //             if (nActual.getDerecho().getElem() == elemento) {
                    
    //             }
    //         }
    //     }

    //     return nRetorno;
    // }

    public int altura() {
        int altura;
        if (this.raiz == null) {
            altura = -1;
        } else if ((this.raiz.getIzquierdo() == null) && (this.raiz.getDerecho() == null)) {
            altura = 0;
        } else {
            altura = 1;
        }

        return altura;
    }

    private int alturaAux(NodoArbol nodo, int i) {
        int j = i;
        if (nodo.getIzquierdo() != null) {
            i = alturaAux(nodo.getIzquierdo(), j+1);
        }
        
        if (nodo.getDerecho() != null) {
            i = alturaAux(nodo.getDerecho(), j+1);
            
        }
        if (i > j) {
            j = i;
        }
        

        return j;
    }

    @Override
    public String toString() {
        String s = "";
        if (this.raiz == null) {
            s = "[Arbol Binario vacío.]";
        } else {
            s += "Raiz: " + this.raiz.getElem() + "\n";
            NodoArbol nodoAux = this.raiz;
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
