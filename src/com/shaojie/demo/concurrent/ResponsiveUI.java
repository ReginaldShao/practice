package com.shaojie.demo.concurrent;

import java.io.IOException;
import static com.shaojie.demo.util.Print.*;

public class ResponsiveUI extends Thread{
	public ResponsiveUI() {
		setDaemon(true);
		start();
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new ResponsiveUI();
		int c = System.in.read();
		println((char)c+" "+d);
	}

	private static volatile double d = 1;
	@Override
	public void run() {
		while(d>0) {
			d = d+ (Math.PI+Math.E)/d;
		}
	}
}
