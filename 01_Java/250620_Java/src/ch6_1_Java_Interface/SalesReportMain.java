package ch6_1_Java_Interface;

//Interface for sales calculations
interface SalesCalcs2 {
	String getName(); // Method to get the product name
	double calcSalesPrice(); // Method to calculate sales price
	double calcCost(); // Method to calculate cost
	double calcProfit(); // Method to calculate profit
}

//Class representing Crushed Rock product implementing SalesCalcs interface
class CrushedRock2 implements SalesCalcs2 {
	private String name = "Crushed Rock"; // Product name
	private double salesPrice = 0; // Sales price per pound
	private double cost = 0; // Cost per pound
	private double weight = 0; // Weight in pounds
//Constructor to initialize sales price, cost, and weight

	public CrushedRock2(double salesPrice, double cost, double weight) {
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

//Class representing Red Paint product implementing SalesCalcs interface
class RedPaint implements SalesCalcs2 {
	private String name = "Red Paint";
	private double salesPrice;
	private double cost;
	private double volume; // In gallons

	public RedPaint(double salesPrice, double cost, double volume) {
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

//Class representing Widget product implementing SalesCalcs interface
class Widget implements SalesCalcs2 {
	private String name = "Widget";
	private double salesPrice;
	private double cost;
	private int quantity;

	public Widget(double salesPrice, double cost, int quantity) {
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

//Class to print item data
class ItemReport {
	void printItemData(SalesCalcs2 item) {
		System.out.println("--" + item.getName() + " Report--");
		System.out.println("Sales Price: " + item.calcSalesPrice());
		System.out.println("Cost: " + item.calcCost());
		System.out.println("Profit: " + item.calcProfit());
	}
}

//Main class to run the program
public class SalesReportMain {
	public static void main(String[] args) {
		// Creating an array of SalesCalcs items
		SalesCalcs2[] itemList = new SalesCalcs2[5];
		ItemReport report = new ItemReport();
		
		// Adding items to the list
		itemList[0] = new CrushedRock2(12.0, 10.0, 50.0);
		itemList[1] = new CrushedRock2(8.0, 6.0, 10.0);
		itemList[2] = new RedPaint(10.0, 8.0, 25.0);
		itemList[3] = new Widget(6.0, 5.0, 10);
		itemList[4] = new Widget(14.0, 12.0, 20);
		
		// Printing the sales report
		System.out.println("== Sales Report ==");
		for (SalesCalcs2 item : itemList) {
			report.printItemData(item);
		}
	}
}
