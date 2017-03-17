package org.test;

import java.util.HashSet;

public class testHashSet {
	public static void main(String[] args) {
		HashSet hs = new HashSet();
		hs.add(1);
		hs.add(3);
		hs.add(6);
		hs.add(8);
		hs.add(8);
		System.out.println(hs);
	}
}