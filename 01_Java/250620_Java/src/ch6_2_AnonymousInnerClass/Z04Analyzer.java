package ch6_2_AnonymousInnerClass;

//Interface for analyzing strings
interface StringAnalyzer3 {
	boolean analyze(String sourceStr, String searchStr);
}

//Class that checks if a string contains another string by implementing StringAnalyzer
class ContainsAnalyzer3 implements StringAnalyzer3 {
	// Override the analyze method to check if target contains searchStr
	@Override
	public boolean analyze(String target, String searchStr) {
		return target.contains(searchStr);
	}
}

//Utility class for analyzing arrays of strings
public class Z04Analyzer {
	// Static method to search an array of strings using a StringAnalyzer
	public static void searchArr(String[] strList, String searchStr, StringAnalyzer3 analyzer) {
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

		// Implementing anonymous inner class for StringAnalyzer
		System.out.println("===Contains===");
		Z04Analyzer.searchArr(strList01, searchStr, new StringAnalyzer3() {
			@Override
			public boolean analyze(String target, String searchStr) {
				return target.contains(searchStr);
			}
		});
	}
}
