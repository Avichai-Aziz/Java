
/**
 *@author Asaf Ben Shabat
 *@author Avichai Aziz
 *
 * this class creates an instance with a given time & date
 */
public class DateTime {
	/** field:
	 * date - an IDate instance represents the date
	 * time - a Time instance represents the time
	 */
	private IDate date;
	private Time time;

	
	/**
	 * @param day - represents the day in the date integer between 1 - 31
	 * @param month - represents the month in the date integer between 1 - 12
	 * @param year - represents the year in the date integer  bigger than 1
	 * @param cal - represents the year type of the date Jewish / Gregorian
	 * @param hour - represents the hour integer between 0 - 23 for hour 
	 * @param min - represents the minutes an integer between 0-59
 	 * @param sec - represents the seconds an integer between 
	 */
	public DateTime(short day, short month, short year, DateUtils.DateType cal, short hour, short min, short sec) {
		if(cal == DateUtils.DateType.JEWISH) {
			this.date = new JewishDate(day, month, year);
		}
		else if(cal == DateUtils.DateType.GREGORIAN) {
			this.date = new GregorianDate(day, month, year);
		}
		this.time = new Time(hour, min, sec);
	}

	/**
	 * @param date - IDate date instance with day/month/year
	 * @param time - Time instance with hour/minutes/seconds
	 */
	public DateTime(IDate date, Time time){ 
		if(date.getDateCalendar() == DateUtils.DateType.JEWISH) {
			this.date = new JewishDate(date.getDay(),date.getMonth(),date.getYear());
		}
		else {
			this.date = new GregorianDate(date.getDay(),date.getMonth(),date.getYear());
		}
		this.time = new Time(time.getHour(),time.getMinute(),time.getSecond());
	}

	
	/**
	 * @param day 
	 * updates the day of the date instance
	 */
	public void setDay(short day) {
		this.date.setDay(day);
	}

	/**
	 * @param month
	 * 
	 * updates the month of the date instance
	 */
	public void setMonth(short month) {
		this.date.setMonth(month);
	}

	/**
	 * @param year
	 * 
	 * updates the year of the date instance
	 */
	public void setYear(short year) {
		this.date.setYear(year);
	}

	/**
	 * @return the day - an integer between 1 - 31
	 */
	public short getDay() {
		return this.date.getDay();
	}

	
	/**
	 * @return the month - an integer between 1 - 12
	 */
	public short getMonth() {
		return this.date.getMonth();
	}

	/**
	 * @return the year - an integer bigger than 1
	 */
	public short getYear() {
		return this.date.getYear();
	}

	/**
	 * @param hour 
	 * updates the hour of the time instance
 	 */
	public void setHour(short hour) {
		this.time.setHour(hour);
	}

	/**
	 * @param min
	 * 
	 * updates the minutes of the time instance
	 */
	public void setMinute(short min) {
		this.time.setMinute(min);
	}

	/**
	 * @param sec
	 * 
	 * updates the seconds of the time instance
	 */
	public void setSecond(short sec) {
		this.time.setSecond(sec);
	}

	/**
	 * @return the hour of the time instance an integer between 0-23
	 *
	 */
	public short getHour() {
		return this.time.getHour();
	}

	/**
	 * @return the minute of the hour instance an integer between 0-59
	 */
	public short getMinute() {
		return this.time.getMinute();
	}

	/**
	 * @return the seconds of the hour instance an integer between 0-59
	 */
	public short getSecond() {
		return this.time.getSecond();
	}

	/**
	 * @return the date type of the calendar - JEWISH/GREGORIAN
	 */
	public DateUtils.DateType getDateCalendar() {
		return this.date.getDateCalendar();
	}

	/**
	 * @param date - the date type of calendar - JEWISH/GREGORIAN
	 */
	public void setDate(IDate date) {
		if(date.getDateCalendar() == DateUtils.DateType.JEWISH) {
			this.date = new JewishDate(date.getDay(), date.getMonth(), date.getYear());
		}
		else if(date.getDateCalendar() == DateUtils.DateType.GREGORIAN) {
			this.date = new GregorianDate(date.getDay(), date.getMonth(), date.getYear());
		}
	}

	/**
	 * @param time - Time instance
	 * 
	 * updates the time instance
	 */
	public void setTime(Time time) {
		this.time = new Time(time.getHour(), time.getMinute(), time.getSecond());
	}

	/**
	 * @return - the IDate instance of this field
	 */
	public IDate getDate() {
		return this.date;
	}
	/**
	 * @return - the Time instance of this field
	 */
	public Time getTime() {
		return this.time;
	}
	
	/**
	 * @param dateTimeOther - a DateTime instance 
	 * @return return true if this instance represents the same date as dateTimeOther. Otherwise
	 *         return false.
	 */
	public boolean equals(DateTime dateTimeOther) {
		return ((this.date.equals(dateTimeOther.date)) && (this.time.equals(dateTimeOther.time)));
	}
	/**
	 * @param dateTimeOther - a DateTime instance 
	 * @return return true if this instance is before dateTimeOther. Otherwise return false 
	 *        
	 */
	public boolean before(DateTime dateTimeOther) {
		if(this.date.before(dateTimeOther.date)) { 
			return true;
		}
		else if(this.date.equals(dateTimeOther.date)) {
			if(this.time.before(dateTimeOther.time)) {
				return true;
			}
			return false;
		}
		return false;
	}
	/**
	 * @param dateTimeOther - a DateTime instance
	 * @return return true if this instance is after dateTimeOther. Otherwise return false 
	 *         
	 */
	public boolean after(DateTime dateTimeOther) {
		if(this.date.after(dateTimeOther.date)) {
			return true;
		}
		else if(this.date.equals(dateTimeOther.date)) {
			if(this.time.after(dateTimeOther.time)) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	/**
	 * returns a string with the pattern of date than time (IDate ,Time)
	 */
	@Override
	public String toString() {
		return this.date.toString() + ", " + this.time.toString();
	}
}
