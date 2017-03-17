package org.test;

public class Loopy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x = 1;
		System.out.println("Before the loop");
		while (x == 4) {
			System.out.println("In the loop");
			System.out.println("Now the value of x is:" +x);
			x = x + 1;
		}
		System.out.println("This is after loop");
	}

}
