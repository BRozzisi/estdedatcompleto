package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import lineales.estaticas.Pila;

/**
 * <p>
 * Tests diseñados para realizar pruebas sobre una pila estática
 * </p>
 * 
 * @author Ochonga Franco - FAI 4638
 */

public class TestPilaEstaticaFO {

    // *****************************************************************************
    //
    // Pruebas sobre pila vacía
    //
    // *****************************************************************************
    @Test
    public void pruebaPilaVaciaEnteros() {
        Pila p = new Pila();

        boolean verificaToString = p.toString().equals("[]");
        boolean verificaVacia = p.esVacia();

        assertEquals(verificaToString, true);
        assertEquals(verificaVacia, true);
    }

    // *****************************************************************************
    //
    // Pruebas sobre clonado
    //
    // *****************************************************************************
    @Test
    public void pruebaClonadoPila() {
        Pila p = new Pila();

        // Llena la lista con elementos
        for (int i = 1; i <= 20; ++i) {
            p.apilar(i);
        }

        Pila clon = p.clone();

        int i = 0;
        boolean verificaIgualdad = true;
        while (i < 20 & verificaIgualdad) {

            if (clon.obtenerTope() != p.obtenerTope()) {
                verificaIgualdad = false;
            }
            ++i;

            p.desapilar();
            clon.desapilar();
        }

        assertEquals(verificaIgualdad, true);
    }

    @Test
    public void pruebaClonPilaVacia() {
        Pila p = new Pila();
        Pila clon = new Pila();

        assertEquals(p.esVacia() && clon.esVacia(), true);

    }

    // *****************************************************************************
    //
    // Pruebas sobre pila llena
    //
    // *****************************************************************************
    // OJO - Esto es sólo para pila estática.
    @Test
    public void insertarElementoPilaLlena() {
        final int MAX_ELEM = 20;
        Pila p = new Pila();
        for (int i = 1; i <= MAX_ELEM; ++i) {
            p.apilar(i);
        }
        assertEquals(p.apilar(21), false);
    }

    // *****************************************************************************
    //
    // Pruebas sobre la función apilar
    //
    // *****************************************************************************
    @Test
    public void insertarElementoPilaVacia() {
        Pila p = new Pila();
        assertEquals(p.apilar(1), true);
        assertEquals(p.obtenerTope(), 1);
        assertEquals(p.esVacia(), false);
    }

    // *****************************************************************************
    //
    // Pruebas sobre la función desapilar
    //
    // *****************************************************************************
    @Test
    public void desapilarUltimoElemento() {
        Pila p = new Pila();

        p.apilar(1);

        assertEquals(p.desapilar(), true);
        assertEquals(p.esVacia(), true);
        assertEquals(p.obtenerTope(), null);
    }

    @Test
    public void desapilarPilaVacia() {
        Pila p = new Pila();
        assertEquals(p.desapilar(), false);
    }

    public void desapilarPilaVariosElem() {
        Pila p = new Pila();

        p.apilar(3);
        p.apilar(2);
        p.apilar(1);

        assertEquals(p.desapilar(), true);
        assertEquals((int) p.obtenerTope(), 2);
        assertEquals(p.desapilar(), true);
        assertEquals(p.desapilar(), true);
        assertEquals((int) p.obtenerTope(), 1);
    }

    // *****************************************************************************
    //
    // Pruebas con la función vaciar
    //
    // *****************************************************************************
    @Test
    public void pruebaVaciarPilaVacia() {
        Pila p = new Pila();

        p.vaciar();

        assertEquals(p.esVacia(), true);
    }

    @Test
    public void pruebaVaciarPila() {
        Pila p = new Pila();

        for (int i = 1; i <= 10; ++i) {
            p.apilar(p);
        }
        p.vaciar();

        String contenido = p.toString();
        boolean verificaToString = contenido.equals("[]");

        boolean verificaVacia = p.esVacia();

        assertEquals(verificaToString, true);
        assertEquals(verificaVacia, true);
        assertEquals(p.obtenerTope(), null);
    }

    // *****************************************************************************
    //
    // Pruebas con el método toString
    //
    // *****************************************************************************
    @Test
    public void pruebaStringPilaVacia() {
        Pila p = new Pila();

        String contenido = p.toString();
        boolean verificaIgualdad = contenido.equals("[]");
        assertEquals(verificaIgualdad, true);

    }

    @Test
    public void pruebaStringPilaConElementos() {
        Pila p = new Pila();

        for (int i = 1; i <= 10; ++i) {
            p.apilar(i);
        }

        String contenido = p.toString();
        boolean verificaIgualdad = contenido.equals("Tope: [10,9,8,7,6,5,4,3,2,1]");
        assertEquals(verificaIgualdad, true);
    }

    // *****************************************************************************
    //
    // Pruebas con números capicúa
    //
    // *****************************************************************************

    /**
     * <p>
     * Determina si el número contenido en la pila es o no capicúa
     * </p>
     * 
     * @param pila Pila de entrada
     * @return true si la pila de entrada contiene un número capicúa, false en caso
     *         contrario
     */
    private boolean esCapicua(Pila pila) {
        // Para no alterar la pila original
        Pila clon = pila.clone();

        Pila clon2 = pila.clone();

        Pila aux = new Pila();

        while (!clon.esVacia()) {
            aux.apilar(clon.obtenerTope());
            clon.desapilar();
        }
        
        boolean bandera = true;

        while (!aux.esVacia() && bandera) {
            if (aux.obtenerTope() != clon2.obtenerTope()) {
                bandera = false;
            } else {
                aux.desapilar();
                clon2.desapilar();
            }
        }

        return bandera;
    }

    /**
     * <p>
     * Descompone un número en sus dígitos componentes
     * </p>
     * 
     * @param pila Pila de entrada
     * @param num  Número a descomponer
     */
    private void descomponerNumero(Pila pila, int num) {
        if (num == 0) {
            pila.apilar(num);
        } else {
            while (num != 0) {
                pila.apilar(num % 10);
                num /= 10;
            }
        }
    }

    @Test
    public void pruebasCapicua() {
        Pila capicua1 = new Pila();
        Pila capicua2 = new Pila();
        Pila capicua3 = new Pila();

        descomponerNumero(capicua1, 0);
        descomponerNumero(capicua2, 123321);
        descomponerNumero(capicua3, 1111111);

        assertEquals(esCapicua(capicua1), true);
        assertEquals(esCapicua(capicua2), true);
        assertEquals(esCapicua(capicua3), true);
        // assertEquals(esCapicua(capicua1), false);

        Pila noCapicua1 = new Pila();
        Pila noCapicua2 = new Pila();
        Pila noCapicua3 = new Pila();

        descomponerNumero(noCapicua1, 12);
        descomponerNumero(noCapicua2, 1234322);
        descomponerNumero(noCapicua3, 1010102);

        assertEquals(esCapicua(noCapicua1), false);
        assertEquals(esCapicua(noCapicua2), false);
        assertEquals(esCapicua(noCapicua3), false);

    }

    // *****************************************************************************
    //
    // Pruebas con cadenas
    //
    // *****************************************************************************
    @Test
    public void pruebaConCadenas() {
        Pila p = new Pila();

        assertEquals(p.apilar("jkl"), true);
        assertEquals(p.apilar("ghi"), true);
        assertEquals(p.apilar("def"), true);
        assertEquals(p.apilar("abc"), true);

        String tope = (String) p.obtenerTope();

        assertEquals(tope.equals("abc"), true);
        assertEquals(p.esVacia(), false);

        p.vaciar();
        assertEquals(p.esVacia(), true);
    }

}
