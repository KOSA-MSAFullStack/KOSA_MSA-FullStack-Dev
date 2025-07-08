package ch6_2_AnonymousInnerClass;

//Interface for analyzing strings
interface StringAnalyzer {
	boolean analyze(String sourceStr, String searchStr);
}
//Class that checks if a string contains another string by implementing StringAnalyzer
class ContainsAnalyzer implements StringAnalyzer {
	// Override the analyze method to check if target contains searchStr
	@Override
	public boolean analyze(String target, String searchStr) {
		return target.contains(searchStr);
	}
}

public class Z02Analyzer {
	// Main method to run the program
	public static void main(String[] args) {
		// Array of strings to search through
		String[] strList = { "tomorrow", "toto", "to", "timbukto", "the", "hello", "heat" };
		String searchStr = "to"; // String to search for
		
		System.out.println("Searching for: " + searchStr);
		
		// Create an instance of ContainsAnalyzer
		ContainsAnalyzer contains = new ContainsAnalyzer();
		System.out.println("===Contains===");
		
		// Iterate through each string in the list
		for (String currentStr : strList) {
			// Check if the current string contains the search string
			if (contains.analyze(currentStr, searchStr)) {
				System.out.println("Match: " + currentStr);
			}
		}
	}
}

