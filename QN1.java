package CS486AI.A2Q1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class QN1 {

	private static LinkedList<City> cityList = new LinkedList<City>();
	private static int noOfCities;
	private static double temperature = 1000;
	private static double coolRate = 0.0001;
	private static double alpha = 0.999999;
	private static TSP baseSolution;
	private static TSP optimal;
	private static ArrayList<Integer> solutionTable = new ArrayList<Integer>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//loadCity(13,9);
		//System.out.println();
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println( "Start Time : " +  sdf.format(cal.getTime()) );
		load36Cities();
		//printCity();
		sitmulatedAnnealing();
        System.out.println( "End Time :" +  sdf.format(cal.getTime()) );
        
        //output data
        writeData();

	}

	private static void writeData() {
		// TODO Auto-generated method stub
		FileWriter writer;
		try {
			writer = new FileWriter("output.txt");
			for(int i =0; i < solutionTable.size(); i++){
				String str = "" + solutionTable.get(i) +"\n";
				writer.write(str);
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	private static void sitmulatedAnnealing() {
		// TODO Auto-generated method stub
		
		//SET INITIAL VALUE		
		baseSolution = new TSP();	
		// randomise solution
		baseSolution.randomiseSolution();
		
		System.out.println("Intial Distance solution: " + baseSolution.getDistance());
		baseSolution.getSolution();
		
		optimal = new TSP(baseSolution.getList());
		int counter=0;
		while(temperature > 1){
			TSP proposeSolution = new TSP(baseSolution.getList());
			
			
			//chose random city to prepare for swap
			int tsp1 = Utility.randomInt(0, baseSolution.getSize());
			int tsp2 = Utility.randomInt(0, proposeSolution.getSize());
			
			City c1 = proposeSolution.getCity(tsp1);
			City c2 = proposeSolution.getCity(tsp2);
			
			//swap
			proposeSolution.setCity(tsp2, c1);
			proposeSolution.setCity(tsp1, c2);
			
			int curDistance = baseSolution.getDistance();
			int fringeDistance = proposeSolution.getDistance();
			// store proposed solution to arraylist
			solutionTable.add(fringeDistance);
			
			//System.out.println("new solution: " + proposeSolution.getDistance());
			//Decision
			double r = Utility.randomDouble();
			if (Utility.acceptPolicy(curDistance, fringeDistance, temperature) > r)
				baseSolution = new TSP(proposeSolution.getList());
			
			// keep track of new solution
			if (baseSolution.getDistance() < optimal.getDistance())
				optimal = new TSP(baseSolution.getList());
			
			//adjust temp
			counter++;
			// linear cooling
			
			//for plotting data Qn1(3)
			temperature *=0.999;
			
			//Exp Cooling
			//temperature = 1001/Math.log(temperature+1);
	
		}
		System.out.println("Best Distance solution: " + optimal.getDistance());
		optimal.getSolution();
		System.out.println(counter + " iteration");
	}

	@SuppressWarnings("unused")
	private static void load36Cities() {
		// TODO Auto-generated method stub
		try{
			@SuppressWarnings("resource")
			//BufferedReader reader = new BufferedReader(new FileReader("CS486AI/A2Q1/randTSP-2/problem36"));
			BufferedReader reader = new BufferedReader(new FileReader("CS486AI/A2Q1/randTSP-2/problem36"));
			String output = reader.readLine();
			noOfCities = Integer.parseInt(output);
			
			output  = reader.readLine();
			while( output != null) {
				StringTokenizer defaultTokenizer = new StringTokenizer(output);
				output = reader.readLine();
				String cityName = defaultTokenizer.nextToken();;
				int x,y;
				x = Integer.parseInt(defaultTokenizer.nextToken());
				y = Integer.parseInt(defaultTokenizer.nextToken());

				City newCity = new City(cityName, x, y);
				TSPManager.addCity(newCity);
			}			
			
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	private static void loadCity(int folder, int instance) {

		// TODO Auto-generated method stub
		try{

			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader("CS486AI/A2Q1/randTSP-2/" + folder + "/instance_" + instance + ".txt"));
			String output = reader.readLine();
			noOfCities = Integer.parseInt(output);
			
			output  = reader.readLine();
			while( output != null) {
				StringTokenizer defaultTokenizer = new StringTokenizer(output);
				output = reader.readLine();
				String cityName = defaultTokenizer.nextToken();;
				int x,y;
				x = Integer.parseInt(defaultTokenizer.nextToken());
				y = Integer.parseInt(defaultTokenizer.nextToken());

				City newCity = new City(cityName, x, y);
				TSPManager.addCity(newCity);
			}			
			
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static void printCity() {
		// TODO Auto-generated method stub
		for (int i = 0; i < cityList.size(); i++){
			City city = cityList.get(i);
			city.toPrint();
		}
	}


}
