package com.tjj.chapter10;

/**
 * @description: 18.6 (7、8、9、12) 利用LinkedList反向打印，大写打印
 * @author: tangjunjian
 * @create: 2018-07-9 15:44
 **/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class BufferedInputFileDemo {
    LinkedList res = new LinkedList();

    public  LinkedList read (String filename, String str) throws IOException {
        String s;
        BufferedReader in = new BufferedReader(new FileReader(filename));
        while((s = in.readLine())!= null) {
            if(s.indexOf(str) != -1)
            this.res.add(s);
        }
        return res;
    }

    public void write (String filename) throws IOException {
        PrintWriter out = new PrintWriter(filename);
        int size = this.res.size();
        for (int i=0; i<size; i++) {
            System.out.println(this.res.get(i));
            out.println(i+1 + ":" + this.res.get(i));
        }
        out.close();
    }

    public void reversePrint () {
        int size = this.res.size();
        for (int i=size-1; i>=0; i--) {
            System.out.println( this.res.get(i).toString().toUpperCase());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedInputFileDemo bif = new BufferedInputFileDemo();
        bif.read("./pom.xml", "xmlns");
        bif.reversePrint();
        bif.write("test.txt");
    }
}
