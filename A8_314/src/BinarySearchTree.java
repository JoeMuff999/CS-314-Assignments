/*  Student information for assignment:
 *
 *  On my honor, <NAME>, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID:
 *  email address:
 *  Grader name:
 *  Number of slip days I am using:
 *  
 *  ask about variables names (ie: node "n")
 *  ask about flourless chocolate cake?
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Shell for a binary search tree class.
 * @author scottm
 * @param <E> The data type of the elements of this BinartSearchTree.
 * Must implement Comparable or inherit from a class that implements
 * Comparable.
 *
 */
public class BinarySearchTree<E extends Comparable<? super E>> {

    private BSTNode<E> root;
    // CS314 students. Add any other instance variables you want here
    private int size;
    // CS314 students. Add a default constructor here.
    
    public BinarySearchTree()
    {
        //root = new BSTNode<E>(null, null, null);
    }
    
    /**
     *  Add the specified item to this Binary Search Tree if it is not already present.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: Add value to this tree if not already present. Return true if this tree
     *  changed as a result of this method call, false otherwise.
     *  @param value the value to add to the tree
     *  @return false if an item equivalent to value is already present
     *  in the tree, return true if value is added to the tree and size() = old size() + 1
     */
    public boolean add(E value) {
        if(value == null)
            throw new IllegalArgumentException("Can't add null");
        //from class
        int prevSize = size;
        root = recursiveAdd(root, value);
        return prevSize != size;
    }
    
    private BSTNode<E> recursiveAdd(BSTNode<E> n, E valToAdd)
    {
        if(n == null)
        {
            size++;
            n = new BSTNode<E>(null, valToAdd, null);
            return n;
        }
        else if(valToAdd.compareTo(n.data) < 0)
        {
            n.left = recursiveAdd(n.left, valToAdd);
        }
        else if(valToAdd.compareTo(n.data) > 0)
        {
            n.right = recursiveAdd(n.right, valToAdd);
        }
        
        return n;
    }

    /**
     *  Remove a specified item from this Binary Search Tree if it is present.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: Remove value from the tree if present, return true if this tree
     *  changed as a result of this method call, false otherwise.
     *  @param value the value to remove from the tree if present
     *  @return false if value was not present
     *  returns true if value was present and size() = old size() - 1
     */
    public boolean remove(E value) {
        if(value == null)
            throw new IllegalArgumentException("Cannot remove null");
        
        //printTree();
        int origSize = size;
        root = recursiveRemove(root, value);
        //printTree();
        return size != origSize;
    }
   
    private BSTNode<E> recursiveRemove(BSTNode<E> n, E valToRemove)
    {
        if(n == null)
        {
            
        }
        else if(n.data.equals(valToRemove))
        {

            size--;
            //is it a leaf ? ez pz
            if(n.left == null && n.right == null)
            {
                return null;
            }
            else if(n.left == null && n.right != null)
            {
                return n.right;
            }
            else if(n.right == null && n.left != null)
            {
                return n.left;
            }
            else //actually hard...make max of left side the new parent and then delete that max node from its original spot.
            {
                n.data = getMaxOfLeftTree(n.left);
                n.left = recursiveRemove(n.left, n.data);
                size++;
            }
        }
        else if(valToRemove.compareTo(n.data) < 0)
        {
            n.left = recursiveRemove(n.left, valToRemove);
        }
        else if(valToRemove.compareTo(n.data) > 0)
        {
            n.right = recursiveRemove(n.right, valToRemove);
        }
        
        return n;
    }
    
    private E getMaxOfLeftTree(BSTNode<E> max)
    {
        while(max.right != null)
        {
            max = max.right;
        }
        return max.data;
    }


    /**
     *  Check to see if the specified element is in this Binary Search Tree.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: return true if value is present in tree, false otherwise
     *  @param value the value to look for in the tree
     *  @return true if value is present in this tree, false otherwise
     */
    public boolean isPresent(E value) {
        if(value == null)
            throw new IllegalArgumentException("value cannot be null");
        
        return recursiveIsPresent(root, value);
    }
    
