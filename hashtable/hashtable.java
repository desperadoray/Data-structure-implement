package a4;
// File:        A4LinkedList.java

// Author:      Geoffrey Tien
//              (your name here)
// Date:        November 16, 2016
// Description: Hash table class for CSCI 225 assignment #4
//              Uses separate chaining with singly-linked list
//              Strings are converted using Horner's method base-26
//              a = 0, b = 1, ... , z = 25
//              Assume that we will only hash lower-case character strings
//              with no whitespace or punctuation.
//              This hash table does NOT store duplicates. Check for existence
//                before inserting any key value.

public class A4HashTable {
	///////////////////////////////
	// private attributes
	///////////////////////////////
	private A4LinkedList[] table;
	private int arrsize;
	private final int defaultarrsize = 53; // default table size
	private final double loadfactorthreshold = 0.75; // maximum load factor
	private int size;

	///////////////////////////////
	// private methods
	///////////////////////////////

	// Finds the smallest prime number larger than n
	private int NextPrime(int n) {
		int result = n;
		if (result < defaultarrsize)
			result = defaultarrsize;
		while (!IsPrime(result)) {
			result += 1;
		}
		return result;
	}

	// Checks if a number is prime
	private boolean IsPrime(int n) {
		if (n < 2)
			return false;
		if (n == 2)
			return true;
		if (n % 2 == 0)
			return false;
		return IsPrime(n, 3);
	}

	private boolean IsPrime(int n, int divisor) {
		if ((divisor * divisor) > n)
			return true;
		if ((n % divisor) == 0)
			return false;
		return IsPrime(n, divisor + 2);
	}

	// performs Horner's method on string parameter, base-26
	// Use a = 0, b = 1, ... , z = 25
	// Assume that we will only hash lower-case character strings
	// with no whitespace or punctuation.
	// A single char can be cast to an ASCII integer,
	// e.g. char c = 'a';
	// int asciival = (int) c;
	// The character 'a' has ASCII value 97, thus subtracting 97
	// from the cast value will give 0 to 25 for 'a' to 'z'
	public int Horner(String item) {
		// to be completed
		int length = item.length();
		int result = 0;
		for (int i = 0; i < length; i++) {
			// result += ((int)item.charAt(i)-97) * Math.pow(26, item.length() -
			// i - 1);
			result = (result * 26 + ((int) item.charAt(i) - 97)) % arrsize;
		}
		//System.out.println(item + result);
		return result;
	}

	///////////////////////////////
	// public methods
	///////////////////////////////

	// default constructor
	// You must initialize each array element as a new A4LinkedList object
	public A4HashTable() {
		// to be completed
		arrsize = defaultarrsize;
		table = new A4LinkedList[arrsize];
		for (int i = 0; i < arrsize; i++)
			table[i] = new A4LinkedList();
		size = 0;
	}

	// parameterized constructor
	// new hash table is created with array size
	// as the next prime number larger than parameter
	public A4HashTable(int tablesize) {
		// to be completed
		arrsize = NextPrime(tablesize);
		table = new A4LinkedList[arrsize];
		for (int i = 0; i < arrsize; i++)
			table[i] = new A4LinkedList();
		size = 0;
	}

	// Returns a (deep) copy of the linked list at index i
	// Note - return the copy, so that the list in the hash table
	// is not modified in case the user calls mutator methods on the
	// returned list.
	public A4LinkedList ListAt(int i) {
		A4LinkedList list = new A4LinkedList(table[i]);
		return list;
	}

	// Hash function
	// Normally this is made private, but for testing/grading purposes
	// this is public
	// Hash function: h(x) = (x * 17) % arrsize
	// where x is an integer returned by Horner's method
	public int Hash(String item) {
		int x = Horner(item);
		// System.out.println(x);
		long tmp = (long) x * 17;
		// System.out.println(tmp);
		long res = tmp % arrsize;
		// System.out.println(res);
		return (int) res;

	}

	// ACCESSORS

	// Returns the number of key values in the hash table
	public int Size() {
		return size;
	}

	// Returns the current load factor
	public double LoadFactor() {
		return (double) size / (double) arrsize;
	}

	// Hash table search
	// Uses linked list search after hashing
	public boolean Contains(String item) {
		// to be completed
		// System.out.println(item);
		int index = Hash(item);
		// System.out.println(index);
		// System.out.println(arrsize);
		if (table[index].IsEmpty())
			return false;
		else if (table[index].Contains(item))
			return true;
		return false;
	}

	// MUTATORS

	// Insert an item into the hash table
	// If item does not exist already, insert and return true
	// Otherwise, hash table is unchanged and return false
	// If load factor exceeds threshold, then double the array
	// size and re-hash all items
	public boolean Insert(String item) {
		// to be completed
		if (this.LoadFactor() > 0.75) {
			int newarrsize = 2*arrsize;
			A4LinkedList[] newtable = new A4LinkedList[newarrsize];
			for(int i = 0; i < newarrsize; i++)
				newtable[i] = new A4LinkedList();
			for(int i = 0; i < arrsize; i++){
				if(!table[i].IsEmpty()){
					Comparable[] arr = table[i].ToArray();
					for(int j = 0; j < arr.length; j++){
						newtable[Hash((String)arr[j])].InsertFront(arr[j]);
					}
				}
			}
			table = newtable;
			arrsize = newarrsize;			
		}
		if (Contains(item))
			return false;
		else {
			table[Hash(item)].InsertFront(item);
			size++;
			return true;
		} // TO DO: resize
	}

	// Remove an item from the hash table
	// If item exists, remove and return true
	// Otherwise, hash table is unchanged and return false
	public boolean Remove(String item) {
		// to be completed
		return false;
	}
}
