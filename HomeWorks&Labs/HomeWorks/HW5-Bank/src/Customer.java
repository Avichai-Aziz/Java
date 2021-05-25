import java.util.Queue;

/**
 * Customer - represents a client of the bank.
 * 
 */
public class Customer extends Thread {

	private int custNumber; //the id of the customer
	private Bank bank;
	private int serviceTime;
	public enum serviceStatus{WAITING, INSERVICE, DONE};
	private serviceStatus service;

	public Customer(int serviceTime, Bank bank, int custNumber){
		this.service = serviceStatus.WAITING;
		this.serviceTime = serviceTime;
		this.bank = bank;
		this.custNumber = custNumber;
	}


	/*
	 * Getters and setters
	 */
	public int getCustNumber() {
		return custNumber;
	}

	/**
	 * serve - simulate the service the customer is getting. This method is called
	 * only by the teller servicing this customer. In addition to holding up the
	 * teller for the duration of the service, it also notifies this customer's
	 * thread that it has been serviced and therefore may terminate.
	 */
	public synchronized int serve() {
		try {
			this.service = serviceStatus.INSERVICE;
			sleep((serviceTime));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.service = serviceStatus.DONE;
		notifyAll();
		return this.getServiceTime();
	}

	/**
	 * run - main thread action
	 */
	public void run() {
		synchronized (this) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int getServiceTime() {
		return this.serviceTime;
	}

	/**
	 * serve - simulate the service the customer is getting. This method is called
	 * only by the teller servicing this customer. In addition to holding up the
	 * teller for the duration of the service, it also notifies this customer's
	 * thread that it has been serviced and therefore may terminate.
	 */


	@Override
	public String toString() {
		return String.valueOf(custNumber);
	}


} /* class Customer */
