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
 */
public class LetterInventory
{
    private static final int alphabetLength = 26;
    private static final int baseASCIIValue = 97;
    private int size;
    private int[] letterStorage;
    
    /*
     * pre: input != null
     * initializes and fills the letter inventory 
     * sets size
     */
    public LetterInventory(String input)
    {
        if(input == null)
            throw new IllegalArgumentException("input cannot be null");
        
        input = input.toLowerCase();
        letterStorage = new int[alphabetLength];
        for(int i = 0; i < input.length(); i++)
        {
            char currLetter = input.charAt(i);
            if('a' <= currLetter && currLetter <= 'z')
            {
                letterStorage[currLetter-baseASCIIValue]++;
                size++;
            }
        }
    }
    /*
     * pre: 'a' <= letterToCheck && letterToCheck <= 'z'
     * returns the frequency of the letter letterToCheck
     */
    public int get(char letterToCheck)
    {
        if('a' > letterToCheck && letterToCheck > 'z')
            throw new IllegalArgumentException("preconditions violated in method get");
        
        letterToCheck = Character.toLowerCase(letterToCheck);
        return letterStorage[letterToCheck-baseASCIIValue];
    }
    /*
     * pre: none
     * returns the size of the current letterInventory
     */
    public int size()
    {
        return size;
    }
    /*
     * pre: none
     * returns if size is 0
     */
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    public String toString()
    {
        StringBuilder toReturn = new StringBuilder();
        for(int i = 0; i < alphabetLength; i++)
        {            
            for(int j = 0; j < letterStorage[i]; j++)
            {
                int asciiVal = i + baseASCIIValue;
                char letter = (char) asciiVal;
                toReturn.append(letter);
            }
        }
        return toReturn.toString();
    }
    /*
     * pre: toAdd != null
     * post: return a new LetterInventory with combined storage values of toAdd and this and with size of toAdd + this
     */
    public LetterInventory add(LetterInventory toAdd)
    {
        if(toAdd == null)
            throw new IllegalArgumentException("add cannot add null");
        LetterInventory toReturn = new LetterInventory("");
        for(int i = 0; i < alphabetLength; i++)
        {
            toReturn.letterStorage[i] = letterStorage[i] + toAdd.letterStorage[i];
            
        }
        toReturn.size = size + toAdd.size;
        return toReturn;
    }
    
    public LetterInventory subtract(LetterInventory toSubtract)
    {
        if(toSubtract == null)
            throw new IllegalArgumentException("subtract cannot subtract null");
        
        LetterInventory toReturn = new LetterInventory("");
        for(int i = 0; i < alphabetLength; i++)
        {
            toReturn.letterStorage[i] = letterStorage[i] - toSubtract.letterStorage[i];
            if(toReturn.letterStorage[i] < 0)
                return null;            
        }
        toReturn.size = size - toSubtract.size;
        return toReturn;
    }
    
    public boolean equals(Object other)
    {
        if (!(other instanceof LetterInventory))
        {
            return false;
        }
        LetterInventory otherLI = (LetterInventory) other;
        
        if(size != otherLI.size)
        {
            return false;
        }
        for(int i = 0; i < alphabetLength; i++)
        {
            if(otherLI.letterStorage[i] != letterStorage[i])
                return false;
        }
        return true;
    }
    
}
