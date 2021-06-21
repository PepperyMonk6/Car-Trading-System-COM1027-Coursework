package coursework_question1;

public class User {
	private String fullname;
	String regExp = "[A-Z][a-z]* [A-Z][a-z]*";
	
	public User(String fullname) throws IllegalArgumentException {
		if(!fullname.matches(regExp)) {
			throw new IllegalArgumentException();
		}
		this.fullname = fullname;
	}
	
	public String getName() {
		String[] arrOfStr = this.fullname.split(" ", 2);
		return arrOfStr[0];
	}
	
	@Override
	public String toString() {
		String result = "";
		return result += this.fullname;
	}
}
