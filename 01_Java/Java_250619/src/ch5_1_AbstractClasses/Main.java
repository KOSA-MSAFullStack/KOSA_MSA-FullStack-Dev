package ch5_1_AbstractClasses;

//The ElectronicDevice class represents a generic electronic device.
//It contains methods that all electronic devices should have.
//This is an example of generalization, where subclasses inherit common behavior.
class ElectronicDevice {
// Method to simulate turning on the electronic device.
// Subclasses may override this method to provide specific behavior.
	public void turnOn() {
		System.out.println("The electronic device is turned on.");
	}
// Method to simulate turning off the electronic device.
// Subclasses may override this method to provide specific behavior.
	public void turnOff() {
		System.out.println("The electronic device is turned off.");
	}
}
//The Television class is a subclass of ElectronicDevice.
//It inherits the generic methods but overrides them to perform television-specific operations.
class Television extends ElectronicDevice {
// Overridden method to turn on the Television.
// Instead of the generic behavior, it performs operations unique to a television.
@Override
public void turnOn() {
    // Change to the default channel (channel 1) upon turning on.
    changeChannel(1);
    // Initialize the screen settings.
    initializeScreen();
    // Optionally, display a message indicating the television is on.
    System.out.println("Television is now turned on.");
}
// Overridden method to turn off the Television.
// Currently, it simply displays a message, but additional shutdown operations could be added.
@Override
public void turnOff() {
    System.out.println("Television is now turned off.");
}
// Method to change the television channel.
// Accepts an integer representing the channel number.
public void changeChannel(int channel) {
    System.out.println("Changing to channel: " + channel);
}
// Method to initialize the television screen.
// This could involve setting up display parameters or other startup configurations.
public void initializeScreen() {
    System.out.println("Initializing the television screen.");
}
}
//The MobilePhone class is another subclass of ElectronicDevice.
//It also overrides the inherited methods to suit its specific needs.
class MobilePhone extends ElectronicDevice {
// Overriding the turnOn method to provide specific behavior for a MobilePhone.
	@Override
	public void turnOn() {
		System.out.println("The mobile phone is now powered up. Ready to use!");
	}
// Overriding the turnOff method to provide specific behavior for a MobilePhone.
	@Override
	public void turnOff() {
		System.out.println("The mobile phone is now powered down.");
	}
}
//Main class containing the main method, which is the entry point for the program.
public class Main {
	public static void main(String[] args) {
		// Demonstrating polymorphism:
		// A Television object is referenced by an ElectronicDevice variable.
		// This is allowed because Television is a subclass of ElectronicDevice.
		ElectronicDevice dev = new Television();
		dev.turnOn(); // Calls the overridden turnOn method in the Television class.
		dev.turnOff(); // Calls the overridden turnOff method in the Television class.
		// Additional example with a MobilePhone:
		// A MobilePhone object is also referenced by an ElectronicDevice variable.
		ElectronicDevice phone = new MobilePhone();
		phone.turnOn(); // Calls the overridden turnOn method in the MobilePhone class.
		phone.turnOff(); // Calls the overridden turnOff method in the MobilePhone class.
	}
}
