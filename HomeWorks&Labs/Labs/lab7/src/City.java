
public class City implements Comparable<City> {

	//fields
	private String name;
	private String country;
	private int population;
	private int area;

	//constructor
	public City(String cityName, String country, int population, int area) {
		this.name = cityName;
		this.country = country;
		this.population = population;
		this.area = area;
	}

	//methods
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	@Override
	public int compareTo(City city) { //sort the names of cities
		
		return (this.getName().compareTo(city.getName()));
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(this.getName()).append('(').append(this.getCountry()).append(')');
		str.append(" population: ").append(this.getPopulation());
		str.append(" area: ").append(this.getArea());		
		return str.toString();
	}
}
