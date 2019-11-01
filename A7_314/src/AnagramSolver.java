/* CS 314 STUDENTS: FILL IN THIS HEADER.
 *
 * Student information for assignment:
 *
 *  On my honor, Joey Muffoletto, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: jrm7925
 *  email address: jrmuff@utexas.edu
 *  Grader name: Andrew
 *  Number of slip days I am using: 0
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AnagramSolver
{

    Map<String, LetterInventory> wordInventory;

    /*
     * pre: list != null
     * @param list Contains the words to form anagrams from.
     */
    public AnagramSolver(Set<String> dictionary)
    {
        if (dictionary == null)
            throw new IllegalArgumentException("dictionary cannot equal null");

        wordInventory = new HashMap<>();
        for (String str : dictionary)
        {
            wordInventory.put(str, new LetterInventory(str));
        }
    }

    /*
     * pre: maxWords >= 0, s != null, s contains at least one 
     * English letter.
     * Return a list of anagrams that can be formed from s with
     * no more than maxWords, unless maxWords is 0 in which case
     * there is no limit on the number of words in the anagram
     */
    public List<List<String>> getAnagrams(String base, int maxWords)
    {
        // CS314 Students, add your code here.
        if (maxWords < 0 || base == null)
            throw new IllegalArgumentException("pre failed in getAnagrams(String, int)");
        LetterInventory invOfBase = new LetterInventory(base);
        List<String> currentAnagram = new ArrayList<>();
        if (maxWords == 0)
            maxWords = Integer.MAX_VALUE;
        //recursiveAnagrams(invOfBase, currentAnagram, wordInventory, maxWords);

        List<String> blacklist = new ArrayList<>();
        //creating a valid dictionary so that cases with no anagrams only need one recursive call
        Map<String, LetterInventory> validDictionary = getValidDictionary(invOfBase, wordInventory, blacklist);
        return recursiveAnagrams(invOfBase, currentAnagram, validDictionary, maxWords, blacklist);
    }

    /*
     * pre: none
     * post: recursively find all anagrams for the given base word.
     */
    private List<List<String>> recursiveAnagrams(LetterInventory currentInventory, List<String> currentAnagram,
            Map<String, LetterInventory> validDictionary, int maxWords, List<String> blacklist)
    {
        List<List<String>> result = new ArrayList<>();
        //base case
        //when there are no more validDictionary, or when the base is out of letters, is currentAnagram valid?\
        if (maxWords - currentAnagram.size() == 1)//faster termination:: when there is only one word left to add, if there are no letter Inventories big enough, its a failed case (dont check any)
        {
            if (lastWordFails(currentInventory.size(), validDictionary))
                return result;
        }
        if (currentInventory.isEmpty() && currentAnagram.size() <= maxWords) //if inventory is perfectly empty and maxWords satisfied, you win!
        {
            return cleanTempLists(currentAnagram); //use helper method to clean up method

        } else if (currentAnagram.size() < maxWords && currentInventory != null) //can still add more words? go right ahead ahahaha
        {
            //create dictionary of choices
            validDictionary = getValidDictionary(currentInventory, validDictionary, blacklist);
            for (String key : validDictionary.keySet()) //for each choice
            {
                currentAnagram.add(key); //add a choice
                LetterInventory invForKey = validDictionary.get(key);
                currentInventory = currentInventory.subtract(invForKey); //subtract dictionary
                List<List<String>> anagramList = recursiveAnagrams(currentInventory, currentAnagram, validDictionary,
                        maxWords, blacklist);
                if (anagramList.size() > 0)
                {
                    //add all of the recursive results
                    for (List<String> anagram : anagramList)
                        result.add(anagram);
                }
                currentInventory = currentInventory.add(invForKey); //reset the inventory
                currentAnagram.remove(currentAnagram.size() - 1); //remove the last added word from the anagram
                //adds the first word of the anagram because it will never be used again (all possibilities have been exhausted). Creates a pretty sizeable
                //decrease in recursive calls for the larger methods.
                if (currentAnagram.size() > 0 && !blacklist.contains(currentAnagram.get(0)))
                    blacklist.add(currentAnagram.get(0));
            }

        }
        //sort 
        Collections.sort(result, new AnagramComparator());
        //handles duplicates
        if (result.size() > 0)
            result = removeDuplicates(result);
        return result;
    }

    /*
     * pre: none
     * post: returns a List<List<String>> for a given List<String> s.t. the returnedList.get(0) = the list.
     */
    private List<List<String>> cleanTempLists(List<String> currentAnagram)
    {
        List<String> copyCurrAnagram = new ArrayList<>(currentAnagram); //create a copy
        Collections.sort(copyCurrAnagram); //sort so duplicates are easier to find (not a big time sink because its like O(4) at max)
        List<List<String>> toReturn = new ArrayList<>();
        toReturn.add(copyCurrAnagram);

        return toReturn;
    }
    /*
     * pre: none
     * post: based on sizeOfInv, if all other LetterInventories are smaller, return true so that the recursion may terminate.
     */
    private boolean lastWordFails(int sizeOfInv, Map<String, LetterInventory> validDictionary)
    {
        for (String key : validDictionary.keySet())
        {
            if (sizeOfInv <= validDictionary.get(key).size())
            {
                return false;
            }
        }

        return true;
    }

    /*
     * pre: none
     * post: if @param currentList has duplicates, remove them by comparing it to the previous List<String>
     * currentList is sorted, so this method can be O(n) by using a two pointer solution (essentially).
     */
    private List<List<String>> removeDuplicates(List<List<String>> currentList)
    {
        List<List<String>> listToReturn = new ArrayList<>();
        List<String> previous = new ArrayList<>();
        previous = currentList.get(0);
        listToReturn.add(previous);

        for (int i = 1; i < currentList.size(); i++)
        {
            List<String> curr = currentList.get(i);
            if (!curr.equals(previous))
                listToReturn.add(curr);

            previous = curr;

        }
        return listToReturn;
    }
    /*
     * pre: none
     * post: returns a valid dictionary based on the intersection of currentInventory and currentDictionary. removes any strings found in blacklist
     */
    private Map<String, LetterInventory> getValidDictionary(LetterInventory currentInventory,
            Map<String, LetterInventory> currentDictionary, List<String> blacklist)
    {
        //iterate over currentDictionary's key set
        Map<String, LetterInventory> newDict = new HashMap<>();

        for (String key : currentDictionary.keySet())
        {
            LetterInventory toSubtract = currentDictionary.get(key);
            if (currentInventory.subtract(toSubtract) != null && !blacklist.contains(key))
                newDict.put(key, toSubtract);
        }

        return newDict;
    }
    /*
     * A comparator object to compare anagrams
     * First, descending sort based on list length
     * then, sort within length : Descending sort based on strings
     */
    private static class AnagramComparator implements Comparator<List<String>>
    {
        @Override
        public int compare(List<String> o1, List<String> o2)
        {
            // TODO Auto-generated method stub
            int compareLength = o1.size() - o2.size();
            if (compareLength != 0)
            {
                return compareLength;
            } else
            {
                for (int i = 0; i < o1.size(); i++)
                {
                    int compareString = o1.get(i).compareTo(o2.get(i));
                    if (compareString != 0)
                        return compareString;
                }
            }
            return 0;
        }

    }

}
