package conjuntistas;

import lineales.dinamicas.Lista;

public class ArbolAVL {
    private NodoAVL raiz;

    public ArbolAVL() {
        // Constructor vacio
        this.raiz = null;
    }

    public boolean pertenece(Comparable elem) {
        boolean pertenece = false;
        int comparacion;
        NodoAVL nodoAux = this.raiz;
        while ((nodoAux != null) && (!pertenece)) {
            // Recorre el arbol hasta que llega al final o hasta que encuentra elem
            comparacion = elem.compareTo(nodoAux.getElem());
            if (comparacion < 0) {
                // Si elem es menor al del nodo actual, se mueve al HI
                nodoAux = nodoAux.getIzquierdo();
            } else if (comparacion > 0) {
                // Si elem es mayor al del nodo actual, se mueve al HD
                nodoAux = nodoAux.getDerecho();
            } else {
                // Si elem es igual al del nodo actual, lo encontró
                pertenece = true;
            }
        }
        return pertenece;
    }

    public boolean insertar(Comparable elemento) {
        boolean exito = true;
        if (this.raiz == null) {
            // Si la raiz es nula, entonces asigna el elemento en la raiz
            this.raiz = new NodoAVL(elemento);
        } else {
            // Si la raiz no es nula retorna el resultado del metodo auxiliar
            exito = insertarAux(this.raiz, null, elemento, false);
        }
        return exito;
    }

    private boolean insertarAux(NodoAVL n, NodoAVL padre, Comparable elemento, boolean esHijoIzq) {
        boolean exito = true;
        if ((elemento.compareTo(n.getElem())) == 0) {
            /*
             * Si el elemento es igual al del nodo actual, entonces retorna false ya que no
             * puede haber 2 elementos iguales en esta implementacion del AVL
             */
            exito = false;
        } else if (elemento.compareTo(n.getElem()) < 0) {
            // Si el elemento es mas chico que el del nodo actual, pregunta:
            if (n.getIzquierdo() != null) {
                /*
                 * Si el nodo actual ya tiene HI entonces llama recursivamente con el para que
                 * lo inserte en su subarbol
                 * izquierdo.
                 */
                exito = insertarAux(n.getIzquierdo(), n, elemento, true);
            } else {
                // Si el nodo actual no tiene HI entonces ingresa el elemento ahi.
                n.setIzquierdo(new NodoAVL(elemento));
            }
        } else {
            // Si el elemento es mas grande que el del nodo actual, pregunta:
            if (n.getDerecho() != null) {
                /*
                 * Si el nodo actual tiene HD entonces llama recursivamente con el para que lo
                 * inserte en su subarbol
                 * derecho
                 */
                exito = insertarAux(n.getDerecho(), n, elemento, false);
            } else {
                // Si el nodo actual no tiene HD entonces inserta elemento ahi
                n.setDerecho(new NodoAVL(elemento));
            }
        }
        if (exito) {
            // Luego de la inserción, se recalcula la altura de los nodos a la vuelta de la
            // recursión.
            n.recalcularAltura();
            // Tambien se calcula el balance de cada nodo con la nueva altura.
            revisarBalance(n, padre, n.calcularBalance(), esHijoIzq);
        }
        // Retorna false solo si el elemento ingresado ya estaba en el arbol
        return exito;
    }

    public boolean eliminar(Comparable elemento) {
        boolean exito = false;
        if (this.raiz != null) {
            exito = eliminarAux(this.raiz, null, elemento, false);
        }
        return exito;
    }

    private boolean eliminarAux(NodoAVL n, NodoAVL padre, Comparable elemento, boolean esHijoIzq) {
        boolean exito = false;
        if (n != null) {
            int comparacion = elemento.compareTo(n.getElem());
            if (comparacion < 0) {
                exito = eliminarAux(n.getIzquierdo(), n, elemento, true);
            } else if (comparacion > 0) {
                exito = eliminarAux(n.getDerecho(), n, elemento, false);
            } else {
                // Si encuentra el elemento, retorna true, e identifica el caso de eliminacion
                // de los 3 siguientes:
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
            }
            if (exito) {
                // Si se realizo la eliminacion se recalcula altura y luego se verifica el
                // balance
                n.recalcularAltura();
                revisarBalance(n, padre, n.calcularBalance(), esHijoIzq);
            }
        }
        return exito;
    }

