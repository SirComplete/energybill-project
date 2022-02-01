import javax.swing.*;

public class EnergyBill {
	public static void main(String[] args) {
		boolean valid = false;
		
		int n = 0;
		while (!valid) {
			try {
				n = Integer.parseInt(JOptionPane.showInputDialog("How many customers are there?"));
				
				if (n < 1) throw new Exception("Number of customers must be greater than 0."); 
				
				valid = true;
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		String name;
		int type;
		double energy;
		int month;
		double bill = 0;
		
		final double RESIDENTIAL_BASE = 6.75;
		final double COMMERCIAL_BASE = 10.75;
		final double W_RES_RATE = 0.04604;
		final double W_COM_RATE = 0.03920;
		final double S_RES_RATE = 0.09;
		final double S_COM_RATE = 0.0645;
		
		String output = "Name\tType of Customer\tEnergy Used\tMonth\tBill Amount\n";
		
		// Get initial values
		for (int i = 1; i <= n; i++) {
			valid = false;
			
			while (!valid) {
				Customer c;
				
				try {
					name = JOptionPane.showInputDialog("What is customer #" + i + "'s full name?");
					if (name.isEmpty()) throw new Exception("Customer name cannot be empty.");
					for (char z : name.toCharArray()) {
						if (Character.isDigit(z)) throw new Exception("Customer name cannot contain numbers.");
					}
					
					String[] options = {"Residential", "Commercial"};
					type = JOptionPane.showOptionDialog(null, "What type of customer is " + name + "?", "Message", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					
					energy = Double.parseDouble(JOptionPane.showInputDialog("How much energy did was used in kWh?"));
					if (energy < 0) throw new Exception("Please enter a positive number.");
				
					month = Integer.parseInt(JOptionPane.showInputDialog("Which month is the bill to be generated for? Enter a number."));
					if (month < 1 || month > 12) throw new Exception("Please enter a number between 1 and 12.");
					
					c = new Customer(name, type, energy, month);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
					
					continue;
				} // end try catch
				
				// Calculate Bill
				if (month >= 10 || month <= 5) {
					// Winter months
					if (type == 0) {
						bill = RESIDENTIAL_BASE + (energy * W_RES_RATE);
					} else {
						bill = COMMERCIAL_BASE + (energy * W_COM_RATE);
					}
				} else if (month > 5) {
					// Summer months
					if (type == 0) {
						// Residential
						if (energy < 500) {
							bill = RESIDENTIAL_BASE + (energy * W_RES_RATE);
						} else {
							bill = RESIDENTIAL_BASE + (500 * W_RES_RATE) + ((energy - 500) * S_RES_RATE);
						}
					} else {
						// Commercial
						bill = COMMERCIAL_BASE + (energy * S_COM_RATE);
					}
				} // end if (determine season)
				c.setBill(bill);
					
				// Add contents to array
				output += c.getName() + "\t" + c.getType() + "\t\t" + c.getEnergy() + "\t" + c.getMonth() + "\t$" + String.format("%.2f", c.getBill()) + "\n";
				
				valid = true;
			} // end while
		} // end for
		
		// Output
		JOptionPane.showMessageDialog(null, new JTextArea(output));
	} // end main
}
