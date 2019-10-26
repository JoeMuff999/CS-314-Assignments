

/*  Student information for assignment:
 *
 *  On <MY> honor, <Joey Muffoletto>, this programming assignment is <MY> own work
 *  and <I> have not provided this code to any other student.
 *
 *  Number of slip days used: 1
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: jrm7925
 *  email address: jrmuff@utexas.edu
 *  Grader name: Andrew
 *  Section number: 50220
 */


import java.util.ArrayList;
import java.util.Collections;

/**
 * Tester class for the methods in Recursive.java
 * @author scottm
 *
 */
public class RecursiveTester {

    // run the tests
    public static void main(String[] args) {
        studentTests();
    }    
    private static void runMazeTest(String rawMaze, int rows, int expected, int num) {
        char[][] maze = makeMaze(rawMaze, rows);
        System.out.println("Can escape maze test number " + num);
        printMaze(maze);
        int actual = Recursive.canEscapeMaze(maze);
        System.out.println("Expected result: " + expected);
        System.out.println("Actual result:   " + actual);
        if (expected == actual) {
            System.out.println("passed test " + num);
        } else {
            System.out.println("FAILED TEST " + num);
        }
        System.out.println();
    }

    // print the given maze
    // pre: none
    private static void printMaze(char[][] maze) {
        if (maze == null) {
            System.out.println("NO MAZE GIVEN");
        } else {
            for (char[] row : maze) {
                for (char c : row) {
                    System.out.print(c);
                    System.out.print(" ");
                }
                System.out.println();
            }
        }
    }

    // generate a char[][] given the raw string
    // pre: rawMaze != null, rawMaze.length() % rows == 0
    private static char[][] makeMaze(String rawMaze, int rows) {
        if (rawMaze == null || rawMaze.length() % rows != 0) {
            throw new IllegalArgumentException("Violation of precondition in makeMaze."
                            + "Either raw data is null or left over values: ");
        }
        int cols = rawMaze.length() / rows;
        char[][] result = new char[rows][cols];
        int rawIndex = 0;
        for (int r = 0; r < result.length; r++) {
            for (int c = 0; c < result[0].length; c++) {
                result[r][c] = rawMaze.charAt(rawIndex);
                rawIndex++;
            }
        }
        return result;
    }

    private static void  doCarpetTest() {
        Recursive.drawCarpet(729, 4);
        Recursive.drawCarpet(729, 1);        
    }

    
    private static void doOneFlowTest(int[][] world, int r, int c, boolean expected, int testNum) {
        System.out.println("Can Flow Off Map Test Number " + testNum);
        boolean actual = Recursive.canFlowOffMap(world, r, c);
        System.out.println("Start location = " + r + ", " + c);
        System.out.println("Expected result = " + expected + " actual result = " + actual);
        if (expected == actual) {
            System.out.println("passed test " + testNum + " can flow off map.");
        } else {
            System.out.println("FAILED TEST " + testNum + " can flow off map.");
        }
        System.out.println();
    }


