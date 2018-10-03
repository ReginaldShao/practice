package com.shaojie.demo;

public class ComparableTest implements Comparable<ComparableTest> {
	private int v;
	@Override
	public int compareTo(ComparableTest o) {
		if(this.v > o.v)
			return 1;
		else if(this.v == o.v)
			return 0;
		else 
			return -1;
	}

}
