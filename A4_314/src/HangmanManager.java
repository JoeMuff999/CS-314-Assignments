/*  Student information for assignment:
 *
 *  On my honor, <NAME>, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name:
 *  email address:
 *  UTEID:
 *  Section 5 digit ID: 
 *  Grader name:
 *  Number of slip days used on this assignment:
 */

// add imports as necessary

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import java.util.Iterator;

/**
 * Manages the details of EvilHangman. This class keeps
 * tracks of the possible words from a dictionary during
 * rounds of hangman, based on guesses so far.
 *
 */
public class HangmanManager {

    // instance vars
    private boolean debugOn;
    private ArrayList<String> dictionary;
    private int guessMax;
    private int guessCount;
    private int difficulty; //
    private ArrayList<Character> guesses; //O(1) lookup, doesn't need to be ordered.
    private String currentPattern = "";
    private ArrayList<String> activeWords;
     
    
    /**
     * Create a new HangmanManager from the provided set of words and phrases.
     * pre: words != null, words.size() > 0
     * @param words A set with the words for this instance of Hangman.
     * @param debugOn true if we should print out debugging to System.out.
     */
    public HangmanManager(Set<String> words, boolean debugOn) {
        if( words == null || words.size() <= 0 )
            throw new IllegalArgumentException("HangmanManager(Set<String>, boolean) illegal parameters");
        
        dictionary = new ArrayList<>();
        for(String word : words)
        {
            dictionary.add(word);
        }
        Collections.sort(dictionary); //in case words is not alphabetical        
        this.debugOn = debugOn;
    }

    /**
     * Create a new HangmanManager from the provided set of words and phrases. 
     * Debugging is off.
     * pre: words != null, words.size() > 0
     * @param words A set with the words for this instance of Hangman.
     */
    public HangmanManager(Set<String> words) {
        this(words,false);
    }


    /**
     * Get the number of words in this HangmanManager of the given length.
     * pre: none
     * @param length The given length to check.
     * @return the number of words in the original Dictionary with the given length
     */
    public int numWords(int length) {
        int result = 0;
        for(String word : dictionary)
        {
            if(word.length() == length)
                result++;
        }
        return result;
    }


    /**
     * Get for a new round of Hangman. Think of a round as a complete game of Hangman.
     * @param wordLen the length of the word to pick this time. numWords(wordLen) > 0
     * @param numGuesses the number of wrong guesses before the player loses the round. numGuesses >= 1
     * @param diff The difficulty for this round.
     */
    public void prepForRound(int wordLen, int numGuesses, HangmanDifficulty diff) {
        if(wordLen <=0 || numGuesses < 1)
            throw new IllegalArgumentException("prepForRound(int,int,HangmanDifficulty) illegal parameters");
        
        guessMax = numGuesses;
        guessCount = 0;
        
        StringBuilder dashPattern = new StringBuilder();
        for(int i = 0; i < wordLen; i++)
        {
            dashPattern.append("-");
        }
        currentPattern = dashPattern.toString();
        
        guesses = new ArrayList<Character>();
        
        activeWords = new ArrayList<String>(dictionary);
        
        Iterator iterator = activeWords.iterator();
        //remove all words from activeList that are not the correct length. No need to re-sort which is nice.
        while(iterator.hasNext())
        {
            if(((String) iterator.next()).length() != wordLen)
            {
                iterator.remove();
            }
        }
        
        if(diff == HangmanDifficulty.EASY)
        {
            //difficulty = 
        }
        
    }


    /**
     * The number of words still possible (live) based on the guesses so far. Guesses will eliminate possible words.
     * @return the number of words that are still possibilities based on the original dictionary and the guesses so far.
     */
    public int numWordsCurrent() {
        return activeWords.size();
    }


    /**
     * Get the number of wrong guesses the user has left in this round (game) of Hangman.
     * @return the number of wrong guesses the user has left in this round (game) of Hangman.
     */
    public int getGuessesLeft() {
        return guessMax - guessCount;
    }


