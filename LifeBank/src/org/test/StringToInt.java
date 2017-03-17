package org.test;
/*
 * 强制类型转换String to int
 * Integer.parseInt()方法仅对String类型中
 * 存储的数值起作用，否则会报错*/
public class StringToInt {
	public static void main(String[] args) {
		String i = "8";
		int x;
		x = Integer.parseInt(i);
		System.out.println(x);
	}
}