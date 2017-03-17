package org.test;

public class String_Array {
public static void main(String[] args) {
	//声明3个数组
	String[] wordListOne = {"钱肖", "马晓荣", "赵耿坚", "胡春海", "李蕾", "郑紫燕", "曾益华", "靳军涛", "靳党军", "谢江辉" };
	String[] wordListTwo = {"喜欢", "吃", "做", "玩", "喷射" , "看" };
	String[] wordListThree = {"青蛙", "鸡", "石头", "玻璃", "盒子", "铅笔", "眼睛", "八宝粥", "台灯", "手机", "大便", "尿", "猪粪", "王老吉", "绿萝", "马晓荣"};
	//计算出每组数据有多少个数据
	int OneList = wordListOne.length;
	int TwoList = wordListTwo.length;
	int ThreeList = wordListThree.length;
	//分别从每个数组中取出一个参数
	int rand1 = (int) (Math.random() * OneList);
	int rand2 = (int) (Math.random() * TwoList);
	int rand3 = (int) (Math.random() * ThreeList);
	//选取每个数组的参数
	String phrase = wordListOne[rand1] + wordListTwo[rand2] + wordListThree[rand3];
	//打印出参数
	System.out.println(phrase+"!");
}
}
