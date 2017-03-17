package org.test;

public class TestArrays {
public static void main(String[] args) {
	//为数组分配空间
	String[] islands = new String[4];
	System.out.println(islands[0]);
	int[] index = new int[4];
	//给数组内空间赋值
	islands[0] = "Bermuda";
	islands[1] = "Fiji";
	islands[2] = "Azores";
	islands[3] = "Cozumel";
	System.out.println(islands[0]);
	index[0] = 1;
	index[1] = 3;
	index[2] = 0;
	index[3] = 2;
	//声明两个变量
	int y = 0;
	int ref;
	while(y < 4) {
		ref = index[y];
		System.out.println("island=" + islands[ref]);
//		System.out.println();
		y = y + 1;
		}
	}
}