    /**
     * Return a String that contains the letters the user has guessed so far during this round.
     * The String is in alphabetical order. The String is in the form [let1, let2, let3, ... letN].
     * For example [a, c, e, s, t, z]
     * @return a String that contains the letters the user has guessed so far during this round.
     */
    public String getGuessesMade() {
        return guesses.toString();
    }


    /**
     * Check the status of a character.
     * @param guess The characater to check.
     * @return true if guess has been used or guessed this round of Hangman, false otherwise.
     */
    public boolean alreadyGuessed(char guess) {
        return guesses.contains(guess);
    }


    /**
     * Get the current pattern. The pattern contains '-''s for unrevealed (or guessed)
     * characters and the actual character for "correctly guessed" characters.
     * @return the current pattern.
     */
    public String getPattern() {          
        return currentPattern;
    }


    // pre: !alreadyGuessed(ch)
    // post: return a tree map with the resulting patterns and the number of
    // words in each of the new patterns.
    // the return value is for testing and debugging purposes
    /**
     * Update the game status (pattern, wrong guesses, word list), based on the give
     * guess.
     * @param guess pre: !alreadyGuessed(ch), the current guessed character
     * @return return a tree map with the resulting patterns and the number of
     * words in each of the new patterns.
     * The return value is for testing and debugging purposes.
     */
    public TreeMap<String, Integer> makeGuess(char guess) {        
        if(alreadyGuessed(guess))
            throw new IllegalStateException("makeGuess(char), param char already guessed!");
        guesses.add(guess);
        
        Map<String, ArrayList<String>> currFam = new TreeMap<>();
        for(String word : activeWords)
        {
            StringBuilder patForCurrBuilder = new StringBuilder();
            for(int i = 0; i < word.length(); i++)
            {
                if(word.charAt(i) == guess)
                {
                    patForCurrBuilder.append(guess);
                }
                else
                {
                    patForCurrBuilder.append(currentPattern.charAt(i));
                }
            }
            String patForCurr = patForCurrBuilder.toString();
            if(currFam.containsKey(patForCurr))
            {
                currFam.get(patForCurr).add(word);
            }
            else
            {
                ArrayList<String> tmp = new ArrayList<>();
                tmp.add(word);
                currFam.put(patForCurr, tmp);
            }
        }
        String tmp = currentPattern; //store so that I can increment their guess counter
        currentPattern = getKeyOfDifficulty(currFam, difficulty);
        if(tmp.equals(currentPattern))
        {
            guessCount++;
        }
        TreeMap<String,Integer> result = new TreeMap<>();
        for(String key : currFam.keySet())
        {
            result.put(key,currFam.get(key).size());
        }
        
        activeWords = currFam.get(currentPattern);
        
        return result;
    }
    
    private String getKeyOfDifficulty(Map<String, ArrayList<String>> currMap, int difficulty)
    {
    	List<patternObj> listToSort = new ArrayList<>();
    	for(String key : currMap.keySet())
    	{
    		patternObj tmp = new patternObj(key,currMap.get(key).size());
    		listToSort.add(tmp);
    	}
    	
    	Collections.sort(listToSort);
    	patternObj result = listToSort.get(difficulty);
    	return result.getPattern();
    }
    
   /* private String getKeyOfDifficulty(Map<String, ArrayList<String>> currMap ,int difficulty)
    {
        
        List<TreeMap<String, ArrayList<String>>> listToSort = new ArrayList<>();
        for(String key : currMap.keySet())
        {
            TreeMap<String, ArrayList<String>> tmp = new TreeMap<>();
            tmp.put(key,currMap.get(key));
            listToSort.add(tmp);
        }
        
        Collections.sort(listToSort, new patternSorter());
        TreeMap<String, ArrayList<String>> askedForMap = listToSort.get(difficulty);
        return askedForMap.firstKey();
    }*/
    /* patternObj is a class which I am using to implement the three comparisons to find the
     * "hardest" pattern available. It has a constructor that gives it a pattern and the how many matches there
     * are for that given pattern.
     * It has a method getPattern() that returns the local instance variable "pattern".
     * It implements Comparable, and as such has a compareTo method. Explained in method comments.
     */
    private class patternObj implements Comparable<patternObj>{

