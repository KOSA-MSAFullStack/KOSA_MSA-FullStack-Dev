package ch6_2_AnonymousInnerClass;

//Interface for analyzing strings
interface StringAnalyzer2 {
	boolean analyze(String sourceStr, String searchStr);
}

//Class that checks if a string contains another string by implementing StringAnalyzer
class ContainsAnalyzer2 implements StringAnalyzer2 {
	// Override the analyze method to check if target contains searchStr
	@Override
	public boolean analyze(String target, String searchStr) {
		return target.contains(searchStr);
	}
}

//Utility class for analyzing arrays of strings
public class Z03Analyzer {
	// Static method to search an array of strings using a StringAnalyzer
	public static void searchArr(String[] strList, String searchStr, StringAnalyzer2 analyzer) {
		for (String currentStr : strList) {
			if (analyzer.analyze(currentStr, searchStr)) {
				System.out.println("Match: " + currentStr);
			}
		}
	}

//Main class to run the program
	public static void main(String[] args) {
		// Array of strings to search through
		String[] strList01 = { "tomorrow", "toto", "to", "timbukto", "the", "hello", "heat" };
		String searchStr = "to"; // String to search for

		System.out.println("Searching for: " + searchStr);

		// Create an instance of ContainsAnalyzer
		ContainsAnalyzer2 contains = new ContainsAnalyzer2();
		
		System.out.println("===Contains===");
		// Call the searchArr method to search for matches
		Z03Analyzer.searchArr(strList01, searchStr, contains);
	}
}
