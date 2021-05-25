
public abstract class AbstractPizza implements IPizza{
	@Override
	public String toString() {
		if(this.toppings().isEmpty()) {
			return ("Pizza: size = " + this.getSize() + "; Price = " + this.howMuch() + "; Toppings: none ");
		}
		StringBuilder sb = new StringBuilder();
		sb.append("Pizza: size = ").append(this.getSize());
		sb.append("; Price = ").append(this.howMuch());
		sb.append("; Toppings: ");
		for(String topping : this.toppings()) {
			sb.append(topping);
			sb.append(", ");
		}
		return sb.toString();
	}
}
