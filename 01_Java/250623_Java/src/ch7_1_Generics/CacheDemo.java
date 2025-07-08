package ch7_1_Generics;

//Cache class for storing strings
class CacheString {
	private String message; // Variable to store a string
//Method to add a string to the cache

	public void add(String message) {
		this.message = message;
	}

//Method to retrieve the cached string
	public String get() {
		return this.message;
	}
}

//Cache class for storing Shirt objects
class CacheShirt {
	private Shirt shirt; // Variable to store a Shirt object
//Method to add a Shirt object to the cache

	public void add(Shirt shirt) {
		this.shirt = shirt;
	}

//Method to retrieve the cached Shirt object
	public Shirt get() {
		return this.shirt;
	}
}

//Dummy Shirt class for demonstration purposes
class Shirt {
	private String color;
	private String size;

	public Shirt(String color, String size) {
		this.color = color;
		this.size = size;
	}

	@Override
	public String toString() {
		return "Shirt [Color: " + color + ", Size: " + size + "]";
	}
}

//Main class to demonstrate caching
public class CacheDemo {
	public static void main(String[] args) {
		// Cache for strings
		CacheString myMessage = new CacheString();
		
		myMessage.add("Save this for me");
		System.out.println("Cached Message: " + myMessage.get());
		
		// Cache for Shirt objects
		CacheShirt myShirt = new CacheShirt();
		Shirt shirt = new Shirt("Blue", "M");
		
		myShirt.add(shirt);
		System.out.println("Cached Shirt: " + myShirt.get());
	}
}
