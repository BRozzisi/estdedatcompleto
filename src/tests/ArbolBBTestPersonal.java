package tests;

import conjuntistas.ArbolBB;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArbolBBTestPersonal {
    /*************************************************************
    * TESTS DE CONSTRUCTOR
    *************************************************************/
    @Test public void testConstructorVacio() {
        ArbolBB abb = new ArbolBB();

        boolean ev = abb.esVacio();
        String ts = abb.toString();
        boolean tse = ts.equals("[Arbol Binario de Busqueda vacío.]");
        assertEquals(true, ev);
        assertEquals(true, tse);
    }

    /*************************************************************
    * TESTS DE INSERTAR
    *************************************************************/
    @Test public void testInsertarIntEnVacio() {
        ArbolBB abb = new ArbolBB();

        boolean in = abb.insertar(2);

        boolean ev = abb.esVacio();
        String ts = abb.toString();
        boolean tse = ts.equals("Raiz: 2\n"+
                                "2: HI: null // HD: null\n");
        System.out.println(ts);
        assertEquals(false, ev);
        assertEquals(true, tse);

        assertEquals(true, in);
    }

    @Test public void testInsertarStringEnVacio() {
        ArbolBB abb = new ArbolBB();

        boolean in = abb.insertar("E");

        boolean ev = abb.esVacio();
        String ts = abb.toString();
        boolean tse = ts.equals("Raiz: E\n"+
                                "E: HI: null // HD: null\n");
        assertEquals(false, ev);
        assertEquals(true, tse);

        assertEquals(true, in);
    }

    @Test public void testInsertarIntMayorYMenor() {
        ArbolBB abb = new ArbolBB();

        boolean in = abb.insertar(10);
        boolean in1 = abb.insertar(5);
        boolean in2 = abb.insertar(20);

        boolean ev = abb.esVacio();
        String ts = abb.toString();
        boolean tse = ts.equals("Raiz: 10\n"+
                                "10: HI: 5 // HD: 20\n"+
                                "5: HI: null // HD: null\n"+
                                "20: HI: null // HD: null\n");
        assertEquals(false, ev);
        assertEquals(true, tse);

        assertEquals(true, in);
        assertEquals(true, in1);
        assertEquals(true, in2);
    }

    @Test public void testInsertarStringMayorYMenor() {
        ArbolBB abb = new ArbolBB();

        boolean in = abb.insertar("E");
        boolean in1 = abb.insertar("A");
        boolean in2 = abb.insertar("R");

        boolean ev = abb.esVacio();
        String ts = abb.toString();
        boolean tse = ts.equals("Raiz: E\n"+
                                "E: HI: A // HD: R\n"+
                                "A: HI: null // HD: null\n"+
                                "R: HI: null // HD: null\n");
        assertEquals(false, ev);
        assertEquals(true, tse);

        assertEquals(true, in);
        assertEquals(true, in1);
        assertEquals(true, in2);
    }

    @Test public void testInsertarIntRepetido() {
        ArbolBB abb = new ArbolBB();

        boolean in = abb.insertar(1);
        boolean in1 = abb.insertar(1);

        boolean ev = abb.esVacio();
        String ts = abb.toString();
        boolean tse = ts.equals("Raiz: 1\n"+"1: HI: null // HD: null\n");
        assertEquals(false, ev);
        assertEquals(true, tse);

        assertEquals(true, in);
        assertEquals(false, in1);
    }

    @Test public void testInsertarStringRepetido() {
        ArbolBB abb = new ArbolBB();

        boolean in = abb.insertar("A");
        boolean in1 = abb.insertar("A");

        boolean ev = abb.esVacio();
        String ts = abb.toString();
        boolean tse = ts.equals("Raiz: A\n"+
                                "A: HI: null // HD: null\n");
        assertEquals(false, ev);
        assertEquals(true, tse);

        assertEquals(true, in);
        assertEquals(false, in1);
    }

    /*************************************************************
    * TESTS DE ELIMINAR
    *************************************************************/
    @Test public void eliminarEnArbolVacio() {
        ArbolBB abb = new ArbolBB();

        boolean del = abb.eliminar(1);

        boolean ev = abb.esVacio();
        String ts = abb.toString();
        boolean tse = ts.equals("[Arbol Binario de Busqueda vacío.]");
        assertEquals(true, ev);
        assertEquals(true, tse);

        assertEquals(false, del);
    }

    @Test public void eliminarRaizHoja() {
        ArbolBB abb = new ArbolBB();

        boolean in = abb.insertar(1);

        boolean del = abb.eliminar(1);

        boolean ev = abb.esVacio();
        String ts = abb.toString();
        boolean tse = ts.equals("[Arbol Binario de Busqueda vacío.]");
        assertEquals(true, ev);
        assertEquals(true, tse);

        assertEquals(true, in);
        assertEquals(true, del);
    }

    @Test public void eliminarHoja() {
        ArbolBB abb = new ArbolBB();

        boolean in = abb.insertar(1);
        boolean in1 = abb.insertar(2);
        boolean in2  = abb.insertar(0);

        boolean del = abb.eliminar(2);

        boolean ev = abb.esVacio();
        String ts = abb.toString();
        boolean tse = ts.equals("Raiz: 1\n"+
                                "1: HI: 0 // HD: null\n"+
                                "0: HI: null // HD: null\n");
        assertEquals(false, ev);
        assertEquals(true, tse);

        assertEquals(true, in);
        assertEquals(true, in1);
        assertEquals(true, in2);
        assertEquals(true, del);
    }

    @Test public void eliminarRamaConUnHijoIzquierdo() {
        ArbolBB abb = new ArbolBB();

        boolean in = abb.insertar(10);
        boolean in1 = abb.insertar(20);
        boolean in2  = abb.insertar(5);
        boolean in3 = abb.insertar(4);

        boolean del = abb.eliminar(5);

        boolean ev = abb.esVacio();
        String ts = abb.toString();
        boolean tse = ts.equals("Raiz: 10\n"+
                                "10: HI: 4 // HD: 20\n"+
                                "4: HI: null // HD: null\n"+
                                "20: HI: null // HD: null\n");
        assertEquals(false, ev);
        assertEquals(true, tse);

        assertEquals(true, in);
        assertEquals(true, in1);
        assertEquals(true, in2);
        assertEquals(true, in3);
        assertEquals(true, del);
    }


    @Test public void testEliminarRamaConHijoIzquierdo() {
        ArbolBB abb = new ArbolBB();

        boolean in = abb.insertar(10);
        boolean in1 = abb.insertar(20);
        boolean in2  = abb.insertar(5);
        boolean in3 = abb.insertar(6);

        boolean del = abb.eliminar(5);

        boolean ev = abb.esVacio();
        String ts = abb.toString();
        boolean tse = ts.equals("Raiz: 10\n"+
                                "10: HI: 6 // HD: 20\n"+
                                "6: HI: null // HD: null\n"+
                                "20: HI: null // HD: null\n");
        assertEquals(false, ev);
        assertEquals(true, tse);

        assertEquals(true, in);
        assertEquals(true, in1);
        assertEquals(true, in2);
        assertEquals(true, in3);
        assertEquals(true, del);
    }

    @Test public void testEliminarRamaConDosHijos() {
        ArbolBB abb = new ArbolBB();

        boolean in = abb.insertar(10);
        boolean in1 = abb.insertar(20);
        boolean in2  = abb.insertar(5);
        boolean in3 = abb.insertar(8);
        boolean in4 = abb.insertar(4);
        boolean in5  = abb.insertar(6);

        boolean del = abb.eliminar(5);

        boolean ev = abb.esVacio();
        String ts = abb.toString();
        boolean tse = ts.equals("Raiz: 10\n"+
                                "10: HI: 4 // HD: 20\n"+
                                "4: HI: null // HD: 8\n"+
                                "8: HI: 6 // HD: null\n"+
                                "6: HI: null // HD: null\n"+
                                "20: HI: null // HD: null\n");
        assertEquals(false, ev);
        assertEquals(true, tse);

        assertEquals(true, in);
        assertEquals(true, in1);
        assertEquals(true, in2);
        assertEquals(true, in3);
        assertEquals(true, in4);
        assertEquals(true, in5);
        assertEquals(true, del);
    }

    @Test public void eliminarElemInexistente() {
        ArbolBB abb = new ArbolBB();

        boolean in = abb.insertar(10);
        boolean in1 = abb.insertar(20);
        boolean in2  = abb.insertar(5);

        boolean del = abb.eliminar(-5);

        boolean ev = abb.esVacio();
        String ts = abb.toString();
        boolean tse = ts.equals("Raiz: 10\n"+
                                "10: HI: 5 // HD: 20\n"+
                                "5: HI: null // HD: null\n"+
                                "20: HI: null // HD: null\n");
        assertEquals(false, ev);
        assertEquals(true, tse);

        assertEquals(true, in);
        assertEquals(true, in1);
        assertEquals(true, in2);
        assertEquals(false, del);
    }

    /*************************************************************
    * TESTS DE PERTENECE
    *************************************************************/
    @Test public void testPerteneceEnVacio() {
        ArbolBB abb = new ArbolBB();

        boolean p = abb.pertenece(10);

        boolean ev = abb.esVacio();
        String ts = abb.toString();
        boolean tse = ts.equals("[Arbol Binario de Busqueda vacío.]");
        assertEquals(true, ev);
        assertEquals(true, tse);

        assertEquals(false, p);
    }

    @Test public void testPerteneceRaiz() {
        ArbolBB abb = new ArbolBB();

        boolean in = abb.insertar(10);
        boolean p = abb.pertenece(10);

        boolean ev = abb.esVacio();
        String ts = abb.toString();
        boolean tse = ts.equals("Raiz: 10\n"+
                                "10: HI: null // HD: null\n");
        assertEquals(false, ev);
        assertEquals(true, tse);

        assertEquals(true, in);
        assertEquals(true, p);
    }

    @Test public void perteneceHoja() {
        ArbolBB abb = new ArbolBB();

        boolean in = abb.insertar(10);
        boolean in2 = abb.insertar(5);
        boolean in3 = abb.insertar(20);
        boolean p = abb.pertenece(20);

        boolean ev = abb.esVacio();
        String ts = abb.toString();
        boolean tse = ts.equals("Raiz: 10\n"+
                                "10: HI: 5 // HD: 20\n"+
                                "5: HI: null // HD: null\n"+
                                "20: HI: null // HD: null\n");
        assertEquals(false, ev);
        assertEquals(true, tse);

        assertEquals(true, in);
        assertEquals(true, in2);
        assertEquals(true, in3);
        assertEquals(true, p);
    }

    @Test public void testNoPertenece() {
        ArbolBB abb = new ArbolBB();

        boolean in = abb.insertar(10);
        boolean in2 = abb.insertar(5);
        boolean in3 = abb.insertar(20);
        boolean p = abb.pertenece(30);

        boolean ev = abb.esVacio();
        String ts = abb.toString();
        boolean tse = ts.equals("Raiz: 10\n"+
                                "10: HI: 5 // HD: 20\n"+
                                "5: HI: null // HD: null\n"+
                                "20: HI: null // HD: null\n");
        assertEquals(false, ev);
        assertEquals(true, tse);

        assertEquals(true, in);
        assertEquals(true, in2);
        assertEquals(true, in3);
        assertEquals(false, p);
    }
}
