package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import lineales.estaticas.Cola;

/**
 * <p>
 * Tests diseñados para realizar pruebas sobre una cola estática
 * </p>
 * 
 * @author Ochonga Franco - FAI 4638
 */

public class TestColaEstaticaFO {
    // *****************************************************************************
    //
    // Métodos auxiliares
    //
    // *****************************************************************************

    /**
     * <p>
     * Método utilizado para llenar de forma predecible la cola
     * </p>
     * 
     * 
     * @param cola          Cola a llenar
     * @param cantElementos Número de elementos a insertar en la cola
     */
    private void llenarCola(Cola c, int cantElementos) {

        for (int i = 1; i <= cantElementos; ++i) {
            c.poner(i);
        }

    }

    // *****************************************************************************
    //
    // Pruebas inserción de elementos
    //
    // *****************************************************************************

    @Test
    public void pruebaInsertarColaVacia() {
        Cola c = new Cola();

        c.poner(1);

        assertEquals(c.obtenerFrente(), 1);
        assertEquals(c.esVacia(), false);

        assertEquals(c.toString().equals("Frente [1] Fin [null,null,null,null,null,null,null,null,null]"), true);

    }

    @Test
    public void pruebaInsertarColaNoVacia() {
        Cola c = new Cola();

        llenarCola(c, 5);

        assertEquals(c.poner(10), true);
        assertEquals(c.esVacia(), false);
    }

    // *****************************************************************************
    //
    // Pruebas sacar elementos
    //
    // *****************************************************************************

    @Test
    public void pruebaSacarColaVacia() {
        Cola c = new Cola();

        assertEquals(c.sacar(), false);
    }

    public void pruebaSacarColaNoVacia() {
        Cola c = new Cola();

        llenarCola(c, 3);

        assertEquals(c.sacar(), true);
        assertEquals(c.sacar(), true);
        assertEquals(c.sacar(), true);
        assertEquals(c.sacar(), false);

    }

    // *****************************************************************************
    //
    // Pruebas sobre obtenerFrente
    //
    // *****************************************************************************

    @Test
    public void pruebaObtenerFrente() {
        Cola c = new Cola();

        // Prueba con cola vacía
        assertEquals(c.obtenerFrente(), null);

        llenarCola(c, 5);

        // Prueba con cola con elementos
        assertEquals((int) c.obtenerFrente(), 1);
        c.sacar();

        assertEquals((int) c.obtenerFrente(), 2);
        c.sacar();

        assertEquals((int) c.obtenerFrente(), 3);
        c.sacar();

    }

    // *****************************************************************************
    //
    // Pruebas vaciar
    //
    // *****************************************************************************

    @Test
    public void pruebaVaciarColaVacia() {
        Cola c = new Cola();

        c.vaciar();
        assertEquals(c.esVacia(), true);
        assertEquals(c.toString().equals("[]"), true);
    }

    @Test
    public void pruebaVaciarColaConElementos() {
        Cola c = new Cola();

        llenarCola(c, 10);

        c.vaciar();

        assertEquals(c.esVacia(), true);
        assertEquals(c.toString().equals("[]"), true);
    }

    // *****************************************************************************
    //
    // Pruebas clonar
    //
    // *****************************************************************************

    @Test
    public void pruebaClonColaVacia() {
        Cola c = new Cola();
        Cola clon = c.clone();

        assertEquals(clon.toString().equals("[]"), true);
        assertEquals(c.obtenerFrente(), clon.obtenerFrente());
    }

    @Test
    public void pruebaClonColaConElementos() {
        Cola c = new Cola();

        llenarCola(c, 5);

        Cola clon = c.clone();

        String contenidoClon = clon.toString();
        String contenidoOriginal = c.toString();
        boolean verificaIgualdad = contenidoClon.equals(contenidoOriginal);

        assertEquals(verificaIgualdad, true);
        assertEquals(c.obtenerFrente(), clon.obtenerFrente());

    }



    // *****************************************************************************
    //
    // Pruebas toString()
    //
    // *****************************************************************************

    @Test
    public void pruebaToString() {
        Cola c = new Cola();

        assertEquals(c.toString().equals("[]"), true);

        llenarCola(c, 5);

        String contenido = c.toString();
        boolean verificaIgualdad = contenido.equals("Frente [1,2,3,4,5] Fin [null,null,null,null,null]");
        assertEquals(verificaIgualdad, true);
    }

}
