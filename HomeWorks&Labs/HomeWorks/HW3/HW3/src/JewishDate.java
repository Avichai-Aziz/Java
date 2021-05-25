
import java.util.Scanner;

public class JewishDate extends MyDate implements IDate{
	/** @param day - represents the day of the date
	 * @param month - represents the month of the date
	 * @param year - represents the year of the date
	 */
	private final String[] JewishMonts  = {"Nisan", "Iyar", "Sivan", "Tamuz", "Av", "Elul", "Tishrei", "Heshvan", "Kislev", "Tevet", "Shvat", "Adar"};
	private final String[] JewishMontsLeaped  = {"Nisan", "Iyar", "Sivan", "Tamuz", "Av", "Elul", "Tishrei", "Heshvan", "Kislev", "Tevet", "Shvat", "Adar A","Adar B"};
	private int year;
	private int month;
	private int day;
	private boolean leapedyear;
	
	public JewishDate (int day, int month, int year) {
		this.setYear((short) year);
		this.leapedyear = DateUtils.hebrewLeapYear(year);
		this.setMonth((short) month);
		this.setDay((short) day);
	}

	public JewishDate(JewishDate date) {
		this.setYear(date.getYear()); 
		this.leapedyear = DateUtils.hebrewLeapYear(date.getYear());
		this.setMonth(date.getMonth());
		this.setDay(date.getDay());
	}
	/** @param date String variable with the pattern of dd/mm/yyyy
	 * and creates an IDate instance with 
	 * dd - as day mm - as month yy - as year
	 */

	public JewishDate(String date) {
		Scanner scan = new Scanner(date).useDelimiter("/");
		int d=0,m=0,y = 0;
		if(scan.hasNext()) {
			d = Integer.parseInt(scan.next());
		}
		if(scan.hasNext()) {
			m = Integer.parseInt(scan.next());
		}
		if(scan.hasNext()) {
			y = Integer.parseInt(scan.next());
			}
		this.setYear((short) y);
		this.leapedyear = DateUtils.hebrewLeapYear(this.year);
		this.setMonth((short) m);
		this.setDay((short) d);

		scan.close();
	}
	/**
	 *  @day - setting the day of the date
	 *  setting it to -1 in case of wrong/fasle input
	 * 
	 */
	@Override
	public void setDay(short day) {
		if((day <= 0) || (day > (DateUtils.getLastDayOfJewishMonth(this.getMonth(), this.getYear())))){
		this.day = -1;
		}
		else {
			this.day = day; // in order to throw exception in main.
		}
	}
	/**
	 *  @month - setting the month of the date
	 *  setting it to -1 in case of wrong/false input
	 * 
	 */
	@Override
	public void setMonth(short month) {
		if((month < 1) || (month > DateUtils.getLastMonthOfJewishYear(this.getYear()))) {
		this.month = -1;
		}
		else {
			if(month == 13) {
				this.leapedyear = true;
			}
			this.month = month;
		}
	}
	/**
	 *  @year - setting the year of the date
	 *  setting it to -1 in case of wrong/false input
	 * 
	 */

	@Override
	public void setYear(short year) {
		if(year < 0) 
		{
			this.year = -1;
		}
		else{
			leapedyear = DateUtils.hebrewLeapYear(year);
			this.year = year;
			
		}
	}
	@Override
	/**
	 * @return the day - an integer number between 1 to 28-31.
	 */

	public short getDay() {
		return (short) this.day;
	}
	/**
	 * @return the month - an integer number between 1 to 13.
	 */
	@Override
	public short getMonth() {
		return (short) this.month;
	}
	/**
	 * @return the year - an integer number > 1
	 */
	@Override
	public short getYear() {
		return (short) this.year;
	}
	/**
	* @return the number of days from the first day in the Gregorian calendar
	*/
	
	@Override
	public int numberOfDaysFromFirstDayGregorian() {
		return DateUtils.absoluteFromJewishDate(this);
	}
	/**
	 * @param t
	 * @return return true if this instance is after t. Otherwise return false If t
	 *         is null return false
	 */
	@Override
	public boolean after(IDate t) {
		return super.after(t);
	}
	/**
	 * @param t
	 * @return return true if this instance is before t. Otherwise return false If t
	 *         is null return false
	 */
	@Override
	public boolean before(IDate t) {
		return super.before(t);
	}
	/**
	 * @param t
	 * @return return true if this instance represents the same time as t. Otherwise
	 *         return false. If t is null return false
	 */
	@Override
	public boolean equals(IDate t) {
		return super.equals(t);
	}
	/**
	 *@return the type of the date (e.g. Jewish) 
	 */

	@Override
	public DateUtils.DateType getDateCalendar() {
		return DateUtils.DateType.JEWISH;
	}

	@Override
	public String toString() {
		if(this.leapedyear) {
			return String.format("Jewish date %02d %s %d" ,this.getDay(),this.JewishMontsLeaped[this.getMonth()-1],this.getYear());
		}
		return String.format("Jewish date %02d %s %d" ,this.getDay(),this.JewishMonts[this.getMonth()-1],this.getYear());
	}
	

}
