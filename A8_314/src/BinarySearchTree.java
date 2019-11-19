/*  Student information for assignment:
 *
 *  On my honor, Joey Muffoletto, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: jrm7925
 *  email address: jrmuff@utexas.edu
 *  Grader name: Andrew
 *  Number of slip days I am using: 0
 *  

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
public class BinarySearchTree<E extends Comparable<? super E>>
{

    private BSTNode<E> root;
    // CS314 students. Add any other instance variables you want here
    private int size;

    private final boolean RIGHT_TREE = false;
    private final boolean LEFT_TREE = true;
    // CS314 students. Add a default constructor here.

    public BinarySearchTree()
    {
        //don't need anything. leaving root as null for my own purposes
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
    public boolean add(E value)
    {
        if (value == null)
            throw new IllegalArgumentException("Can't add null");
        //size changed? from class
        int prevSize = size;
        root = recursiveAdd(root, value);
        return prevSize != size;
    }

    /* recursively adds valToAdd to the tree. uses the method that Mike showed in class */
    private BSTNode<E> recursiveAdd(BSTNode<E> n, E valToAdd)
    {
        if (n == null)
        {
            size++;
            n = new BSTNode<E>(null, valToAdd, null);
            return n;
        } else if (valToAdd.compareTo(n.data) < 0)
        {
            n.left = recursiveAdd(n.left, valToAdd);
        } else if (valToAdd.compareTo(n.data) > 0)
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
    public boolean remove(E value)
    {
        if (value == null)
            throw new IllegalArgumentException("Cannot remove null");
        //size changed?
        int origSize = size;
        root = recursiveRemove(root, value);
        return size != origSize;
    }

    /*recursively removes a val from the tree. Uses the method Mike showed in class */
    private BSTNode<E> recursiveRemove(BSTNode<E> n, E valToRemove)
    {
        if (n != null)
        {
            if (n.data.equals(valToRemove))
            {

                size--;
                //is it a leaf ? ez pz
                if (n.left == null && n.right == null)
                {
                    return null;
                } //leaf but one child? return the non null child
                else if (n.left == null && n.right != null)
                {
                    return n.right;
                } else if (n.right == null && n.left != null)
                {
                    return n.left;
                } else //actually hard...make max of left side the new parent and then delete that max node from its original spot.
                {
                    n.data = getMaxOfLeftTree(n.left);
                    //removes the node that is replacing the removed node
                    n.left = recursiveRemove(n.left, n.data);
                    //have to add to size because it will end up being two calls to recursiveRemove which means size-=2
                    size++;
                }
            } //continue traversing tree if not equal
            else if (valToRemove.compareTo(n.data) < 0)
            {
                n.left = recursiveRemove(n.left, valToRemove);
            } else if (valToRemove.compareTo(n.data) > 0)
            {
                n.right = recursiveRemove(n.right, valToRemove);
            }
        }

        return n;
    }

    /* returns the value of the max of the left side of the tree */
    private E getMaxOfLeftTree(BSTNode<E> max)
    {
        while (max.right != null)
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
    public boolean isPresent(E value)
    {
        if (value == null)
            throw new IllegalArgumentException("value cannot be null");

        return recursiveIsPresent(root, value);
    }

    /* simple traversal of the tree. returns if node was found */
    private boolean recursiveIsPresent(BSTNode<E> n, E val)
    {
        if (n == null)
        {
            return false;
        } else if (n.data.equals(val))
        {
            return true;
        } else if (val.compareTo(n.data) < 0)
        {
            return recursiveIsPresent(n.left, val);
        } else if (val.compareTo(n.data) > 0)
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
    public int size()
    {
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
    public int height()
    {
        return recursiveHeight(root);
    }

    /* simple traversal of all branches. takes the max of left or right side of tree depending on who has a bigger height */
    private int recursiveHeight(BSTNode<E> n)
    {
        if (n == null)
        {
            return -1;
        } else if (n.left == null && n.right != null)
        {
            return 1 + recursiveHeight(n.right);
        } else if (n.right == null && n.left != null)
        {
            return 1 + recursiveHeight(n.left);
        } else
        {
            return 1 + Math.max(recursiveHeight(n.left), recursiveHeight(n.right));
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
    public List<E> getAll()
    {
        List<E> result = new ArrayList<>();
        return recursiveGetAll(result, root);
    }

    /* simple in order traversal. go alll the way left, then as the stack pops add the middle and right node*/
    private List<E> recursiveGetAll(List<E> result, BSTNode<E> n)
    {
        //in order traversal, really short and simple
        if (n != null)
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
    public E max()
    {
        if (size <= 0)
            throw new IllegalStateException("size is 0 or less, which means there is no maximum :(");

        BSTNode<E> max = root;
        while (max.right != null)
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
    public E min()
    {
        if (size <= 0)
            throw new IllegalStateException("size is 0 or less, which means there is no minimum :(");

        BSTNode<E> min = root;
        while (min.left != null)
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
    public boolean iterativeAdd(E data)
    {
        if (data == null)
            throw new IllegalArgumentException("data cannot be null");
        //base node = new
        if (root == null)
        {
            size++;
            root = new BSTNode<E>(null, data, null);
            return true;
        }
        BSTNode<E> currentNode = root;
        BSTNode<E> parentNode = root;
        //simple traversal of the tree until we either find val or find a place for it. use parent to track the node above curr
        while (currentNode != null)
        {
            parentNode = currentNode;

            if (data.compareTo(currentNode.data) < 0)
            {
                currentNode = currentNode.left;
            } else if (data.compareTo(currentNode.data) > 0)
            {
                currentNode = currentNode.right;
            } else //found the val? don't add and return false;
            {
                return false;
            }
        }
        //make a child of parentNode contain data based on data compare to parent.data
        if (data.compareTo(parentNode.data) < 0)
        {
            parentNode.left = new BSTNode<E>(null, data, null);
        } else
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
    public E get(int kth)
    {
        if (0 > kth || kth >= size)
            throw new IllegalArgumentException("kth cannot be greater than or equal to size or less than 0");
        List<E> result = new ArrayList<>();
        //get the kth node (should be the last node of the list) from result.
        return recursiveGet(result, root, kth).get(kth);
    }

    private List<E> recursiveGet(List<E> result, BSTNode<E> n, int tgtIndex)
    {
        //in order traversal, really short and simple
        if (result.size() > tgtIndex)
        {
            //use this to terminate 
            return result;
        } else if (n != null)
        {
            //add between the recursive calls so that the parent node gets added in between its left child and right child
            recursiveGet(result, n.left, tgtIndex);
            result.add(n.data);
            recursiveGet(result, n.right, tgtIndex);
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
    public List<E> getAllLessThan(E value)
    {
        if (value == null)
            throw new IllegalArgumentException("yea you can't find a null value");
        //make sure root is not null before getting the list
        if (root == null)
            return new ArrayList<E>(); //return empty
        return iterativeLessThan(value);
    }

    /* hybrid way of getting less than values. Finds the first node thats greater than val. While finding this node, creates a list of all parent nodes
     * (we know that all parent nodes must be less than val). The next step, once we found the first node greater than val, is to look at the left tree of this node
     * to see if any children happen to be less than val. if so, add the left tree of these nodes to the above parent node list. at the end, use a recursiveGetAll method (in order traversal)
     * on each node we added to the list. Ensures that recursion is limited to nodes that are 100% going to be less than value. 
     * returns the list of all values less than.
     */
    private List<E> iterativeLessThan(E val)
    {
        // an iterative way of getting greater than nodes. Should be faster than recursion because no flourless calls.
        //i also understand that I could've just done an in order traversal and then terminated once i reached a node greater than val, but thats boring :)
        BSTNode<E> firstNodeGreaterThanVal = root;
        List<BSTNode<E>> listOfNodesToAddLeftSubtree = new ArrayList<>();
        //find first node greater than val
        while (firstNodeGreaterThanVal.data.compareTo(val) <= 0)
        {
            listOfNodesToAddLeftSubtree.add(firstNodeGreaterThanVal);
            if (firstNodeGreaterThanVal.data.compareTo(val) == 0)
            {
                return handleEquals(listOfNodesToAddLeftSubtree, LEFT_TREE);
            }

            firstNodeGreaterThanVal = firstNodeGreaterThanVal.right;
            //check for null
            if (firstNodeGreaterThanVal == null)
                return getLeftSubtree(listOfNodesToAddLeftSubtree);
        }

        //now account for the possibility that the left subtree of this node (which is greater than val) could be less than val.
        firstNodeGreaterThanVal = firstNodeGreaterThanVal.left;
        while (firstNodeGreaterThanVal != null)
        {
            //less than val? add entire left subtree then go right
            if (firstNodeGreaterThanVal.data.compareTo(val) < 0)
            {
                listOfNodesToAddLeftSubtree.add(firstNodeGreaterThanVal);
                firstNodeGreaterThanVal = firstNodeGreaterThanVal.right;
            } else if (firstNodeGreaterThanVal.data.compareTo(val) > 0)//greater than? go left
            {
                firstNodeGreaterThanVal = firstNodeGreaterThanVal.left;
            } else //equal to (do this for earliest return possible in the event you find a node equal to the cutoff
            {
                listOfNodesToAddLeftSubtree.add(firstNodeGreaterThanVal);
                return handleEquals(listOfNodesToAddLeftSubtree, LEFT_TREE);
            }
        }
        return getLeftSubtree(listOfNodesToAddLeftSubtree);
    }

    /* method for handling the case when we reach a node == val. condenses code. 
     * returns the list of all nodes less than or greater than depending on the boolean flag "isLeft" (isLeft == true indicates less than).
     */
    private List<E> handleEquals(List<BSTNode<E>> listOfNodes, boolean isLeft)
    {

        //the removals are necessary because the way my get___Subtree method works is that each node will be added to the list along with its left or right tree children.
        //the node that equals val will also be added (as a result of the logic). the most efficient way to remedy this is to remove the node. It will be the largest node
        //for the lessThan method, and the smallest node for the greaterThan method.
        List<E> result = new ArrayList<>();
        if (isLeft)
        {
            result = getLeftSubtree(listOfNodes);
            result.remove(result.size() - 1);
        } else
        {
            result = getRightSubtree(listOfNodes);
            result.remove(0);
        }
        return result;
    }

    /*returns an inorder list of the leftSubtree for a list of nodes. */
    private List<E> getLeftSubtree(List<BSTNode<E>> listOfNodes)
    {
        List<E> result = new ArrayList<>();
        for (int i = 0; i < listOfNodes.size(); i++)
        {
            result = recursiveGetAll(result, listOfNodes.get(i).left);
            //add parent (will be greater than all of left sub tree thats being added)
            result.add(listOfNodes.get(i).data);
        }
        return result;
    }

    /**
     * Return a List with all values in this Binary Search Tree that are greater than
     * the parameter <tt>value</tt>.
     * <tt>value</tt> != null<br>
     * @param value the cutoff value
     * @return a List with all values in this tree that are greater than the parameter value. If there are
     * no values in this tree greater than value return an empty list. The elements of the list are in ascending order.
     */
    public List<E> getAllGreaterThan(E value)
    {
        if (value == null)
            throw new IllegalArgumentException("yea you can't find a null value");
        if (root == null)
            return new ArrayList<E>();//return empty
        return iterativeGreaterThan(value);
    }

    /*once again, a hybrid way of getting greater than values. First finds the first node that is less than val. All parent nodes can be assumed to be greater than val, so add these
     * to our list of BSTNodes. Now, find out if any children if this "first node" in the right subtree happen to be greater than val. Iteratively find them and add them to BSTNode list.
     * returns the list of values greater than val.
     */
    private List<E> iterativeGreaterThan(E val)
    {
        // an iterative way of getting greater than nodes. Should be faster than recursion because no flourless calls.
        BSTNode<E> firstNodeLessThanVal = root;
        List<BSTNode<E>> listOfNodesToAddRightSubtree = new ArrayList<>();
        //find first node less than val
        while (firstNodeLessThanVal.data.compareTo(val) >= 0)
        {
            listOfNodesToAddRightSubtree.add(firstNodeLessThanVal);
            if (firstNodeLessThanVal.data.compareTo(val) == 0)
            {
                //must be done this way to remove first node
                return handleEquals(listOfNodesToAddRightSubtree, RIGHT_TREE);
            }

            firstNodeLessThanVal = firstNodeLessThanVal.left;
            //check for null
            if (firstNodeLessThanVal == null)
                return getRightSubtree(listOfNodesToAddRightSubtree);
        }

        //now account for the possibility that a the right subtree of this node (which is smaller than val) could be greater than val.
        firstNodeLessThanVal = firstNodeLessThanVal.right;
        while (firstNodeLessThanVal != null)
        {
            //less than or equal to val? go right
            if (firstNodeLessThanVal.data.compareTo(val) < 0)
            {
                firstNodeLessThanVal = firstNodeLessThanVal.right;
            } else if (firstNodeLessThanVal.data.compareTo(val) > 0) //greater than? add entire right subtree and go left
            {
                listOfNodesToAddRightSubtree.add(firstNodeLessThanVal);
                firstNodeLessThanVal = firstNodeLessThanVal.left;
            } else //equals
            {
                listOfNodesToAddRightSubtree.add(firstNodeLessThanVal);
                return handleEquals(listOfNodesToAddRightSubtree, RIGHT_TREE);
            }
        }
        return getRightSubtree(listOfNodesToAddRightSubtree);
    }

    /* returns an in order list of the rightSubtree for a given list of nodes. */
    private List<E> getRightSubtree(List<BSTNode<E>> listOfNodes)
    {
        List<E> result = new ArrayList<>();
        //go backwards in list because we added them in descending order.
        for (int i = listOfNodes.size() - 1; i >= 0; i--)
        {
            //add parent (will be smaller than all of right sub tree thats being added)
            result.add(listOfNodes.get(i).data);
            result = recursiveGetAll(result, listOfNodes.get(i).right);
        }
        return result;
    }

    /**
     * Find the number of nodes in this tree at the specified depth.
     * <br>pre: none
     * @param d The target depth.
     * @return The number of nodes in this tree at a depth equal to
     * the parameter d.
     */
    public int numNodesAtDepth(int d)
    {
        return recursiveDepth(d, 0, root);
    }

    /*returns the depth for a given level through recursion */
    private int recursiveDepth(int tgt, int currDepth, BSTNode<E> n)
    {
        int result = 0;

        if (n != null)
        {
            if (n != null && currDepth == tgt)
            {
                return 1;
            } else if (currDepth < tgt)
            {
                //top level result will add up all calls that return 1 (any node at the tgt level)
                result += recursiveDepth(tgt, currDepth + 1, n.left) + recursiveDepth(tgt, currDepth + 1, n.right);
            }
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
    public void printTree()
    {
        printTree(root, "");
    }

    private void printTree(BSTNode<E> n, String spaces)
    {
        if (n != null)
        {
            printTree(n.getRight(), spaces + "  ");
            System.out.println(spaces + n.getData());
            printTree(n.getLeft(), spaces + "  ");
        }
    }

    private static class BSTNode<E extends Comparable<? super E>>
    {
        private E data;
        private BSTNode<E> left;
        private BSTNode<E> right;

        public BSTNode()
        {
            this(null);
        }

        public BSTNode(E initValue)
        {
            this(null, initValue, null);
        }

        public BSTNode(BSTNode<E> initLeft, E initValue, BSTNode<E> initRight)
        {
            data = initValue;
            left = initLeft;
            right = initRight;
        }

        public E getData()
        {
            return data;
        }

        public BSTNode<E> getLeft()
        {
            return left;
        }

        public BSTNode<E> getRight()
        {
            return right;
        }

        public void setData(E theNewValue)
        {
            data = theNewValue;
        }

        public void setLeft(BSTNode<E> theNewLeft)
        {
            left = theNewLeft;
        }

        public void setRight(BSTNode<E> theNewRight)
        {
            right = theNewRight;
        }
    }
}
