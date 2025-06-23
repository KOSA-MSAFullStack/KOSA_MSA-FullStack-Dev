package ch7_1_Generics;

//Cache class for storing strings
class CacheString2 {
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
class CacheShirt2 {
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

//Generic cache class
class CacheAny<T> {
	private T t; // Generic variable to store any type of object
//Method to add an item to the cache

	public void add(T t) {
		this.t = t;
	}

//Method to retrieve the cached item
	public T get() {
		return this.t;
	}
}

//Dummy Shirt class for demonstration purposes
class Shirt2 {
	private String color;
	private String size;

	public Shirt2(String color, String size) {
		this.color = color;
		this.size = size;
	}

	@Override
	public String toString() {
		return "Shirt [Color: " + color + ", Size: " + size + "]";
	}
}

//Main class to demonstrate caching
public class CacheDemo2 {
	public static void main(String[] args) {
		// Cache for strings
		CacheString2 myMessage = new CacheString2();
		
		myMessage.add("Save this for me");
		System.out.println("Cached Message: " + myMessage.get());
		
		
		
		// Cache for Shirt objects
		CacheShirt2 myShirt = new CacheShirt2();
		Shirt shirt = new Shirt("Blue", "M");
		
		myShirt.add(shirt);
		System.out.println("Cached Shirt: " + myShirt.get());
		
		
		
		// Generic caches
		CacheAny<String> myGenericMessage = new CacheAny<>();
		CacheAny<Shirt> myGenericShirt = new CacheAny<>();
		
		myGenericMessage.add("Save this for me (Generic)");
		myGenericShirt.add(new Shirt("Red", "L"));
		System.out.println("Generic Cached Message: " + myGenericMessage.get());
		System.out.println("Generic Cached Shirt: " + myGenericShirt.get());
	}
}
