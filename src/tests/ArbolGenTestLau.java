package tests;

import lineales.dinamicas.*;
import org.junit.Before;
import org.junit.Test;

import jerarquicas.ArbolGen;

import static org.junit.Assert.*;

public class ArbolGenTestLau {

    private ArbolGen arbol;

    /*
     * A
     * / | \
     * B C D
     * / \ \
     * E F G
     * /
     * H
     */
    @Before
    public void setUp() {
        arbol = new ArbolGen();
        arbol.insertar('A', null);
        arbol.insertar('B', 'A');
        arbol.insertar('C', 'A');
        arbol.insertar('D', 'A');
        arbol.insertar('E', 'B');
        arbol.insertar('F', 'B');
        arbol.insertar('G', 'D');
        arbol.insertar('H', 'E');
    }

    // --- insertar ---
    @Test
    public void insertarEnArbolVacio() {
        ArbolGen a = new ArbolGen();
        assertTrue(a.insertar('A', null));
        assertFalse(a.esVacio());
    }

    @Test
    public void insertarPrimerHijo() {
        ArbolGen a = new ArbolGen();
        a.insertar('A', null);
        assertTrue(a.insertar('B', 'A'));
        assertTrue(a.pertenece('B'));
    }

    @Test
    public void insertarSegundoHijo() {
        // B y C son hijos de A
        assertTrue(arbol.pertenece('B'));
        assertTrue(arbol.pertenece('C'));
        assertEquals('A', arbol.padre('B'));
        assertEquals('A', arbol.padre('C'));
    }

    @Test
    public void insertarSinPadreEnArbolVacio() {
        // insertar con padre null en árbol vacío → debe crear la raíz
        ArbolGen a = new ArbolGen();
        assertTrue(a.insertar('A', null));
    }

    // --- insertarPorPos ---
    @Test
    public void insertarPorPosEnArbolVacio() {
        ArbolGen a = new ArbolGen();
        assertTrue(a.insertarPorPosicion('A', 0));
        assertFalse(a.esVacio());
        assertTrue(a.pertenece('A'));
    }

    @Test
    public void insertarPorPosEnArbolVacioPosGrande() {
        ArbolGen a = new ArbolGen();
        // 1000 es una posición inexistente en el árbol
        assertFalse(a.insertarPorPosicion('A', 1000));
        // assertTrue(a.insertarPorPosicion('A', 1000));
        // No se pudo insertar: el árbol queda vacío
        assertTrue(a.esVacio());
        // assertFalse(a.esVacio());
        assertFalse(a.pertenece('A'));
        // idem
        // assertTrue(a.pertenece('A'));
    }

    @Test
    public void insertarPorPosEnRaiz() {
        // pos 0 = raíz A, agrega hijo al final de B,C,D
        ArbolGen a = new ArbolGen();
        a.insertar('A', null);
        a.insertar('B', 'A');
        assertTrue(a.insertarPorPosicion('C', 0)); // padre en pos 0 = A
        assertTrue(a.pertenece('C'));
        assertEquals('A', a.padre('C'));
    }

    @Test
    public void insertarPorPosNodoProfundo() {
        // árbol: A(pos0) -> B(pos1) -> E(pos2)
        ArbolGen a = new ArbolGen();
        a.insertarPorPosicion('A', 0);
        a.insertarPorPosicion('B', 0); // hijo de A
        a.insertarPorPosicion('E', 1); // hijo de B
        assertTrue(a.insertarPorPosicion('Z', 2)); // hijo de E (pos 2)
        assertTrue(a.pertenece('Z'));
        assertEquals('E', a.padre('Z'));
    }

    @Test
    public void insertarPorPosPadreInexistente() {
        // pos 99 no existe en el árbol
        ArbolGen a = new ArbolGen();
        a.insertar('A', null);
        assertFalse(a.insertarPorPosicion('Z', 99));
        assertFalse(a.pertenece('Z'));
    }

