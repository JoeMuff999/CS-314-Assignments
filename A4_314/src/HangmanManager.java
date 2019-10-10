/*  Student information for assignment:
 *
 *  On my honor, Joey Muffoletto, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name: Joey Muffoletto
 *  email address: jrmuff@utexas.edu
 *  UTEID: jrm7925
 *  Section 5 digit ID: 50220
 *  Grader name: Andrew
 *  Number of slip days used on this assignment: 0
 */

// add imports as necessary

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Iterator;

/**
 * Manages the details of EvilHangman. This class keeps
 * tracks of the possible words from a dictionary during
 * rounds of hangman, based on guesses so far.
 *
 */
public class HangmanManager
{

    // instance vars
    private boolean debugOn;
    private ArrayList<String> dictionary;
    private int guessMax;
    private int guessCount;
    private String difficultyString;
    private int difficultyTracker;
    private TreeSet<Character> guesses; //Always sorted :)
    private String currentPattern = "";
    private ArrayList<String> activeWords;

    /**
     * Create a new HangmanManager from the provided set of words and phrases.
     * pre: words != null, words.size() > 0
     * @param words A set with the words for this instance of Hangman.
     * @param debugOn true if we should print out debugging to System.out.
     */
    public HangmanManager(Set<String> words, boolean debugOn)
    {
        if (words == null || words.size() <= 0)
            throw new IllegalArgumentException("HangmanManager(Set<String>, boolean) illegal parameters");

        dictionary = new ArrayList<>();
        for (String word : words)
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
    public HangmanManager(Set<String> words)
    {
        this(words, false);
    }

    /**
     * Get the number of words in this HangmanManager of the given length.
     * pre: none
     * @param length The given length to check.
     * @return the number of words in the original Dictionary with the given length
     */
    public int numWords(int length)
    {
        int result = 0;
        for (String word : dictionary)
        {
            if (word.length() == length)
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
    public void prepForRound(int wordLen, int numGuesses, HangmanDifficulty diff)
    {
        if (wordLen <= 0 || numGuesses < 1)
            throw new IllegalArgumentException("prepForRound(int,int,HangmanDifficulty) illegal parameters");

        guessMax = numGuesses;
        guessCount = 0;

        //making a new default "-..." pattern based on the length of the word they selected
        StringBuilder dashPattern = new StringBuilder();
        for (int i = 0; i < wordLen; i++)
        {
            dashPattern.append("-");
        }
        currentPattern = dashPattern.toString();

        guesses = new TreeSet<Character>();

        //reset dictionary
        activeWords = new ArrayList<String>(dictionary);

        Iterator<String> iterator = activeWords.iterator();
        //remove all words from activeList that are not the correct length. No need to re-sort which is nice.
        while (iterator.hasNext())
        {
            if (((String) iterator.next()).length() != wordLen)
            {
                iterator.remove();
            }
        }

        //resetting difficultyTracker to 0 for each new game
        difficultyTracker = 0;
        //0 = index of hardest, 1 = index of 2nd hardest

        if (diff == HangmanDifficulty.EASY)
        {
            //hard then 2nd hardest (0 -> 1)
            difficultyString = "01";
        } else if (diff == HangmanDifficulty.MEDIUM)
        {
            //hard hard hard then 2nd hardest (0->0->0->1)
            difficultyString = "0001";
        } else
        {
            //always hardest 0->0->.....->0
            difficultyString = "0";
        }

    }

    /**
     * The number of words still possible (live) based on the guesses so far. Guesses will eliminate possible words.
     * @return the number of words that are still possibilities based on the original dictionary and the guesses so far.
     */
    public int numWordsCurrent()
    {
        return activeWords.size();
    }

    /**
     * Get the number of wrong guesses the user has left in this round (game) of Hangman.
     * @return the number of wrong guesses the user has left in this round (game) of Hangman.
     */
    public int getGuessesLeft()
    {
        return guessMax - guessCount;
    }

    /**
     * Return a String that contains the letters the user has guessed so far during this round.
     * The String is in alphabetical order. The String is in the form [let1, let2, let3, ... letN].
     * For example [a, c, e, s, t, z]
     * @return a String that contains the letters the user has guessed so far during this round.
     */
    public String getGuessesMade()
    {
        return guesses.toString();
    }

    /**
     * Check the status of a character.
     * @param guess The characater to check.
     * @return true if guess has been used or guessed this round of Hangman, false otherwise.
     */
    public boolean alreadyGuessed(char guess)
    {
        return guesses.contains(guess);
    }

    /**
     * Get the current pattern. The pattern contains '-''s for unrevealed (or guessed)
     * characters and the actual character for "correctly guessed" characters.
     * @return the current pattern.
     */
    public String getPattern()
    {
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
    public TreeMap<String, Integer> makeGuess(char guess)
    {
        if (alreadyGuessed(guess))
            throw new IllegalStateException("makeGuess(char), param char already guessed!");
        //add their guess to the list of guesses
        guesses.add(guess);

        Map<String, ArrayList<String>> currFam = getMapOfPatterns(guess);

        String previousPattern = currentPattern; //store so that I can increment their guess counter
        currentPattern = getKeyOfDifficulty(currFam, getDifficulty());//gets the current pattern based on the difficulty

        if (previousPattern.equals(currentPattern)) //means they guessed "incorrectly" if the pattern has not changed.
        {
            guessCount++;
        }

        //adding the patterns and their match amount to the returned treemap
        TreeMap<String, Integer> result = new TreeMap<>();
        for (String key : currFam.keySet())
        {
            result.put(key, currFam.get(key).size());
        }

        activeWords = currFam.get(currentPattern);

        difficultyTracker++; //getting difficultyTracker for difficulty-sake 

        return result;
    }

    /*returns a map of the patterns and their matches based on the active dictionary
     * makes code a bit easier to read, especially in makeGuess
     * pre: guess != null ... this is checked in HangmanMain so its fine.
     */
    private Map<String, ArrayList<String>> getMapOfPatterns(char guess)
    {
        if (guess == '\u0000') //check if char is null which is impossible but hey its great practice right?
            throw new IllegalArgumentException("Somehow their guess was null.");

        Map<String, ArrayList<String>> currFam = new TreeMap<>();
        //for each word in the active dictionary
        //builds a pattern then adds the pattern the map, and adds the string to the respective arraylist
        for (String word : activeWords)
        {
            StringBuilder patForCurrBuilder = new StringBuilder();
            for (int i = 0; i < word.length(); i++)
            {
                if (word.charAt(i) == guess)
                {
                    patForCurrBuilder.append(guess);
                } else
                {
                    patForCurrBuilder.append(currentPattern.charAt(i));
                }
            }
            String patForCurr = patForCurrBuilder.toString();
            if (currFam.containsKey(patForCurr))
            {
                currFam.get(patForCurr).add(word);
            } else
            {
                //create a tmp arraylist then add it to the map
                ArrayList<String> tmp = new ArrayList<>();
                tmp.add(word);
                currFam.put(patForCurr, tmp);
            }
        }
        return currFam;
    }

    /*returns the index of the sorted list. uses the strings i created above
     * for difficulty to either return 0 or 1 (0 being the index of the hardest and 1 being the index of the 
     * second hardest). 
     */
    private int getDifficulty()
    {

        if (difficultyTracker > difficultyString.length() - 1)
        {
            //if we are at the end of the string, reset the difficultyTracker.
            difficultyTracker = 0;
        }

        return Integer.parseInt(String.valueOf(difficultyString.charAt(difficultyTracker)));
    }

    /*takes in the Map of current patterns and their respective ArrayList of matches
     * and then returns the pattern based on the asked for difficulty.
     * EX: if the difficulty was hard, difficulty = 0, listToSort.get(0) would return the hardest
     * since listToSort is assorted in descending order (hardest -> easiest).
     * pre: currMap != null && 0 <= difficulty
     */

    private String getKeyOfDifficulty(Map<String, ArrayList<String>> currMap, int difficulty)
    {
        if (currMap == null || difficulty < 0)
            throw new IllegalArgumentException(
                    "in getKeyOfDifficulty(Map<String, ArrayList<String>>,int) there is an illegal parameter");

        List<patternObj> listToSort = new ArrayList<>();
        //for every key/pattern in the given Map, create a pattern object and 
        // add it to listToSort 
        for (String key : currMap.keySet())
        {
            patternObj tmp = new patternObj(key, currMap.get(key).size());
            listToSort.add(tmp);
        }

        Collections.sort(listToSort);
        if (difficulty > listToSort.size() - 1) //if 2nd hardest isn't available, return hardest.
            difficulty = 0;
        patternObj result = listToSort.get(difficulty);
        return result.getPattern();
    }

    /* patternObj is a class which I am using to implement the three comparisons to find the
     * "hardest" pattern available. It has a constructor that gives it a pattern and the how many matches there
     * are for that given pattern.
     * It has a method getPattern() that returns the local instance variable "pattern".
     * It implements Comparable, and as such has a compareTo method. Explained in method comments.
     */
    private class patternObj implements Comparable<patternObj>
    {

        private String pattern;
        private int matchAmt;

        /* create a new patternObj with a String pattern
         * and an int matchAmt which represents how many 
         * matches the pattern has
         * pre: pat != null && matchAmt >= 0
         */
        public patternObj(String pat, int matchAmt)
        {
            if (pat == null || matchAmt < 0)
                throw new IllegalArgumentException("I can't believe you've done this Joey");

            pattern = pat;
            this.matchAmt = matchAmt;
        }

        /*returns the pattern for this patternObj
         * pre: none
         */
        public String getPattern()
        {
            return pattern;
        }

        /* long if-else tree which does the following:
         * 1. does this.pattern have more matches than other.pattern?
         * 2. if matchAmt is same, does this.pattern reveal more characters than other.pattern?
         * 3. if they reveal the same characters, sort by lexicographical order.
         * pre: other != null
         */

        public int compareTo(patternObj other)
        {
            if (other == null)
                throw new IllegalArgumentException("how did this happen... other is null :(");

            Integer length1 = this.matchAmt; //declaring this again for READABILITY! 
            Integer length2 = other.matchAmt;

            int compareLength = length2.compareTo(length1); //sort descending

            if (compareLength == 0)
            {
                Integer revealed1 = 0; //how many characters are revealed by this pattern?
                Integer revealed2 = 0;

                String pat1 = this.pattern; //declaring this again for READABILITY! otherwise, it would look like "this.pattern.compareTo(other.pattern)" ew!
                String pat2 = other.pattern;

                for (int i = 0; i < pat2.length(); i++)
                {
                    //if not a dash, then a character must have been revealed
                    if (pat1.charAt(i) != '-')
                    {
                        revealed1++;
                    }
                    if (pat2.charAt(i) != '-')
                    {
                        revealed2++;
                    }
                }
                int compareRevealedChars = revealed1.compareTo(revealed2); //sort ascending
                if (compareRevealedChars == 0)
                {
                    //lexigraphical sorting. "---a" < "a---"
                    return pat1.compareTo(pat2); //sort ascending
                } else
                {
                    return compareRevealedChars;
                }
            } else
            {
                return compareLength;
            }

        }
    }

    /**
     * Return the secret word this HangmanManager finally ended up picking for this round.
     * If there are multiple possible words left one is selected at random.
     * <br> pre: numWordsCurrent() > 0
     * @return return the secret word the manager picked.
     */
    public String getSecretWord()
    {
        if (numWordsCurrent() <= 0)
            throw new IllegalStateException("numWordsCurrent <= 0, which means you messed up joe. ");
        if (activeWords.size() == 0)
        {
            return activeWords.get(0);
        } else
        {
            //gets a random word from activeWords
            Random random = new Random();
            int index = random.nextInt(activeWords.size());
            return activeWords.get(index);
        }
    }

}
