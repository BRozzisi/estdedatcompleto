package tests;

import lineales.estaticas.*;

public class TestsPersonales {
    
    public static void main(String[] args) {
        Cola c = new Cola();
        Cola cClone;

        c.poner(1);
        c.poner(1);
        c.poner(1);

        cClone = c.clone();

        System.out.println(c);
        System.out.println(cClone);
    }
}
