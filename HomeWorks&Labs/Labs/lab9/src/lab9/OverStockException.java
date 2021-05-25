package lab9;

public class OverStockException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Product product;
	private int provided;
	
	public OverStockException(Product product, int amonut) {
		this.product = product;
		this.provided  = amonut;
	}

	public OverStockException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public OverStockException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public OverStockException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}

