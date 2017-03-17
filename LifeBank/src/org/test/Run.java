package org.test;

public class Run extends man implements action {
	public static void main(String[] args) {
		//实例化对象之后调用抽象类与/接口中的方法
		kk();//直接可以调用是因为主方法和该方法都是静态的
		Run a = new Run();
		a.look();//调用重写之后的方法
		a.setAge(7);//设置类中的参数
		a.getAge();//获取类中的参数
		System.out.println(a.getAge());
	}

	public void look() {
		System.out.println("我是被重写的action接口的look方法" + age);
	}
}

//声明一个抽象类
abstract class man{
	private String name;
	private int age;
	
	public static void kk(){
		System.out.println("我是man中的方法");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}

//声明一个接口，接口中的变量默认都是public，final，static
interface action {
	final int age = 10;
	public void look();
}