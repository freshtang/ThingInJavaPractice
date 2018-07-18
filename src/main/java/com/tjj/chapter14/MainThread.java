package com.tjj.chapter14;

import java.util.ArrayList;
import java.util.concurrent.*;

public class MainThread {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> result = new ArrayList<Future<String>>();
        for (int i = 0; i < 5; i++) {
            result.add(exec.submit(new LiftOff()));
        }
        for(Future<String> fs: result){
            try{
                System.out.println(fs.get());
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                exec.shutdown();
            }
        }
    }
}
