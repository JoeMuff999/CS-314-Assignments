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
import java.util.HashMap;
import java.util.Map;

/*
 * class that implements a relatively standard HuffmanTree 
 */
public class HuffmanTree
{
    private final int LEAF_NODE = 1;
    private final int INTERNAL_NODE = 0;
    private TreeNode root;
    private Map<String, Integer> nodeFreqMap;
    private NodePriorityQueue nPQ;
    private int bitSizeOfTree;

    /* Huffman constructor
     * takes in a frequency map of bytes and generates a huffman tree
     */
    public HuffmanTree(Map<String, Integer> nodeFreqMap)
    {
        this.nodeFreqMap = nodeFreqMap;
        nPQ = new NodePriorityQueue();
        createTree();
    }

    /*
     * if given a node, assume its the root and set internally. very useful for decompression of STF
     */
    public HuffmanTree(TreeNode root)
    {
        this.root = root;
    }

    /*
     * returns the encoded chunks in map form
     * uses a recursive helper to walk tree
     */
    public Map<Integer, String> getChunkMap()
    {
        //key = ascii value, val = huffman code
        Map<Integer, String> chunkMap = recursiveChunkMap(root, "");

        return chunkMap;
    }

    /*
     * recursively generates the chunks for each leaf
     * returns a map of these chunks where the key is the actual ASCII value and the value is encoded(?) chunk
     */
    private Map<Integer, String> recursiveChunkMap(TreeNode n, String codeSoFar)
    {
        //HM because don't need sorted tree
        Map<Integer, String> result = new HashMap<>();
        if (n.isLeaf())
        {
            //leaf? add to result
            result.put(n.getValue(), codeSoFar);
            return result;
        } else
        {
            //gets the map of the left side of tree
            Map<Integer, String> left = recursiveChunkMap(n.getLeft(), codeSoFar + "0");
            for (Integer key : left.keySet()) //adds left map to result
            {
                result.put(key, left.get(key));
            }
            //gets the map of the right side of tree
            Map<Integer, String> right = recursiveChunkMap(n.getRight(), codeSoFar + "1");
            for (Integer key : right.keySet()) //adds right map to result
            {
                result.put(key, right.get(key));
            }
        }
        return result;
    }

    /*
     * returns the root of the tree
     */
    public TreeNode getRoot()
    {
        return root;
    }

    /*
     * returns the bit size of the tree
     */
    public int getBitSize()
    {
        return bitSizeOfTree;
    }

    /*
     * returns a pre order traversal of the tree stored in an ArrayList of Integers
     * utilizes a recursive helper
     */
    public ArrayList<Integer> getPreorderTraversal()
    {
        ArrayList<Integer> result = new ArrayList<>();
        return recursivePT(root, result);
    }

    /*
     * recursive helper to generate a preorder traversal
     * returns an arraylist of the tree 
     */
    private ArrayList<Integer> recursivePT(TreeNode n, ArrayList<Integer> currList)
    {
        if (n != null)
        {
            if (n.isLeaf())
            {
                //leaf? add a leaf node flag and then 9 bits representing leaf value
                currList.add(LEAF_NODE); //LEAF_NODE = 1
                currList.add(n.getValue());
                bitSizeOfTree += IHuffConstants.BITS_PER_WORD + 1 + LEAF_NODE;
            } else
            {
                //pre order. add, go left, go right, believe
                currList.add(INTERNAL_NODE); //INTERNAL_NODE = 0
                bitSizeOfTree++;
                currList = recursivePT(n.getLeft(), currList);
                currList = recursivePT(n.getRight(), currList);
            }
        }

        return currList;
    }

    /*
     * creates a tree from the given byte frequency map
     * returns the root of the tree (TreeNode)
     */
    private TreeNode createTree()
    {
        if (nodeFreqMap == null)
            throw new IllegalStateException("nice going joey, HuffmanTree nodeFreqMap is null ");

        for (String key : nodeFreqMap.keySet())
        { //add all to prio queue
            nPQ.enqueue(new TreeNode(Integer.parseInt(key), nodeFreqMap.get(key)));
        }
        int maintainPriority = IHuffConstants.ALPH_SIZE; //used for maintaining priority when adding a combined node to prio queue. ALPH_SIZE > any val in tree, add 1 for each new combined node being added

        while (nPQ.size() > 1)
        {
            //follows the guidelines on the assignment webpage. 1st deque = left, 2nd = right. 
            TreeNode leftNode = nPQ.dequeue();
            TreeNode rightNode = nPQ.dequeue();
            //sum the two into one node and then add back to the priority queue
            TreeNode sumNode = new TreeNode(maintainPriority, leftNode.getFrequency() + rightNode.getFrequency());
            maintainPriority += 1;
            sumNode.setLeft(leftNode);
            sumNode.setRight(rightNode);
            nPQ.enqueue(sumNode);
        }
        root = nPQ.dequeue();
        return root;
    }

    /*
     * method stolen from previous assignment
     * All credits to Mike Scott :) (slight modifications to tree to cater it more towards this assignment)
     * uses a recursive method to print the current state of the huffman tree (incredibly helpful)
     */
    public void printTree()
    {
        printTree(root, "");
    }

    /*
     * recursively prints the tree. Made by Mike Scott
     */
    private void printTree(TreeNode n, String spaces)
    {
        if (n != null)
        {
            printTree(n.getRight(), spaces + "  ");
            System.out.println(spaces + (char) n.getValue() + " : " + n.getValue() + " freq = " + n.getFrequency());
            printTree(n.getLeft(), spaces + "  ");
        }
    }

}
