package com.shaojie.demo.concurrent;

import java.util.concurrent.TimeUnit;
import static com.shaojie.demo.util.Print.*;

public class SimpleDeamons implements Runnable {

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
		for(int i=0;i<10;i++) {
			Thread daemon = new Thread(new SimpleDeamons());
			daemon.setDaemon(true);
			daemon.start();
		}
		println("All daemons started!");
		TimeUnit.MILLISECONDS.sleep(95);
	}
}
