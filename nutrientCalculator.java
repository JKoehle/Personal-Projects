import java.util.Scanner;

public class nutrientCalculator 
{
	public static int fatAmount (int bodyweight, int input)
	{	
		double factor = 0;
		int output;
		if (input == 1) 
		{ 
			factor = 0.35;	
		}
		if (input == 2) 
		{
			factor = 0.4;
			
		}
		if (input == 3) 
		{
			factor = 0.45;
		} 
		output = (int) (factor * bodyweight);
		return output;
		
	}
	public static int carbAmount (int bodyweight, int input) 
	{
		double factor = 0;
		int output = 0;	
		if (input == 1) 
		{ 
			factor = 1.2;	
		}
		if (input == 2) 
		{
			factor = 1.4;
			
		}
		if (input == 3) 
		{
			factor = 1.6;
		} 
		output = (int)(factor * bodyweight);
		return output;
	}
	public static int proteinAmount (int bodyweight, int input) 
	{
		double factor = 0;
		int output = 0;	
		if (input == 1) 
		{ 
			factor = 0.8;	
		}
		if (input == 2) 
		{
			factor = 0.95;
			
		}
		if (input == 3) 
		{
			factor = 1.05;
		} 
		output = (int) (factor * bodyweight);
		return output;
		}
		
	public static void main(String[] args) 
	{
		Scanner scan = new Scanner (System.in);
		System.out.println("Enter your current bodyweight in pounds, rounded to the nearest whole number:");
		int bodyweight = scan.nextInt();
		System.out.println("Please make a selection from the following indicating your weight goals: [1] to lose, [2] to maintain, or [3] to gain.");
		int goal = scan.nextInt();
		int protein = proteinAmount(bodyweight, goal);
		int fat = fatAmount(bodyweight, goal);
		int carbs = carbAmount(bodyweight,goal);
		System.out.println("Your daily recommended minimum macronutrient intake is as follows:");
		System.out.printf("%d grams of protein, %d grams of fat, and %d grams of carbohydrates\n", protein, fat, carbs);
		System.out.println("The calculations used in this program are based around your basal metabolic rate, or the energy you burn at rest.");
		System.out.println("Thus you may need to increase the amount you intake based on your activity level.");
}
}
