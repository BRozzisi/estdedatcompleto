package tests;

import lineales.dinamicas.*;

public class TestsPersonales {
    
    public static void main(String[] args) {
        Cola c = new Cola();

        c.poner(1);
        c.sacar();

        System.out.println(c);
    }
}
