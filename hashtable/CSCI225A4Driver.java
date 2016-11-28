package a4;

//File:        CSCI225A4Driver.java
//Author:      Geoffrey Tien
//           (your name here)
//Date:        November 16, 2016
//Description: Partial test driver for CSCI 225 assignment #4
//           Ensure that the "a4-names.txt" file is in your
//           compiled binary folder.
//           It is highly recommended for you to add your own testing
//           code to this file.

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class CSCI225A4Driver {
	static Random r = new Random(System.nanoTime());

	public static void main(String[] args) {
		String[] names = ReadFile("a4-names.txt");

		LLTest1(names);
		LLTest2(names);
		//HTTest1(names);
		//HTTest2(names);
	}

	private static void LLTest1(String[] arr) {
		System.out.println("BEGIN LINKED LIST TEST 1");
		int numitemstoinsert = 50;
		int numitemstosearch = 10;
		int numitemstoremove = 10;

		A4LinkedList lla = new A4LinkedList();

		// 25 items, InsertFront
		for (int i = 0; i < numitemstoinsert / 2; i++) {
			lla.InsertFront(arr[r.nextInt(arr.length)]);
		}

		// 25 items, InsertAt
		for (int i = 0; i < numitemstoinsert / 2; i++) {
			lla.InsertAt(arr[r.nextInt(arr.length)], r.nextInt(lla.Size()));
		}

		System.out.println(numitemstoinsert + " items inserted. Size: " + lla.Size());

		// 10 items, Contains
		for (int i = 0; i < numitemstosearch; i++) {
			String searchkey = arr[r.nextInt(arr.length)];
			System.out.print("Searching for " + searchkey + ": ");
			if (lla.Contains(searchkey))
				System.out.println("Found!");
			else
				System.out.println("Not found.");
		}

		// 10 items, Remove
		int removecount = 0;
		for (int i = 0; i < numitemstoremove; i++) {
			String removekey = arr[r.nextInt(arr.length)];
			System.out.print("Attempting to remove " + removekey + ": ");
			if (lla.Remove(removekey)) {
				System.out.println("Success!");
				removecount += 1;
			} else
				System.out.println("Not found.");
		}

		System.out.println("Removed " + removecount + " items. Size: " + lla.Size());
		System.out.println("END LINKED LIST TEST 1");
	}

	private static void LLTest2(String[] arr) {
		// add your own test code here
		A4LinkedList llb = new A4LinkedList();
		llb.InsertAt(1, 0);
		
		System.out.println(llb.Contains(1));
		llb.printlist();
	}

	private static void HTTest1(String[] arr) {
		System.out.println("BEGIN HASH TABLE TEST 1");
		int numitemstoinsert = 100;
		int numitemstosearch = 10;
		int numitemstoremove = 10;

		A4HashTable hta = new A4HashTable(100);
		// add 75 items into parameterized hash table,
		// should not cause resize
		for (int i = 0; i < 75; i++) {
			hta.Insert(arr[i]);
		}
		System.out.println("75 items inserted. Size: " + hta.Size() + ", Load factor: " + hta.LoadFactor());
		for (int i = 75; i < 101; i++) {
			hta.Insert(arr[i]);
		}
		System.out.println("26 more items inserted. Size: " + hta.Size() + ", Load factor: " + hta.LoadFactor());

		// 10 items, Contains
		for (int i = 0; i < numitemstosearch; i++) {
			String searchkey = arr[r.nextInt(arr.length)];
			System.out.print("Searching for " + searchkey + ": ");
			if (hta.Contains(searchkey))
				System.out.println("Found!");
			else
				System.out.println("Not found.");
		}

		// 10 items, Remove
		int removecount = 0;
		for (int i = 0; i < numitemstoremove; i++) {
			String removekey = arr[r.nextInt(arr.length)];
			System.out.print("Attempting to remove " + removekey + ": ");
			if (hta.Remove(removekey)) {
				System.out.println("Success!");
				removecount += 1;
			} else
				System.out.println("Not found.");
		}
		System.out.println("Removed " + removecount + " items. Size: " + hta.Size());
		System.out.println("END HASH TABLE TEST 1");
	}

	private static void HTTest2(String[] arr) {
		// add your own test code here
	}

	private static String[] ReadFile(String inputfilestring) {
		ArrayList<String> arrlists = new ArrayList<String>();
		try {
			URL path = CSCI225A4Driver.class.getResource(inputfilestring);
			File inputfile = new File(path.getFile());
			BufferedReader br = new BufferedReader(new FileReader(inputfile));
			String line;
			while ((line = br.readLine()) != null) {
				arrlists.add(line);
			}
			br.close();
			return arrlists.toArray(new String[0]);
		} catch (IOException e) {
			System.out.println("ReadFile: " + e.getMessage());
		}
		return null; // should not encounter this statement
	}
}
