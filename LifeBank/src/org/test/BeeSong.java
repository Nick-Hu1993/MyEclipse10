package org.test;

public class BeeSong {
	public static void main(String[] args) {
		int beerNum = 99;
		String word = "bottle";
		while (beerNum > 0) {
			if (beerNum == 1){
				word = "bottle";
			}
			System.out.println(beerNum+ " " +word+ " " +"on the war.");
			System.out.println(beerNum+ " " +word+ " " +"of beer.");
			System.out.println("Take one down.");
			System.out.println("Pass it around.");
			beerNum = beerNum - 1;
		}
		if(beerNum > 0) {
		System.out.println(beerNum+ " " +word+ " " +"on the war.");
		System.out.println(beerNum+ " " +word+ " " +"of beer.");
	} else {
		System.out.println("no bottle on the war.");
		}
	}
}