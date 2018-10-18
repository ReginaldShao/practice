package com.shaojie.demo.concurrent;
import static com.shaojie.demo.util.Print.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class EventChecker implements Runnable {
	private IntGenerator generator;
	private final int id;
	
	public EventChecker(IntGenerator g,int id) {
		this.generator = g;
		this.id = id;
	}
	@Override
	public void run() {
		while(!generator.isCanceled()) {
			int val = generator.next();
			if(val % 2 != 0) {
				println(val+" not even!");
				generator.cancel();
			}else
				println(val+" is even!");
		}
	}

	public static void test(IntGenerator gp,int count) {
		println("Press Ctrl-C to exit");
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i=0;i<count;i++) {
			exec.execute(new EventChecker(gp, i));
		}
		exec.shutdown();
	}
	
	public static void test(IntGenerator gp) {
		test(gp,5);
	}
}
