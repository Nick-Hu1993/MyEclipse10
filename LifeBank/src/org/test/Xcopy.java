package org.test;

public class Xcopy {
	int go(int arg) {
		arg = arg * 2;
		return arg;
	}
public static void main(String[] args) {
	int orig = 42;
	Xcopy x = new Xcopy();
	int y = x.go(orig);
	System.out.println(orig + " " +y);
	}
}
