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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AnagramSolver {
    
    Map<String, LetterInventory> wordInventory;

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
        return recursiveAnagrams(invOfBase, currentAnagram, wordInventory, maxWords);
    }
    
    private List<List<String>> recursiveAnagrams(LetterInventory currentInventory, List<String> currentAnagram, Map<String, LetterInventory> validDictionary, int maxWords)
    {
        List<List<String>> ductTape = new ArrayList<>();
        //base case
        //when there are no more validDictionary, or when the base is out of letters, is currentAnagram valid?
        //List<List<String>> toReturn = new ArrayList<>();
        if(currentInventory == null || currentInventory.isEmpty())
        {            
            if(validResult(currentAnagram, maxWords))
            {              
               //System.out.println("current anagram " + currentAnagram.toString());
                List<String> tmp = new ArrayList<>(currentAnagram);
                ductTape.add(tmp);
                //System.out.println(ductTape.toString());
                return ductTape;               
            }
            //return null;
                
        }
        else
        {
            validDictionary = getValidDictionary(currentInventory, validDictionary);
            //System.out.println("current dictionary " + validDictionary.toString());
            //System.out.println("current inventory " + currentInventory.toString());
            for(String key : validDictionary.keySet())
            {
                currentAnagram.add(key);
                LetterInventory originalInventory = new LetterInventory("");
                originalInventory = originalInventory.add(currentInventory);
                currentInventory = currentInventory.subtract(validDictionary.get(key));
                if(recursiveAnagrams(currentInventory, currentAnagram, validDictionary,maxWords).size() != 0)
                    ductTape.add(recursiveAnagrams(currentInventory, currentAnagram, validDictionary,maxWords).get(0));
                //System.out.println("reached " + originalInventory);
                currentInventory = originalInventory;
                currentAnagram.remove(currentAnagram.size()-1);
                
                
            }
        }
        return ductTape;
        
        
    }
    
    private Map<String, LetterInventory> getValidDictionary(LetterInventory currentInventory, Map<String, LetterInventory> currentDictionary)
    {
        //iterate over currentDictionary's key set
        Map<String, LetterInventory> newDict = new HashMap<>();
        
        for(String key : currentDictionary.keySet())
        {
            LetterInventory toSubtract = currentDictionary.get(key);
            if(currentInventory.subtract(toSubtract) != null)
                newDict.put(key, toSubtract);
        }
        
        return newDict;
    }
    
    private boolean validResult(List<String> currentAnagram, int maxWords)
    {
        if(currentAnagram.size() > maxWords)
            return false;
        for(String str : currentAnagram)
        {
            if(!wordInventory.containsKey(str))
            {
                return false;
            }
        }
        return true;
    }

}
