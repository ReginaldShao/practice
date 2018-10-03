package com.shaojie.demo;

public class ExcepTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			try {
				throw new NumberFormatException("runtimeException");
			}catch(Exception e) {
				throw new RuntimeException("runtimeException",e);
			}
		}catch(NumberFormatException e) {
			System.out.println(" num excep");
		} finally {
			System.out.println("finally");
		}
	}

}
