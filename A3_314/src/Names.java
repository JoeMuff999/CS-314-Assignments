/*  Student information for assignment:
*
*  On my honor, <NAME>, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID:
*  email address:
*  Number of slip days I am using:
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
* A collection of NameRecords. 
* Stores NameRecord objects and provides methods to select
* NameRecords based on various criteria.
*/
public class Names 
{

    private ArrayList<NameRecord> names;

    /**
     * Construct a new Names object based on the data source the Scanner 
     * sc is connected to. Assume the first two lines in the 
     * data source are the base year and number of decades to use.
     * Any lines without the correct number of decades are discarded 
     * and are not part of the resulting Names object. 
     * Any names with ranks of all 0 are discarded and not
     * part of the resulting Names object.
     * @param sc Is connected to a data file with baby names 
     * and positioned at the start of the data source.
     */
    public Names(Scanner sc)
    {
        //don't know if this is necessary, but just to be safe.
        if (sc == null)
            throw new IllegalArgumentException(
                    "Illegal Argument Exception in constructor Names(Scanner)" + "Scanner sc must not be null");
        int baseDecade = Integer.parseInt(sc.nextLine());
        int numDecades = Integer.parseInt(sc.nextLine());
        names = new ArrayList<>();
        while (sc.hasNextLine())
        {           
            String currentLine = sc.nextLine();
            String[] splitData = currentLine.split("\\s+");
            boolean notAllZero = false; //checking if all ranks are zero
            if (splitData.length-1 == numDecades)
            {
                String name = splitData[0];
                ArrayList<Integer> rankByDecade = new ArrayList<>();
                for (int i = 1; i < splitData.length; i++)
                {
                    if (Integer.parseInt(splitData[i]) != 0 && !notAllZero)
                    {                        
                        notAllZero = true;
                    }
                    rankByDecade.add(Integer.parseInt(splitData[i]));
                }
                if (notAllZero)
                {
                    names.add(new NameRecord(name, baseDecade, rankByDecade));
                }
            }
        }
        
    }

    /**
    * Returns an ArrayList of NameRecord objects that contain a 
    * given substring, ignoring case.  The names must be in sorted order based 
    * on name.
    * @param partialName != null, partialName.length() > 0
    * @return an ArrayList of NameRecords whose names contains
    * partialName. If there are no NameRecords that meet this
    * criteria returns an empty list. 
    */
    public ArrayList<NameRecord> getMatches(String partialName)
    {
        if (partialName == null || partialName.length() <= 0)
            throw new IllegalArgumentException("Illegal Argument Exception in method"
                    + "getMatches(String). String partialName may not be null or of <= 0 length");
        
        ArrayList<NameRecord> listOfPartialNames = new ArrayList<>();
        for(int i = 0; i < names.size(); i++)
        {
            if(names.get(i).getName().toLowerCase().indexOf(partialName.toLowerCase()) != -1)
            {
                listOfPartialNames.add(names.get(i));
            }
        }
        Collections.sort(listOfPartialNames);
        return listOfPartialNames;
    }

    /**
    * Returns an ArrayList of Strings of names that have been ranked in the
    * top 1000 or better for every decade. The Strings  must be in sorted 
    * order based on name. 
    * @return A list of the names that have been ranked in the top
    * 1000 or better in every decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list.
    */

    public ArrayList<String> rankedEveryDecade()
    {
        ArrayList<String> listOfRankedEveryDecade = new ArrayList<>();
        
        for(int i = 0; i < names.size(); i++)
        {
            if(names.get(i).isRankedAllDecades())
            {
                listOfRankedEveryDecade.add(names.get(i).getName());
            }
        }
        
        Collections.sort(listOfRankedEveryDecade);
        return listOfRankedEveryDecade;
    }

    /**
    * Returns an ArrayList of Strings of names that have been ranked in the 
    * top 1000 or better in exactly one decade. The Strings must be in sorted 
    * order based on name. 
    * @return A list of the names that have been ranked in the top
    * 1000 or better in exactly one decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list.
    */
    public ArrayList<String> rankedOnlyOneDecade()
    {
        ArrayList<String> listOfRankedOnlyOneDecade = new ArrayList<>();
        
        for(int i = 0; i < names.size(); i++)
        {
            if(names.get(i).isRankedOnlyOneDecade())
            {
                listOfRankedOnlyOneDecade.add(names.get(i).getName());
            }
        }
        
        Collections.sort(listOfRankedOnlyOneDecade);
        
        return listOfRankedOnlyOneDecade;
    }

    /**
    * Returns an ArrayList of Strings of names that have been getting more
    * popular every decade. The Strings  must be in sorted order based on name.
    * @return A list of the names that have been getting more popular in
    * every decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list. 
    */
    public ArrayList<String> alwaysMorePopular()
    {
        ArrayList<String> listOfAlwaysMorePopular = new ArrayList<>();
        
        for(int i = 0; i < names.size(); i++)
        {
            if(names.get(i).isPopularityAlwaysIncreasing())
            {
                listOfAlwaysMorePopular.add(names.get(i).getName());
            }
        }
        
        Collections.sort(listOfAlwaysMorePopular);
        
        return listOfAlwaysMorePopular;
    }

    /**
    * Returns an ArrayList of Strings of names that have been getting less
    * popular every decade. The Strings  must be in sorted order based on name.
    * @return A list of the names that have been getting less popular in
    * every decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list. 
    */
    public ArrayList<String> alwaysLessPopular()
    {
        ArrayList<String> listOfAlwaysLessPopular = new ArrayList<>();
        
        for(int i = 0; i < names.size(); i++)
        {
            if(names.get(i).isPopularityAlwaysDecreasing())
            {
                listOfAlwaysLessPopular.add(names.get(i).getName());
            }
        }
        
        Collections.sort(listOfAlwaysLessPopular);
        
        return listOfAlwaysLessPopular;
    }

    /**
    * Return the NameRecord in this Names object that matches the given String.
    * <br>
    * <tt>pre: name != null</tt>
    * @param name The name to search for.
    * @return The name record with the given name or null if no NameRecord in this Names
    * object contains the given name.
    */
    public NameRecord getName(String name)
    {
        if (name == null)
            throw new IllegalArgumentException("The parameter name cannot be null");
        
        for(int i = 0; i < names.size(); i++)
        {
            if(names.get(i).getName().toLowerCase().equals(name.toLowerCase()))
            {
                return names.get(i);
            }
        }
        
        return null;
    }
    
    
}