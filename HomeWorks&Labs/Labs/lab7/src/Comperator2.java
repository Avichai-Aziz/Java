import java.util.Comparator;

public class Comperator2 implements Comparator<City>{

	@Override
	public int compare(City city1, City city2) { //sort the density
		double density1 = ((double) city1.getPopulation() / (double) city1.getArea());
		double density2 = ((double) city2.getPopulation() / (double) city2.getArea());
		if(density1 > density2) {
			return 1;
		}
		else if(density1 < density2) {
			return -1;
		}
		else {
			return 0;
		}
	}
}
