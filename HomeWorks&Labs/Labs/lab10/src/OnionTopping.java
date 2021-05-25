import java.util.List;

public class OnionTopping extends AbstractPizza {
	private IPizza pizza;
	private double onionsToppingPrice;
	
	public OnionTopping(IPizza pizza, double toppingPrice) {
		this.pizza = pizza;
		this.pizza.toppings().add("ONIONS");
		this.onionsToppingPrice = toppingPrice;
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
		return (this.pizza.howMuch() + this.onionsToppingPrice);
	}
	
}
