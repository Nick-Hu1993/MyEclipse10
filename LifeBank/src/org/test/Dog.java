package org.test;

class Dog {
	public static void main(String[] args) {
		Dog[] d = new Dog[3];//数组分配空间
		System.out.println(d[0]);//验证只是分配了磁盘空间
		
		d[0] = new Dog();
		d[1] = new Dog();
		d[2] = new Dog();
		System.out.println(d.length);
		
		int i = 0;
		while (i < d.length) {
			d[i].dark();
			i = i + 1;
		}
	}


public void dark() {
		System.out.println("汪汪！");
	}
}