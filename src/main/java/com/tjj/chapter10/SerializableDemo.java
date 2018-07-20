package com.tjj.chapter10;

/**
 * @description: 18.12 实现序列化
 * @author: tangjunjian
 * @create: 2018-07-12 15:44
 **/

import java.io.*;
import java.util.Random;
import static net.mindview.util.Print.*;

class Data implements Serializable {
    private int n;
    public Data(int n) {
        this.n = n;
    }
    public String toString() { return Integer.toString(n); }
}

public class SerializableDemo implements Serializable {
    private static Random rand = new Random(47);
    private Data[] d = {
            new Data(rand.nextInt(10)),
            new Data(rand.nextInt(10)),
            new Data(rand.nextInt(10)),
    };
    public SerializableDemo() {
        System.out.println("default constructor");
    }
    public String toString() {
        StringBuilder result = new StringBuilder(":");
        result.append("(");
        for(Data dat : d)
            result.append(dat + ",");
        result.append(")");
        return result.toString();
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        SerializableDemo sd = new SerializableDemo();
        print("sd = " + sd);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("serializable.out"));
        out.writeObject("Worm storage\n");
        out.writeObject(sd);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("serializable.out"));
        String s = (String)in.readObject();
        SerializableDemo sd2 = (SerializableDemo)in.readObject();
        print(s + "sd2 = " + sd2);
    }
}
