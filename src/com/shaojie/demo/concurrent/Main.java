package com.shaojie.demo.concurrent;
import static com.shaojie.demo.util.Print.*;

class Sync {
	private static int count=0;
	private final int id=count++;
	private volatile static long a=0;
	public void test() {
		synchronized (Sync.class) {
			System.out.println(id+"-test开始..");
			for(long c=0;c<100L;c++) {
				print(++a);
				print(",");
			}
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			println();
			System.out.println(id+"-test结束..");
		}
	}
}

class MyThread extends Thread {

	Sync sync = new Sync();
	public void run() {
		sync.test();
	}
}

public class Main {

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			Thread thread = new MyThread();
			thread.start();
		}
	}
}