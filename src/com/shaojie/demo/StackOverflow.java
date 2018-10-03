package com.shaojie.demo;

public class StackOverflow {
	private int stackLength =1 ;
	public void stack(int a) {
		stackLength++;
//		int b =0;
//		int c =0;
//		int d =0;
		stack(++a);
	}
	public static void main(String[] args) {
		StackOverflow s = new StackOverflow();
		int a = 0;
		try {
			s.stack(a);
		}catch(Throwable e) {
			System.out.println(s.stackLength);
			throw e;
		}
	}

}
