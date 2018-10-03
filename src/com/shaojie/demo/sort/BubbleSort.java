package com.shaojie.demo.sort;

public class BubbleSort implements SortMethod{

	@Override
	public int[] sort(int[] data) {
		//使用一个int的额外空间,进行交换,时间复杂度是O(n2)
		int tmp = 0;
		for(int k=0;k<data.length;k++) {
			for(int l = 0;l<data.length-1-k;l++) {
				if(data[l]>data[l+1]) {
					tmp = data[l+1];
					data[l+1] = data[l];
					data[l] = tmp;
				}
			}
		}
		return data;
	}

	@Override
	public String getMethodName() {
		return "冒泡排序";
	}

	@Override
	public int[] reverseSort(int[] data) {
		int tmp = 0;
		for(int k=0;k<data.length;k++) {
			for(int l = 0;l<data.length-1-k;l++) {
				if(data[l]<data[l+1]) {
					tmp = data[l+1];
					data[l+1] = data[l];
					data[l] = tmp;
				}
			}
		}
		return data;
	}

}
