package tests;
import lineales.dinamicas.Lista;
import jerarquicas.ArbolGen;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class ArbolGenTestPersonal {
    private ArbolGen arbol;
    public static void cargarArbolGen(ArbolGen ag) {
        ag.insertar("a", null);
        ag.insertar("b", "a");
        ag.insertar("c", "a");
        ag.insertar("d", "a");
        ag.insertar("e", "b");
        ag.insertar("f", "b");
        ag.insertar("j", "f");
        ag.insertar("k", "f");
        ag.insertar("l", "f");
        ag.insertar("g", "d");
        ag.insertar("h", "d");
        ag.insertar("i", "d");
        ag.insertar("m", "g");
        ag.insertar("p", "m");
        ag.insertar("q", "m");
        ag.insertar("n", "i");
        ag.insertar("o", "i");
    }
    public static void cargarArbolGenPorPos(ArbolGen ag) {
        ag.insertarPorPosicion("a", 0);
        ag.insertarPorPosicion("b", 1);
        ag.insertarPorPosicion("c", 1);
        ag.insertarPorPosicion("d", 1);
        ag.insertarPorPosicion("e", 4);
        ag.insertarPorPosicion("f", 4);
        ag.insertarPorPosicion("j", 5);
        ag.insertarPorPosicion("k", 5);
        ag.insertarPorPosicion("l", 5);
        ag.insertarPorPosicion("g", 2);
        ag.insertarPorPosicion("h", 2);
        ag.insertarPorPosicion("i", 2);
        ag.insertarPorPosicion("m", 5);
        ag.insertarPorPosicion("p", 6);
        ag.insertarPorPosicion("q", 6);
        ag.insertarPorPosicion("n", 3);
        ag.insertarPorPosicion("o", 3);
    }

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
    /*************************************************************
    * TEST DE ArbolGen()
    *************************************************************/
    @Test public void testConstructorVacio() {
        ArbolGen ag = new ArbolGen();
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        System.out.println(ts);
        boolean tse = ts.equals("");
        assertEquals(ev, true);
        assertEquals(tse, true);
    }

    /*************************************************************
    * TESTS DE insertar(Object, Object)
    *************************************************************/
    @Test public void insertarEnVacio() {
        ArbolGen ag = new ArbolGen();
        boolean ex = ag.insertar("a", null);
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(ex, true);
    }
    @Test public void insertarHijoUnico() {
        ArbolGen ag = new ArbolGen();
        boolean ex = ag.insertar("a", null);
        boolean ex2 = ag.insertar("b", "a");
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:b\n"+ //
                                "b:");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(ex, true);
        assertEquals(ex2, true);
    }
    @Test public void insertarHermano() {
        ArbolGen ag = new ArbolGen();
        boolean ex = ag.insertar("a", null);
        boolean ex2 = ag.insertar("b", "a");
        boolean ex3 = ag.insertar("c", "a");
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:c,b\n"+ //
                                "c:\n"+ //
                                "b:");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(ex, true);
        assertEquals(ex2, true);
        assertEquals(ex3, true);
    }
    @Test public void insertarEnPadreInexistente() {
        ArbolGen ag = new ArbolGen();
        boolean ex = ag.insertar("a", null);
        boolean ex2 = ag.insertar("b", "a");
        boolean ex3 = ag.insertar("c", "a");
        boolean ex4 = ag.insertar("y", "z");
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:c,b\n"+ //
                                "c:\n"+ //
                                "b:");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(ex, true);
        assertEquals(ex2, true);
        assertEquals(ex3, true);
        assertEquals(ex4, false);
    }

    /**************************************************************************************************************** 
     * TESTS DE insertarPorPosicion(Object, int)
     ****************************************************************************************************************/
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
        assertTrue(a.insertarPorPosicion('A', 1000));
        assertFalse(a.esVacio());
        assertTrue(a.pertenece('A'));
    }

    @Test
    public void insertarPorPosEnRaiz() {
        // pos 0 = raíz A, agrega hijo al final de B,C,D
        ArbolGen a = new ArbolGen();
        a.insertar('A', null);
        a.insertar('B', 'A');
        assertTrue(a.insertarPorPosicion('C', 1)); // padre en pos 0 = A
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

    /**************************************************************************************************************** 
     * TESTS pertenece(Object)
     ****************************************************************************************************************/
    @Test public void perteneceEnVacio() {
        ArbolGen ag = new ArbolGen();
        boolean p = ag.pertenece("a");
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("");
        assertEquals(ev, true);
        assertEquals(tse, true);
        assertEquals(p, false);
    }
    @Test public void perteneceRaiz() {
        ArbolGen ag = new ArbolGen();
        ag.insertar("a", null);
        ag.insertar("b", "a");
        ag.insertar("c", "a");
        boolean p = ag.pertenece("a");
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:c,b\n"+ //
                                "c:\n"+ //
                                "b:");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(p, true);
    }
    @Test public void perteneceHoja() {
        ArbolGen ag = new ArbolGen();
        ag.insertar("a", null);
        ag.insertar("b", "a");
        ag.insertar("c", "a");
        boolean p = ag.pertenece("c");
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:c,b\n"+ //
                                "c:\n"+ //
                                "b:");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(p, true);
    }
    @Test public void noPertenece() {
        ArbolGen ag = new ArbolGen();
        ag.insertar("a", null);
        ag.insertar("b", "a");
        ag.insertar("c", "a");
        boolean p = ag.pertenece("z");
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:c,b\n"+ //
                                "c:\n"+ //
                                "b:");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(p, false);
    }

    /**************************************************************************************************************** 
     * TESTS DE ancestros(Object)
     ****************************************************************************************************************/
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

    /**************************************************************************************************************** 
     * TESTS DE esVacio()
     ****************************************************************************************************************/
    @Test public void vacioEnVacio() {
        ArbolGen ag = new ArbolGen();
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("");
        assertEquals(ev, true);
        assertEquals(tse, true);
    }
    @Test public void vacioEnLleno() {
        ArbolGen ag = new ArbolGen();
        ag.insertar("a", null);
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:");
        assertEquals(ev, false);
        assertEquals(tse, true);
    }
    @Test public void vacioCambio() {
        ArbolGen ag = new ArbolGen();
        boolean antes = ag.esVacio();
        ag.insertar("a", null);
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:");
        assertEquals(ev, false);
        assertEquals(antes, true);
        assertEquals(tse, true);
    }

    /**************************************************************************************************************** 
     * TESTS DE altura()
     ****************************************************************************************************************/
    @Test public void alturaArbolVacio() {
        ArbolGen ag = new ArbolGen();
        int a = ag.altura();
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("");
        assertEquals(ev, true);
        assertEquals(tse, true);
        assertEquals(a, -1);
    }
    @Test public void alturaArbolSoloRaiz() {
        ArbolGen ag = new ArbolGen();
        boolean in = ag.insertar("a", null);
        int a = ag.altura();
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(in, true);
        assertEquals(a, 0);
    }
    @Test public void alturaArbolCompleto() {
        ArbolGen ag = new ArbolGen();
        boolean in = ag.insertar("a", null);
        boolean in2 = ag.insertar("b", "a");
        boolean in3 = ag.insertar("c", "a");
        boolean in4 = ag.insertar("d", "b");
        int a = ag.altura();
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:c,b\n"+ //
                                "c:\n"+ //
                                "b:d\n"+ //
                                "d:");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(in, true);
        assertEquals(in2, true);
        assertEquals(in3, true);
        assertEquals(in4, true);
        assertEquals(a, 2);
    }

    /**************************************************************************************************************** 
     * TESTS DE nivel(Object)
     ****************************************************************************************************************/
    @Test public void nivelEnArbolVacio() {
        ArbolGen ag = new ArbolGen();
        int n = ag.nivel("a");
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("");
        assertEquals(ev, true);
        assertEquals(tse, true);
        assertEquals(n, -1);
    }
    @Test public void nivelArbolSoloRaiz() {
        ArbolGen ag = new ArbolGen();
        boolean in = ag.insertar("a", null);
        int n = ag.nivel("a");
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(in, true);
        assertEquals(n, 0);
    }
    @Test public void nivelArbolConElementos() {
        ArbolGen ag = new ArbolGen();
        boolean in = ag.insertar("a", null);
        boolean in2 = ag.insertar("b", "a");
        boolean in3 = ag.insertar("c", "a");
        boolean in4 = ag.insertar("d", "b");
        int n = ag.nivel("d");
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:c,b\n"+ //
                                "c:\n"+ //
                                "b:d\n"+ //
                                "d:");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(in, true);
        assertEquals(in2, true);
        assertEquals(in3, true);
        assertEquals(in4, true);
        assertEquals(n, 2);
    }
    @Test public void nivelObjetoInexistente() {
        ArbolGen ag = new ArbolGen();
        boolean in = ag.insertar("a", null);
        boolean in2 = ag.insertar("b", "a");
        boolean in3 = ag.insertar("c", "a");
        boolean in4 = ag.insertar("d", "b");
        int n = ag.nivel("z");
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:c,b\n"+ //
                                "c:\n"+ //
                                "b:d\n"+ //
                                "d:");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(in, true);
        assertEquals(in2, true);
        assertEquals(in3, true);
        assertEquals(in4, true);
        assertEquals(n, -1);
    }

    /**************************************************************************************************************** 
     * TESTS DE padre(Object)
     ****************************************************************************************************************/
    @Test public void padreEnArbolVacio() {
        ArbolGen ag = new ArbolGen();
        Object o = ag.padre("a");
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("");
        assertEquals(ev, true);
        assertEquals(tse, true);
        assertEquals(o, null);
    }
    @Test public void padreDeRaiz() {
        ArbolGen ag = new ArbolGen();
        boolean in = ag.insertar("a", null);
        Object o = ag.padre("a");
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(in, true);
        assertEquals(o, null);
    }
    @Test public void padreArbolConElementos() {
        ArbolGen ag = new ArbolGen();
        boolean in = ag.insertar("a", null);
        boolean in2 = ag.insertar("b", "a");
        boolean in3 = ag.insertar("c", "a");
        boolean in4 = ag.insertar("d", "b");
        Object o = ag.padre("d");
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:c,b\n"+ //
                                "c:\n"+ //
                                "b:d\n"+ //
                                "d:");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(in, true);
        assertEquals(in2, true);
        assertEquals(in3, true);
        assertEquals(in4, true);
        assertEquals(o, "b");
    }
    @Test public void padreElementoInexistente() {
        ArbolGen ag = new ArbolGen();
        boolean in = ag.insertar("a", null);
        boolean in2 = ag.insertar("b", "a");
        boolean in3 = ag.insertar("c", "a");
        boolean in4 = ag.insertar("d", "b");
        Object o = ag.padre("z");
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:c,b\n"+ //
                                "c:\n"+ //
                                "b:d\n"+ //
                                "d:");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(in, true);
        assertEquals(in2, true);
        assertEquals(in3, true);
        assertEquals(in4, true);
        assertEquals(o, null);
    }

    /**************************************************************************************************************** 
     * TESTS DE vaciar()
     ****************************************************************************************************************/
    @Test public void vaciarArbolVacio() {
        ArbolGen ag = new ArbolGen();
        ag.vaciar();
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("");
        assertEquals(ev, true);
        assertEquals(tse, true);
    }
    @Test public void vaciarArbolConElementos() {
        ArbolGen ag = new ArbolGen();
        boolean in = ag.insertar("a", null);
        boolean in2 = ag.insertar("b", "a");
        boolean in3 = ag.insertar("c", "a");
        boolean in4 = ag.insertar("d", "b");
        ag.vaciar();
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("");
        assertEquals(ev, true);
        assertEquals(tse, true);
        assertEquals(in, true);
        assertEquals(in2, true);
        assertEquals(in3, true);
        assertEquals(in4, true);
    }

    /**************************************************************************************************************** 
     * TESTS DE listarPreorden()
     ****************************************************************************************************************/
    @Test public void listarPreArbolVacio() {
        ArbolGen ag = new ArbolGen();
        Lista lis = ag.listarPreorden();
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("");
        boolean lisev = lis.esVacia();
        String lists = lis.toString();
        boolean listse = lists.equals("[]");
        assertEquals(ev, true);
        assertEquals(tse, true);
        assertEquals(lisev, true);
        assertEquals(listse, true);
    }
    @Test public void listarPreSoloRaiz() {
        ArbolGen ag = new ArbolGen();
        boolean in = ag.insertar("a", null);
        Lista lis = ag.listarPreorden();
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:");
        boolean lisev = lis.esVacia();
        String lists = lis.toString();
        boolean listse = lists.equals("[a]");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(in, true);
        assertEquals(lisev, false);
        assertEquals(listse, true);
    }
    @Test public void listarPreConElementos() {
        ArbolGen ag = new ArbolGen();
        boolean in = ag.insertar("a", null);
        boolean in2 = ag.insertar("b", "a");
        boolean in3 = ag.insertar("c", "a");
        boolean in4 = ag.insertar("d", "b");
        Lista lis = ag.listarPreorden();
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:c,b\n"+ //
                                "c:\n"+ //
                                "b:d\n"+ //
                                "d:");
        boolean lisev = lis.esVacia();
        String lists = lis.toString();
        boolean listse = lists.equals("[a,c,b,d]");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(in, true);
        assertEquals(in2, true);
        assertEquals(in3, true);
        assertEquals(in4, true);
        assertEquals(lisev, false);
        assertEquals(listse, true);
    }

    /**************************************************************************************************************** 
     * TESTS DE listarInorden()
     ****************************************************************************************************************/
    @Test public void listarInArbolVacio() {
        ArbolGen ag = new ArbolGen();
        Lista lis = ag.listarInorden();
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("");
        boolean lisev = lis.esVacia();
        String lists = lis.toString();
        boolean listse = lists.equals("[]");
        assertEquals(ev, true);
        assertEquals(tse, true);
        assertEquals(lisev, true);
        assertEquals(listse, true);
    }
    @Test public void listarInSoloRaiz() {
        ArbolGen ag = new ArbolGen();
        boolean in = ag.insertar("a", null);
        Lista lis = ag.listarInorden();
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:");
        boolean lisev = lis.esVacia();
        String lists = lis.toString();
        boolean listse = lists.equals("[a]");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(in, true);
        assertEquals(lisev, false);
        assertEquals(listse, true);
    }
    @Test public void listarInConElementos() {
        ArbolGen ag = new ArbolGen();
        boolean in = ag.insertar("a", null);
        boolean in2 = ag.insertar("b", "a");
        boolean in3 = ag.insertar("c", "a");
        boolean in4 = ag.insertar("d", "b");
        Lista lis = ag.listarInorden();
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:c,b\n"+ //
                                "c:\n"+ //
                                "b:d\n"+ //
                                "d:");
        boolean lisev = lis.esVacia();
        String lists = lis.toString();
        boolean listse = lists.equals("[c,a,d,b]");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(in, true);
        assertEquals(in2, true);
        assertEquals(in3, true);
        assertEquals(in4, true);
        assertEquals(lisev, false);
        assertEquals(listse, true);
    }

    /**************************************************************************************************************** 
     * TESTS DE listarPosorden()
     ****************************************************************************************************************/
    @Test public void listarPosArbolVacio() {
        ArbolGen ag = new ArbolGen();
        Lista lis = ag.listarPosorden();
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("");
        boolean lisev = lis.esVacia();
        String lists = lis.toString();
        boolean listse = lists.equals("[]");
        assertEquals(ev, true);
        assertEquals(tse, true);
        assertEquals(lisev, true);
        assertEquals(listse, true);
    }
    @Test public void listarPosSoloRaiz() {
        ArbolGen ag = new ArbolGen();
        boolean in = ag.insertar("a", null);
        Lista lis = ag.listarPosorden();
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:");
        boolean lisev = lis.esVacia();
        String lists = lis.toString();
        boolean listse = lists.equals("[a]");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(in, true);
        assertEquals(lisev, false);
        assertEquals(listse, true);
    }
    @Test public void listarPosConElementos() {
        ArbolGen ag = new ArbolGen();
        boolean in = ag.insertar("a", null);
        boolean in2 = ag.insertar("b", "a");
        boolean in3 = ag.insertar("c", "a");
        boolean in4 = ag.insertar("d", "b");
        Lista lis = ag.listarPosorden();
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:c,b\n"+ //
                                "c:\n"+ //
                                "b:d\n"+ //
                                "d:");
        boolean lisev = lis.esVacia();
        String lists = lis.toString();
        boolean listse = lists.equals("[c,d,b,a]");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(in, true);
        assertEquals(in2, true);
        assertEquals(in3, true);
        assertEquals(in4, true);
        assertEquals(lisev, false);
        assertEquals(listse, true);
    }

    /**************************************************************************************************************** 
     * TESTS DE listarPorNiveles()
     ****************************************************************************************************************/
    @Test public void listarPorNivelesArbolVacio() {
        ArbolGen ag = new ArbolGen();
        Lista lis = ag.listarPorNiveles();
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("");
        boolean lisev = lis.esVacia();
        String lists = lis.toString();
        boolean listse = lists.equals("[]");
        assertEquals(ev, true);
        assertEquals(tse, true);
        assertEquals(lisev, true);
        assertEquals(listse, true);
    }
    @Test public void listarPorNivelesSoloRaiz() {
        ArbolGen ag = new ArbolGen();
        boolean in = ag.insertar("a", null);
        Lista lis = ag.listarPorNiveles();
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:");
        boolean lisev = lis.esVacia();
        String lists = lis.toString();
        boolean listse = lists.equals("[a]");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(in, true);
        assertEquals(lisev, false);
        assertEquals(listse, true);
    }
    @Test public void listarPorNivelesConElementos() {
        ArbolGen ag = new ArbolGen();
        boolean in = ag.insertar("a", null);
        boolean in2 = ag.insertar("b", "a");
        boolean in3 = ag.insertar("c", "a");
        boolean in4 = ag.insertar("d", "b");
        Lista lis = ag.listarPorNiveles();
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:c,b\n"+ //
                                "c:\n"+ //
                                "b:d\n"+ //
                                "d:");
        boolean lisev = lis.esVacia();
        String lists = lis.toString();
        boolean listse = lists.equals("[a,c,b,d]");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(in, true);
        assertEquals(in2, true);
        assertEquals(in3, true);
        assertEquals(in4, true);
        assertEquals(lisev, false);
        assertEquals(listse, true);
    }

    /**************************************************************************************************************** 
     * TESTS DE toString()
     ****************************************************************************************************************/
    @Test public void toStringArbolVacio() {
        ArbolGen ag = new ArbolGen();
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("");
        assertEquals(ev, true);
        assertEquals(tse, true);
    }
    @Test public void toStringSoloRaiz() {
        ArbolGen ag = new ArbolGen();
        boolean in = ag.insertar("a", null);
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(in, true);
    }
    @Test public void toStringConElementos() {
        ArbolGen ag = new ArbolGen();
        boolean in = ag.insertar("a", null);
        boolean in2 = ag.insertar("b", "a");
        boolean in3 = ag.insertar("c", "a");
        boolean in4 = ag.insertar("d", "b");
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:c,b\n"+ //
                                "c:\n"+ //
                                "b:d\n"+ //
                                "d:");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(in, true);
        assertEquals(in2, true);
        assertEquals(in3, true);
        assertEquals(in4, true);
    }
}
