import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;



public class HuffmanTree
{
    private TreeNode root;
    private Map<String, Integer> nodeFreqMap;
    private NodePriorityQueue nPQ;
    
    public HuffmanTree(Map<String, Integer> nodeFreqMap)
    {
        this.nodeFreqMap = nodeFreqMap;
        nPQ = new NodePriorityQueue();        
    }
    
//    public TreeNode getTreeRoot()
//    {        
//        return createTree();
//    }
    
    public Map<Integer, String> getChunkMap()
    {
        createTree();       
        //key = ascii value, val = huffman code
        //printTree();
        Map<Integer, String> chunkMap = recursiveChunkMap(root, "");
        
        return chunkMap;
    }
    
    private Map<Integer, String> recursiveChunkMap(TreeNode n, String codeSoFar )
    {
        Map<Integer, String> result = new HashMap<>();
        if(n.isLeaf())
        {
            result.put(n.getValue(), codeSoFar);
            return result;
        }
        else
        {
            Map<Integer, String> left = recursiveChunkMap(n.getLeft(), codeSoFar + "0");
            for(Integer key : left.keySet())
            {
                result.put(key, left.get(key));
            }
            Map<Integer, String> right = recursiveChunkMap(n.getRight(), codeSoFar + "1");
            for(Integer key : right.keySet())
            {
                result.put(key, right.get(key));
            }
        }
        return result;
    }
    
    public void printTree()
    {
        printTree(root, "");
    }
    
    private TreeNode createTree()
    {
       if(nodeFreqMap == null)
           throw new IllegalStateException("nice going joey, HuffmanTree nodeFreqMap is null ");
       
       for(String key : nodeFreqMap.keySet())
       {    //add all to prio queue
           nPQ.enqueue(new TreeNode(Integer.parseInt(key), nodeFreqMap.get(key)));
       }
       int maintainPriority = IHuffConstants.ALPH_SIZE; //used for maintaining priority when adding a combined node to prio queue. ALPH_SIZE > any val in tree, add 1 for each new combined node being added
       while(nPQ.size() > 1)
       {
           TreeNode leftNode = nPQ.dequeue();
           TreeNode rightNode = nPQ.dequeue();
           //give them -1 as value because it might be useful later on
           
           TreeNode sumNode = new TreeNode(maintainPriority, leftNode.getFrequency() + rightNode.getFrequency());
           maintainPriority+=1;
           sumNode.setLeft(leftNode);
           sumNode.setRight(rightNode);
           nPQ.enqueue(sumNode);
       }
       root = nPQ.dequeue();
       return root;
    }

    private void printTree(TreeNode n, String spaces)
    {
        if (n != null)
        {
            printTree(n.getRight(), spaces + "  ");
            System.out.println(spaces + (char)n.getValue() + " freq = " + n.getFrequency());
            printTree(n.getLeft(), spaces + "  ");
        }
    }

}
