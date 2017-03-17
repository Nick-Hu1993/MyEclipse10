package org.test;

import java.util.ArrayList;

public class testArrayList {
public static void main(String[] args) {
	//创建一个ArrayList,类型为Egg
	ArrayList<Egg> myList = new ArrayList<Egg>();
	//创建2个Egg实例
	Egg a = new Egg();
	Egg b = new Egg();
	//给Egg的参数加入数据
	a.setName("钱肖");
	a.getName();
	System.out.println(a.getName());
	//将这个a加入到ArrayList中
	myList.add(a);
	myList.add(b);
	//查询myList的大小
	int thesize = myList.size();
	System.out.println(myList.size());
	//查询特定元素是否存在
	boolean isIn = myList.contains(a);
	System.out.println(myList.contains(a));
	//查询特定元素的位置
	int idx = myList.indexOf(a);
	System.out.println(myList.indexOf(b));
	//判断集合是否为空
	boolean empty = myList.isEmpty();
	System.out.println(myList.isEmpty());
	//以索引方式删除元素（此处0的位置为a）
	myList.remove(0);
	//查看是否删除成功
	for(Egg i:myList) {
		System.out.println(i);		
	}
}
}

//使用封装
class Egg{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}