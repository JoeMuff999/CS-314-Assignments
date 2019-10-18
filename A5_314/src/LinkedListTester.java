/*  Student information for assignment:
*
*  On my honor, <Joey Muffoleto>, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  Name: Joey Muffoletto
*  email address: jrmuff@utexas.edu
*  UTEID: jrm7925
*  Grader name: Andrew
*  Number of slip days used on this assignment: 0
*/

//Experiment results. CS314 students, place your experiment
//results here:
/*
 * MethodL
 * Adding at End: roughly the same speed
 * Num Repeats: 100
    Adding at end: ArrayList
    N = 30000, total time:  0.2152
    N = 60000, total time:  0.3349
    N = 120000, total time:  0.4241
    N = 240000, total time:  0.5388
    N = 480000, total time:  1.3359

    ArrayList Big O: This method is O(1). Timing data is O(N), divide this by O(N) = O(1)
    Why O(N) (for the initial): As N doubles, the time it takes kind of doubles, signaling 
    linear time.

   Num Repeats: 100
    Adding at end: LinkedList
    N = 30000, total time:  0.0383
    N = 60000, total time:  0.0751  
    N = 120000, total time:  0.1606
    N = 240000, total time:  0.4533
    N = 480000, total time:  0.6411

 *  LinkedList Big O: This method is O(1). Timing data is O(N), divide this by O(N) = O(1)
 *  Why O(N) (for the initial): As N doubles, the time it takes kind of doubles, signaling 
    linear time.
 * 
 *  
 * Adding at front: LinkedList much faster
 * Num Repeats: 100
    Adding at front: ArrayList
    N = 2000, total time:  0.0215
    N = 4000, total time:  0.0654
    N = 8000, total time:  0.2350
    N = 16000, total time:  1.0181
    N = 32000, total time:  4.1470

    ArrayList Big O: based on timing data, it is O(N^2)/O(N) = O(N)
    Why O(N^2) (for the initial): As N doubles, the time it takes kind of quadruples, signaling 
    quadratic time.
    
    Num Repeats: 100
    Adding at front: LinkedList
    N = 10000, total time:  0.0089
    N = 20000, total time:  0.0166
    N = 40000, total time:  0.0338
    N = 80000, total time:  0.0793
    N = 160000, total time:  0.1335
    
 *  LinkedList Big O: based on timing data, it is O(N)/O(N) = O(1)
 *  Why O(N) (for the initial): As N doubles, the time it takes kind of doubles, signaling 
    linear time.
 *  
 *  
 * Removing from front: LinkedList much faster
 *  
 *  Num Repeats: 100
    Removing from front: ArrayList
    N = 2000, total time:  0.0191
    N = 4000, total time:  0.0576
    N = 8000, total time:  0.2081
    N = 16000, total time:  0.9311
    N = 32000, total time:  3.8596
    
    ArrayList Big O: based on timing data, it is O(N^2)/O(N) = O(N)
    Why O(N^2) (for the initial): As N doubles, the time it takes kind of quadruples, signaling 
    quadratic time.    
    
    Num Repeats: 100
    removing from front: LinkedList
    N = 5000, total time:  0.0109
    N = 10000, total time:  0.0292
    N = 20000, total time:  0.0619
    N = 40000, total time:  0.1303
    N = 80000, total time:  0.2516
 *  
 *  LinkedList Big O: based on timing data, it is O(N)/O(N) = O(1)
 *  Why O(N) (for the initial): As N doubles, the time it takes kind of doubles, signaling 
    linear time.
    
    
    
 * Getting random: ArrayList much faster
 * 
 * Num Repeats: 100
    Getting random: ArrayList
    N = 10000, total time:  0.0143
    N = 20000, total time:  0.0375
    N = 40000, total time:  0.0831
    N = 80000, total time:  0.2086
    N = 160000, total time:  0.5251

    ArrayList Big O: based on timing data, it is O(N)/O(N) = O(1)
    Why O(N) (for the initial): As N doubles, the time it takes kind of doubles, signaling 
    linear time.
    
    Num Repeats: 100
    Getting random: LinkedList
    N = 1000, total time:  0.0572
    N = 2000, total time:  0.2421
    N = 4000, total time:  0.9822
    N = 8000, total time:  3.9814
    N = 16000, total time: 16.3267
    
    LinkedList Big O: based on timing data, it is O(N^2)/O(N) = O(N)
    Why O(N^2) (for the initial): As N doubles, the time it takes kind of quadruples, signaling 
    quadratic time.    
    
    
    
 * Getting all using iterator: about the same
 * Num Repeats: 100
    Getting all using iterator: ArrayList
    N = 50000, total time:  0.0132
    N = 100000, total time:  0.0246
    N = 200000, total time:  0.0396
    N = 400000, total time:  0.0780
    N = 800000, total time:  0.1580

    ArrayList Big O: based on timing data, it is O(N)/O(N) = O(1)
    Why O(N) (for the initial): As N doubles, the time it takes kind of doubles, signaling 
    linear time.

    Num Repeats: 100
    Getting all using iterator: LinkedList
    N = 50000, total time:  0.0189
    N = 100000, total time:  0.0377
    N = 200000, total time:  0.0632
    N = 400000, total time:  0.1273
    N = 800000, total time:  0.2413
    
    LinkedList Big O: based on timing data, it is O(N)/O(N) = O(1)
    Why O(N) (for the initial): As N doubles, the time it takes kind of doubles, signaling 
    linear time.  
    
 * Getting all using get method: ArrayList is much faster
 * 
 * Num Repeats: 100
    Getting all using get method: ArrayList
    N = 100000, total time:  0.0137
    N = 200000, total time:  0.0274
    N = 400000, total time:  0.0587
    N = 800000, total time:  0.1040
    N = 1600000, total time:  0.2034
    
    ArrayList Big O: based on timing data, it is O(N)/O(N) = O(1)
    Why O(N) (for the initial): As N doubles, the time it takes kind of doubles, signaling 
    linear time.


    Num Repeats: 100
    Getting all using get method: LinkedList
    N = 1000, total time:  0.0557
    N = 2000, total time:  0.2398
    N = 4000, total time:  0.9778
    N = 8000, total time:  3.9957
    N = 16000, total time: 16.4017
    
    LinkedList Big O: based on timing data, it is O(N^2)/O(N) = O(N)
    Why O(N^2) (for the initial): As N doubles, the time it takes kind of quadruples, signaling 
    quadratic time.  
 * 
 */

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;

