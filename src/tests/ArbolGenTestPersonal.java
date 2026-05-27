package tests;
import lineales.dinamicas.Lista;
import jerarquicas.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArbolGenTestPersonal {
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
    @Test public void insertarPPEnVacio() {
        ArbolGen ag = new ArbolGen();
        boolean ex = ag.insertarPorPosicion("a", 0);
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(ex, true);
    }
    @Test public void insertarPPEnRaizNegativa() {
        ArbolGen ag = new ArbolGen();
        boolean ex = ag.insertarPorPosicion("a", -1);
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("");
        assertEquals(ev, true);
        assertEquals(tse, true);
        assertEquals(ex, false);
    }
    @Test public void insertarPPEnRaizMayorACero() {
        ArbolGen ag = new ArbolGen();
        boolean ex = ag.insertarPorPosicion("a", 5);
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("");
        assertEquals(ev, true);
        assertEquals(tse, true);
        assertEquals(ex, false);
    }
    @Test public void insertarPPHijoUnico() {
        ArbolGen ag = new ArbolGen();
        boolean ex = ag.insertarPorPosicion("a", 0);
        boolean ex2 = ag.insertarPorPosicion("b", 1);
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:b\n"+ //
                                "b:");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(ex, true);
        assertEquals(ex2, true);
    }
    @Test public void insertarPPHermano() { 
        ArbolGen ag = new ArbolGen();
        boolean ex = ag.insertarPorPosicion("a", 0);
        boolean ex2 = ag.insertarPorPosicion("b", 1);
        boolean ex3 = ag.insertarPorPosicion("c", 1);
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
    @Test public void insertarPPEnPosicionInvalida() {
        ArbolGen ag = new ArbolGen();
        boolean ex = ag.insertarPorPosicion("a", 0);
        boolean ex2 = ag.insertarPorPosicion("b", 1);
        boolean ex3 = ag.insertarPorPosicion("c", 1);
        boolean ex4 = ag.insertarPorPosicion("z", 20);
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
    @Test public void ancestroEnArbolVacio() {
        ArbolGen ag = new ArbolGen();
        Lista la = ag.ancestros("a");
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("");
        boolean evla = la.esVacia();
        String tsla = la.toString();
        boolean tslae = tsla.equals("[]");
        assertEquals(ev, true);
        assertEquals(tse, true);
        assertEquals(evla, true);
        assertEquals(tslae, true);
    }
    @Test public void ancestroUnico() {
        ArbolGen ag = new ArbolGen();
        ag.insertar("a", null);
        Lista la = ag.ancestros("a");
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:");
        boolean evla = la.esVacia();
        String tsla = la.toString();
        boolean tslae = tsla.equals("[a]");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(evla, false);
        assertEquals(tslae, true);
    }
    @Test public void testAncestros() {
        ArbolGen ag = new ArbolGen();
        ag.insertar("a", null);
        ag.insertar("b", "a");
        ag.insertar("c", "a");
        ag.insertar("d", "b");
        Lista la = ag.ancestros("d");
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:c,b\n"+ //
                                "c:\n"+ //
                                "b:d\n"+ //
                                "d:");
        boolean evla = la.esVacia();
        String tsla = la.toString();
        boolean tslae = tsla.equals("[a,b,d]");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(evla, false);
        assertEquals(tslae, true);
    }
    @Test public void ancestroObjetoInexistente() {
        ArbolGen ag = new ArbolGen();
        ag.insertar("a", null);
        ag.insertar("b", "a");
        ag.insertar("c", "a");
        ag.insertar("d", "b");
        Lista la = ag.ancestros("z");
        boolean ev = ag.esVacio();
        String ts = ag.toString();
        boolean tse = ts.equals("a:c,b\n"+ //
                                "c:\n"+ //
                                "b:d\n"+ //
                                "d:");
        boolean evla = la.esVacia();
        String tsla = la.toString();
        boolean tslae = tsla.equals("[]");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(evla, true);
        assertEquals(tslae, true);
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
