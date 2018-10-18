package com.shaojie.demo.concurrent;

import static com.shaojie.demo.util.Print.println;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DaemonFromFactory implements Runnable {

	@Override
	public void run() {
		while(true) {
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				println("sleep() interrupted");
			}
			println(this);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
		for(int i=0;i<5;i++) {
			exec.execute(new DaemonFromFactory());
		}
		println("All daemons started!");
		TimeUnit.MILLISECONDS.sleep(500);
	}
}
