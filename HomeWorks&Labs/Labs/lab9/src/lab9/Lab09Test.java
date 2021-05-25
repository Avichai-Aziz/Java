package lab9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Lab09Test {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		try { //try 1
			File file = new File("storehouse.txt");
			Scanner scanner = new Scanner(file);
			String commandType = null, productName = null;
			int productAmount = 0;
			Map<String,Product> mp = new HashMap<String,Product>();
			StoreHouse sh;

			while(scanner.hasNextLine()) {
				try { //try 2
				String[] line = scanner.nextLine().split("\\s+"); ///???
				commandType = line[0];
				productName = line[1];
				productAmount = Integer.parseInt(line[2]);
				if(line.length != 3) {
					System.err.println("Error, length input entered is wrong");
				}
				else {
					Product product = null;
					if(commandType.equals("define")) {
						product = new Product(productName, productAmount);
						mp.put(productName, product);
						System.out.println("Defined product " + productName);
						
						if(mp.containsKey(productName)) {
							product = mp.get(productName);
						}
						else {
							System.out.println("Unknown product " + productName);
						}
					}
					
					else if(commandType.equals("order")) {
						sh.order(product, productAmount);
						
						System.out.println("Ordered " + productAmount  + " " + productName);
					}
					
					else if(commandType.equals("supply")) {
						sh.supply(product, productAmount);

						System.out.println("Supplied " + productAmount  + " " + productName);

					}
					else {
						System.out.println("Unrecognized operation " + commandType);
					}
				}
				
				} //end of try 2
				
				catch(OutOfStockExceptin ofse) {
					System.err.println("Product <" + productName + "> is out of stock: " + productAmount + " are needed but only " 
				+  + " are in stock.");
				}
				catch(OverStockException ose) {
					System.err.println("Product <" + productName + "> is overstocked:\r\n"
							+ " there are " + productAmount + " in stock already, and additional " + 10 + " are supplied,\r\n"
							+ " but there is room for only " + 15);
				}
			} //end of while
			scanner.close();
		} //end of try 1

		catch(FileNotFoundException fnfe) {
			System.err.println("Error, file not found.");
		}
	}
	
	

}
