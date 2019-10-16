
/*
 * Student information for assignment: On my honor, <NAME>, this programming
 * assignment is my own work and I have not provided this code to any other
 * student. 
 * UTEID: 
 * email address: 
 * Grader name: 
 * Number of slip days I am using:
 * 
 * what i need help with:
 * Cast warning for IList
 * remove method for iterator (which one do i remove?)
 * tests are not showing O(1), problems interpreting the testing data :(
 * casting in getSubList
 * 
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements IList<E> {
    // CS314 students. Add you instance variables here.
    // You decide what instance variables to use.
    // Must adhere to assignment requirements. No ArrayLists or Java
    // LinkedLists.

    private DoubleListNode<E> HEAD;
    private int size;
    // CS314 students, add constructors here:
    public LinkedList()
    {
        HEAD = new DoubleListNode<E>();
        HEAD.setNext(HEAD);
        HEAD.setPrev(HEAD);
    }
    // CS314 students, add methods here:

    /**
     * add item to the front of the list. <br>
     * pre: item != null <br>
     * post: size() = old size() + 1, get(0) = item
     * 
     * @param item the data to add to the front of this list
     */
    public void addFirst(E item) {
        if(item == null)
            throw new IllegalArgumentException("Cannot add object of value null to linked list");
        
        DoubleListNode<E> nodeToAdd = new DoubleListNode<E>(HEAD,item,HEAD.getNext());
        HEAD.getNext().setPrev(nodeToAdd);
        HEAD.setNext(nodeToAdd);
        
        size++;
    }

    /**
     * add item to the end of the list. <br>
     * pre: item != null <br>
     * post: size() = old size() + 1, get(size() -1) = item
     * 
     * @param item the data to add to the end of this list
     */
    public void addLast(E item) {
        if(item == null)
            throw new IllegalArgumentException("Cannot add object of value null to linked list");
        
        DoubleListNode<E> nodeToAdd = new DoubleListNode<E>(HEAD.getPrev(),item,HEAD);
        HEAD.getPrev().setNext(nodeToAdd);
        HEAD.setPrev(nodeToAdd);
        
        size++;
        
        
    }

    /**
     * remove and return the first element of this list. <br>
     * pre: size() > 0 <br>
     * post: size() = old size() - 1
     * 
     * @return the old first element of this list
     */
    public E removeFirst() {
        if(size <= 0)
            throw new IllegalArgumentException("cannot remove from list with size <= 0");
        return remove(0);
    }

    /**
     * remove and return the last element of this list. <br>
     * pre: size() > 0 <br>
     * post: size() = old size() - 1
     * 
     * @return the old last element of this list
     */
    public E removeLast() {
        if(size <= 0)
            throw new IllegalArgumentException("can't remove from empty list");
        E toReturn = HEAD.getPrev().getData();
        HEAD.getPrev().getPrev().setNext(HEAD);
        HEAD.setPrev(HEAD.getPrev().getPrev());
        size--;
        return toReturn;
    }
    /**
     * Return the size of this list. In other words the number of elements in this list.
     * <br>pre: none
     * <br>post: return the number of items in this list
     * @return the number of items in this list
     */
    public int size()
    {
        return size;
    }
    /**
     * Return a String version of this list enclosed in
     * square brackets, []. Elements are in
     * are in order based on position in the 
     * list with the first element
     * first. Adjacent elements are separated by comma's
     * @return a String representation of this IList
     */
    public String toString()
    {
        Iterator<E> iterator = this.iterator();
        if(!iterator.hasNext()) //because of the way my comma logic works, I need to make sure the list isn't empty
            return "[]";
        
        StringBuilder result = new StringBuilder();
        
        result.append("[" + iterator.next());
        while(iterator.hasNext())
        {
            result.append(", ");
            result.append(iterator.next());            
        }
        result.append("]");
        return result.toString();
    }
    /**
     * Add an item to the end of this list.
     * <br>pre: item != null
     * <br>post: size() = old size() + 1, get(size() - 1) = item
     * @param item the data to be added to the end of this list, item != null
     */
    @Override
    public void add(E item)
    {
        addLast(item);        
    }
    /**
     * Insert an item at a specified position in the list.
     * <br>pre: 0 <= pos <= size(), item != null
     * <br>post: size() = old size() + 1, get(pos) = item, all elements in
     * the list with a positon >= pos have a position = old position + 1
     * @param pos the position to insert the data at in the list
     * @param item the data to add to the list, item != null
    */
    @Override
    public void insert(int pos, E item)
    {
        if(pos < 0 || pos > size || item == null)
            throw new IllegalArgumentException("in method insert(int,E), int out of bounds || item is null");
        
        if(pos == 0)
        {
            addFirst(item);
        }
        else if(pos == size)
        {
            addLast(item);
        }
        else
        {
            DoubleListNode<E> end = getNode(pos);
            
            DoubleListNode<E> mid = new DoubleListNode<E>(end.getPrev(), item, end);
            end.getPrev().setNext(mid);
            end.setPrev(mid);
            size++;
        }        
    }
    /*
     * return a node based on position
     * pre: 0 <= pos <= size()
     * psot: returns a DoubleListNode<E> at index pos
     */
    private DoubleListNode<E> getNode(int pos)
    {
        if(pos > size() || pos < 0)
            throw new IllegalArgumentException("parameter int in method getNode(int) is out of bounds");
        
        DoubleListNode<E> currentNode = HEAD.getNext();
        for(int i = 0; i < pos; i++)
        {
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }
    /**
     * Change the data at the specified position in the list.
     * the old data at that position is returned.
     * <br>pre: 0 <= pos < size(), item != null
     * <br>post: get(pos) = item, return the
     * old get(pos)
     * @param pos the position in the list to overwrite  
     * @param item the new item that will overwrite the old item, item != null
     * @return the old data at the specified position
     */
    @Override
    public E set(int pos, E item)
    {
        if(pos < 0 || pos >= size || item == null)
            throw new IllegalArgumentException("in method set(int,E), int out of bounds || item is null");
        
        DoubleListNode<E> nodeToSet = getNode(pos);
        E oldData = nodeToSet.getData();
        nodeToSet.setData(item);
        return oldData;
    }
    /**
     * Get an element from the list.
     * <br>pre: 0 <= pos < size()
     * <br>post: return the item at pos
     * @param pos specifies which element to get
     * @return the element at the specified position in the list
     */
    @Override
    public E get(int pos)
    {
        if(pos >= size() || pos < 0)
            throw new IllegalArgumentException("parameter int in method get(int) is out of bounds");
                
        return getNode(pos).getData();
    }
    /**
     * Remove an element in the list based on position.
     * <br>pre: 0 <= pos < size()
     * <br>post: size() = old size() - 1, all elements of
     * list with a position > pos have a position = old position - 1
     * @param pos the position of the element to remove from the list
     * @return the data at position pos
     */
    @Override
    public E remove(int pos)
    {
        if(pos < 0 || pos >= size)
            throw new IllegalArgumentException("Cannot remove from index which is out of bounds, or list has no elements to remove");
        
        DoubleListNode<E> removeNode = getNode(pos);
        size--;
        
        return removeNode(removeNode);
    }
    /**
     * Remove the first occurrence of obj in this list.
     * Return <tt>true</tt> if this list changed as a result of this call, <tt>false</tt> otherwise.
     * <br>pre: obj != null
     * <br>post: if obj is in this list the first occurrence has been removed and size() = old size() - 1. 
     * If obj is not present the list is not altered in any way.
     * @param obj The item to remove from this list. obj != null
     * @return Return <tt>true</tt> if this list changed as a result of this call, <tt>false</tt> otherwise.
     */
    @Override
    public boolean remove(E obj)
    {
        if(obj == null)
            throw new IllegalArgumentException("in method remove(E), cannot remove object of value null");
        
        int pos = indexOf(obj);
        if(pos != -1)
        	remove(pos);
        return pos != -1;
    }
    /*
     * removes node, returns value of removed node
     * pre: nodeToRemove != null
     * post: return E, the value of the removed node
     */
    private E removeNode(DoubleListNode<E> nodeToRemove)
    {
        if(nodeToRemove == null)
            throw new IllegalArgumentException("how did this even happen");
        nodeToRemove.getPrev().setNext(nodeToRemove.getNext());
        nodeToRemove.getNext().setPrev(nodeToRemove.getPrev());
        
        return nodeToRemove.getData();
    }
    /**
     * Return a sublist of elements in this list from <tt>start</tt> inclusive to <tt>stop</tt> exclusive.
     * This list is not changed as a result of this call.
     * <br>pre: <tt>0 <= start <= size(), start <= stop <= size()</tt>
     * <br>post: return a list whose size is stop - start and contains the elements at positions start through stop - 1 in this list.
     * @param start index of the first element of the sublist.
     * @param stop stop - 1 is the index of the last element of the sublist.
     * @return a list with <tt>stop - start</tt> elements, The elements are from positions <tt>start</tt> inclusive to
     * <tt>stop</tt> exclusive in this list. If start == stop an empty list is returned.
     */
    @Override
    public IList<E> getSubList(int start, int stop)
    {
        if(start < 0 || start > size || stop < start || stop > size)
            throw new IllegalArgumentException("parameters are out of bounds for getSubList(start,stop)");
        LinkedList<E> subList = new LinkedList<E>();
        DoubleListNode<E> currentNode = getNode(start);
        for(int i = start; i < stop; i++)
        {
            subList.add(currentNode.getData());
            currentNode = currentNode.getNext();
            
        }
        subList.size = stop-start;
        return subList;
    }
    /**
     * Find the position of an element in the list.
     * <br>pre: item != null
     * <br>post: return the index of the first element equal to item
     * or -1 if item is not present
     * @param item the element to search for in the list. item != null
     * @return return the index of the first element equal to item or a -1 if item is not present
     */
    @Override
    public int indexOf(E item)
    {
        if(item == null)
            throw new IllegalArgumentException("in method indexOf(E), cannot find item of value null");
        
        DoubleListNode<E> current = HEAD.getNext();
        for(int i = 0; i < size; i++)
        {
            if(current.getData().equals(item))
                return i;
            current = current.getNext();
        }
        /* so why not just say "return indexOf(item,0)?
         * well, as you can see from preconditions, this doesnt work. indexOf(E) is not supposed to
         * throw an exception if size is 0. However, if size is 0, and i then call indexOf(item,0), 
         * this will throw an exception (as pos >= size). Therefore, since these methods have conflicting
         * (and imo confusing) guidelines for throwing an exception, they must be separated.
         */
        return -1;        
    }
    /**
     * find the position of an element in the list starting at a specified position.
     * <br>pre: 0 <= pos < size(), item != null
     * <br>post: return the index of the first element equal to item starting at pos
     * or -1 if item is not present from position pos onward
     * @param item the element to search for in the list. Item != null
     * @param pos the position in the list to start searching from
     * @return starting from the specified position return the index of the first element equal to item or a -1 if item is not present between pos and the end of the list
     */
    @Override
    public int indexOf(E item, int pos)
    {
        if(item == null || pos < 0 || pos >= size) 
            throw new IllegalArgumentException("in method indexOf(E,int), cannot find item of value null or int is OOB");
        DoubleListNode<E> current = getNode(pos);
        for(int i = pos; i < size; i++)
        {
            if(current.getData().equals(item))
                return i;
            current = current.getNext();
        }
        return -1;
    }
    /**
     * Remove all elements in this list from <tt>start</tt> inclusive to <tt>stop</tt> exclusive.
     * <br>pre: <tt>0 <= start < size(), start <= stop <= size()</tt>
     * <br>post: <tt>size() = old size() - (stop - start)</tt>
     * @param start position at beginning of range of elements to be removed
     * @param stop stop - 1 is the position at the end of the range of elements to be removed
     */
    @Override
    public void removeRange(int start, int stop)
    {
        if(start < 0 || start > size || stop < start || stop > size)
            throw new IllegalArgumentException("parameters are out of bounds for removeRange(start,stop)");
        
        DoubleListNode<E> startNode = getNode(start).getPrev();
        DoubleListNode<E> endNode = startNode.getNext();
        for(int i = start; i < stop; i++)
        {
            endNode = endNode.getNext();
        }
        startNode.setNext(endNode);
        endNode.setPrev(startNode);
        size-=(stop-start); 
    }
    /**
     * Determine if this IList is equal to other. Two
     * ILists are equal if they contain the same elements
     * in the same order.
     * @return true if this IList is equal to other, false otherwise
     */
    public boolean equals(Object other)
    {
        //check if size is equal
    	if(!(other instanceof IList))
    	{
    		return false;
    	}
        IList<?> otherIList = (IList<?>) other;      
        
        if(otherIList.size() != this.size)
        {
            //if size not equal, not same list
            return false;
        }
        //use iterator
        Iterator<?> iterator = otherIList.iterator();
        Iterator<E> iteratorThis = this.iterator();
       
        while(iterator.hasNext())
        {
            //whenever the elemtsn arent the same, they are not equal
        	if(!iterator.next().equals(iteratorThis.next()))
        		return false;
        }
        return true;
    }
    /**
     * return the list to an empty state.
     * <br>pre: none
     * <br>post: size() = 0
     */
    @Override
    public void makeEmpty()
    {
        HEAD.setNext(HEAD);
        HEAD.setPrev(HEAD);
        size = 0;
        
    }
    /**
    * return an Iterator for this list.
    * <br>pre: none
    * <br>post: return an Iterator object for this List
    */
    @Override
    public Iterator<E> iterator()
    {
        return new lliterator();
    }
    
    private class lliterator implements Iterator<E>{
        private DoubleListNode<E> currentNode;
        private DoubleListNode<E> trailer; //used for remove
        private boolean gotNext; //can't call remove if you haven't called next
        
        public lliterator()
        {
            currentNode = HEAD.getNext(); //avoid off by 1 logic error
            trailer = HEAD;
        }
        @Override
        public boolean hasNext()
        {
            return currentNode != HEAD; 
        }
        @Override
        public E next()
        {
            if(!hasNext())
                throw new NoSuchElementException("hasNext is false, don't call next please :)");
            
            E currentData = currentNode.getData();
            trailer = currentNode;
            currentNode = currentNode.getNext();
            gotNext = true;
            return currentData;
        }
        public void remove()
        {
            if(gotNext)
            {
                System.out.println("removed " + trailer.getData());
                removeNode(trailer);
                gotNext = false;
                size--;
            }                
            else
                throw new IllegalStateException("need to call next before remove");
        }
    }



}
