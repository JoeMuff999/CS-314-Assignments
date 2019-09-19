import java.util.Arrays;
import java.util.Random;

/*  Student information for assignment:
 *
 *  UTEID: jrm7925
 *  email address: jrmuff@utexas.edu
 *  Grader name: Andrew
 *  Number of slip days I am using: 0
 */

/* CS314 Students. Put your experiment results and
 * answers to questions here.
 * 
 * Experiment 1 Results ::
 * 
 * It took about 1.0685924 seconds to add two math matrices of size 1000x1000, 1000 times
 * It took about 5.1168321 seconds to add two math matrices of size 2000x2000, 1000 times
 * It took about 18.8154158 seconds to add two math matrices of size 4000x4000, 1000 times
 * 
 * Experiment 2 Results ::
 * 
 * It took about 1.1685081 seconds to multiply two math matrices of size 235x235, 100 times
 * It took about 17.2767142 seconds to multiply two math matrices of size 470x470, 100 times
 * It took about 219.5911192 seconds to multiply two math matrices of size 940x940, 100 times
 * 
 * Questions ::
 * 
 * 1. I would expect the add method to take 64 seconds since it seems to be O(N^2) (if ran 1000 times like in experiment 1).
 * 
 * 2. The big O of the add operation is O(N^2) since I run through each element in the matrix being added to. 
 *    Since there are NxN elements, it will be N^2. Time timing data supports this hypothesis since, as expected, 
 *      the time it takes to run the experiment just-more-than quadruples as N doubles. 
 *      
 * 3. I expect it would take about 3723 seconds.
 * 
 * 4. The big O of my multiply operation should be O(N^3). This is because, for each element of the matrix (NxN), it another N times. Therefore, it should be 
 *  rowCount*colCount*depth or NxNxN (since all are N in this situation). The timing data does not support this. This is because as N doubles, the runtime increases
 * by a factor of around 14, whereas in an O(N^3) algorithm the runtime should increase by a factor of 8.
 * 
 *5. In my case, a matrix of size 32643x32643 is the largest size which doesn't give a heap memory error (32644x32644 causes the error). 
    32643x32643 = 1,065,565,449 elements in the matrix. Multiplied by 4 bytes per int, this gives a total of supposedly 
    4,262,261,796 bytes allocated to my program which is around 4.25 GB's, a surprising amount of memory. 
 * 
 */

/**
 * A class to run tests on the MathMatrix class
 */
public class MathMatrixTester
{

