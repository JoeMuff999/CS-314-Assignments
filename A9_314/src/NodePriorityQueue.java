import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NodePriorityQueue
{   
    List<TreeNode> con;
    
    //front of list is the back. 
    public NodePriorityQueue()
    {
        con = new ArrayList<>();        
    }
    
    public void enqueue(TreeNode n)
    {
        con.add(n);
        //is less efficient, but the max size of array we will be sorting is 2^9-1 (which is really, really small in the grand scheme of things).
        Collections.sort(con, new PrioritizerComparator());
    }
    
    public TreeNode dequeue()
    {        
        return con.remove(con.size()-1);
    }
    
    public int size()
    {
        return con.size();
    }
    
    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append("{ ");
        for(TreeNode n : con)
        {
            string.append(n.getValue() + "=" + n.getFrequency() + ", ");
        }
        string.delete(string.length()-2, string.length());
        string.append(" }");
        return string.toString();
    }
    private class PrioritizerComparator implements Comparator<TreeNode>{
        //sorting in descending order so dequeue removes from back 
        public int compare(TreeNode t1, TreeNode t2)
        {
            int compareFreq = t2.compareTo(t1);           
            return compareFreq != 0 ? compareFreq : compareASCII(t2,t1);
        }
        
        private int compareASCII(TreeNode t1, TreeNode t2)
        {
            Integer t1Val = t1.getValue();
            Integer t2Val = t2.getValue();
            return t1Val.compareTo(t2Val);
        }
    }
}
