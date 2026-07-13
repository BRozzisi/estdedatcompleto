package tests;

import conjuntistas.ArbolAVL;
import lineales.dinamicas.Lista;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArbolAVLTest {
    /*************************************************************
     * TESTS DE ArbolAVL()
     *************************************************************/
    @Test
    public void crearArbol() {
        ArbolAVL a = new ArbolAVL();
        String ts = a.toString();
        assertTrue(a.esVacio());
        assertTrue(ts.equals("[arbol vacio]"));
    }

    /*************************************************************
     * TESTS DE insertar(Object)
     *************************************************************/
    @Test
    public void insertarEnVacio() {
        ArbolAVL a = new ArbolAVL();
        boolean ex = a.insertar(10);
        String ts = a.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 10\n" +
                "10: HI: null // HD: null\n"));
        assertTrue(ex);
    }

    @Test
    public void insertarInvalido() {
        ArbolAVL a = new ArbolAVL();
        boolean ex = a.insertar(10);
        boolean ex2 = a.insertar(10);
        String ts = a.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 10\n" +
                "10: HI: null // HD: null\n"));
        assertTrue(ex);
        assertFalse(ex2);
    }

    @Test
    public void insertarRequiereRotIzqEnRaiz() {
        ArbolAVL a = new ArbolAVL();
        boolean ex = a.insertar(10);
        boolean ex2 = a.insertar(20);
        boolean ex3 = a.insertar(30);
        String ts = a.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 20\n" +
                "20: HI: 10 // HD: 30\n" +
                "10: HI: null // HD: null\n" +
                "30: HI: null // HD: null\n"));
        assertTrue(ex);
        assertTrue(ex2);
        assertTrue(ex3);
    }

    @Test
    public void insertarRequiereRotDerEnRaiz() {
        ArbolAVL a = new ArbolAVL();
        boolean ex = a.insertar(10);
        boolean ex2 = a.insertar(8);
        boolean ex3 = a.insertar(7);
        String ts = a.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 8\n" +
                "8: HI: 7 // HD: 10\n" +
                "7: HI: null // HD: null\n" +
                "10: HI: null // HD: null\n"));
        assertTrue(ex);
        assertTrue(ex2);
        assertTrue(ex3);
    }

    @Test
    public void insertarRequiereRotDerIzqEnRaiz() {
        ArbolAVL a = new ArbolAVL();
        boolean ex = a.insertar(10);
        boolean ex2 = a.insertar(20);
        boolean ex3 = a.insertar(15);
        String ts = a.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 15\n" +
                "15: HI: 10 // HD: 20\n" +
                "10: HI: null // HD: null\n" +
                "20: HI: null // HD: null\n"));
        assertTrue(ex);
        assertTrue(ex2);
        assertTrue(ex3);
    }

    @Test
    public void insertarRequiereRotIzqDerEnRaiz() {
        ArbolAVL a = new ArbolAVL();
        boolean ex = a.insertar(10);
        boolean ex2 = a.insertar(8);
        boolean ex3 = a.insertar(9);
        String ts = a.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 9\n" +
                "9: HI: 8 // HD: 10\n" +
                "8: HI: null // HD: null\n" +
                "10: HI: null // HD: null\n"));
        assertTrue(ex);
        assertTrue(ex2);
        assertTrue(ex3);
    }

    @Test
    public void insertarRequiereRotDer() {
        ArbolAVL a = new ArbolAVL();
        boolean ex = a.insertar(10);
        boolean ex2 = a.insertar(6);
        boolean ex3 = a.insertar(20);
        boolean ex4 = a.insertar(3);
        boolean ex5 = a.insertar(15);
        boolean ex6 = a.insertar(30);
        boolean ex7 = a.insertar(2);
        String ts = a.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 10\n" +
                "10: HI: 3 // HD: 20\n" +
                "3: HI: 2 // HD: 6\n" +
                "2: HI: null // HD: null\n" +
                "6: HI: null // HD: null\n" +
                "20: HI: 15 // HD: 30\n" +
                "15: HI: null // HD: null\n" +
                "30: HI: null // HD: null\n"));
        assertTrue(ex);
        assertTrue(ex2);
        assertTrue(ex3);
        assertTrue(ex4);
        assertTrue(ex5);
        assertTrue(ex6);
        assertTrue(ex7);
    }

    @Test
    public void insertarRequiereRotIzq() {
        ArbolAVL a = new ArbolAVL();
        boolean ex = a.insertar(10);
        boolean ex2 = a.insertar(3);
        boolean ex3 = a.insertar(20);
        boolean ex4 = a.insertar(2);
        boolean ex5 = a.insertar(6);
        boolean ex6 = a.insertar(30);
        boolean ex7 = a.insertar(40);
        String ts = a.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 10\n" +
                "10: HI: 3 // HD: 30\n" +
                "3: HI: 2 // HD: 6\n" +
                "2: HI: null // HD: null\n" +
                "6: HI: null // HD: null\n" +
                "30: HI: 20 // HD: 40\n" +
                "20: HI: null // HD: null\n" +
                "40: HI: null // HD: null\n"));
        assertTrue(ex);
        assertTrue(ex2);
        assertTrue(ex3);
        assertTrue(ex4);
        assertTrue(ex5);
        assertTrue(ex6);
        assertTrue(ex7);
    }

    @Test
    public void insertarRequiereRotIzqDer() {
        ArbolAVL a = new ArbolAVL();
        boolean ex = a.insertar(10);
        boolean ex2 = a.insertar(6);
        boolean ex3 = a.insertar(20);
        boolean ex4 = a.insertar(3);
        boolean ex5 = a.insertar(15);
        boolean ex6 = a.insertar(30);
        boolean ex7 = a.insertar(4);
        String ts = a.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 10\n" +
                "10: HI: 4 // HD: 20\n" +
                "4: HI: 3 // HD: 6\n" +
                "3: HI: null // HD: null\n" +
                "6: HI: null // HD: null\n" +
                "20: HI: 15 // HD: 30\n" +
                "15: HI: null // HD: null\n" +
                "30: HI: null // HD: null\n"));
        assertTrue(ex);
        assertTrue(ex2);
        assertTrue(ex3);
        assertTrue(ex4);
        assertTrue(ex5);
        assertTrue(ex6);
        assertTrue(ex7);
    }

    @Test
    public void insertarRequiereRotDerIzq() {
        ArbolAVL a = new ArbolAVL();
        boolean ex = a.insertar(10);
        boolean ex2 = a.insertar(3);
        boolean ex3 = a.insertar(20);
        boolean ex4 = a.insertar(2);
        boolean ex5 = a.insertar(6);
        boolean ex6 = a.insertar(30);
        boolean ex7 = a.insertar(25);
        String ts = a.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 10\n" +
                "10: HI: 3 // HD: 25\n" +
                "3: HI: 2 // HD: 6\n" +
                "2: HI: null // HD: null\n" +
                "6: HI: null // HD: null\n" +
                "25: HI: 20 // HD: 30\n" +
                "20: HI: null // HD: null\n" +
                "30: HI: null // HD: null\n"));
        assertTrue(ex);
        assertTrue(ex2);
        assertTrue(ex3);
        assertTrue(ex4);
        assertTrue(ex5);
        assertTrue(ex6);
        assertTrue(ex7);
    }

    /*************************************************************
    * TESTS DE eliminar(Object)
    *************************************************************/
    @Test public void eliminarEnVacio() {
        ArbolAVL a = new ArbolAVL();
        boolean ex = a.eliminar(10);
        String ts = a.toString();
        assertTrue(a.esVacio());
        assertTrue(ts.equals("[arbol vacio]"));
        assertFalse(ex);
    }

    @Test public void eliminarRaiz() {
        ArbolAVL a = new ArbolAVL();
        boolean in = a.insertar(10);
        boolean el = a.eliminar(10);
        String ts = a.toString();
        assertTrue(a.esVacio());
        assertTrue(ts.equals("[arbol vacio]"));
        assertTrue(in);
        assertTrue(el);
    }

    @Test public void eliminarRaizConDosHijos() {
        ArbolAVL a = new ArbolAVL();
        boolean in = a.insertar(10);
        boolean in2 = a.insertar(5);
        boolean in3 = a.insertar(20);
        boolean el = a.eliminar(10);
        String ts = a.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 5\n"+
                            "5: HI: null // HD: 20\n"+
                            "20: HI: null // HD: null\n"
        ));
        assertTrue(in);
        assertTrue(in2);
        assertTrue(in3);
        assertTrue(el);
    }

    @Test public void eliminarInexistente() {
        ArbolAVL a = new ArbolAVL();
        boolean in = a.insertar(10);
        boolean in2 = a.insertar(5);
        boolean in3 = a.insertar(20);
        boolean el = a.eliminar(8);
        String ts = a.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 10\n"+
                            "10: HI: 5 // HD: 20\n"+
                            "5: HI: null // HD: null\n"+
                            "20: HI: null // HD: null\n"
        ));
        assertTrue(in);
        assertTrue(in2);
        assertTrue(in3);
        assertFalse(el);
    }

    @Test public void eliminarRequiereRotIzqRaiz() {
        ArbolAVL a = new ArbolAVL();
        boolean in = a.insertar(10);
        boolean in2 = a.insertar(5);
        boolean in3 = a.insertar(20);
        boolean in4 = a.insertar(30);
        boolean el = a.eliminar(10);
        String ts = a.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 20\n"+
                            "20: HI: 5 // HD: 30\n"+
                            "5: HI: null // HD: null\n"+
                            "30: HI: null // HD: null\n"
        ));
        assertTrue(in);
        assertTrue(in2);
        assertTrue(in3);
        assertTrue(in4);
        assertTrue(el);
    }

    @Test public void eliminarRequiereRotDerRaiz() {
        ArbolAVL a = new ArbolAVL();
        boolean in = a.insertar(10);
        boolean in2 = a.insertar(5);
        boolean in3 = a.insertar(20);
        boolean in4 = a.insertar(3);
        boolean in5 = a.insertar(8);
        boolean el = a.eliminar(20);
        String ts = a.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 5\n"+
                            "5: HI: 3 // HD: 10\n"+
                            "3: HI: null // HD: null\n"+
                            "10: HI: 8 // HD: null\n"+
                            "8: HI: null // HD: null\n"
        ));
        assertTrue(in);
        assertTrue(in2);
        assertTrue(in3);
        assertTrue(in4);
        assertTrue(in5);
        assertTrue(el);
    }

    @Test public void eliminarRequiereRotDerIzqRaiz() {
        ArbolAVL a = new ArbolAVL();
        boolean in = a.insertar(10);
        boolean in2 = a.insertar(5);
        boolean in3 = a.insertar(20);
        boolean in4 = a.insertar(15);
        boolean el = a.eliminar(10);
        String ts = a.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 15\n"+
                            "15: HI: 5 // HD: 20\n"+
                            "5: HI: null // HD: null\n"+
                            "20: HI: null // HD: null\n"
        ));
        assertTrue(in);
        assertTrue(in2);
        assertTrue(in3);
        assertTrue(in4);
        assertTrue(el);
    }

    @Test public void eliminarRequiereRotIzqDerRaiz() {
        ArbolAVL a = new ArbolAVL();
        boolean in = a.insertar(10);
        boolean in2 = a.insertar(5);
        boolean in3 = a.insertar(20);
        boolean in4 = a.insertar(8);
        boolean el = a.eliminar(20);
        String ts = a.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 8\n"+
                            "8: HI: 5 // HD: 10\n"+
                            "5: HI: null // HD: null\n"+
                            "10: HI: null // HD: null\n"
        ));
        assertTrue(in);
        assertTrue(in2);
        assertTrue(in3);
        assertTrue(in4);
        assertTrue(el);
    }

    @Test public void eliminarRequiereRotDer() {
        ArbolAVL a = new ArbolAVL();
        boolean in = a.insertar(10);
        boolean in2 = a.insertar(5);
        boolean in3 = a.insertar(20);
        boolean in4 = a.insertar(3);
        boolean in5 = a.insertar(8);
        boolean in6 = a.insertar(30);
        boolean in7 = a.insertar(1);
        boolean el = a.eliminar(8);
        String ts = a.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 10\n"+
                            "10: HI: 3 // HD: 20\n"+
                            "3: HI: 1 // HD: 5\n"+
                            "1: HI: null // HD: null\n"+
                            "5: HI: null // HD: null\n"+
                            "20: HI: null // HD: 30\n"+
                            "30: HI: null // HD: null\n"
        ));
        assertTrue(in);
        assertTrue(in2);
        assertTrue(in3);
        assertTrue(in4);
        assertTrue(in5);
        assertTrue(in6);
        assertTrue(in7);
        assertTrue(el);
    }

    @Test public void eliminarRequiereRotIzq() {
        ArbolAVL a = new ArbolAVL();
        boolean in = a.insertar(10);
        boolean in2 = a.insertar(5);
        boolean in3 = a.insertar(20);
        boolean in4 = a.insertar(3);
        boolean in5 = a.insertar(15);
        boolean in6 = a.insertar(30);
        boolean in7 = a.insertar(40);
        boolean el = a.eliminar(15);
        String ts = a.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 10\n"+
                            "10: HI: 5 // HD: 30\n"+
                            "5: HI: 3 // HD: null\n"+
                            "3: HI: null // HD: null\n"+
                            "30: HI: 20 // HD: 40\n"+
                            "20: HI: null // HD: null\n"+
                            "40: HI: null // HD: null\n"
        ));
        assertTrue(in);
        assertTrue(in2);
        assertTrue(in3);
        assertTrue(in4);
        assertTrue(in5);
        assertTrue(in6);
        assertTrue(in7);
        assertTrue(el);
    }

    @Test public void eliminarRequiereRotIzqDer() {
        ArbolAVL a = new ArbolAVL();
        boolean in = a.insertar(10);
        boolean in2 = a.insertar(5);
        boolean in3 = a.insertar(20);
        boolean in4 = a.insertar(3);
        boolean in5 = a.insertar(8);
        boolean in6 = a.insertar(30);
        boolean in7 = a.insertar(4);
        boolean el = a.eliminar(8);
        String ts = a.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 10\n"+
                            "10: HI: 4 // HD: 20\n"+
                            "4: HI: 3 // HD: 5\n"+
                            "3: HI: null // HD: null\n"+
                            "5: HI: null // HD: null\n"+
                            "20: HI: null // HD: 30\n"+
                            "30: HI: null // HD: null\n"
        ));
        assertTrue(in);
        assertTrue(in2);
        assertTrue(in3);
        assertTrue(in4);
        assertTrue(in5);
        assertTrue(in6);
        assertTrue(in7);
        assertTrue(el);
    }

    @Test public void eliminarRequiereRotDerIzq() {
        ArbolAVL a = new ArbolAVL();
        boolean in = a.insertar(10);
        boolean in2 = a.insertar(5);
        boolean in3 = a.insertar(20);
        boolean in4 = a.insertar(3);
        boolean in5 = a.insertar(15);
        boolean in6 = a.insertar(30);
        boolean in7 = a.insertar(25);
        boolean el = a.eliminar(15);
        String ts = a.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 10\n"+
                            "10: HI: 5 // HD: 25\n"+
                            "5: HI: 3 // HD: null\n"+
                            "3: HI: null // HD: null\n"+
                            "25: HI: 20 // HD: 30\n"+
                            "20: HI: null // HD: null\n"+
                            "30: HI: null // HD: null\n"
        ));
        assertTrue(in);
        assertTrue(in2);
        assertTrue(in3);
        assertTrue(in4);
        assertTrue(in5);
        assertTrue(in6);
        assertTrue(in7);
        assertTrue(el);
    }

    /*************************************************************
    * TESTS DE pertenece(Object)
    *************************************************************/
    @Test public void perteneceEnVacio() {
        ArbolAVL a = new ArbolAVL();
        boolean pe = a.pertenece(10);
        String ts = a.toString();
        assertTrue(a.esVacio());
        assertTrue(ts.equals("[arbol vacio]"));
        assertFalse(pe);
    }

    @Test public void perteneceSoloRaiz() {
        ArbolAVL a = new ArbolAVL();
        boolean in = a.insertar(10);
        boolean pe = a.pertenece(10);
        String ts = a.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 10\n"+
                            "10: HI: null // HD: null\n"
        ));
        assertTrue(pe);
        assertTrue(in);
    }

    @Test public void perteneceHoja() {
        ArbolAVL a = new ArbolAVL();
        boolean in = a.insertar(10);
        boolean in2 = a.insertar(5);
        boolean in3 = a.insertar(20);
        boolean in4 = a.insertar(8);
        boolean pe = a.pertenece(8);
        String ts = a.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 10\n"+
                            "10: HI: 5 // HD: 20\n"+
                            "5: HI: null // HD: 8\n"+
                            "8: HI: null // HD: null\n"+
                            "20: HI: null // HD: null\n"
        ));
        assertTrue(pe);
        assertTrue(in);
        assertTrue(in2);
        assertTrue(in3);
        assertTrue(in4);
    }

    @Test public void perteneceRama() {
        ArbolAVL a = new ArbolAVL();
        boolean in = a.insertar(10);
        boolean in2 = a.insertar(5);
        boolean in3 = a.insertar(20);
        boolean in4 = a.insertar(8);
        boolean pe = a.pertenece(5);
        String ts = a.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 10\n"+
                            "10: HI: 5 // HD: 20\n"+
                            "5: HI: null // HD: 8\n"+
                            "8: HI: null // HD: null\n"+
                            "20: HI: null // HD: null\n"
        ));
        assertTrue(pe);
        assertTrue(in);
        assertTrue(in2);
        assertTrue(in3);
        assertTrue(in4);
    }

    @Test public void perteneceInexistente() {
        ArbolAVL a = new ArbolAVL();
        boolean in = a.insertar(10);
        boolean in2 = a.insertar(5);
        boolean in3 = a.insertar(20);
        boolean in4 = a.insertar(8);
        boolean pe = a.pertenece(100);
        String ts = a.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 10\n"+
                            "10: HI: 5 // HD: 20\n"+
                            "5: HI: null // HD: 8\n"+
                            "8: HI: null // HD: null\n"+
                            "20: HI: null // HD: null\n"
        ));
        assertFalse(pe);
        assertTrue(in);
        assertTrue(in2);
        assertTrue(in3);
        assertTrue(in4);
    }

    /*************************************************************
    * TESTS DE listar()
    *************************************************************/
    @Test public void listarVacio() {
        ArbolAVL a = new ArbolAVL();
        Lista l = a.listar();
        String ts = a.toString();
        String lts = l.toString();
        assertTrue(a.esVacio());
        assertTrue(ts.equals("[arbol vacio]"));
        assertTrue(l.esVacia());
        assertTrue(lts.equals("[]"));
    }

    @Test public void listarSoloRaiz() {
        ArbolAVL a = new ArbolAVL();
        boolean in = a.insertar(10);
        Lista l = a.listar();
        String ts = a.toString();
        String lts = l.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 10\n"+
                            "10: HI: null // HD: null\n"
        ));
        assertFalse(l.esVacia());
        assertTrue(lts.equals("[10]"));
        assertTrue(in);
    }

    @Test public void testListar() {
        ArbolAVL a = new ArbolAVL();
        boolean in = a.insertar(10);
        boolean in2 = a.insertar(5);
        boolean in3 = a.insertar(20);
        boolean in4 = a.insertar(2);
        boolean in5 = a.insertar(8);
        boolean in6 = a.insertar(12);
        boolean in7 = a.insertar(25);
        Lista l = a.listar();
        String ts = a.toString();
        String lts = l.toString();
        assertFalse(a.esVacio());
        assertTrue(ts.equals("Raiz: 10\n"+
                            "10: HI: 5 // HD: 20\n"+
                            "5: HI: 2 // HD: 8\n"+
                            "2: HI: null // HD: null\n"+
                            "8: HI: null // HD: null\n"+
                            "20: HI: 12 // HD: 25\n"+
                            "12: HI: null // HD: null\n"+
                            "25: HI: null // HD: null\n"
        ));
        assertFalse(l.esVacia());
        assertTrue(lts.equals("[2,5,8,10,12,20,25]"));
        assertTrue(in);
        assertTrue(in2);
        assertTrue(in3);
        assertTrue(in4);
        assertTrue(in5);
        assertTrue(in6);
        assertTrue(in7);
    }
}
