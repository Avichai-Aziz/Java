/**
 * WorkingHours - a main clock regulating the bank's work day
 * 
 */
public class Clock extends Thread {
	
	private int dayLength; // minutes
	private boolean isWorking;
	
	public Clock(int dayLength){
		this.dayLength = dayLength;
		this.isWorking = true;
	}

	public void run() { // decrements the dayLength
		this.isWorking = true;
		while(dayLength>0){
			try {
				Clock.sleep(dayLength*Bank.TIME_SIMULATION_FACTOR);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.dayLength--;
		}
		this.isWorking = false;
	}

	// returns if the clock is ticking , which means the bank is working .
	public boolean isWorking() {
		return isWorking;
	}
}