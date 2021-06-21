package coursework_question3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trader extends Dealership {
	private List<Seller> seller;
		
	public Trader(String name) {
		super(name);
		seller = new ArrayList<Seller>();
	}
		
	private boolean checkExistence(Car car) {
		for(Map.Entry<Advert, Seller> item: this.carsForSale.entrySet()) {
			if(item.getKey().getCar() == car) {
				return true;
			}
		}
		return false;
	}
	
	public String displaySoldCars() {
		String result = "SOLD CARS:\n";
		for(Map.Entry<Advert, Buyer> item: soldCars.entrySet()) {
			Advert advert = item.getKey();
			User user = item.getValue();
			Car car = advert.getCar();
			Offer offer = advert.getHighestOffer();
			result += car.getID() + " - Purchased by " + user.toString() + " with a successful £" + 
					String.format("%.2f", offer.getValue()) + " bid. \n";
		}
		
		return result.replaceAll(",", ".");
	}
	
	public String displayStatistics() {
		return "Statistics";
	}
	
	public String displayUnsoldCars() {
		String result = "UNSOLD CARS:\n";
		for(Map.Entry<Advert, Seller> item: unsoldCars.entrySet()) {
			Advert advert = item.getKey();
			Car car = advert.getCar();
			result += "Ad: " + car.displayCarSpecification() + "\n";
		}
		return result.replace(",", ".");
	}
	
	public void endSale(Advert advert) {
		if(advert == null) {
			throw new IllegalArgumentException();
		}
			soldCars.put(advert, advert.getHighestOffer().getBuyer());
			carsForSale.remove(advert);
	}
	
	public boolean placeOffer(Advert carAdvert, User user, double value) {
		if(carAdvert == null || user == null) {
			throw new IllegalArgumentException();
		}
		if(carsForSale.containsKey(carAdvert)) {
			if(carAdvert.getCar().getPrice() > value) {
				return false;
			}	else if(carAdvert.placeOffer(user, value)) {
					this.endSale(carAdvert);
					return  true;
				}
		}
		return false;
	}
	
	public void registerCar(Advert advert, User user, String colour, CarType type, CarBody body, int noOfSeats) {
		if(advert == null || user == null) {
			throw new IllegalArgumentException();
		}
		if(!this.checkExistence(advert.getCar())) {
			Car car = advert.getCar();
			car.setColour(colour);
			car.setBody(body);
			car.setGearbox(type);
			car.setNumberOfSeats(noOfSeats);
			if((!carsForSale.containsKey(advert)) && (advert.getCar().getType() == SaleType.FORSALE)) {
				carsForSale.put(advert, (Seller) user);
			}
		}
	}
}
