package CS486AI.A2Q1;

import java.util.Random;

public class Utility {

	// calculate the distance between 2 cities
	public static double traversalCost(City a, City b){
		double x = a.getX() - b.getX();
		double y = a.getY() - b.getY();
		double cost =  Math.sqrt(x*x + y*y);
		return cost;
	}
	
	public static double acceptPolicy(int curDistance, int newDistance, double tempeture){
		// newDistance is bigger than current, adjust p
		if (newDistance > curDistance)
			return Math.exp((curDistance-newDistance)/tempeture);
		else
			// curDistance > newDistance, therefore,	 accept new result
			return 1;
	}
	
	// Randomize a number(double)
	public static double randomDouble(){
		Random r = new Random();
		return r.nextInt(1000) / 1000;
	}
	
	public static int randomInt(int low, int high){
		Random r = new Random();
		double result = (high-low) * r.nextDouble() + low;
		return (int)result;
	}
	
}
