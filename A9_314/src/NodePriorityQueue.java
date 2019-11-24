/*  Student information for assignment:
 *
 *  On my honor, Joey Muffoletto, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used: 2 :(
 *  UTEID: jrm7925
 *  email address: jrmuff@utexas.edu    
 *  Grader name: Andrew
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * implementation of a priority queue that only takes in TreeNodes and uses an arraylist is the internal storage
 * priority explained by nested comparator (found below)
 */
public class NodePriorityQueue
{
    List<TreeNode> con;

    //front of list is the back. 
    public NodePriorityQueue()
    {
        //instantiate internal storage
        con = new ArrayList<>();
    }

    /*
     * enqueue = add TreeNode n to the list. doesn't matter where because it will be sorted
     * return nothing
     */
    public void enqueue(TreeNode n)
    {
        con.add(n);
        //is less efficient, but the max size of array we will be sorting is 2^9-1 (which is really, really small in the grand scheme of things).
        Collections.sort(con, new PrioritizerComparator());
    }

    /*
     * removes from the "front" of the queue (back of the array)
     * returns the removed TreeNode
     */
    public TreeNode dequeue()
    {
        return con.remove(con.size() - 1);
    }

    /*
     * returns the current size of the queue
     */
    public int size()
    {
        return con.size();
    }

    /*
     * custom toString method for debugging the priority queue
     * returns the nPQ in this form : { (TreeNode.val, TreeNode.freq),... }
     */
    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append("{ ");
        for (TreeNode n : con)
        {
            string.append(n.getValue() + "=" + n.getFrequency() + ", ");
        }
        string.delete(string.length() - 2, string.length());
        string.append(" }");
        return string.toString();
    }

    /*
     * custom comparator class
     * sorting method:
     *    -Priority first given to frequency. lowest frequency is at the front of the queue
     *    -If frequency is the same between two elements, the element with the lower ASCII value gets to be closer to the front of the queue
     */
    private class PrioritizerComparator implements Comparator<TreeNode>
    {

        /*
         * sorts in descending order by frequency. 
         * if freq same, resort to comparing the ASCII values
         * return is a ternary operation ( < 0 , > 0 , 0)
         */
        public int compare(TreeNode t1, TreeNode t2)
        {
            int compareFreq = t2.compareTo(t1);
            //same frequency? compare ASCII
            return compareFreq != 0 ? compareFreq : compareASCII(t2, t1);
        }

        /*
         * method to compare ASCII values.
         * descending order
         * returns a ternary int
         */
        private int compareASCII(TreeNode t1, TreeNode t2)
        {
            Integer t1Val = t1.getValue();
            Integer t2Val = t2.getValue();
            return t1Val.compareTo(t2Val);
        }
    }
}
