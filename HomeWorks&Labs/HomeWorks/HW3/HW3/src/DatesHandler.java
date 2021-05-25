import java.io.File;
import java.io.FileNotFoundException;
import java.io.Writer;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import exceptionsTypes.InputDateException;
import exceptionsTypes.InputLineException;
import exceptionsTypes.InputTimeException;

public class DatesHandler {
	Scanner scan,row;
	DateTime[] arr;
	List<DateTime> list;
	SortedSet<DateTime> set;
	Map<DateUtils.DateType, Set<DateTime>> map;

	public static void main(String[] args) throws InputTimeException, IOException, InputDateException, InputLineException {
		/* argv[0] = filename including path, argv[1] = number of lines */
		try { //try #1
			File f = new File(args[0]); // f - text file with dates and times
			Scanner scan = new Scanner(f).useDelimiter("\n"); 
			List<DateTime> list = new ArrayList<DateTime>(); // contains all of the dates by reversed order
			SortedSet<DateTime> set = new TreeSet<DateTime>(new SetCompartor()); // contains all of the date by chronological order
			DateTime[] arr = new DateTime[Integer.valueOf(args[1])]; //contains the dates by the reading order , args[1] is the number of lines in the file
			Map<DateUtils.DateType, Set<DateTime>> map = new TreeMap<DateUtils.DateType, Set<DateTime>>(); // keys are enum types of  JEWISH/GREGORIAN the values are sorted by chronological order
			map.put(DateUtils.DateType.GREGORIAN, new TreeSet<DateTime>(new SetCompartor())); // Initializing  the map
			map.put(DateUtils.DateType.JEWISH, new TreeSet<DateTime>(new SetCompartor())); // Initializing  the map

			int lineCounter = 1;
			int i = 0; //index
			
			//files writers
			Writer wrILE = new FileWriter("InputLineException.txt"); //writer to file InputLineException
			Writer wrIDE = new FileWriter("InputDateException.txt"); //writer to file InputDateException
			Writer wrITE = new FileWriter("InputTimeException.txt"); //writer to file InputTimeException
			
			//files of collections
			Writer wrDOA1 = new FileWriter("datesOutArr.txt"); //writer to file datesOutArr
			Writer wrDOL1 = new FileWriter("datesOutList.txt"); //writer to file datesOutList
			Writer wrDSOA1 = new FileWriter("datesSortOutArr.txt"); //writer to file datesSortOutArr
			Writer wrDSOL1 = new FileWriter("datesSortOutList.txt"); //writer to file datesSortOutList
			Writer wrDSOS1 = new FileWriter("datesSortOutSet.txt"); //writer to file datesSortOutSet
			Writer wrDSOM1 = new FileWriter("datesSortOutMap.txt"); //writer to file datesSortOutMap
			
			while(scan.hasNext()) { // this while scans the input file dates and times 
				String line = scan.next();
				try { //try #2
					if(line.length() > 22) { // checks for too many arguments in the file
						throw new InputLineException("Error message = Too many " + "\""+ line.charAt(22) + "\"" + " in the input line.");
					}
	
					if((line.startsWith("j#")) || (line.startsWith("g#"))) { // checks for wrong Datetype input
						Scanner row = new Scanner(line).useDelimiter("[#\r]");

						while(row.hasNext()) {
							String datetype, date, time = null;
							datetype = row.next();

							if(!(row.hasNext())) { //check if row has more letters after the date type
								throw new InputLineException("Error message = Not enough parameters in the input line.");
							}

							date = row.next();

							if(!(date.matches("\\d{1,2}/\\d{1,2}/\\d{1,4}"))) { // check if the pattern is xx/xx/xxxx and also x is a digit
								throw new InputDateException("Error message = Exception in scanning the date string");
							}

							time = "00:00:00"; //default time in case there is no time entered in the file
							
							if(row.hasNext()) { //checking if time was written if not it gives the default time
								time = row.next();
							}
							
							if(row.hasNext()) { //checking if there is more letters after the time
								String value = row.next();
								throw new InputLineException("Error message = Too many " + "\""+ value + "\"" + " in the input line.");
							}

							if(datetype.equals("j")) { //checking if date is a Jewish date type
								JewishDate date1 = new JewishDate(date);
								if((date1.getDay() == -1) || (date1.getMonth() == -1) || (date1.getYear() == -1)) { //checking if the date entered correctly
									throw new InputDateException("Error message = Exception in scanning the date string");
								}
								Time time1 = new Time(time);
								arr[i] = new DateTime(date1, time1);
								map.get(DateUtils.DateType.JEWISH).add(arr[i]);
							}

							if(datetype.equals("g")) { //checking if date is a Gregorian date
								GregorianDate date1 = new GregorianDate(date);
								if((date1.getDay() == -1) || (date1.getMonth() == -1) || (date1.getYear() == -1)) { //checking if the date entered correctly
									throw new InputDateException("Error message = Exception in scanning the date string");
								}
								Time time1 = new Time(time); // Initializing Time instance
								arr[i] = new DateTime(date1, time1);
								map.get(DateUtils.DateType.GREGORIAN).add(arr[i]);
							}
							list.add(arr[i]);
							set.add(arr[i]);
							i++;
							break;	
						}//end of inner while.
						row.close();
					}

					else if(line.length() > 3) { 
						if((line.charAt(0) != 'j' || line.charAt(0) != 'g') && (line.charAt(1) == '#' || line.charAt(1) == '#')) { //checking if the type date entered is wrong- such as x#
							throw new InputLineException("Error message =  The type of the shape should be j(jewish) or g(gregorian) and not "+ line.charAt(0) + ".");
						}
						else {
							throw new InputLineException("Error message = Not enough parameters in the input line.");
						}
					}
					
					else {
						throw new InputLineException("Error message = Not enough parameters in the input line.");
					}
				}//end 2st try

				//error handling and writing to file
				catch(InputDateException ide) {
					wrIDE.write("line number=" + lineCounter + ", " + "input line=" + line + ", " + ide.getMessage() + "\n");
					wrIDE.flush();
				}
				catch(InputTimeException ite) {
					wrITE.write("line number=" + lineCounter + ", " + "input line=" + line + ", Error message = " + ite.getMessage() + "\n");
					wrITE.flush();
				}
				catch(InputLineException ile) {
					wrILE.write("line number=" + lineCounter + ", " + "input line=" + line + ", " + ile.getMessage() + "\n");
					wrILE.flush();
				}

				lineCounter++;
			}//end of outer while
			wrILE.close();
			wrIDE.close();
			wrITE.close();
			scan.close();

			//write to the collections files 
			for (DateTime date : arr) { //arr - "datesOutArr.txt"
				if (date==null) break;
				if(date == arr[0]) {
					wrDOA1.write(date.toString());
				}
				else {
					wrDOA1.write("\n" + date.toString());
				}
			}
			wrDOA1.flush();
			wrDOA1.close();
			
			Collections.reverse(list); //reversing the list as requested.
			for(DateTime date : list) { //list - "datesOutList.txt"
				if(date == list.get(0)) {
					wrDOL1.write(date.toString());
				}
				else {
					wrDOL1.write("\n" + date.toString());
				}
			}
			wrDOL1.flush();
			wrDOL1.close();

			Arrays.sort(arr, new SetCompartor());
			for(DateTime date : arr) { //sorted arr - "datesSortOutArr.txt"
				if (date==null) break;
				if(date == arr[0]) {
					wrDSOA1.write(date.toString());
				}
				else {
					wrDSOA1.write("\n" + date.toString());
				}
			}
			wrDSOA1.flush();
			wrDSOA1.close();

			list.sort(new SetCompartor());
			for(DateTime date : list) { //sorted list - "datesSortOutList.txt"
				if(date == list.get(0)) {
					wrDSOL1.write(date.toString());
				}
				else {
					wrDSOL1.write("\n" + date.toString());
				}
			}
			wrDSOL1.flush();
			wrDSOL1.close();

			for(DateTime date : set) { //set - "datesSortOutSet.txt"
				if(date == (set.toArray()[0])) {
					wrDSOS1.write(date.toString());
				}
				else {
					wrDSOS1.write("\n" + date.toString());
				}
			}
			wrDSOS1.flush();
			wrDSOS1.close();
			
			int index = 0;
			for(Set<DateTime> setDatesType : map.values()) { //map - "datesSortOutMap.txt"
				for (DateTime date : setDatesType) {
					if(index == 0) {
						wrDSOM1.write(date.toString());
					}
					else {
						wrDSOM1.write("\n" + date.toString());
					}
					index++;
				}
			}
			wrDSOM1.flush();
			wrDSOM1.close();
		} //end try 1

		catch(FileNotFoundException fnfe) { // in case there is no file as args[0]
			System.err.println("Error, File not found exception.");
		}
	}
}
