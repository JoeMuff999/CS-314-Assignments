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

/**
 * Students are to complete this class. 
 * Students should implement as many methods
 * as they can using the Iterator from the iterator 
 * method and the other methods. 
 *
 */
public abstract class AbstractSet<E> implements ISet<E> {

    /* NO INSTANCE VARIABLES ALLOWED.
     * NO DIRECT REFERENCE TO UnsortedSet OR SortedSet ALLOWED.
     * (In other words the data types UnsortedSet and SortedSet
     * will not appear any where in this class.)
     * Also no direct references to ArrayList or other Java Collections.
     */
    /**
     * skeleton add
     * throws UnsupportedOperationException if no implementation of add in child class
     * Add an item to this set.
     * <br> item != null
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation, false otherwise.
     */
    public boolean add(E e)
    {
        throw new UnsupportedOperationException("Method boolean add(E e) not implemented in child class " + this.getClass());
    }
    /**
     * throws UnsupportedOperationException if no implementation of add in child class
      * A union operation. Add all items of otherSet that are not already present in this set
      * to this set.
      * @param otherSet != null
      * @return true if this set changed as a result of this operation, false otherwise.
     */
    public boolean addAll(ISet<E> otherSet)
    {
        if(otherSet == null)
            throw new IllegalArgumentException("cannot add null");
        
        boolean isSetModified = false;
        for(E otherVal : otherSet)
        {
            if(add(otherVal))
                isSetModified = true;
        }
        return isSetModified;
    }
    
    /**
     * Make this set empty.
     * <br>pre: none
     * <br>post: size() = 0
     */
    public void clear()
    {
        Iterator<E> setIterator = this.iterator();
        while(setIterator.hasNext())
        {
            setIterator.next();
            setIterator.remove();
        }
    }
    /**
     * Determine if item is in this set. 
     * <br>pre: item != null
     * @param itemToFind element whose presence is being tested. Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     */
    public boolean contains(E itemToFind)
    {
        if(itemToFind == null)
            throw new IllegalArgumentException("cannot contain null value");
        
        Iterator<E> setIterator = this.iterator();
        while(setIterator.hasNext())
        {
            if(setIterator.next().equals(itemToFind))
                return true;
        }
        return false; 
    }
    /**
     * Determine if all of the elements of otherSet are in this set.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return true if this set contains all of the elements in otherSet, 
     * false otherwise.
     */
    public boolean containsAll(ISet<E> otherSet)
    {
        if(otherSet == null)
            throw new IllegalArgumentException("cannot containsAll null value");
        
        for(E itemToFind : otherSet)
        {
            if(!contains(itemToFind))
                return false;
        }
        return true;
    }
    
    /**
     * Create a new set that is the difference of this set and otherSet. Return an ISet of 
     * elements that are in this Set but not in otherSet. Also called
     * the relative complement. 
     * <br>Example: If ISet A contains [X, Y, Z] and ISet B contains [W, Z] then
     * A.difference(B) would return an ISet with elements [X, Y] while
     * B.difference(A) would return an ISet with elements [W]. 
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the difference of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the difference of this set and otherSet
     */
    public ISet<E> difference(ISet<E> otherSet)
    {
        if(otherSet == null)
            throw new IllegalArgumentException("otherSet cannot be null");
        
        ISet<E> thisSet = union(this); //getting this set
        ISet<E> intersection = intersection(otherSet); 
        
        Iterator<E> thisIterator = thisSet.iterator();
        while(thisIterator.hasNext())
        {
            //Set A = this, Set B = other. If A and B contain currVal, remove from A
            E currVal = thisIterator.next();
            if(intersection.contains(currVal))
                thisIterator.remove();                    
        }
        return thisSet;
    }
    /*
     * skeleton intersection
     * throws UnsupportedOperationException if child class does not implement this method
     * create a new set that is the intersection of this set and otherSet.
     * <br>pre: otherSet != null<br>
     * <br>post: returns a set that is the intersection of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the intersection of this set and otherSet
     */
     
    public ISet<E> intersection(ISet<E> otherSet)
    {
        throw new UnsupportedOperationException("Method ISet<E> intersection(ISet<E> otherSet) not implemented in child class " + this.getClass());
    }
    
    /*
     * skeleton union
     * throws UnsupportedOperationException if child class does not implement this method
     * Create a new set that is the union of this set and otherSet.
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the union of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the union of this set and otherSet
     */
     
    public ISet<E> union(ISet<E> otherSet)
    {
        throw new UnsupportedOperationException("Method ISet<E> union(ISet<E> otherSet) not implemented in child class " + this.getClass());
    }
        
    /**
     * Return the number of elements of this set.
     * pre: none
     * @return the number of items in this set
     */
    public boolean remove(E itemToRemove)
    {
        if(itemToRemove == null)
            throw new IllegalArgumentException("cannot remove null in method boolean remove(E itemToRemove)");
        
        Iterator<E> setIterator = this.iterator();
        while(setIterator.hasNext())
        {
            if(setIterator.next().equals(itemToRemove))
            {
                setIterator.remove();
                return true;
            }
        }
        return false;
    }
    /**
     * Return the number of elements of this set.
     * pre: none
     * @return the number of items in this set
     */
    public int size()
    {
        int size = 0;
        Iterator<E> setIterator = iterator();
        while(setIterator.hasNext())
        {
            setIterator.next();
            size++;
        }
        return size;
    }
    
    /**
     * throws UOE if child class doesn't implement iterator()
     * Return an Iterator object for the elements of this set.
     * pre: none
     * @return an Iterator object for the elements of this set
     */
    public Iterator<E> iterator()
    {
        throw new UnsupportedOperationException("Method Iterator<E> iterator() not implemented by child class " + this.getClass());
    }
    /**
     * Determine if this set is equal to other.
     * Two sets are equal if they have exactly the same elements.
     * The order of the elements does not matter.
     * <br>pre: none
     * @param other the object to compare to this set 
     * @return true if other is a Set and has the same elements as this set
     */
    public boolean equals(Object other)
    {
        //check if ISet
        if (!(other instanceof ISet))
        {
            return false;
        }
        ISet<?> otherISet = (ISet<?>) other;

        if (otherISet.size() != this.size())
        {
            //if size not equal, not same set
            return false;
        } 
        
        Iterator<E> thisIterator = this.iterator();
         
        while (thisIterator.hasNext())
        {
            //whenever the elements aren't the same, they are not equal
            E val = thisIterator.next();
            boolean found = false;
            Iterator<?> otherIterator = otherISet.iterator();
            while(!found && otherIterator.hasNext())
            {
                found = otherIterator.next() == val; 
            }
            
            if(!found)
                return false;
        }
        return true;
    }

    
    public String toString() {
        StringBuilder result = new StringBuilder();
        String seperator = ", ";
        result.append("(");

        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            result.append(it.next());
            result.append(seperator);
        }
        // get rid of extra separator
        if (this.size() > 0)
            result.setLength(result.length() - seperator.length());

        result.append(")");
        return result.toString();
    }
}
