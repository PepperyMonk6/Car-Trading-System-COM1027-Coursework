package coursework_question3;

public class Buyer extends User {
	private int age;
	
	public Buyer(String fullname, int age) {
		super(fullname);
		if(age < 18) {
			throw new IllegalArgumentException();
		}
		this.age = age;
	}
	
	public int getAge() {
		return this.age;
	}
	
	@Override
	public String toString() {
		String result = "";
		return result += this.getName().charAt(0) + "***" + this.getName().charAt(this.getName().length() - 1);
	}

	@Override
	public String getName() {
		String[] arrOfStr = this.getFullName().split(" ", 2);
		return arrOfStr[0];
	}
}
