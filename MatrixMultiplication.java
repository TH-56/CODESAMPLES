package classwork;

public class MatrixMultiplication
{
    public static void main(String[] args)
    {
        double[][] a = {{2, -4, 6}, {6, -6, 6}, {4, 2, 2}};
        double[][] b = {{-0.2, 1.0/6.0, 0.1}, {0.1, -1.0/6.0, 0.2}, {0.3, -1.0/6.0, 0.1}};

        double[][] result = matrixMult(a, b);

        for(int i = 0; i < result.length; i++)
        {
            for(int j = 0; j < result[0].length; j++)
            {
                System.out.printf("%5.4f", result[i][j]);
                System.out.print(" ");
            }
            
            System.out.println("\n");
        }
    }

    public static double[][] matrixMult(double[][] a, double[][] b) throws IllegalArgumentException
    {
        if(a[0].length != b.length)
        {
            throw new IllegalArgumentException("Mismatched matrixes!");
        }

        double result[][] = new double[a.length][b[0].length];

        try
        {
            for(int i = 0; i < a.length; i++) // Pick a row.
            {
                for(int j = 0; j < b[0].length; j++) // Pick a column.
                {
                    for(int k = 0; k < a[0].length; k++) // Pick a column from 'a' and a row from 'b'.
                    {
                        result[i][j] += a[i][k] * b[k][j];
                    }
                }
            }

            return result;
        }
        catch(IndexOutOfBoundsException e)
        {
            throw new IllegalArgumentException("Mismatched matrixes!");
        }
    }
}
