package com.shaojie.demo.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static com.shaojie.demo.util.Print.*;

public class NativeExceptionHandling {
	public static void main(String[] args) {
		try {
			ExecutorService exec = Executors.newCachedThreadPool();
			exec.execute(new ExceptionThread());
		}catch(RuntimeException e) {
			println("handled");
		}
	}
}
