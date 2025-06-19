package ch5_1_AbstractClasses;

//Abstract base class representing a generic electronic device.
//By declaring this class abstract, we ensure that it cannot be instantiated directly.
//The abstract methods require each subclass to provide its own implementation.
abstract class ElectronicDevice2 {
	// Abstract method to simulate turning on the electronic device.
	// Each subclass must override this method to define its specific behavior.
	public abstract void turnOn();
	// Abstract method to simulate turning off the electronic device.
	// Each subclass must override this method to define its specific behavior.
	public abstract void turnOff();
}
//The Television class is a subclass of ElectronicDevice.
//It inherits the generic methods but overrides them to perform television-specific operations.
class Television2 extends ElectronicDevice2 {
	// Overridden method to turn on the Television.
	// Instead of the generic behavior, it performs operations unique to a
	// television.
	@Override
	public void turnOn() {
		// Change to the default channel (channel 1) upon turning on.
		changeChannel(1);
		// Initialize the screen settings.
		initializeScreen();
		// Optionally, display a message indicating the television is on.
		System.out.println("Television2 is now turned on.");
	}
	// Overridden method to turn off the Television.
	// Currently, it simply displays a message, but additional shutdown operations
	// could be added.
	@Override
	public void turnOff() {
		System.out.println("Television2 is now turned off.");
	}
	// Method to change the television channel.
	// Accepts an integer representing the channel number.
	public void changeChannel(int channel) {
		System.out.println("Changing to channel: " + channel);
	}
	// Method to initialize the television screen.
	// This could involve setting up display parameters or other startup
	// configurations.
	public void initializeScreen() {
		System.out.println("Initializing the television2 screen.");
	}
}
//The MobilePhone class is another subclass of ElectronicDevice.
//It also overrides the inherited methods to suit its specific needs.
class MobilePhone2 extends ElectronicDevice2 {
//Overriding the turnOn method to provide specific behavior for a MobilePhone.
	@Override
	public void turnOn() {
		System.out.println("The mobile phone2 is now powered up. Ready to use!");
	}
//Overriding the turnOff method to provide specific behavior for a MobilePhone.
	@Override
	public void turnOff() {
		System.out.println("The mobile phone2 is now powered down.");
	}
}
//Main class containing the main method, which is the entry point for the program.
public class Main2 {
	public static void main(String[] args) {
		// Demonstrating polymorphism:
		// A Television object is referenced by an ElectronicDevice variable.
		// This is allowed because Television is a subclass of ElectronicDevice.
		ElectronicDevice2 dev = new Television2();
		dev.turnOn(); // Calls the overridden turnOn method in the Television class.
		dev.turnOff(); // Calls the overridden turnOff method in the Television class.
		// Additional example with a MobilePhone:
		// A MobilePhone object is also referenced by an ElectronicDevice variable.
		ElectronicDevice2 phone = new MobilePhone2();
		phone.turnOn(); // Calls the overridden turnOn method in the MobilePhone class.
		phone.turnOff(); // Calls the overridden turnOff method in the MobilePhone class.
	}
}

