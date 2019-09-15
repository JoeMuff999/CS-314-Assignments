import java.util.Arrays;
//MathMatrix.java - CS314 Assignment 2

/*  Student information for assignment:
*
*  On my honor, <NAME>, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID:
*  email address:
*  Unique section number:
*  Number of slip days I am using:
*/

/**
 * A class that models systems of linear equations (Math Matrices)
 * as used in linear algebra.
 */
public class MathMatrix {
    
    // instance variable
	private int[][] values;
    /**
     * create a MathMatrix with cells equal to the values in mat.
     * A "deep" copy of mat is made.
     * Changes to mat after this constructor do not affect this
     * Matrix and changes to this MathMatrix do not affect mat
     * @param  mat  mat !=null, mat.length > 0, mat[0].length > 0,
     * mat is a rectangular matrix
     */
    public MathMatrix(int[][] mat) {
    	if (mat == null || mat.length <=0 || mat[0].length <= 0 ) 
            throw new IllegalArgumentException("Violation of precondition: " +
            		"MathMatrix(int[][] matrix). matrix must not be null, matrix row and col length must be greater than 0");
    	rectangularMatrix(mat);
    	values = new int[mat.length][mat[0].length];
    	for(int i = 0; i < mat.length; i++)
    	{
    		values[i] = mat[i].clone();
    	}
    	Arrays.toString(values);
    	
    	
    	
    }


    /**
     * create a MathMatrix of the specified size with all cells set to the intialValue.
     * <br>pre: numRows > 0, numCols > 0
     * <br>post: create a matrix with numRows rows and numCols columns. 
     * All elements of this matrix equal initialVal.
     * In other words after this method has been called getVal(r,c) = initialVal 
     * for all valid r and c.
     * @param numRows numRows > 0
     * @param numCols numCols > 0
     * @param initialVal all cells of this Matrix are set to initialVal
     */
    public MathMatrix(int numRows, int numCols, int initialVal) {
    	if (numRows <= 0 || numCols <=0)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"MathMatrix(int row,int col,int initialVal). param row and param col must > 0");
    	
    	values = new int[numRows][numCols]; 
    	
