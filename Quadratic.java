package classwork;
// Creator: Thomas Hernandez.
// Purpose: Quadratic Problem.

// Importing libraries.
import java.util.Scanner;
import java.lang.Math;

// Class declaration.
public class Quadratic
{
   public static void main(String[] args)
   {
      // Initialize variables.
      double discriminant;
      double root1;
      double root2;
      double solution;
      String imaginary;
      
      // Try statement to catch possible input error(s).
      // Use Case #1: Solve the equation.
      try
      {
         // Gather input from user.
         Scanner input = new Scanner(System.in);
         System.out.print("Please input a value for the 'real valued' coefficient \"a\": ");
         double a = input.nextDouble();
         System.out.print("Please input a value for the 'real valued' coefficient \"b\": ");
         double b = input.nextDouble();
         System.out.print("Please input a value for the 'real valued' coefficient \"c\": ");
         double c = input.nextDouble();
         
         // Calculate discriminant.
         discriminant = (b * b) - (4 * (a * c));
         
         // Scenario #1: If statement is true when there are infinite solution(s) / no solution.
         if(a == 0 && b == 0 && c == 0)
         {
            // Display results.
            System.out.println();
            System.out.println("RESULTS: ");
            System.out.println("There are infinite solution(s) / no solution.");
            return;
         }
         
         // Scenario #2: If statement is true when there is no solution and it is a linear equation.
         if(a == 0 && b == 0)
         {
            // Print results.
            System.out.println();
            System.out.println("RESULTS: ");
            System.out.println("This is a linear equation. There is no solution.");
            return;
         }
         
         // Scenario #3: If statement is true when there is one solution and it is a linear equation.
         if(a == 0 && b != 0)
         {
            // Calculate x-intercept/solution for linear equation.
            solution = ((-c) / b);
            
            // Display results.
            System.out.println();
            System.out.println("RESULTS: ");
            System.out.println("This is a linear equation. There is one solution.");
            System.out.println("Solution: " + solution);
            return;
         }
         
         // Scenario #4: If statement is true when there are two real roots and it is a quadratic equation.
         if(discriminant > 0)
         {
            // Calculate roots.
            root1 = (-b + (Math.sqrt(discriminant)));
            root1 = root1 / (2 * a);
            root2 = (-b - (Math.sqrt(discriminant)));
            root2 = root2 / (2 * a);
            
            // Display results.
            System.out.println();
            System.out.println("RESULTS: ");
            System.out.println("Two real roots.");
            System.out.println("Root(s): " + root1 + ", " + root2);
            return;
         }
         
         // Scenario #5: If statement is true when there are two complex roots and it is a quadratic equation.
         if(discriminant < 0)
         {
            // Calculate roots.
            discriminant = discriminant * -1; // Set the discr. to a positive number.
            root1 = ((-b) / (2 * a));
            root2 = ((Math.sqrt(discriminant)) / (2 * a));
            imaginary = root1 + " + " + root2;
            
            // Display results.
            System.out.println();
            System.out.println("RESULTS: ");
            System.out.println("Two complex roots.");
            System.out.println("Root #1: " + root1 + " + " + root2 + " i");  // Attach an "i" to the end of the result to indicate that it is an imaginary number.4
            System.out.println("Root #2: " + root1 + " - " + root2 + " i");  // Attach an "i" to the end of the result to indicate that it is an imaginary number.
            return;
         }
         
         // Scenario #6: If statement is true when there is one real root and it is a quadratic equation.
         if(discriminant == 0)
         {
            // Calculate roots.
            root1 = (-b + (Math.sqrt(discriminant)));
            root1 = root1 / (2 * a);
            root2 = (-b - (Math.sqrt(discriminant)));
            root2 = root2 / (2 * a);
            
            // Display results.
            System.out.println();
            System.out.println("RESULTS: ");
            System.out.println("One real root.");
            System.out.println("Root(s): " + root1);
            return;
         }
      }
      
      // Catch statement handles errors regarding invalid input.
      // Use Case #2: Don't solve the equation: invalid input.
      catch(Exception InputMismatchException)
      {
         // Display error messages.
         System.out.println("ERROR: Please enter valid input only! (numeric inputs only)!");
      }
   }
}