    private void revisarBalance(NodoAVL n, NodoAVL padre, int balance, boolean esHijoIzq) {
        if ((balance <= -2) || (balance >= 2)) {
            // Si el arbol esta desbalanceado se realiza la rotacion correspondiente
            if (padre == null) {
                // Si el padre es nulo significa que el nodo desbalanceado es la raiz
                this.raiz = realizarRotacion(n, balance);
            } else {
                /*
                 * esHijoIzq define de que lado como hijo estaba el nodo desbalanceado, para
                 * poder asignarle al padre la
                 * raiz del nuevo subarbol balanceado del lado que le corresponda
                 */
                if (esHijoIzq) {
                    padre.setIzquierdo(realizarRotacion(n, balance));
                } else {
                    padre.setDerecho(realizarRotacion(n, balance));
                }
            }
        }
    }

    private void casoSinHijos(NodoAVL padre, boolean esHijoIzq) {
        if (padre == null) {
            /*
             * Si el nodo donde se encontro el elemento no tiene padre, significa que es la
             * raiz, por lo tanto se pone
             * nula y se vacia el arbol
             */
            this.raiz = null;
        } else {
            /*
             * Si el nodo donde se encontro el elemento si tiene padre, se fija si es el HI
             * o HD de su padre y lo
             * elimina
             */
            if (esHijoIzq) {
                padre.setIzquierdo(null);
            } else {
                padre.setDerecho(null);
            }
        }
    }

    private void casoDosHijos(NodoAVL padre) {
        // CasoDosHijosAux realiza la eliminacion del candidato, moviendolo a la
        // posicion del nodo desbalanceado
        padre.setElem(casoDosHijosAux(padre.getIzquierdo(), padre, true));
    }

    private Object casoDosHijosAux(NodoAVL candidato, NodoAVL padreCandidato, boolean esHijoIzq) {
        Object retorno = null;
        if (candidato.getDerecho() != null) {
            /* Si el candidato actual tiene HD, ese pasa a ser el nuevo candidato */
            retorno = casoDosHijosAux(candidato.getDerecho(), candidato, false);
        } else {
            /*
             * Si el candidato actual no tiene HD, se retorna el elemento del candidato, y
             * se elimina el mismo
             */
            retorno = candidato.getElem();
            if (esHijoIzq) {
                padreCandidato.setIzquierdo(candidato.getIzquierdo());
            } else {
                padreCandidato.setDerecho(candidato.getIzquierdo());
            }
        }

        /*
         * A la vuelta de la recursion se recalcula la altura de los nodos modificados y
         * se verifica el balance
         */
        padreCandidato.recalcularAltura();
        revisarBalance(candidato, padreCandidato, candidato.calcularBalance(), esHijoIzq);

        return retorno;
    }

