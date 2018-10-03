package com.shaojie.demo;

import java.util.SortedMap;
import java.util.function.BiConsumer;

public class MapTest {

	public static void main(String[] args) {
		A x = new A();
		x.v = 1;
		A y = new A();
		y.v = 2;
		MapTest mt = new MapTest(x,y);
		System.out.println("before accept--->>>");
		System.out.println("\tx.v="+x.v);
		System.out.println("\ty.v="+y.v);
		mt.functionTest(new Consumer());
		System.out.println("-------------------");
		System.out.println("\tx.v="+x.v);
		System.out.println("\ty.v="+y.v);
		System.out.println("<<<---after accept");
	}
	
	public MapTest(A x,A y) {
		this.x = x;
		this.y = y;
	}
	
	private A x,y;

	public void functionTest(Consumer consumer) {
		consumer.accept(x, y);
	}
}
class Consumer implements BiConsumer<A,A>{

	@Override
	public void accept(A t, A u) {
		t.v = u.v;
	}
	
}
class A {
	int v;
}