    	for(int[] row : values)
    	{
    		Arrays.fill(row, initialVal);
    	}
    	
    }

    /**
     * Get the number of rows.
     * @return the number of rows in this MathMatrix
     */
    public int getNumRows() {
        return this.values.length;
    }


    /**
     * Get the number of columns.
     * @return the number of columns in this MathMatrix
     */
    public int getNumColumns(){
        return this.values[0].length;
    }


    /**
     * get the value of a cell in this MathMatrix.
     * <br>pre: row  0 <= row < getNumRows(), col  0 <= col < getNumColumns()
     * @param  row  0 <= row < getNumRows()
     * @param  col  0 <= col < getNumColumns()
     * @return the value at the specified position
     */
    public int getVal(int row, int col) {
    	if (row < 0 || col < 0 || row >= getNumRows() || col >= getNumColumns())
            throw new IllegalArgumentException("Violation of precondition: " +
            		"getVal(int row, int col). param row and param col must >= 0 && row < getNumRows() && col < getNumColumns()");
        return values[row][col];
    }


    /**
     * implements MathMatrix addition, (this MathMatrix) + rightHandSide.
     * <br>pre: rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
     * <br>post: This method does not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
     * @return a new MathMatrix that is the result of adding this Matrix to rightHandSide.
     * The number of rows in the returned Matrix is equal to the number of rows in this MathMatrix.
     * The number of columns in the returned Matrix is equal to the number of columns in this MathMatrix.
     */
    public MathMatrix add(MathMatrix rightHandSide){
    	if (rightHandSide.getNumRows() != getNumRows() || rightHandSide.getNumColumns() != getNumColumns())
            throw new IllegalArgumentException("Violation of precondition: " +
            		"add(MathMatrix rightHandSide). rightHandSide.getNumRows() must equal this.getNumRows(). same applies to column length");
    	
    	MathMatrix matrixToReturn = new MathMatrix(rightHandSide.getNumRows(), rightHandSide.getNumColumns(), 0);
    	
    	for(int row = 0; row < matrixToReturn.values.length; row++)
    	{
    		for(int col = 0; col < matrixToReturn.values[0].length; col++)
    		{
    			matrixToReturn.values[row][col] = rightHandSide.getVal(row, col) + values[row][col];
    		}
    	}
    	
        return matrixToReturn;
    }


    /**
     * implements MathMatrix subtraction, (this MathMatrix) - rightHandSide.
     * <br>pre: rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
     * <br>post: This method does not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
     * @return a new MathMatrix that is the result of subtracting rightHandSide from this MathMatrix.
     * The number of rows in the returned MathMatrix is equal to the number of rows in this MathMatrix.
     * The number of columns in the returned MathMatrix is equal to the number of columns in this MathMatrix.
     */
    public MathMatrix subtract(MathMatrix rightHandSide){
    	if (rightHandSide.getNumRows() != getNumRows() || rightHandSide.getNumColumns() != getNumColumns())
            throw new IllegalArgumentException("Violation of precondition: " +
            		"subtract(MathMatrix rightHandSide). rightHandSide.getNumRows() must equal this.getNumRows(). same applies to column length");
    	
    	MathMatrix matrixToReturn = new MathMatrix(rightHandSide.getNumRows(), rightHandSide.getNumColumns(), 0);
    	
    	for(int row = 0; row < matrixToReturn.values.length; row++)
    	{
    		for(int col = 0; col < matrixToReturn.values[0].length; col++)
    		{
    			matrixToReturn.values[row][col] = values[row][col] - rightHandSide.getVal(row, col) ;
    		}
    	}
    	
        return matrixToReturn;
    }


    /**
     * implements matrix multiplication, (this MathMatrix) * rightHandSide.
     * <br>pre: rightHandSide.getNumRows() = getNumColumns()
     * <br>post: This method should not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumColumns()
     * @return a new MathMatrix that is the result of multiplying this MathMatrix and rightHandSide.
     * The number of rows in the returned MathMatrix is equal to the number of rows in this MathMatrix.
     * The number of columns in the returned MathMatrix is equal to the number of columns in rightHandSide.
     */
    public MathMatrix multiply(MathMatrix rightHandSide){
    	if (rightHandSide.getNumRows() != getNumColumns())
            throw new IllegalArgumentException("Violation of precondition: " +
            		"multiply(MathMatrix rightHandSide). rightHandSide.getNumRows() must equal this.getNumColumns()");
    	
    	MathMatrix matrixToReturn = new MathMatrix(getNumRows(), rightHandSide.getNumColumns(), 0);
    	
    	int depth = this.getNumColumns();
    	
    	for(int row = 0; row < matrixToReturn.values.length; row++)
    	{
    		for(int col = 0; col < matrixToReturn.values[0].length; col++)
    		{
    			for(int z = 0; z < depth; z++)
    			{
    				matrixToReturn.values[row][col] += values[row][z] * rightHandSide.getVal(z,col);
    			}
    		}
    	}    	
    	
        return matrixToReturn;
    } 


    /**
     * Create and return a new Matrix that is a copy
     * of this matrix, but with all values multiplied by a scale
     * value.
     * <br>pre: none
     * <br>post: returns a new Matrix with all elements in this matrix 
     * multiplied by factor. 
     * In other words after this method has been called 
     * returned_matrix.getVal(r,c) = original_matrix.getVal(r, c) * factor
     * for all valid r and c.
     * @param factor the value to multiply every cell in this Matrix by.
     * @return a MathMatrix that is a copy of this MathMatrix, but with all
     * values in the result multiplied by factor.
     */
    public MathMatrix getScaledMatrix(int factor) {
    	
    	MathMatrix scaledMatrix = new MathMatrix(values);
    	
    	for(int row = 0; row < scaledMatrix.values.length; row++)
    	{
    		for(int col = 0; col < scaledMatrix.values[0].length; col++)
    		{
    			scaledMatrix.values[row][col] *= factor;
    		}
    	}
        return scaledMatrix;
    }


    /**
     * accessor: get a transpose of this MathMatrix. 
     * This Matrix is not changed.
     * <br>pre: none
     * @return a transpose of this MathMatrix
     */
    public MathMatrix getTranspose() {
    	
    	MathMatrix transposedMatrix = new MathMatrix(values);
    	
    	int[][] newValues = new int[transposedMatrix.getNumColumns()][transposedMatrix.getNumRows()];
    	
    	for(int row = 0; row < transposedMatrix.values.length; row++)
    	{
    		for(int col = 0; col < transposedMatrix.values[0].length; col++)
    		{
    			newValues[col][row] = transposedMatrix.getVal(row, col);
    		}
    	}
    	transposedMatrix.values = newValues;
        return transposedMatrix;
    }


    /**
     * override equals.
     * @return true if rightHandSide is the same size as this MathMatrix and all values in the
     * two MathMatrix objects are the same, false otherwise
     */
    public boolean equals(Object rightHandSide){
        /* CS314 Students. The following is standard equals
         * method code. We will learn about in the coming weeks.
         */
        if( rightHandSide != null && this.getClass() == rightHandSide.getClass()){
            // rightHandSide is a non null MathMatrix
            MathMatrix otherMatrix = (MathMatrix) rightHandSide;

            // complete the method, delete this comment
            if(otherMatrix.getNumColumns() != this.getNumColumns() || otherMatrix.getNumRows() != this.getNumRows())
            	return false;
            
            for(int row = 0; row < getNumRows(); row++)
            	for(int col = 0; col < getNumColumns(); col++)
            		if(otherMatrix.getVal(row, col) != getVal(row,col));
            			return false;
        }
        return true;
    }


    /**
     * override toString.
     * @return a String with all elements of this MathMatrix. 
     * Each row is on a separate line.
     * Spacing based on longest element in this Matrix.
     */
    public String toString() {
        StringBuilder arrayString = new StringBuilder();
        
        int maxLength = 0;
        for(int row = 0; row < getNumRows(); row++)
        {
        	for(int col = 0; col < getNumColumns(); col++)
        	{
        		String s = "" +getVal(row,col);
        		if(s.length() > maxLength)
        			maxLength = s.length();
        			
        	}
        }
        for(int row = 0; row < getNumRows(); row++)
        {
        	//arrayString += Arrays.toString(this.values[row]) + "\n";
        	arrayString.append("|");
        	for(int col = 0; col < getNumColumns(); col++)
        	{
        		String s = "" + getVal(row,col);
        		for(int spaces = 0; spaces < maxLength-s.length()+1; spaces++)
        			arrayString.append(" ");
        		arrayString.append(getVal(row,col));
        	}
        	arrayString.append("|\n");	
        	//arrayString += getVal(row,getNumColumns()-1) + " ] "; 
        }
        return arrayString.toString();
    }



    /**
     * Return true if this MathMatrix is upper triangular. To
     * be upper triangular all elements below the main 
     * diagonal must be 0.<br>
     * pre: this is a square matrix. getNumRows() == getNumColumns()  
     * @return <tt>true</tt> if this MathMatrix is upper triangular,
     * <tt>false</tt> otherwise. 
     */
    public boolean isUpperTriangular(){
    	if (getNumRows() != getNumColumns()) {
            throw new IllegalArgumentException("method isUpperTriangular requires that the tested"
            		+ "MathMatrix is a square (ie: row.length = col.length)");
    	}
        
		 for(int row = 0; row < getNumRows(); row++)
		 {

		 	if(getVal(row,row) == 0)
		 		return false;	 		

		 	for(int col = 0; col < row; col ++)
		 	{
		 		if(getVal(row,col) != 0)
		 		{
		 			
		 			return false;
		 		}
		 	}
		 }        
    	
    	return true;
    }

    // method to ensure mat is rectangular
    // pre: mat != null, mat has at least one row
    // return true if all rows in mat have the same
    // number of columns false otherwise.
    public static boolean rectangularMatrix(int[][] mat) {
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("argument mat may not be null and must "
                    + " have at least one row. mat = " + Arrays.toString(mat));
        }
        boolean isRectangular = true;
        int row = 1;
        final int COLUMNS = mat[0].length;
        while (isRectangular && row < mat.length) {
            isRectangular = (mat[row].length == COLUMNS);
            row++;
        }
        return isRectangular;
    }

}