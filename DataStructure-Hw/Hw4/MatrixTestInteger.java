
public class MatrixTestInteger extends MatrixTest<Integer>{
	private int x = 0;
	@Override
	public Integer getParameterInstance() {
		++x;
		return x;
	}

}
