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
            // Si el arbol esta vacío, inserta el elemento dado en la raiz.
            this.raiz = new NodoGen(elem, null, null);
        } else {
            // Si el arbol no esta vacío busca al nodo con el elemento padre dado.
            NodoGen padre = buscarPorElem(this.raiz, elemPadre);
            if (padre != null) {
                // Si se encontro al padre, se le asigna como hijo un nuevo nodo con el elemento
                // dado, y como hermano al
                // hijo actual del padre
                padre.setHijoIzquierdo(new NodoGen(elem, null, padre.getHijoIzquierdo()));
            } else {
                // Si no se encontro al padre retorna false
                exito = false;
            }
        }
        return exito;
    }

    private NodoGen buscarPorElem(NodoGen n, Object elem) {
        NodoGen nBuscado = null;
        if (n != null) {
            // Si el nodo actual no es nulo
            if (n.getElem() == elem) {
                // Si el nodo actual tiene al elemento buscado se retorna el nodo actual
                nBuscado = n;
            } else {
                // Si el nodo actual no tiene al elemento buscado
                NodoGen hijo = n.getHijoIzquierdo();
                if (hijo == null) {
                    // Si el nodo actual no tiene hijos, pregunta al hermano derecho
                    hijo = n.getHermanoDerecho();
                    nBuscado = buscarPorElem(hijo, elem);
                } else {
                    // Si el nodo actual tiene hijos, pregunta a ellos
                    nBuscado = buscarPorElem(hijo, elem);
                    if (nBuscado == null) {
                        // Si no lo encontro en los hijos, pregunta al hermano.
                        hijo = n.getHermanoDerecho();
                        nBuscado = buscarPorElem(hijo, elem);
                    }
                }

            }
        }
        return nBuscado;
    }

    public boolean insertarPorPosicion(Object elem, int posPadre) {
        boolean exito = false;
        if (this.raiz == null) {
            this.raiz = new NodoGen(elem, null, null);
                exito = true;
        } else {
            if (posPadre > 0) {
                int[] i = { 0 };
                NodoGen padre = buscarPorPos(this.raiz, posPadre, i);
                if (padre != null) {
                    // Si se encontro al padre le asigna el elemento como hijo y retorna true.
                    padre.setHijoIzquierdo(new NodoGen(elem, null, padre.getHijoIzquierdo()));
                    exito = true;
                }
            }
        }
        return exito;
    }

    private NodoGen buscarPorPos(NodoGen n, int posPadre, int[] i) {
        NodoGen nBuscado = null;
        if (n != null) {
            // Si el nodo actual no es nulo
            i[0] = i[0] + 1;
            if (i[0] == posPadre) {
                // Si el contador es igual a la posicion deseada retorna el nodo actual
                nBuscado = n;
            } else {
                // Si el contador no es igual recorre en preorden
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
            // Si el nodo actual no es nulo
            if (n.getElem() == elem) {
                // Si el nodo actual tiene al elemento buscado, retorna true
                pertenece = true;
            } else {
                // Si el nodo actual no tiene al elemento buscado, pregunta en preorden
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
        if (n != null) {
            // Si el nodo actual no es nulo
            if (n.getElem() == elem) {
                // Si el nodo actual tiene al elemento buscado retorna true, y lo inserta
                // adelante de la lista
                encontrado = true;
            } else {
                // Si el nodo actual no tiene al elemento buscado pregunta en preorden hasta
                // encontrarlo
                NodoGen hijo = n.getHijoIzquierdo();
                while ((hijo != null) && (!encontrado)) {
                    encontrado = ancestrosAux(hijo, elem, l);
                    hijo = hijo.getHermanoDerecho();
                }
                if (encontrado) {
                    // Si lo encontro se inserta adelante de la lista
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
            // Si el arbol esta vacio retorna -1
            i = -1;
        } else if (this.raiz.getHijoIzquierdo() == null) {
            // Si la raiz no tiene hijos retorna 0
            i = 0;
        } else {
            // Si la raiz tiene hijos retorna al metodo auxiliar
            i = alturaAux(this.raiz, 0);
        }
        return i;
    }

    private int alturaAux(NodoGen n, int i) {
        int j = i;
        int aux;
        // Recorre en preorden con el contador + 1
        NodoGen hijo = n.getHijoIzquierdo();
        while (hijo != null) {
            aux = alturaAux(hijo, j + 1);
            if (aux > i) {
                // Si el contador devuelto por ese hijo es mayor a alguno de los hijos
                // recorridos anteriormente, se
                // setea como mayor
                i = aux;
            }
            hijo = hijo.getHermanoDerecho();
        }
        if (i > j) {
            // Si el contador fue modificado lo retorna
            j = i;
        }
        return j;
    }

    public int nivel(Object elem) {
        int nivel = -1;
        boolean[] aux = { false };
        if (this.raiz != null) {
            // Si la raiz no es nula retorna el auxiliar
            nivel = nivelAux(this.raiz, elem, 0, aux);
        }
        if ((this.raiz == null) || (!aux[0])) {
            // Si la raiz es nula, o el auxiliar no encontro al objeto ingresado, retorna -1
            nivel = -1;
        }
        return nivel;
    }

    private int nivelAux(NodoGen n, Object elem, int i, boolean[] encontrado) {
        int j = i;
        if (n.getElem() == elem) {
            // Si el nodo actual tiene al elemento buscado, cambia la bandera a true
            encontrado[0] = true;
        } else {
            // Si el nodo actual no tiene al elemento buscado, recorre en preorden con
            // contador + 1 hasta que se termine
            // el subarbol o hasta que lo encuentre
            NodoGen hijo = n.getHijoIzquierdo();
            while ((hijo != null) && (!encontrado[0])) {
                i = nivelAux(hijo, elem, j + 1, encontrado);
                hijo = hijo.getHermanoDerecho();
            }
            if (encontrado[0]) {
                // Si se encontro al elemento retorna el contador que devolvio la rama de ese
                // elemento
                j = i;
            }
        }
        return j;
    }

    public Object padre(Object elem) {
        Object retorno = null;
        boolean[] encontrado = { false };
        if ((this.raiz != null) && (this.raiz.getElem() != elem)) {
            // Si la raiz no es nula y su elemento no es el buscado, busca al padre mediante
            // el metodo auxiliar
            NodoGen nPadre = buscarPadre(this.raiz, elem, encontrado);
            if (nPadre != null) {
                // Si se encontro al padre, retorna su elemento
                retorno = nPadre.getElem();
            }
        }
        return retorno;
    }

    private NodoGen buscarPadre(NodoGen n, Object elem, boolean[] encontrado) {
        NodoGen nPadre = null;
        if (n.getElem() == elem) {
            // Si el nodo actual tiene al elemento buscado, cambia la bandera a true
            encontrado[0] = true;
        } else {
            // Si el nodo actual no tiene al elemento buscado, recorre en preorden hasta
            // llegar al final del subarbol
            // o hasta encontrarlo
            NodoGen hijoActual = n.getHijoIzquierdo();
            while ((hijoActual != null) && (nPadre == null)) {
                nPadre = buscarPadre(hijoActual, elem, encontrado);
                if ((nPadre == null) && (encontrado[0])) {
                    // Si el retorno fue nulo pero la bandera fue cambiada, entonces el padre es el
                    // actual, lo retorna
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
            // Si el nodo actual no es nulo, retorna un nodo con el elemento del actual, el
            // retorno del llamado con el
            // hijo izquierdo y el retorno del llamado con el hermano derecho.
            nAux = new NodoGen(n.getElem(), cloneAux(n.getHijoIzquierdo()), cloneAux(n.getHermanoDerecho()));
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
            l.insertar(n.getElem(), l.longitud() + 1);
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
            l.insertar(n.getElem(), l.longitud() + 1);
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
            l.insertar(n.getElem(), l.longitud() + 1);
        }
    }

    public Lista listarPorNiveles() {
        Lista l = new Lista();
        Cola q = new Cola();
        if (this.raiz != null) {
            q.poner(this.raiz);
        }
        // Se agregan y quitan elementos de la cola por los niveles del arbol, hasta que
        // la cola se vacie
        while (!q.esVacia()) {
            NodoGen nodoAux = (NodoGen) q.obtenerFrente();
            q.sacar();
            l.insertar(nodoAux.getElem(), l.longitud() + 1);
            // Pone todos los hijos del nodoAux en la cola.
            NodoGen hijo = nodoAux.getHijoIzquierdo();
            while (hijo != null) {
                q.poner(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return l;
    }

    public String toString(){
        return toStingAux(this.raiz);
    }

    private String toStingAux(NodoGen n){
        String s="";
        if(n!=null){
            s+=n.getElem().toString()+":";
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo!=null){
                s+=hijo.getElem().toString();
                hijo = hijo.getHermanoDerecho();
                if (hijo!=null) s+=",";
            }
            hijo=n.getHijoIzquierdo();
            while (hijo!=null){
                s+='\n' + toStingAux(hijo);
                hijo=hijo.getHermanoDerecho();
            }
        }
        return s;

    }

    /*************************************************************
     * MÉTODOS PARA EJERCICIOS DE PRÁCTICA
     *************************************************************/
    public boolean verificarPatron(Lista lisPatron) {
        boolean esPatron = false;
        if ((this.raiz != null) && (!lisPatron.esVacia())) {
            int longitud = lisPatron.longitud();
            esPatron = verificarPatronAux(this.raiz, lisPatron, 1, longitud);
        }
        return esPatron;
    }

    private boolean verificarPatronAux(NodoGen n, Lista lp, int i, int l) {
        boolean esPatron = false;
        if (n != null) {
            if (n.getElem() == lp.recuperar(i)) {
                if (i == l) {
                    if (n.getHijoIzquierdo() == null) {
                        esPatron = true;
                    }
                } else {
                    NodoGen hijo = n.getHijoIzquierdo();
                    while ((hijo != null) && (!esPatron)) {
                        esPatron = verificarPatronAux(hijo, lp, i + 1, l);
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            }
        }
        return esPatron;
    }

    public Lista frontera() {
        Lista listaNodosFrontera = new Lista();
        if (this.raiz != null) {
            fronteraAux(this.raiz, listaNodosFrontera);
        }
        return listaNodosFrontera;
    }

    private void fronteraAux(NodoGen n, Lista frontera) {
        NodoGen hijo = n.getHijoIzquierdo();
        if (hijo == null) {
            frontera.insertar(n.getElem(), frontera.longitud() + 1);
        } else {
            do {
                fronteraAux(hijo, frontera);
                hijo = hijo.getHermanoDerecho();
            } while (hijo != null);
        }
    }
    
    public Lista listaQueJustificaLaAltura() {
    Lista listaDeAltura = new Lista();
    if (this.raiz != null) {
        listaDeAltura = listaQueJustificaLaAlturaAux(this.raiz);
    }
    return listaDeAltura;
}

private Lista listaQueJustificaLaAlturaAux(NodoGen n) {
    Lista mayorCaminoHijos = new Lista();
    int maxAltura = -1;

    // 1. Explorar todos los hijos para encontrar el camino más largo entre ellos
    NodoGen hijo = n.getHijoIzquierdo();
    while (hijo != null) {
        Lista caminoHijo = listaQueJustificaLaAlturaAux(hijo);
        int alturaHijo = caminoHijo.longitud();

        if (alturaHijo > maxAltura) {
            maxAltura = alturaHijo;
            mayorCaminoHijos = caminoHijo; // Nos quedamos con la lista más larga
        }
        hijo = hijo.getHermanoDerecho();
    }

    // 2. Crear una nueva lista que sea: mi elemento + el mejor camino de mis hijos
    // Usamos un clon o una lista nueva para no alterar las rutas de otros niveles
    Lista resultado = mayorCaminoHijos.clone();
    resultado.insertar(n.getElem(), 1); // Insertamos al padre al principio

    return resultado;
}
}
