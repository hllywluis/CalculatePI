import java.lang.Math;
import java.util.Scanner;
/*=============================================================================
| Source code: CalculatePI.java
| Author: Luis Bauza
| Student ID: 5825548
| Assignment: Program #1 Calculate π
|
| Course: COP 3337 - (Computer Programming II)
| Section: U06
| Instructor: William Feild
| Due Date: 1-24-19, at the beginning of class
|
| I hereby certify that this collective work is my own
| and none of it is the work of any other person or entity.
|
| ______________________________________ [Signature]
|
| Language: Java
| Compile/Run: javac CalculatePI.java
|              java CalculatePI
|
| +-----------------------------------------------------------------------------
|
| Description: The goal of this program is to use an algorithm called the Taylor Series to calculate π to a user-input
|              precision, without using Math.PI as a reference to compare the original "estimation" of π with the
|              constant that Java has.
|
| Formulas:  Taylor Series:
|              π/4 = 1 - (1/3) + (1/5) - (1/7) + (1/9) ...
|
|            Alternating Series:
|              (-1)^n * (a subscript n)
|
| Input: The only user input required by this program is a number between 2 and 9, inclusive, with which to calculate
|        the precision of our "estimated" π.
|
| Output: The output will produce 5 lines, which are:
|         1. The number of iterations required to reach the desired precision.
|         2. The "estimated" or "computed" value of π.
|         3. The actual value of π, as obtained from Math.PI.
|         4. The "required delta" or the difference between Math.PI and our "computed value."
|         5. The user-input "desired precision" with which the calculation was made.
|
| Process: 1. We start out the program by defining every variable that we may need, I separated them by immutable and
|             type of variable.
|
|          2. Next, we prompt the user to enter a number between 2-9, inclusive, with which the program
|             will know up to what decimal place to stop at with the delta value.
|
|          3. After this, we have a StringBuilder object that receives the userDefinedPrecision and builds a String with
|             the requested number of zeroes. A 1 is appended to the end of the String so that when we parse the Double,
|             we don't end up with 0.0
|
|          4. We then begin to calculate π using the Taylor Sum formula combined with the Alternating Series formula.
|             There are multiple variables set up in order to achieve this, including an oddNumber counter and
|             iterationCounter variables.
|
|
| Required Features Not Included: None, the output is exactly as described in the assignment.
|
| Known Bugs: There is no input validation using try{} and catch{} for mismatched inputs, and the program crashes when a
|             user enters 0.
| *===========================================================================*/
public class CalculatePI {

    public static void main(String[] args) {

        // Immutable variables
        final double PI = Math.PI;
        final double TAYLOR_ONE = 1.0;

        // Scanner for getting user input from the console with user's keyboard.
        Scanner userInput = new Scanner(System.in);

        // Instance variables
        double piCounter = 0;
        double piCopy = 0;
        double oddNumber = 1;
        double delta = 5;
        int userDefinedPrecision;
        int iterationCounter = 1;
        StringBuilder precisionString = new StringBuilder();
        /*==========================================================================================*/

        // CalculatePI, START!

        /*
         * Prompting the user to enter a number between 2 and 9.
         */
        System.out.println("Please enter a precision (number of decimals places) which you'd like to calculate PI" +
                " with. [2-9]");
        userDefinedPrecision = userInput.nextInt();

        /*
         * The for loop and next 2 statements are there in order to build a String with which we can compare the delta
         * value and the String value together. A . is appended to the first position of the String, and a 1 is appended
         * at the end in order to stop Java from simplifying the result as 0.0
         */
        for (int i = 0; i < userDefinedPrecision; i++) {
            precisionString.append("0");
        } // end for

        precisionString.insert(1, ".");
        precisionString.append(1);

        /*
         * This is the heart of the program, where the value of Pi is computed. The piCounter is set up in a way that
         * constantly subtracts what comes after the -=, since the formula starts with a subtraction. A piCopy variable
         * is set to the value of piCounter after the delta is calculated to be kept as a copy of the previous computed
         * value. We take the absolute value of the counter minus the copy because it alternates between positive and
         * negative values. The oddNumber counter is incremented by 2 each time, starting at 1, to match the formula for
         * the Taylor Sum as given in the book. Finally, we increment the iterationCounter by 1 after each loop.
         */
        while (delta > Double.parseDouble(precisionString.toString())) {
            piCounter -= 4 * Math.pow(-1, iterationCounter) * (TAYLOR_ONE / oddNumber);
            delta = Math.abs(piCounter - piCopy);
            piCopy = piCounter;
            oddNumber += 2;
            iterationCounter++;
        } // end while

        /*
         * The block of code underneath pretty-prints out the information obtained from the statements above.
         */
        System.out.println("\nNumber of iterations: " + iterationCounter + "\n" +
                "Computed Value of Pi: " + piCounter + "\n" +
                "Actual Value of Pi: " + PI + "\n" +
                "Required Delta: " + delta + "\n" +
                "Desired Accuracy: " + userDefinedPrecision);
    } // end main

} // end class
