package com.shaojie.demo.concurrent;
import static com.shaojie.demo.util.Print.*;

import java.util.concurrent.TimeUnit;

public class Damons {
	public static void main(String[] args) throws InterruptedException {
		Thread d = new Thread(new Daemon());
		d.setDaemon(true);
		d.start();
		println("d.isDaemon() = "+d.isDaemon()+", ");
		TimeUnit.MICROSECONDS.sleep(100);
	}
}
class Daemon implements Runnable{
	private Thread[] t = new Thread[10];
	@Override
	public void run() {
		for(int i=0;i<t.length;i++) {
			t[i] = new Thread(new DaemonSpawn());
			t[i].start();
			println("DaemonSpawn "+i+" started.");
		}
		for(int i=0;i<t.length;i++) {
			print("t["+i+"].isDaemon() = "+t[i].isDaemon()+", ");
		}
		while(true)
			Thread.yield();
	}
	
}
class DaemonSpawn implements Runnable{

	@Override
	public void run() {
		while(true) {
			Thread.yield();
		}
	}
	
}