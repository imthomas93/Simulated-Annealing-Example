package CS486AI.A2Q1;

import java.util.ArrayList;

public class TSPManager {

	private static ArrayList<City> cityList = new ArrayList<City>();
	
	// Add a new city
	public static void addCity(City city){
		cityList.add(city);
	}
	
	//return a city
	public static City getCity(int target){
		City result = cityList.get(target);
		return result;
	}
	
	// return a total amount of cities
	public static int countCities(){
		int count = 0;
		for(City c: cityList)
			count++;
		return count;
	}	
}
