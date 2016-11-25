// File:        A4LinkedList.java
// Author:      Geoffrey Tien
//              (your name here)
// Date:        November 16, 2016
// Description: Singly-linked list class for CSCI 225 assignment #4

class A4ListNode {
  public Comparable data;
  public A4ListNode next;
  
  // constructor
  public A4ListNode(Comparable d)
  {
    data = d;
    next = null;
  }
  
  // parameterized constructor
  public A4ListNode(Comparable d, A4ListNode n)
  {
    data = d;
    next = n;
  }
}

public class A4LinkedList {
  ///////////////////////////////
  // private attributes
  ///////////////////////////////
  private A4ListNode front;
  private int size;
  
  
  ///////////////////////////////
  // private methods
  ///////////////////////////////
  
  
  
  ///////////////////////////////
  // public methods
  ///////////////////////////////
  
  // default constructor
  public A4LinkedList()
  {
    front = null;
    size = 0;
  }
  
  // copy constructor
  // creates a new A4LinkedList instance as a deep copy of the parameter
  // The new list must have the same size and order of elements as the source.
  public A4LinkedList(A4LinkedList sourcelist)
  {
    // to be completed
    
  }
  
  // ACCESSORS
  public boolean IsEmpty()
  {
    return size == 0;
  }
  
  public int Size()
  {
    return size;
  }
  
  // Linear search
  // Return true if the list contains item, false otherwise
  public boolean Contains(Comparable item)
  {
    // to be completed
    return false;
  }
  
  // returns the list contents to an array
  // The array size should be exactly the number of
  // elements in the list
  public Comparable[] ToArray()
  {
    // to be completed
    return null;
  }
  
  // MUTATORS
  
  // Insert an item at the front of the list
  public void InsertFront(Comparable item)
  {
    // to be completed
    
  }
  
  // Insert an item at the specified index (0-indexed)
  // Do not insert if index is invalid
  // POST: item is in the list at index
  // note - for an empty list, index 0 is valid
  //        likewise for inserting at the back of a non-empty list
  public void InsertAt(Comparable item, int index)
  {
    // to be completed
    
  }
  
  // Removes the first occurrence of specified item from the list
  // Remove and return true if the item is found
  // List is unchanged and return false if item is not found
  public boolean Remove(Comparable item)
  {
    // to be completed
    return false;
  }
}
