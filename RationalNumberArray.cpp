#include "RationalNumberArray.h"

using namespace std;

// Constructs array[rows][cols].
RationalNumberArray::RationalNumberArray(int rows, int cols)
{
    this->rows = rows;
    this->cols = cols;

    // Allocate an array of pointers to represent rows of the array.
    data = new RationalNumbers* [rows];

    // Allocate an array of rows * cols to represent the data cells of the array.
    data[0] = new RationalNumbers[rows * cols];

    // Assign the row pointers to the data cells to facilitate array indexing.
    for(int i = 1; i < rows; ++i)
    {
        data[i] = data[i - 1] + cols;
    }

    // Assign values to the data cells.
    for(int i = 0; i < rows; ++i)
    {
        for(int j = 0; j < cols; ++j)
        {
            data[i][j] = RationalNumbers();
        }
    }
}

// Constructs a copy of the argument.
RationalNumberArray::RationalNumberArray(const RationalNumberArray& rhs)
{
    this->rows = rhs.getRows();
    this->cols = rhs.getCols();

    // Allocate an array of pointers to represent rows of the array.
    data = new RationalNumbers* [rhs.getRows()];

    // Allocate an array of rows * cols to represent the data cells of the array.
    data[0] = new RationalNumbers[rhs.getRows() * rhs.getCols()];

    // Assign the row pointers to the data cells to facilitate array indexing.
    for(int i = 1; i < rhs.getRows(); ++i)
    {
        data[i] = data[i - 1] + rhs.getCols();
    }

    // Assign values to the data cells.
    for(int i = 0; i < rhs.getRows(); ++i)
    {
        for(int j = 0; j < rhs.getCols(); ++j)
        {
            data[i][j] = rhs.data[i][j];
        }
    }
}

// Deletes all dynamic memory.
RationalNumberArray::~RationalNumberArray()
{
    delete[] data[0];
    delete[] data;
}

// Returns the array as a string.
string RationalNumberArray::toString()
{
    // Print the data cells.
    for (int i = 0; i < rows; ++i)
    {
        for (int j = 0; j < cols; ++j)
        {
            cout << data[i][j].toString() << "\t";
        }

        cout << endl;
    }

    return "";
}

// Compares two arrays for equality.
bool RationalNumberArray::equals(const RationalNumberArray& rhs) const
{
    // Pull and compare values from the data cells.
    for(int i = 0; i < rhs.getRows(); ++i)
    {
        for(int j = 0; j < rhs.getCols(); ++j)
        {
            if(data[i][j].equals(rhs.data[i][j]) == false)
            {
                return false;
            }
        }
    }

    return true;
}

// Returns the mean value as a double.
double RationalNumberArray::getMean() const
{
    double sum = 0;
    double total = rows * cols;

    // Pull values from the data cells.
    for(int i = 0; i < rows; ++i)
    {
        for(int j = 0; j < cols; ++j)
        {
            sum += (double)(data[i][j].getNumerator() / data[i][j].getDenominator());
        }
    }

    if(total > 0)
        sum = (double)(sum / total);

    return sum;
}

// Returns the standard deviation as a double.
double RationalNumberArray::getStdDev() const
{
    RationalNumbers stdv = RationalNumbers();
    double sum = 0;
    double mean = this->getMean();

    // Pull values from the data cells.
    for(int i = 0; i < this->rows; ++i)
    {
        for(int j = 0; j < this->cols; ++j)
        {
            try
            {
                double temp = (double)(data[i][j].getNumerator() / data[i][j].getDenominator()) - mean;
                double temp2 = (double(pow(temp, 2.0)));
                sum += (double)temp2;
            }
            catch (const char* msg)
            {
                cout << msg << endl;
            }            
        }
    }

    sum = sum / (this->rows * this->cols);
    return (double)(sqrt(sum));
}

// Set cell value.
void RationalNumberArray::setCell(int row, int col, RationalNumbers& value)
{
    if(row < 0 || row > this->rows || col < 0 || col > this->cols)
        throw "Cannot set a cell that does not exist!";

    data[row][col] = value;
}