package com.chauvet.utils;

public class Test1 {
	public static void main(String[] args) {
		int[] arr = new int[]{8,5,1,3,6,4};
		int[] index = new int[]{2,0,1,2,5,1,3,2,4,5,2};
		String tel = "";
		for (int i : index) {
			tel += arr[i];
		}
		System.out.println("my tel:"+tel);
	}
}


