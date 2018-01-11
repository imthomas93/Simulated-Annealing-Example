package CS486AI.A2Q1;

public class City {
	private String cityName;
	private int x;
	private int y;
		
	public City(String cityName, int x, int y){
		this.cityName = cityName;
		this.x = x;
		this.y = y;
	}
	
	public String getName() {
		return cityName;
	}
	
	public void setName(String cityName) {
		this.cityName = cityName;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void toPrint(){
		System.out.println(this.cityName + " " + this.x + " " + this.y);
	}
		
}
