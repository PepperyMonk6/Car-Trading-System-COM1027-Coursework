package coursework_question4;

import java.util.HashMap;
import java.util.Map;

public abstract class Dealership {
	protected String name;
	protected Map<Advert, Seller> carsForSale;
	protected Map<Advert, Buyer> soldCars;
	protected Map<Advert, Seller> unsoldCars;
	
	public Dealership(String name) {
		super();
		this.name = name;
		this.carsForSale = new HashMap<Advert, Seller>();
		this.soldCars = new HashMap<Advert, Buyer>();
		this.unsoldCars = new HashMap<Advert, Seller>();
	}
	
	public String getName() {
		return this.name;
	}
	
	abstract public String displaySoldCars();
	
	abstract public String displayStatistics();
	
	abstract public String displayUnsoldCars();
	
	abstract public boolean placeOffer(Advert carAdvert, User user, double value);
	
	abstract public void registerCar(Advert advert, User user, String colour, CarType type, CarBody body, int noOfSeats);
}