    /**
     * main method that runs simple test on the MathMatrix class
     *
     * @param args not used
     */
    public static void main(String[] args)
    {

        // CS314 Students. When ready delete the above tests
        // and add your 22 tests here.

        int[][] testerArray1 = new int[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        MathMatrix testerMatrix1 = new MathMatrix(testerArray1);

        //getNumRows() testing ::
        if (testerMatrix1.getNumRows() == 2)
        {
            System.out.println("Congrats! Test case 1 passed for method getNumRows(). Wow!");
        } else
        {
            System.out.println("Wow! Test case 1 failed for method getNumRows().");
        }
        testerArray1 = new int[][] { { 0, 0 } };
        testerMatrix1 = new MathMatrix(testerArray1);
        if (testerMatrix1.getNumRows() == 1)
        {
            System.out.println("Congrats! Test case 2 passed for method getNumRows(). Wow!");
        } else
        {
            System.out.println("Wow! Test case 2 failed for method getNumRows().");
        }

        //getNumColumns() testing ::
        testerArray1 = new int[][] { { 0, 0 } };
        testerMatrix1 = new MathMatrix(testerArray1);
        if (testerMatrix1.getNumColumns() == 2)
        {
            System.out.println("Congrats! Test case 1 passed for method getNumColumns(). Wow!");
        } else
        {
            System.out.println("Wow! Test case 1 failed for method getNumColumns().");
        }
        testerArray1 = new int[][] { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };
        testerMatrix1 = new MathMatrix(testerArray1);
        if (testerMatrix1.getNumColumns() == 18)
        {
            System.out.println("Congrats! Test case 2 passed for method getNumColumns(). Wow!");
        } else
        {
            System.out.println("Wow! Test case 2 failed for method getNumColumns().");
        }
        //getVal() testing ::
        testerMatrix1 = new MathMatrix(100, 100, 99);
        if (testerMatrix1.getVal(99, 99) == 99)
        {
            System.out.println("Congrats! Test case 1 passed for method getVal(). Wow!");
        } else
        {
            System.out.println("Wow! Test case 1 failed for method getVal().");
        }
        testerArray1 = new int[][] { { 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1 } };
        testerMatrix1 = new MathMatrix(testerArray1);
        if (testerMatrix1.getVal(0, 0) == 0)
        {
            System.out.println("Congrats! Test case 2 passed for method getVal(). Wow!");
        } else
        {
            System.out.println("Wow! Test case 2 failed for method getVal().");
        }
        //add() testing ::
        testerArray1 = new int[][] { { 0 }, { Integer.MIN_VALUE } };
        testerMatrix1 = new MathMatrix(testerArray1);
        int[][] testerArray2 = new int[][] { { Integer.MAX_VALUE }, { Integer.MAX_VALUE } };
        MathMatrix testerMatrix2 = new MathMatrix(testerArray2);
        testerMatrix1 = testerMatrix1.add(testerMatrix2);
        int[][] solutionArray = new int[][] { { Integer.MAX_VALUE }, { Integer.MAX_VALUE + Integer.MIN_VALUE } };
        MathMatrix solutionMatrix = new MathMatrix(solutionArray);
        if (testerMatrix1.equals(solutionMatrix))
        {
            System.out.println("Congrats! Test case 1 passed for method add(). Wow!");
        } else
        {
            System.out.println("Wow! Test case 1 failed for method add().");
        }
        testerMatrix1 = new MathMatrix(5, 5, 2);
        testerMatrix2 = new MathMatrix(5, 5, 3);
        solutionMatrix = new MathMatrix(5, 5, 5);
        testerMatrix1 = testerMatrix1.add(testerMatrix2);
        if (testerMatrix1.equals(solutionMatrix))
        {
            System.out.println("Congrats! Test case 2 passed for method add(). Wow!");
        } else
        {
            System.out.println("Wow! Test case 2 failed for method add().");
        }
        //subtract() testing ::
        testerArray1 = new int[][] { { 0 }, { Integer.MIN_VALUE } };
        testerMatrix1 = new MathMatrix(testerArray1);
        testerArray2 = new int[][] { { Integer.MAX_VALUE }, { Integer.MAX_VALUE } };
        testerMatrix2 = new MathMatrix(testerArray2);
        testerMatrix2 = testerMatrix2.subtract(testerMatrix1);
        solutionArray = new int[][] { { Integer.MAX_VALUE }, { Integer.MAX_VALUE - Integer.MIN_VALUE } };
        solutionMatrix = new MathMatrix(solutionArray);
        if (testerMatrix2.equals(solutionMatrix))
        {
            System.out.println("Congrats! Test case 1 passed for method subtract(). Wow!");
        } else
        {
            System.out.println("Wow! Test case 1 failed for method subtract().");
        }
        testerMatrix1 = new MathMatrix(5, 5, 5);
        testerMatrix2 = new MathMatrix(5, 5, 3);
        solutionMatrix = new MathMatrix(5, 5, 2);
        testerMatrix1 = testerMatrix1.subtract(testerMatrix2);
        if (testerMatrix1.equals(solutionMatrix))
        {
            System.out.println("Congrats! Test case 2 passed for method subtract(). Wow!");
        } else
        {
            System.out.println("Wow! Test case 2 failed for method subtract().");
        }
        //multiply() testing ::
        testerArray1 = new int[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        testerMatrix1 = new MathMatrix(testerArray1);
        testerArray2 = new int[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } };
        testerMatrix2 = new MathMatrix(testerArray2);

        testerMatrix1 = testerMatrix1.multiply(testerMatrix2);
        solutionArray = new int[][] { { 22, 28 }, { 49, 64 } };
        solutionMatrix = new MathMatrix(solutionArray);
        if (testerMatrix1.equals(solutionMatrix))
        {
            System.out.println("Congrats! Test case 1 passed for method multiply(). Wow!");
        } else
        {
            System.out.println("Wow! Test case 1 failed for method multiply().");
        }
        testerMatrix1 = new MathMatrix(5, 5, 2);
        testerMatrix2 = new MathMatrix(5, 5, 3);
        solutionMatrix = new MathMatrix(5, 5, 30);
        testerMatrix1 = testerMatrix1.multiply(testerMatrix2);
        if (testerMatrix1.equals(solutionMatrix))
        {
            System.out.println("Congrats! Test case 2 passed for method multiply(). Wow!");
        } else
        {
            System.out.println("Wow! Test case 2 failed for method multiply().");
        }
        //getScaledMatrix() testing ::
        testerArray1 = new int[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        testerMatrix1 = new MathMatrix(testerArray1);

        testerMatrix1 = testerMatrix1.getScaledMatrix(0);

        solutionMatrix = new MathMatrix(2, 3, 0);

        if (testerMatrix1.equals(solutionMatrix))
        {
            System.out.println("Congrats! Test case 1 passed for method getScaledMatrix(). Wow!");
        } else
        {
            System.out.println("Wow! Test case 1 failed for method getScaledMatrix().");
        }
        testerMatrix1 = new MathMatrix(5, 5, 15);
        testerMatrix1 = testerMatrix1.getScaledMatrix(-10);
        solutionMatrix = new MathMatrix(5, 5, -150);
        if (testerMatrix1.equals(solutionMatrix))
        {
            System.out.println("Congrats! Test case 2 passed for method getScaledMatrix(). Wow!");
        } else
        {
            System.out.println("Wow! Test case 2 failed for method getScaledMatrix().");
        }
        //getTranspose() testing ::
        testerArray1 = new int[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        testerMatrix1 = new MathMatrix(testerArray1);
        testerMatrix1 = testerMatrix1.getTranspose();
        solutionArray = new int[][] { { 1, 4 }, { 2, 5 }, { 3, 6 } };
        solutionMatrix = new MathMatrix(solutionArray);

        if (testerMatrix1.equals(solutionMatrix))
        {
            System.out.println("Congrats! Test case 1 passed for method getTranspose(). Wow!");
        } else
        {
            System.out.println("Wow! Test case 1 failed for method getTranspose().");
        }
        testerMatrix1 = new MathMatrix(5, 90, 15);
        testerMatrix1 = testerMatrix1.getTranspose();
        solutionMatrix = new MathMatrix(90, 5, 15);
        if (testerMatrix1.equals(solutionMatrix))
        {
            System.out.println("Congrats! Test case 2 passed for method getTranspose(). Wow!");
        } else
        {
            System.out.println("Wow! Test case 2 failed for method getTranspose().");
        }
        //equals() testing ::
        testerArray1 = new int[100][100];
        testerMatrix1 = new MathMatrix(testerArray1);
        solutionArray = new int[100][100];
        solutionMatrix = new MathMatrix(solutionArray);

        if (testerMatrix1.equals(solutionMatrix))
        {
            System.out.println("Congrats! Test case 1 passed for method equals(). Wow!");
        } else
        {
            System.out.println("Wow! Test case 1 failed for method equals().");
        }
        testerMatrix1 = new MathMatrix(5, 5, -15);
        solutionMatrix = new MathMatrix(5, 5, 15);
        if (!testerMatrix1.equals(solutionMatrix))
        {
            System.out.println("Congrats! Test case 2 passed for method equals(). Wow!");
        } else
        {
            System.out.println("Wow! Test case 2 failed for method equals().");
        }
        //toString() testing ::
        testerMatrix1 = new MathMatrix(1, 5, 100);
        String solutionString = "| 100 100 100 100 100|\n";
        if (testerMatrix1.toString().equals(solutionString))
        {
            System.out.println("Congrats! Test case 1 passed for method toString(). Wow!");
        } else
        {
            System.out.println("Wow! Test case 1 failed for method toString().");
        }
        testerMatrix1 = new MathMatrix(5, 1, -15);
        solutionString = "| -15|\n| -15|\n| -15|\n| -15|\n| -15|\n";
        if (testerMatrix1.toString().equals(solutionString))
        {
            System.out.println("Congrats! Test case 2 passed for method toString(). Wow!");
        } else
        {
            System.out.println("Wow! Test case 2 failed for method toString().");
        }
        //isUpperTriangular() testing ::
        testerArray1 = new int[][] { { 0 } };
        testerMatrix1 = new MathMatrix(testerArray1);
        if (testerMatrix1.isUpperTriangular())
        {
            System.out.println("Congrats! Test case 1 passed for method isUpperTriangular(). Wow!");
        } else
        {
            System.out.println("Wow! Test case 1 failed for method isUpperTriangular().");
        }
        testerArray1 = new int[][] { { 1, 99, 99 }, { 0, 19034981, -99 }, { -0, -0, 5 } };
        testerMatrix1 = new MathMatrix(testerArray1);

        if (testerMatrix1.isUpperTriangular())
        {
            System.out.println("Congrats! Test case 2 passed for method isUpperTriangular(). Wow!");
        } else
        {
            System.out.println("Wow! Test case 2 failed for method isUpperTriangular().");
        }

        // Experiment Code ::
        // experiment 1 ::

        // I could combine the two experiments into one for loop, and make the
        // "experimentSize" array have length 6. However, I did not
        // do it that way because of readability and also the ease of commenting out one
        // experiment over the other (ie: I don't want my program to run for
        // 300 seconds just to test experiment 1).

        // declared outside because Mike said that we should only use one instance of
        // these random matrices. Please don't kill me for this :)
        /*
        MathMatrix randomMatrix1;
        MathMatrix randomMatrix2;
        int[][] testDat1;
        int[][] testDat2;
        int[] experimentSize = new int[] { 1000, 2000, 4000 };
        
        Stopwatch s = new Stopwatch();
        
        for (int i = 0; i < 3; i++)
        {
        
            testDat1 = generateMatrix(experimentSize[i]);
            testDat2 = generateMatrix(experimentSize[i]);
        
            randomMatrix1 = new MathMatrix(testDat1);
            randomMatrix2 = new MathMatrix(testDat2);
        
            s.start();
            for (int j = 0; j < 1000; j++)
            {
                randomMatrix1.add(randomMatrix2);
            }
            s.stop();
        
            System.out.println("It took " + s.time() + " seconds to add two math matrices of length and width "
                    + experimentSize[i] + " , 1000 times.");
        }
        
        // end of experiment 1
        // Experiment 2 ::
        experimentSize = new int[] { 235, 470, 940 };
        
        for (int i = 0; i < 3; i++)
        {
        
            testDat1 = generateMatrix(experimentSize[i]);
            testDat2 = generateMatrix(experimentSize[i]);
        
            randomMatrix1 = new MathMatrix(testDat1);
            randomMatrix2 = new MathMatrix(testDat2);
        
            s.start();
            for (int j = 0; j < 100; j++)
            {
                randomMatrix1.multiply(randomMatrix2);
            }
            s.stop();
        
            System.out.println("It took " + s.time() + " seconds to multiply two math matrices of length and width "
                    + experimentSize[i] + " , 100 times.");
        }
        // Experiment 2A :: testing for heap max:
        int[][] heapMax = generateMatrix(32643);
        */
    }

    // helper method to generate and return a matrix of length and width of "size"
    // then fill it with random integers.
    // ex: if size were 1000, a matrix of size 1000x1000 would be created.
    private static int[][] generateMatrix(int size)
    {

        Random random = new Random();
        int[][] randomMatrix = new int[size][size];
        for (int i = 0; i < randomMatrix.length; i++)
        {
            for (int j = 0; j < randomMatrix[0].length; j++)
            {
                randomMatrix[i][j] = random.nextInt(randomMatrix.length);
            }
        }
        return randomMatrix;
    }

    // method that sums elements of mat, may overflow int!
    // pre: mat != null
    private static int sumVals(MathMatrix mat)
    {
        if (mat == null)
        {
            throw new IllegalArgumentException("mat may not be null");
        }
        int result = 0;
        final int ROWS = mat.getNumRows();
        final int COLS = mat.getNumColumns();
        for (int r = 0; r < ROWS; r++)
        {
            for (int c = 0; c < COLS; c++)
            {
                result += mat.getVal(r, c); // likely to overflow, but can still do simple check
            }
        }
        return result;
    }

    // create a matrix with random values
    // pre: rows > 0, cols > 0, randNumGen != null
    public static MathMatrix createMat(Random randNumGen, int rows, int cols, final int LIMIT)
    {

        if (randNumGen == null)
        {
            throw new IllegalArgumentException("randomNumGen variable may no be null");
        } else if (rows <= 0 || cols <= 0)
        {
            throw new IllegalArgumentException(
                    "rows and columns must be greater than 0. " + "rows: " + rows + ", cols: " + cols);
        }

        int[][] temp = new int[rows][cols];
        final int SUB = LIMIT / 4;
        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < cols; c++)
            {
                temp[r][c] = randNumGen.nextInt(LIMIT) - SUB;
            }
        }

        return new MathMatrix(temp);
    }

    private static void printTestResult(int[][] data1, int[][] data2, int testNum, String testingWhat)
    {
        System.out.print("Test number " + testNum + " tests the " + testingWhat + ". The test ");
        String result = equals(data1, data2) ? "passed" : "failed";
        System.out.println(result);
    }

    // pre: m != null, m is at least 1 by 1 in size
    // return a 2d array of ints the same size as m and with
    // the same elements
    private static int[][] get2DArray(MathMatrix m)
    {
        // check precondition
        if ((m == null) || (m.getNumRows() == 0) || (m.getNumColumns() == 0))
        {
            throw new IllegalArgumentException("Violation of precondition: get2DArray");
        }

        int[][] result = new int[m.getNumRows()][m.getNumColumns()];
        for (int r = 0; r < result.length; r++)
        {
            for (int c = 0; c < result[0].length; c++)
            {
                result[r][c] = m.getVal(r, c);
            }
        }
        return result;
    }

    // pre: data1 != null, data2 != null, data1 and data2 are at least 1 by 1
    // matrices
    // data1 and data2 are rectangular matrices
    // post: return true if data1 and data2 are the same size and all elements are
    // the same
    private static boolean equals(int[][] data1, int[][] data2)
    {
        // check precondition
        if ((data1 == null) || (data1.length == 0) || (data1[0].length == 0) || !rectangularMatrix(data1)
                || (data2 == null) || (data2.length == 0) || (data2[0].length == 0) || !rectangularMatrix(data2))
        {
            throw new IllegalArgumentException("Violation of precondition: equals check on 2d arrays of ints");
        }
        boolean result = (data1.length == data2.length) && (data1[0].length == data2[0].length);
        int row = 0;
        while (result && row < data1.length)
        {
            int col = 0;
            while (result && col < data1[0].length)
            {
                result = (data1[row][col] == data2[row][col]);
                col++;
            }
            row++;
        }

        return result;
    }

    // method to ensure mat is rectangular
    // pre: mat != null, mat is at least 1 by 1
    private static boolean rectangularMatrix(int[][] mat)
    {
        if (mat == null || mat.length == 0 || mat[0].length == 0)
        {
            throw new IllegalArgumentException(
                    "Violation of precondition: " + " Parameter mat may not be null" + " and must be at least 1 by 1");
        }
        return MathMatrix.rectangularMatrix(mat);
    }
}
