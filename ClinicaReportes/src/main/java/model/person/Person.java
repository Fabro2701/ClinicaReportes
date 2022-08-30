package model.person;

public class Person {
	protected String fullname;
	public Person(String fullname) {
		this.fullname = fullname;
	}
	public String toString() {
		return "FullName: "+fullname;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
}
