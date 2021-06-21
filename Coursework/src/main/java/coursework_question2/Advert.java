package coursework_question2;

import java.util.ArrayList;
import java.util.List;

public class Advert {
	private Car car;
	private List<Offer> offers;
	
	public Advert(Car car) {
		this.car = car;
		offers = new ArrayList<Offer>();
	}
	
	public Car getCar() {
		return this.car;
	}
	
	public List<Offer> getOffers() {
		return this.offers;
	}
	
	public Offer getHighestOffer() {
		int highestOffer = 0;
		double highestValue = 0;
		for(int i = 0; i < offers.size(); i++) {
			if(highestValue < offers.get(i).getValue()) {
				highestValue = offers.get(i).getValue();
				highestOffer = i;
			}
		}
		return offers.get(highestOffer);
	}
	
	public boolean placeOffer(User buyer, double value) {
		Offer offer = new Offer(buyer, value);
			if(!offers.contains(offer)) {
				offers.add(offer);
				return true;
			}
			return false;
		}
	
	@Override
	public String toString() {
		String result = "";
		result += "Ad: " + this.car.getID() + " - " + this.car.getName() + " (£" + String.format("%.2f", this.car.getPrice()) +
				")\n\tType: " + this.car.getGearbox() + "\n\tStyle: " + this.car.getBodyStyle() + "\n\tColour: " + this.car.getColour() +
				"\n\tNo. of Seats: " + this.car.getNumberOfSeats() + "\n\tCondition: " + this.car.getCondition();
		return result.replaceAll(",", ".");
	}
}
