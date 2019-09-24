
/*
 * Student information for assignment: Replace <NAME> in the following with your
 * name. You are stating, on your honor you did not copy any other code on this
 * assignment and have not provided your code to anyone. 
 * 
 * On my honor, <NAME>, this programming assignment is my own work 
 * and I have not provided this code
 * to any other student. 
 * UTEID: 
 * email address: 
 * Number of slip days I am using:
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.*;

public class NameSurfer
{

    public static final String NAME_FILE = "names.txt";

    // constants for menu choices
    public static final int SEARCH = 1;
    public static final int ONE_NAME = 2;
    public static final int APPEAR_ONCE = 3;
    public static final int APPEAR_ALWAYS = 4;

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
        simpleTest();

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
            } else
            {
                System.out.println("\n\nGoodbye.");
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
        if(n.getName(name) != null)
        {
            System.out.println("\n"+ n.getName(name).toString());
        }
        else
        {
            System.out.println("\nThere are 0 matches for "+ name);
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
        System.out.println("\nThere are " + n.getMatches(name).size() + " matches for " + name);
        if(n.getMatches(name).size() != 0)
        {
            System.out.print("\nThe matches with their highest ranking decade are: \n");
        }
        for (int i = 0; i < n.getMatches(name).size(); i++)
        {
            System.out.println(matches.get(i).getName() + " " + matches.get(i).getBestDecade());
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

        System.out.println("Enter " + QUIT + " to quit.\n");
    }

}