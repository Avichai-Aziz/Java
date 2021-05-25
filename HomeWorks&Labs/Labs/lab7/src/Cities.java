

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class Cities implements Comparator<City> {
	//fields
	private SortedSet<City> cityList;

	//constructor
	public Cities() {
		this.cityList = new TreeSet<City>();
		this.init("cities.txt");
	}

	//methods
	public void init(String filename) {
		File file = new File(filename);
		Scanner scanner;
		try {
			scanner = new Scanner(file).useDelimiter("[\t\n]");

			String cityName = null, country = null;
			int population = 0 , area = 0;

			while(scanner.hasNext()) {
				if(scanner.hasNext()) {
					cityName = scanner.next();
				}
				if(scanner.hasNext()) {
					country = scanner.next();
				}
				if(scanner.hasNextInt()) {
					population = scanner.nextInt();
				}
				if(scanner.hasNextInt()) {
					area = scanner.nextInt();
				}
				City city = new City(cityName, country, population, area);
				cityList.add(city);	
			}
			scanner.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void listAlphabetically() { //print sorted by the Alphabetically
		System.out.println();
		System.out.println("City list\n");
		for(City c : cityList) {
			System.out.println(c.toString());
		}
	}

	public void listByCountry(String country) { //print all the cities in the input country
		System.out.println();
		for(City c : cityList) {
			if(country.toLowerCase().equals(c.getCountry().toLowerCase())) {
				System.out.println("Cities in " + c.getCountry() + ":\n");
				break;
			}
		}
		for(City city : cityList) {
			if(country.toLowerCase().equals(city.getCountry().toLowerCase())) {
				System.out.println(city.toString());
				}
			}
	}
	
	public void listByPopulation() {//print sorted by the population
		System.out.println();
		System.out.println("City list by population\n");
		SortedSet<City> cityListPopulation = new TreeSet<City>(this);
		for(City c : cityList) {
			cityListPopulation.add(c);
		}
		for(City c2 : cityListPopulation) {
			System.out.println(c2.toString());
		}
	}

	public void listByDensity() { //print sorted by the density
		System.out.println();
		System.out.println("City list by density\n");
		Comperator2 comperator2 = new Comperator2(); 
		SortedSet<City> cityListDensity = new TreeSet<City>(comperator2);
		for(City c : cityList) {
			cityListDensity.add(c);
		}
		for(City c2 : cityListDensity) {
			System.out.println(c2.toString() + " density: " + c2.getPopulation()/c2.getArea());
		}
	}

	@Override
	public int compare(City city1, City city2) { //sort the population
		return city2.getPopulation() - city1.getPopulation();
	}
}
