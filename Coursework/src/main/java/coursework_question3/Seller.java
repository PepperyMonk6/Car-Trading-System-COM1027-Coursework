package coursework_question3;

public class Seller extends User {
	public Seller(String fullname) {
		super(fullname);
	}
	
	@Override
	public String toString() {
		String result = "";
		String[] line = this.getFullName().split(" ");
		return result += this.getName() + " " + line[1].charAt(0) + ". ()";
	}

	@Override
	public String getName() {
		String[] arrOfStr = this.getFullName().split(" ", 2);
		return arrOfStr[0];
	}
}
