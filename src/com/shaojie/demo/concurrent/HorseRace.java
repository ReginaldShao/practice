package com.shaojie.demo.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static com.shaojie.demo.util.Print.*;

/**
 * 赛马游戏
 */
public class HorseRace {
    static final int FINISH_LINE=75;
    private List<Horse> horses = new ArrayList<>();
    private ExecutorService exec = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;
    public HorseRace(int nHorses,final int pause){
        barrier = new CyclicBarrier(nHorses, new Runnable() {
            @Override
            public void run() {
                StringBuilder sb = new StringBuilder();
                for(int i=0;i<FINISH_LINE;i++){
                    sb.append("=");
                }
                println(sb.toString());

                for(Horse horse : horses){
                    println(horse.tracks());
                }

                for(Horse horse : horses){
                    if(horse.getStrides()>FINISH_LINE){
                        println(horse +" won!");
                        exec.shutdownNow();
                        return;
                    }
                }

                try {
                    TimeUnit.MILLISECONDS.sleep(pause);
                } catch (InterruptedException e) {
                    println("barrier-action sleep interrupted");
                }
            }
        });

        Horse.setBarrier(barrier);
        for(int i=0;i<nHorses;i++){
            Horse horse = new Horse();
            horses.add(horse);
            exec.execute(horse);
        }
    }

    public static void main(String[] args) {
        int nHorse = 7;
        int pause = 200;
        new HorseRace(nHorse,pause);
    }
}
class Horse implements Runnable{
    private static int counter = 0;
    private final int id = counter++;
    private int strides = 0;
    private static Random rand = new Random(47);
    private static CyclicBarrier barrier;

    public static void setBarrier(CyclicBarrier barrier){
        Horse.barrier = barrier;
    }

    public synchronized int getStrides(){
        return strides;
    }
    @Override
    public void run() {
        try{
            while (!Thread.interrupted()) {
                synchronized (this){
                    strides+=rand.nextInt(3 );
                }
                barrier.await();
            }
        } catch (InterruptedException e){
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Horse "+id+" ";
    }

    public String tracks(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<getStrides();i++){
            sb.append("*");
        }
        sb.append(id);
        sb.append(" ").append(strides);
        return sb.toString();
    }
}
