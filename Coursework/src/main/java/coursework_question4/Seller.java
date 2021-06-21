package coursework_question4;

public class Seller extends User {
	public int sales;
	
	public Seller(String fullname) {
		super(fullname);
		this.sales = 0;
	}
	
	public Seller(String fullname, int sales) {
		super(fullname);
		this.sales = sales;
	}
	
	public int getNumberOfSales() {
		return this.sales;
	}
	
	public void setNumberOfSales(int sales) {
		this.sales = sales;
	}
	
	public String identifyRating() {
		if(this.sales == 0) {
			return "Level 0";
		}	else if(this.sales <= 5 && this.sales > 0) {
			return "Level 1";
		}	else if(this.sales >= 6 && this.sales <= 10) {
			return "Level 2";
		}	
		return "Level 3";
	}	
	
	@Override
	public String toString() {
		String result = "";
		String[] line = this.getFullName().split(" ");
		return result += this.getName() + " " + line[1].charAt(0) + ". (Sales: " + this.sales + ", Rating: "
				+ this.identifyRating() + ")";
	}

	@Override
	public String getName() {
		String[] arrOfStr = this.getFullName().split(" ", 2);
		return arrOfStr[0];
	}
}
