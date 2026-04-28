package tests;

import jerarquicas.*;
import lineales.dinamicas.Lista;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArbolBinTestPersonal {
    public static void cargarArbolCompleto4Niveles(ArbolBin arbol) {
        arbol.insertar("A", null, true);

        arbol.insertar("B", "A", true);
        arbol.insertar("C", "A", false);

        arbol.insertar("D", "B", true);
        arbol.insertar("E", "B", false);
        arbol.insertar("F", "C", true);
        arbol.insertar("G", "C", false);

        arbol.insertar("H", "D", true);
        arbol.insertar("I", "D", false);
        arbol.insertar("J", "E", true);
        arbol.insertar("K", "E", false);
        arbol.insertar("L", "F", true);
        arbol.insertar("M", "F", false);
        arbol.insertar("N", "G", true);
        arbol.insertar("O", "G", false);
    }

    public static void cargarArbolSoloRaiz(ArbolBin arbol) {
        arbol.insertar("A", null, true);
    }

    public static void cargarArbolAsimetrico(ArbolBin arbol) {
        arbol.insertar("A", null, true);
        arbol.insertar("B", "A", true);
        arbol.insertar("D", "B", true);
        arbol.insertar("C", "A", false);
        arbol.insertar("E", "C", true);
        arbol.insertar("F", "C", false);
        arbol.insertar("G", "E", true);
        arbol.insertar("H", "E", false);
    }

    public static void cargarArbol3Elementos(ArbolBin arbol) {
        arbol.insertar("A", null, true);
        arbol.insertar("B", "A", true);
        arbol.insertar("C", "A", false);
    }

    /****************************************************************************************************************
     * TEST DE CONSTRUCCIÓN
     ****************************************************************************************************************/
    @Test public void testCrearArbolBinario() {
        ArbolBin a = new ArbolBin();
        boolean ev = a.esVacio();
        String ts = a.toString();
        boolean tse = ts.equals("[Arbol Binario vacío.]");
        assertEquals(ev, true);
        assertEquals(tse, true);
    }

    /****************************************************************************************************************
     * TEST DE INSERTAR
     ****************************************************************************************************************/
    @Test public void insertarArbolVacio() {
        ArbolBin a = new ArbolBin();
        Lista listaPreorden = new Lista();
        boolean ins = a.insertar("A", 1, true);
        listaPreorden = a.listarPreorden();
        boolean ev = a.esVacio();
        String ts = listaPreorden.toString();
        System.out.println(ts);
        boolean tse = ts.equals("[A]");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(ins, true);
    }

    @Test public void insertarArbolConElementos() {
        ArbolBin a = new ArbolBin();
        Lista lp = new Lista();
        cargarArbolAsimetrico(a);
        boolean ins = a.insertar("I", "H", true);
        lp = a.listarPreorden();
        boolean ev = a.esVacio();
        String ts = lp.toString();
        boolean tse = ts.equals("[A,B,D,C,E,G,H,I,F]");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(ins, true);
    }

    @Test public void insertarEnArbolConPadreNoEncontrado() {
        ArbolBin a = new ArbolBin();
        Lista lp = new Lista();
        cargarArbolSoloRaiz(a);
        boolean ins = a.insertar("I", "H", true);
        lp = a.listarPreorden();
        boolean ev = a.esVacio();
        String ts = lp.toString();
        boolean tse = ts.equals("[A]");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(ins, false);
    }

    @Test public void insertarEnPosicionOcupada() {
        ArbolBin a = new ArbolBin();
        Lista lp = new Lista();
        cargarArbolSoloRaiz(a);
        a.insertar("B", "A", true);
        boolean ins = a.insertar("C", "A", true);
        lp = a.listarPreorden();
        boolean ev = a.esVacio();
        String ts = lp.toString();
        boolean tse = ts.equals("[A,B]");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(ins, false);
    }

    /**************************************************************************************************************** 
     * TEST DE INSERTAR POR POSICION
     ****************************************************************************************************************/
    @Test public void insertarPPArbolVacio() {
        ArbolBin a = new ArbolBin();
        Lista lp;
        boolean ins = a.insertarPorPosicion("A", 0, false);
        lp = a.listarPreorden();
        boolean ev = a.esVacio();
        String ts = lp.toString();
        boolean tse = ts.equals("[A]");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(ins, true);
    }

    @Test public void insertarPPRaizPosMenorACero() {
        ArbolBin a = new ArbolBin();
        Lista lp;
        boolean ins = a.insertarPorPosicion("A", -1, false);
        lp = a.listarPreorden();
        boolean ev = a.esVacio();
        String ts = lp.toString();
        boolean tse = ts.equals("[]");
        assertEquals(ev, true);
        assertEquals(tse, true);
        assertEquals(ins, false);
    }

    @Test public void insertarPPRaizPosMayorACero() {
        ArbolBin a = new ArbolBin();
        Lista lp;
        boolean ins = a.insertarPorPosicion("A", 1, false);
        lp = a.listarPreorden();
        boolean ev = a.esVacio();
        String ts = lp.toString();
        boolean tse = ts.equals("[]");
        assertEquals(ev, true);
        assertEquals(tse, true);
        assertEquals(ins, false);
    }

    @Test public void insertarPPNoVacio() {
        ArbolBin a = new ArbolBin();
        Lista lp;
        cargarArbolAsimetrico(a);
        boolean ins = a.insertarPorPosicion("I", 7, true);
        lp = a.listarPreorden();
        boolean ev = a.esVacio();
        String ts = lp.toString();
        boolean tse = ts.equals("[A,B,D,C,E,G,H,I,F]");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(ins, true);
    }

    @Test public void insertarPPConPadreNoEncontrado() {
        ArbolBin a = new ArbolBin();
        Lista lp;
        cargarArbolAsimetrico(a);
        boolean ins = a.insertarPorPosicion("I", 28, true);
        lp = a.listarPreorden();
        boolean ev = a.esVacio();
        String ts = lp.toString();
        boolean tse = ts.equals("[A,B,D,C,E,G,H,F]");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(ins, false);
    }

    @Test public void insertarPPEnPosicionOcupada() {
        ArbolBin a = new ArbolBin();
        Lista lp;
        cargarArbolAsimetrico(a);
        boolean ins = a.insertarPorPosicion("I", 1, true);
        lp = a.listarPreorden();
        boolean ev = a.esVacio();
        String ts = lp.toString();
        boolean tse = ts.equals("[A,B,D,C,E,G,H,F]");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(ins, false);
    }

    /**************************************************************************************************************** 
     * TEST DE LISTAR POSORDEN
     ****************************************************************************************************************/

    @Test public void testListarPosorden() {
        ArbolBin a = new ArbolBin();
        Lista lp;
        lp = a.listarPosorden();
        String ts = lp.toString();
        boolean tse = ts.equals("[]");
        assertEquals(tse, true);
        a.vaciar();
        cargarArbolSoloRaiz(a);
        lp = a.listarPosorden();
        ts = lp.toString();
        tse = ts.equals("[A]");
        assertEquals(tse, true);
        a.vaciar();
        cargarArbolAsimetrico(a);
        lp = a.listarPosorden();
        ts = lp.toString();
        System.out.println(lp.toString());
        tse = ts.equals("[D,B,G,H,E,F,C,A]");
        assertEquals(tse, true);
        a.vaciar();
        cargarArbolCompleto4Niveles(a);
        lp = a.listarPosorden();
        ts = lp.toString();
        tse = ts.equals("[H,I,D,J,K,E,B,L,M,F,N,O,G,C,A]");
        assertEquals(tse, true);
    }

    /**************************************************************************************************************** 
     * TEST DE LISTAR INORDEN
     ****************************************************************************************************************/
    @Test public void testListarInorden() {
        ArbolBin a = new ArbolBin();
        Lista lp;
        lp = a.listarInorden();
        String ts = lp.toString();
        boolean tse = ts.equals("[]");
        assertEquals(tse, true);
        a.vaciar();
        cargarArbolSoloRaiz(a);
        lp = a.listarInorden();
        ts = lp.toString();
        tse = ts.equals("[A]");
        assertEquals(tse, true);
        a.vaciar();
        cargarArbolAsimetrico(a);
        lp = a.listarInorden();
        ts = lp.toString();
        System.out.println(lp.toString());
        tse = ts.equals("[D,B,A,G,E,H,C,F]");
        assertEquals(tse, true);
        a.vaciar();
        cargarArbolCompleto4Niveles(a);
        lp = a.listarInorden();
        ts = lp.toString();
        tse = ts.equals("[H,D,I,B,J,E,K,A,L,F,M,C,N,G,O]");
        assertEquals(tse, true);
    }

    /**************************************************************************************************************** 
     * TEST DE LISTAR POR NIVELES
     ****************************************************************************************************************/
    @Test public void testListarPorNiveles() {
        ArbolBin a = new ArbolBin();
        Lista lp;
        lp = a.listarPorNiveles();
        String ts = lp.toString();
        boolean tse = ts.equals("[]");
        assertEquals(tse, true);
        a.vaciar();
        cargarArbolSoloRaiz(a);
        lp = a.listarPorNiveles();
        ts = lp.toString();
        System.out.println(ts);
        tse = ts.equals("[A]");
        assertEquals(tse, true);
        a.vaciar();
        cargarArbolAsimetrico(a);
        lp = a.listarPorNiveles();
        ts = lp.toString();
        System.out.println(lp.toString());
        tse = ts.equals("[A,B,C,D,E,F,G,H]");
        assertEquals(tse, true);
        a.vaciar();
        cargarArbolCompleto4Niveles(a);
        lp = a.listarPorNiveles();
        ts = lp.toString();
        tse = ts.equals("[A,B,C,D,E,F,G,H,I,J,K,L,M,N,O]");
        assertEquals(tse, true);
    }

    /**************************************************************************************************************** 
     * TESTS PADRE
     ****************************************************************************************************************/
    @Test public void buscarPadreEnVacio() {
        ArbolBin a = new ArbolBin();
        Lista lp;
        NodoArbol na = a.padre("B");
        lp = a.listarPreorden();
        boolean ev = a.esVacio();
        String ts = lp.toString();
        boolean tse = ts.equals("[]");
        assertEquals(ev, true);
        assertEquals(tse, true);
        assertEquals(na, null);
    }

    @Test public void buscarPadreRaiz() {
        ArbolBin a = new ArbolBin();
        Lista lp;
        cargarArbol3Elementos(a);
        NodoArbol na = a.padre("B");
        String nats = na.getElem().toString();
        lp = a.listarPreorden();
        boolean ev = a.esVacio();
        String ts = lp.toString();
        boolean tse = ts.equals("[A,B,C]");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(nats, "A");
    }

    @Test public void buscarPadreElementoMedio() {
        ArbolBin a = new ArbolBin();
        Lista lp;
        cargarArbolAsimetrico(a);
        NodoArbol na = a.padre("E");
        String nats = na.getElem().toString();
        lp = a.listarPreorden();
        boolean ev = a.esVacio();
        String ts = lp.toString();
        boolean tse = ts.equals("[A,B,D,C,E,G,H,F]");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(nats, "C");
    }

    @Test public void buscarPadreElemInexistente() {
        ArbolBin a = new ArbolBin();
        Lista lp;
        cargarArbol3Elementos(a);
        NodoArbol na = a.padre("E");
        lp = a.listarPreorden();
        boolean ev = a.esVacio();
        String ts = lp.toString();
        boolean tse = ts.equals("[A,B,C]");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(na, null);
    }

    /**************************************************************************************************************** 
     * TESTS DE ALTURA
     ****************************************************************************************************************/
    @Test public void calcularAlturaVacio() {
        ArbolBin a = new ArbolBin();
        int h = a.altura();
        boolean ev = a.esVacio();
        assertEquals(ev, true);
        assertEquals(h, -1);
    }
    
    @Test public void calcularAlturaSoloRaiz() {
        ArbolBin a = new ArbolBin();
        cargarArbolSoloRaiz(a);
        int h = a.altura();
        boolean ev = a.esVacio();
        assertEquals(ev, false);
        assertEquals(h, 0);
    }

    @Test public void calcularAlturaAsimetrico() {
        ArbolBin a = new ArbolBin();
        cargarArbolAsimetrico(a);
        int h = a.altura();
        boolean ev = a.esVacio();
        assertEquals(ev, false);
        assertEquals(h, 3);
    }

    @Test public void calcularAlturaCompleto() {
        ArbolBin a = new ArbolBin();
        cargarArbolCompleto4Niveles(a);
        int h = a.altura();
        boolean ev = a.esVacio();
        assertEquals(ev, false);
        assertEquals(h, 3);
    }

    /**************************************************************************************************************** 
     * TESTS DE NIVEL
     ****************************************************************************************************************/
    @Test public void calcularNivelVacio() {
        ArbolBin a = new ArbolBin();
        int n = a.nivel("A");
        boolean ev = a.esVacio();
        assertEquals(ev, true);
        assertEquals(n, -1);
    }

    @Test public void calcularNivelElementoInexistente() {
        ArbolBin a = new ArbolBin();
        cargarArbol3Elementos(a);
        int n = a.nivel("Z");
        boolean ev = a.esVacio();
        assertEquals(ev, false);
        assertEquals(n, -1);
    }

    @Test public void calcularNivelRaiz() {
        ArbolBin a = new ArbolBin();
        cargarArbolAsimetrico(a);
        int n = a.nivel("A");
        boolean ev = a.esVacio();
        assertEquals(ev, false);
        assertEquals(n, 0);
    }

    @Test public void calcularNivelIntermedio() {
        ArbolBin a = new ArbolBin();
        cargarArbolCompleto4Niveles(a);
        int n = a.nivel("F");
        boolean ev = a.esVacio();
        assertEquals(ev, false);
        assertEquals(n, 2);
    }

    @Test public void calcularNivelHoja() {
        ArbolBin a = new ArbolBin();
        cargarArbolCompleto4Niveles(a);
        int n = a.nivel("J");
        boolean ev = a.esVacio();
        assertEquals(ev, false);
        assertEquals(n, 3);
    }

    /**************************************************************************************************************** 
     * TESTS DE CLONE
     ****************************************************************************************************************/
    @Test public void testClonarVacio() {
        ArbolBin a = new ArbolBin();
        ArbolBin ac;
        ac = a.clone();
        boolean ev = a.esVacio();
        boolean evc = ac.esVacio();
        String ts = a.toString();
        String tsc = ac.toString();
        assertEquals(ev, true);
        assertEquals(evc, true);
        assertEquals(ts, tsc);
    }

    @Test public void testClonarSoloRaiz() {
        ArbolBin a = new ArbolBin();
        cargarArbolSoloRaiz(a);
        ArbolBin ac;
        ac = a.clone();
        boolean ev = a.esVacio();
        boolean evc = ac.esVacio();
        String ts = a.toString();
        String tsc = ac.toString();
        assertEquals(ev, false);
        assertEquals(evc, false);
        assertEquals(ts, tsc);
    }

    @Test public void testClonarAsimetrico() {
        ArbolBin a = new ArbolBin();
        cargarArbolAsimetrico(a);
        ArbolBin ac;
        ac = a.clone();
        boolean ev = a.esVacio();
        boolean evc = ac.esVacio();
        String ts = a.toString();
        String tsc = ac.toString();
        assertEquals(ev, false);
        assertEquals(evc, false);
        assertEquals(ts, tsc);
    }

    @Test public void testClonarCompleto() {
        ArbolBin a = new ArbolBin();
        cargarArbolCompleto4Niveles(a);
        ArbolBin ac;
        ac = a.clone();
        boolean ev = a.esVacio();
        boolean evc = ac.esVacio();
        String ts = a.toString();
        String tsc = ac.toString();
        assertEquals(ev, false);
        assertEquals(evc, false);
        assertEquals(ts, tsc);
    }
}
