package com.shaojie.demo.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.shaojie.demo.util.Print.*;

public class SleepingTask extends LiftOff {
	@Override
	public void run() {
		while(countDown-->0) {
			try {
				println(status());
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i=0;i<5;i++) {
			exec.execute(new SleepingTask());
		}
		exec.shutdown();
	}
}
