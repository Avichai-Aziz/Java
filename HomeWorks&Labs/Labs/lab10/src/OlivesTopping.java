
import java.util.List;

public class OlivesTopping extends AbstractPizza {
	private IPizza pizza;
	private double olivesToppingPrice;

	public OlivesTopping(IPizza pizza, double toppingPrice) {
		this.pizza = pizza;
		this.pizza.toppings().add("OLIVES");
		this.olivesToppingPrice = toppingPrice;
	}


	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return this.pizza.getSize();
	}

	@Override
	public List<String> toppings() {
		// TODO Auto-generated method stub
		return this.pizza.toppings();
	}

	@Override
	public double howMuch() {
		// TODO Auto-generated method stub
		return (this.pizza.howMuch() + this.olivesToppingPrice);
	}

}
