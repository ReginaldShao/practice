package com.shaojie.demo;

public class Lambda {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WorkerInterface wi  = ()->{System.out.println("work is done !");};
		wi.doSomeWork();
	}

}
