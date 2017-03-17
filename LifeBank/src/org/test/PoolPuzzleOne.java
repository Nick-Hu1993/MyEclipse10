package org.test;

public class PoolPuzzleOne {
	public static void main(String[] args) {
		int x = 0;
		
		while (x < 1) {
			System.out.print("a");
			System.out.print(" ");
		
		if (x < 4) {
			x = x + 2;
		}
		System.out.println("noise");
		
		if (x > 1){
			System.out.println("annoys");
			x = x-1;
		}
		
		if (x == 1) {
			System.out.print("an");
		}
		
		if (x > 0) {
			System.out.print(" oyster");
		}
		System.out.print(" ");
		x = x + 1;
		}
	}
}
