import java.util.Arrays;
import java.util.Random;

/*  Student information for assignment:
 *
 *  UTEID:
 *  email address:
 *  Grader name:
 *  Number of slip days I am using:
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
 * 2. The big O of the add operation is O(N^2) since I run through each element in the matrix being added to. 
 *    Since there are NxN elements, it will be N^2. Time timing data supports this hypothesis since, as expected, 
 *      the time it takes to run the experiment just-more-than quadruples as N doubles. 
 * 3. I expect it would take about 3723 seconds.
 * 4. The big O of my mulitply operation should be O(N^3). This is because, for each element of the matrix (NxN), it another N times. Therefore, it should be 
 *  rowCount*colCount*depth or NxNxN (since all are N in this situation). The timing data does not support this. This is because as N doubles, the runtime increases
 * by a factor of around 14, whereas in an O(N^3) algorithm the runtime should increase by a factor of 8.
 *5. In my case, a matrix of size 32643x32643 is the largest size which doesn't give a heap memory error (32644x32644 causes the error). 
    32643x32643 = 1,065,565,449 elements in the matrix. Multiplied by 4 bytes per int, this gives a total of supposedly 
    4,262,261,796 bytes allocated to my program which is around 4.25 GB's, a surprising amount of memory. 
 * 
 */

/**
 * A class to run tests on the MathMatrix class
 */
public class MathMatrixTester {