        private String pattern;
        private int matchAmt;

        public patternObj(String pat, int matchAmt)
        {
            pattern = pat;
            this.matchAmt = matchAmt;
        }
        
        public String getPattern()
        {
        	return pattern;
        }
        
        /* long if-else tree which does the following:
         * 1. does this.pattern have more matches than other.pattern?
         * 2. if matchAmt is same, does this.pattern reveal more characters than other.pattern?
         * 3. if they reveal the same characters, sort by lexicographical order.
         */

        public int compareTo(patternObj other)
        {
            Integer length1 = this.matchAmt; //declaring this again for READABILITY! 
            Integer length2 = other.matchAmt;


            
            int compareLength = length2.compareTo(length1); //sort descending

            if( compareLength == 0 )
            {                
                Integer revealed1 = 0; //how many characters are revealed by this pattern?
                Integer revealed2 = 0;
                
                String pat1 = this.pattern; //declaring this again for READABILITY! otherwise, it would look like "this.pattern.compareTo(other.pattern)" ew!
                String pat2 = other.pattern;
                
                for(int i = 0; i < pat2.length(); i++)
                {
                	//if not a dash, then a character must have been revealed
                    if(pat1.charAt(i) != '-')
                    {
                        revealed1++;
                    }
                    if(pat2.charAt(i) != '-')
                    {
                        revealed2++;
                    }
                }
                int compareRevealedChars = revealed1.compareTo(revealed2); //sort ascending
                if(compareRevealedChars == 0)
                {
                	//lexigraphical sorting. "---a" < "a---"
                    return pat1.compareTo(pat2); //sort ascending
                }
                else
                {
                    return compareRevealedChars;
                }
            }
            else
            {
                return compareLength;
            }
            

        }
    }
    
  /*  private class patternSorter implements Comparator<TreeMap<String,ArrayList<String>>>{
        
        @Override 
        public int compare(TreeMap<String,ArrayList<String>> mapOne, TreeMap<String,ArrayList<String>> mapTwo)
        {
            Integer length1 = mapOne.firstEntry().getValue().size();
            String key1 = mapOne.firstKey();
            
            Integer length2 = mapTwo.firstEntry().getValue().size();
            String key2 = mapTwo.firstKey();
            
            int compareLength = length2.compareTo(length1); //sort descending
            
            if(compareLength == 0 )
            {                
                Integer revealed1 = 0;
                Integer revealed2 = 0;
                for(int i = 0; i < key2.length(); i++)
                {
                    if(key1.charAt(i) != '-')
                    {
                        revealed1++;
                    }
                    if(key2.charAt(i) != '-')
                    {
                        revealed2++;
                    }
                }
                int compareRevealedChars = revealed1.compareTo(revealed2); //sort ascending
                if(compareRevealedChars == 0)
                {
                    return key1.compareTo(key2); //sort ascending
                }
                else
                {
                    return compareRevealedChars;
                }
            }
            else
            {
                return compareLength;
            }
            
        }
    }*/
    


    /**
     * Return the secret word this HangmanManager finally ended up picking for this round.
     * If there are multiple possible words left one is selected at random.
     * <br> pre: numWordsCurrent() > 0
     * @return return the secret word the manager picked.
     */
    public String getSecretWord() {
        if(numWordsCurrent() <= 0)
            throw new IllegalStateException("numWordsCurrent <= 0, which means you messed up joe. ");
        if(activeWords.size() == 0)
        {
            return activeWords.get(0);
        }
        else
        {
            Random random = new Random();
            int index = random.nextInt(activeWords.size());
            return activeWords.get(index);
        }
    }



}
