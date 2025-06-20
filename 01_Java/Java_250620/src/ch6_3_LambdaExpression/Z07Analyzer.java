package ch6_3_LambdaExpression;

//Interface for analyzing strings
interface StringAnalyzer6 {
	boolean analyze(String sourceStr, String searchStr);
}

//Class that checks if a string contains another string by implementing StringAnalyzer
class ContainsAnalyzer6 implements StringAnalyzer6 {
	// Override the analyze method to check if target contains searchStr
	@Override
	public boolean analyze(String target, String searchStr) {
		return target.contains(searchStr);
	}
}

//Utility class for analyzing arrays of strings
public class Z07Analyzer {
	// Static method to search an array of strings using a StringAnalyzer
	public static void searchArr(String[] strList, String searchStr, StringAnalyzer6 analyzer) {
		for (String currentStr : strList) {
			if (analyzer.analyze(currentStr, searchStr)) {
				System.out.println("Match: " + currentStr);
			}
		}
	}

//Main class to run the program
	public static void main(String[] args) {
		// Array of strings to search through
		String[] strList = { "tomorrow", "toto", "to", "timbukto", "the", "hello", "heat" };
		String searchStr = "to"; // String to search for
		
		System.out.println("Searching for: " + searchStr);
		
		// Lambda expressions stored in variables for reuse
		StringAnalyzer6 contains = (t, s) -> t.contains(s);
		StringAnalyzer6 startsWith = (t, s) -> t.startsWith(s);
		
		// Using 'contains' lambda
		System.out.println("==Contains==");
		Z07Analyzer.searchArr(strList, searchStr, contains);
		
		// Using 'startsWith' lambda
		System.out.println("==Starts With==");
		Z07Analyzer.searchArr(strList, searchStr, startsWith);
	}
}
