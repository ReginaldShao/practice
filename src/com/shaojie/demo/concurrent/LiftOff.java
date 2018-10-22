package com.shaojie.demo.concurrent;

import static com.shaojie.demo.util.Print.*;

public class LiftOff implements Runnable {
	protected int countDown = 10;
	private static int taskCount = 0;
	private final int id = taskCount++;
	public LiftOff() {}
	public LiftOff(int i) {
		countDown = i;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(countDown-- > 0) {
			println(status());
			Thread.yield();
		}
	}
	
	protected String status() {
		return "#"+id+"("+(countDown>0?countDown:"Liftoff!")+")";
	}

	public static void main(String[] args) {
//		LiftOff lo = new LiftOff();
//		lo.run();
		for(int j=0;j<4;j++) {
			Thread t = new Thread(new LiftOff());
			t.start();
		}
		println("waiting for liftoff!");
	}
}
