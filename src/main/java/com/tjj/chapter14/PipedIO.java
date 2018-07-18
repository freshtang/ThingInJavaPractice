package com.tjj.chapter14;

import java.util.concurrent.*;
import java.io.*;
import java.util.*;
import static net.mindview.util.Print.*;

class CharQueue extends LinkedBlockingQueue<Character> {}

class Sender implements Runnable {
    private Random rand = new Random(47);
    private CharQueue cq;
    Sender(CharQueue charQueue) {cq = charQueue;}
    public void run() {
        try {
            while(!Thread.interrupted())
                for(char c = 'A'; c <= 'z'; c++) {
                    cq.put(c);
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                }
        } catch(InterruptedException e) {
            print(e + " Sender sleep interrupted");
        }
    }
}

class Receiver implements Runnable {
    private PipedReader in;
    private CharQueue cq;
    Receiver(CharQueue charQueue) {cq = charQueue;}
    public void run() {
        try {
            while(!Thread.interrupted()) {
                // Blocks until characters are there:
                printnb("Read: " + cq.take() + ", ");
            }
        } catch(InterruptedException e) {
            print(e + " Receiver read exception");
        }
    }
}

public class PipedIO {
    public static void main(String[] args) throws Exception {
        CharQueue cq = new CharQueue();
        Sender sender = new Sender(cq);
        Receiver receiver = new Receiver(cq);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(sender);
        exec.execute(receiver);
        TimeUnit.SECONDS.sleep(4);
        exec.shutdownNow();
    }
}
