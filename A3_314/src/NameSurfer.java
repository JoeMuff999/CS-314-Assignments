
/*
 * Student information for assignment: Replace <NAME> in the following with your
 * name. You are stating, on your honor you did not copy any other code on this
 * assignment and have not provided your code to anyone. 
 * 
 * On my honor, Joey Mufoletto, this programming assignment is my own work 
 * and I have not provided this code
 * to any other student. 
 * UTEID: jrm7925
 * email address: jrmuff@utexas.edu
 * Number of slip days I am using: 2
 * 
 * The documentation for my custom search:
    * Returns an ArrayList of Strings of names that have been getting less.
    * popular every decade until int decade, and then getting more popular every decade after int decade. 
    * Decade can be any year. Ex: 1956 will return the data based on the decade index of 1950.
    * If there is only one decade before or after the index, will still return true if the other
    * decades satisfy the requirement. Ex: 1900-2000, 1910 will still be considered
    * sufficient if 1910-2000 are always increasing in popularity regardless of 1900's and 1910's values.
    * The Strings must be in sorted order based on name.
    * The popularity at index decade does not matter.
    * For 1900-2000, if the year is before 1910 (1900+10), or after 1990 (2000-10), an exception will be thrown
    * Just for reference, the "decade" parameter is obtained from the client. The user will input a date which
    * will then be read in through the scanner.
 * 
 * Interesting Trend:
 * I searched the names of famous classical musicians, and as expected, they consistently find their peak in the 1900's, and then
 * taper off as time goes on.
 * For Beethoven, the name Ludwig peeks in 1900, and does not make the top 1000 after 1910:
 * 1900: 417
   1910: 743
   1920: 0
   1930: 0
   1940: 0
   1950: 0
   1960: 0 
   1970: 0
   1980: 0
   1990: 0
   2000: 0
   For Bach, the name Johann does nearly the same, peaking in 1900 and never making top 1000 again:
   1900: 841
   1910: 0
   1920: 0
   1930: 0
   1940: 0
   1950: 0
   1960: 0
   1970: 0
   1980: 0
   1990: 0
   2000: 0
   For Chopin, the name Frederic actually does quite well. Unlike the names above, Frederic survives until the 70's. Like the names above, 
   its best rankings come in the first half of the century.
   1900: 540
   1910: 480
   1920: 534
   1930: 493
   1940: 469
   1950: 490
   1960: 728
   1970: 851
   1980: 0
   1990: 0
   2000: 0
   For Debussy, the name Claude also survives in the same manner as Frederic, but once again finds itself decreasing in popularity as time goes on.
   1900: 73
   1910: 88
   1920: 106
   1930: 137
   1940: 165
   1950: 230
   1960: 318
   1970: 467
   1980: 624
   1990: 977
   2000: 0
   Like clockwork, the same also happens for Franz Liszt, with Franz being rather highly ranked in the 1900's, but fizzling out of the top 1000 only 10 years later.
   1900: 598
   1910: 0
   1920: 0
   1930: 0
   1940: 0
   1950: 0
   1960: 0
   1970: 0
   1980: 0
   1990: 0
   2000: 0
   This same pattern continued for many other classical musicians, including Gustav Mahler, Maurice Ravel, and Felix Mendelssohn.
   Composers like George Handel and Edward Elgar find their names in the top 100 until the 1990s, slowly decreasing in popularity over time. ex:
   George:
   1900: 4
   1910: 6
   1920: 6
   1930: 8
   1940: 15
   1950: 25 
   1960: 33
   1970: 49
   1980: 72
   1990: 105
   2000: 129
   Some musicians were able to break the pattern, such as Antonio Vivaldi, whos first name actually increased in popularity:
   Antonio
   1900: 118
   1910: 136
   1920: 133
   1930: 135
   1940: 157    
   1950: 170
   1960: 141
   1970: 81
   1980: 74
   1990: 82
   2000: 82
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NameSurfer
{

    public static final String NAME_FILE = "names.txt";

    // constants for menu choices
    public static final int SEARCH = 1;
    public static final int ONE_NAME = 2;
    public static final int APPEAR_ONCE = 3;
    public static final int APPEAR_ALWAYS = 4;
    public static final int ALWAYS_MORE_POPULAR = 5;
    public static final int ALWAYS_LESS_POPULAR = 6;
    public static final int DECREASING_THEN_INCREASING_AT_DECADE = 7;

    public static final int QUIT = 8;

    // CS314 students, explain your menu option 7 here:

    // CS314 students, Explain your interesting search / trend here:

    // CS314 students, add test code for NameRecord class here:

    // A few simple tests for the Names and NameRecord class.
    public static void simpleTest()
    {
        // raw data for Jake. Alter as necessary for your NameRecord
        Integer[] jakeRawData = new Integer[] { 262, 312, 323, 479, 484, 630, 751, 453, 225, 117, 97 };
        List<Integer> listJakeData = Arrays.asList(jakeRawData);
        ArrayList<Integer> jakeData = new ArrayList<>();
        jakeData.addAll(listJakeData);

        int baseDecade = 1900;
        NameRecord jakeRecord = new NameRecord("Jake", baseDecade, jakeData); // complete with your constructor
        String expected = "Jake\n1900: 262\n1910: 312\n1920: 323\n1930: 479\n1940: "
                + "484\n1950: 630\n1960: 751\n1970: 453\n1980: 225\n1990: 117\n2000: 97\n";
        String actual = jakeRecord.toString();
        System.out.println("expected string:\n" + expected);
        System.out.println("actual string:\n" + actual);
        if (expected.equals(actual))
        {
            System.out.println("passed Jake toString test.");
        } else
        {
            System.out.println("FAILED Jake toString test.");
        }

        // Some getName Tests

        Names names = new Names(getFileScannerForNames(NAME_FILE));
        String[] testNames = { "Isabelle", "isabelle", "sel", "X1178", "ZZ", "via", "kelly" };
        boolean[] expectNull = { false, false, true, true, true, true, false };
        for (int i = 0; i < testNames.length; i++)
        {
            performGetNameTest(names, testNames[i], expectNull[i]);
        }
    }

    private static void performGetNameTest(Names names, String name, boolean expectNull)
    {
        System.out.println("Performing test for this name: " + name);
        if (expectNull)
        {
            System.out.println("Expected return value is null");
        } else
        {
            System.out.println("Expected return value is not null");
        }
        NameRecord result = names.getName(name);
        if ((expectNull && result == null) || (!expectNull && result != null))
        {
            System.out.println("PASSED TEST.");
        } else
        {
            System.out.println("Failed test");
        }
    }

    // main method. Driver for the whole program
    public static void main(String[] args)
    {
        // delete the following line in the final version of your program.
        Integer[] testRawData = new Integer[] { 0, 312, 999, 479, 484, 630, 751, 453, 225, 117, 97 };
        List<Integer> testListData = Arrays.asList(testRawData);

        ArrayList<Integer> rankByDecadeTest1 = new ArrayList<>();

        rankByDecadeTest1.addAll(testListData);
        NameRecord test1 = new NameRecord("Joey", 0, rankByDecadeTest1);

        if (test1.getName().equals("Joey"))
        {
            System.out.println("getName() Test 1 passed");
        } else
        {
            System.out.println("getName() Test 1 failed");
        }
        test1 = new NameRecord("____", 0, rankByDecadeTest1);
        if (test1.getName().equals("____"))
        {
            System.out.println("getName() Test 2 passed");
        } else
        {
            System.out.println("getName() Test 2 failed");
        }
        if (test1.getBaseDecade() == 0)
        {
            System.out.println("getBaseDecade() Test 1 passed");
        } else
        {
            System.out.println("getBaseDecade() Test 1 failed");
        }
        test1 = new NameRecord("____", 84, rankByDecadeTest1);
        if (test1.getBaseDecade() == 84)
        {
            System.out.println("getBaseDecade() Test 2 passed");
        } else
        {
            System.out.println("getBaseDecade() Test 2 failed");
        }
        if (test1.getRankByDecade(4) == 484)
        {
            System.out.println("getRankByDecade(4) Test 1 passed");
        } else
        {
            System.out.println("getRankByDecade(4) Test 1 failed");
        }
        if (test1.getRankByDecade(0) == Integer.MAX_VALUE)
        {
            System.out.println("getRankByDecade(0) Test 2 passed");
        } else
        {
            System.out.println("getRankByDecade(0) Test 2 failed");
        }
        int expectedResult = 84 + 100;
        if (test1.getBestDecade() == expectedResult)
        {
            System.out.println("getBestDecade() Test 1 passed");
        } else
        {
            System.out.println("getBestDecade() Test 1 failed");
        }
        rankByDecadeTest1.set(0, 1);
        test1 = new NameRecord("joe schmo", 150333, rankByDecadeTest1);
        if (test1.getBestDecade() == 150333)
        {
            System.out.println("getBestDecade() Test 2 passed");
        } else
        {
            System.out.println("getBestDecade() Test 2 failed");
        }
        if (test1.getNumberOfRankedDecades() == 11)
        {
            System.out.println("getRankByDecade() Test 1 passed");
        } else
        {
            System.out.println("getRankByDecade() Test 1 failed");
        }
        rankByDecadeTest1.set(10, 0);
        test1 = new NameRecord("joe schmo", 0, rankByDecadeTest1);
        if (test1.getNumberOfRankedDecades() == 10)
        {
            System.out.println("getRankByDecade() Test 2 passed");
        } else
        {
            System.out.println("getRankByDecade() Test 2 failed");
        }
        if (!test1.isRankedAllDecades())
        {
            System.out.println("isRankedAllDecades() Test 1 passed");
        } else
        {
            System.out.println("isRankedAllDecades() Test 1 failed");
        }
        rankByDecadeTest1.set(10, 999);
        test1 = new NameRecord("joe schmo", 0, rankByDecadeTest1);
        if (test1.isRankedAllDecades())
        {
            System.out.println("isRankedAllDecades() Test 2 passed");
        } else
        {
            System.out.println("isRankedAllDecades() Test 2 failed");
        }
        testRawData = new Integer[] { 99, 9, 9, 9, 9, 9, 9, 9, 9 };
        testListData = Arrays.asList(testRawData);
        rankByDecadeTest1 = new ArrayList<>();
        rankByDecadeTest1.addAll(testListData);
        test1 = new NameRecord("joe schmo", 0, rankByDecadeTest1);
        if (!test1.isRankedOnlyOneDecade())
        {
            System.out.println("isRankedOnlyOneDecade() Test 1 passed");
        } else
        {
            System.out.println("isRankedOnlyOneDecade() Test 1 failed");
        }
        testRawData = new Integer[] { 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        testListData = Arrays.asList(testRawData);
        rankByDecadeTest1 = new ArrayList<>();
        rankByDecadeTest1.addAll(testListData);
        test1 = new NameRecord("joe schmo", 0, rankByDecadeTest1);
        if (test1.isRankedOnlyOneDecade())
        {
            System.out.println("isRankedOnlyOneDecade() Test 2 passed");
        } else
        {
            System.out.println("isRankedOnlyOneDecade() Test 2 failed");
        }
        testRawData = new Integer[] { 0, 9, 9, 9, 9, 9, 9, 9, 9 };
        testListData = Arrays.asList(testRawData);
        rankByDecadeTest1 = new ArrayList<>();
        rankByDecadeTest1.addAll(testListData);
        test1 = new NameRecord("joe schmo", 0, rankByDecadeTest1);
        if (!test1.isPopularityAlwaysIncreasing())
        {
            System.out.println("isPopularityAlwaysIncreasing() Test 1 passed");
        } else
        {
            System.out.println("isPopularityAlwaysIncreasing() Test 1 failed");
        }
        testRawData = new Integer[] { 99, 78, 22, 1 };
        testListData = Arrays.asList(testRawData);
        rankByDecadeTest1 = new ArrayList<>();
        rankByDecadeTest1.addAll(testListData);
        test1 = new NameRecord("joe schmo", 0, rankByDecadeTest1);
        if (test1.isPopularityAlwaysIncreasing())
        {
            System.out.println("isPopularityAlwaysIncreasing() Test 2 passed");
        } else
        {
            System.out.println("isPopularityAlwaysIncreasing() Test 2 failed");
        }
        testRawData = new Integer[] { 0, 9, 9, 9, 9, 9, 9, 9, 9 };
        testListData = Arrays.asList(testRawData);
        rankByDecadeTest1 = new ArrayList<>();
        rankByDecadeTest1.addAll(testListData);
        test1 = new NameRecord("joe schmo", 0, rankByDecadeTest1);
        if (!test1.isPopularityAlwaysDecreasing())
        {
            System.out.println("isPopularityAlwaysDecreasing() Test 1 passed");
        } else
        {
            System.out.println("isPopularityAlwaysDecreasing() Test 1 failed");
        }
        testRawData = new Integer[] { 1, 555, 925, 0, 0, 0 };
        testListData = Arrays.asList(testRawData);
        rankByDecadeTest1 = new ArrayList<>();
        rankByDecadeTest1.addAll(testListData);
        test1 = new NameRecord("joe schmo", 0, rankByDecadeTest1);
        if (!test1.isPopularityAlwaysDecreasing())
        {
            System.out.println("isPopularityAlwaysDecreasing() Test 2 passed");
        } else
        {
            System.out.println("isPopularityAlwaysDecreasing() Test 2 failed");
        }
        testRawData = new Integer[] { 1, 555, 925, 0 };
        testListData = Arrays.asList(testRawData);
        rankByDecadeTest1 = new ArrayList<>();
        rankByDecadeTest1.addAll(testListData);
        test1 = new NameRecord("joe schmo", 0, rankByDecadeTest1);
        if (test1.isPopularityAlwaysDecreasing())
        {
            System.out.println("isPopularityAlwaysDecreasing() Test 3 passed");
        } else
        {
            System.out.println("isPopularityAlwaysDecreasing() Test 3 failed");
        }
        String expected = "joe schmo\n0: 1\n10: 555\n20: 925\n30: 0\n";
        if (test1.toString().equals(expected))
        {
            System.out.println("toString() Test 1 passed");
        } else
        {
            System.out.println("toString() Test 1 failed");
        }
        testRawData = new Integer[] { 1, 1, 1, 1 };
        testListData = Arrays.asList(testRawData);
        rankByDecadeTest1 = new ArrayList<>();
        rankByDecadeTest1.addAll(testListData);
        test1 = new NameRecord("joe schmo", 0, rankByDecadeTest1);
        expected = "joe schmo\n0: 1\n10: 1\n20: 1\n30: 1\n";
        if (test1.toString().equals(expected))
        {
            System.out.println("toString() Test 2 passed");
        } else
        {
            System.out.println("toString() Test 2 failed");
        }
        NameRecord test2 = new NameRecord("aoe schmo", 0, rankByDecadeTest1);
        if (test1.compareTo(test2) > 0)
        {
            System.out.println("compareTo() Test 1 passed");
        } else
        {
            System.out.println("compareTo() Test 1 failed");
        }
        test2 = new NameRecord("schmo", 0, rankByDecadeTest1);
        if (test1.compareTo(test2) < 0)
        {
            System.out.println("compareTo() Test 2 passed");
        } else
        {
            System.out.println("compareTo() Test 2 failed");
        }
        test2 = new NameRecord("joe schmo", 0, rankByDecadeTest1);
        if (test1.compareTo(test2) == 0)
        {
            System.out.println("compareTo() Test 3 passed");
        } else
        {
            System.out.println("compareTo() Test 3 failed");
        }

        Scanner fileScanner = getFileScannerForNames(NAME_FILE);
        Names namesDatabase = new Names(fileScanner);
        fileScanner.close();
        runOptions(namesDatabase);
    }

    // pre: namesDatabase != null
    // ask user for options to perform on the given Names object.
    // Creates a Scanner connected to System.in.
    private static void runOptions(Names namesDatabase)
    {
        Scanner keyboard = new Scanner(System.in);
        int choice;
        do
        {
            showMenu();
            choice = getChoice(keyboard);
            if (choice == SEARCH)
            {
                search(namesDatabase, keyboard);
            } else if (choice == ONE_NAME)
            {
                oneName(namesDatabase, keyboard);
            } else if (choice == APPEAR_ONCE)
            {
                appearOnce(namesDatabase);
            } else if (choice == APPEAR_ALWAYS)
            {
                appearAlways(namesDatabase);
                // CS314 students, complete this section
            } else if (choice == ALWAYS_MORE_POPULAR)
            {
                alwaysMorePopular(namesDatabase);
            } else if (choice == ALWAYS_LESS_POPULAR)
            {
                alwaysLessPopular(namesDatabase);
            } else if (choice == DECREASING_THEN_INCREASING_AT_DECADE)
            {
                decreasingBeforeDecadeThenIncreasingAfter(namesDatabase, keyboard);
            } else
            {
                System.out.println("\n\nGoodbye.\n\n");
            }
        } while (choice != QUIT);
        keyboard.close();
    }

    // pre: fileName != null
    // create a Scanner and return connected to a File with the given name.
    private static Scanner getFileScannerForNames(String fileName)
    {
        Scanner sc = null;
        try
        {
            sc = new Scanner(new File(fileName));
        } catch (FileNotFoundException e)
        {
            System.out.println("Problem reading the data file. Returning null for Scanner"
                    + "object. Problems likely to occur." + e);
        }
        return sc;
    }

    // method that shows names that have appeared in ever decade
    // pre: n != null
    // post: print out names that have appeared in ever decade
    private static void appearAlways(Names n)
    {
        if (n == null)
        {
            throw new IllegalArgumentException("The parameter n cannot be null");
        }
        ArrayList<String> listToPrint = n.rankedEveryDecade();
        System.out.println(listToPrint.size() + " names appear in every decade. The names are: ");
        for (int i = 0; i < listToPrint.size(); i++)
        {
            System.out.println(listToPrint.get(i));
        }
    }

    // method that shows names that have appeared in only one decade
    // pre: n != null
    // post: print out names that have appeared in only one decade
    private static void appearOnce(Names n)
    {
        if (n == null)
        {
            throw new IllegalArgumentException("The parameter n cannot be null");
        }

        ArrayList<String> listToPrint = n.rankedOnlyOneDecade();
        System.out.println(listToPrint.size() + " names appear in exactly one decade. The names are: ");
        for (int i = 0; i < listToPrint.size(); i++)
        {
            System.out.println(listToPrint.get(i));
        }
    }

    // method that shows data for one name, or states that name has never been
    // ranked
    // pre: n != null, keyboard != null and is connected to System.in
    // post: print out the data for n or a message that n has never been in the
    // top 1000 for any decade
    private static void oneName(Names n, Scanner keyboard)
    {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (n == null || keyboard == null)
        {
            throw new IllegalArgumentException("The parameters cannot be null");
        }
        System.out.print("Enter a name: ");
        String name = keyboard.next();
        keyboard.nextLine(); //clear the buffer (ex: if they input "jake 1", the 1 will be registered for the next choice. bug not a feature in my opinion
        if (n.getName(name) != null)
        {
            System.out.println("\n" + n.getName(name).toString());
        } else
        {
            System.out.println("\n" + name + " does not appear in any decade.");
        }
    }

    // method that shows all names that contain a substring from the user
    // and the decade they were most popular in
    // pre: n != null, keyboard != null and is connected to System.in
    // post: show the correct data
    private static void search(Names n, Scanner keyboard)
    {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (n == null || keyboard == null)
        {
            throw new IllegalArgumentException("The parameters cannot be null");
        }
        System.out.print("Enter a partial name: ");
        String name = keyboard.next();
        keyboard.nextLine(); //clear the buffer (ex: if they input "jake 1", the 1 will be registered for the next choice. bug not a feature in my opinion
        ArrayList<NameRecord> matches = n.getMatches(name);
        System.out.println("\nThere are " + n.getMatches(name).size() + " matches for " + name + ".");
        if (n.getMatches(name).size() != 0)
        {
            System.out.print("\nThe matches with their highest ranking decade are: \n");
        }
        for (int i = 0; i < n.getMatches(name).size(); i++)
        {
            System.out.println(matches.get(i).getName() + " " + matches.get(i).getBestDecade());
        }

    }

    // method that shows all names that are more popular in every decade
    // pre: n != null
    // post: print all names that are always more popular 
    private static void alwaysMorePopular(Names n)
    {
        if (n == null)
        {
            throw new IllegalArgumentException("The parameter n cannot be null");
        }
        ArrayList<String> listToPrint = n.alwaysMorePopular();
        System.out.println(listToPrint.size() + " names are more popular in every decade.");
        for (int i = 0; i < listToPrint.size(); i++)
        {
            System.out.println(listToPrint.get(i));
        }
    }

    // method that shows all names that are less popular in every decade
    // pre: n != null
    // post: print all names that are always less popular 
    private static void alwaysLessPopular(Names n)
    {
        if (n == null)
        {
            throw new IllegalArgumentException("The parameter n cannot be null");
        }
        ArrayList<String> listToPrint = n.alwaysLessPopular();
        System.out.println(listToPrint.size() + " names are less popular in every decade.");
        for (int i = 0; i < listToPrint.size(); i++)
        {
            System.out.println(listToPrint.get(i));
        }
    }

    //method that shows all names that are decreasing in popularity before input int decade
    //and then decreasing in popularity after input int decade
    //for further explanation, see the method comments in Names.java
    //pre: n != null
    //post: print all names that follow the above criteria
    private static void decreasingBeforeDecadeThenIncreasingAfter(Names n, Scanner keyboard)
    {
        if (n == null)
        {
            throw new IllegalArgumentException("The parameter n cannot be null");
        }
        System.out.println("Enter a year between " + (n.returnBaseDecade() + 10) + " and "
                + (n.returnTerminalDecade() - 10) + ": ");
        int decade = keyboard.nextInt();
        ArrayList<String> listToPrint = n.decreasingBeforeDecadeThenIncreasingAfter(decade);
        System.out.println(listToPrint.size() + " names decrease in popularity before " + decade
                + " and increase in popularity after " + decade);
        for (int i = 0; i < listToPrint.size(); i++)
        {
            System.out.println(listToPrint.get(i));
        }
    }

    // get choice from the user
    // keyboard != null and is connected to System.in
    // return an int that is >= SEARCH and <= QUIT
    private static int getChoice(Scanner keyboard)
    {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (keyboard == null)
        {
            throw new IllegalArgumentException("The parameter keyboard cannot be null");
        }
        int choice = getInt(keyboard, "Enter choice: ");
        keyboard.nextLine();
        while (choice < SEARCH || choice > QUIT)
        {
            System.out.println("\n" + choice + " is not a valid choice");
            choice = getInt(keyboard, "Enter choice: ");
            keyboard.nextLine();
        }
        return choice;
    }

    // ensure an int is entered from the keyboard
    // pre: s != null and is connected to System.in
    private static int getInt(Scanner s, String prompt)
    {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (s == null)
        {
            throw new IllegalArgumentException("The parameter s cannot be null");
        }
        System.out.print(prompt);
        while (!s.hasNextInt())
        {
            s.next();
            System.out.println("That was not an int.");
            System.out.print(prompt);
        }
        return s.nextInt();
    }

    // show the user the menu
    private static void showMenu()
    {
        System.out.println("\nOptions:");
        System.out.println("Enter " + SEARCH + " to search for names.");
        System.out.println("Enter " + ONE_NAME + " to display data for one name.");
        System.out.println("Enter " + APPEAR_ONCE + " to display all names that appear in only one decade.");
        System.out.println("Enter " + APPEAR_ALWAYS + " to display all names that appear in all decades.");

        // CS314 students fill in other options
        System.out.println(
                "Enter " + ALWAYS_MORE_POPULAR + " to display all names that are more popular in every decade.");
        System.out.println(
                "Enter " + ALWAYS_LESS_POPULAR + " to display all names that are less popular in every decade.");
        System.out.println("Enter " + DECREASING_THEN_INCREASING_AT_DECADE
                + " to display the names which are decreasing in popularity until decade n, then increasing in popularity after n.");

        System.out.println("Enter " + QUIT + " to quit.\n");
    }

}