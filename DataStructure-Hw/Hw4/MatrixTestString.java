
public class MatrixTestString extends MatrixTest<String>{
	private String str = "a";
	@Override
	public String getParameterInstance() {
		str += new String("a");
		return new String(str);
	}

}
