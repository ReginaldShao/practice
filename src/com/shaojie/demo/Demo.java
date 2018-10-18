package com.shaojie.demo;

import static com.shaojie.demo.util.Print.*;
public class Demo {
	public static void main(String[] args) {
		try {
			throw new NullPointerException();
		} catch(Exception e) {
			println("exception");
		} finally {
			println("finally");
		}
	}
}