    private void casoUnHijo(NodoAVL n, NodoAVL padre, boolean esHijoIzq) {
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

    /**
     * Decide cual de las 4 rotaciones corresponde realizarse, segun el balance del
     * nodo y el ed su hijo.
     */
    private NodoAVL realizarRotacion(NodoAVL n, int balance) {
        /* Ignoren los comentarios de sout, son para testear, dsp se sacan */
        NodoAVL nodoRetorno = null;
        if (balance > 0) {
            int balanceIzquierdo;
            if (n.getIzquierdo() != null) {
                balanceIzquierdo = n.getIzquierdo().calcularBalance();
            } else {
                balanceIzquierdo = -1;
            }
            if ((balanceIzquierdo == 1) || (balanceIzquierdo == 0)) {
                // System.out.println("Se realiza una rotacion simple a derecha con pivote " + n.getElem());
                nodoRetorno = rotacionSimpleDer(n);
            } else if (balanceIzquierdo < 0) {
                // System.out.println("Se realiza una rotacion doble der-izq con pivote " + n.getElem());
                nodoRetorno = rotacionDobleIzqDer(n);
            }
        } else if (balance < 0) {
            int balanceDerecho;
            if (n.getDerecho() != null) {
                balanceDerecho = n.getDerecho().calcularBalance();
            } else {
                balanceDerecho = -1;
            }
            if ((balanceDerecho == -1) || (balanceDerecho == 0)) {
                // System.out.println("Se realiza una rotacion simple a izquierda con pivote " + n.getElem());
                nodoRetorno = rotacionSimpleIzq(n);
            } else if (balanceDerecho > 0) {
                // System.out.println("Se realiza una rotacion doble izq-der con pivote " + n.getElem());
                nodoRetorno = rotacionDobleDerIzq(n);
            }
        }
        // Retorna la nueva raiz del subarbol que debe ser ubicada como hijo del padre
        // de n en la recursion de insertarAux(...)
        return nodoRetorno;
    }

    private NodoAVL rotacionSimpleIzq(NodoAVL r) {
        NodoAVL h = r.getDerecho();
        NodoAVL temp = h.getIzquierdo();
        h.setIzquierdo(r);
        r.setDerecho(temp);

        r.recalcularAltura();
        h.recalcularAltura();

        return h;
    }

    private NodoAVL rotacionSimpleDer(NodoAVL r) {
        NodoAVL h = r.getIzquierdo();
        NodoAVL temp = h.getDerecho();
        h.setDerecho(r);
        r.setIzquierdo(temp);

        r.recalcularAltura();
        h.recalcularAltura();

        return h;
    }

    private NodoAVL rotacionDobleDerIzq(NodoAVL r) {
        r.setDerecho(rotacionSimpleDer(r.getDerecho()));
        NodoAVL h = rotacionSimpleIzq(r);
        return h;
    }

    private NodoAVL rotacionDobleIzqDer(NodoAVL r) {
        r.setIzquierdo(rotacionSimpleIzq(r.getIzquierdo()));
        NodoAVL h = rotacionSimpleDer(r);
        return h;
    }

    public Lista listar() {
        Lista lista = new Lista();
        listarAux(this.raiz, lista);
        return lista;
    }

    private void listarAux(NodoAVL n, Lista l) {
        if (n != null) {
            listarAux(n.getDerecho(), l);
            l.insertar(n.getElem(), 1);
            listarAux(n.getIzquierdo(), l);
        }
    }

    public Lista listarRango(Comparable topeMenor, Comparable topeMayor) {
        Lista lista = new Lista();
        int comparacion = topeMenor.compareTo(topeMayor);
        if (comparacion <= 0) {
            listarRangoAux(this.raiz, topeMenor, topeMayor, lista);
        } else {
            listarRangoAux(this.raiz, topeMayor, topeMenor, lista);
        }
        return lista;
    }

    private void listarRangoAux(NodoAVL n, Comparable topeMenor, Comparable topeMayor, Lista l) {
        if (n != null) {
            if ((topeMenor.compareTo(n.getElem())) <= 0) {
                listarRangoAux(n.getIzquierdo(), topeMenor, topeMayor, l);
                if ((topeMayor.compareTo(n.getElem())) >= 0) {
                    l.insertar(n.getElem(), l.longitud() + 1);
                    listarRangoAux(n.getDerecho(), topeMenor, topeMayor, l);
                }
            } else {
                listarRangoAux(n.getDerecho(), topeMenor, topeMayor, l);
            }
        }
    }

    public Object minimoElem() {
        Object elem = null;
        if (this.raiz != null) {
            NodoAVL aux = this.raiz;
            while (aux.getIzquierdo() != null) {
                aux = aux.getIzquierdo();
            }
            elem = aux.getElem();
        }

        return elem;
    }

    public Object maximoElem() {
        Object elem = null;
        if (this.raiz != null) {
            NodoAVL aux = this.raiz;
            while (aux.getDerecho() != null) {
                aux = aux.getDerecho();
            }
            elem = aux.getElem();
        }

        return elem;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public ArbolAVL clone() {
        ArbolAVL clone = new ArbolAVL();
        clone.raiz = this.cloneNodo(this.raiz);
        return clone;
    }

    private NodoAVL cloneNodo(NodoAVL n) {
        NodoAVL clone = null;
        if (n != null) {
            clone = new NodoAVL(n.getElem());
            clone.setIzquierdo(cloneNodo(n.getIzquierdo()));
            clone.setDerecho(cloneNodo(n.getDerecho()));
        }
        return clone;
    }

    @Override
    public String toString() {
        String s = "";
        if (this.raiz == null) {
            // Si el arbol esta vacío, devuelve:
            s = "[arbol vacio]";
        } else {
            // Si el árbol no está vacío, genera al cadena con la raíz, y le suma el retorno
            // del llamado al método
            // auxiliar que recorre todo el árbol.
            s += "Raiz: " + this.raiz.getElem() + "\n";
            s += toStringAux(this.raiz);
        }

        return s;
    }

    private String toStringAux(NodoAVL nodo) {
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
