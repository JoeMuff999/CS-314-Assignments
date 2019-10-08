import java.util.Arrays;

public class tester
{
    private static String[] con;
    private static int size;
    public static void main(String[] args)
    {
        size = 7;
        con = new String[] {"AA","A","B","AA","AA","B","CC"};
        removeFirstN(3);
        System.out.println(Arrays.toString(con));
        
        
    }
    public static void removeFirstN(int n)
    {
        for(int i = 0; i < (size-n); i++)
        {
            con[i] = con[i+n];
            con[i+n] = null;
        }
    }
    public static int removeAll(String tgt)
    {
        int removed = 0;
        int i = 0;
        while(i+removed < size)
        {
            if(con[i].equals(tgt))
            {
                removed++;
            }
            con[i] = con[i+removed];
            i++;
        }
        for(int j = size-removed; j < size; j++)
        {
            con[j]= null;
        }
        size = size-removed;
        
        return removed;
        
    }
}
