
public class Fork {
	private int owner;
	
	public Fork() {
		this.owner = 0;
	}

	public Fork(int owner) {
		this.owner = owner;
	}
	
	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		if(owner >= 0 && owner <= 5) {
			this.owner = owner;
		}
	}
	
}
