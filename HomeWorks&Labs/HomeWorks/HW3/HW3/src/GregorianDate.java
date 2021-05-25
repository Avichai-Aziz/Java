import java.util.Scanner;

public class GregorianDate extends MyDate implements IDate {

	/** @param day - represents the day of the date
	 * @param month - represents the month of the date
	 * @param year - represents the year of the date
	 */
	private short day;
	private short month;
	private short year;
	private static final String[] GregorianMonths = { "Jan.", "Feb.", "Mar.", "Apr.", "May.", "Jun.", "Jul.", "Aug.",
			"Sep.", "Oct.", "Nov.", "Dec."};

	//constructors
	public GregorianDate (int day, int month, int year) {
		this.setYear((short) year);
		this.setMonth((short) month);
		this.setDay((short) day);
	}

	public GregorianDate(GregorianDate date) {
		this.setYear(date.getYear());
		this.setMonth(date.getMonth());
		this.setDay(date.getDay());
	}
	/** @param date String variable with the pattern of dd/mm/yyyy
	 * and creates an IDate instance with 
	 * dd - as day mm - as month yy - as year
	 */
	public GregorianDate(String date) { 
		Scanner scanDate = new Scanner(date).useDelimiter("/");
		String d = null, m = null, y = null;
		if(scanDate.hasNext()) {
			d = scanDate.next();
		}
		if(scanDate.hasNext()) {
			m = scanDate.next();
		}
		if(scanDate.hasNext()) {
			y = scanDate.next();
		}
		this.setYear(Short.parseShort(y));
		this.setMonth(Short.parseShort(m));
		this.setDay(Short.parseShort(d));

		scanDate.close();	
	}

	/**
	 *  @day - setting the day of the date
	 *  setting it to -1 in case of wrong/false input
	 * 
	 */
	@Override
	public void setDay(short day) {
		if((day <= 0) || (day > DateUtils.getLastDayOfGregorianMonth((int)this.month,(int)this.year))) {
			this.day = -1;
		}
		else {
			this.day = day;
		}
	}

	/**
	 *  @month - setting the month of the date
	 *  setting it to -1 in case of wrong/false input
	 * 
	 */

	@Override
	public void setMonth(short month) {
		if((month < 1) || (month > 12)) {
			this.month = -1;
		}
		else {
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
		if(year < 0) {
			this.year = -1;	
		}
		else {
			this.year = year;
		}
	}
	/**
	 * @return the day - an integer number between 1 to 28-31.
	 */

	@Override
	public short getDay() {
		return this.day;
	}
	
	/**
	 * @return the month - an integer number between 1 to 12.
	 */

	@Override
	public short getMonth() {
		return this.month;
	}
	/**
	 * @return the year - an integer number between 1 to xxxx. ?
	 */

	@Override
	public short getYear() {
		return this.year;
	}
	
	/**
	* @return the number of days from the first day in the Gregorian calendar
	*/
	
	@Override
	public int numberOfDaysFromFirstDayGregorian() { 
		return DateUtils.absoluteFromGregorianDate(this);

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
	 *@return the type of the date (e.g. Gregorian) 
	 */
	@Override
	public DateUtils.DateType getDateCalendar() { 
		return DateUtils.DateType.GREGORIAN;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Gregorian date ");
		sb.append(String.format("%02d",  this.getDay()));
		sb.append(" ");
		sb.append(String.format("%s", GregorianDate.GregorianMonths[this.getMonth()-1]));
		sb.append(" ");
		sb.append(String.format("%d", this.getYear()));
		return sb.toString();
	}
}
