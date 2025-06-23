package ch7_2_JavaCollectionFramework;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MapExample {
	public static void main(String[] args) {
		// Create a TreeMap with String keys and values
		Map<String, String> partList = new TreeMap<>();
		// Add key-value pairs to the map
		partList.put("S001", "Blue Polo Shirt");
		partList.put("S002", "Black Polo Shirt");
		partList.put("H001", "Duke Hat");
		// Overwrite the value for key "S002"
		partList.put("S002", "Black T-Shirt");
		// Get the set of keys from the map
		Set<String> keys = partList.keySet();
		// Print all key-value pairs in the map
		System.out.println("=== Part List ===");
		for (String key : keys) {
			System.out.println("Part#: " + key + " " + partList.get(key));
		}
		// Lambda Expressions
		System.out.println("=== Part List ===");
		partList.forEach((k, v) -> {
			System.out.println("Part#: " + k + " " + v);
		});
	}
}

