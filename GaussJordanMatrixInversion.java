package classwork;

public class GaussJordanMatrixInversion
{
    // Rules:
        // 1. It must be a square matrix to start or you cannot invert it (there is no inverse if it is not square).
        // 2. You want to apply a series of operations to make your matrix look like this:
            // 1  0  0
            // 0  1  0
            // 0  0  1
        // 3. Here are the legal operations:
            // a. You can multiply/divide a row by a scalar.
            // b. You can exchange two rows.
            // c. You can subtract one row from another, replacing the first.
            // d. If at any time the matrix fails to be square, bail out.
                // i. You have a "SINGULAR" matrix (you do NOT want this).
                // ii. A row of zeros does NOT count towards it being square.
        // OTHER NOTES:
            // a. Going from input --> identity matrix --> inverse.
            // b. The pivot value is always on the diagonal (where row = column).
                // i. If the pivot value is not zero, divide the row of the pivot by the pivot value.
                // ii. If the pivot value is zero, exchange it with a row that has the pivot not being zero.
            // c. You are figuring out which operations to do based on the input, but you are performing the operations on both the input matrix AND the identity matrix.
    
    // Steps (for each row):
        // 1. Select a row (start at the top).
        // 2. Get a non-zero value as the pivot (exchanging rows if you have to [swapRows function]).
        // 3. Make the pivot value equal to one (divide pivot's row by the pivot).
        // 4. Subtract multiples of the "pivot row" from all other rows setting "pivot column" to zero.

    // Main function/method.
    public static void main(String[] args)
    {
        double a[][] = {{2, -4, 6}, {6, -6, 6}, {4, 2, 2}};
        printMatrix(invert(a));
    }

    // Function for inverting a 2D matrix.
    public static double[][] invert(double[][] a) throws IllegalArgumentException
    {
        // Checking size of the inputted matrix.
        if(a.length != a[0].length)
            throw new IllegalArgumentException("ERROR: Matrix not invertable.");

        // Initializing size variables.
        int rows = a.length;
        int columns = a[0].length;

        // Initializing our inverted matrix variable.
        double[][] inverted = new double[rows][columns];

        // Generating our identity matrix.
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                if(i == j)
                inverted[i][j] = 1.0;
            }
        }

        for(int i = 0; i < rows; i++)
        {
            int pivotLocation = i;

            double pivot = a[pivotLocation][i];

            try
            {
                // Swapping rows in order to get the pivot value to be non-zero.
                while(Math.abs(a[pivotLocation][i]) < 0.0000000001)
                {
                    pivotLocation++;
                }

                pivot = a[pivotLocation][i];

                swapRows(a, i, pivotLocation);
                swapRows(inverted, i, pivotLocation);

                // Dividing rows by the pivot value in order to get the pivot value equal to one.
                for(int j = 0; j < columns; j++)
                {
                    a[i][j] /= pivot;
                    inverted[i][j] /= pivot;
                }

                // Subtracting multiples of the pivot in order to set the pivot column to zeros.
                for(int ii = 0; ii < rows; ii++)
                {
                    if(i == ii) continue;

                    double multiplier = a[ii][i];

                    for(int j = 0; j < columns; j++)
                    {
                        a[ii][j] -= multiplier * a[i][j];
                        inverted[ii][j] -= multiplier * inverted[i][j];
                    }
                }
            }
            catch(IndexOutOfBoundsException e)
            {
                throw new IndexOutOfBoundsException("ERROR: Matrix is singular.");
            }
        }

        // Returning the result.
        return inverted;
    }

    // Function for swapping two rows in a 2D matrix.
    private static void swapRows(double[][] a, int r0, int r1)
    {
        int columns = a[0].length;

        for(int j = 0; j < columns; j++)
        {
            double temp = a[r0][j];
            a[r0][j] = a[r1][j];
            a[r1][j] = temp;
        }
    }

    public static void printMatrix(double[][] a)
    {
        for(int i = 0; i < a.length; i++)
        {
            for(int j = 0; j < a[i].length; j++)
            {
                System.out.printf("%7.2f", a[i][j]);
            }

            System.out.println();
        }
    }
}
