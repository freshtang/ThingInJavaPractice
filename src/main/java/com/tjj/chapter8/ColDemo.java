package com.tjj.chapter8;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

class Gerbil {
    int gerbilNumber;
    Gerbil(int num) {gerbilNumber = num;}
    void hop() {
        System.out.println(gerbilNumber + "is jumping");
    }
}

public class ColDemo {
    public static void IteratorList(Iterator<Gerbil> it) {
        while(it.hasNext()) {
            Gerbil g = it.next();
            g.hop();
        }
    }
    public static void main(String[] args) {
        ArrayList<Gerbil> list = new ArrayList<Gerbil>();
        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            list.add(new Gerbil(r.nextInt(30)));
        }
        IteratorList(list.iterator());
    }
}
