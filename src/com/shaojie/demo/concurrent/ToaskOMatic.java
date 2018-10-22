package com.shaojie.demo.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import static com.shaojie.demo.util.Print.*;

class Toast {
	public enum Status {
		DRY, BUTTERED, JAMMED
	}

	private Status status = Status.DRY;
	private final int id;

	public Toast(int idn) {
		id = idn;
	}

	public void butter() {
		status = Status.BUTTERED;
	}

	public void jam() {
		status = Status.JAMMED;
	}

	public Status getStatus() {
		return status;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return "Toast " + id + ": " + status;
	}
}

class ToastQueue extends LinkedBlockingQueue<Toast> {
};

class Toaster implements Runnable {
	private ToastQueue toastQueue;
	private Random rand = new Random(47);
	private int count = 0;

	Toaster(ToastQueue queue) {
		toastQueue = queue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(50);
				Toast t = new Toast(count++);
				println(t);
				toastQueue.put(t);
			}
		} catch (InterruptedException e) {
			println("butter interrupted");
		}
		println("butter off");
	}

}

class Butterer implements Runnable {
	private ToastQueue dryQueue, butteredQueue;

	public Butterer(ToastQueue dry, ToastQueue buttered) {
		dryQueue = dry;
		butteredQueue = buttered;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t;
				t = dryQueue.take();
				t.butter();
				println(t);
				butteredQueue.put(t);
			}
		} catch (InterruptedException e) {
			println("buttered interrupted");
		}
		println("buttered off");
	}

}

class Jammer implements Runnable{
	private ToastQueue butteredQueue,finishedQueue;
	public Jammer(ToastQueue buttered,ToastQueue finished) {
		butteredQueue = buttered;
		finishedQueue = finished;
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Toast t = butteredQueue.take();
				t.jam();
				println(t);
				finishedQueue.put(t);
			}
		}catch(InterruptedException e) {
			println("jam");
		}
	}
	
}

class Eater implements Runnable{
	private ToastQueue finishedQueue;
	private int counter =0;
	public Eater(ToastQueue finished) {
		this.finishedQueue = finished;
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Toast t = finishedQueue.take();
				if(t.getId() != counter++ ||
						t.getStatus() != Toast.Status.JAMMED) {
					println(">>>> error: "+t);
					System.exit(1);
				}else {
					println("Chomp! "+t);
				}
			}
		} catch (InterruptedException e) {
			println("eater interrupted");
		}
		println("eater off");
	}
	
}
public class ToaskOMatic {

	public static void main(String[] args) throws InterruptedException {
		ToastQueue dryQueue = new ToastQueue(),
				butteredQueue = new ToastQueue(),
				finishedQueue = new ToastQueue();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Toaster(dryQueue));
		exec.execute(new Butterer(dryQueue, butteredQueue));
		exec.execute(new Jammer(butteredQueue, finishedQueue));
		exec.execute(new Eater(finishedQueue));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}

}
