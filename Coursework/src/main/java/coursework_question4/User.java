package coursework_question4;

public abstract class User {
	private String fullname;
	final static String regExp = "[A-Z][a-z]* [A-Z][a-z]*";
	
	public User(String fullname) throws IllegalArgumentException {
		super();
		if(!fullname.matches(regExp)) {
			throw new IllegalArgumentException();
		}
		this.fullname = fullname;
	}
	
	public String getFullName() {
		return this.fullname;
	}
	
	abstract public String getName();
	
	@Override
	abstract public String toString();
}
