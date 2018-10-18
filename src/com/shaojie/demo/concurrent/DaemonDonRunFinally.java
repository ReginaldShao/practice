package com.shaojie.demo.concurrent;
import static com.shaojie.demo.util.Print.*;

import java.util.concurrent.TimeUnit;

public class DaemonDonRunFinally {
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new ADaemon());
//		t.setDaemon(true);
		t.start();
//		TimeUnit.SECONDS.sleep(1);
	}
}
class ADaemon implements Runnable{

	@Override
	public void run() {
		println("Starting ADaemon");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			println("This should always run?");
		}
	}
	
}
