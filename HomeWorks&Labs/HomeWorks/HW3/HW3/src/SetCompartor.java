import java.util.Comparator;

public class SetCompartor implements Comparator<DateTime> {
	/**
	 * a Comparator for the @param DateTime.set
	 * comparing by chronological order
	 */
	@Override
	public int compare(DateTime dt1, DateTime dt2) {
		if(dt1 == null || dt2 == null) {
			return 0;
		}
		if(dt1.before(dt2)) {
			return -1;
		}
		else if(dt1.after(dt2)) {
			return 1;
		}
		else
			return 0;
	}
}
