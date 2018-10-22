package com.shaojie.demo.concurrent;

import static com.shaojie.demo.util.Print.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Resteraunt {
	boolean cleaned = true;
	Meal meal;
	Chef chef = new Chef(this);
	Waiter waiter = new Waiter(this);
	BusBoy bb = new BusBoy(this);
	ExecutorService exec = Executors.newCachedThreadPool();

	public static void main(String[] args) {
		Resteraunt r = new Resteraunt();
		r.exec.execute(r.waiter);
		r.exec.execute(r.chef);
		r.exec.execute(r.bb);
	}

}

class Meal {
	int id;

	Meal(int id) {
		this.id = id;
	}
}

class BusBoy implements Runnable {
	private Resteraunt resteraunt;

	public BusBoy(Resteraunt r) {
		this.resteraunt = r;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (resteraunt.cleaned)
						wait();
				}
				println("clean table");
				resteraunt.cleaned = true;
			}
		} catch (InterruptedException e) {
			println("interrupterd busboy");
		}
	}

}

class Chef implements Runnable {
	private Resteraunt resteraunt;
	private int count = 0;

	Chef(Resteraunt r) {
		this.resteraunt = r;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (resteraunt.meal != null)
						wait();
				}

				if (++count == 10) {
					println("食物没有了");
					resteraunt.exec.shutdownNow();
				}
				synchronized (resteraunt.waiter) {
					print("做好啦 ");
					resteraunt.meal = new Meal(count);
					resteraunt.waiter.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(500);
			}
		} catch (InterruptedException e) {
			println("Interrupterd chef");
		}
	}
}

class Waiter implements Runnable {
	private Resteraunt resteraunt;

	Waiter(Resteraunt r) {
		this.resteraunt = r;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (resteraunt.meal == null) {
						wait();
					}
				}
				println("waiter get meal:" + resteraunt.meal.id);
				synchronized (resteraunt.bb) {
					resteraunt.cleaned = false;
					resteraunt.bb.notifyAll();
				}
				synchronized (resteraunt.chef) {
					resteraunt.meal = null;
					resteraunt.chef.notifyAll();
				}
			}
		} catch (InterruptedException e) {
			println("Interrupterd waiter");
		}

	}

}