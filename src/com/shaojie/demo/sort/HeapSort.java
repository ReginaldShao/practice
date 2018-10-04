package com.shaojie.demo.sort;

public class HeapSort implements SortMethod {

	@Override
	public String getMethodName() {
		return "堆排序";
	}

	@Override
	public int[] sort(int[] data) {
		// 一个构造堆的私有方法
		for(int k=data.length-1;k>=0;k--) {
			makeHeap(k,data);
			int tmp = data[k];
			data[k] = data[0];
			data[0] = tmp;
		}
		return data;
	}

	private void makeHeap(int e,int[] data) {
		//一个节点i的左子节点：2*i+1
		//一个节点i的右子节点：2*i+2
		//一个节点i的父节点：  (i-1)/2
		for(int k=e;k>0;k--) {
			int parent = k>>1;//(k-1)/2
			if(data[k]>data[parent]) {
				int tmp = data[parent];
				data[parent] = data[k];
				data[k] = tmp;
			}
		}
	}
	@Override
	public int[] reverseSort(int[] data) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		HeapSort hs = new HeapSort();
		int[] data = new int[] {0,1,2,3,4,5,6,7,8,9,10};
		hs.sort(data);
	}

}
