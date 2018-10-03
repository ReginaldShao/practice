package com.shaojie.demo;

import java.io.IOException;

public class Euclid {
	public static void main(String[] args) throws IOException {
		while(true) {
			int a = System.in.read();
			int b = System.in.read();
			System.out.println(EucCalc.calc(a,b));
		}
	}
	
	public static class EucCalc{
		public static int calc(int a,int b) {
			int m = 0,n = a,r = b;
			do {
				m = n;
				n = r;
				r = m%n;
			}while(r != 0);
			return n;
		}
	}
}
