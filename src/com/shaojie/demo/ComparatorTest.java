package com.shaojie.demo;

import java.util.Comparator;

public class ComparatorTest implements Comparator<Becompare> {

	@Override
	public int compare(Becompare o1, Becompare o2) {
		if(o1.x>o2.x)
			return 1;
		else if(o1.x == o2.x)
			return 0;
		else
			return -1;
	}

	public static void main(String[] args) {
		ComparatorTest ct = new ComparatorTest();
		Becompare bc1 = new Becompare(1);
		Becompare bc2 = new Becompare(2);
		System.out.println(ct.compare(bc1, bc2));
	}
}
class Becompare{
	public Becompare(int x) {
		this.x = x;
	}
	int x;
}
