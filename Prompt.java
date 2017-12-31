import java.util.Scanner;

/**
 *	Prompt.java
 *	Provide some utilities for user input.  We want to enhance the Scanner class,
 *	so that our programs can recover from "bad" input, and also provide a way to
 *	limit numerical input to a range of values.
 *
 *	@author Kevin Zhou
 *	@version August 30, 2017
 */

public class Prompt
{
	/**
	 *	Javadoc comments go here
	 */
	public static String getString (String ask)
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print(ask + " -> ");
		String result = keyboard.nextLine();
		return result;
	}
	
	/**
	 *	Javadoc comments go here
	 */
	public static char getChar (String ask)
	{
		boolean badInput = false;
		String input = "";
		char result = 'a';
		
		do {
			badInput = false;
			input = getString(ask);
			if (input.length() <= 1) {}
			else {
				badInput = true;
			}
		} while (badInput);
		
		result = input.charAt(0);
		return result;
	}
	
	/**
	 *	Javadoc comments go here
	 */
	public static int getInt (String ask)
	{
		boolean badInput = false;
		String input = new String("");
		int result = 0;
		
		do {
			badInput = false;
			input = getString(ask);
			try {
				result = Integer.parseInt(input);
			}
			catch (NumberFormatException e) {
				badInput = true;
			}
		} while (badInput);

		return result;
	}
	
	/**
	 *	Javadoc comments go here
	 */
	public static int getInt (String ask, int min, int max)
	{
		boolean badInput = false;
		int input = 0;
		int result = 0;
		int x = min;
		int y = max;
		
		do {
			badInput = false;
			input = getInt(ask);
			if (x <= input && input <= y) {}
			else {
				badInput = true;
			}
		} while (badInput);
		
		result = input;
		return result;
	}
	
	/**
	 *	Javadoc comments go here
	 */
	public static double getDouble (String ask)
	{
		boolean badInput = false;
		String input = new String("");
		double result = 0.0;
		
		do {
			badInput = false;
			input = getString(ask);
			try {
				result = Double.parseDouble(input);
			}
			catch (NumberFormatException e) {
				badInput = true;
			}
		} while (badInput);

		return result;
	}
	
	/**
	 *	Javadoc comments go here
	 */
	public static double getDouble (String ask, double min, double max)
	{
		boolean badInput = false;
		double input = 0.0;
		double result = 0.0;
		double x = min;
		double y = max;
		
		do {
			badInput = false;
			input = getDouble(ask);
			if (x <= input && input <= y) {}
			else {
				badInput = true;
			}
		} while (badInput);
		
		result = input;
		return result;
	}
}