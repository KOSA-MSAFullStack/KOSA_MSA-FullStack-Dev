package ch6_1_Java_Interface;
// 인터페이스 다중 상속

//Interface for sales calculations with a default report method
interface SalesCalcs5 {
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
	static void printItemArray(SalesCalcs5[] items) {
		System.out.println("== Sales Report ==");
		for (SalesCalcs5 item : items) {
			item.printItemReport();
		}
	}
}

//Class representing Crushed Rock product implementing SalesCalcs5 interface
class CrushedRock5 implements SalesCalcs5 {
	private String name = "Crushed Rock"; // Product name
	private double salesPrice; // Sales price per pound
	private double cost; // Cost per pound
	private double weight; // Weight in pounds
//Constructor to initialize sales price, cost, and weight

	public CrushedRock5(double salesPrice, double cost, double weight) {
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

//Interface for Widget-specific calculations
interface WidgetSalesCalcs5 extends SalesCalcs5 {
	String getWidgetType(); // Method to get the widget type
}

//Base Widget class implementing SalesCalcs5
class Widget5 implements SalesCalcs5 {
	private String name = "Widget";
	private double salesPrice;
	private double cost;
	private long quantity;

	public Widget5(double salesPrice, double cost, long quantity) {
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

//Extended Widget class with an additional type attribute
class WidgetPro extends Widget5 implements WidgetSalesCalcs5 {
	private String type;

	public WidgetPro(double salesPrice, double cost, long quantity, String type) {
		super(salesPrice, cost, quantity);
		this.type = type;
	}

	@Override
	public String getWidgetType() {
		return type;
	}

	@Override
	public String getName() {
		return super.getName() + " - " + type;
	}
}

//Class representing Red Paint product implementing SalesCalcs5 interface
class RedPaint5 implements SalesCalcs5 {
	private String name = "Red Paint";
	private double salesPrice;
	private double cost;
	private double volume; // In gallons

	public RedPaint5(double salesPrice, double cost, double volume) {
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

//Main class to run the program
public class SalesReportMain5 {
	public static void main(String[] args) {
		// Creating an array of SalesCalcs5 items
		SalesCalcs5[] itemList = new SalesCalcs5[5];
		
		// Adding items to the list
		itemList[0] = new CrushedRock5(12, 10, 50);
		itemList[1] = new CrushedRock5(8, 6, 10);
		itemList[2] = new RedPaint5(10, 8, 25);
		itemList[3] = new Widget5(6, 5, 10);
		itemList[4] = new WidgetPro(14, 12, 20, "Premium");
		
		// Printing the sales report using the static method
		SalesCalcs5.printItemArray(itemList);
	}
}
