
public class SparseMatrix<T> implements Matrix<T>{
	private DLinkedList<SparseMatrixEntry> dll;
	private int size;
	private T defaultValue;
	private boolean isTranspose;
	
private class SparseMatrixEntry {
		  private T value; 
		  private int row;
		  private int col;      

		public SparseMatrixEntry(int row, int col, T val) {
			this.row = row;
			this.col = col;
			this.value = val;
		}
			public int getRow() { 
				if(isTranspose) {
					return col;
				} 
				return row;
			}	
			public void setRow(int row) { this.row = row; }	
			public int getCol() {				
				if(isTranspose) {
					return row;
				} 
				return col;
			}	
			public void setCol(int col) { this.col = col; }	
			public T getValue() { return this.value; }
			public void setValue(T newVal) { this.value = newVal; }
		}
	
	public SparseMatrix (int size, T defaultValue){
		dll = new DLinkedList<SparseMatrixEntry>();
		this.size = size;
		this.defaultValue = defaultValue;
		this.isTranspose = false;
	}
	public SparseMatrix (T defaultValue){
		this(MAX_SIZE, defaultValue);
	}

	@Override
	public T get(int row, int col) {
		if(row < 1 || col < 1 || row > this.size || col > this.size)
		{
			throw new IllegalArgumentException("index out of range.");
		}
        if(dll.isEmpty())
            return defaultValue;
		dll.goToBeginning();
		while(dll.getCursor() != null)
		{
			if((dll.getCursor().getRow() == row) && (dll.getCursor().getCol() == col))
			{
				return dll.getCursor().getValue();
			}
			if (!dll.hasNext()) {
				break;
			}
			dll.getNext();
		}
		return this.defaultValue;
	}
	@Override
	public void set(int row, int col, T element) {
		boolean isExist = false;
		if(row < 1 || col < 1 || row > this.size || col > this.size)
		{
			throw new IllegalArgumentException("index out of range.");
		}
		dll.goToBeginning();
		while(dll.getCursor() != null)
		{
			if((dll.getCursor().getRow() == row) && (dll.getCursor().getCol() == col))
			{
				isExist = true;
				dll.getCursor().setValue(element);
				break;
			}
			if (!dll.hasNext()) {
				break;
			}
			dll.getNext();
		}

		if(!isExist)
		{
			dll.goToEnd();
			dll.insert(new SparseMatrixEntry(row, col, element));
		}
	}
	@Override
	public void transpose() {
		this.isTranspose = !this.isTranspose;
	}
	@Override
	public Matrix<T> getTranspose() {
		SparseMatrix<T> temp_mat = new SparseMatrix<T>(this.size, this.defaultValue);
		dll.goToBeginning();
		while(dll.getCursor() != null)
		{
			temp_mat.set(dll.getCursor().getCol(), dll.getCursor().getRow(), dll.getCursor().getValue());
			if(!dll.hasNext())
			{
				break;
			}
			dll.getNext();
		}
		return temp_mat ;
	}
}
