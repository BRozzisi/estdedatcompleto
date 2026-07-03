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
            // Si la raiz es nula, entonces asigna el elemento en la raiz
            this.raiz = new NodoABB(elemento);
        } else {
            // Si la raiz no es nula retorna el resultado del metodo auxiliar
            exito = insertarAux(this.raiz, elemento);
        }
        return exito;
    }

    private boolean insertarAux(NodoABB n, Comparable elemento) {
        boolean exito = true;
        if ((elemento.compareTo(n.getElem())) == 0) {
            // Si el elemento es igual al del nodo actual, entonces retorna false ya que no
            // puede haber 2 elementos
            // iguales en esta implementacion del ABB
            exito = false;
        } else if (elemento.compareTo(n.getElem()) < 0) {
            // Si el elemento es mas chico que el del nodo actual, pregunta:
            if (n.getIzquierdo() != null) {
                // Si el nodo actual ya tiene HI entonces llama recursivamente con el para que
                // lo inserte en su
                // subarbol izquierdo.
                exito = insertarAux(n.getIzquierdo(), elemento);
            } else {
                // Si el nodo actual no tiene HI entonces ingresa el elemento ahi.
                n.setIzquierdo(new NodoABB(elemento));
            }
        } else {
            // Si el elemento es mas grande que el del nodo actual, pregunta:
            if (n.getDerecho() != null) {
                // Si el nodo actual tiene HD entonces llama recursivamente con el para que lo
                // inserte en su subarbol
                // derecho
                exito = insertarAux(n.getDerecho(), elemento);
            } else {
                // Si el nodo actual no tiene HD entonces inserta elemento ahi
                n.setDerecho(new NodoABB(elemento));
            }
        }
        // Retorna false solo si el elemento ingresado ya estaba en el arbol
        return exito;
    }

    public boolean eliminar(Comparable elemento) {
        boolean exito = false;
        NodoABB n = this.raiz;
        NodoABB padre = null;
        boolean esHijoIzq = false;
        int comparacion = 1;
        while ((n != null) && (comparacion != 0)) {
            // Se baja por la rama correspondiente del arbol segun el valor de elemento
            // hasta que se llegue al final o
            // hasta que encontremos el elemento
            comparacion = elemento.compareTo(n.getElem());
            if (comparacion == 0) {
                // Si el elemento es igual al del nodo actual, procede al proceso de eliminacion
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
                // Si el elemento es menor al del nodo actual, entonces se baja por la rama
                // izquierda
                padre = n;
                n = n.getIzquierdo();
                esHijoIzq = true;
            } else {
                // Si el elemento es mayor al del nodo actual, se baja por la rama derecha
                padre = n;
                n = n.getDerecho();
                esHijoIzq = false;
            }
        }

        return exito;
    }

    private void casoSinHijos(NodoABB padre, boolean esHijoIzq) {
        if (padre == null) {
            // Si el nodo donde se encontro el elemento no tiene padre, significa que es la
            // raiz, por lo tanto se pone
            // nula y se vacia el arbol
            this.raiz = null;
        } else {
            // Si el nodo donde se encontro el elemento si tiene padre, se fija si es el HI
            // o HD de su padre y lo
            // elimina
            if (esHijoIzq) {
                padre.setIzquierdo(null);
            } else {
                padre.setDerecho(null);
            }
        }
    }

    private void casoDosHijos(NodoABB padre) {
        NodoABB padreCandidato = padre;
        NodoABB candidato = padre.getIzquierdo();
        if ((candidato.getDerecho()) != null) {
            // Si el HI del nodo que se va a eliminar tiene HD, se recorre hasta que
            // encontramos el mayor del subarbol
            // izquierdo del nodo a eliminar
            while (candidato.getDerecho() != null) {
                padreCandidato = candidato;
                candidato = candidato.getDerecho();
            }
            padre.setElem(candidato.getElem());
            padreCandidato.setDerecho(candidato.getIzquierdo());
        } else {
            // Si el HI del nodo que se va a eliminar no tiene HD, entonces ese sera su
            // reemplazante, se setean los
            // parametros del padre (que en este caso es el nodo a eliminar) para que sea
            // igual al nodo reemplazante
            padre.setElem(candidato.getElem());
            padre.setIzquierdo(candidato.getIzquierdo());
        }
    }

    private void casoUnHijo(NodoABB n, NodoABB padre, boolean esHijoIzq) {
        if (n.getIzquierdo() != null) {
            // Si el nodo a eliminar solo tiene HI pregunta:
            if (padre == null) {
                // Si el padre es nulo, significa que el nodo a eliminar es la raiz, por lo que
                // se le asigna a la raiz
                // su propio HI.
                this.raiz = this.raiz.getIzquierdo();
            } else {
                // Si el padre no es nulo entonces pregunta:
                if (esHijoIzq) {
                    // Si es HI del padre entonces le asigna su subarbol izquierdo al izquierdo del
                    // padre
                    padre.setIzquierdo(n.getIzquierdo());
                } else {
                    // Si es HD del padre entonces le asigna su subarbol izquierdo al derecho del
                    // padre
                    padre.setDerecho(n.getIzquierdo());
                }
            }
        } else if (n.getDerecho() != null) {
            // El mismo razonamiento del lado izquierdo aplica para el lado derecho.
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
            // Bajamos por la rama correspondiene hasta encontrar el elemento o hasta llegar
            // al final
            comparacion = elemento.compareTo(n.getElem());
            if (comparacion < 0) {
                // Si el elemento es mas chico que el del nodo actual entonces baja por el
                // subarbol izquierdo
                n = n.getIzquierdo();
            } else if (comparacion > 0) {
                // Si el elemento es mas grande que el del nodo actual entonces baja por el
                // subarbol derecho
                n = n.getDerecho();
            } else {
                // Si el elemento es igual al del nodo actual entonces se verifica que pertenece
                // y retorna true.
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
            // Si el nodo actual no es nulo, le pide a su subarbol izquierdo que se liste,
            // luego se lista a si mismo,
            // y luego le pide a su subarbol derecho que se liste
            listarAux(n.getIzquierdo(), l);
            l.insertar(n.getElem(), l.longitud() + 1);
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
            // Si el nodo actual no es nulo pregunta:
            if (elemMin.compareTo(n.getElem()) <= 0) {
                // Si el elemento minimo del rango es menor o igual al del nodo actual, entonces
                // le pide a su subarbol
                // izquierdo que se liste.
                listarRangoAux(n.getIzquierdo(), l, elemMin, elemMax);
                if (elemMax.compareTo(n.getElem()) >= 0) {
                    // Si el elemento maximo del rango es mayor o igual al del nodo actual, se
                    // inserta a si mismo (ya
                    // que por la condicion anterior se verifica que esta dentro del rango) y le
                    // pide a su subarbol
                    // derecho que se liste
                    l.insertar(n.getElem(), l.longitud() + 1);
                    listarRangoAux(n.getDerecho(), l, elemMin, elemMax);
                }
            } else {
                // Si el elemento minimo del rango es mayor al del nodo actual, entonces el
                // actual es muy chico para
                // entrar en el rango por lo que todo su subarbol izquierdo tambien lo sera.
                // Unicamente pide a su
                // subarbol derecho que se liste
                listarRangoAux(n.getDerecho(), l, elemMin, elemMax);
            }
        }
    }

    public Object minimoElem() {
        Object rta = null;
        if (this.raiz != null) {
            NodoABB n = this.raiz;
            // Si el arbol no esta vacio, baja por la rama extrema izquierda hasta llegar al
            // final y retorna el elemento
            // del nodo hoja.
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
            // Si el arbol no esta vacio, baja por la rama extrema derecha hasta llegar al
            // final y retorna el elemento
            // del nodo hoja.
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

    /*************************************************************
    * MÉTODOS DE SIMULACRO DEL SEGUNDO PARCIAL
    *************************************************************/
    public void eliminarMinimo() {
        NodoABB actual = this.raiz;
        if (actual != null) {
            NodoABB menor = actual.getIzquierdo();
            if (menor == null) { 
                this.raiz = this.raiz.getDerecho();
            }
            while (menor != null) {
                if (menor.getIzquierdo() == null) {
                    actual.setIzquierdo(null);
                }
                actual = menor;
                menor = menor.getIzquierdo();
            }
        }
    }

    public ArbolBB clonarParteInvertida(Comparable elem) {
        ArbolBB clonInvertido = new ArbolBB();
        if (this.raiz != null) {
            boolean encontrado = false;
            NodoABB nodoAux = this.raiz;
            while ((!encontrado) && (nodoAux != null)) {
                int comparacion = elem.compareTo(nodoAux.getElem());
                if (comparacion < 0) {
                    nodoAux = nodoAux.getIzquierdo();
                } else if (comparacion > 0) {
                    nodoAux = nodoAux.getDerecho();
                } else {
                    encontrado = true;
                }
            }
            if (encontrado) {
                clonInvertido.raiz = invertirRamas(nodoAux);
            }
        }
        return clonInvertido;
    }

    private NodoABB invertirRamas(NodoABB n) {
        NodoABB ret = null;
        if (n != null) {
            ret = new NodoABB(n.getElem());
            ret.setIzquierdo(invertirRamas(n.getDerecho()));
            ret.setDerecho(invertirRamas(n.getIzquierdo()));
        }
        return ret;
    }

    /*************************************************************
    * SEGUNDO PARCIAL
    *************************************************************/
    public Lista listarMenoresQue(Comparable valor, Comparable elem) {
        Lista lisMenores = new Lista();
        if (this.raiz != null) {
            NodoABB raizSubarbol = this.raiz;
            int comparacion = -1;
            while ((raizSubarbol != null) && (comparacion != 0)) {
                comparacion = elem.compareTo(raizSubarbol.getElem());
                if (comparacion > 0) {
                    raizSubarbol = raizSubarbol.getDerecho();
                } else if (comparacion < 0) {
                    raizSubarbol = raizSubarbol.getIzquierdo();
                }
            }
            listadoMenores(raizSubarbol, valor, lisMenores);
        }
        return lisMenores;
    }

    private void listadoMenores(NodoABB n, Comparable valor, Lista lis) {
        if (n != null) {
            listadoMenores(n.getIzquierdo(), valor, lis);
            if (valor.compareTo(n.getElem()) > 0) {
                lis.insertar(n.getElem(), lis.longitud()+1);
                listadoMenores(n.getDerecho(), valor, lis);
            }
        }
    }
}
