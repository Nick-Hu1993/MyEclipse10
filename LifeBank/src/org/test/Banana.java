package org.test;

public class Banana {
public static void main (String[] args) {
	//常规调用
	Bibi k = new Bibi();
	k.go();
	k.come();
	}
}

class Bibi {
	void go() {
	System.out.println("我要削皮");
		}
static void come(){
	System.out.println("我要吃了你");
		}
}
