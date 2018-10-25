package com.shaojie.demo.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static com.shaojie.demo.util.Print.print;
import static com.shaojie.demo.util.Print.println;

class DelayedTask implements Runnable, Delayed {
    private static int counter = 0;
    private final int id = counter++;
    private final int delta;
    private final long trigger;
    protected static List<DelayedTask> sequence =
            new ArrayList<>();

    public DelayedTask(int delayInMilliseconds) {
        delta = delayInMilliseconds;
        trigger = System.nanoTime() +
                TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
        sequence.add(this);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayedTask that = (DelayedTask) o;
        if (trigger < that.trigger) return -1;
        if (trigger > that.trigger) return 1;
        return 0;
    }

    @Override
    public void run() {
        print(this + " ");
    }

    @Override
    public String toString() {
        return String.format("[%1$-4d]", delta) + " Task " + id;
    }

    public String summary(){
        return "("+id+":"+delta+")";
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger - System.nanoTime(), TimeUnit.SECONDS);
    }

    public static class EndSentinel extends DelayedTask {
        private ExecutorService exec;

        public EndSentinel(int delayInMilliseconds,ExecutorService e) {
            super(delayInMilliseconds);
            exec = e;
        }

        @Override
        public void run() {
            for(DelayedTask pt : sequence){
                print(pt.summary()+" ");
            }
            println();
            println(this+" Calling shutdownNow()");
            exec.shutdownNow();
        }
    }
}

class DelayedTaskConsumer implements Runnable{
    private DelayQueue<DelayedTask> q;

    public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
        this.q = q;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()) {
                q.take().run();
            }
        } catch (InterruptedException e) {
        }
        println("Finished DelayedTaskConsumer");
    }
}

public class DelayQueueDemo {
    public static void main(String[] args) {
        Random random = new Random(47);
        ExecutorService executorService = Executors.newCachedThreadPool();
        DelayQueue<DelayedTask> queue = new DelayQueue<>();
        for (int i=0;i<20;i++){
            queue.put(new DelayedTask(random.nextInt(5000)));
        }
        queue.add(new DelayedTask.EndSentinel(5000,executorService));
        executorService.execute(new DelayedTaskConsumer(queue));
    }
}
