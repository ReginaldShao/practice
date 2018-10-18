package com.shaojie.demo.concurrent;
import static com.shaojie.demo.util.Print.*;

public class Joining {

	public static void main(String[] args) {
		Sleeper 
			sleepy = new Sleeper("Sleepy", 50),
			grumy = new Sleeper("Grumy", 50);
		Joiner
			dopey = new Joiner("Dopey", sleepy),
			doc = new Joiner("Doc", grumy);
		grumy.interrupt();
	}

}

class Sleeper extends Thread{
	private int duration;
	public Sleeper(String name,int duration) {
		super(name);
		this.duration = duration;
		start();
	}
	@Override
	public void run() {
		try {
			sleep(duration);
		} catch (InterruptedException e) {
			println(getName()+" was interrupted. isInterrupted:"+isInterrupted());
			return;
		}
		println(getName()+" has awakened");
	}
}

class Joiner extends Thread{
	private Sleeper sleeper;
	
	public Joiner(String name,Sleeper sleeper) {
		super(name);
		this.sleeper = sleeper;
		start();
	}
	
	@Override
	public void run() {
		try {
			sleeper.join();
		} catch (InterruptedException e) {
			println("interrupted!");
		}
		println(getName()+" join completed!");
	}
}