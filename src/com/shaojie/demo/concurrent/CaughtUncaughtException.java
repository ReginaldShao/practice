package com.shaojie.demo.concurrent;
import static com.shaojie.demo.util.Print.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class CaughtUncaughtException {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(1, new HandlerThreadFactory());
		exec.execute(new ExceptionThread2());
	}

}

class ExceptionThread2 implements Runnable{

	@Override
	public void run() {
		Thread t = Thread.currentThread();
		println("run() by "+t);
		println("eh1 = "+t.getUncaughtExceptionHandler());
		throw new RuntimeException();
	}
	
}
class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
//		println("caught: "+e);
		throw new RuntimeException(e);
	}
	
}
class HandlerThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		println("eh0 = "+t.getUncaughtExceptionHandler());
		return t;
	}
	
}