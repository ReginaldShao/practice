package com.shaojie.demo.sort;

public class SelectSort implements SortMethod{

	@Override
	public String getMethodName() {
		return "选择排序";
	}

	@Override
	public int[] sort(int[] data) {
		for(int k=0;k<data.length-1;k++) {
			int minIndex = k;
			for(int l=k+1;l<data.length;l++) {
				if(data[l]<data[minIndex]) {
					minIndex = l;
				}
			}
			int tmp = data[k];
			data[k] = data[minIndex];
			data[minIndex] = tmp;
		}
		return data;
	}

	@Override
	public int[] reverseSort(int[] data) {
		for(int k=0;k<data.length-1;k++) {
			int maxIndex = k;
			for(int l=k+1;l<data.length;l++) {
				if(data[l]>data[maxIndex]) {
					maxIndex = l;
				}
			}
			int tmp = data[k];
			data[k] = data[maxIndex];
			data[maxIndex] = tmp;
		}
		return data;
	}

}
