package com.shaojie.demo.concurrent;
import static com.shaojie.demo.util.Print.*;

public class JoinTwo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Sleeper
			sp1 = new Sleeper("Sp1", 1500),
			sp2 = new Sleeper("Sp2", 1000);
		sp1.join();
		sp2.join();
		println("main finished");
	}

}
