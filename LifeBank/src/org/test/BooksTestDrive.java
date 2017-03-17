package org.test;

public class BooksTestDrive {
	public static void main(String[] args) {
		//分配数组内存
		Book[] b = new Book[3];
		//实例化三本书的对象
		b[0]  = new Book();
		b[1]  = new Book();
		b[2]  = new Book();
		//给每本书的对象特征赋予参数
		b[0].title = "《Java 编程思想》";
		b[1].title = "《Java 从入门到放弃》";
		b[2].title = "《Java 你好么》";
		b[0].author = "胡春海";
		b[1].author = "钱肖";
		b[2].author = "靳军涛";
		//挨个的输出打印
		int i = 0;
		while (i < 3) {
			System.out.println(b[i].title + "的作者是" + b[i].author);
			i++;
		}
	}
}

class Book {
	String title;
	String author;
}
