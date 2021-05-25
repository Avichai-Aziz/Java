import java.util.Queue;

/**
 * Teller - represents a bank clerk
 * 
 */
public class Teller extends Thread {

	private int tellerNumber; //the id of the teller
	private Bank bank;
	private Queue<Customer> customerQueue;
	private boolean isServing; // will be flagged |true| if the Teller is working.
	private boolean isIdle; // will be flagged |false| if the teller is working.
	private int custNumber; //the is of the last (current) served customer 
	private int customersServed; //the number of the customers which are served by this teller
	private double tellerIdleMean;
	private double tellerIdleVar;

	public Teller(Queue<Customer> customerQueue, Bank bank, int tellerNumber, double tellerIdleMean, double tellerIdleVar){
		this.customerQueue = bank.getCustomersQueue();
		this.bank = bank;
		this.customersServed = 0;
		this.tellerNumber = tellerNumber;
		this.tellerIdleMean = tellerIdleMean;
		this.tellerIdleVar = tellerIdleVar;
	}

	/*
	 * Getters and setters
	 */
	public Customer getCustomer() { 
		Customer c = null;
		synchronized (customerQueue) {
			if(!(customerQueue.isEmpty()) || (this.bank.getClock().isWorking())) {
				c = customerQueue.poll();
			}
		}
		return c;
	}

	public int getCustNumber() {
		return custNumber;
	}

	public int getTellerNumber() {
		return tellerNumber;
	}

	public boolean isServing() {return  isServing;}

	public boolean isIdle() { return  isIdle;}

	public void goIdle() { 
		if(this.isServing) {
			this.isIdle = false;
		}
		this.isIdle = true;
	}
	/**
	 * run - main thread action
	 **/
	public void run() {
		while(bank.getClock().isWorking() || (!(customerQueue.isEmpty()))) {
			if(customerQueue.isEmpty()) {
				synchronized(customerQueue) {
					try {
						customerQueue.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if(!(customerQueue.isEmpty()))  // checking if the queue is not empty
			{
				this.isIdle = false;
				Customer currentServedCustomer = this.getCustomer();
				if(currentServedCustomer != null) {	
					this.custNumber = currentServedCustomer.getCustNumber();
					this.isServing = true;
					currentServedCustomer.serve();
					customersServed++;
				}
			}
			if(isIdle()) {
				try {
					Teller.sleep(Bank.gaussian(tellerIdleMean, tellerIdleVar)*Bank.TIME_SIMULATION_FACTOR);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			this.isServing = false;
		}
		synchronized(this.bank) {
			this.bank.notify();
		}
	}
}
