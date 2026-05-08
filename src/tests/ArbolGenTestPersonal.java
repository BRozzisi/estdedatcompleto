package tests;
import lineales.dinamicas.Lista;
import jerarquicas.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArbolGenTestPersonal {
    public static void comparacionString() {
        String ts = "";
        boolean tse = ts.equals("a -> d, c, b, \n"+ //
                                "d -> i, h, g, \n"+ //
                                "i -> o, n, \n"+ //
                                "o -> \n"+ //
                                "n -> \n"+ //
                                "h -> \n"+ //
                                "g -> m, \n"+ //
                                "m -> q, p, \n"+ //
                                "q -> \n"+ //
                                "p -> \n"+ //
                                "c -> \n"+ //
                                "b -> f, e, \n"+ //
                                "f -> l, k, j, \n"+ //
                                "l -> \n"+ //
                                "k -> \n"+ //
                                "j -> \n"+ //
                                "e -> r, \n"+ //
                                "r -> ");
    }
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
        boolean tse = ts.equals("[]");
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
        boolean tse = ts.equals("a -> ");
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
        boolean tse = ts.equals("a -> b, \n"+ //
                                "b -> ");
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(ex, true);
        assertEquals(ex2, true);
    }
    @Test public void insertarHermano() {
        
    }
