
public class Student {
	private String name;
	private String id;
	private int year;
	private double avg;
	
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}
	public int getYear() {
		return year;
	}
	public double getAvg() {
		return avg;
	}
	
	

	public void setName(String name) {
		this.name = name;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public Student (String name, String id, int year, double avg) {
		this.name = name;
		this.id = id;
		this.year = year;
		this.avg = avg;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", id=" + id + ", year=" + year + ", avg=" + avg + "]";
	}
	
	
	

}
