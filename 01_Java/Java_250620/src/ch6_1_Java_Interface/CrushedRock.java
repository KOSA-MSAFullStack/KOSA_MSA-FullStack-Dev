package ch6_1_Java_Interface;

//Interface for sales calculations
interface SalesCalcs {
	String getName(); // Method to get the product name
	double calcSalesPrice(); // Method to calculate sales price
	double calcCost(); // Method to calculate cost
	double calcProfit(); // Method to calculate profit
}

//Class representing Crushed Rock product implementing SalesCalcs interface
public class CrushedRock implements SalesCalcs {
	private String name = "Crushed Rock"; // Product name
	private double salesPrice = 0; // Sales price per pound
	private double cost = 0; // Cost per pound
	private double weight = 0; // Weight in pounds
//Constructor to initialize sales price, cost, and weight

	public CrushedRock(double salesPrice, double cost, double weight) {
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

//Main method to run the program
	public static void main(String[] args) {
		// Create CrushedRock objects
		CrushedRock rock1 = new CrushedRock(12, 10, 50); // Direct instance
		SalesCalcs rock2 = new CrushedRock(12, 10, 50); // Interface reference
		
		// Print the calculated sales prices
		System.out.println("Sales Price (rock1): $" + rock1.calcSalesPrice());
		System.out.println("Sales Price (rock2): $" + rock2.calcSalesPrice());
	}
}
