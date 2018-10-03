package com.shaojie.demo;

import java.io.PrintStream;

public class ObjectAddr {

	public static int WID = 10;
	public static void main(String[] args) {
		PrintStream out = System.out;
//		out.printf("%-1s吃了 %d 顿饭,花了 %d 元", "xiaoming",1,40);
//		out.printf("%05X: \n", 11);
		out.printf("%-"+WID+"s %10s %10s\n", "Name","Age","ID");
		out.printf("%-10s %10s %10s\n", "----","---","--");
		out.printf("%-10.10s %10d %10d\n", "xiaoming",10,1);
		out.printf("%-10.10s %10.2f %10d\n", "yingqiangqiang",12.01111,2100);
//		System.out.println(new ObjectAddr());
	}

	@Override
	public String toString() {
		return "class address"+super.toString();
	}
}
