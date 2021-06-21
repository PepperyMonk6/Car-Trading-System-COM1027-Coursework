package coursework_question4;

public class Offer {
	private double value;
	private User buyer;
	
	public Offer(User buyer, double value) {
		if (value < 0) {
			throw new IllegalArgumentException();
		}
		if(buyer == null) {
			throw new IllegalArgumentException();
		}
		this.value = value;
		this.buyer = buyer;
	}
	
	public Buyer getBuyer() {
		return (Buyer) this.buyer;	//type change
	}
	
	public double getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		String result = "";
		return result += this.buyer + " offered " + "£" + this.value;
	}
}
