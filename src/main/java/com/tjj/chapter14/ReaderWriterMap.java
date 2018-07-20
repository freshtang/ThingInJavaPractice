package com.tjj.chapter14;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.*;
import static net.mindview.util.Print.*;

public class ReaderWriterMap<K, V> {
    private HashMap<K, V> lockedmap = new HashMap<K, V>();
    // Make the ordering fair:
    private ReentrantReadWriteLock lock =
            new ReentrantReadWriteLock(true);
    public ReaderWriterMap() {
    }
    public V set(K key, V value ) {
        Lock wlock = lock.writeLock();
        wlock.lock();
        try {
            return lockedmap.put(key, value);
        } finally {
            wlock.unlock();
        }
    }
    public V get(K key) {
        Lock rlock = lock.readLock();
        rlock.lock();
        try {
            // Show that multiple readers
            // may acquire the read lock:
            if(lock.getReadLockCount() > 1)
                print(lock.getReadLockCount());
            return lockedmap.get(key);
        } finally {
            rlock.unlock();
        }
    }
    public static void main(String[] args) throws Exception {
        new ReaderWriterListTest(30, 1);
    }
}

class ReaderWriterListTest {
    ExecutorService exec = Executors.newCachedThreadPool();
    private final static int SIZE = 100;
    private static Random rand = new Random(47);
    private ReaderWriterMap<Integer, Integer> map =
            new ReaderWriterMap<Integer, Integer>();
    private class Writer implements Runnable {
        public void run() {
            try {
                for(int i = 0; i < 20; i++) { // 2 second test
                    map.set(i, rand.nextInt());
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            } catch(InterruptedException e) {
                // Acceptable way to exit
            }
            print("Writer finished, shutting down");
            exec.shutdownNow();
        }
    }
    private class Reader implements Runnable {
        public void run() {
            try {
                while(!Thread.interrupted()) {
                    for(int i = 0; i < SIZE; i++) {
                        map.get(i);
                        TimeUnit.MILLISECONDS.sleep(1);
                    }
                }
            } catch(InterruptedException e) {
                // Acceptable way to exit
            }
        }
    }
    public ReaderWriterListTest(int readers, int writers) {
        for(int i = 0; i < readers; i++)
            exec.execute(new Reader());
        for(int i = 0; i < writers; i++)
            exec.execute(new Writer());
    }
}
