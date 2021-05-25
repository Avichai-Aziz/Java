package lab9;

import java.util.HashMap;
import java.util.Map;

public class StoreHouse{
	private Map<Product, Integer> mapProducts;
	
	public StoreHouse(Product product, int amount) {
		this.mapProducts = new HashMap<Product, Integer>();
	}
	
	public void order(Product product, int amount) throws OverStockException {
		if(!(this.mapProducts.keySet().contains(product))) {
			this.mapProducts.put(product, 0);
		}
		int stockAmount = this.mapProducts.get(product);
		if((stockAmount + amount) <= product.getMaxStock() && amount > 0) {
			this.mapProducts.put(product, stockAmount + amount);
		}
		else {
			throw new OverStockException(product, amount);
		}
	}
	
	public void supply(Product product, int amount) throws OutOfStockExceptin {
		if(!(this.mapProducts.keySet().contains(product))) {
			this.mapProducts.put(product, 0); //if the product does'nt exists then create one with zero amount 
		}
		int stockAmount = this.mapProducts.get(product);
		if((amount <= stockAmount && amount > 0)) {
			this.mapProducts.put(product, stockAmount - amount);
		}
		else {
			throw new OutOfStockExceptin(product, amount);
		}
	}

	public int stock(Product product) {
		if()
		return 0;
	}
	
	
	public Map<Product, Integer> getMapProducts() {
		
		return mapProducts;
	}

	public void setMapProducts(Map<Product, Integer> mapProducts) {
		this.mapProducts = mapProducts;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("StoreHouse:\n");
		int i = 1;
		for(Product p : this.mapProducts.keySet()) {
			stringBuilder.append("\t");
			stringBuilder.append(i++ + ". ");
			stringBuilder.append(p.getName());
			stringBuilder.append(" - ").append(this.mapProducts.get(p)).append("\n");
		}
		stringBuilder.append("end of list");
		return stringBuilder.toString();
	}
}
