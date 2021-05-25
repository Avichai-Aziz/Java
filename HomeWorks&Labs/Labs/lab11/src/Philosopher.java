import java.util.List;
import java.util.concurrent.locks.Lock;

public class Philosopher implements IPhilosopher{
	
	private int count; //count the numbers of meals the philosopher eat
	private int place;
	private int meals;
	private List<Fork> forks;
	private boolean isEating;
	private Lock lock;
	private RandomSleep randomSleep;
	
	public Philosopher(int place, List<Fork> forks, int meals, Lock lock) {
		this.count = 0;
		this.place = place;
		this.meals = meals;
		this.forks = forks;
		this.isEating = false;
		this.lock = lock;
		this.randomSleep = new RandomSleep(this.place);
	}
	
	@Override
	public void run() {
		Fork leftFork = forks.get((place) % (forks.size()));
		Fork rightFork = forks.get((place+1) % (forks.size()));
		
		while(count < meals) {
			randomSleep.sleep(); //the philosopher is thinking
			
			lock.lock(); //lock the opportunity that all the philosophers lift one fork and no one will have 2 forks 
			synchronized (rightFork) { //promising that no other philosopher will lift this specific right fork
				rightFork.setOwner(place);
				
				synchronized (leftFork) { //promising that no other philosopher will lift this specific right fork
					leftFork.setOwner(place);
					lock.unlock(); //let the other philosophers in the table to lift 2 forks 
					//after lifting 2 forks the philosopher can start eating
					isEating = true; //the philosopher is eating
					count++;
					randomSleep.sleep(); //the time philosopher is eating
					isEating = false; //the philosopher done to eat
					leftFork.setOwner(0); //set the left fork to none philosopher
				}
				rightFork.setOwner(0); //set the right fork to none philosopher
			}
		}
	}

	@Override
	public boolean isEating() {
		return isEating;
	}

	@Override
	public int getPlace() {
		return this.place;
	}

	@Override
	public int getCount() {
		return this.count;
	}
}
