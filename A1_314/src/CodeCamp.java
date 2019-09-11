//  CodeCamp.java - CS314 Assignment 1

/*  Student information for assignment:
 * 
 * replace Joey Muffoletto with your name.
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

public class CodeCamp {
  
    /**
     * Determine the Hamming distance between two arrays of ints. 
     * Neither the parameter <tt>aList</tt> or
     * <tt>bList</tt> are altered as a result of this method.<br>
     * @param aList != null, aList.length == bList.length
     * @param bList != null, bList.length == aList.length
     * @return the Hamming Distance between the two arrays of ints.
     */    
    public static int hammingDistance(int[] aList, int[] bList){
        // check preconditions
        if (aList == null || bList == null || aList.length != bList.length) 
            throw new IllegalArgumentException("Violation of precondition: " +
            		"hammingDistance. neither parameter may equal null, arrays" +
            		" must be equal length.");       
        
        /*CS314 STUDENTS: INSERT YOUR CODE HERE*/
        
        /* Uses the length of aList to loop through and check each integer in both arrays. If not same integer, add 1 to hammingDist */
        
        int hammingDist = 0;
        
        for(int index = 0; index < aList.length; index++ )
        {
        	if(aList[index]!=bList[index])
        	{
        		hammingDist++;
        	}
        }
        
        return hammingDist; //must change
    }
    
    
    /**
     * Determine if one array of ints is a permutation of another.
     * Neither the parameter <tt>listA</tt> or 
     * the parameter <tt>listB</tt> are altered as a result of this method.<br>
     * @param listA != null
     * @param listB != null
     * @return <tt>true</tt> if listB is a permutation of listA, 
     * <tt>false</tt> otherwise
     * 
    */
    public static boolean isPermutation(int[] listA, int[] listB) {
        // check preconditions
        if (listA == null || listB == null) 
            throw new IllegalArgumentException("Violation of precondition: " +
            		"isPermutation. neither parameter may equal null.");
        /*CS314 STUDENTS: INSERT YOUR CODE HERE*/
        
        //if not the same length, not permutation
        if (listA.length != listB.length)
        	return false;
        
        int[] usedIndices = new int[listA.length];
        int matches = 0;
                
        for(int i = 0; i < listA.length; i++)
        {
        	//if listB[j] has been matched with listA[i], skip this number so no repeat indices are used
        	loopToContinue:
        	for(int j = 0; j < listB.length; j++)
        	{        		
        		if(listA[i] == listB[j])
        		{
        			for(int k = 0; k < matches; k++)
        			{
        				if(j == usedIndices[k])
        					continue loopToContinue;
        			}
        			usedIndices[matches] = j;
        			matches++;
        		}
        	}
        	if(matches == listA.length)
        	{
        		return true;
        	}
        }
        
        return matches == listA.length;
       
    }
    
     

    
    
    /**
     * Determine the index of the String that 
     * has the largest number of vowels. 
     * Vowels are defined as <tt>'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 
     * 'U', and 'u'</tt>.
     * The parameter <tt>list</tt> is not altered as a result of this method.
     * <p>pre: <tt>list != null</tt>, <tt>list.length > 0</tt>, there is an least 1 non null element in list
     * <p>post: return the index of the non-null element in list that has the 
     * largest number of characters that are vowels.
     * If there is a tie return the index closest to zero. 
     * The empty String, "", has zero vowels.
     * It is possible for the maximum number of vowels to be 0.<br>
     * @param list the array to check
     * @return the index of the non-null element in list that has 
     * the largest number of vowels.
     */
    public static int mostVowels(String[] list) {
        // check preconditions
        if (list == null || list.length == 0 || !atLeastOneNonNull(list))  
            throw new IllegalArgumentException("Violation of precondition: " +
            		"mostVowels. parameter may not equal null and must contain " +
            		"at least one none null value.");
       

        // CS314 STUDENTS: ADD YOUR CODE HERE
        //  You can use all methods from the String class and native arrays.
        
        int largestIndex, localVowelCount, globalMaxVowelCount;
        localVowelCount = globalMaxVowelCount = 0;
        largestIndex = Integer.MAX_VALUE;
        
        for (int index  = 0; index < list.length; index++)
        {
        	if(list[index] == null)
        		continue; 
        	/* Makes the string lowercase then uses checkVowel helper method to check each character */
        	String stringToParse = list[index].toLowerCase();
        	for(int charIndex = 0; charIndex < stringToParse.length(); charIndex++)
        	{
        		if(isVowel(stringToParse.charAt(charIndex)))
        			localVowelCount++;
        	}
        	/*Essentially greedy (?) algorithm. If a word has more vowels than the previous = new max */
        	if(localVowelCount > globalMaxVowelCount)
        	{
        		largestIndex = index;
        		globalMaxVowelCount = localVowelCount;
        	}
    		localVowelCount = 0;
        	/* If vowel count is 0, returns the closest non-null index to 0 */
        	if(globalMaxVowelCount == 0)
        	{
        		if(index < largestIndex)
        			largestIndex = index;
        	}
        }

        return largestIndex; 
    }
    
    /* Helper method to check if a character "charToCheck" is a vowel (returns a boolean) */
    //charToCheck cannot be null, but this is taken care of in method above
    private static boolean isVowel(char charToCheck)
    {	
    	
    	if (charToCheck == 'a' || charToCheck == 'i' || charToCheck == 'e'|| charToCheck == 'o'|| charToCheck == 'u')
    		return true;
    	return false;
    }
    

    
    /**
     * Perform an experiment simulating the birthday problem.
     * Pick random birthdays for the given number of people. 
     * Return the number of pairs of people that share the
     * same birthday.<br>
     * @param numPeople The number of people in the experiment.
     * This value must be > 0
     * @param numDaysInYear The number of days in the year for this experiement.
     * This value must be > 0
     * @return The number of pairs of people that share a birthday 
     * after running the simulation.
     */
    public static int sharedBirthdays(int numPeople, int numDaysInYear) {
        // check preconditions
        if (numPeople <= 0 || numDaysInYear <= 0)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"sharedBirthdays. both parameters must be greater than 0. " +
                    "numPeople: " + numPeople + 
                    ", numDaysInYear: " + numDaysInYear);
        
        //CS314 STUDENTS: ADD YOUR CODE HERE
        
        int days = numDaysInYear;
        int[] people = new int[numPeople];
        
        int numPairs = 0;
        for(int i = 0; i < people.length; i++)
        {
        	//generating randoms
        	people[i] = (int)(Math.random()*days);
        	for(int j = i-1; j >= 0; j--)
        	{
        		/* adds a person, then checks everyone already in array for pair */
        		if(people[i]==people[j])
        			numPairs++;
        	}
        }
        
        return numPairs;
    }
 
    
    
    /**
     * Determine if the chess board represented by board is a safe set up.
     * <p>pre: board != null, board.length > 0, board is a square matrix.
     * (In other words all rows in board have board.length columns.),
     * all elements of board == 'q' or '.'. 'q's represent queens, '.'s
     * represent open spaces.<br>
     * <p>post: return true if the configuration of board is safe,
     * that is no queen can attack any other queen on the board.
     * false otherwise.
     * the parameter <tt>board</tt> is not altered as a result of 
     * this method.<br>
     * @param board the chessboard
     * @return true if the configuration of board is safe,
     * that is no queen can attack any other queen on the board.
     * false otherwise.
    */
    public static boolean queensAreSafe(char[][] board) {
        char[] validChars = {'q', '.'};
        // check preconditions
        if (board == null || board.length == 0 || !isSquare(board) 
                || !onlyContains(board, validChars))
            throw new IllegalArgumentException("Violation of precondition: " +
            		"queensAreSafe. The board may not be null, must be square, " +
            		"and may only contain 'q's and '.'s");        
                
      //CS314 STUDENTS: ADD YOUR CODE HERE
        int[][] queenStorage = new int[board.length][2];
        //initializes queenStorage with MAX_VALUE.    
        for (int[] row : queenStorage)
        {
        	row[0] = Integer.MAX_VALUE;
        	row[1] = Integer.MAX_VALUE;
        }
        //If q, add to queenStorage. Also checks for queens on the same row
        for (int row = 0; row < board.length; row++)
        {
        	for (int col = 0; col < board[0].length; col++)
        	{
        		if (board[row][col] == 'q')
        		{
        			if(queenStorage[row][0] != Integer.MAX_VALUE)
        			{
        				//checking for horizontal matches
        				return false;
        			}
        			queenStorage[row][0] = row;
        			queenStorage[row][1] = col;
        		}
        	}
        }
        //queenStorage looks like -> queen row = queenStorage[row][0] and col = queenStorage[row][1]
        /* Starts from the queen with lowest row val and checks diags and verts for each queen below it */
        for (int row = 0; row < queenStorage.length; row++)
        {
        	if(queenStorage[row][0]== Integer.MAX_VALUE)
    			continue;//some queen storages never get changed because a row doesn't have a queen so skip
        	for (int below = row+1; below < queenStorage.length; below++)
        	{
        		if(queenStorage[below][0]== Integer.MAX_VALUE)
        			continue;// see comment above
        		//check diagonals and verticals
        		int rowDiff = Math.abs(queenStorage[row][0] - queenStorage[below][0]);
        		int colDiff = Math.abs(queenStorage[row][1] - queenStorage[below][1]);
        		if (rowDiff == colDiff || colDiff == 0)
        			return false;        		
        	}
        }
        return true; // if input survives above checks, return true
    }
    
    
    /**
     * Given a 2D array of ints return the value of the
     * most valuable contiguous sub rectangle in the 2D array.
     * The sub rectangle must be at least 1 by 1. 
     * <p>pre: <tt>mat != null, mat.length > 0, mat[0].length > 0,
     * mat</tt> is a rectangular matrix.
     * <p>post: return the value of the most valuable contigous sub rectangle
     * in <tt>city</tt>.<br>
     * @param city The 2D array of ints representing the value of
     * each block in a portion of a city.
     * @return return the value of the most valuable contigous sub rectangle
     * in <tt>city</tt>.
     */
    public static int getValueOfMostValuablePlot(int[][] city) {
        // check preconditions
        if (city == null || city.length == 0 || city[0].length == 0 
                || !isRectangular(city) )
            throw new IllegalArgumentException("Violation of precondition: " +
            		"getValueOfMostValuablePlot. The parameter may not be null," +
            		" must value at least one row and at least" +
                    " one column, and must be rectangular."); 
        

        //CS314 STUDENTS: ADD YOUR CODE HERE

        //n rows, n columns, n size, n positions
        
        int maxSum = Integer.MIN_VALUE;
        // run through each coordinate in city
        for(int i = 0; i < city.length; i++)
        {
        	for(int j = 0; j < city[0].length; j++)
        	{
        		//each coordinate is a starting point
        		maxSum = Math.max(expandRowThenColumn(i,j,city), maxSum);
        		maxSum = Math.max(expandColumnThenRow(i,j,city), maxSum);
        		
        	}
        }
        return maxSum; //must change
    }
    
    /* Takes the starting coordinate in city/grid, calculates the max value from that 
     * starting position by expanding downward 1 tile then expanding rightwards to rowlength-1.
     * Expands downwards then checks rightward until the row number is columnlength -1
     * Returns the highest value grid it found
     * pre: rowStart and colStart > 0, city != null. Already checked by method which calls it.
     */
    private static int expandRowThenColumn(int rowStart, int colStart, int[][] city)
    {
    	int methodMax = Integer.MIN_VALUE;
    	//used to store the value of the previous column up to the row number (row number is the index)
    	int[] sumOfPriorColumnAtRow = new int[city.length-rowStart];
    	
    	for(int col = colStart; col < city[0].length; col++)
    	{
    		int current = 0;
    		for(int row = rowStart; row < city.length; row++)
        	{    			
    			current+=city[row][col];
    			methodMax = Math.max(current+sumOfPriorColumnAtRow[row-rowStart], methodMax);
    			sumOfPriorColumnAtRow[row-rowStart]+= current;    		 	
        	}
    		
    	}
    	return methodMax;
    }
    /* Takes the starting coordinate in city/grid, calculates the max value from that 
     * starting position by expanding rightward 1 tile then expanding downwards to columnLength-1.
     * Expands rightward and checks below until the column number is rowLength -1
     * Returns the highest value grid it found
     * pre: rowStart and colStart > 0, city != null. Already checked by method which calls it.
     */
    private static int expandColumnThenRow(int rowStart, int colStart, int[][] city)
    {
    	int methodMax = Integer.MIN_VALUE;
    	//used to store the value of the previous row up to the column number (col number is index)
    	int[] sumOfPriorRowAtColumn = new int[city[0].length-colStart];
    	
    	for(int row = rowStart; row < city.length; row++)
    	{    		
        	int current = 0;
    		for(int col = colStart; col < city[0].length; col++)
    		{
    			current+=city[row][col];
    			methodMax = Math.max(current+sumOfPriorRowAtColumn[col-colStart], methodMax);
    			sumOfPriorRowAtColumn[col-colStart]+= current;    
    		}    		 
    	}
    	return methodMax;
    }

    	
    
    
    // !!!!! ***** !!!!! ***** !!!!! ****** !!!!! ****** !!!!! ****** !!!!!!
    // CS314 STUDENTS: Put your birthday problem experiement code here:
    
    /* runs the first birthday experiment and outputs the average number of pairs */
    public static int birthdayExperimentOne()
    {
    	//1,000,000 experiments, 365 days per year, 182 people per experiment
    	int sum = 0;
    	
    	for(int i = 0; i < 1000000; i++)
    		sum += sharedBirthdays(182,365);
    	
    	return sum/1000000;
    }
    /*runs the second birthday experiment and outputs a string formated based on the assignment webpage. 
     * Includes num of experiments with one or more shared birthday + percentage */
    public static String birthdayExperimentTwo()
    {
    	String output = "";
    	double sharedBirthdaysCount, percentBirthday;
    	sharedBirthdaysCount = percentBirthday = 0;
    	for(int i = 2; i <= 100; i++)
    	{
    		for(int j = 0; j < 50000; j++)
    		{
    			if(sharedBirthdays(i,365)>0)
    				sharedBirthdaysCount++;
    		}
    		
    		percentBirthday = sharedBirthdaysCount/50000 *100;
    		output+= "\n" + "Num people: " + i + ", number of experiments with one or more shared birthday: " + (int)sharedBirthdaysCount + ", percentage: " + percentBirthday;
    		sharedBirthdaysCount = percentBirthday = 0;
    	}
    	
    	return output;
    }
    
    // pre: list != null
    // post: return true if at least one element in list is null
    // otherwise return false.
    private static boolean atLeastOneNonNull(String[] list) {
        // check precondition
        if(list == null)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"atLeastOneNonNull. parameter may not equal null.");
        
        boolean foundNonNull = false;
        int i = 0;
        while( !foundNonNull && i < list.length ){
            foundNonNull = list[i] != null;
            i++;
        }
        return foundNonNull;
    }
    
    
    /* pre: mat != null
    post: return true if mat is a square matrix, false otherwise
     */
    private static boolean isSquare(char[][] mat) {
        if(mat == null)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"isSquare. paremeter may not be null.");

        final int numRows = mat.length;
        int row = 0;
        boolean isSquare = true;
        while( isSquare && row < numRows ) {
            isSquare = ( mat[row] != null) && (mat[row].length == numRows);
            row++;
        }
        return isSquare;
    }
    
    
    /* pre: mat != null, valid != null
    post: return true if all elements in mat are one of the characters in valid
     */
    private static boolean onlyContains(char[][] mat, char[] valid) {
        // check preconditions
        if(mat == null || valid == null)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"onlyContains. paremeters may not be null.");
        
        int row = 0;
        boolean correct = true;
        while( correct && row < mat.length) {
            int col = 0;
            while(correct && col < mat[row].length) {
                correct = contains(valid, mat[row][col]);
                col++;
            }
            row++;
        }
        return correct;
    }
    
    
    /*  pre: list != null
        post: return true if c is in list
     */
    private static boolean contains(char[] list, char tgtChar) {
        // check preconditions
        if(list == null)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"contains. paremeter may not be null.");

        boolean found = false;
        int index = 0;
        while( !found && index < list.length) {
            found = list[index] == tgtChar;
            index++;
        }
        return found;
    }
   
    
     // pre: mat != null, mat.length > 0
     // post: return true if mat is rectangular
    private static boolean isRectangular(int[][] mat) {
        // check preconditions
        if(mat == null || mat.length == 0)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"isRectangular. paremeter may not be null and must contain" +
            		" at least one row.");

        boolean correct = mat[0] != null;
        int row = 0;
        while(correct && row < mat.length) {
            correct = (mat[row] != null) && (mat[row].length == mat[0].length);
            row++;
        }
        return correct;
    }
    
    // make constructor pirvate so no instances of CodeCamp can be created
    private CodeCamp() {
        
    }
}