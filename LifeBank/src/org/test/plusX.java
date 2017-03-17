package org.test;

public class plusX {
	public static void main(String[] args) {
		int y = 7;
		for(int x = 1; x < 8;x++) {
			y++;
//			System.out.println(y);
			if(x > 4) {
				System.out.println(++y + " ");
			}
			if(y > 14) {
				System.out.println(x);
				break;
			}
//			System.out.println(x);
		}
	}

}