    /**
     * main method that runs simple test on the MathMatrix class
     *
     * @param args not used
     */
    public static void main(String[] args) {
        int[][] data1 = { { 1, 2, 3 }, { 2, 3, 4 } };
        int[][] data2 = { { 2, 1, 1 }, { 2, 3, 1 } };
        int[][] e1;

        // test 1, specify size and values constructor
        MathMatrix mat1 = new MathMatrix(2, 3, -1);
        e1 = new int[][] { { -1, -1, -1 }, { -1, -1, -1 } };
        printTestResult(get2DArray(mat1), e1, 1, "Constructor with size and initial val specified.");

        // tests 2 and 3, int[][] constructor, deep copy
        mat1 = new MathMatrix(data1);
        data1[0][0] = 2;
        // alter data1. mat1 should be unchanged if deep copy made
        e1 = new int[][] { { 2, 2, 3 }, { 2, 3, 4 } };
        printTestResult(data1, e1, 2, "constructor with one parameter of type int[][]");
        // data1 altered. mat1 should be unchanged if deep copy made
        e1 = new int[][] { { 1, 2, 3 }, { 2, 3, 4 } };
        printTestResult(get2DArray(mat1), e1, 3,
                "constructor with one parameter of type int[][]. Testing deep copy made.");

        // tests 4 - 6, addition
        data1[0][0] = 1;
        mat1 = new MathMatrix(data1);
        MathMatrix mat2 = new MathMatrix(data2);
        MathMatrix mat3 = mat1.add(mat2);
        e1 = new int[][] { { 1, 2, 3 }, { 2, 3, 4 } };
        printTestResult(get2DArray(mat1), e1, 4, "add method. Testing mat1 unchanged.");
        e1 = new int[][] { { 2, 1, 1 }, { 2, 3, 1 } };
        printTestResult(get2DArray(mat2), e1, 5, "add method. Testing mat2 unchanged.");
        e1 = new int[][] { { 3, 3, 4 }, { 4, 6, 5 } };
        printTestResult(get2DArray(mat3), e1, 6, "add method. Testing mat3 correct result.");

        // test 7, multiplication
        data2 = new int[][] { { 1, 2 }, { 3, 1 }, { 2, 1 } };
        mat2 = new MathMatrix(data2);
        mat1 = new MathMatrix(data1);
        mat3 = mat2.multiply(mat1);
        e1 = new int[][] { { 5, 8, 11 }, { 5, 9, 13 }, { 4, 7, 10 } };
        printTestResult(get2DArray(mat3), e1, 7, "multiply method");

        int[][] test1 = new int[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        int[][] test2 = new int[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } };

        mat1 = new MathMatrix(test1);
        mat2 = new MathMatrix(test2);
        mat3 = mat1.multiply(mat2);
        e1 = new int[][] { { 22, 28 }, { 49, 64 } };
        printTestResult(get2DArray(mat3), e1, 8, "multiply method");

        // test 8, toString()
        data1 = new int[][] { { 10, 100, 101, -1000 }, { 1000, 10, 55, 4 }, { 1, -1, 4, 0 } };
        mat1 = new MathMatrix(data1);
        String expected = "|    10   100   101 -1000|\n|  1000    10    55     4|\n|     1    -1     4     0|\n";
        if (mat1.toString().equals(expected)) {
            System.out.println("Passed test 8, toString method.");
        } else {
            System.out.println("Failed test 8, toString method.");
        }

        // test 9, upperTriangular
        data1 = new int[][] { { 1, 2, 3, 0 }, { 0, 3, 2, 3 }, { 0, 0, 4, -1 }, { 0, 0, 0, 12 } };
        mat1 = new MathMatrix(data1);
        if (mat1.isUpperTriangular()) {
            System.out.println("Passed test 9, upperTriangular method.");
        } else {
            System.out.println("Failed test 9, upperTriangular method.");
        }

        // test 10, upperTriangular
        data1 = new int[][] { { 1, 2, 3, 0 }, { 0, 3, 2, 3 }, { 0, 0, 4, -1 }, { 1, 2, 3, 4 } };
        mat1 = new MathMatrix(data1);
        if (!mat1.isUpperTriangular()) {
            System.out.println("Passed test 10, upperTriangular method.");
        } else {
            System.out.println("Failed test 10, upperTriangular method.");
        }

        data1 = new int[][] { { 0 } };
        mat1 = new MathMatrix(data1);
        if (mat1.isUpperTriangular()) {
            System.out.println("Passed test 11, upperTriangular 1 by 1.");
        } else {
            System.out.println("Failed test 11, upperTriangular 1 by 1.");
        }

        // test 10, upperTriangular
        data1 = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
        mat1 = new MathMatrix(data1);
        if (mat1.isUpperTriangular()) {
            System.out.println("Passed test 12, upperTriangular 0 matrix.");
        } else {
            System.out.println("Failed test 12, upperTriangular 0 matrix.");
        }

        // test 11 - 14, mutliply stress test (possible to get Answer by Accident)
        Random randNumGen = new Random(6201919);
        final int MAGIC_SUM = -1190513360;
        final int ROWS1 = 1000;
        final int COLS1 = 500;
        final int ROWS2 = 500;
        final int COLS2 = 750;
        final int LIMIT = 1000;
        mat1 = createMat(randNumGen, ROWS1, COLS1, LIMIT);
        mat2 = createMat(randNumGen, ROWS2, COLS2, LIMIT);
        Stopwatch st = new Stopwatch();
        mat3 = mat1.multiply(mat2);

        if (mat3.getNumRows() == ROWS1) {
            System.out.println("Passed test 11, multiply stess test numRows.");
        } else {
            System.out.println("Failed test 11, multiply stess test numRows");
        }

        if (mat3.getNumColumns() == COLS2) {
            System.out.println("Passed test 12, multiply stess test numCols.");
        } else {
            System.out.println("Failed test 12, multiply stess test numCols");
        }

        if (sumVals(mat3) == MAGIC_SUM) {
            System.out.println("Passed test 13, stress test, sum of values in result.");
        } else {
            System.out.println("Failed test 13, stress test, sum of values in result.");
        }

        final int MAGIC_STRING_LENGTH = 6753000;
        if (mat3.toString().length() == MAGIC_STRING_LENGTH) {
            System.out.println("Passed test 14, stress test, length of toString result.");
        } else {
            System.out.println("Failed test 14, stress test, length of toString result.");
        }
        // CS314 Students. When ready delete the above tests
        // and add your 24 tests here.

        // Experiment Code ::        
        // experiment 1 ::
        MathMatrix experiment1;
        MathMatrix experiment2;
        int[][] testDat1;
        int[][] testDat2;  
        int[] experimentSize = new int[] { 1000, 2000, 4000 };

        Stopwatch s = new Stopwatch();

      /*  for (int i = 0; i < 3; i++) {

            testDat1 = generateMatrix(experimentSize[i]);
            testDat2 = generateMatrix(experimentSize[i]);

            experiment1 = new MathMatrix(testDat1);
            experiment2 = new MathMatrix(testDat2);

            s.start();            
            for (int j = 0; j < 1000; j++) {
                experiment1.add(experiment2);
            }
            s.stop();

            System.out.println("It took " + s.time() + " seconds to add two math matrices of length and width " + experimentSize[i] + " , 1000 times.");
        }
        
        
        //end of experiment 1
        // Experiment 2 ::
        experimentSize = new int[] { 235, 470, 940 };

        for (int i = 0; i < 3; i++) {

            testDat1 = generateMatrix(experimentSize[i]);
            testDat2 = generateMatrix(experimentSize[i]);

            experiment1 = new MathMatrix(testDat1);
            experiment2 = new MathMatrix(testDat2);

            s.start();
            for (int j = 0; j < 100; j++) {
                experiment1.multiply(experiment2);
            }
            s.stop();

            System.out.println("It took " + s.time() + " seconds to multiply two math matrices of length and width " + experimentSize[i] + " , 100 times.");
        }
        //Experiment 2A :: testing for heap max:
        int[][] heapMax = generateMatrix(32643);
        */
    }
    // helper method to generate and return a matrix of length and width of "size" then fill it with random integers. 
    //ex: if size were 1000, a matrix of size 1000x1000 would be created.
    private static int[][] generateMatrix(int size){

        Random random = new Random();
        int[][] randomMatrix = new int[size][size];
        for (int i = 0; i < randomMatrix.length; i++) {
            for(int j = 0; j < randomMatrix[0].length; j++){
                randomMatrix[i][j] = random.nextInt(randomMatrix.length);
            }
        }
        return randomMatrix;
    }

