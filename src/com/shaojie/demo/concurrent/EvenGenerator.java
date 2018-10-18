package com.shaojie.demo.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EvenGenerator extends IntGenerator {
	private Lock lock = new ReentrantLock();
	private int currentEvenValue = 0;
	@Override
	public int next() {
		lock.lock();
		try {
			++currentEvenValue;
			Thread.yield();
			++currentEvenValue;
			return currentEvenValue;
		}finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		EventChecker.test(new EvenGenerator(),5);
	}
}
