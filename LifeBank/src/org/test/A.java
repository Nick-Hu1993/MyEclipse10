package org.test;

public class A  extends B{
	public static void main(String[] args) {
		//继承之后可以直接调用父类的方法无需实例化
	  eat();
	  //此处不实例化将无法调用父类的方法，是由于主方法中声明了static属性，
	  //当这种时候即使继承了父类，也需要实例化对象，才能调用父类中非静态的方法
	  B h = new B();
	  h.c();
	}
}

class B {
	 static void eat(){
		System.out.println("我要吃掉马晓荣");
	}
	
	 void c(){
		System.out.println("哇哇！别吃我。");
	}
}