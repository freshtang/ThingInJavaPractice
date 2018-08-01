package com.tjj.chapter14;

/**
 * @description: 21.5 （21）notifyAll（）跟wait（）的使用
 * @author: tangjunjian
 * @create: 2018-07-31 16:03
 **/

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static net.mindview.util.Print.*;

class Clazz {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void waitFor()
            throws InterruptedException {
        lock.lock();
        try{
            condition.await();
        } finally {
            lock.unlock();
        }

    }
    public void noti() {
        lock.lock();
        try{
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}


class Run1 implements Runnable {
    private Clazz clazz;

    Run1(Clazz c) {clazz = c;}

    public void run() {
        try {
            while(!Thread.interrupted()) {
                System.out.println("before run1 wait");
                clazz.waitFor();
                System.out.println("after run1 wait");
            }
        } catch(InterruptedException e) {
            print("Exiting via interrupt");
        }
        print("Ending Run1 task");
    }
}

class Run2 implements Runnable {

    private Clazz clazz;

    Run2(Clazz c) {clazz = c;}

    public void run() {
        try {
            while(!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(900);
                printnb("run2 notifyAll! ");
                clazz.noti();
            }
        } catch(InterruptedException e) {
            print("Exiting via interrupt");
        }
        print("Ending Run2 task");
    }
}

public class WaxOMatic {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();

        Clazz c = new Clazz();
        exec.execute(new Run1(c));
        exec.execute(new Run2(c));

        TimeUnit.SECONDS.sleep(2);
        // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}
