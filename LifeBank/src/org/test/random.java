package org.test;
/*
 * 了解了解随机函数和强制类型转换*/
public class random {
	public static void main(String[] args) {
		//random()的返回值是一个double类型的，但此处要求返回0-5之间的整数
		int randomNum =(int) (Math.random()*5);
		System.out.println(randomNum);
	}
}
		
