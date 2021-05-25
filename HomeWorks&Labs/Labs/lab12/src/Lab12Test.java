

public class Lab12Test {

	public static void main(String[] args) {
		Calculator cal = new Calculator();
		try {
			Thread.sleep(6000);
		}
		catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		System.exit(1);
	}

}
