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
 * In this implementation of the ISet interface the elements in the Set are 
 * maintained in ascending order.
 * 
 * The data type for E must be a type that implements Comparable.
 * 
 * Students are to implement methods that were not implemented in AbstractSet 
 * and override methods that can be done more efficiently. An ArrayList must 
 * be used as the internal storage container. For methods involving two set, 
 * if that method can be done more efficiently if the other set is also a 
 * SortedSet do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {

    private ArrayList<E> myCon;

    /**
     * create an empty SortedSet
     */
    public SortedSet() {
        myCon = new ArrayList<>();
    }
    /**
     * create a SortedSet out of an unsorted set. <br>
     * @param other != null
     */
    public SortedSet(ISet<E> other) {
        
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
    
    public boolean addAll(ISet<E> otherSet)
    {
        if(otherSet == null)
            throw new IllegalArgumentException("cannot add null set");
        if(otherSet instanceof SortedSet)
        {
            int origSize = myCon.size();
            myCon = merge((SortedSet<E>) otherSet);
            return origSize == myCon.size();
        }
        else
        {
            return super.addAll(otherSet);
        }
    }
    
    private ArrayList<E> merge(SortedSet<E> otherSet)
    {
        int thisIndex = 0;
        int otherIndex = 0;
        ArrayList<E> result = new ArrayList<>();
        while(thisIndex < this.size() && otherIndex < otherSet.size())
        {
            E currThis = myCon.get(thisIndex);
            E currOther = otherSet.myCon.get(otherIndex);
            int compare = currThis.compareTo(currOther);
            if(compare < 0)
            {
                result.add(currThis);
                thisIndex++;
            }
            else if(compare >  0 )
            {
                result.add(currOther);
                otherIndex++;
            }
            else //if equal, add one of them and then advance both indices (no dupes)
            {
                result.add(currThis);
                thisIndex++;
                otherIndex++;
            }
        }
        
        while(thisIndex < this.size())
        {
            result.add(myCon.get(thisIndex));
            thisIndex++;
        }
        while(otherIndex < otherSet.size())
        {
            result.add(otherSet.myCon.get(otherIndex));
            otherIndex++;
        }
        
        return result;
        
    }
    
    //dont need clear, remove
    
    public boolean contains(E itemToFind)
    {
        if(itemToFind == null)
            throw new IllegalArgumentException("cant contain null");
        
        return binarySearch(itemToFind);
    }
    
    private boolean binarySearch(E itemToFind)
    {
        int low = 0;
        int high = size()-1;
        while(low <= high)
        {
            int mid = low+high/2;            
            E currMid = myCon.get(mid);
            int compare = itemToFind.compareTo(currMid);
            if(compare < 0)
            {
                high = mid - 1;
            }
            else if(compare > 0)
            {
                low = mid + 1;
            }
            else
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean containsAll(ISet<E> otherSet)
    {
        if(otherSet == null)
            throw new IllegalArgumentException("containsAll for null ?");
        
        if(otherSet.size() > this.size())
            return false;
        
        if(otherSet instanceof SortedSet)
        {
            return sortedContainsAll((SortedSet<E>) otherSet);
        }
        else
        {
            return super.containsAll(otherSet);
        }
    }
    
    private boolean sortedContainsAll(SortedSet<E> otherSet)
    {
        int thisIndex = 0;
        int otherIndex = 0;
        while(thisIndex < this.size())
        {
            E thisVal = myCon.get(thisIndex);
            if(otherIndex < otherSet.size())
            {
                E otherVal = otherSet.myCon.get(otherIndex);
                int compare = thisVal.compareTo(otherVal);
                if(compare > 0)
                    return false;
                else if(compare < 0)
                    thisIndex++;
                else
                {
                    thisIndex++;
                    otherIndex++;
                }                    
            }
        }
        return true;
    }
    
    public ISet<E> difference(ISet<E> otherSet)
    {
        if(otherSet == null)
            throw new IllegalArgumentException("otherSet cannot be null");
        
        if(otherSet instanceof SortedSet)
        {
            return sortedDifference((SortedSet<E>) otherSet);
        }
        else
        {
            return super.difference(otherSet);
        }
    }
    
    private SortedSet<E> sortedDifference(SortedSet<E> otherSet)
    {
        SortedSet<E> result = new SortedSet<>();
        int thisIndex = 0;
        int otherIndex = 0;
        
        while(thisIndex < size() && otherIndex < otherSet.size())
        {
            E val = myCon.get(thisIndex);
            E otherVal = otherSet.myCon.get(otherIndex);
            int compare = val.compareTo(otherVal);
            if(compare < 0)
            {
                result.add(val);
                thisIndex++;
            }
            else if(compare > 0)
            {
                otherIndex++;
            }
            else                
            {
                thisIndex++;
                otherIndex++;
            }
        }
        //in case this had a "larger" final element than other
        while(thisIndex < size())
        {
            result.add(myCon.get(thisIndex));
            thisIndex++;
        }
        return result;
    }
    
    public ISet<E> union(ISet<E> otherSet)
    {
        if(otherSet == null)
            throw new IllegalArgumentException("cant be null union");
        
        if(!(otherSet instanceof SortedSet))
        {
            //why make it O(N^2)? make it O(N) by making a new sortedSet and do union from that
            SortedSet<E> sortedOtherSet = new SortedSet<>(otherSet);  
            return sortedUnion(sortedOtherSet);
        }
        else
        {            
            return sortedUnion((SortedSet<E>) otherSet);
        }
    }
    
    private SortedSet<E> sortedUnion(SortedSet<E> otherSet)
    {
        if(otherSet == null)
            throw new IllegalArgumentException("can't find union of null");        
        SortedSet<E> result = new SortedSet<>();        
        result.merge(this);
        result.merge(otherSet);
        
        return result;
    }
    
    public ISet<E> intersection(ISet<E> otherSet)
    {
        if(!(otherSet instanceof SortedSet))
        {
            //why make it O(N^2)? make it O(N) by making a new sortedSet and do intersection from that
            SortedSet<E> sortedOtherSet = new SortedSet<>(otherSet);  
            return sortedIntersection(sortedOtherSet);
        }
        else
        {            
            return sortedIntersection((SortedSet<E>) otherSet);
        }
    }
    
    private SortedSet<E> sortedIntersection(SortedSet<E> otherSet)
    {
        SortedSet<E> result = new SortedSet<>();
        int thisIndex = 0;
        int otherIndex = 0;
        
        while(thisIndex < size() && otherIndex < otherSet.size())
        {
            E val = myCon.get(thisIndex);
            E otherVal = otherSet.myCon.get(otherIndex);
            int compare = val.compareTo(otherVal);
            if(compare < 0)
            {
                thisIndex++;
            }
            else if(compare > 0)
            {
                otherIndex++;
            }
            else                
            {
                result.add(val);
                thisIndex++;
                otherIndex++;
            }
        }
        return result;
    }

    
    public Iterator<E> iterator()
    {
        return myCon.iterator();
    }
    
    public int size()
    {
        return myCon.size();
    }
 
    /**
     * Return the smallest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the smallest element in this SortedSet.
     */
    public E min() {
        if(size() == 0)
            throw new IllegalStateException("cant get the minimum element if there is no elements");
        return myCon.get(0);
    }

    /**
     * Return the largest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the largest element in this SortedSet.
     */
    public E max() {
        if(size() == 0)
            throw new IllegalStateException("cant get the maximum element if there is no elements");
        return myCon.get(size()-1);
    }



}
