package ch6_1_Java_Interface;
// Interface 안 필드 (public static final-상수)

//Interface for sales calculations with a default report method
interface SalesCalcs6 {
	public static final String reportTitle = "\n==Static List Report==";
	String getName(); // Method to get the product name
	double calcSalesPrice(); // Method to calculate sales price
	double calcCost(); // Method to calculate cost
	double calcProfit(); // Method to calculate profit
	// Default method to print a single item sales report
	default void printItemReport() {
		System.out.println( reportTitle);
		System.out.println("--" + this.getName() + " Report--");
		System.out.println("Sales Price: $" + this.calcSalesPrice());
		System.out.println("Cost: $" + this.calcCost());
		System.out.println("Profit: $" + this.calcProfit());
		System.out.println("-----------------------------");
	}
	// Static method to print reports for an array of items
	static void printItemArray(SalesCalcs6[] items) {
		System.out.println("== Sales Report ==");
		for (SalesCalcs6 item : items) {
			item.printItemReport();
		}
	}
}
//Class representing Crushed Rock product implementing SalesCalcs4 interface
class CrushedRock6 implements SalesCalcs6 {
	private String name = "Crushed Rock"; // Product name
	private double salesPrice; // Sales price per pound
	private double cost; // Cost per pound
	private double weight; // Weight in pounds
	// Constructor to initialize sales price, cost, and weight
	public CrushedRock6(double salesPrice, double cost, double weight) {
		this.salesPrice = salesPrice;
		this.cost = cost;
		this.weight = weight;
	}
	// Getter method for product name
	@Override
	public String getName() {
		return this.name;
	}
	// Method to calculate total sales price
	@Override
	public double calcSalesPrice() {
		return this.salesPrice * this.weight;
	}
	// Method to calculate total cost
	@Override
	public double calcCost() {
		return this.cost * this.weight;
	}
	// Method to calculate profit (sales price - cost)
	@Override
	public double calcProfit() {
		return this.calcSalesPrice() - this.calcCost();
	}
}
//Class representing Red Paint product implementing SalesCalcs4 interface
class RedPaint6 implements SalesCalcs6 {
	private String name = "Red Paint";
	private double salesPrice;
	private double cost;
	private double volume; // In gallons
	public RedPaint6(double salesPrice, double cost, double volume) {
		this.salesPrice = salesPrice;
		this.cost = cost;
		this.volume = volume;
	}
	@Override
	public String getName() {
		return this.name;
	}
	@Override
	public double calcSalesPrice() {
		return this.salesPrice * this.volume;
	}
	@Override
	public double calcCost() {
		return this.cost * this.volume;
	}
	@Override
	public double calcProfit() {
		return this.calcSalesPrice() - this.calcCost();
	}
}
//Class representing Widget product implementing SalesCalcs4 interface
class Widget6 implements SalesCalcs6 {
	private String name = "Widget";
	private double salesPrice;
	private double cost;
	private int quantity;
	public Widget6(double salesPrice, double cost, int quantity) {
		this.salesPrice = salesPrice;
		this.cost = cost;
		this.quantity = quantity;
	}
	@Override
	public String getName() {
		return this.name;
	}
	@Override
	public double calcSalesPrice() {
		return this.salesPrice * this.quantity;
	}
	@Override
	public double calcCost() {
		return this.cost * this.quantity;
	}
	@Override
	public double calcProfit() {
		return this.calcSalesPrice() - this.calcCost();
	}
}
//Main class to run the program
public class SalesReportMain4 {
	public static void main(String[] args) {
		// Creating an array of SalesCalcs4 items
		SalesCalcs6[] itemList = new SalesCalcs6[5];
		// Adding items to the list
		itemList[0] = new CrushedRock6(12, 10, 50);
		itemList[1] = new CrushedRock6(8, 6, 10);
		itemList[2] = new RedPaint6(10, 8, 25);
		itemList[3] = new Widget6(6, 5, 10);
		itemList[4] = new Widget6(14, 12, 20);
		// Printing the sales report using the static method
		SalesCalcs6.printItemArray(itemList);
	}
}

