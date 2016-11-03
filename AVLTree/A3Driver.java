package csciA3;

//File:        A3driver.java
//Author:      Geoffrey Tien
//           (your name here)
//Date:        2016-10-30
//Description: Basic test driver for AVL tree class
//           You should add your own testing code to this file
//           Ensure that general and border cases for all methods
//           are thoroughly tested.

import java.util.Random;

public class A3driver {
	public static void main(String[] args) {
		System.out.println("Entering Test1:...");
		Test1();
		Test2();
	}

	public static void Test1() {
		Random r = new Random();
		int maxvalue = 50;
		int numitemstoinsert = 15;
		AVLTree treeA = new AVLTree();
		for (int i = 0; i < numitemstoinsert; i++) {
			treeA.Insert(new Integer(r.nextInt(maxvalue)));
		}
		System.out.println("Tree A:");
		System.out.println(treeA.PrintTree());
		System.out.println("Tree contains " + treeA.Size() + " items.");
		System.out.println("Tree height: " + treeA.Height());

		int numitemstoremove = 5;
		System.out.println("\nAttempting to remove " + numitemstoremove + " items...");
		for (int j = 0; j < numitemstoremove; j++) {
			int value = r.nextInt(maxvalue);
			System.out.print("Remove " + value + ": ");
			if (treeA.Remove(new Integer(value)))
				System.out.println("success!");
			else
				System.out.println("failed.");
		}
		System.out.println("Tree A:");
		System.out.println(treeA.PrintTree());
		System.out.println("Tree contains " + treeA.Size() + " items.");
		System.out.println("Tree height: " + treeA.Height());

		int numitemstofind = 3;
		System.out.println("Searching for " + numitemstofind + " item...");
		for (int k = 0; k < numitemstofind; k++) {
			int searchkey = r.nextInt(maxvalue);
			System.out.println("Tree contains " + searchkey + ": " + treeA.Contains(searchkey));
		}

		System.out.println("Creating tree B as a copy of tree A...");
		AVLTree treeB = new AVLTree(treeA);

		System.out.println("\nRemoving all elements of tree A...");
		treeA.RemoveAll();
		System.out.println("Tree A:");
		System.out.println(treeA.PrintTree());
		System.out.println("Tree A contains " + treeA.Size() + " items.");
		System.out.println("Tree A height: " + treeA.Height());

		System.out.println("Tree B:");
		System.out.println(treeB.PrintTree());
		System.out.println("Tree B contains " + treeB.Size() + " items.");
		System.out.println("Tree B height: " + treeB.Height());
	}

	public static void Test2() {
		AVLTree tree2 = new AVLTree();
		tree2.Insert(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16);
		System.out.println(tree2.PrintTree());
		System.out.print(tree2.Contains(15));
	}
}
