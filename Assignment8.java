//Assignment #: Arizona State University Spring 2023 CSE205 #8
//Name: Justin Koehle
//StudentID: 1227108859 
//Lecture: T/TH 4:30PM
//Description: Assignment 8 implements several recursive methods. The methods are used to calculate the sum of
//elements in an array of doubles, the sum of all integers between two integers, the prime factorization of an integer, 
//as well as remove all occurrences of a given substring within a string.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Assignment8 {

	public static void main(String[] args) {
		// menu
		try {
			int num1;
			int num2;
			double doubleResult;
			int sumResult;
			char inputChar = ' ';
			String outputLine;
			String inputLine;
			String subxstring;
			InputStreamReader inputStream = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(inputStream);
			
			do 
			{
				printMenu();
				inputLine = br.readLine().trim();
				if (inputLine.isEmpty())
				{
					continue;
				}
				inputChar = inputLine.charAt(0);
				inputChar = Character.toUpperCase(inputChar);
				
				switch (inputChar)
				{
				case 'A':
					System.out.print("Enter numbers (0 to finish): ");
					//do I need a separate isr/br?
					doubleResult = sumOfDoubles(parseDoubles(br), 0, 0.0);
					System.out.println("Sum of elements in array: " + doubleResult);
					break;
					
				case 'B':
					System.out.print("Enter the first number: ");
					num1 = readInteger(br);
					System.out.print("Enter the second number: ");
					num2 = readInteger(br);
					sumResult = sumOfIntegers (num1, num2);
					System.out.printf("The sum of all integers between %d and %d is: %d\n", num1, num2, sumResult);
					break;
				
				case 'C':
					System.out.print("Enter an integer to factorize: ");
					num1 = readInteger(br);
					outputLine = primeFactors(num1, 2);
					System.out.println("The prime factorization of " + num1 + " is: " + outputLine.substring(0, outputLine.length() - 1));
					break;
					
				case 'D':
					System.out.print("Please enter string: ");
					inputLine = br.readLine().trim();
					System.out.print("Please enter substring to remove: ");
					subxstring = br.readLine().trim();
					num1 = 0;
					num2 = subxstring.length();
					outputLine = removeSub(inputLine, subxstring, num1, num2);
					System.out.println("String after substring removal: " + outputLine);
					break;
					
				case 'E':
					break;
					
				default:
					System.out.println("Invalid choice. Please choose a char between A and E.");
					break;
				}
			} while (inputChar != 'E' || inputLine.length() != 1);

		} catch (IOException ex) {
			System.out.println("IO Exception thrown");
		}
	}

	// A: recursive method that calculates the sum of all elements in an array of
	// doubles and returns the sum
	public static double sumOfDoubles (double[] array, int x, double someSum)
	{
		if (x >= array.length)
		{
			return someSum;
		}
		else 
		{
			someSum = array[x] + sumOfDoubles(array, x + 1, someSum);
			return someSum;
		}
	}

	// B: recursive method that calculates the sum of all integers between two
	// numbers (including the two numbers) and returns the sum
	public static int sumOfIntegers (int x, int y)
	{
		if (x == y)
		{
			return x ;
		}
		if (x < y)
		{
			return y + sumOfIntegers (x, y - 1);
		}
		else
		{
			return x + sumOfIntegers (x - 1, y);
		}
	}

	// C: recursive method that calculates the prime factorization of an integer 
	//and returns a string with the result
	public static String primeFactors (int x, int y)
	{
		if (x == 1)
		{
			return "";
		}
		if (isItPrime(y))
		{
			if (x % y == 0)
			{
				return y + "x" + primeFactors(x/y, y);
			}
		}
		return primeFactors(x, y + 1);
	}

	//helper method to find if a number is prime for primeFactors
	public static boolean isItPrime (int x)
	{
		for (int i = 2; i < x; i++)
		{
			if (x % i == 0)
			{
				return false;
			}
		}
		return true;
	}
	
	// D: recursive method that removes all occurrences of a specified substring in a string and returns the result string
	public static String removeSub (String theString, String theSub, int x, int y)
	{	
		if (theString.length() == 0 || y > theString.length())
		{
			return theString;
		}
		if (theString.substring(x, y).equals(theSub))
		{
			String hi = theString.substring(0, x) + theString.substring(y, theString.length());
			return removeSub(hi, theSub, x + 1, y + 1);
		}
		else
		{
			return removeSub(theString, theSub, x + 1, y + 1);
		}
	}
	// ----------------------------------------------------------------------------------------

	// utility method for parsing doubles from standard input that returns an array of doubles
	public static double[] parseDoubles(BufferedReader reader) {
		String line = "";
		ArrayList<Double> container = new ArrayList<>();
		try {
			line = reader.readLine();
			double num = Double.parseDouble(line);

			while (num != 0) {
				container.add(num);
				line = reader.readLine();
				num = Double.parseDouble(line);
			}

		} catch (IOException ex) {
			System.out.println("IO Exception.");
		} catch (NumberFormatException e) {
			System.out.println("Invalid input, return to main menu.");
		}

		double[] result = new double[container.size()];
		for (int i = 0; i < container.size(); i++) {
			result[i] = container.get(i);
		}
		return result;
	}

	// utility method for parsing integers from standard input (only positive integers allowed)
    public static int readInteger(BufferedReader reader) throws IOException {
        int number = 0;
        try {
            String line = reader.readLine();
            number = Integer.parseInt(line);
        } catch (IOException e) {
            System.out.println("Error reading input. Please try again.");
            number = readInteger(reader);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please try again.");
            number = readInteger(reader);
        }
        if (number <0) {
        	System.out.println("Invalid input. Only positive integers allowed. Please try again.");
        	number = readInteger(reader);
        }
        return number;
    }

	// utility method for printing the menu
	public static void printMenu() {
		System.out.print("\nWhat would you like to do?\n\n");
		System.out.print("A: Calculate the sum of all elements in an array of doubles\n");
		System.out.print("B: Calculate the sum of all integers between two numbers (including the two numbers)\n");
		System.out.print("C: Calculate the prime factorization of an integer\n");
		System.out.print("D: Remove all occurrences of a specified substring in a string\n");
		System.out.print("E: Quit\n\n");
	}
}
