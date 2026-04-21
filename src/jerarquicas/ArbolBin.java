package jerarquicas;

import lineales.dinamicas.Lista;

/**
 * esVacio
 * padre
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
            this.raiz = new NodoArbol(elemNuevo, null, null);
        } else {
            Lista lis = this.listarPreorden();
            NodoArbol nPadre;
            if (posPadre>0 && posPadre<=lis.longitud()) {
                nPadre = buscarPosPreorden(this.raiz, 1, posPadre);
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

    private NodoArbol buscarPosPreorden(NodoArbol nodo, int i, int posPadre) {
        NodoArbol nodoRetorno = null;
        if (i == posPadre) {
            nodoRetorno = nodo;
        } 
        if (i < posPadre) {
            if (nodo.getIzquierdo() != null) {
                nodoRetorno = buscarPosPreorden(nodo.getIzquierdo(), i+1, posPadre);
            }
            if (nodoRetorno == null) {
                if (nodo.getDerecho() != null) {
                    nodoRetorno = buscarPosPreorden(nodo.getDerecho(), i+1, posPadre);
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

    @Override
    public String toString() {
        String s = "";
        Lista lisPreorden = new Lista();
        listarPreordenAux(this.raiz, lisPreorden);
        Object nodoAux;
        if (lisPreorden.longitud() == 0) {
            s = "[Arbol Binario vacío.]";
        } else {
            int i = lisPreorden.longitud();
            while (i > 0) {
                nodoAux = lisPreorden.recuperar(1);
                lisPreorden.eliminar(1);
                i--;
                s += nodoAux.toString();
                if (i != 0) {
                    s += "; ";
                }
            }
        }

        return s;
    }
}
