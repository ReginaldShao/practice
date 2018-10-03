package com.shaojie.demo.sort;

public class QuickSort implements SortMethod {

	@Override
	public String getMethodName() {
		return "快速排序";
	}

	@Override
	public int[] sort(int[] data) {
		quick(0,data.length-1,data);
		return data;
	}
	
	private void quick(int b,int e,int[] data) {
		if(b == e) return;
		int pivot = b;
		int mid = pivot+1;
		for(int k=b+1;k<=e;k++) {
			if(data[k]<data[pivot]) {
				int tmp = data[mid];
				data[mid] = data[k];
				data[k] = tmp;
				mid++;
			}
		}
		//将pivot放到中间
		int tmp = data[mid-1];
		data[mid-1] = data[pivot];
		data[pivot] = tmp;
		
		if(b != mid) {
			quick(b,mid-1,data);
		}
		if(mid != e) {
			quick(mid+1,e,data);
		}
	}
	
	private void quickReverse(int b,int e,int[] data) {
		if(b == e) return;
		int pivot = b;
		int mid = pivot+1;
		for(int k=b+1;k<=e;k++) {
			if(data[k]>data[pivot]) {
				int tmp = data[mid];
				data[mid] = data[k];
				data[k] = tmp;
				mid++;
			}
		}
		//将pivot放到中间
		int tmp = data[mid-1];
		data[mid-1] = data[pivot];
		data[pivot] = tmp;
		
		if(b != mid) {
			quick(b,mid-1,data);
		}
		if(mid != e) {
			quick(mid+1,e,data);
		}
	}
	@Override
	public int[] reverseSort(int[] data) {
		quickReverse(0,data.length-1,data);
		return data;
	}

}
