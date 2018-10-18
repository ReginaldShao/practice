package com.shaojie.demo.concurrent;

public class VolitileTest {
	private volatile int a=0;
	public int add() {
		return a++;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VolitileTest vt =new VolitileTest();
		vt.add();
	}

}
