package tests;

import lineales.dinamicas.Lista;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListaTestPersonal {

    // *************************************************************************************
    // TESTS DE CONSTRUCCIÓN.
    // *************************************************************************************
    // */
    @Test
    public void testCrearLista() {
        Lista l = new Lista();
        boolean ev = l.esVacia();
        String ts = l.toString();
        boolean tse = ts.equals("[]");
        Object t = l.recuperar(1);
        assertEquals(ev, true);
        assertEquals(tse, true);
        assertEquals(t, null);
    }

    // ************************************************************************************
    // */
    // TESTS DE INSERCION
    // ************************************************************************************
    // */
    @Test
    public void testInsertarListaVacia() {
        Lista l = new Lista();
        boolean ins = l.insertar(1, 1);
        boolean ev = l.esVacia();
        String ts = l.toString();
        boolean tse = ts.equals("[1]");
        Object t = l.recuperar(1);
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(t, 1);
        assertEquals(ins, true);
    }

    @Test
    public void testInsertarPosicionCero() {
        Lista l = new Lista();
        boolean ins = l.insertar(1, 0);
        boolean ev = l.esVacia();
        String ts = l.toString();
        boolean tse = ts.equals("[]");
        Object t = l.recuperar(1);
        assertEquals(ev, true);
        assertEquals(tse, true);
        assertEquals(t, null);
        assertEquals(ins, false);
    }

    @Test
    public void testInsertarPosicionInvalida() {
        Lista l = new Lista();
        l.insertar(1, 1);
        boolean ins2 = l.insertar(2, 3);
        boolean ev = l.esVacia();
        String ts = l.toString();
        boolean tse = ts.equals("[1]");
        Object t = l.recuperar(1);
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(t, 1);
        assertEquals(ins2, false);
    }

    @Test
    public void testInsertarMultiplesElementos() {
        Lista l = new Lista();
        boolean ins = l.insertar(1, 1);
        boolean ins2 = l.insertar(2, 2);
        boolean ins3 = l.insertar(3, 3);
        boolean ev = l.esVacia();
        String ts = l.toString();
        boolean tse = ts.equals("[1,2,3]");
        Object t = l.recuperar(1);
        Object t2 = l.recuperar(2);
        Object t3 = l.recuperar(3);
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(t, 1);
        assertEquals(t2, 2);
        assertEquals(t3, 3);
        assertEquals(ins, true);
        assertEquals(ins2, true);
        assertEquals(ins3, true);
    }

    @Test
    public void testInsertarEntreDosElementos() {
        Lista l = new Lista();
        boolean ins = l.insertar(1, 1);
        boolean ins2 = l.insertar(2, 2);
        boolean ins3 = l.insertar(4, 3);
        boolean ins4 = l.insertar(3, 3);
        boolean ev = l.esVacia();
        String ts = l.toString();
        boolean tse = ts.equals("[1,2,3,4]");
        Object t = l.recuperar(1);
        Object t2 = l.recuperar(2);
        Object t3 = l.recuperar(3);
        Object t4 = l.recuperar(4);
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(t, 1);
        assertEquals(t2, 2);
        assertEquals(t3, 3);
        assertEquals(t4, 4);
        assertEquals(ins, true);
        assertEquals(ins2, true);
        assertEquals(ins3, true);
        assertEquals(ins4, true);
    }

    /************************************************************************ */
    // TESTS DE LONGITUD
    // *********************************************************************** */
    @Test
    public void testLongitudVacia() {
        Lista l = new Lista();
        boolean ev = l.esVacia();
        String ts = l.toString();
        int length = l.longitud();
        boolean tse = ts.equals("[]");
        Object t = l.recuperar(1);
        assertEquals(ev, true);
        assertEquals(tse, true);
        assertEquals(t, null);
        assertEquals(length, 0);
    }

    @Test
    public void testLongitudConElementos() {
        Lista l = new Lista();
        l.insertar(1, 1);
        l.insertar(2, 2);
        l.insertar(3, 3);
        boolean ev = l.esVacia();
        String ts = l.toString();
        int length = l.longitud();
        boolean tse = ts.equals("[1,2,3]");
        Object t = l.recuperar(1);
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(t, 1);
        assertEquals(length, 3);
    }

    /************************************************************************ */
    // TESTS DE ELIMINAR
    /************************************************************************ */
    @Test
    public void testEliminarListaVacia() {
        Lista l = new Lista();
        boolean elim = l.eliminar(1);
        boolean ev = l.esVacia();
        String ts = l.toString();
        boolean tse = ts.equals("[]");
        Object t = l.recuperar(1);
        assertEquals(ev, true);
        assertEquals(tse, true);
        assertEquals(t, null);
        assertEquals(elim, false);
    }

    @Test
    public void testEliminarCabeceraSinEnlace() {
        Lista l = new Lista();
        l.insertar(1, 1);
        boolean elim = l.eliminar(1);
        boolean ev = l.esVacia();
        String ts = l.toString();
        boolean tse = ts.equals("[]");
        Object t = l.recuperar(1);
        assertEquals(ev, true);
        assertEquals(tse, true);
        assertEquals(t, null);
        assertEquals(elim, true);
    }

    @Test
    public void testEliminarCabeceraConEnlace() {
        Lista l = new Lista();
        l.insertar(1, 1);
        l.insertar(2, 2);
        boolean elim = l.eliminar(1);
        boolean ev = l.esVacia();
        String ts = l.toString();
        boolean tse = ts.equals("[2]");
        Object t = l.recuperar(1);
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(t, 2);
        assertEquals(elim, true);
    }

    @Test
    public void testEliminarElementoMedio() {
        Lista l = new Lista();
        l.insertar(1, 1);
        l.insertar(2, 2);
        l.insertar(3, 3);
        boolean elim = l.eliminar(2);
        boolean ev = l.esVacia();
        String ts = l.toString();
        boolean tse = ts.equals("[1,3]");
        Object t = l.recuperar(1);
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(t, 1);
        assertEquals(elim, true);
    }

    /************************************************************************ */
    // TESTS DE LOCALIZAR
    /************************************************************************ */
    @Test
    public void testLocalizarElementoNoExistente() {
        Lista l = new Lista();
        int pos = l.localizar(1);
        boolean ev = l.esVacia();
        String ts = l.toString();
        boolean tse = ts.equals("[]");
        Object t = l.recuperar(1);
        assertEquals(ev, true);
        assertEquals(tse, true);
        assertEquals(t, null);
        assertEquals(pos, -1);
    }

    @Test
    public void testLocalizarElementoInicio() {
        Lista l = new Lista();
        l.insertar(1, 1);
        l.insertar(2, 2);
        l.insertar(3, 3);
        int pos = l.localizar(1);
        boolean ev = l.esVacia();
        String ts = l.toString();
        boolean tse = ts.equals("[1,2,3]");
        Object t = l.recuperar(1);
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(t, 1);
        assertEquals(pos, 1);
    }

    @Test
    public void testLocalizarElementoMedio() {
        Lista l = new Lista();
        l.insertar(1, 1);
        l.insertar(2, 2);
        l.insertar(3, 3);
        int pos = l.localizar(2);
        boolean ev = l.esVacia();
        String ts = l.toString();
        boolean tse = ts.equals("[1,2,3]");
        Object t = l.recuperar(1);
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(t, 1);
        assertEquals(pos, 2);
    }

    @Test
    public void testLocalizarElementoFinal() {
        Lista l = new Lista();
        l.insertar(1, 1);
        l.insertar(2, 2);
        l.insertar(3, 3);
        int pos = l.localizar(3);
        boolean ev = l.esVacia();
        String ts = l.toString();
        boolean tse = ts.equals("[1,2,3]");
        Object t = l.recuperar(1);
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(t, 1);
        assertEquals(pos, 3);
    }

    @Test
    public void testLocalizarElementoDuplicado() {
        Lista l = new Lista();
        l.insertar(1, 1);
        l.insertar(2, 2);
        l.insertar(3, 3);
        l.insertar(2, 4);
        int pos = l.localizar(2);
        boolean ev = l.esVacia();
        String ts = l.toString();
        boolean tse = ts.equals("[1,2,3,2]");
        Object t = l.recuperar(1);
        assertEquals(ev, false);
        assertEquals(tse, true);
        assertEquals(t, 1);
        assertEquals(pos, 2);
    }

    /************************************************************************ */
    // TESTS DE VACIAR
    /************************************************************************ */
    @Test
    public void testVaciarVacia() {
        Lista l = new Lista();
        l.vaciar();
        boolean ev = l.esVacia();
        String ts = l.toString();
        boolean tse = ts.equals("[]");
        Object t = l.recuperar(1);
        assertEquals(ev, true);
        assertEquals(tse, true);
        assertEquals(t, null);
    }

    @Test
    public void testVaciarConElementos() {
        Lista l = new Lista();
        l.insertar(1, 1);
        l.insertar(2, 2);
        l.vaciar();
        boolean ev = l.esVacia();
        String ts = l.toString();
        boolean tse = ts.equals("[]");
        Object t = l.recuperar(1);
        assertEquals(ev, true);
        assertEquals(tse, true);
        assertEquals(t, null);
    }

    /**************************************************************************** */
    // TEST DE CLONAR
    /**************************************************************************** */
    @Test
    public void clonarVacia() {
        Lista l = new Lista();
        Lista lC = l.clone();
        boolean ev = l.esVacia();
        boolean evC = lC.esVacia();
        String ts = l.toString();
        String tsC = lC.toString();
        boolean tse = ts.equals("[]");
        Object t = l.recuperar(1);
        Object tC = lC.recuperar(1);
        assertEquals(ev, true);
        assertEquals(ev, evC);
        assertEquals(tse, true);
        assertEquals(ts, tsC);
        assertEquals(t, null);
        assertEquals(t, tC);
    }

    @Test
    public void clonarConElementos() {
        Lista l = new Lista();
        l.insertar(1, 1);
        l.insertar(2, 2);
        l.insertar(3, 3);
        Lista lC = l.clone();
        boolean ev = l.esVacia();
        boolean evC = lC.esVacia();
        String ts = l.toString();
        String tsC = lC.toString();
        boolean tse = ts.equals("[1,2,3]");
        Object t = l.recuperar(1);
        Object tC = lC.recuperar(1);
        assertEquals(ev, false);
        assertEquals(ev, evC);
        assertEquals(tse, true);
        assertEquals(ts, tsC);
        assertEquals(t, 1);
        assertEquals(t, tC);
    }
}