    private boolean recursiveIsPresent(BSTNode<E> n, E val)
    {
        if(n == null)
        {
            return false;
        }
        else if(n.data.equals(val))
        {
            return true;
        }
        else if(val.compareTo(n.data) < 0 )
        {
            return recursiveIsPresent(n.left, val);
        }
        else if(val.compareTo(n.data) > 0)
        {
            return recursiveIsPresent(n.right, val);
        }
        return false;
    }


    /**
     *  Return how many elements are in this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return the number of items in this tree
     *  @return the number of items in this Binary Search Tree
     */
    public int size() {
        return size;
    }

    /**
     *  return the height of this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return the height of this tree.
     *  If the tree is empty return -1, otherwise return the
     *  height of the tree
     *  @return the height of this tree or -1 if the tree is empty
     */
    public int height() {
       // System.out.println(recursiveHeight(root));
        return recursiveHeight(root);
    }
    
    private int recursiveHeight(BSTNode<E> n)
    {        
        if(n == null)
        {
            return -1;
        }
        else if(n.left == null && n.right != null)
        {
            return 1 + recursiveHeight(n.right);
        }
        else if(n.right == null && n.left != null)
        {
            return 1 + recursiveHeight(n.left);
        }
        else
        {
            return 1 + Math.max(recursiveHeight(n.left),recursiveHeight(n.right));
        }
    }

    /**
     *  Return a list of all the elements in this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return a List object with all data from the tree in ascending order. 
     *  If the tree is empty return an empty List
     *  @return a List object with all data from the tree in sorted order
     *  if the tree is empty return an empty List
     */
    public List<E> getAll() {     
        List<E> result = new ArrayList<>();
        //printTree();
        //System.out.println(recursiveGetAll(result, root));
        return recursiveGetAll(result, root);
    }
    
    private List<E> recursiveGetAll(List<E> result, BSTNode<E> n)
    {
        //in order traversal, really short and simple
        if(n != null)
        {
          //add between the recursive calls so that the parent node gets added in between its left child and right child
            recursiveGetAll(result, n.left);
            result.add(n.data);
            recursiveGetAll(result, n.right); 
        }
        
        return result;

    }



    /**
     * return the maximum value in this binary search tree.
     * <br>
     * pre: <tt>size()</tt> > 0<br>
     * post: return the largest value in this Binary Search Tree
     * @return the maximum value in this tree
     */
    public E max() {
        if(size <= 0)
            throw new IllegalStateException("size is 0 or less, which means there is no maximum :(");
        
        BSTNode<E> max = root;
        while(max.right != null)
        {
            max = max.right;
        }
        return max.data;
    }

    /**
     * return the minimum value in this binary search tree.
     * <br>
     * pre: <tt>size()</tt> > 0<br>
     * post: return the smallest value in this Binary Search Tree
     * @return the minimum value in this tree
     */
    public E min() {
        if(size <= 0)
            throw new IllegalStateException("size is 0 or less, which means there is no minimum :(");
        
        BSTNode<E> min = root;
        while(min.left != null)
        {
            min = min.left;
        }
        return min.data;
    }

    /**
     * An add method that implements the add algorithm iteratively instead of recursively.
     * <br>pre: data != null
     * <br>post: if data is not present add it to the tree, otherwise do nothing.
     * @param data the item to be added to this tree
     * @return true if data was not present before this call to add, false otherwise.
     */
    public boolean iterativeAdd(E data) {
        if(data == null)
            throw new IllegalArgumentException("data cannot be null");
        
        BSTNode<E> currentNode = root;
        BSTNode<E> parentNode = root;
        while(currentNode != null)
        {
            parentNode = currentNode; 
            
            if(data.compareTo(currentNode.data) < 0 )
            {
                currentNode = currentNode.left;
            }
            else if(data.compareTo(currentNode.data) > 0)
            {
                currentNode = currentNode.right;
            }
            else //found the val? don't add and return false;
            {
                return false;
            }
        }
        if(data.compareTo(parentNode.data) < 0 )
        {
            parentNode.left = new BSTNode<E>(null, data, null);    
        }
        else
        {
            parentNode.right = new BSTNode<E>(null, data, null);    
        }    
        size++;
        return true;
    }