    // method that sums elements of mat, may overflow int!
    // pre: mat != null
    private static int sumVals(MathMatrix mat) {
        if (mat == null) {
            throw new IllegalArgumentException("mat may not be null");
        }
        int result = 0;
        final int ROWS = mat.getNumRows();
        final int COLS = mat.getNumColumns();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                result += mat.getVal(r, c); // likely to overflow, but can still do simple check
            }
        }
        return result;
    }

    // create a matrix with random values
    // pre: rows > 0, cols > 0, randNumGen != null
    public static MathMatrix createMat(Random randNumGen, int rows, int cols, final int LIMIT) {

        if (randNumGen == null) {
            throw new IllegalArgumentException("randomNumGen variable may no be null");
        } else if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException(
                    "rows and columns must be greater than 0. " + "rows: " + rows + ", cols: " + cols);
        }

        int[][] temp = new int[rows][cols];
        final int SUB = LIMIT / 4;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                temp[r][c] = randNumGen.nextInt(LIMIT) - SUB;
            }
        }

        return new MathMatrix(temp);
    }

    private static void printTestResult(int[][] data1, int[][] data2, int testNum, String testingWhat) {
        System.out.print("Test number " + testNum + " tests the " + testingWhat + ". The test ");
        String result = equals(data1, data2) ? "passed" : "failed";
        System.out.println(result);
    }

    // pre: m != null, m is at least 1 by 1 in size
    // return a 2d array of ints the same size as m and with
    // the same elements
    private static int[][] get2DArray(MathMatrix m) {
        // check precondition
        if ((m == null) || (m.getNumRows() == 0) || (m.getNumColumns() == 0)) {
            throw new IllegalArgumentException("Violation of precondition: get2DArray");
        }

        int[][] result = new int[m.getNumRows()][m.getNumColumns()];
        for (int r = 0; r < result.length; r++) {
            for (int c = 0; c < result[0].length; c++) {
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
    private static boolean equals(int[][] data1, int[][] data2) {
        // check precondition
        if ((data1 == null) || (data1.length == 0) || (data1[0].length == 0) || !rectangularMatrix(data1)
                || (data2 == null) || (data2.length == 0) || (data2[0].length == 0) || !rectangularMatrix(data2)) {
            throw new IllegalArgumentException("Violation of precondition: equals check on 2d arrays of ints");
        }
        boolean result = (data1.length == data2.length) && (data1[0].length == data2[0].length);
        int row = 0;
        while (result && row < data1.length) {
            int col = 0;
            while (result && col < data1[0].length) {
                result = (data1[row][col] == data2[row][col]);
                col++;
            }
            row++;
        }

        return result;
    }

    // method to ensure mat is rectangular
    // pre: mat != null, mat is at least 1 by 1
    private static boolean rectangularMatrix(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            throw new IllegalArgumentException(
                    "Violation of precondition: " + " Parameter mat may not be null" + " and must be at least 1 by 1");
        }
        return MathMatrix.rectangularMatrix(mat);
    }
}
