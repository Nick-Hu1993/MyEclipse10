package org.test;

import java.util.ArrayList;

public class ArrayListMagnet 
{
	public static void main(String[] args) 
	{
		ArrayList<String> a = new ArrayList<String>();
		a.add(0, "zero");
		a.add(1, "one");
		a.add(2,"two");
		if(a.contains("two"))
		{
			a.add(3,"three");
			printAL(a);
		}
		if(a.contains("three")) 
		{
			a.remove(2); 
			a.add("four");
			printAL(a);
		}
		if(a.indexOf("four") != 4) 
		{
			a.add(4, "4.2");
			printAL(a);
			printAL(a);
		}
	}
	
	public static void printAL (ArrayList<String> a1) 
		{
			for(String element: a1) 
			{
				System.out.print(element + "  ");
			}
			System.out.println("  ");
		}
}
