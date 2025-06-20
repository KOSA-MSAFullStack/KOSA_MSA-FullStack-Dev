package ch6_1_Java_Interface;

//Interface for sales calculations with a default report method
interface SalesCalcs3 {
	String getName(); // Method to get the product name
	double calcSalesPrice(); // Method to calculate sales price
	double calcCost(); // Method to calculate cost
	double calcProfit(); // Method to calculate profit
//Default method to print a sales report

	default void printItemReport() {
		System.out.println("--" + this.getName() + " Report--");
		System.out.println("Sales Price: $" + this.calcSalesPrice());
		System.out.println("Cost: $" + this.calcCost());
		System.out.println("Profit: $" + this.calcProfit());
		System.out.println("-----------------------------");
	}
}

//Class representing Crushed Rock product implementing SalesCalcs3 interface
class CrushedRock3 implements SalesCalcs3 {
	private String name = "Crushed Rock"; // Product name
	private double salesPrice; // Sales price per pound
	private double cost; // Cost per pound
	private double weight; // Weight in pounds
//Constructor to initialize sales price, cost, and weight

	public CrushedRock3(double salesPrice, double cost, double weight) {
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

//Class representing Red Paint product implementing SalesCalcs3 interface
class RedPaint3 implements SalesCalcs3 {
	private String name = "Red Paint";
	private double salesPrice;
	private double cost;
	private double volume; // In gallons

	public RedPaint3(double salesPrice, double cost, double volume) {
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

//Class representing Widget product implementing SalesCalcs3 interface
class Widget3 implements SalesCalcs3 {
	private String name = "Widget";
	private double salesPrice;
	private double cost;
	private int quantity;

	public Widget3(double salesPrice, double cost, int quantity) {
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
public class SalesReportMain2 {
	public static void main(String[] args) {
		// Creating an array of SalesCalcs3 items
		SalesCalcs3[] itemList = new SalesCalcs3[5];
		
		// Adding items to the list
		itemList[0] = new CrushedRock3(12, 10, 50);
		itemList[1] = new CrushedRock3(8, 6, 10);
		itemList[2] = new RedPaint3(10, 8, 25);
		itemList[3] = new Widget3(6, 5, 10);
		itemList[4] = new Widget3(14, 12, 20);
		
		// Printing the sales report
		System.out.println("== Sales Report ==");
		for (SalesCalcs3 item : itemList) {
			item.printItemReport();
		}
	}
}
