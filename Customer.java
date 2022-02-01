
public class Customer {
	private String name;
	private String type;
	private double energy;
	private String month;
	private double bill;
	
	public Customer(String name, int type, double energy, int month) {
		this.name = name;
		this.energy = energy;
		
		// Convert month to string
		switch (month) {
		case 1:
			this.month = "Jan";
			break;
		case 2:
			this.month = "Feb";
			break;
		case 3:
			this.month = "Mar";
			break;
		case 4:
			this.month = "Apr";
			break;
		case 5:
			this.month = "May";
			break;
		case 6:
			this.month = "Jun";
			break;
		case 7:
			this.month = "Jul";
			break;
		case 8:
			this.month = "Aug";
			break;
		case 9:
			this.month = "Sep";
			break;
		case 10:
			this.month = "Oct";
			break;
		case 11:
			this.month = "Nov";
			break;
		case 12:
			this.month = "Dec";
			break;
		default:
			System.out.println("Special case, month[m] = " + month);
		}
		
		// Converts integer user input to usable string
		if (type == 0) {
			this.type = "Residential";
		} else {
			this.type = "Commercial";
		}
	}
	
	public void setBill(double bill) {
		this.bill = bill;
	}
	
	public double getBill() {
		return bill;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public double getEnergy() {
		return energy;
	}
	
	public String getMonth() {
		return month;
	}
}