public class LinkedListTester
{

    public static void main(String[] args)
    {

        //CS314 students. Add your tests here:

        //make sure to test empties ****
        //addFirst
        LinkedList<String> test = new LinkedList<>();
        test.addFirst(":)");
        if (test.toString().equals("[:)]"))
            System.out.println("PASSED addFirst TEST");
        else
            System.out.println("FAILED addFirst TEST");
        test.addFirst(":(");
        test.addFirst(":|");
        if (test.toString().equals("[:|, :(, :)]"))
            System.out.println("PASSED addFirst TEST");
        else
            System.out.println("FAILED addFirst TEST");
        test.addFirst("");
        if (test.toString().equals("[, :|, :(, :)]"))
            System.out.println("PASSED addFirst TEST");
        else
            System.out.println("FAILED addFirst TEST");

        //testing size
        if (test.size() == 4)
            System.out.println("PASSED size TEST");
        else
            System.out.println("FAILED size TEST");

        //addLast
        test.addLast("::");
        if (test.toString().equals("[, :|, :(, :), ::]"))
            System.out.println("PASSED addLast TEST");
        else
            System.out.println("FAILED addLast TEST");
        test.makeEmpty();
        test.addLast("TEST FOR EMPTY");
        if (test.toString().equals("[TEST FOR EMPTY]"))
            System.out.println("PASSED addLast TEST");
        else
            System.out.println("FAILED addLast TEST");
        test.addLast("TEST FOR LAST ELEMENT");
        if (test.toString().equals("[TEST FOR EMPTY, TEST FOR LAST ELEMENT]"))
            System.out.println("PASSED addLast TEST");
        else
            System.out.println("FAILED addLast TEST");

        //testing size
        if (test.size() == 2)
            System.out.println("PASSED size TEST");
        else
            System.out.println("FAILED size TEST");

        //removeFirst
        test.removeFirst();
        if (test.toString().equals("[TEST FOR LAST ELEMENT]"))
            System.out.println("PASSED removeFirst TEST");
        else
            System.out.println("FAILED removeFirst TEST");
        test.removeFirst();
        if (test.toString().equals("[]"))
            System.out.println("PASSED removeFirst TEST");
        else
            System.out.println("FAILED removeFirst TEST");
        test.addFirst("test");
        test.addFirst("remove");
        test.addFirst("First");
        test.removeFirst();
        if (test.toString().equals("[remove, test]"))
            System.out.println("PASSED removeFirst TEST");
        else
            System.out.println("FAILED removeFirst TEST");

        //testing size
        if (test.size() == 2)
            System.out.println("PASSED size TEST");
        else
            System.out.println("FAILED size TEST");

        //removeLast
        test.removeLast();
        if (test.toString().equals("[remove]"))
            System.out.println("PASSED removeLast TEST");
        else
            System.out.println("FAILED removeLast TEST");
        test.removeLast();
        if (test.toString().equals("[]"))
            System.out.println("PASSED removeLast TEST");
        else
            System.out.println("FAILED removeLast TEST");
        test.add("REMOVE ME PLEASE");
        test.removeLast();
        if (test.toString().equals("[]"))
            System.out.println("PASSED removeLast TEST");
        else
            System.out.println("FAILED removeLast TEST");

        //testing size
        if (test.size() == 0)
            System.out.println("PASSED size TEST");
        else
            System.out.println("FAILED size TEST");

        //testing size
        //already doing for each method

        //testing toString
        LinkedList<Integer> testToString = new LinkedList<>();
        testToString.add(5);
        testToString.add(4);
        if (testToString.toString().equals("[5, 4]"))
            System.out.println("PASSED toString TEST");
        else
            System.out.println("FAILED toString TEST");
        testToString.removeLast();
        if (testToString.toString().equals("[5]"))
            System.out.println("PASSED toString TEST");
        else
            System.out.println("FAILED toString TEST");
        testToString.add(9999);
        if (testToString.toString().equals("[5, 9999]"))
            System.out.println("PASSED toString TEST");
        else
            System.out.println("FAILED toString TEST");

        //testing size
        if (testToString.size() == 2)
            System.out.println("PASSED size TEST");
        else
            System.out.println("FAILED size TEST");

        //add 
        test.add("");
        test.add("");
        if (test.toString().equals("[, ]"))
            System.out.println("PASSED add TEST");
        else
            System.out.println("FAILED add TEST");
        test.add("what else can I test for this?");
        if (test.toString().equals("[, , what else can I test for this?]"))
            System.out.println("PASSED add TEST");
        else
            System.out.println("FAILED add TEST");
        test.add("[, , what else can I test for this?]");
        if (test.toString().equals("[, , what else can I test for this?, [, , what else can I test for this?]]"))
            System.out.println("PASSED add TEST");
        else
            System.out.println("FAILED add TEST");

        //testing size       
        if (test.size() == 4)
            System.out.println("PASSED size TEST");
        else
            System.out.println("FAILED size TEST");

        //insert
        test.makeEmpty();
        test.insert(test.size(), "does this work?");
        if (test.toString().equals("[does this work?]"))
            System.out.println("PASSED insert TEST");
        else
            System.out.println("FAILED insert TEST");
        test.makeEmpty();
        test.insert(0, "probably not lol");
        if (test.toString().equals("[probably not lol]"))
            System.out.println("PASSED insert TEST");
        else
            System.out.println("FAILED insert TEST");
        test.makeEmpty();
        test.add("im one");
        test.add("im three");
        test.insert(1, "im two");
        if (test.toString().equals("[im one, im two, im three]"))
            System.out.println("PASSED insert TEST");
        else
            System.out.println("FAILED insert TEST");
        test.makeEmpty();
        test.add("im one");
        test.add("im two");
        test.insert(test.size(), "im three");
        if (test.toString().equals("[im one, im two, im three]"))
            System.out.println("PASSED insert TEST");
        else
            System.out.println("FAILED insert TEST");

        //testing size       
        if (test.size() == 3)
            System.out.println("PASSED size TEST");
        else
            System.out.println("FAILED size TEST");

        //set

        test.set(0, "im zero");
        test.set(1, "im one");
        if (test.toString().equals("[im zero, im one, im three]"))
            System.out.println("PASSED set TEST");
        else
            System.out.println("FAILED set TEST");
        test.set(0, "");
        test.set(1, "");

        if (test.toString().equals("[, , im three]"))
            System.out.println("PASSED set TEST");
        else
            System.out.println("FAILED set TEST");
        test.makeEmpty();
        test.add("");
        test.set(0, "Setting an empty");
        if (test.toString().equals("[Setting an empty]"))
            System.out.println("PASSED set TEST");
        else
            System.out.println("FAILED set TEST");

        //testing size       
        if (test.size() == 1)
            System.out.println("PASSED size TEST");
        else
            System.out.println("FAILED size TEST");

        //get

        test.set(0, "im zero");
        test.add("im one");

        if (test.get(test.size() - 1).equals("im one"))
            System.out.println("PASSED get TEST");
        else
            System.out.println("FAILED get TEST");
        if (test.get(0).equals("im zero"))
            System.out.println("PASSED get TEST");
        else
            System.out.println("FAILED get TEST");
        test.insert(1, "im the middle");
        if (test.get(1).equals("im the middle"))
            System.out.println("PASSED get TEST");
        else
            System.out.println("FAILED get TEST");

        //testing size
        if (test.size() == 3)
            System.out.println("PASSED size TEST");
        else
            System.out.println("FAILED size TEST");

        //remove(int)
        test.remove(0);
        if (test.toString().equals("[im the middle, im one]"))
            System.out.println("PASSED remove TEST");
        else
            System.out.println("FAILED remove TEST");
        test.remove(test.size() - 1);
        if (test.toString().equals("[im the middle]"))
            System.out.println("PASSED remove TEST");
        else
            System.out.println("FAILED remove TEST");
        test.remove(test.size() - 1);
        if (test.toString().equals("[]"))
            System.out.println("PASSED remove TEST");
        else
            System.out.println("FAILED remove TEST");
        for (int i = 0; i < 5; i++)
        {
            test.add("" + i);
            test.remove(0);
        }
        if (test.toString().equals("[]"))
            System.out.println("PASSED remove TEST");
        else
            System.out.println("FAILED remove TEST");

        //testing size

        if (test.size() == 0)
            System.out.println("PASSED size TEST");
        else
            System.out.println("FAILED size TEST");

        //remove(E)
        for (int i = 0; i <= 5; i++)
        {
            test.add("" + i);
        }
        test.remove("5");
        if (test.toString().equals("[0, 1, 2, 3, 4]"))
            System.out.println("PASSED remove TEST");
        else
            System.out.println("FAILED remove TEST");
        test.remove(0);
        test.remove("1");
        if (test.toString().equals("[2, 3, 4]"))
            System.out.println("PASSED remove TEST");
        else
            System.out.println("FAILED remove TEST");
        test.remove("6");
        if (test.toString().equals("[2, 3, 4]"))
            System.out.println("PASSED remove TEST");
        else
            System.out.println("FAILED remove TEST");

        //testing size
        if (test.size() == 3)
            System.out.println("PASSED size TEST");
        else
            System.out.println("FAILED size TEST");

        //getSubList
        test.makeEmpty();
        for (int i = 0; i <= 5; i++)
        {
            test.add("" + i);
        }
        if (test.getSubList(0, test.size()).toString().equals("[0, 1, 2, 3, 4, 5]"))
            System.out.println("PASSED getSubList TEST");
        else
            System.out.println("FAILED getSubList TEST");
        if (test.getSubList(3, 4).toString().equals("[3]"))
            System.out.println("PASSED getSubList TEST");
        else
            System.out.println("FAILED getSubList TEST");
        if (test.getSubList(2, 5).toString().equals("[2, 3, 4]"))
            System.out.println("PASSED getSubList TEST");
        else
            System.out.println("FAILED getSubList TEST");
        IList<String> tSL = test.getSubList(2, 5);
        if (tSL.toString().equals("[2, 3, 4]"))
            System.out.println("PASSED getSubList TEST");
        else
            System.out.println("FAILED getSubList TEST");
        tSL = test.getSubList(2, 2);
        if (tSL.toString().equals("[]"))
            System.out.println("PASSED getSubList TEST");
        else
            System.out.println("FAILED getSubList TEST");

        //testing size:
        if (test.size() == 6 && tSL.size() == 0)
            System.out.println("PASSED size TEST");
        else
            System.out.println("FAILED size TEST");

        //indexOf(E)
        if (test.indexOf("1") == 1)
            System.out.println("PASSED indexOf TEST");
        else
            System.out.println("FAILED indexOf TEST");
        if (test.indexOf("7") == -1)
            System.out.println("PASSED indexOf TEST");
        else
            System.out.println("FAILED indexOf TEST");
        if (test.indexOf("0") == 0)
            System.out.println("PASSED indexOf TEST");
        else
            System.out.println("FAILED indexOf TEST");
        if (test.indexOf("5") == test.size() - 1)
            System.out.println("PASSED indexOf TEST");
        else
            System.out.println("FAILED indexOf TEST");

        //indexOf(E,int)
        for (int i = 0; i <= 5; i++)
        {
            test.add("" + i);
        }
        if (test.indexOf("1", 2) == 7)
            System.out.println("PASSED indexOf TEST");
        else
            System.out.println("FAILED indexOf TEST");
        if (test.indexOf("5", 2) == 5 && test.indexOf("5", 5) == 5 && test.indexOf("5", 6) == test.size() - 1)
            System.out.println("PASSED indexOf TEST");
        else
            System.out.println("FAILED indexOf TEST");
        test.insert(5, "99");
        if (test.indexOf("99", 2) == 5 && test.indexOf("99", 6) == -1)
            System.out.println("PASSED indexOf TEST");
        else
            System.out.println("FAILED indexOf TEST");

        //removeRange
        test.makeEmpty();
        for (int i = 0; i <= 10; i++)
        {
            test.add("" + i);
        }
        test.removeRange(0, test.size());
        if (test.toString().equals("[]"))
            System.out.println("PASSED removeRange TEST");
        else
            System.out.println("FAILED removeRange TEST");
        test.makeEmpty();
        for (int i = 0; i <= 10; i++)
        {
            test.add("" + i);
        }
        test.removeRange(1, test.size() - 2);
        if (test.toString().equals("[0, 9, 10]"))
            System.out.println("PASSED removeRange TEST");
        else
            System.out.println("FAILED removeRange TEST");

        if (test.size() == 3)
            System.out.println("PASSED size TEST");
        else
            System.out.println("FAILED size TEST");

        test.removeRange(0, test.size());
        for (int i = 0; i <= 10; i++)
        {
            test.add("" + i);
        }
        test.removeRange(5, 5);
        if (test.toString().equals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]"))
            System.out.println("PASSED removeRange TEST");
        else
            System.out.println("FAILED removeRange TEST");

        //equals

        IList<String> other = new LinkedList<>();
        test.makeEmpty();
        for (int i = 0; i <= 10; i++)
        {
            other.add("" + i);
            test.add("" + i);
        }
        if (other.equals(test))
            System.out.println("PASSED equals TEST");
        else
            System.out.println("FAILED equals TEST");
        test.makeEmpty();
        other.makeEmpty();
        if (other.equals(test))
            System.out.println("PASSED equals TEST");
        else
            System.out.println("FAILED equals TEST");
        test.add(":)");
        other.add(":)");
        other.add("too big brah");
        if (!other.equals(test))
            System.out.println("PASSED equals TEST");
        else
            System.out.println("FAILED equals TEST");

        //makeEmpty

        for (int i = 0; i <= 10; i++)
        {
            other.add("" + i);
            test.add("" + i);
        }
        other.makeEmpty();
        if (other.toString().equals("[]"))
            System.out.println("PASSED makeEmpty TEST");
        else
            System.out.println("FAILED makeEmpty TEST");
        test.makeEmpty();
        if (test.toString().equals("[]"))
            System.out.println("PASSED makeEmpty TEST");
        else
            System.out.println("FAILED makeEmpty TEST");
        for (int i = 0; i < 1; i++)
        {
            other.add("");
        }
        other.makeEmpty();
        if (other.toString().equals("[]"))
            System.out.println("PASSED makeEmpty TEST");
        else
            System.out.println("FAILED makeEmpty TEST");

        //iterator tests:

        for (int i = 0; i <= 10; i++)
        {
            test.add("" + i);
        }
        Iterator<String> iterator = test.iterator();
        while (iterator.hasNext())
        {
            iterator.next();
            iterator.remove();
        }
        if (test.toString().equals("[]"))
            System.out.println("PASSED iterator remove TEST");
        else
            System.out.println("FAILED iterator remove TEST");

        for (int i = 0; i <= 0; i++)
        {
            test.add("" + i);
        }
        iterator = test.iterator();
        while (iterator.hasNext())
        {
            iterator.next();
            iterator.remove();
        }
        if (test.toString().equals("[]"))
            System.out.println("PASSED iterator remove TEST");
        else
            System.out.println("FAILED iterator remove TEST");
        for (int i = 0; i <= 1000; i++)
        {
            test.add("" + i);
        }
        iterator = test.iterator();
        while (iterator.hasNext())
        {
            iterator.next();
            iterator.remove();
        }
        if (test.toString().equals("[]"))
            System.out.println("PASSED iterator remove TEST");
        else
            System.out.println("FAILED iterator remove TEST");

        //next:
        for (int i = 0; i <= 10; i++)
        {
            test.add("" + i);
        }
        int i = 0;
        while (iterator.hasNext())
        {
            if (!iterator.next().equals("" + i))
            {
                System.out.println("FAILED iterator next TEST");
                break;
            }
            i++;
        }
        System.out.println("PASSED iterator next TEST");
        test.makeEmpty();
        test.add("A");
        iterator = test.iterator();
        if (iterator.next().equals("A"))
            System.out.println("PASSED iterator next TEST");
        else
            System.out.println("FAILED iterator next TEST");
        test.makeEmpty();
        test.add("");
        iterator = test.iterator();
        if (iterator.next().equals(""))
            System.out.println("PASSED iterator next TEST");
        else
            System.out.println("FAILED iterator next TEST");

        //hasNext()
        for (int j = 0; j < 11; j++)
        {
            test.add("" + j);
        }
        i = 0;
        while (i < 11)
        {
            if (!iterator.hasNext())
            {
                System.out.println("FAILED iterator hasNext() TEST");
                break;
            }
            i++;
        }
        System.out.println("PASSED iterator hasNext() TEST");
        test.makeEmpty();
        iterator = test.iterator();
        if (!iterator.hasNext())
            System.out.println("PASSED iterator hasNext() TEST");
        else
            System.out.println("FAILED iterator hasNext() TEST");
        test.add("");
        iterator = test.iterator();
        if (iterator.hasNext())
            System.out.println("PASSED iterator hasNext() TEST");
        else
            System.out.println("FAILED iterator hasNext() TEST");

        //comparison();
    }

