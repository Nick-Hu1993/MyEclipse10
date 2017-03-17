package org.test;

public class Rabbit extends Animal {
	public static void main(String[] args) {
		Animal rabbit = new Rabbit();
		rabbit.have();
	}
}

class Animal{
	void have(){
		System.out.println("我喜欢吃胡萝卜，嘎嘎");
	}
}