    // pre: r != null
    // post: run student test
    private static void studentTests() {
        // CS314 students put your tests here
      System.out.println("Stress test for minDifference - may take up to a minute");
      int[] testerArr = new int[] {5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 100000};
      Stopwatch s = new Stopwatch();
      s.start();
      int actualInt = Recursive.minDifference(4, testerArr);
      s.stop();
      System.out.println("Time to solve for 16 people on 4 teams: " + s.time() + "\n");
      System.out.println(actualInt);
        Recursive r = new Recursive();
        if(r == null)
            throw new IllegalArgumentException("how did this even happen");
        //binary: 
        String actualBinary = Recursive.getBinary(2147483647);
        String expectedBinary = "1111111111111111111111111111111";
        if (actualBinary.equals(expectedBinary))
            System.out.println( "Test 1 passed. get binary.");
        else
            System.out.println( "Test 1 failed. get binary. expected: " + expectedBinary + ", actual: " + actualBinary);


        actualBinary = Recursive.getBinary(-2147483647);
        expectedBinary = "-1111111111111111111111111111111";
        if (actualBinary.equals(expectedBinary))
            System.out.println( "Test 2 passed. get binary.");
        else
            System.out.println( "Test 2 failed. get binary. expected: " + expectedBinary + ", actual: " + actualBinary);
        //reverse tests
        String actualRev = Recursive.revString("=");
        String expectedRev = "=";
        if (actualRev.equals(expectedRev))
            System.out.println( "Test 1 passed. reverse string.");
        else
            System.out.println( "Test 1 failed. reverse string. expected: " + expectedRev + ", actual: " + actualRev);


        actualRev = Recursive.revString("");
        expectedRev = "";
        if (actualRev.equals(expectedRev))
            System.out.println( "Test 2 passed. reverse string.");
        else
            System.out.println( "Test 2 failed. reverse string. expected: " + expectedRev + ", actual: " + actualRev);
       
        //nextIsDouble
        int[] numsForDouble = {256,128,64,32,16,32,64,128};
        int actualDouble = Recursive.nextIsDouble(numsForDouble);
        int expectedDouble = 3;
        if (actualDouble == expectedDouble)
            System.out.println( "Test 1 passed. next is double.");
        else
            System.out.println( "Test 1 failed. next is double. expected: " + expectedDouble + ", actual: " + actualDouble);


        numsForDouble = new int[]{0,0,0,0,0};
        actualDouble = Recursive.nextIsDouble(numsForDouble);
        expectedDouble = 5;
        if (actualDouble == expectedDouble)
            System.out.println( "Test 2 passed. next is double.");
        else
            System.out.println( "Test 2 failed. next is double. expected: " + expectedDouble + ", actual: " + actualDouble);
        //mnemonics
        ArrayList<String> mnemonics = Recursive.listMnemonics("2");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("A");
        expected.add("B");
        expected.add("C");
        if (mnemonics.equals(expected))
            System.out.println( "Test 1 passed. Phone mnemonics.");
        else
            System.out.println( "Test 1 failed. Phone mnemonics.");

        mnemonics = Recursive.listMnemonics("623");
        Collections.sort(mnemonics);
        expected.clear();
        expected.add("MAD");
        expected.add("MBD");
        expected.add("MCD");
        expected.add("NAD");
        expected.add("NBD");
        expected.add("NCD");
        expected.add("OAD");
        expected.add("OBD");
        expected.add("OCD");
        expected.add("MAE");
        expected.add("MBE");
        expected.add("MCE");
        expected.add("NAE");
        expected.add("NBE");
        expected.add("NCE");
        expected.add("OAE");
        expected.add("OBE");
        expected.add("OCE");
        expected.add("MAF");
        expected.add("MBF");
        expected.add("MCF");
        expected.add("NAF");
        expected.add("NBF");
        expected.add("NCF");
        expected.add("OAF");
        expected.add("OBF");
        expected.add("OCF");
        
        Collections.sort(expected);
        if (mnemonics.equals(expected))
            System.out.println( "Test 2 passed. Phone mnemonics.");
        else
            System.out.println( "Test 2 failed. Phone mnemonics.");
        //canFlowOffMap
        int testNum = 1;
        int[][] world = {{5,5,5,5,5,5},
                         {5,5,-8,-9,-10,5},
                         {5,5,-7,5,-11,5},
                         {5,5,-6,4,-12,5},
                         {5,5,-5,3,-13,5},
                         {5,5,-4,2,-14,5},
                         {5,-2,-3,1,-15,5},
                         {5,5,5,5,-16,5}};

       doOneFlowTest(world, 6, 1, true, testNum++);
       int [][] world2 = {{4,4,4,4,4,4},
               {4,5,5,5,5,5},
               {4,5,5,5,5,4},
               {4,5,4,4,5,4},
               {4,5,3,3,5,4},
               {4,5,2,2,5,4},
               {4,5,5,1,5,4},
               {4,4,4,4,4,4}};
       doOneFlowTest(world2, 1, 1, true, testNum++);
       //minDifference in teams
       int[] abilities = {0,1,2,4,5,6};
       if(Recursive.minDifference(2, abilities) == 0)
           System.out.println( "Test 1 passed. min difference.");
       else
           System.out.println( "Test 1 failed. min difference.");
       if(Recursive.minDifference(5, abilities) == 5)
           System.out.println( "Test 2 passed. min difference.");
       else
           System.out.println( "Test 2 failed. min difference.");
       //maze
       int mazeTestNum = 1;
       runMazeTest("SE", 1, 2, mazeTestNum++);
       runMazeTest("SEG$", 1, 1, mazeTestNum++);
    }


}