    private static Object[] toArray(LinkedList<String> list)
    {
        Object[] result = new Object[list.size()];
        Iterator<String> it = list.iterator();
        int index = 0;
        while (it.hasNext())
        {
            Object f = it.next();
            //System.out.println(f);
            result[index] = f;
            index++;
        }
        return result;
    }

    //pre: none
    private static boolean arraysSame(Object[] one, Object[] two)
    {
        boolean same;
        if (one == null || two == null)
        {
            same = (one == two);
        } else
        {
            //neither one or two are null
            assert one != null && two != null;
            same = one.length == two.length;
            if (same)
            {
                int index = 0;
                while (index < one.length && same)
                {
                    same = (one[index].equals(two[index]));
                    index++;
                }
            }
        }
        return same;
    }

    public static final int NUM_DOUBLINGS_OF_N = 5;
    private static final int NUM_REPEATS_OF_TEST = 100;

    // A method to be run to compare the LinkedList you are completing and the Java ArrayList class
    public static void comparison()
    {
        Stopwatch s = new Stopwatch();

        int initialN = 30000;
        addEndArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
        addEndLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 2000;
        addFrontArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
        initialN = 10000;
        addFrontLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 2000;
        removeFrontArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
        initialN = 5000;
        removeFrontLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 10000;
        getRandomArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
        initialN = 1000;
        getRandomLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 50000;
        getAllArrayListUsingIterator(s, initialN, NUM_DOUBLINGS_OF_N);
        getAllLinkedListUsingIterator(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 100000;
        getAllArrayListUsingGetMethod(s, initialN, NUM_DOUBLINGS_OF_N);
        initialN = 1000;
        getAllLinkedListUsingGetMethod(s, initialN, NUM_DOUBLINGS_OF_N);

    }

    // These pairs of method illustarte a failure to use polymorphism
    // If the students had implemented the Java list interface there
    // could be a single method. Also we could use function objects to
    // reduce the awful repitition of code.
    public static void addEndArrayList(Stopwatch s, int initialN, int numTests)
    {

        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++)
        {
            int n = initialN;
            for (int i = 0; i < numTests; i++)
            {
                ArrayList<Integer> javaList = new ArrayList<>();
                s.start();
                for (int j = 0; j < n; j++)
                {
                    javaList.add(j);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Adding at end: ArrayList", totalTimes, initialN);
    }

    private static void showResults(String title, double[] times, int initialN)
    {
        System.out.println();
        System.out.println("Num Repeats: " + NUM_REPEATS_OF_TEST);
        System.out.println(title);
        for (double time : times)
        {
            System.out.print("N = " + initialN + ", total time: ");
            System.out.printf("%7.4f\n", time);
            initialN *= 2;
        }
        System.out.println();
    }

    public static void addEndLinkedList(Stopwatch s, int initialN, int numTests)
    {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++)
        {
            int n = initialN;
            for (int i = 0; i < numTests; i++)
            {
                LinkedList<Integer> studentList = new LinkedList<>();
                s.start();
                for (int j = 0; j < n; j++)
                {
                    studentList.add(j);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Adding at end: LinkedList", totalTimes, initialN);
    }

    public static void addFrontArrayList(Stopwatch s, int initialN, int numTests)
    {

        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++)
        {
            int n = initialN;
            for (int i = 0; i < numTests; i++)
            {
                ArrayList<Integer> javaList = new ArrayList<>();
                s.start();
                for (int j = 0; j < n; j++)
                {
                    javaList.add(0, j);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Adding at front: ArrayList", totalTimes, initialN);
    }

    public static void addFrontLinkedList(Stopwatch s, int initialN, int numTests)
    {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++)
        {
            int n = initialN;
            for (int i = 0; i < numTests; i++)
            {
                LinkedList<Integer> studentList = new LinkedList<>();
                s.start();
                for (int j = 0; j < n; j++)
                {
                    studentList.insert(0, j);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Adding at front: LinkedList", totalTimes, initialN);
    }

    public static void removeFrontArrayList(Stopwatch s, int initialN, int numTests)
    {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++)
        {
            int n = initialN;
            for (int i = 0; i < numTests; i++)
            {
                ArrayList<String> javaList = new ArrayList<>();
                for (int j = 0; j < n; j++)
                {
                    javaList.add(j + "");
                }
                s.start();
                while (!javaList.isEmpty())
                {
                    javaList.remove(0);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Removing from front: ArrayList", totalTimes, initialN);
    }

    public static void removeFrontLinkedList(Stopwatch s, int initialN, int numTests)
    {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++)
        {
            int n = initialN;
            for (int i = 0; i < numTests; i++)
            {
                LinkedList<String> studentList = new LinkedList<>();
                for (int j = 0; j < n; j++)
                {
                    studentList.add(j + "");
                }
                s.start();
                while (studentList.size() != 0)
                {
                    studentList.removeFirst();
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("removing from front: LinkedList", totalTimes, initialN);
    }

    public static void getRandomArrayList(Stopwatch s, int initialN, int numTests)
    {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++)
        {
            int n = initialN;
            int total = 0;
            Random r = new Random();
            for (int i = 0; i < numTests; i++)
            {
                ArrayList<Integer> javaList = new ArrayList<>();
                for (int j = 0; j < n; j++)
                {
                    javaList.add(j);
                }
                s.start();
                for (int j = 0; j < n; j++)
                {
                    total += javaList.get(r.nextInt(javaList.size()));
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting random: ArrayList", totalTimes, initialN);
    }

    public static void getRandomLinkedList(Stopwatch s, int initialN, int numTests)
    {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++)
        {
            int n = initialN;
            int total = 0;
            Random r = new Random();
            for (int i = 0; i < numTests; i++)
            {
                LinkedList<Integer> studentList = new LinkedList<>();
                for (int j = 0; j < n; j++)
                {
                    studentList.add(j);
                }
                s.start();
                for (int j = 0; j < n; j++)
                {
                    total += studentList.get(r.nextInt(studentList.size()));
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting random: LinkedList", totalTimes, initialN);
    }

    public static void getAllArrayListUsingIterator(Stopwatch s, int initialN, int numTests)
    {

        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++)
        {
            int n = initialN;
            int total = 0;
            for (int i = 0; i < numTests; i++)
            {
                ArrayList<Integer> javaList = new ArrayList<>();
                for (int j = 0; j < n; j++)
                {
                    javaList.add(j);
                }
                Iterator<Integer> it = javaList.iterator();
                s.start();
                while (it.hasNext())
                {
                    total += it.next();
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting all using iterator: ArrayList", totalTimes, initialN);
    }

    public static void getAllLinkedListUsingIterator(Stopwatch s, int initialN, int numTests)
    {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++)
        {
            int n = initialN;
            int total = 0;
            for (int i = 0; i < numTests; i++)
            {
                LinkedList<Integer> studentList = new LinkedList<>();
                for (int j = 0; j < n; j++)
                {
                    studentList.add(j);
                }
                Iterator<Integer> it = studentList.iterator();
                s.start();
                while (it.hasNext())
                {
                    total += it.next();
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting all using iterator: LinkedList", totalTimes, initialN);
    }

    public static void getAllArrayListUsingGetMethod(Stopwatch s, int initialN, int numTests)
    {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++)
        {
            int n = initialN;
            for (int i = 0; i < numTests; i++)
            {
                ArrayList<Integer> javaList = new ArrayList<>();
                for (int j = 0; j < n; j++)
                {
                    javaList.add(j);
                }
                s.start();
                int x = 0;
                for (int j = 0; j < javaList.size(); j++)
                {
                    x += javaList.get(j);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting all using get method: ArrayList", totalTimes, initialN);
    }

    public static void getAllLinkedListUsingGetMethod(Stopwatch s, int initialN, int numTests)
    {
        double[] totalTimes = new double[numTests];
        for (int t = 0; t < NUM_REPEATS_OF_TEST; t++)
        {
            int n = initialN;
            for (int i = 0; i < numTests; i++)
            {
                LinkedList<Integer> studentList = new LinkedList<>();
                for (int j = 0; j < n; j++)
                {
                    studentList.add(j);
                }
                s.start();
                int x = 0;
                for (int j = 0; j < studentList.size(); j++)
                {
                    x += studentList.get(j);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting all using get method: LinkedList", totalTimes, initialN);
    }

    // for future changes
    private static interface ArrayListOp
    {
        public <E> void op(ArrayList<E> list, E data);
    }

    private ArrayListOp addAL = new ArrayListOp()
    {
        public <E> void op(ArrayList<E> list, E data)
        {
            list.add(data);
        }
    };

    private ArrayListOp addFrontAL = new ArrayListOp()
    {
        public <E> void op(ArrayList<E> list, E data)
        {
            list.add(0, data);
        }
    };

    private ArrayListOp removeFrontAL = new ArrayListOp()
    {
        public <E> void op(ArrayList<E> list, E data)
        {
            list.remove(0);
        }
    };

    private static interface LinkedListOp<E>
    {
        public void op(LinkedList<E> list);
    }
}
