package ch6_1_Java_Interface;
// default Method

//Interface for sales calculations with a default report method
interface SalesCalcs4 {
	String getName(); // Method to get the product name
	double calcSalesPrice(); // Method to calculate sales price
	double calcCost(); // Method to calculate cost
	double calcProfit(); // Method to calculate profit
//Default method to print a single item sales report

	default void printItemReport() {
		System.out.println("--" + this.getName() + " Report--");
		System.out.println("Sales Price: $" + this.calcSalesPrice());
		System.out.println("Cost: $" + this.calcCost());
		System.out.println("Profit: $" + this.calcProfit());
		System.out.println("-----------------------------");
	}

//Static method to print reports for an array of items
	static void printItemArray(SalesCalcs4[] items) {
		System.out.println("== Sales Report ==");
		for (SalesCalcs4 item : items) {
			item.printItemReport();
		}
	}
}

//Class representing Crushed Rock product implementing SalesCalcs4 interface
class CrushedRock4 implements SalesCalcs4 {
	private String name = "Crushed Rock"; // Product name
	private double salesPrice; // Sales price per pound
	private double cost; // Cost per pound
	private double weight; // Weight in pounds
//Constructor to initialize sales price, cost, and weight

	public CrushedRock4(double salesPrice, double cost, double weight) {
		this.salesPrice = salesPrice;
		this.cost = cost;
		this.weight = weight;
	}

//Getter method for product name
	@Override
	public String getName() {
		return this.name;
	}

//Method to calculate total sales price
	@Override
	public double calcSalesPrice() {
		return this.salesPrice * this.weight;
	}

//Method to calculate total cost
	@Override
	public double calcCost() {
		return this.cost * this.weight;
	}

//Method to calculate profit (sales price - cost)
	@Override
	public double calcProfit() {
		return this.calcSalesPrice() - this.calcCost();
	}
}

//Class representing Red Paint product implementing SalesCalcs4 interface
class RedPaint4 implements SalesCalcs4 {
	private String name = "Red Paint";
	private double salesPrice;
	private double cost;
	private double volume; // In gallons

	public RedPaint4(double salesPrice, double cost, double volume) {
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
class Widget4 implements SalesCalcs4 {
	private String name = "Widget";
	private double salesPrice;
	private double cost;
	private int quantity;

	public Widget4(double salesPrice, double cost, int quantity) {
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
public class SalesReportMain3 {
	public static void main(String[] args) {
		// Creating an array of SalesCalcs4 items
		SalesCalcs4[] itemList = new SalesCalcs4[5];
		
		// Adding items to the list
		itemList[0] = new CrushedRock4(12, 10, 50);
		itemList[1] = new CrushedRock4(8, 6, 10);
		itemList[2] = new RedPaint4(10, 8, 25);
		itemList[3] = new Widget4(6, 5, 10);
		itemList[4] = new Widget4(14, 12, 20);
		
		// Printing the sales report using the static method
		SalesCalcs4.printItemArray(itemList);
	}
}
