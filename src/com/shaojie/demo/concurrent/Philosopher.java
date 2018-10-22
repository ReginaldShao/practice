package com.shaojie.demo.concurrent;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import static com.shaojie.demo.util.Print.*;

public class Philosopher implements Runnable {
	private Chopstick left;
	private Chopstick right;
	private final int id;
	private final int ponderFactor;
	private Random rand = new Random(47);

	private void pause() throws InterruptedException {
		if (ponderFactor == 0)
			return;
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 50));
	}

	public Philosopher(Chopstick left, Chopstick right, int ident, int ponder) {
		this.left = left;
		this.right = right;
		this.id = ident;
		this.ponderFactor = ponder;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				println(this + " thinking");
				pause();
				println(this + " grabbing right");
				right.take();
				println(this + " grabbing left");
				left.take();
				println(this + " eating");
				pause();
				right.drop();
				left.drop();
			}
		} catch (InterruptedException e) {
			println(this + " exiting via interrupt");
		}
	}

	@Override
	public String toString() {
		return "Philosopher " + id;
	}

}
