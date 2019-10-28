/*  Student information for assignment:
 *
 *  On <MY> honor, <Joey Muffoletto>, this programming assignment is <MY> own work
 *  and <I> have not provided this code to any other student.
 *
 *  Number of slip days used: 2
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: jrm7925
 *  email address: jrmuff@utexas.edu
 *  Grader name: Andrew
 *  Section number: 50220

 *
 *
 */

//imports

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Various recursive methods to be implemented.
 * Given shell file for CS314 assignment.
 */
public class Recursive
{
    /**
     * Problem 1: convert a base 10 int to binary recursively.
     *   <br>pre: n != Integer.MIN_VALUE<br>
     *   <br>post: Returns a String that represents N in binary.
     *   All chars in returned String are '1's or '0's. 
     *   Most significant digit is at position 0
     *   @param n the base 10 int to convert to base 2
     *   @return a String that is a binary representation of the parameter n
     */

    public static String getBinary(int n)
    {
        if (n == Integer.MIN_VALUE)
        {
            throw new IllegalArgumentException(
                    "Failed precondition: getBinary. " + "n cannot equal Integer.MIN_VALUE. n: " + n);
        }
        //if its 1, dont call the 0 base case because then there will be an extra 0 at the front
        if(n == 1)
        {
            return "1";
        }
        if (n == 0)
        {
            return "0";
        }

        //if n < 0, make n positive and then append a minus sign
        String binary = "";
        if (n < 0)
        {
            n *= -1;
            binary = getBinary(n/2) + "" + n%2;
            return "-" + binary ;
        } else
        {
            binary = getBinary(n/2) + "" + n%2 ;
            return "" + binary ;
        }     

    }

    /**
     * Problem 2: reverse a String recursively.<br>
     *   pre: stringToRev != null<br>
     *   post: returns a String that is the reverse of stringToRev
     *   @param stringToRev the String to reverse.
     *   @return a String with the characters in stringToRev in reverse order.
     */
    public static String revString(String stringToRev)
    {
        if (stringToRev == null)
        {
            throw new IllegalArgumentException("Failed precondition: revString. parameter may not be null.");
        }
        String result = "";
        if (stringToRev.length() == 0)
        {
            return "";
        }
        //add the last character then remove the last character of the string
        result += stringToRev.charAt(stringToRev.length() - 1);
        return result + revString(stringToRev.substring(0, stringToRev.length() - 1)); 
    }

    /**
     * Problem 3: Returns the number of elements in data
     * that are followed directly by value that is
     * double that element.
     * pre: data != null
     * post: return the number of elements in data that are followed immediately by double the value
     * @param data The array to search.
     * @return The number of elements in data that are followed immediately by a value that
     * is double the element.
     */
    public static int nextIsDouble(int[] data)
    {
        if (data == null)
        {
            throw new IllegalArgumentException("Failed precondition: revString. parameter may not be null.");
        }

        return nextIsDouble(data, 0, 0); // must change. Need to write a helper method
    }

    // CS314 students, add your nextIsDouble helper method here
    private static int nextIsDouble(int[] data, int currIndex, int prevNumber)
    {
        if (currIndex == data.length)
        {
            return 0;
        }
        //add to sum if double
        if (data[currIndex] == 2 * prevNumber)
        {
            return 1 + nextIsDouble(data, currIndex + 1, data[currIndex]);
        }
        //if not double, then just return 0 + ...
        return nextIsDouble(data, currIndex + 1, data[currIndex]);
    }

    /**  Problem 4: Find all combinations of mnemonics for the given number.<br>
     *   pre: number != null, number.length() > 0, all characters in number are digits<br>
     *   post: see tips section of assignment handout
     *   @param number The number to find mnemonics for
     *   @return An ArrayList<String> with all possible mnemonics for the given number
     */
    public static ArrayList<String> listMnemonics(String number)
    {
        if (number == null || number.length() == 0 || !allDigits(number))
        {
            throw new IllegalArgumentException("Failed precondition: listMnemonics");
        }

        ArrayList<String> result = new ArrayList<>();
        recursiveMnemonics(result, "", number);
        return result;
    }

