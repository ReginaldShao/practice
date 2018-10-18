package com.shaojie.demo.concurrent;

import static com.shaojie.demo.util.Print.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class SimpleProperties implements Runnable {
	private int countDown = 5;
	private volatile double d;
	private int priority;
	
	public SimpleProperties(int priority) {
		this.priority = priority;
	}
	
	@Override
	public String toString() {
		return Thread.currentThread()+": "+countDown;
	}
	@Override
	public void run() {
		Thread.currentThread().setPriority(priority);
		while(true) {
			for(int i=1;i<100000;i++) {
				d += (Math.PI+Math.E)/(double)i;
				if(i % 1000 == 0)
					Thread.yield();
			}
			println(this);
			if(--countDown == 0)
				return;
		}
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i=0;i<5;i++) {
			exec.execute(new SimpleProperties(Thread.MIN_PRIORITY));
		}
		exec.execute(new SimpleProperties(Thread.MAX_PRIORITY));
		exec.shutdown();
	}
}
