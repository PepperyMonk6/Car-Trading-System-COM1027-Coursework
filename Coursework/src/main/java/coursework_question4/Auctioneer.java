package coursework_question4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Auctioneer extends Dealership{
	private Map<Seller, Integer> sales;
	private Seller topSeller;
	
	public Auctioneer(String name) {
		super(name);
		sales = new HashMap<Seller, Integer>();
	}
	
	//Method loops through the carsForSale hash map and check existence of a specific car element
	private boolean checkExistence(Car car) {
		for(Map.Entry<Advert, Seller> item: this.carsForSale.entrySet()) {
			if(item.getKey().getCar() == car) {
				return true;
			}
		}
		return false;
	}
	
	/* Method checks whether the seller has the biggest number of sales in comparison with all other sellers
	 * if yes, then it will be described as a topSeller. Otherwise, it will be just added to the sales hash map
	 *  with a provided parameter
	 */
	private void updateStatistics(Seller seller) {
		for(Map.Entry<Seller, Integer> item1: this.sales.entrySet()) {
			if(item1.getValue() < seller.getNumberOfSales()) {
				this.topSeller = seller;
			}
		}
		if(this.topSeller == null) {
			this.topSeller = seller;
		}
		this.sales.put(seller, seller.getNumberOfSales());
	}
	
	//Method saves all necessary information to the text file
	private void saveInFile(int noOfSales, double persentageOfUsed, double percentageOfNew) {
		double Automatic = 0;
		double Manual = 0;
		double percentage1 = 0;
		for(Map.Entry<Advert, Buyer> item1: soldCars.entrySet()) {
			if(item1.getKey().getCar().getGearbox() == CarType.AUTOMATIC) {
				Automatic++;
			}	else {
				Manual++;
			}
		}
		percentage1 = Automatic / soldCars.size() * 100;
		
		try {
		BufferedWriter aWriter = new BufferedWriter(new FileWriter("auction_statistics.txt"));
			aWriter.write("** Auctioneer - " + this.name + "**\n");
			aWriter.write("Total Auction Sales: " + this.soldCars.size() + "	 Automatic Cars: "
					+ percentage1 + "%	 Manual Cars: " + Manual / this.soldCars.size() *
					100 + "%\n");
			aWriter.write("Top Seller: " + this.topSeller + "\n");
			aWriter.close();
	}
	catch (IOException e) {
		e.printStackTrace();
	}
	}
	
	//Method displays sold cars in a specific format
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
	
	//Method displays statistics in a specific format with calculated number of Automatic and Manual cars
	public String displayStatistics() {
		this.saveInFile(this.topSeller.getNumberOfSales(), 0, 0);
		double Automatic = 0;
		double Manual = 0;
		double percentage1 = 0;
		for(Map.Entry<Advert, Buyer> item1: soldCars.entrySet()) {
			if(item1.getKey().getCar().getGearbox() == CarType.AUTOMATIC) {
				Automatic++;
			}	else {
				Manual++;
			}
		}
		percentage1 = Automatic / soldCars.size() * 100;
		return "** Auctioneer - " + this.name + "**\nTotal Auction Sales: " + this.soldCars.size() + "	 Automatic Cars: "
				+ percentage1 + "%	 Manual Cars: " + Manual / this.soldCars.size() *
				100 + "%\nTop Seller: " + this.topSeller + "\n";
	}
	
	//Method displays unsold cars with a specific format
	public String displayUnsoldCars() {
		String result = "UNSOLD CARS:\n";
		for(Map.Entry<Advert, Seller> item: unsoldCars.entrySet()) {
			Advert advert = item.getKey();
			Car car = advert.getCar();
			result += "Ad: " + car.displayCarSpecification() + "\n";
		}
		return result.replace(",", ".");
	}
	
	/* Method checks whether the highest offer >= the price of the car and then puts specific element to
	 * soldCars or unsoldCars and removes it form carsFarSale in both cases. In addition here it also increases
	 * the number of sales of a specific seller
	 */
	public void endSale(Advert advert) {
		if(advert == null) {
			throw new IllegalArgumentException();
		}
		if(advert.getHighestOffer().getValue() >= advert.getCar().getPrice()) {
			soldCars.put(advert, advert.getHighestOffer().getBuyer());
			Seller seller = carsForSale.get(advert);
			seller.setNumberOfSales(seller.getNumberOfSales() + 1);
			sales.put(seller, seller.getNumberOfSales());
			carsForSale.remove(advert);
		}	else {
			unsoldCars.put(advert, carsForSale.get(advert));
			carsForSale.remove(advert);
		}
	}
	
	//Method places an offer if the required carAdvert exits in carsForSale hash map
	public boolean placeOffer(Advert carAdvert, User user, double value) {
		if(carAdvert == null || user == null) {
			throw new IllegalArgumentException();
		}
		if(carsForSale.containsKey(carAdvert)) {
			return carAdvert.placeOffer(user, value);
		}
		return false;
	}
	
	/* Method registers a car if it is not already exist within the carsForSale hash map with all necessary 
	 * information about it
	 */
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
			if(!carsForSale.containsKey(advert)) {
				carsForSale.put(advert, (Seller) user); //type change
				this.updateStatistics((Seller) user);	//type change
			}
		}
	}
}
