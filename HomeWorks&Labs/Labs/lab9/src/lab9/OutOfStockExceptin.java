package lab9;

public class OutOfStockExceptin extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Product product;
	private int needed;
	
	public OutOfStockExceptin(Product product, int amount) {
		this.product = product;
		this.needed = amount;
	}

	public OutOfStockExceptin(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public OutOfStockExceptin(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public OutOfStockExceptin(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}