    /**
     * Return the "kth" element in this Binary Search Tree. If kth = 0 the 
     * smallest value (minimum) is returned. If kth = 1 the second smallest value is
     * returned, and so forth.
     * <br>pre: 0 <= kth < size()
     * @param kth indicates the rank of the element to get
     * @return the kth value in this Binary Search Tree
     */
    public E get(int kth) {
        if(0 > kth || kth >= size)
            throw new IllegalArgumentException("kth cannot be greater than or equal to size or less than 0");
        printTree();
        System.out.println(recursiveGet(kth, -1, root));
        return recursiveGet(kth, -1, root);
    }

    private E recursiveGet(int tgtIndex, int index, BSTNode<E> n)
    {
        E result = null;
        if(n == null)
        {
            
        }
        else if(index == tgtIndex)
        {
            return n.data;
        }
        else
        {
            //pass index as -1 so in case the tgtindex is 0, the root element wont be counted as the first in order element. 
            //(need to wait until i go all the way left until I start adding to index
            recursiveGet(tgtIndex, -1, n.left);
            System.out.println(n.data);
            result = recursiveGet(tgtIndex, index + 1 , n.right);
        }           
        return result;
        
    }

    /**
     * Return a List with all values in this Binary Search Tree that are less than
     * the parameter <tt>value</tt>.
     * <tt>value</tt> != null<br>
     * @param value the cutoff value
     * @return a List with all values in this tree that are less than the parameter value. If there are
     * no values in this tree less than value return an empty list. The elements of the list are in ascending order.
     */
    public List<E> getAllLessThan(E value) {
        return null;
    }


    /**
     * Return a List with all values in this Binary Search Tree that are greater than
     * the parameter <tt>value</tt>.
     * <tt>value</tt> != null<br>
     * @param value the cutoff value
     * @return a List with all values in this tree that are greater than the parameter value. If there are
     * no values in this tree greater than value return an empty list. The elements of the list are in ascending order.
     */
    public List<E> getAllGreaterThan(E value) {
        return null;
    }



    /**
     * Find the number of nodes in this tree at the specified depth.
     * <br>pre: none
     * @param d The target depth.
     * @return The number of nodes in this tree at a depth equal to
     * the parameter d.
     */
    public int numNodesAtDepth(int d) {
        return recursiveDepth(d, 0, root);
    }
    
    private int recursiveDepth(int tgt, int currDepth, BSTNode<E> n)
    {
        int result = 0;
        if(n == null)
        {
            
        }
        else if(n != null && currDepth == tgt)
        {
            return 1;
        }
        else if(currDepth < tgt)
        {
            result += recursiveDepth(tgt, currDepth + 1, n.left) + recursiveDepth(tgt, currDepth + 1, n.right);
        }
        return result;
    }

    /**
     * Prints a vertical representation of this tree.
     * The tree has been rotated counter clockwise 90
     * degrees. The root is on the left. Each node is printed
     * out on its own row. A node's children will not necessarily
     * be at the rows directly above and below a row. They will
     * be indented three spaces from the parent. Nodes indented the
     * same amount are at the same depth.
     * <br>pre: none
     */
    public void printTree() {
        printTree(root, "");
    }

    private void printTree(BSTNode<E> n, String spaces) {
        if(n != null){
            printTree(n.getRight(), spaces + "  ");
            System.out.println(spaces + n.getData());
            printTree(n.getLeft(), spaces + "  ");
        }
    }

    private static class BSTNode<E extends Comparable<? super E>> {
        private E data;
        private BSTNode<E> left;
        private BSTNode<E> right;

        public BSTNode() {
            this(null);
        }

        public BSTNode(E initValue) {
            this(null, initValue, null); 
        }

        public BSTNode(BSTNode<E> initLeft,
                E initValue,        
                BSTNode<E> initRight) {
            data = initValue; 
            left = initLeft; 
            right = initRight; 
        }

        public E getData() { 
            return data; 
        }

        public BSTNode<E> getLeft() { 
            return left;
        }

        public BSTNode<E> getRight() { 
            return right; 
        }

        public void setData(E theNewValue) { 
            data = theNewValue; 
        }

        public void setLeft(BSTNode<E> theNewLeft) { 
            left = theNewLeft; 
        }

        public void setRight(BSTNode<E> theNewRight) { 
            right = theNewRight; 
        }    
    }
}
