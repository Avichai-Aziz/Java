package lab02;

public class DiagonalMatrix implements Matrix{
	private double[] array;
	private int size;
	private boolean isTransposed;
	
	public DiagonalMatrix(int size) {
		this.size = size;
		this.array = new double[(2*this.size)-1];
		this.isTransposed = false;
	}
	
	public DiagonalMatrix() {
		this(MAX_SIZE);
	}
	
	@Override
	public double get(int i, int j) {
		if(isTransposed)
		{
			int temp = i;
			i = j;
			j = temp;
		}
		if((i <= 0) || (i > this.size) || (j <= 0) || (j > this.size))
		{	
			throw new IllegalArgumentException();
		}
		int middle = (int)((this.array.length)/2);
		return this.array[(middle- i + j)];
	}

	@Override
	public void set(int i, int j, double x) {
		if((i <= 0) || (i > this.size) || (j <= 0) || (j > this.size))
		{	
			throw new IllegalArgumentException();
		}
		int middle = (int)((this.array.length)/2);
		this.array[middle- i + j] = x;
	}

	@Override
	public void transpose() {
		if(this.isTransposed)
		{
			this.isTransposed = false;
		}
		else
		{
			this.isTransposed = true;
		}
	}

	@Override
	public Matrix getTranspose() {
		DiagonalMatrix mat = new DiagonalMatrix(this.size);

		for(int i = this.array.length; i > 0; i--)
		{
			mat.setArray(mat.getArray().length-i, this.array[i-1]);
		}
		return mat;
	}
	
	public String toString() {
		for(int i = 0; i < this.size; i++)
		{
			for(int j = 0; j < this.size; j++)
			{
				System.out.print(get(i, j));
				if(j % this.size == 0)
				{
					System.out.println();
				}
			}
		}
		return null;
	}
	
	private double[] getArray() {
		return this.array;
	}
	private void setArray(int i, double x) {
		this.array[i] = x;
	}
}


