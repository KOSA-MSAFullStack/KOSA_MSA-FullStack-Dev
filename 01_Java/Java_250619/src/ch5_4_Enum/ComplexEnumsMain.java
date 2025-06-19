package ch5_4_Enum;

//Enum to represent the power states with descriptions
enum PowerState {
	OFF("The power is off"), ON("The usage power is high"), SUSPEND("The power usage is low");

//Description of each power state
	private String description;

//Constructor to initialize the description
	private PowerState(String d) {
		description = d;
	}

//Getter method to retrieve the description
	public String getDescription() {
		return description;
	}
}

//Class representing a Computer with a power state
class Computer {
	private PowerState state;

//Method to set the computer's power state
	public void setState(PowerState state) {
		this.state = state;
	}

//Method to get the computer's current power state
	public PowerState getState() {
		return state;
	}
}

//Main class to test the PowerState enum with the Computer class
public class ComplexEnumsMain {
	public static void main(String[] args) {
		// Create a new Computer object
		Computer comp = new Computer();

		// Set the computer's state to SUSPEND
		comp.setState(PowerState.SUSPEND);

		// Print the current state and its description
		System.out.println("Current state: " + comp.getState());
		System.out.println("Description: " + comp.getState().getDescription());
	}
}
