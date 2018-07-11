package com.tjj.chapter7;

interface Rodent {
    void eat ();
}

class Mouse implements Rodent {
    public void eat () {
        System.out.println("Mouse is eating");
    }
}


class Gerbil implements Rodent {
    public void eat () {
        System.out.println("Gerbil is eating");
    }
}


class Hamster implements Rodent {
    public void eat () {
        System.out.println("Hamster is eating");
    }
}

public class Animal {
    public static Rodent randomRodent() {
        int rand = (int)(Math.random()*3);
        switch (rand) {
            default:
            case 0: return new Mouse();
            case 1: return new Gerbil();
            case 2: return new Hamster();
        }
    }
    public static void main(String[] args) {
        Rodent[] r = new Rodent[10];
        for (int i = 0; i < r.length; i++) {
            r[i] = randomRodent();
        }

        for (int i = 0; i < r.length; i++) {
            r[i].eat();
        }
    }
}
