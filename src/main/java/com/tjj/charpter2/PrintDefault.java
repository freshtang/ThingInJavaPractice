package com.tjj.charpter2;

public class PrintDefault {

    int i;
    char c;

    public PrintDefault () {
        System.out.println("i = " + i);
        System.out.println("c = " + c);
    }

    public static void main(String[] args) {
        new PrintDefault();
    }

}
