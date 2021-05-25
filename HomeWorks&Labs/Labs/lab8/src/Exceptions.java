
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Math;

public class Exceptions {
	public static final double maxNumber = 1.0E6; //the maximum number
	public static final int maxLines = 1000; //the maximum lines
	public static final double maxSum = 5.0E8; //the maximum sum

	public void process(String fileName) {

		File file = new File(fileName);
		int lineNumber = 0; //count the number of lines
		double sumOfNumbers = 0;
		boolean flagOutOfRangeLines = false;
		boolean flagSumTooBig = false;

		try {
			double num = 0;

			Scanner scanner = new Scanner(file); //throws FileNotFindException
			if(!(scanner.hasNext())) {
				throw new EmptyFileException();
			}
			
			while(scanner.hasNextLine()) {

				String line = scanner.nextLine();
				String[] splitLine = line.split(" ");

				try {
					
					if(splitLine.length > 1) { //catch exception - TooManyEntriesException
						throw new TooManyEntriesException();
					}
					
					num = Double.parseDouble(splitLine[0]); //throws NumberFormatException if the vale is'nt a number, else change the value to the number as a double number
					
					if((Math.abs(num)) > maxNumber) { //catch exception - TooBigNumberException
						throw new TooBigNumberException();
					}
					
					sumOfNumbers += num;
					if(lineNumber > maxLines && (!flagOutOfRangeLines)) { //catch exception - TooManyLinesException
						flagOutOfRangeLines = true;
						lineNumber++;
						throw new TooManyLinesException();
					}

					if(sumOfNumbers > maxSum && (!flagSumTooBig)) { //catch exception - SumTooBigException
						flagSumTooBig = true;
						throw new SumTooBigException();
					}
					lineNumber++; 

				} //end of the inner try

				catch (NumberFormatException nfe) { //exception - the value is not a number
					System.err.println("Exception program - not a number.\r\n"
							+ "For input string: " + "\"" + line +"\"" + " after processing " + lineNumber + " lines.");
				}
				catch (TooManyEntriesException tmee) { //exception - too many values entered in  one line
					int counter = 0;
					for(int i=0; i < splitLine.length; i++) {
						if(i % 2 == 0) { //the numbers is in the even indexes
							counter++;
						}
					}
					System.err.print("Exception program - multiple entries in a line.\r\n"
							+ counter + " entries in line " + lineNumber + "\r\n"
							+ "");
				}
				catch (TooBigNumberException tbne) { //exception - the number is bigger then the maximum number
					System.err.print("Exception program - entry too big\r\n"
							+ "offending entry: " + num + " after processing " + lineNumber + " lines.\r\n"
							+ "");
				}
				catch (TooManyLinesException tmle) { //exception - too many lines entered
					System.err.print("Exception program - Too many entries\r\n"
							+ "after processing " + (lineNumber-1) + " lines.\r\n"
							+ "");
				}
				catch (SumTooBigException stbe) { //exception - the sum of numbers is bigger then the maximum sum
					System.err.print("Exception program - sum too large.\r\n"
							+ "is " + sumOfNumbers + "(exceeding " + maxSum + ") after processing " + lineNumber + " lines.\r\n"
							+ "");
				}
			} //end of while
				
			if(lineNumber != 0 && sumOfNumbers != 0) {
			//print in the end of each file summary of the line numbers read and the sum of all numbers
			System.out.print(lineNumber + " lines were processed.\r\n"
					+ "");
			System.out.print("Sum of all entries is " + sumOfNumbers + "\r\n"
					+ "");
			}
			scanner.close();

		} //end of the outside try

		catch (EmptyFileException efe) { //exception - file is empty
			System.err.print("Exception program - file empty\r\n");
			return;
		}
		catch (FileNotFoundException fnfe) { //exception - file not found
			System.err.print("Exception program - file not found\r\n"
					+ "");
			return;
		}
	} 
}

