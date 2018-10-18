package com.shaojie.demo.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateThreadPool {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		for(int j=0;j<5;j++) {
			executor.execute(new LiftOff());
		}
		executor.shutdown();
	}
}
