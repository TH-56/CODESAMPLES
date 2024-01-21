// Pre-compiler guard to prevent circular includes. Can also use #pragma once.
#ifndef RationalNumberArray_h
#define RationalNumberArray_h

// Some common standard include files:
//    <> tells the pre-compiler to search the system directories.
//    "" tells the pre-compiler to search the current directories.
#include <string>
#include <cmath>
#include <iostream>
#include <cstdint>

#include "RationalNumbers.h"

// Set the default namespace, avoids having to do std:: on objects/methods.
using namespace std;

class RationalNumberArray
{

// Set up a public section.
// Can include member variables and functions.
// Can have multiple public sections, they do not have to be contiguous in the file.
public: 
    // Constructs array[rows][cols].
    RationalNumberArray(int rows, int cols);

    // Constructs a copy of the argument.
    RationalNumberArray(const RationalNumberArray& rhs);

    // Deletes all dynamic memory.
    virtual ~RationalNumberArray();


    // Returns the array as a string.
    string toString();


    // Compares two arrays for equality.
    bool equals(const RationalNumberArray& rhs) const;

    // Returns the mean value as a double.
    double getMean() const;

    // Returns the standard deviation as a double.
    double getStdDev() const;

    int getRows() const
    {
        return rows;
    }

    int getCols() const
    {
        return cols;
    }

    // Set cell value.
    void setCell(int row, int col, RationalNumbers& value);

// Set up a private section.
// Can include member variables and functions.
// Can have multiple private sections, they do not have to be contiguous in the file.
private:
    RationalNumbers** data;
    int rows;
    int cols;

}; // IMPORTANT: Don't forget the semi-colon!

#endif /* 
RationalNumberArray_h */