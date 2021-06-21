package coursework_question4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Trader extends Dealership {
	private List<Seller> seller;
		
	public Trader(String name) {
		super(name);
		seller = new ArrayList<Seller>();
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
	
	//updates statistics(adds an element to the seller list)
	private void updateStatistics(Seller seller) {
		this.seller.add(seller);
	}
	
	//method saves all necessary information to the text file
	private void saveInFile(int noOfSales) {
		List<String> str = new ArrayList<String>();
		for(Seller sel: this.seller) {
			str.add(sel.toString());
		}
		Collections.sort(str);
		
		try {
			BufferedWriter tWriter = new BufferedWriter(new FileWriter("trade_statistics.txt"));
				tWriter.write("** Trader - " + this.name + "**\nTotal Sales: " + soldCars.size() + "\nAll Sellers:\n");
				for(String sel: str) {
					tWriter.write("\t" + sel + "\n");
				}
				tWriter.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//method displays sold cars in a specific format
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
	
	//method displays statistics in a specific format with alphabetically sorted elements
	public String displayStatistics() {
		this.saveInFile(0);
		String result = "** Trader - " + this.name + "**\nTotal Sales: " + soldCars.size() + "\nAll Sellers:\n";
		List<String> str = new ArrayList<String>();
		for(Seller sel: this.seller) {
			str.add(sel.toString());
		}
		Collections.sort(str);
		for(String sel: str) {
			result += "\t" + sel + "\n";
		}
		return result;
	}
	
	//method displays unsold cars with a specific format
	public String displayUnsoldCars() {
		String result = "UNSOLD CARS:\n";
		for(Map.Entry<Advert, Seller> item: unsoldCars.entrySet()) {
			Advert advert = item.getKey();
			Car car = advert.getCar();
			result += "Ad: " + car.displayCarSpecification() + "\n";
		}
		return result.replace(",", ".");
	}
	
	/* Method puts an element to the soldCars hash map and removes it from carsForSale hash map. 
	 * In addition, method increases a number of sales of a specific seller and adds the seller to 
	 * a seller list if this element does not already exist.
	 */
	public void endSale(Advert advert) {
		if(advert == null) {
			throw new IllegalArgumentException();
		}
			soldCars.put(advert, advert.getHighestOffer().getBuyer());
			Seller seller = carsForSale.get(advert);
			seller.setNumberOfSales(seller.getNumberOfSales() + 1);
			if(!this.seller.contains(seller)) {
				this.updateStatistics(seller);
			}
			carsForSale.remove(advert);
	}
	
	/* Offer can be placed only if the value is more then already existing offer. If it happens, then the sale
	 * ends with all necessary actions.
	 */
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
	
	/* Method registers a car with all necessary information if it is not already exist within the carsForSale 
	 * hash map and if the type is FORSALE
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
			if((!carsForSale.containsKey(advert)) && (advert.getCar().getType() == SaleType.FORSALE)) {
				carsForSale.put(advert, (Seller) user);	//type change
			}
		}
	}
}
