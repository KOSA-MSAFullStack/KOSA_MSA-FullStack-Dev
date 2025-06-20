package ch6_2_AnonymousInnerClass;

//Interface for analyzing strings
interface StringAnalyzer4 {
	boolean analyze(String sourceStr, String searchStr);
}

//Class that checks if a string contains another string by implementing StringAnalyzer
class ContainsAnalyzer4 implements StringAnalyzer4 {
	// Override the analyze method to check if target contains searchStr
	@Override
	public boolean analyze(String target, String searchStr) {
		return target.contains(searchStr);
	}
}

//Utility class for analyzing arrays of strings
public class Z05Analyzer {
	// Static method to search an array of strings using a StringAnalyzer
	public static void searchArr(String[] strList, String searchStr, StringAnalyzer4 analyzer) {
		for (String currentStr : strList) {
			if (analyzer.analyze(currentStr, searchStr)) {
				System.out.println("Match: " + currentStr);
			}
		}
	}

	public static void main(String[] args) {
		// Array of strings to search through
		String[] strList = { "tomorrow", "toto", "to", "timbukto", "the", "hello", "heat" };
		String searchStr = "to"; // String to search for
		
		System.out.println("Searching for: " + searchStr);
		
		// Lambda Expression replaces anonymous inner class
		System.out.println("==Contains==");
		Z05Analyzer.searchArr(strList, searchStr, (target, search) -> target.contains(search));
	}
}
