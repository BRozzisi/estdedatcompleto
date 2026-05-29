package aplicaciones;

import lineales.dinamicas.*;


public class SimulacroPrimerParcial {
    public static Cola generar(Cola c1) {
        Cola colaNueva = new Cola();
        Pila pAux = new Pila();
        Cola cAux = new Cola();
        Cola c1Copia = c1.clone();
        char aux;
        while (!c1Copia.esVacia()) {
            aux = (Character) c1Copia.obtenerFrente();
            while ((!c1Copia.esVacia()) && (aux != '#')) {
                colaNueva.poner(aux);
                pAux.apilar(aux);
                cAux.poner(aux);
                c1Copia.sacar();
                if (!c1Copia.esVacia())
                    aux = (Character) c1Copia.obtenerFrente();
            }
            while (!pAux.esVacia()) {
                aux = (Character) pAux.obtenerTope();
                colaNueva.poner(aux);
                pAux.desapilar();
            }
            while (!cAux.esVacia()) {
                aux = (Character) cAux.obtenerFrente();
                colaNueva.poner(aux);
                cAux.sacar();
            }
            if (!c1Copia.esVacia()) {
                colaNueva.poner('#');
                c1Copia.sacar();
            }
        }
        return colaNueva;
    }

    public static Boolean verificarBalanceo(Cola q) {
        Boolean estaBalanceado = true;
        Cola qaux = q.clone();
        int contLlaves = 0;
        int contCorchetes = 0;
        int contParentesis = 0;
        while ((qaux.obtenerFrente() != null) && (contLlaves >= 0) && (contCorchetes >= 0) && (contParentesis >= 0)) {
            Character frente = (Character) qaux.obtenerFrente();
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
            qaux.sacar();
        }
        if ((contLlaves != 0) || (contCorchetes != 0) || (contParentesis != 0)) {
            estaBalanceado = false;
        }
        return estaBalanceado;
    }

    public static Lista generarLista(Cola q) {
        Lista listaGenerada = new Lista();
        Cola qClon = q.clone();
        Pila pAux = new Pila();
        Pila pAux2 = new Pila();
        Character aux;
        boolean indice = true;
        while (!qClon.esVacia()) {
            aux = (Character) qClon.obtenerFrente();
            if (indice) {
                while ((!qClon.esVacia()) && (aux != '#')) {
                    pAux.apilar(aux);
                    qClon.sacar();
                    if (!qClon.esVacia()) {
                        aux = (Character) qClon.obtenerFrente();
                    }
                }
                while (!pAux.esVacia()) {
                    aux = (Character) pAux.obtenerTope();
                    pAux2.apilar(aux);
                    pAux.desapilar();
                }
            } else {
                while ((!qClon.esVacia()) && (aux != '#')) {
                    pAux2.apilar(aux);
                    qClon.sacar();
                    if (!qClon.esVacia()) {
                        aux = (Character) qClon.obtenerFrente();
                    }
                }
            }
            if (!qClon.esVacia()) {
                pAux2.apilar('#');
            }
            qClon.sacar();
            indice = !indice;
        }
        while (!pAux2.esVacia()) {
            aux = (Character) pAux2.obtenerTope();
            listaGenerada.insertar(aux, 1);
            pAux2.desapilar();
        }
        return listaGenerada;
    }

    public static String testGenerarLista() {
        Cola q = new Cola();
        q.poner('A');
        q.poner('B');
        q.poner('C');
        q.poner('#');
        q.poner('A');
        q.poner('B');
        q.poner('C');
        q.poner('#');
        q.poner('A');
        q.poner('B');
        q.poner('C');
        q.poner('#');
        q.poner('A');
        q.poner('B');
        q.poner('C');
        q.poner('#');
        q.poner('A');
        q.poner('B');
        q.poner('C');
        q.poner('#');
        q.poner('A');

        Lista l = generarLista(q);

        return l.toString();
    }

    public static String testGenerar() {
        Cola c = new Cola();
        c.poner('A');
        c.poner('B');
        c.poner('#');
        c.poner('C');
        c.poner('D');
        c.poner('E');
        c.poner('#');
        c.poner('F');

        Cola colaGenerada = generar(c);

        return colaGenerada.toString();
    }

    public static void testBalanceo() {
        String ec1 = "{5+[8*9-(4/2)+7]-1}";
        String ec2 = "{5+8*9-(4/2)+7]-1}";
        Cola q1 = new Cola();
        Cola q2 = new Cola();

        for (int i = 0; i < 19; i++) {
            char car = ec1.charAt(i);
            q1.poner(car);
        }
        System.out.println(q1);

        for (int i = 0; i < 18; i++) {
            char car = ec2.charAt(i);
            q2.poner(car);
        }

        System.out.println(verificarBalanceo(q1));
        System.out.println(verificarBalanceo(q2));
    }

    public static String testInsertarAntesDe() {
        Lista l = new Lista();
        l.insertar(6, 1);
        l.insertar(2, 2);
        l.insertar(7, 3);
        l.insertar(6, 4);
        l.insertar(3, 5);
        l.insertar(6, 6);

        l.insertarAntesDe(8, 6);

        return l.toString();
    }

    public static void main(String[] args) {
        System.out.println(testInsertarAntesDe());
    }
}
