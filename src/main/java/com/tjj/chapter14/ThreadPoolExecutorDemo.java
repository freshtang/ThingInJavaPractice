package com.tjj.chapter14;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static net.mindview.util.Print.print;

/**
 * @description: 何实现一个自定义线程工厂，自定义等待队列以及长度以及自定义任务拒绝重新添加的线程池使用场景；
 * @author: tangjunjian
 * @create: 2018-08-01 14:09
 **/

class NThread implements Runnable {
    private int taskNum;

    public NThread(int num) {
        this.taskNum = num;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+ taskNum +" 在忙...");
            Thread.sleep(4000);
        } catch(InterruptedException e) {
            print("Exiting via interrupt");
        }
        print("Ending  " + Thread.currentThread().getName() + taskNum);
    }
}

class MyRejectedPolicy implements RejectedExecutionHandler {

    public MyRejectedPolicy() { }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        throw new RejectedExecutionException("Task " + r.toString() +
                " rejected from " +
                e.toString() + "----MyRejectedPolicy");
    }
}


class MyThreadFactory implements ThreadFactory {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    MyThreadFactory() {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        namePrefix = "pool-" +
                poolNumber.getAndIncrement() +
                "-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                namePrefix + threadNumber.getAndIncrement(),
                0);
        System.out.println("生成线程 "+t);
        if (t.isDaemon())
            t.setDaemon(false);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
}

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new
                ArrayBlockingQueue<Runnable>(5), new MyThreadFactory(), new MyRejectedPolicy());

        for(int i=0;i<15;i++){
            NThread nThread = new NThread(i);
            executor.execute(nThread);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
        }

        executor.shutdown();
    }
}