    /*
     * Helper method for listMnemonics
     * mnemonics stores completed mnemonics
     * mneominicSoFar is a partial (possibly complete) mnemonic
     * digitsLeft are the digits that have not been used from the original number
     */
    private static void recursiveMnemonics(ArrayList<String> mnemonics, String mnemonicSoFar, String digitsLeft)
    {

        // CS314 students, complete this method

        if (digitsLeft.length() == 0)
        {
            mnemonics.add(mnemonicSoFar);
        } else
        {
            char[] storeLetters = digitLetters(digitsLeft.charAt(0)).toCharArray();
            for (int i = 0; i < storeLetters.length; i++)
            {
                //use original to reset it when i go backwards so I can go forwards through a different door ya feel.
                String original = mnemonicSoFar;
                mnemonicSoFar += storeLetters[i];
                recursiveMnemonics(mnemonics, mnemonicSoFar, digitsLeft.substring(1, digitsLeft.length()));
                mnemonicSoFar = original;
            }
        }

    }

    // used by method digitLetters
    private static final String[] letters = { "0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ" };

    /* helper method for recursiveMnemonics
     * pre: ch is a digit '0' through '9'
     * post: return the characters associated with this digit on a phone keypad
     */
    private static String digitLetters(char ch)
    {
        if (ch < '0' || ch > '9')
        {
            throw new IllegalArgumentException("parameter ch must be a digit, 0 to 9. Given value = " + ch);
        }
        int index = ch - '0';
        return letters[index];
    }

    /* helper method for listMnemonics
     * pre: s != null
     * post: return true if every character in s is a digit ('0' through '9')
     * */
    private static boolean allDigits(String s)
    {
        if (s == null)
        {
            throw new IllegalArgumentException("Failed precondition: allDigits. String s cannot be null.");
        }
        boolean allDigits = true;
        int i = 0;
        while (i < s.length() && allDigits)
        {
            allDigits = s.charAt(i) >= '0' && s.charAt(i) <= '9';
            i++;
        }
        return allDigits;
    }

