/* CS 314 STUDENTS: FILL IN THIS HEADER.
 *
 * Student information for assignment:
 *
 *  On my honor, <NAME>, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID:
 *  email address:
 *  Grader name:
 *  Number of slip days I am using:
 *  
 *  only problem : duct tape. same problem as last assignment.
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AnagramSolver {
    
    Map<String, LetterInventory> wordInventory;
    public int recursiveCalls;

    /*
     * pre: list != null
     * @param list Contains the words to form anagrams from.
     */
    public AnagramSolver(Set<String> dictionary) {
        if(dictionary == null)
            throw new IllegalArgumentException("dictionary cannot equal null"); 
        
        wordInventory = new HashMap<>();
        for(String str : dictionary)
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
    public List<List<String>> getAnagrams(String base, int maxWords) {
        // CS314 Students, add your code here.
        if(maxWords < 0 || base == null)
            throw new IllegalArgumentException("pre failed in getAnagrams(String, int)");
        LetterInventory invOfBase = new LetterInventory(base);
        List<String> currentAnagram = new ArrayList<>();
        if(maxWords == 0)
            maxWords = Integer.MAX_VALUE;
        //recursiveAnagrams(invOfBase, currentAnagram, wordInventory, maxWords);

        List<String> blacklist = new ArrayList<>();
        recursiveCalls = 0;
        Map<String, LetterInventory> validDictionary = getValidDictionary(invOfBase, wordInventory,blacklist);
        return recursiveAnagrams(invOfBase, currentAnagram, validDictionary, maxWords, blacklist);
    }
    
    private List<List<String>> recursiveAnagrams(LetterInventory currentInventory, List<String> currentAnagram, Map<String, LetterInventory> validDictionary, int maxWords, List<String> blacklist)
    {
        List<List<String>> result = new ArrayList<>();
        //base case
        //when there are no more validDictionary, or when the base is out of letters, is currentAnagram valid?\
        recursiveCalls++;
        if(maxWords - currentAnagram.size() == 1)
        {
            if(lastWordFails(currentInventory.size(), validDictionary))
                return result;
        }            
        if(currentInventory == null || currentInventory.isEmpty())
        {            
            if(currentAnagram.size() <= maxWords)
            {
                List<String> tmp = new ArrayList<>(currentAnagram);
                Collections.sort(tmp);
                List<List<String>> temp = new ArrayList<>();
                temp.add(tmp);
                
                return temp;               
            }                
        }
        else if(currentAnagram.size() < maxWords)
        {
            validDictionary = getValidDictionary(currentInventory, validDictionary, blacklist);
            for(String key : validDictionary.keySet())
            {
                currentAnagram.add(key);
                LetterInventory originalInventory = new LetterInventory("");
                originalInventory = originalInventory.add(currentInventory);
                currentInventory = currentInventory.subtract(validDictionary.get(key));
                List<List<String>> anagramList = recursiveAnagrams(currentInventory, currentAnagram, validDictionary,maxWords, blacklist);
                if(anagramList.size() > 0)
                {
                    for(List<String> anagram : anagramList)
                        result.add(anagram);
                }
                currentInventory = originalInventory;
                currentAnagram.remove(currentAnagram.size()-1);
                if(currentAnagram.size() > 0 && !blacklist.contains(currentAnagram.get(0)))
                    blacklist.add(currentAnagram.get(0));
                
            }
            
        }

        Collections.sort(result, new AnagramComparator());
        if(result.size() > 0)
            result = removeDuplicates(result);
        return result;       
    }
    
    private boolean lastWordFails(int sizeOfInv, Map<String, LetterInventory> validDictionary)
    {
        for(String key : validDictionary.keySet())
        {
            if(sizeOfInv <= validDictionary.get(key).size())
            {
                return false;
            }
        }

        return true;
    }
    
    private List<List<String>> removeDuplicates(List<List<String>> currentList)
    {
        List<List<String>> listToReturn = new ArrayList<>();
        List<String> previous = new ArrayList<>();
        previous = currentList.get(0);
        listToReturn.add(previous);
        
        for(int i = 1; i < currentList.size(); i++)
        {
            List<String> curr = currentList.get(i);
            if(!curr.equals(previous))
                listToReturn.add(curr);
            
            
            previous = curr;
                
        }
        return listToReturn;
    }
    
    private Map<String, LetterInventory> getValidDictionary(LetterInventory currentInventory, Map<String, LetterInventory> currentDictionary, List<String> blacklist)
    {
        //iterate over currentDictionary's key set
        Map<String, LetterInventory> newDict = new HashMap<>();
        
        for(String key : currentDictionary.keySet())
        {
            LetterInventory toSubtract = currentDictionary.get(key);
            if(currentInventory.subtract(toSubtract) != null && !blacklist.contains(key)) 
                newDict.put(key, toSubtract);
        }
        
        return newDict;
    }

    
    private static class AnagramComparator implements Comparator<List<String>> {
        @Override
        public int compare(List<String> o1, List<String> o2)
        {
            // TODO Auto-generated method stub
            int compareLength = o1.size() - o2.size();
            if(compareLength != 0)
            {
                return compareLength;
            }
            else
            {
                for(int i = 0; i < o1.size(); i++)
                {
                    int compareString = o1.get(i).compareTo(o2.get(i));
                    if(compareString != 0)
                        return compareString;                          
                }
            }
            return 0;
        }
        
    }

}
