package com.shaojie.demo.sort;

public class CountingSort implements SortMethod {

	@Override
	public String getMethodName() {
		return "计数排序";
	}

	@Override
	public int[] sort(int[] data) {
		int[] ct = new int[data.length];
		for(int k=0;k<data.length;k++) {
			ct[data[k]]++;
		}
		int inx = 0;
		for(int k=0;k<ct.length;k++) {
			int ctk = ct[k];
			while(ctk!= 0) {
				data[inx++] = k;
				ctk--;
			}
		}
		return data;
	}

	@Override
	public int[] reverseSort(int[] data) {
		// TODO Auto-generated method stub
		return null;
	}

}
