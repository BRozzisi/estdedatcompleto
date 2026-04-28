package jerarquicas;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;

/**
 * La clase ArbolBin es una estructura de datos jerárquica en la que se guarda
 * un {@link NodoArbol} inicial llamado
 * raíz, el cual tiene dos enlaces hacía otros nodos.
 * 
 * @param raiz - {@link NodoArbol} para acceder al arbol.
 * @version 1.00
 * @author @BRozzisi
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

    /**
     * Dado un elemento y un nodo, revisa si el nodo tiene almacenado el elemento
     * dado, caso contrario, llama
     * recursivamente con su hijo izquierdo, en caso de no encontrarlo ahi, llama
     * recursivamente con su hijo derecho. <br>
     * Metodo privado utilizado por: insertar(Object, Object, boolean)
     * 
     * @param n       {@link NodoArbol} que está utilizando el actual llamado
     *                recursivo.
     * @param buscado {@link Object} que estamos buscando.
     * @return {@link NodoArbol} con el objeto dado (primera aparición). null si no
     *         lo encontró.
     */
    private NodoArbol obtenerNodo(NodoArbol n, Object buscado) {
        NodoArbol resultado = null;
        if (n != null) {
            // Si n no es nulo, pregunta si tiene almacenado el elemento buscado.
            if (n.getElem().equals(buscado)) {
                // Si lo tiene lo guarda en resultado y lo retorna.
                resultado = n;
            } else {
                // Si no lo tiene le pregunta al hizo izquierdo.
                resultado = obtenerNodo(n.getIzquierdo(), buscado);
                if (resultado == null) {
                    // Si el hijo izquierdo devolvió null (no lo tenía), le pregunta al hijo
                    // derecho.
                    resultado = obtenerNodo(n.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

    /**
     * Permite ingresar un elemento indicando la posición del padre en recorrido
     * preorden del árbol.
     * 
     * @param elemNuevo {@link Object} elemento que queremos almacenar en el nuevo
     *                  nodo.
     * @param posPadre  {@link Integer} que indica la posición del nodo que queremos
     *                  que sea padre en un recorrido
     *                  preorden
     * @param posHijo   {@link Boolean} indica si el hijo es izquierdo o derecho,
     *                  con valores lógicos true o false,
     *                  respectivamente
     * @return true si 0 <= posPadre <= longitudArbol
     */
    public boolean insertarPorPosicion(Object elemNuevo, int posPadre, boolean posHijo) {
        boolean exito = true;
        if (this.raiz == null) {
            // Si el árbol todavia esta vacío, pregunta si posPadre = 0
            if (posPadre == 0) {
                // Si posPadre == 0 entonces el elemento ingresado se almacena en un NodoArbol y
                // se establece como raíz.
                this.raiz = new NodoArbol(elemNuevo, null, null);
            } else {
                // Si posPadre != 0 entonces devuelve false.
                exito = false;
            }
        } else {
            // Si el árbol ya tenía al menos un elemento, busca al NodoArbol padre según su
            // posición en preorden.
            NodoArbol nPadre;
            int[] aux = { 1 };
            nPadre = buscarPosPreorden(this.raiz, posPadre, aux);
            if (nPadre != null) {
                // Si encontró dicho nodo padre, analiza las posibilidades de donde debe ser
                // almacenado el nuevo nodo.
                if (posHijo && nPadre.getIzquierdo() == null) {
                    // Si la posición indicada fue la izquierda y el nodo padre tiene el HI vacío,
                    // se almacena.
                    nPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else if (!posHijo && nPadre.getDerecho() == null) {
                    // Si la posición indicada fue la derecha y el nodo padre tiene el HD vacío, se
                    // almacena.
                    nPadre.setDerecho(new NodoArbol(elemNuevo, null, null));
                } else {
                    // Si la posición indicada estaba ocupada retorna false.
                    exito = false;
                }
            } else {
                // Si no se encontró al nodo padre (se ingresó una posición inválida) retorna
                // false.
                exito = false;
            }
        }
        return exito;
    }

    /**
     * Dado un número, devuelve el nodo almacenado en esa posición en el recorrido
     * preorden del árbol.
     * 
     * @param nodo     {@link NodoArbol} actual en el llamado recursivo.
     * @param posPadre {@link Integer} posicion del nodo que queremos buscar en
     *                 recorrido preorden.
     * @param aux      arreglo de {@link Integer} de tamaño 1 utilizado para tener
     *                 "2 retornos" del llamado recursivo.
     * @return {@link NodoArbol} en la posición posPadre en recorrido preorden.
     */
    private NodoArbol buscarPosPreorden(NodoArbol nodo, int posPadre, int[] aux) {
        NodoArbol nodoRetorno = null;
        int i = aux[0];
        if (posPadre > 0) {
            // Si posPadre > 0 entonces empieza la recursión.
            if (i == posPadre) {
                // Si el contador i == posPadre, significa que nodo es el que está en la
                // posición deseada, por lo tanto
                // lo retorna.
                nodoRetorno = nodo;
            } else {
                // Si i != posPadre entonces pregunta en el HI.
                if (nodo.getIzquierdo() != null) {
                    // Si tiene HI, suma 1 al contador global y llama recursivamente con el HI.
                    aux[0] = aux[0] + 1;
                    nodoRetorno = buscarPosPreorden(nodo.getIzquierdo(), posPadre, aux);
                }
                if ((nodoRetorno == null) && (nodo.getDerecho() != null)) {
                    // Si no lo encontró en el HI, y tiene HD, suma 1 al contador global y llama
                    // recursivamente con el HD.
                    aux[0] = aux[0] + 1;
                    nodoRetorno = buscarPosPreorden(nodo.getDerecho(), posPadre, aux);
                }
            }
        }
        return nodoRetorno;
    }

    /**
     * {@return {@link Lista} con los elementos en recorrido preorden del árbol}
     */
    public Lista listarPreorden() {
        Lista lis = new Lista();
        listarPreordenAux(this.raiz, lis);
        return lis;
    }

    /**
     * Método recursivo que dado un nodo y una lista, pone el objeto del nodo en la
     * lista, luego llama recursivamente
     * con su HI, seguido de su HD. <br>
     * Método auxiliar utilizado por: listarPreorden()
     * 
     * @param nodo {@link NodoArbol} actual del llamado recursivo
     * @param lis  {@link Lista} en la que almacenamos todos los elementos.
     */
    private void listarPreordenAux(NodoArbol nodo, Lista lis) {
        if (nodo != null) {
            // Pone el elemento en la lista y llama a sus hijos solo si el nodo actual no es
            // nulo.
            lis.insertar(nodo.getElem(), lis.longitud() + 1);
            listarPreordenAux(nodo.getIzquierdo(), lis);
            listarPreordenAux(nodo.getDerecho(), lis);
        }
    }

    /**
     * {@return {@link Lista} con los elementos en recorrido posorden del árbol}
     */
    public Lista listarPosorden() {
        Lista listaPosorden = new Lista();
        listarPosordenAux(this.raiz, listaPosorden);
        return listaPosorden;
    }

    /**
     * Método recursivo que dado un nodo y una lista, llama recursivamente con su
     * HI, luego con su HD, y seguidamente
     * pone el elemento almacenado en el nodo en la lista.
     * Método auxiliar utilizado por: listarPosorden()
     * 
     * @param nodo {@link NodoArbol} actual del llamado recursivo
     * @param lis  {@link Lista} en la que almacenamos todos los elementos.
     */
    private void listarPosordenAux(NodoArbol nodo, Lista lis) {
        if (nodo != null) {
            // Pone el elemento en la lista y llama a sus hijos solo si el nodo actual no es
            // nulo.
            listarPosordenAux(nodo.getIzquierdo(), lis);
            listarPosordenAux(nodo.getDerecho(), lis);
            lis.insertar(nodo.getElem(), lis.longitud() + 1);
        }
    }

    /**
     * {@return {@link Lista} con los elementos en recorrido inorden del árbol}
     */
    public Lista listarInorden() {
        Lista listaInorden = new Lista();
        listarInordenAux(this.raiz, listaInorden);
        return listaInorden;
    }

    /**
     * Método recursivo que dado un nodo y una lista, llama recursivamente con su
     * HI, luego pone el elemento almacenado
     * en el nodo en la lista, y luego llama recursivamente con su HD
     * Método auxiliar utilizado por: listarInorden()
     * 
     * @param nodo {@link NodoArbol} actual del llamado recursivo
     * @param lis  {@link Lista} en la que almacenamos todos los elementos.
     */
    private void listarInordenAux(NodoArbol nodo, Lista lis) {
        if (nodo != null) {
            listarInordenAux(nodo.getIzquierdo(), lis);
            lis.insertar(nodo.getElem(), lis.longitud() + 1);
            listarInordenAux(nodo.getDerecho(), lis);
        }
    }

    /**
     * {@return {@link Lista} con los elementos del árbol en recorrido por niveles.}
     */
    public Lista listarPorNiveles() {
        Lista listaPorNiveles = new Lista();
        Cola Q = new Cola();
        NodoArbol nodoActual = null;
        int l = 1;
        if (this.raiz != null) {
            // Si el árbol no está vacío, pone la raíz en la cola auxiliar y luego en la
            // Lista, eliminandola de la cola
            // y añadiendo a sus hijos. El procedimiento se repite con los elementos en el
            // orden que aparecen en la cola
            // auxiliar hasta que la cola no tenga elementos.
            Q.poner(this.raiz);
            while (!Q.esVacia()) {
                nodoActual = (NodoArbol) Q.obtenerFrente();
                Q.sacar();
                listaPorNiveles.insertar(nodoActual.getElem(), l);
                l++;
                if (nodoActual.getIzquierdo() != null) {
                    // Si el nodo en el frente de la cola tiene HI, lo pone al final de la cola.
                    Q.poner(nodoActual.getIzquierdo());
                }
                if (nodoActual.getDerecho() != null) {
                    // Si el nodo en el frente de la cola tiene HD, lo pone al final de la cola.
                    Q.poner(nodoActual.getDerecho());
                }
            }
        }
        return listaPorNiveles;
    }

    /**
     * {@returns true si el árbol no tiene elementos}
     */
    public boolean esVacio() {
        return this.raiz == null;
    }

    /**
     * Dado un objeto, devuelve el nodo padre de la primera aparicion de un nodo con
     * ese objeto.
     * 
     * @param elemento {@link Object} que buscamos
     * @return {@link NodoArbol} padre del nodo con el elemento dado.
     */
    public NodoArbol padre(Object elemento) {
        NodoArbol nRetorno = null;
        if ((this.raiz != null) && (this.raiz.getElem() != elemento)) {
            // Si la raiíz no es nula (árbol no vacío) y el elemento no es el almacenado en
            // la raíz (la raíz no tiene
            // padre) llama al método auxiliar que devuelve el nodo padre. Caso contrario
            // devuelve null.
            boolean[] aux = { false };
            nRetorno = obtenerNodoPadre(this.raiz, elemento, aux);
        }
        return nRetorno;
    }

    /**
     * Método recursivo que encuentra el padre de la primera aparición de un nodo
     * con el elemento almacenado buscado. <br>
     * Método auxiliar privado utilizado por: padre()
     * 
     * @param n          {@link NodoArbol} actual del llamado recursivo
     * @param buscado    {@link Object} al cual le queremos encontrar el nodo padre.
     * @param encontrado arreglo de {@link Boolean} que permite "obtener 2 salidas"
     *                   del llamado recursivo. Indicará true
     *                   si el elemento que buscamos ya fue encontrado.
     * @return {@link NodoArbol} padre del nodo con el elemento ingresado.
     */
    private NodoArbol obtenerNodoPadre(NodoArbol n, Object buscado, boolean[] encontrado) {
        NodoArbol nRetorno = null;
        if (n != null) {
            // Revisa que el nodo actual no sea nulo, caso contrario retorna null.
            if (n.getElem() == buscado) {
                // Si el elemento en el nodo actual es igual al buscado, entonces la variable
                // del arreglo auxiliar
                // cambia a true, y retorna null. Esta dupla (null, true) indica que el nodo que
                // llamó
                // recursivamente al actual, es el padre.
                encontrado[0] = true;
            } else {
                // Si el elemento del nodo actual no es el buscado, entonces llama
                // recursivamente con su HI.
                nRetorno = obtenerNodoPadre(n.getIzquierdo(), buscado, encontrado);
                if (nRetorno == null) {
                    // Si el retorno del HI es null:
                    if (encontrado[0]) {
                        // Y la variable del arreglo auxiliar es true, retorna el nodo actual. dupla
                        // (null, true)
                        nRetorno = n;
                    } else {
                        // Si el retorno es null pero tampoco lo encontro, llama recursivamente con su
                        // HD.
                        nRetorno = obtenerNodoPadre(n.getDerecho(), buscado, encontrado);
                        if ((nRetorno == null) && (encontrado[0])) {
                            // Si el retorno del HD es null pero el arreglo auxiliar muestra true (dupla
                            // (null, true))
                            // entonces el nodo actual es el padre, y lo retorna.
                            nRetorno = n;
                        }
                    }
                }
            }
        }
        return nRetorno;
    }

    /**
     * Altura del árbol: Mayor cantidad de enlaces entre la raíz y una hoja del
     * árbol.
     * 
     * @return {@link Integer} que indica la altura del árbol.
     */
    public int altura() {
        int altura;
        if (this.raiz == null) {
            // Si el arbol está vacío retorna -1
            altura = -1;
        } else if ((this.raiz.getIzquierdo() == null) && (this.raiz.getDerecho() == null)) {
            // Si el arbol está compuesto únicamente por su raíz retorna 0.
            altura = 0;
        } else {
            // Si el árbol tiene mas de un elemento entonces retorna el retorno del método
            // recursivo auxiliar.
            altura = alturaAux(this.raiz, 0);
        }

        return altura;
    }

    /**
     * Método recursivo que encuentra el camino mas largo entre la raíz y cualquier
     * hoja del árbol. <br>
     * Método auxiliar privado utilizado por: altura()
     * 
     * @param nodo {@link NodoArbol} indica el nodo actual en el llamado recursivo.
     * @param i    {@link Integer} que lleva el contador de la rama actual
     * @return {@link Integer} que indica la altura del arbol.
     */
    private int alturaAux(NodoArbol nodo, int i) {
        int j = i;
        int aux;
        if (nodo.getIzquierdo() != null) {
            // Si el nodo actual tiene HI, llama recursivamente con el HI y con el contador
            // actual + 1, y lo guarda en
            // el auxiliar
            aux = alturaAux(nodo.getIzquierdo(), j + 1);
            if (aux > i) {
                // Si el auxiliar es mayor al contador global, lo cambia.
                i = aux;
            }
        }
        if (nodo.getDerecho() != null) {
            // Si el nodo actual tiene HD, llama recursivamente con el HD y con el contador
            // actual + 1 y lo guarda en
            // el auxiliar
            aux = alturaAux(nodo.getDerecho(), j + 1);
            if (aux > i) {
                // Si el auxiliar es mayor al contador global, lo cambia.
                i = aux;
            }
        }
        if (i > j) {
            // Si el contador global es mayor que el contador actual, lo cambia.
            j = i;
        }

        return j;
    }

    /**
     * Nivel de un Nodo: Cantidad de enlaces para llegar desde la raíz hasta el nodo
     * 
     * @param elemento {@link Object} que tiene almacenado el nodo deseado.
     * @return {@link Integer} que indica el nivel del nodo ingresado.
     */
    public int nivel(Object elemento) {
        int nivel = 0;
        boolean[] aux = { false };
        if (this.raiz != null) {
            // Si el árbol no esta vacío, retorna el retorno del método auxiliar recursivo
            nivel = nivelAux(this.raiz, nivel, elemento, aux);
        }
        if ((this.raiz == null) || (!aux[0])) {
            // Si el árbol está vacío Ó el arreglo auxiliar no fue modificado por el método
            // auxiliar recursivo (no se
            // encontró el elemento dado), retorna -1.
            nivel = -1;
        }
        return nivel;
    }

    /**
     * Método recursivo que devuelve el nivel de un nodo que almacena un objeto
     * dado.
     * Método auxiliar privado utilizado por: nivel()
     * 
     * @param n          {@link NodoArbol} actual en el llamado recursivo.
     * @param i          {@link Integer} que indica el contador de niveles en el
     *                   llamado recursivo.
     * @param buscado    {@link Object} que almacena el nodo deseado.
     * @param encontrado arreglo de {@link Boolean} que indica globalmente true si
     *                   el elemento fue encontrado
     * @return {@link Integer} que indica el nivel del nodo.
     */
    private int nivelAux(NodoArbol n, int i, Object buscado, boolean[] encontrado) {
        int j = i;
        if (n != null) {
            // Revisa que el nodo llamado recursivamente no sea nulo.
            if (n.getElem() == buscado) {
                // Si el nodo actual tiene el elemento buscado, cambia el arreglo auxiliar a
                // true.
                encontrado[0] = true;
            } else {
                // Si el nodo actual no tiene el elemento buscado, entonces el contador se
                // convierte en el retorno del
                // llamado recursivo con el HI y con el contador actual + 1
                i = nivelAux(n.getIzquierdo(), j + 1, buscado, encontrado);
                if (!encontrado[0]) {
                    // Si no lo encontró en el HI, repite el llamado recursivo en el HD.
                    i = nivelAux(n.getDerecho(), j + 1, buscado, encontrado);
                    if (encontrado[0]) {
                        // Si lo encontró en el HD, el contador actual se cambia por el retorno del
                        // llamado con HD.
                        j = i;
                    }
                } else {
                    // Si lo encontró en el HI, el contador actual se cambia por el retorno del
                    // llamado con HD.
                    j = i;
                }
            }
        }
        return j;
    }

    /**
     * Elimina todos los elementos almacenados en el árbol.
     */
    public void vaciar() {
        this.raiz = null;
    }

    /**
     * {@return un {@link ArbolBin} con las mismas características}
     */
    public ArbolBin clone() {
        ArbolBin arbolClon = new ArbolBin();
        if (this.raiz != null) {
            // Si el árbol no está vacío, le asigna a la raíz del árbol de retorno, el nodo
            // que devuelve el método
            // auxiliar, que tiene todos los enlaces idénticos al árbol original.
            arbolClon.raiz = cloneAux(this.raiz);
        }

        return arbolClon;
    }

    /**
     * Método recursivo que dado un nodo, retorna otro con su mismo elemento, de HI
     * el llamado recursivo con el HI del
     * nodo dado como parámetro, y de HD el llamado recursivo con el HD del nodo
     * dado como parámetro
     * Método auxiliar privado utilizado por: clone()
     * 
     * @param nOg {@link NodoArbol} nodo del árbol original dado como parámetro
     * @return {@link NodoArbol} idéntico a {@link @param nOg} pero almaecenado en
     *         otro espacio de memoria.
     */
    private NodoArbol cloneAux(NodoArbol nOg) {
        NodoArbol nodoAux = null;
        if (nOg != null) {
            // Si el nodo dado como parámetro no es nulo, devuelve un nuevo nodo cuyo
            // elemento es el mismo que el del
            // parámetro, su HI es el llamado recursivo con el HI del del parámetro y su HD
            // es el llamado recursivo con
            // el HD del del parámetro. Caso contrario retornará null.
            nodoAux = new NodoArbol(nOg.getElem(), cloneAux(nOg.getIzquierdo()), cloneAux(nOg.getDerecho()));
        }
        return nodoAux;
    }

    /**
     * {@return representación textual en {@link String} del árbol, mostrando su
     * raíz, seguido de todos los nodos con
     * sus respectivos hijos}
     */
    @Override
    public String toString() {
        String s = "";
        if (this.raiz == null) {
            // Si el arbol esta vacío, devuelve:
            s = "[Arbol Binario vacío.]";
        } else {
            // Si el árbol no está vacío, genera al cadena con la raíz, y le suma el retorno
            // del llamado al método
            // auxiliar que recorre todo el árbol.
            s += "Raiz: " + this.raiz.getElem() + "\n";
            s += toStringAux(this.raiz);
        }

        return s;
    }

    /**
     * Dado un nodo añade a la cadena su elemento, seguido de sus HI y HD. Luego
     * llama recursivamente con su HI,
     * seguido de su HD, solo en caso de tener, para ambos.
     * 
     * @param nodo {@link NodoArbol} que indica el nodo actual del llamado recursivo
     * @return {@link String} que representa su elemento, junto con la de todos los
     *         nodos que están hacía abajo en el
     *         árbol.
     */
    private String toStringAux(NodoArbol nodo) {
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
            s += "HD: null \n";
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

    /**************************************************************************************************************** 
     * MÉTODOS PARA SIMULACRO DEL PRIMER PARCIAL
     ****************************************************************************************************************/
    public boolean verificarPatron(Lista patron) {
        boolean esPatron = true;
        Lista patronAux = patron.clone();
        NodoArbol nodoAux = this.raiz;
        int i = 1;
        while ((esPatron) && (nodoAux != null)) {
            Object aux = patron.recuperar(i);
            if (aux == nodoAux.getElem()) {

            }
        }
        return esPatron;
    }
}
