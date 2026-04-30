package aplicaciones;
import lineales.dinamicas.*;

// En la clase Matematica, que utiliza a los TDA Lista, Pila y Cola vistos en la materia para guardar elementos
// tipo CHAR que representan una expresión matemática, desarrollar el método verificarBalanceo (Cola q)
// que recibe por parámetro una cola con una expresión matemática y verifique que los paréntesis, corchetes y
// llaves estén correctamente balanceados. Debe usar como estructura auxiliar alguno de los TDA lineales
// vistos, el que considere más apropiado.
// Ejemplos: Si q es ← { 5 + [ 8 * 9 -( 4 / 2 ) + 7 ] -1 } ← el método debe devolver TRUE
// Si q es ← { 5 + 8 * 9 -( 4 / 2 ) + 7 ] -1 } ← el método debe devolver FALSE
public class SimulacroPrimerParcial {
    public static Cola generar(Cola c1) {
        Cola cr = new Cola();
        if (!c1.esVacia()) {
            Pila p1 = new Pila();
            Pila p2 = new Pila();
            Cola c1Copia = c1.clone();
            char aux = (Character) c1Copia.obtenerFrente();
            while (!c1Copia.esVacia()) {
                if (aux != '#') {
                    cr.poner(aux);
                    p1.apilar(aux);
                } else {
                    while (!p1.esVacia()) {
                        aux = (Character) p1.obtenerTope();
                        p2.apilar(aux);
                        cr.poner(aux);
                        p1.desapilar();
                    }
                    while (!p2.esVacia()) {
                        aux = (Character) p2.obtenerTope();
                        cr.poner(aux);
                        p2.desapilar();
                    }
                    cr.poner('#');
                }
                c1Copia.sacar();
                if (c1Copia.obtenerFrente() != null) {
                    aux = (Character) c1Copia.obtenerFrente();
                }
            }
            while (!p1.esVacia()) {
                aux = (Character) p1.obtenerTope();
                p2.apilar(aux);
                cr.poner(aux);
                p1.desapilar();
            }
            while (!p2.esVacia()) {
                aux = (Character) p2.obtenerTope();
                cr.poner(aux);
                p2.desapilar();
            }
        }
        return cr;
    }
    
    public static Boolean verificarBalanceo(Cola q) {
        Boolean estaBalanceado = true;
        Cola qaux = q.clone();
        int contLlaves = 0;
        int contCorchetes = 0;
        int contParentesis = 0;
        int i = 1;
        while (q.obtenerFrente() != null){
            Character frente = (Character) q.obtenerFrente(); 
            switch (frente) {
                case '{':
                    contLlaves++;
                    break;
                case '}':
                    contLlaves--;
                    break;
                case '[':
                    contCorchetes++;
                    break;
                case ']':
                    contCorchetes--;
                    break;
                case '(':
                    contParentesis++;
                    break;
                case ')':
                    contParentesis--;
                    break;
            }
            q.sacar();
        }
        if ((contLlaves != 0) || (contCorchetes != 0) || (contParentesis != 0)) {
            estaBalanceado = false;
        }
        return estaBalanceado;
    }
    public static void main (String[] args) {
        String ec1 = "{5+[8*9-(4/2)+7]-1}";
        String ec2 = "{5+8*9-(4/2)+7]-1}";
        Cola q1 = new Cola();
        Cola q2 = new Cola();

        for (int i=0; i<19; i++) {
            char car = ec1.charAt(i);
            q1.poner(car);
        }
        System.out.println(q1);

        for (int i=0; i<18; i++) {
            char car = ec2.charAt(i);
            q2.poner(car);
        }

        System.out.println(verificarBalanceo(q1));
        System.out.println(verificarBalanceo(q2));
    }
}
