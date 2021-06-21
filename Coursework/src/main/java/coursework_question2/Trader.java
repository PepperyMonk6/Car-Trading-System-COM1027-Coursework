package coursework_question2;
import java.util.HashMap;
import java.util.Map;
public class Trader {
		protected String name;
		protected Map<Advert, User> carsForSale;
		protected Map<Advert, User> soldCars;
		protected Map<Advert, User> unsoldCars;
		
		public Trader(String name) {
			this.name = name;
			this.carsForSale = new HashMap<Advert, User>();
			this.soldCars = new HashMap<Advert, User>();
			this.unsoldCars = new HashMap<Advert, User>();
		}
		
		public String getName() {
			return this.name;
		}
		
		private boolean checkExistence(Car car) {
			for(Map.Entry<Advert, User> item: this.carsForSale.entrySet()) {
				if(item.getKey().getCar() == car) {
					return true;
				}
			}
			return false;
		}
		
		public String displaySoldCars() {
			String result = "SOLD CARS:\n";
			for(Map.Entry<Advert, User> item: soldCars.entrySet()) {
				Advert advert = item.getKey();
				User user = item.getValue();
				Car car = advert.getCar();
				Offer offer = advert.getHighestOffer();
				result += car.getID() + " - Purchased by " + user.getName() + " with a successful £" + 
						String.format("%.2f", offer.getValue()) + " bid. \n";
			}
			
			return result.replaceAll(",", ".");
		}
		
		public String displayStatistics() {
			return "Statistics";
		}
		
		public String displayUnsoldCars() {
			String result = "UNSOLD CARS:\n";
			for(Map.Entry<Advert, User> item: unsoldCars.entrySet()) {
				Advert advert = item.getKey();
				User user = item.getValue();
				Car car = advert.getCar();
				Offer offer = advert.getHighestOffer();
				result += "Ad: " + car.displayCarSpecification() + "\n";
			}
			return result.replace(",", ".");
		}
		
		public void endSale(Advert advert) {
			if(advert == null) {
				throw new IllegalArgumentException();
			}
			if(advert.getHighestOffer().getValue() >= advert.getCar().getPrice()) {
				soldCars.put(advert, advert.getHighestOffer().getBuyer());
				carsForSale.remove(advert);
			}	else {
				unsoldCars.put(advert, advert.getHighestOffer().getBuyer());
				carsForSale.remove(advert);
			}
		}
		
		public boolean placeOffer(Advert carAdvert, User user, double value) {
			if(carAdvert == null || user == null) {
				throw new IllegalArgumentException();
			}
			if(carsForSale.containsKey(carAdvert)) {
					if(carAdvert.placeOffer(user, value)) {
						this.endSale(carAdvert);
						return  true;
					}
					return false;
			}
			return true;
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
					carsForSale.put(advert, user);
				}
			}
		}
	}
