/*  Student information for assignment:
*
*  On my honor, Joey Muffoletto, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID: jrm7925
*  email address: jrmuff@utexas.edu
*  Number of slip days I am using: 2 :(
*/

import java.util.ArrayList;
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
            if (splitData.length - 1 == numDecades)
            {
                String name = splitData[0];
                ArrayList<Integer> rankByDecade = new ArrayList<>();
                for (int i = 1; i < splitData.length; i++)
                {
                    //checking for all zeros
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
        for (int i = 0; i < names.size(); i++)
        {
            //quite a long statement. takes the given string and checks all names objects for the string
            if (names.get(i).getName().toLowerCase().indexOf(partialName.toLowerCase()) != -1)
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

        for (int i = 0; i < names.size(); i++)
        {
            if (names.get(i).isRankedAllDecades())
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

        for (int i = 0; i < names.size(); i++)
        {
            if (names.get(i).isRankedOnlyOneDecade())
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

        for (int i = 0; i < names.size(); i++)
        {
            if (names.get(i).isPopularityAlwaysIncreasing())
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

        for (int i = 0; i < names.size(); i++)
        {
            if (names.get(i).isPopularityAlwaysDecreasing())
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

        for (int i = 0; i < names.size(); i++)
        {
            if (names.get(i).getName().toLowerCase().equals(name.toLowerCase()))
            {
                return names.get(i);
            }
        }

        return null;
    }

    /**
    * Returns an ArrayList of Strings of names that have been getting less
    * popular every decade until int decade, and then getting more popular every decade after int decade. 
    * Decade can be any year. Ex: 1956 will return the data based on the decade index of 1950.
    * If there is only one decade before or after the index, will still return true if the other
    * decades satisfy the requirement. Ex: 1900-2000, 1910 will still be considered
    * sufficient if 1910-2000 are always increasing in popularity regardless of 1900's and 1910's values.
    * The Strings must be in sorted order based on name.
    * The popularity at index decade does not matter.
    * @return A list of the names that have been getting less than more popular based on int decade.
    * The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list. 
    * pre: decade > baseDecade+10 && decade < endDecade()-10
    */
    public ArrayList<String> decreasingBeforeDecadeThenIncreasingAfter(int decade)
    {
        if (decade <= names.get(0).getBaseDecade() + 10 || decade >= names.get(0).getEndDecade() - 10)
            throw new IllegalArgumentException("The parameter decade must fall between the given years");
        ArrayList<String> listOfNames = new ArrayList<>();
        decade = decade - (decade % 10); //ex: 1957 becomes 1950 to satisfy my criteria above.
        for (int i = 0; i < names.size(); i++)
        {
            //creates a list of ranks before decade index and a list of ranks after decade index
            //could also just use one arraylist, but made two for the sake of clarity. 
            ArrayList<Integer> ranksBeforeDecade = new ArrayList<>();
            ArrayList<Integer> ranksAfterDecade = new ArrayList<>();
            int middleIndex = (decade - names.get(i).getBaseDecade()) / 10;
            for (int j = 0; j < middleIndex; j++)
            {
                ranksBeforeDecade.add(names.get(i).getRankByDecade(j));

            }
            for (int j = middleIndex; j < (names.get(i).getEndDecade() - decade) / 10 + middleIndex + 1; j++)
            {
                ranksAfterDecade.add(names.get(i).getRankByDecade(j));
            }

            NameRecord beforeDecade = new NameRecord("dummy1", 0, ranksBeforeDecade);
            NameRecord afterDecade = new NameRecord("dummy2", 0, ranksAfterDecade);
            //if beforeDecade is always decreasing in popularity before the index decade
            //and afterDecade is always increasing in popularity after the index decade
            //add the NameRecord to the listOfNames.
            if (beforeDecade.isPopularityAlwaysDecreasing() && afterDecade.isPopularityAlwaysIncreasing())
            {
                listOfNames.add(names.get(i).getName());
            }
        }

        Collections.sort(listOfNames);
        return listOfNames;

    }

    //returns the baseDecade so that NameSurfer may access this information (for printing)
    public int returnBaseDecade()
    {
        return names.get(0).getBaseDecade();
    }

    //returns the terminalDecade so that NameSurfer may access this information (for printing)
    public int returnTerminalDecade()
    {
        return names.get(0).getEndDecade();
    }

}