    @Test
    public void insertarPorPosAgregaComoUltimoHermano() {
        // A ya tiene hijos B,C,D → insertar en pos 0 agrega al final
        assertTrue(arbol.insertarPorPosicion('Z', 1));
        assertTrue(arbol.pertenece('Z'));
        assertEquals('A', arbol.padre('Z'));
    }

    // --- pertenece ---
    @Test
    public void perteneceHojaProfunda() {
        assertTrue(arbol.pertenece('H'));
    }

    @Test
    public void perteneceInexistente() {
        assertFalse(arbol.pertenece('Z'));
    }

    @Test
    public void perteneceVacio() {
        assertFalse(new ArbolGen().pertenece('A'));
    }

    // --- altura ---
    @Test
    public void alturaVacio() {
        assertEquals(-1, new ArbolGen().altura());
    }

    @Test
    public void alturaRaiz() {
        ArbolGen a = new ArbolGen();
        a.insertar('A', null);
        assertEquals(0, a.altura());
    }

    @Test
    public void alturaTotal() {
        assertEquals(3, arbol.altura());
    } // A->B->E->H

    // --- nivel ---
    @Test
    public void nivelRaiz() {
        assertEquals(0, arbol.nivel('A'));
    }

    @Test
    public void nivelHojaProfunda() {
        assertEquals(3, arbol.nivel('H'));
    }

    @Test
    public void nivelInexistente() {
        assertEquals(-1, arbol.nivel('Z'));
    }

    // --- ancestros ---
    @Test
    public void ancestrosRaizVacia() {
        assertTrue(arbol.ancestros('A').esVacia());
    }

    @Test
    public void ancestrosHojaProfunda() {
        Lista r = arbol.ancestros('H'); // A, B, E
        assertEquals(3, r.longitud());
        assertEquals('A', r.recuperar(1));
        assertEquals('B', r.recuperar(2));
        assertEquals('E', r.recuperar(3));
    }

    @Test
    public void ancestrosInexistente() {
        assertTrue(arbol.ancestros('Z').esVacia());
    }

    @Test
    public void ancestrosNoAgregaHermanos() {
        Lista r = arbol.ancestros('G'); // solo A y D, no B ni C
        assertEquals(2, r.longitud());
        assertEquals('A', r.recuperar(1));
        assertEquals('D', r.recuperar(2));
    }

    // --- padre ---
    @Test
    public void padreRaizNull() {
        assertNull(arbol.padre('A'));
    }

    @Test
    public void padreInexistenteNull() {
        assertNull(arbol.padre('Z'));
    }

    @Test
    public void padreHojaProfunda() {
        assertEquals('E', arbol.padre('H'));
    }

    @Test
    public void padreUltimoHijo() {
        assertEquals('D', arbol.padre('G'));
    }

    // --- clone ---
    @Test
    public void cloneIndependiente() {
        ArbolGen clon = arbol.clone();
        clon.vaciar();
        assertTrue(arbol.pertenece('H')); // original intacto
    }

    @Test
    public void cloneContenido() {
        ArbolGen clon = arbol.clone();
        assertEquals(arbol.altura(), clon.altura());
        assertEquals(arbol.nivel('H'), clon.nivel('H'));
    }

    // --- recorridos ---
    @Test
    public void preOrdenRaizPrimero() {
        assertEquals('A', arbol.listarPreorden().recuperar(1));
    }

    @Test
    public void posOrdenRaizUltimo() {
        Lista r = arbol.listarPosorden();
        assertEquals('A', r.recuperar(r.longitud()));
    }

    @Test
    public void listaNivelesSegundoNivel() {
        Lista r = arbol.listarPorNiveles(); // A B C D E F G H
        assertEquals('B', r.recuperar(2));
        assertEquals('C', r.recuperar(3));
        assertEquals('D', r.recuperar(4));
    }
}
