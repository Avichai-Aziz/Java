
public abstract class MyDate implements IDate{

	public boolean equals(IDate t) { //checking if date t is equals to this date
		if(t == null) {
			return false;
		}
		return (t.numberOfDaysFromFirstDayGregorian() == this.numberOfDaysFromFirstDayGregorian());
	}

	public boolean before(IDate t) { //checking if this date is before date t 
		if(t == null) {
			return false;
		}
		return (this.numberOfDaysFromFirstDayGregorian() < t.numberOfDaysFromFirstDayGregorian());
	}

	public boolean after(IDate t) { //checking if this date is after date t  
		if(t == null) {
			return false;
		}
		return (this.numberOfDaysFromFirstDayGregorian() > t.numberOfDaysFromFirstDayGregorian());
	}
}
