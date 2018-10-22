package com.shaojie.demo.concurrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import static com.shaojie.demo.util.Print.*;

public class TestBlockingQueues {

	public static void main(String[] args) {
		test("LinkedBQ",new LinkedBlockingQueue<>());
		test("ArrayBQ",new ArrayBlockingQueue<>(3));
		test("SychronousQueue",new SynchronousQueue<>());
	}
	
	static void getKey() {
		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	static void getKey(String messges) {
		println(messges);
		getKey();
	}
	
	static void test(String msg,BlockingQueue<LiftOff> queue) {
		println(msg);
		LiftOffRunner runner = new LiftOffRunner(queue);
		Thread t = new Thread(runner);
		t.start();
		for(int i=0;i<5;i++) {
			runner.add(new LiftOff(5));
		}
		getKey("Press 'Enter' ("+msg+")");
		t.interrupt();
		println("Finished "+msg+" test ");
	}

}
class LiftOffRunner implements Runnable{
	private BlockingQueue<LiftOff> rockets;
	public LiftOffRunner(BlockingQueue<LiftOff> queue) {
		this.rockets = queue;
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				LiftOff rocket = rockets.take();
				rocket.run();
			}
		}catch(InterruptedException e) {
			println("waking from take()");
		}
		
		println("Exiting liftoffrunner");
	}
	
	public void add(LiftOff lo) {
		try {
			rockets.put(lo);
		} catch (InterruptedException e) {
			println("interrupterd during put()");
		}
	}
	
}
