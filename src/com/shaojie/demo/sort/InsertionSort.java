package com.shaojie.demo.sort;

public class InsertionSort implements SortMethod{

	@Override
	public String getMethodName() {
		return "插入排序";
	}

	@Override
	public int[] sort(int[] data) {
		//插入排序是将数据分成有序列表和无序列表两个,每次从无序数据中选择一个,比较并放到有序数据中
		//因为是in-place sort,所以需要对数据进行移动
		//k表示有序列表和无序列表的分界，是有序列表的最后一个元素
		for(int k=0;k<data.length-1;k++) {
			int pre=k;
			int tmp = data[k+1];
			while(pre>=0 && data[pre]>tmp) {
				data[pre+1] = data[pre];
				pre--;
			}
			data[pre+1]=tmp;
		}
		return data;
	}

	@Override
	public int[] reverseSort(int[] data) {
		for(int k=0;k<data.length-1;k++) {
			int pre=k;
			int tmp = data[k+1];
			while(pre>=0 && data[pre]<tmp) {
				data[pre+1] = data[pre];
				pre--;
			}
			data[pre+1]=tmp;
		}
		return data;
	}

}
