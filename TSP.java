package CS486AI.A2Q1;

import java.util.ArrayList;
import java.util.Collections;

public class TSP {

	private ArrayList<City> list = new ArrayList<City>();
	private int distance = 0;
	
	public TSP(){
		for(int i =0;i < TSPManager.countCities(); i++)
			list.add(null);
	}
	
	@SuppressWarnings("unchecked")
	public TSP( ArrayList<City> list){
		this.list= (ArrayList<City>) list.clone();
	}
	
	public ArrayList<City> getList(){
		return list;
	}
	
	// randomize to get one original solution
	public void randomiseSolution(){
		for(int i =0; i < TSPManager.countCities(); i++){
			City c = TSPManager.getCity(i);
			setCity(i, c);
		}
		Collections.shuffle(list);
	}
	
	public void getSolution(){
		for(int i =0; i < list.size(); i++){
			System.out.print(list.get(i).getName() + "->");
		}
		System.out.println(list.get(0).getName());
	}
	
	public void setCity(int target, City city) {
		list.set(target, city);
		//reset distance
		distance =0;
	}
	
	public City getCity(int target) {
		return list.get(target);
	}
	
	public int getDistance(){

		int TSPDistance = 0;
		
		for(int i=0; i< list.size(); i++){
			City curCity = getCity(i);
			City finalCity;
			
			// check final city is out of arraylist bound
			if (i+1 == list.size())
				finalCity = getCity(0);
			else
				finalCity = getCity(i+1);
			
			TSPDistance = (int) (TSPDistance + Utility.traversalCost(curCity, finalCity));
		}
		return TSPDistance;
	}
	
	public int getSize(){
		return list.size();
	}
}
