package com.tjj.chapter7;

/**
 * @description: 7.7 (16) （17） 8.2(all)  9(1)向上转型
 * @author: tangjunjian
 * @create: 2018-07-13 10:19
 **/

class Rodent {
    void eat () {
        System.out.println("eat from Rodent");
    };
}

class Mouse extends Rodent {
    public void eat () {
        System.out.println("Mouse is eating");
    }
}


class Gerbil extends Rodent {
    public void eat () {
        System.out.println("Gerbil is eating");
    }
}


class Hamster extends Rodent {
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
