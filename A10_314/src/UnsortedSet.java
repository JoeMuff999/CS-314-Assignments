/*  Student information for assignment:
 *
 *  On <MY|OUR> honor, <NAME1> and <NAME2), this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *  Number of slip days used:
 *
 *  Student 1 (Student whose turnin account is being used)
 *  UTEID:
 *  email address:
 *  Grader name:
 *  Section number:
 *  
 *  Student 2 
 *  UTEID:
 *  email address:
 *  Grader name:
 *  Section number:
 *  
 */

import java.util.Iterator;
import java.util.ArrayList;

/**
 * A simple implementation of an ISet. 
 * Elements are not in any particular order.
 * Students are to implement methods that 
 * were not implemented in AbstractSet and override
 * methods that can be done more efficiently. 
 * An ArrayList must be used as the internal storage container.
 *
 */
public class UnsortedSet<E> extends AbstractSet<E> {
    
	private ArrayList<E> myCon;
	
	/*
	 * default constructor
	 */
	public UnsortedSet()
	{
	    myCon = new ArrayList<>();
	}
	
	/* Add an item to this set.
     * <br> item != null
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation, false otherwise.
     */	
	public boolean add(E valToAdd)
	{
	    if(valToAdd == null)
	        throw new IllegalArgumentException("cannot add null");

	    if(!myCon.contains(valToAdd))
	    {
	        myCon.add(valToAdd);
	        return true;
	    }
	    return false;
	}

	// dont need addAll, clear, contains, containsAll, remove, equals, difference (still N^2 for big O)
	
    /* create a new set that is the intersection of this set and otherSet.
    * <br>pre: otherSet != null<br>
    * <br>post: returns a set that is the intersection of this set and otherSet.
    * Neither this set or otherSet are altered as a result of this operation.
    * <br> pre: otherSet != null
    * @param otherSet != null
    * @return a set that is the intersection of this set and otherSet
    */
	public ISet<E> intersection(ISet<E> otherSet)
	{
	    if(otherSet == null)
	        throw new IllegalArgumentException("can't find intersection of null");
	    ISet<E> result = new UnsortedSet<>();
	    for(E val : myCon)
	    {
	        if(otherSet.contains(val))
	            result.add(val);
	    }
	    return result;
	}
    /* Create a new set that is the union of this set and otherSet.
    * <br>pre: otherSet != null
    * <br>post: returns a set that is the union of this set and otherSet.
    * Neither this set or otherSet are altered as a result of this operation.
    * <br> pre: otherSet != null
    * @param otherSet != null
    * @return a set that is the union of this set and otherSet
    */
	public ISet<E> union(ISet<E> otherSet)
	{
	    if(otherSet == null)
            throw new IllegalArgumentException("can't find union of null");
	    ISet<E> result = new UnsortedSet<>();
	    result.addAll(this);
	    result.addAll(otherSet);	   
	    return result;
	}
    /**
     * Return the number of elements of this set.
     * pre: none
     * @return the number of items in this set
     */
	public int size()
	{
	    return myCon.size();
	}
	
	/*
    * Return an Iterator object for the elements of this set.
    * pre: none
    * @return an Iterator object for the elements of this set
    */
	public Iterator<E> iterator()
	{
	    return myCon.iterator();
	}
}
