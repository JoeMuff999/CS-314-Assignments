
public class PQTester
{
    public static void main(String [] args)
    {
        NodePriorityQueue test = new NodePriorityQueue();
        TreeNode t1 = new TreeNode(1, 5);
        TreeNode t2 = new TreeNode(100, 5);
        TreeNode t3 = new TreeNode(10, 8);
        test.enqueue(t1);
        test.enqueue(t2);
        test.enqueue(t3);
        System.out.println(test.toString());
        System.out.println(test.dequeue());
        System.out.println(test.toString());
    }
}
