/*  Student information for assignment:
*
*  On my honor, <NAME>, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  Name:
*  email address:
*  UTEID:
*  Grader name:
*  Number of slip days used on this assignment:
*/

//Experiment results. CS314 students, place your experiment
//results here:
/*
 * MethodL
 * Adding at End: roughly the same speed
 *  LinkedList Big O: This method is O(1).
 *  ArrayList Big O: This method is O(1).
 * Adding at front: LinkedList much faster
 *  LinkedList Big O: based on timing data, it is O
 * Removing from front: LinkedList much faster
 * Getting random: ArrayList much faster
 * Getting all using iterator: about the same
 * Getting all using get method: ArrayList is much faster
 * 
 * 
 */


import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class LinkedListTester {

   public static void main(String[] args) {

       LinkedList<String> list = new LinkedList<>();
       Object[] actual;
       Object[] expected;

       //test 0
       System.out.println("\nTest 0: initial list is empty");
       if( list.toString().equals("[]") )
           System.out.println("Passed test 0");
       else
           System.out.println("Failed test 0");

       //test 0.1
       System.out.println("\nTest 0.1: add to end");
       list.add("A");
       if( list.get(0).equals("A") )
           System.out.println("Passed test 0.1");
       else
           System.out.println("Failed test 0.1");  

       //test 0.2
       System.out.println("\nTest 0.2: size");
       if( list.size() == 1 )
           System.out.println("Passed test 0.2");
       else
           System.out.println("Failed test 0.2"); 

       //test 0.3
       System.out.println("\nTest 0.3: remove from position 0");
       String removed = list.remove(0);
       if(removed.equals("A"))
           System.out.println("Passed test 0.3");
       else
           System.out.println("Failed test 0.3");            

       System.out.println("\nTest 0.31: toStrin after remove");
       //test 0.31
       if( list.toString().equals("[]") )
           System.out.println("Passed test 0.31");
       else
           System.out.println("Failed test 0.31");  

       //test 0.4
       System.out.println("\nTest 0.4: size");
       if( list.size() == 0 )
           System.out.println("Passed test 0.4");
       else
           System.out.println("Failed test 0.4"); 

       //test 0.5
       System.out.println("\nTest 0.5: add and toString");
       list.add("A");
       list.add("B");
       if( list.toString().equals("[A, B]") )
           System.out.println("Passed test 0.5");
       else
           System.out.println("Failed test 0.5");  

       //test 0.6
       System.out.println("\nTest 0.6: size");
       if( list.size() == 2 )
           System.out.println("Passed test 0.6");
       else
           System.out.println("Failed test 0.6");
       
       //test 0.7
       System.out.println("\nTest 0.7: makeEmpty");
       list.makeEmpty();
       if( list.size() == 0 )
           System.out.println("Passed test 0.7");
       else
           System.out.println("Failed test 0.7");
       
     //test 0.8
       System.out.println("\nTest 0.8: makeEmpty on empty list");
       list.makeEmpty();
       if( list.size() == 0 )
           System.out.println("Passed test 0.8");
       else
           System.out.println("Failed test 0.8");


       //test 1
       System.out.println("\nTest 1: Adding at end");
       list = new LinkedList<>();
       list.add("A");
       actual = toArray(list);
       expected = new Object[]{"A"};
       System.out.println( "Expected result: " + Arrays.toString(expected) );
       System.out.println( "Actual result: " + Arrays.toString(actual) );      
       if( arraysSame(actual, expected) )
           System.out.println("Passed test 1");
       else
           System.out.println("Failed test 1");


       //test 2
       System.out.println("\nTest 2: making empty");
       list.makeEmpty();
       actual = toArray(list);
       expected = new Object[] {};
       System.out.println( "Expected result: " + Arrays.toString(expected) );
       System.out.println( "Actual result: " + Arrays.toString(actual) );  
       if( arraysSame(actual, expected) )
           System.out.println("Passed test 2");
       else
           System.out.println("Failed test 2");


       //test 3
       System.out.println("\nTest 3: Adding at pos 0 in empty list");
       list.insert(0, "A");
       actual = toArray(list);
       expected = new Object[] {"A"};
       System.out.println( "Expected result: " + Arrays.toString(expected) );
       System.out.println( "Actual result: " + Arrays.toString(actual) );          
       if( arraysSame(actual, expected) )
           System.out.println("Passed test 3");
       else
           System.out.println("Failed test 3");



       //test 4
       System.out.println("\nTest 4: Adding at front");
       list = new LinkedList<>();
       list.addFirst("A");
       actual = toArray(list);
       expected = new Object[] {"A"};
       System.out.println( "Expected result: " + Arrays.toString(expected) );
       System.out.println( "Actual result: " + Arrays.toString(actual) );  
       if( arraysSame(actual, expected) )
           System.out.println("Passed test 4");
       else
           System.out.println("Failed test 4");



       //test 5
       System.out.println("\nTest 5: Removing from front");
       list.removeFirst();
       actual = toArray(list);
       expected = new Object[] {};
       System.out.println( "Expected result: " + Arrays.toString(expected) );
       System.out.println( "Actual result: " + Arrays.toString(actual) );  
       if( arraysSame(actual, expected) )
           System.out.println("Passed test 5");
       else
           System.out.println("Failed test 5");


       //test 6
       list = new LinkedList<>();
       System.out.println("\nTest 6: Adding at end");
       list.addLast("A");
       actual = toArray(list);
       expected = new Object[] {"A"};
       System.out.println( "Expected result: " + Arrays.toString(expected) );
       System.out.println( "Actual result: " + Arrays.toString(actual) );  
       if( arraysSame(actual, expected) )
           System.out.println("Passed test 6");
       else
           System.out.println("Failed test 6");


       //test 7
       System.out.println("\nTest 7: Removing from back");
       list.removeLast();
       actual = toArray(list);
       expected = new Object[] {};
       System.out.println( "Expected result: " + Arrays.toString(expected) );
       System.out.println( "Actual result: " + Arrays.toString(actual) );  
       if( arraysSame(actual, expected) )
           System.out.println("Passed test 7");
       else
           System.out.println("Failed test 7");

       //test 8
       System.out.println("\nTest 8: Adding at middle");
       list = new LinkedList<>();
       list.add("A");
       list.add("C");
       list.insert(1, "B");
       actual = toArray(list);
       expected = new Object[] {"A", "B", "C"};
       System.out.println( "Expected result: " + Arrays.toString(expected) );
       System.out.println( "Actual result: " + Arrays.toString(actual) );  
       if( arraysSame(actual, expected) )
           System.out.println("Passed test 8");
       else
           System.out.println("Failed test 8");


       //test 9
       System.out.println("\nTest 9: Setting");
       list = new LinkedList<>();
       list.add("A");
       list.add("D");
       list.add("C");
       String oldValue = list.set(1, "B");
       actual = toArray(list);
       expected = new Object[] {"A", "B", "C"};
       System.out.println( "Expected result: " + Arrays.toString(expected) );
       System.out.println( "Actual result: " + Arrays.toString(actual) );  
       if( arraysSame(actual, expected) )
           System.out.println("Passed test 9.1");
       else
           System.out.println("Failed test 9.1");
       if( oldValue.equals("D") )
           System.out.println("Passed test 9.2");
       else
           System.out.println("Failed test 9.2");


       //test 10
       System.out.println("\nTest 10: Removing");
       list = new LinkedList<>();
       list.add("A");
       list.add("B");
       list.add("C");
       list.add("D");
       list.remove(0);
       list.remove( list.size() - 1 );
       actual = toArray(list);
       expected = new Object[] {"B", "C"};
       System.out.println( "Expected result: " + Arrays.toString(expected) );
       System.out.println( "Actual result: " + Arrays.toString(actual) );  
       if( arraysSame(actual, expected) )
           System.out.println("Passed test 10");
       else
           System.out.println("Failed test 10");

       //CS314 students. Add your tests here:
       
       //make sure to test empties ****
       //addFirst
       LinkedList<String> test = new LinkedList<>();
       actual = new Object[] {};
       expected = new Object[] {};
       test.addFirst(":)");
       if(test.toString().equals("[:)]"))
           System.out.println("PASSED addFirst TEST");
       else
           System.out.println("FAILED addFirst TEST");
       test.addFirst(":(");
       test.addFirst(":|");
       if(test.toString().equals("[:|, :(, :)]"))
           System.out.println("PASSED addFirst TEST");
       else
           System.out.println("FAILED addFirst TEST");
       test.addFirst("");       
       if(test.toString().equals("[, :|, :(, :)]"))
           System.out.println("PASSED addFirst TEST");
       else
           System.out.println("FAILED addFirst TEST");
       
       //testing size
       if(test.size() == 4)
           System.out.println("PASSED size TEST");
       else
           System.out.println("FAILED size TEST");
       
       //addLast
       test.addLast("::");
       if(test.toString().equals("[, :|, :(, :), ::]"))
           System.out.println("PASSED addLast TEST");
       else
           System.out.println("FAILED addLast TEST");
       test.makeEmpty();
       test.addLast("TEST FOR EMPTY");
       if(test.toString().equals("[TEST FOR EMPTY]"))
           System.out.println("PASSED addLast TEST");
       else
           System.out.println("FAILED addLast TEST");
       test.addLast("TEST FOR LAST ELEMENT");
       if(test.toString().equals("[TEST FOR EMPTY, TEST FOR LAST ELEMENT]"))
           System.out.println("PASSED addLast TEST");
       else
           System.out.println("FAILED addLast TEST");       
       
     //testing size
       if(test.size() == 2)
           System.out.println("PASSED size TEST");
       else
           System.out.println("FAILED size TEST");
       
       //removeFirst
       test.removeFirst();
       if(test.toString().equals("[TEST FOR LAST ELEMENT]")) 
           System.out.println("PASSED removeFirst TEST");
       else
           System.out.println("FAILED removeFirst TEST");  
       test.removeFirst();
       if(test.toString().equals("[]")) 
           System.out.println("PASSED removeFirst TEST");
       else
           System.out.println("FAILED removeFirst TEST");
       test.addFirst("test");
       test.addFirst("remove");
       test.addFirst("First");
       test.removeFirst();
       if(test.toString().equals("[remove, test]"))
           System.out.println("PASSED removeFirst TEST");
       else
           System.out.println("FAILED removeFirst TEST");  
       
     //testing size
       if(test.size() == 2)
           System.out.println("PASSED size TEST");
       else
           System.out.println("FAILED size TEST");
       
       //removeLast
       test.removeLast();
       if(test.toString().equals("[remove]"))
           System.out.println("PASSED removeLast TEST");
       else
           System.out.println("FAILED removeLast TEST");  
       test.removeLast();
       if(test.toString().equals("[]"))
           System.out.println("PASSED removeLast TEST");
       else
           System.out.println("FAILED removeLast TEST");  
       test.add("REMOVE ME PLEASE");
       test.removeLast();
       if(test.toString().equals("[]"))
           System.out.println("PASSED removeLast TEST");
       else
           System.out.println("FAILED removeLast TEST"); 
       
     //testing size
       if(test.size() == 0)
           System.out.println("PASSED size TEST");
       else
           System.out.println("FAILED size TEST");
       
       //testing size
       //already doing for each method
       
       //testing toString
       LinkedList<Integer> testToString = new LinkedList<>();
       testToString.add(5);
       testToString.add(4);
       if(testToString.toString().equals("[5, 4]"))
           System.out.println("PASSED toString TEST");
       else
           System.out.println("FAILED toString TEST");
       testToString.removeLast();
       if(testToString.toString().equals("[5]"))
           System.out.println("PASSED toString TEST");
       else
           System.out.println("FAILED toString TEST");
       testToString.add(9999);
       if(testToString.toString().equals("[5, 9999]"))
           System.out.println("PASSED toString TEST");
       else
           System.out.println("FAILED toString TEST");
       
       //testing size
       if(testToString.size() == 2)
           System.out.println("PASSED size TEST");
       else
           System.out.println("FAILED size TEST");
       
       //add 
       test.add("");
       test.add("");
       if(test.toString().equals("[, ]"))
           System.out.println("PASSED add TEST");
       else
           System.out.println("FAILED add TEST"); 
       test.add("what else can I test for this?");
       if(test.toString().equals("[, , what else can I test for this?]"))
           System.out.println("PASSED add TEST");
       else
           System.out.println("FAILED add TEST"); 
       test.add("[, , what else can I test for this?]");
       if(test.toString().equals("[, , what else can I test for this?, [, , what else can I test for this?]]"))
           System.out.println("PASSED add TEST");
       else
           System.out.println("FAILED add TEST"); 
       
       //testing size       
       if(test.size() == 4)
           System.out.println("PASSED size TEST");
       else
           System.out.println("FAILED size TEST");
       
       //insert
       test.makeEmpty();
       test.insert(test.size(),"does this work?");
       if(test.toString().equals("[does this work?]"))
           System.out.println("PASSED insert TEST");
       else
           System.out.println("FAILED insert TEST"); 
       test.makeEmpty();
       test.insert(0,"probably not lol");
       if(test.toString().equals("[probably not lol]"))
           System.out.println("PASSED insert TEST");
       else
           System.out.println("FAILED insert TEST");
       test.makeEmpty();
       test.add("im one");
       test.add("im three");
       test.insert(1,"im two");
       if(test.toString().equals("[im one, im two, im three]"))
           System.out.println("PASSED insert TEST");
       else
           System.out.println("FAILED insert TEST"); 
       test.makeEmpty();
       test.add("im one");
       test.add("im two");
       test.insert(test.size(),"im three");
       if(test.toString().equals("[im one, im two, im three]"))
           System.out.println("PASSED insert TEST");
       else
           System.out.println("FAILED insert TEST");
       
       //testing size       
       if(test.size() == 3)
           System.out.println("PASSED size TEST");
       else
           System.out.println("FAILED size TEST");
       
       //set
       
       
       // CS314 Students:
       // uncomment the following line to run tests comparing
       // your LinkedList class to the java ArrayList class.
       //comparison();
   }

   private static Object[] toArray(LinkedList<String> list) {
       Object[] result = new Object[list.size()];
       Iterator<String> it = list.iterator();
       int index = 0;
       while( it.hasNext() ){
           Object f = it.next();
           //System.out.println(f);
           result[index] = f;
           index++;
       }
       return result;
   }

   //pre: none
   private static boolean arraysSame(Object[] one, Object[] two)  {
       boolean same;
       if( one == null || two == null ) {
           same = (one == two);
       }
       else {
           //neither one or two are null
           assert one != null && two != null;
           same = one.length == two.length;
           if( same ) {
               int index = 0;
               while( index < one.length && same ) {
                   same = ( one[index].equals(two[index]) );
                   index++;
               }
           }
       }
       return same;
   }
  
   
   public static final int NUM_DOUBLINGS_OF_N = 5;
   private static final int NUM_REPEATS_OF_TEST = 100;

   // A method to be run to compare the LinkedList you are completing and the Java ArrayList class
   public static void comparison() {
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
   public static void addEndArrayList(Stopwatch s, int initialN, int numTests) {

       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           for (int i = 0; i < numTests; i++) {
               ArrayList<Integer> javaList = new ArrayList<>();
               s.start();
               for (int j = 0; j < n; j++) {
                   javaList.add(j);
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Adding at end: ArrayList", totalTimes, initialN);
   }

   private static void showResults(String title, double[] times, int initialN) {
       System.out.println();
       System.out.println("Num Repeats: " + NUM_REPEATS_OF_TEST);
       System.out.println(title);
       for (double time : times) {
           System.out.print("N = " + initialN + ", total time: ");
           System.out.printf("%7.4f\n", time);
           initialN *= 2;
       }
       System.out.println();
   }

   public static void addEndLinkedList(Stopwatch s, int initialN, int numTests){
       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           for (int i = 0; i < numTests; i++) {
               LinkedList<Integer> studentList = new LinkedList<>();
               s.start();
               for (int j = 0; j < n; j++) {
                   studentList.add(j);
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Adding at end: LinkedList", totalTimes, initialN);
   }

   public static void addFrontArrayList(Stopwatch s, int initialN, int numTests){

       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           for (int i = 0; i < numTests; i++) {
               ArrayList<Integer> javaList = new ArrayList<>();
               s.start();
               for (int j = 0; j < n; j++) {
                   javaList.add(0, j);
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Adding at front: ArrayList", totalTimes, initialN);
   }

   public static void addFrontLinkedList(Stopwatch s, int initialN, int numTests){
       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           for (int i = 0; i < numTests; i++) {
               LinkedList<Integer> studentList = new LinkedList<>();
               s.start();
               for (int j = 0; j < n; j++) {
                   studentList.insert(0, j);
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Adding at front: LinkedList", totalTimes, initialN);
   }

   public static void removeFrontArrayList(Stopwatch s, int initialN, int numTests){     
       double[] totalTimes = new double[numTests];        
       for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           for (int i = 0; i < numTests; i++) {
               ArrayList<String> javaList = new ArrayList<>();
               for (int j = 0; j < n; j++) {
                   javaList.add(j + "");
               }
               s.start();
               while (!javaList.isEmpty()) {
                   javaList.remove(0);
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Removing from front: ArrayList", totalTimes, initialN);
   }

   public static void removeFrontLinkedList(Stopwatch s, int initialN, int numTests){
       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;    
           for (int i = 0; i < numTests; i++) {
               LinkedList<String> studentList = new LinkedList<>();
               for (int j = 0; j < n; j++) {
                   studentList.add(j + "");
               }
               s.start();
               while (studentList.size() != 0) {
                   studentList.removeFirst();
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("removing from front: LinkedList", totalTimes, initialN);
   }

   public static void getRandomArrayList(Stopwatch s, int initialN, int numTests){
       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           int total = 0;
           Random r = new Random();
           for (int i = 0; i < numTests; i++) {
               ArrayList<Integer> javaList = new ArrayList<>();
               for (int j = 0; j < n; j++) {
                   javaList.add(j);
               }
               s.start();
               for (int j = 0; j < n; j++) {
                   total += javaList.get(r.nextInt(javaList.size()));
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Getting random: ArrayList", totalTimes, initialN);
   }

   public static void getRandomLinkedList(Stopwatch s, int initialN, int numTests){
       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           int total = 0;
           Random r = new Random();
           for (int i = 0; i < numTests; i++) {
               LinkedList<Integer> studentList = new LinkedList< >();
               for (int j = 0; j < n; j++) {
                   studentList.add(j);
               }
               s.start();
               for (int j = 0; j < n; j++) {
                   total += studentList.get( r.nextInt(studentList.size()) );
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Getting random: LinkedList", totalTimes, initialN);
   }

   public static void getAllArrayListUsingIterator(Stopwatch s, int initialN, int numTests){

       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           int total = 0;
           for (int i = 0; i < numTests; i++) {
               ArrayList<Integer> javaList = new ArrayList<>();
               for (int j = 0; j < n; j++) {
                   javaList.add(j);
               }
               Iterator<Integer> it = javaList.iterator();
               s.start();
               while (it.hasNext()) {
                   total += it.next();
               }        
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Getting all using iterator: ArrayList", totalTimes, initialN);
   }

   public static void getAllLinkedListUsingIterator(Stopwatch s, int initialN, int numTests){
       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           int total = 0;
           for (int i = 0; i < numTests; i++) {
               LinkedList<Integer> studentList = new LinkedList<>();
               for (int j = 0; j < n; j++) {
                   studentList.add(j);
               }
               Iterator<Integer> it = studentList.iterator();
               s.start();
               while (it.hasNext()) {
                   total += it.next();
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Getting all using iterator: LinkedList", totalTimes, initialN);
   }

   public static void getAllArrayListUsingGetMethod(Stopwatch s, int initialN, int numTests){
       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           for (int i = 0; i < numTests; i++) {
               ArrayList<Integer> javaList = new ArrayList<>();
               for (int j = 0; j < n; j++) {
                   javaList.add(j);
               }
               s.start();
               int x = 0;
               for (int j = 0; j < javaList.size(); j++) {
                   x += javaList.get(j);
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Getting all using get method: ArrayList", totalTimes, initialN);
   }

   public static void getAllLinkedListUsingGetMethod(Stopwatch s, int initialN, int numTests){
       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           for (int i = 0; i < numTests; i++) {
               LinkedList<Integer> studentList = new LinkedList<>();
               for (int j = 0; j < n; j++) {
                   studentList.add(j);
               }
               s.start();
               int x = 0;
               for (int j = 0; j < studentList.size(); j++) {
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
   private static interface ArrayListOp {
       public <E> void op(ArrayList<E> list, E data);
   }
   
   private ArrayListOp addAL = new ArrayListOp() {
       public <E> void op(ArrayList<E> list, E data) {
           list.add(data);
       }
   };
   
   private ArrayListOp addFrontAL = new ArrayListOp() {
       public <E> void op(ArrayList<E> list, E data) {
           list.add(0, data);
       }
   };
   
   private ArrayListOp removeFrontAL = new ArrayListOp() {
       public <E> void op(ArrayList<E> list, E data) {
           list.remove(0);
       }
   };

   private static interface LinkedListOp<E> {
       public void op(LinkedList<E> list);
   }
}
