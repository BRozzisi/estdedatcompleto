package tests;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import static org.junit.Assert.*;
import org.junit.Test;
import lineales.dinamicas.Cola;

/* 
*
*
* @author Bruno Rozzisi - FAI-5892
*
*/

public class ColaTestPersonal {

    private static boolean isSubstring(String s, String rx){
        Pattern pattern = Pattern.compile(rx);
        Matcher matcher = pattern.matcher(s);
        boolean findSubstring = false;
        while (matcher.find()) {
            //System.out.println(s.substring(matcher.start(), matcher.end()));
            findSubstring = true;
        }
        return findSubstring;
    }

    @Test
    public void testCrearColaVacía() {
        Cola c = new Cola();
        boolean ev = c.esVacia();
        Object t = c.obtenerFrente();
        String s = c.toString();
        String rx = "\\[\\]";
        boolean findSubstring = isSubstring(s, rx);
        assertEquals(ev, true);
        assertEquals(t, null);
        assertEquals(findSubstring, true);
    }

    @Test
    public void testPonerPrimerElemento() {
        Cola c = new Cola();
        boolean po = c.poner(1);
        boolean ev = c.esVacia();
        Object t = c.obtenerFrente();
        String s = c.toString();
        String rx = "\\[1\\]";
        boolean findSubstring = isSubstring(s, rx);
        assertEquals(po, true);
        assertEquals(ev, false);
        assertEquals(t, 1);
        assertEquals(findSubstring, true);
    }

    @Test
    public void testPonerElementoEnColaNoVacia() {
        Cola c = new Cola();
        c.poner(1);
        c.poner(2);
        boolean po = c.poner(3);
        boolean ev = c.esVacia();
        Object t = c.obtenerFrente();
        String s = c.toString();
        String rx = "\\[1,2,3\\]";
        boolean findSubstring = isSubstring(s, rx);
        assertEquals(po, true);
        assertEquals(ev, false);
        assertEquals(t, 1);
        assertEquals(findSubstring, true);
    }

    @Test
    public void sacarConUnSoloElemento() {
        Cola c = new Cola();
        c.poner(1);
        c.sacar();
        boolean ev = c.esVacia();
        Object t = c.obtenerFrente();
        String s = c.toString();
        String rx = "\\[\\]";
        boolean findSubstring = isSubstring(s, rx);
        assertEquals(ev, true);
        assertEquals(t, null);
        assertEquals(findSubstring, true);
    }

    @Test
    public void sacarConMasDeUnElemento() {
        Cola c = new Cola();
        c.poner(1); c.poner(2); c.poner(3);
        boolean sac = c.sacar();
        boolean ev = c.esVacia();
        Object t = c.obtenerFrente();
        String s = c.toString();
        String rx = "\\[2,3\\]";
        boolean findSubstring = isSubstring(s, rx);
        assertEquals(sac,true);
        assertEquals(ev, false);
        assertEquals(t, 2);
        assertEquals(findSubstring, true);
    }

    @Test
    public void sacarEnColaVacia(){ 
        Cola c = new Cola();
        boolean sac = c.sacar();
        boolean ev = c.esVacia();
        Object t = c.obtenerFrente();
        String s = c.toString();
        String rx = "\\[\\]";
        boolean findSubstring = isSubstring(s, rx);
        assertEquals(sac,false);
        assertEquals(ev, true);
        assertEquals(t, null);
        assertEquals(findSubstring, true);
    }

    @Test
    public void clonarColaNoVacia() {
        Cola c = new Cola();
        c.poner(1); c.poner(2); c.poner(3);
        Cola cClone = c.clone();
        boolean ev = c.esVacia();
        boolean evClone = cClone.esVacia();
        Object t = c.obtenerFrente();
        Object tClone = cClone.obtenerFrente();
        String s = c.toString();
        String sClone = cClone.toString();
        String rx = "\\[1,2,3\\]";
        boolean findSubstring = isSubstring(s, rx);
        boolean findSubstringClone = isSubstring(sClone, rx);
        assertEquals(ev, false);
        assertEquals(evClone, false);
        assertEquals(t, 1);
        assertEquals(tClone, 1);
        assertEquals(findSubstring, true);
        assertEquals(findSubstringClone, true);
        assertNotEquals(cClone, c);
        assertEquals(s, sClone);
    }

    @Test
    public void clonarColaVacia() {
        Cola c = new Cola();
        Cola cClone = c.clone();
        boolean ev = c.esVacia();
        boolean evClone = cClone.esVacia();
        Object t = c.obtenerFrente();
        Object tClone = cClone.obtenerFrente();
        String s = c.toString();
        String sClone = cClone.toString();
        String rx = "\\[\\]";
        boolean findSubstring = isSubstring(s, rx);
        boolean findSubstringClone = isSubstring(sClone, rx);
        assertEquals(ev, true);
        assertEquals(evClone, true);
        assertEquals(t, null);
        assertEquals(tClone, null);
        assertEquals(findSubstring, true);
        assertEquals(findSubstringClone, true);
        assertNotEquals(cClone, c);
        assertEquals(s, sClone);
    }
}