    /**
     * Problem 5: Draw a Sierpinski Carpet.
     * @param size the size in pixels of the window
     * @param limit the smallest size of a square in the carpet.
     */
    public static void drawCarpet(int size, int limit)
    {
        DrawingPanel p = new DrawingPanel(size, size);
        Graphics g = p.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, size, size);
        g.setColor(Color.WHITE);
        drawSquares(g, size, limit, 0, 0);
    }

    /* Helper method for drawCarpet
     * Draw the individual squares of the carpet.
     * @param g The Graphics object to use to fill rectangles
     * @param size the size of the current square
     * @param limit the smallest allowable size of squares
     * @param x the x coordinate of the upper left corner of the current square
     * @param y the y coordinate of the upper left corner of the current square
     */
    private static void drawSquares(Graphics g, int size, int limit, double x, double y)
    {
        if (size > limit)
        {
            //do these 4 operations every time you recurse. fills square black then fills in the white center
            g.setColor(Color.black);
            g.fillRect((int) x, (int) y, size, size);
            g.setColor(Color.white);
            g.fillRect((int) x + (size / 3), (int) y + (size / 3), size / 3, size / 3);

            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    if (!(i == 1 && j == 1))
                    {
                        //continually divide up the space into 9 separate parts and then draw as above
                        drawSquares(g, size / 3, limit, x + i * size / 3, y + j * size / 3);
                    }
                }
            }

        }
    }

    /**
     * Problem 6: Determine if water at a given point
     * on a map can flow off the map.
     * <br>pre: map != null, map.length > 0,
     * map is a rectangular matrix, 0 <= row < map.length, 0 <= col < map[0].length
     * <br>post: return true if a drop of water starting at the location
     * specified by row, column can reach the edge of the map, false otherwise.
     * @param map The elevations of a section of a map.
     * @param row The starting row of a drop of water.
     * @param col The starting column of a drop of water.
     * @return return true if a drop of water starting at the location
     * specified by row, column can reach the edge of the map, false otherwise.
     */
    public static boolean canFlowOffMap(int[][] map, int row, int col)
    {
        if (map == null || map.length == 0 || !isRectangular(map) || !inbounds(row, col, map))
        {
            throw new IllegalArgumentException("Failed precondition: canFlowOffMap");
        }
        //base = if you fell of the map, congrats!
        if (row == map.length - 1 || col == map[0].length - 1 || row == 0 || col == 0)
        {
            return true;
        } else
        {
            //0 = left, 1 = right, 2 = up, 3 = down
            boolean[] validChoices = isValidDirection(row, col, map);
            for (int i = 0; i < validChoices.length; i++)
            {
                if (validChoices[i])
                {
                    //checks which direction, then initiates another recursive step.
                    //backtracking that doesn't require you to reset its position.
                    //if it can't find a valid new move, will just return to the valid old position automatically.
                    if (i == 0)
                    {
                        if (canFlowOffMap(map, row, col - 1))
                            return true;
                    } else if (i == 1)
                    {
                        if (canFlowOffMap(map, row, col + 1))
                            return true;
                    } else if (i == 2)
                    {
                        if (canFlowOffMap(map, row - 1, col))
                            return true;
                    } else
                    {
                        if (canFlowOffMap(map, row + 1, col))
                            return true;
                    }
                }
            }

        }
        return false;
    }

    /*
     * checks left, right, up, and down. validity is based on if the elevation would decrease and if the move is in bounds.
     * returns a boolean array of the valid moves.
     */
    private static boolean[] isValidDirection(int row, int col, int[][] map)
    {
        boolean[] local = new boolean[4];
        int currentElevation = map[row][col];
        local[0] = col > 0 && map[row][col - 1] < currentElevation;
        local[1] = col + 1 < map[0].length && map[row][col + 1] < currentElevation;
        local[2] = row > 0 && map[row - 1][col] < currentElevation;
        local[3] = row + 1 < map.length && map[row + 1][col] < currentElevation;
        return local;
    }

    /* helper method for canFlowOfMap - CS314 students you should not have to
     * call this method,
     * pre: mat != null,
     */
    private static boolean inbounds(int r, int c, int[][] mat)
    {
        if (mat == null)
        {
            throw new IllegalArgumentException("Failed precondition: inbounds. The 2d array mat may not be null.");
        }
        return r >= 0 && r < mat.length && mat[r] != null && c >= 0 && c < mat[r].length;
    }

    /*
     * helper method for canFlowOfMap - CS314 stdents you should not have to
     * call this method,
     * pre: mat != null, mat.length > 0
     * post: return true if mat is rectangular
     */
    private static boolean isRectangular(int[][] mat)
    {
        assert (mat != null) && (mat.length > 0) : "Violation of precondition: isRectangular";
        if (mat == null || mat.length == 0)
        {
            throw new IllegalArgumentException("Failed precondition: inbounds. "
                    + "The 2d array mat may not be null and must have at least 1 row.");
        }
        boolean correct = true;
        final int numCols = mat[0].length;
        int row = 0;
        while (correct && row < mat.length)
        {
            correct = (mat[row] != null) && (mat[row].length == numCols);
            row++;
        }
        return correct;
    }

    /**
     * Problem 7: Find the minimum difference possible between teams
     * based on ability scores. The number of teams may be greater than 2.
     * The goal is to minimize the difference between the team with the
     * maximum total ability and the team with the minimum total ability.
     * <br> pre: numTeams >= 2, abilities != null, abilities.length >= numTeams
     * <br> post: return the minimum possible difference between the team
     * with the maximum total ability and the team with the minimum total
     * ability.
     * @param numTeams the number of teams to form.
     * Every team must have at least one member
     * @param abilities the ability scores of the people to distribute
     * @return return the minimum possible difference between the team
     * with the maximum total ability and the team with the minimum total
     * ability. The return value will be greater than or equal to 0.
     */
    public static int minDifference(int numTeams, int[] abilities)
    {
        if (numTeams < 2 || abilities == null || abilities.length < numTeams)
            throw new IllegalArgumentException("preconditions violated");
        int[] teams = new int[numTeams]; //store total value of teams         
        int[] currAssignments = new int[numTeams]; //stores how many players are assigned to a team.
        return recursiveTeams(0, abilities, teams, Integer.MAX_VALUE, currAssignments);
    }

    /*
     * recursively finds every combination of teams
     * will go through every combination and find its max-min difference.
     * returns the smallest difference it found
     */
    private static int recursiveTeams(int indexToCheck, int[] remainingPeople, int[] teams, int minDiff,
            int[] currAssignments)
    {
        //base case: if there are no more people left to pick from then :: ...
        if (indexToCheck >= remainingPeople.length)
        {
            //check if all teams are assigned. if not, return max value
            if (checkEmpty(currAssignments))
                return Integer.MAX_VALUE;
            else
            {
                //if all teams are assigned, get the difference
                return getDiff(teams);
            }
        } else
        {
            for (int i = 0; i < teams.length; i++)
            {
                //store the previous state
                currAssignments[i]++;
                teams[i] += remainingPeople[indexToCheck];
                //compare the current mindiff with the recusive mindiff
                minDiff = Math.min(minDiff,
                        recursiveTeams(indexToCheck + 1, remainingPeople, teams, minDiff, currAssignments));
                //backtrack to the previous state
                teams[i] -= remainingPeople[indexToCheck];
                currAssignments[i]--;
            }

        }
        return minDiff;
    }

    /*
     * finds the difference between the largest and smallest team value
     */
    private static int getDiff(int[] teams)
    {
        int minTeam = Integer.MAX_VALUE;
        int maxTeam = Integer.MIN_VALUE;
        for (int i = 0; i < teams.length; i++)
        {
            minTeam = Math.min(minTeam, teams[i]);
            maxTeam = Math.max(maxTeam, teams[i]);
        }
        return maxTeam - minTeam;
    }

    /*
     * checks if any of them teams are empty. currAssignments tracks how many players have been assigned to a team.
     */
    private static boolean checkEmpty(int[] currAssignments)
    {
        for (int val : currAssignments)
        {
            if (val == 0)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Problem 8: Maze solver. Return 2 if it is possible to escape the maze after
     * collecting all the coins. Return 1 if it is possible to escape the maze 
     * but without collecting all the coins. Return 0 if it is not possible
     * to escape the maze. More details in the assignment handout.
     * <br>pre: board != null
     * <br>pre: board is a rectangular matrix
     * <br>pre: board only contains characters 'S', 'E', '$', 'G', 'Y', and '*'
     * <br>pre: there is a single 'S' character present
     * <br>post: rawMaze is not altered as a result of this method.
     * Return 2 if it is possible to escape the maze after
     * collecting all the coins. Return 1 if it is possible to escape the maze 
     * but without collecting all the coins. Return 0 if it is not possible
     * to escape the maze. More details in the assignment handout.
     * @param rawMaze represents the maze we want to escape. 
     * rawMaze is not altered as a result of this method.
     * @return per the post condition
     */
    //declaring magic characters
    private static final char coin = '$';
    private static final char greenSpace = 'G';
    private static final char yellowSpace = 'Y';
    private static final char wall = '*';
    private static final char startSpace = 'S';
    private static final char exit = 'E';

    public static int canEscapeMaze(char[][] rawMaze)
    {
        if (rawMaze == null || !isRectangularAndNoIllegalCharactersAndExistsStart(rawMaze))
            throw new IllegalArgumentException("violated pre");
        int coinMax = 0;
        int startRow = 0;
        int startCol = 0;
        boolean exitExist = false;
        //find the starting point and how many coins there are
        for (int i = 0; i < rawMaze.length; i++)
        {
            for (int j = 0; j < rawMaze[0].length; j++)
            {
                //find how many coins are on the board
                if (rawMaze[i][j] == coin)
                {
                    coinMax++;
                } else if (rawMaze[i][j] == startSpace)
                {
                    startRow = i;
                    startCol = j;
                } else if (rawMaze[i][j] == exit)
                {
                    exitExist = true;
                }
            }
        }
        //if there is no exit, save your computer the work and just return 0.
        if (!exitExist)
        {
            return 0;
        }
        //set the start to G so I don't have to handle the S case.
        rawMaze[startRow][startCol] = greenSpace;

        return recursiveMaze(rawMaze, startRow, startCol, 0, coinMax, 0);
    }

    /*
     * recursively paths through rawMaze until it finds a solution in which all coins
     * are obtained, or until every path has been traversed.
     * returns 2 if all coins collected and a valid exit
     * returns 1 if a valid exit but not all coins possible
     * returns 0 if no valid exit
     */
    //how can i cut down on this method's size without doing 
    //unorthodox helper methods or just redoing my whole structure?
    private static int recursiveMaze(char[][] rawMaze, int row, int col, int currCoins, int maxCoins, int possibility)
    {
        //uses possibility to track if it can leave the maze. if its ever 2, exit recursion immediately.
        //even if its 1, will continue looking for a 2.
        if (possibility == 2)
            return possibility;

        if (rawMaze[row][col] == exit && currCoins == maxCoins)
        {
            return 2;
        } else if (rawMaze[row][col] == exit)
        {
            return 1;
        } else
        {
            //0 = left, 1 = right, 2 = up, 3 = down
            boolean[] validMoves = isValidMove(rawMaze, row, col);
            for (int i = 0; i < validMoves.length; i++)
            {
                if (validMoves[i])
                {
                    //if there is valid move, store your current state (row, col, coin count, current maze) so you can backtrack

                    int origRow = row;
                    int origCol = col;
                    int origCoinCount = currCoins;
                    //store the previous maze 
                    char[][] origMaze = copyMaze(rawMaze);
                    //left? right? up? down? go through all valid possibilities with the above for loop.
                    if (i == 0)
                    {
                        col = col - 1;
                    } else if (i == 1)
                    {
                        col = col + 1;
                    } else if (i == 2)
                    {
                        row = row - 1;
                    } else
                    {
                        row = row + 1;
                    }
                    //coin space? use helper method
                    if (isMoney(rawMaze, row, col))
                        currCoins++;

                    //use alterSpace to change the space you just landed on
                    rawMaze[row][col] = alterSpace(rawMaze, row, col);
                    //recurse!
                    possibility = Math.max(possibility,
                            recursiveMaze(rawMaze, row, col, currCoins, maxCoins, possibility));

                    //backtrack if necessary
                    row = origRow;
                    col = origCol;
                    rawMaze = origMaze;
                    currCoins = origCoinCount;
                }

            }

        }
        return possibility;
    }

    /*
     * returns a deep copy of the param rawMaze
     */
    private static char[][] copyMaze(char[][] rawMaze)
    {
        char[][] local = new char[rawMaze.length][rawMaze[0].length];
        for (int j = 0; j < rawMaze.length; j++)
        {
            local[j] = rawMaze[j].clone();
        }
        return local;
    }

    /*
     * returns if space is a coin space
     */
    private static boolean isMoney(char[][] rawMaze, int row, int col)
    {
        return rawMaze[row][col] == coin;
    }

    /*
     * alters the space according to the assignment description. 
     * instead of altering it after we move off of a space, alter the space
     * as soon as you land on it (makes logic easier)
     */
    private static char alterSpace(char[][] rawMaze, int row, int col)
    {
        char currentSpace = rawMaze[row][col];
        if (currentSpace == greenSpace)
            return yellowSpace;
        else if (currentSpace == yellowSpace)
            return wall;
        else if (currentSpace == coin)
            return yellowSpace;
        return exit;

    }

    /*
     * determines which moves (left, right, up, down) are valid
     * as long as its not out of bounds, and the space is not a wall, its a valid move
     */
    private static boolean[] isValidMove(char[][] rawMaze, int row, int col)
    {
        boolean[] local = new boolean[4];
        local[0] = col > 0 && rawMaze[row][col - 1] != wall;
        local[1] = col + 1 < rawMaze[0].length && rawMaze[row][col + 1] != wall;
        local[2] = row > 0 && rawMaze[row - 1][col] != wall;
        local[3] = row + 1 < rawMaze.length && rawMaze[row + 1][col] != wall;
        return local;
    }

    //using to check preconditions for maze method
    private static boolean isRectangularAndNoIllegalCharactersAndExistsStart(char[][] mat)
    {
        assert (mat != null) && (mat.length > 0) : "Violation of precondition: isRectangular";
        if (mat == null || mat.length == 0)
        {
            throw new IllegalArgumentException("Failed precondition: inbounds. "
                    + "The 2d array mat may not be null and must have at least 1 row.");
        }
        boolean correct = true;
        final int numCols = mat[0].length;
        int row = 0;
        boolean isStart = false;

        char[] legalChars = new char[] { greenSpace, yellowSpace, wall, coin, exit, startSpace };
        while (correct && row < mat.length)
        {
            int col = 0;
            while (col < numCols && correct)
            {
                int i = 0;
                //iterate through legal chars to see if it matches one of them
                while (mat[row][col] != legalChars[i] && i < legalChars.length)
                {
                    i++;
                }
                //make sure a start space exists
                if (mat[row][col] == startSpace)
                    isStart = true;
                //if it iterated through all of them and one more, then it clearly was not equal to one of them
                if (i == legalChars.length)
                    correct = false;

                col++;
            }

            row++;
        }

        if (!isStart)
            correct = false;

        return correct;
    }
}