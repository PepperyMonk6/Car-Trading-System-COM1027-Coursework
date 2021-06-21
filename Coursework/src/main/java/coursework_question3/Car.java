package coursework_question3;

public class Car {
	private int id;
	private String name;
	private String colour;
	private double reservedPrice;
	private CarType gearbox;
	private CarBody body;
	private int numberOfSeats;
	private Condition condition;
	private SaleType type;
	
	public Car(int id, String name, double reservedPrice, Condition condition, SaleType type) {
		if(id < 0) {
			throw new IllegalArgumentException();
		}
		if(name == null) {
			throw new IllegalArgumentException();
		}
		if(reservedPrice < 0) {
			throw new IllegalArgumentException();
		}
		this.id = id;
		this.name = name;
		this.reservedPrice = reservedPrice;
		this.condition = condition;
		this.type = type;
	}
	
	public int getID() {
		return this.id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getColour() {
		return this.colour;
	}
	
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	public double getPrice() {
		return this.reservedPrice;
	}
	
	public void setReservedPrice(double reservedPrice) {
		this.reservedPrice = reservedPrice;
	}
	
	public int getNumberOfSeats() {
		return this.numberOfSeats;
	}
	
	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	
	public CarType getGearbox() {
		return this.gearbox;
	}
	
	public void setGearbox(CarType gearbox) {
		this.gearbox = gearbox;
	}
	
	public CarBody getBodyStyle() {
		return this.body;
	}
	
	public void setBody(CarBody body) {
		this.body = body;
	}
	
	public Condition getCondition() {
		return this.condition;
	}
	
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
	public SaleType getType() {
		return this.type;
	}
	
	public String displayCarSpecification() {
		String result = "";
		result += this.id + " - " + this.name + " (£" + String.format("%.2f", this.reservedPrice) + ")\n\t Type: " + this.gearbox + 
				"\n\t Style: " + this.body + "\n\t Colour: " + this.colour + "\n\t No. of Seats: " + this.numberOfSeats +
				"\n\t Condition: " + this.condition;
		return result.replaceAll(",", ".");
	}
	
	@Override
	public String toString() {
		String result = "";
		return result += this.id + " - " + this.name;
	}
}
